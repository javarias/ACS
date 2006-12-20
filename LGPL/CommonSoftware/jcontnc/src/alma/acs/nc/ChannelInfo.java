/*
 * ALMA - Atacama Large Millimiter Array (c) European Southern Observatory, 2004
 * Copyright by ESO (in the framework of the ALMA collaboration), All rights
 * reserved
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */

package alma.acs.nc;

import java.util.logging.Logger;
import java.util.HashMap;

import com.cosylab.CDB.DAO;

import alma.acs.component.client.ComponentClient;
import alma.acs.container.ContainerServices;

/**
 * ChannelInfo is intended to provide various tidbits on information on CORBA
 * Notification Channels to the ACS NC APIs.
 * 
 * @author dfugate
 * @version $Id$
 */
public class ChannelInfo {
   /**
    * Used strictly for testing purposes at the moment. Should be moved to the
    * test directory???
    * 
    * @param args
    */
   static public void main(String[] args) {
      try {
         // get a client to get at the CS
         ComponentClient client = new ComponentClient(null, System
               .getProperty("ACS.manager"), "ChannelInfoTestClient");
         // create a channel info
         ChannelInfo joe = new ChannelInfo(client.getContainerServices());
         String[] cNames = { "cdb_channel", "blarIL", "no_channel" };
         for (int i = 0; i < cNames.length; i++) {
            System.out.println("Channel:" + cNames[i] + ", HashMap:"
                  + joe.getEventHandlerTimeoutMap(cNames[i]));
         }
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * standard logger
    */
   private Logger            m_logger   = null;

   /** our own private copy of the container services. used
    * to access the ACS CDB among other things
    */
   private ContainerServices m_services = null;

   /**
    * Constructor.
    * 
    * @param services
    *           A component or client's container services.
    */
   public ChannelInfo(ContainerServices services) {
      m_services = services;
      // immediately grab a logger
      m_logger = m_services.getLogger();
   }

   /**
    * The following returns a map where each key is the name of an event and the
    * value is the maximum amount of time (in floating point seconds) an event receiver has 
    * to process the event before a warning message is logged.
    * 
    * @param channelName name of the channel
    * @return HashMap described above
    */
   public HashMap<String, Double> getEventHandlerTimeoutMap(String channelName) {
      // initialize the return value
      HashMap<String, Double> retVal = new HashMap<String, Double>();

      // data access object to traverse the ACS CDB
      DAO dao = null;

      // keys into the DAO
      String[] keys = null;

      // get the dao for the channel...
      // ...if this fails, just return.
      try {
         dao = m_services.getCDB().get_DAO_Servant("MACI/Channels/" + channelName);
      }
      catch (Exception e) {
         m_logger.finer("No CDB entry found for '" + channelName + "' channel");
         return retVal;
      }

      // names of all the events
      try {
         keys = dao.get_string_seq("Events");
      }
      catch (Exception e) {
         m_logger.finer("CDB entry found for '" + channelName
               + "' but no Events element.");
         return retVal;
      }

      // sanity check on the number of events
      if (keys.length == 0) {
         m_logger.finer("No event definitions found for the '" + channelName
               + "' within the CDB.");
         return retVal;
      }

      // populate the hashmap
      for (int i = 0; i < keys.length; i++) {
         // determine the value location
         String timeoutLocation = "Events/" + keys[i] + "/MaxProcessTime";
         // get the value (floating point seconds)
         try {
            double value = dao.get_double(timeoutLocation);
            retVal.put(keys[i], new Double(value));
         }
         catch (Exception e) {
            e.printStackTrace();
            m_logger.severe("Could not convert 'MaxProcessTime' to floating "
                  + "point seconds for the '" + channelName + "' channel and '"
                  + keys[i] + "' event type.");
         }
      }

      return retVal;
   }
}
