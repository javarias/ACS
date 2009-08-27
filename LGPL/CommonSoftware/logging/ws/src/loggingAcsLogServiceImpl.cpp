#include <typeinfo>
#include <iostream>

#include <acscommonC.h>
#include <acsutilAnyAide.h>

#include "loggingAcsLogServiceImpl.h"

#define BATCH_LEN 100

void AcsLogServiceImpl::LogRecordBatch::
sendRecords(::Logging::XmlLogRecordSeq *reclist)
{
   CosNotification::StructuredEvent logging_event;
   logging_event.header.fixed_header.event_type.domain_name = 
      CORBA::string_dup(acscommon::LOGGING_DOMAIN);
   logging_event.header.fixed_header.event_type.type_name =  
      CORBA::string_dup(acscommon::LOGGING_TYPE);
   logging_event.header.fixed_header.event_name = CORBA::string_dup("");
   logging_event.header.variable_header.length (0); // put nothing here
   logging_event.filterable_data.length (0);

   /*if the log record list length is long enough, send it directly*/
   if (reclist->length() > 2){
      logging_event.remainder_of_body <<= *reclist;
      try{
         loggingSupplier_->send_event(logging_event);
         //for(unsigned int i = 0; i < reclist->length(); i++){
            //std::cout << (*reclist)[i].xml << std::endl;
         //}
      }catch(::CORBA::TRANSIENT &ex){
      }
   }
   /*otherwise, send the log Records one by one*/
   else{
      for (CORBA::ULong i = 0; i < reclist->length(); i++){
         logging_event.remainder_of_body <<= (*reclist)[i].xml;
         try{
            loggingSupplier_->send_event (logging_event);
         }catch(::CORBA::TRANSIENT &ex){
         }
      }
   }
}

AcsLogServiceImpl::LogRecordBatch::LogRecordBatch(): 
   size_(0),
   waitCond_(mutex_),
   shutdown_(false),
   nBuff(0)
{
   buffer[0].length(BATCH_LEN * 2);
   buffer[1].length(BATCH_LEN * 2);
   buffer[2].length(BATCH_LEN * 2);
   buffer_ = &buffer[0];
   ACE_Thread::spawn(static_cast<ACE_THR_FUNC>(
               AcsLogServiceImpl::LogRecordBatch::worker), this);
}

AcsLogServiceImpl::LogRecordBatch::~LogRecordBatch()
{
   sendRecords();
   waitCond_.signal();
   buffer_->length(0);
}

void AcsLogServiceImpl::LogRecordBatch::
sendRecords()
{
   batchMutex_.acquire();
   if (size_ > 0){
      /*save the current buffer status*/
      tmpBuffer = buffer_;
      tmpSize = size_;
      /*interchange the buffers*/
      nBuff = (nBuff + 1) % 3;
      buffer_ = &buffer[nBuff];
      size_ = 0;
      batchMutex_.release();
      /*send the buffer through NC*/
      tmpBuffer->length(tmpSize);
      sendRecords(tmpBuffer);
      /*prepare the size of the buffer*/
      tmpBuffer->length(BATCH_LEN * 2 );
      return;
   }
   batchMutex_.release();
}

void AcsLogServiceImpl::LogRecordBatch::
add(const ::Logging::XmlLogRecordSeq *reclist)
{
   batchMutex_.acquire();
   if (reclist->length() == 0){
      batchMutex_.release();
      return;
   }
   else if (reclist->length() > BATCH_LEN - 1){
      waitCond_.signal();
      batchMutex_.release();
      sendRecords(const_cast<Logging::XmlLogRecordSeq *>(reclist));
      return;
   }
   if(buffer_->length() < (size_ + reclist->length()))
      buffer_->length(size_ + reclist->length() + 10);

   for(CORBA::ULong i = 0; i < reclist->length(); i++, size_++)
      (*buffer_)[size_] = (*reclist)[i];
   if(size_ > BATCH_LEN){
      waitCond_.signal();
   }
   batchMutex_.release();
}

int AcsLogServiceImpl::LogRecordBatch::svc()
{
   sendRecords(buffer_);
   while(!shutdown_){
      ACE_Time_Value timeout = ACE_OS::gettimeofday() + ACE_Time_Value(1, 0);
      mutex_.acquire();
      waitCond_.wait(&timeout);
      mutex_.release();
      if(!shutdown_)
         sendRecords();
   }
   return 0;
}

void* AcsLogServiceImpl::LogRecordBatch::worker(void *arg)
{
   static_cast<AcsLogServiceImpl::LogRecordBatch*>(arg)->svc();
   return 0;
}

void AcsLogServiceImpl::LogRecordBatch::
set_logging_supplier(ACSStructuredPushSupplier* supplier)
{
   loggingSupplier_ = supplier;
}

AcsLogServiceImpl::AcsLogServiceImpl(CORBA::ORB_ptr orb,
		PortableServer::POA_ptr poa,
		TAO_LogMgr_i &logmgr_i,
		DsLogAdmin::LogMgr_ptr factory,
		DsLogAdmin::LogId id) : ACSLog_i(orb, poa, logmgr_i, factory, id)
{
}

AcsLogServiceImpl::~AcsLogServiceImpl()
{
}

void AcsLogServiceImpl::writeRecords (const Logging::XmlLogRecordSeq &reclist)
{
	if (reclist.length() <= 0)
		return;

	// Check if supplier is given
	if (!this->m_logging_supplier)
		return;

	// Check the operational status.
	if (this->op_state_ == DsLogAdmin::disabled)
		return;

	// Check if the log is on duty
	// @@ Wait for Comittee ruling on the proper behavior
	DsLogAdmin::AvailabilityStatus avail_stat =
		this->get_availability_status ();

	//I don't know what do this code, but I put anyway...
	if (avail_stat.off_duty == 1)
	{

		// why are we off duty? investigate ...
		// Check if the log is full.
		if (avail_stat.log_full == 1)
		{
			throw DsLogAdmin::LogFull (0);
		}
		else   // Check the administrative state.
			if (this->get_administrative_state() == DsLogAdmin::locked)
			{
				throw DsLogAdmin::LogLocked ();
			}
			else
				return; // we are not scheduled at this time.
	}

   logStat.receivedLogs += reclist.length();
   if (supOutput == NULL)
      recordBatch.add(&reclist);

}

Logging::LogStatistics AcsLogServiceImpl::getStatistics()
{
	return logStat;
}

void AcsLogServiceImpl::set_logging_supplier
(ACSStructuredPushSupplier* supplier)
{
   ACSLog_i::set_logging_supplier(supplier);
   recordBatch.set_logging_supplier(supplier);
}
