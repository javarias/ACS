package tmcdb.test;

import alma.ACS.ROdouble;
import alma.ACS.ROdoubleHelper;
import alma.ACS.ROdoublePOATie;
import alma.ACS.ROdoubleSeq;
import alma.ACS.ROdoubleSeqHelper;
import alma.ACS.ROdoubleSeqPOATie;
import alma.ACS.ROpattern;
import alma.ACS.ROpatternHelper;
import alma.ACS.ROpatternPOATie;
import alma.ACS.RWlong;
import alma.ACS.RWlongHelper;
import alma.ACS.RWlongPOATie;
import alma.ACS.RWlongSeq;
import alma.ACS.RWlongSeqHelper;
import alma.ACS.RWlongSeqPOATie;
import alma.ACS.doubleSeqHolder;
import alma.ACS.longSeqHolder;
import alma.ACS.impl.CharacteristicComponentImpl;
import alma.ACS.impl.ROdoubleImpl;
import alma.ACS.impl.ROdoubleSeqImpl;
import alma.ACS.impl.ROpatternImpl;
import alma.ACS.impl.RWlongImpl;
import alma.ACS.impl.RWlongSeqImpl;
import alma.ACS.jbaci.DataAccess;
import alma.TMCDB.MCtestComponentOperations;
import alma.acs.component.ComponentLifecycleException;
import alma.acs.container.ContainerServices;
import alma.acs.exceptions.AcsJException;

public class MCtestComponentImpl extends CharacteristicComponentImpl implements
		MCtestComponentOperations {

	private ROdouble doubleProp;
	private RWlong longProp;
	private RWlongSeq longSeqProp;
	private ROdoubleSeq doubleSeqProp;
	private ROpattern patternProp;
	
	@Override
	public void initialize(ContainerServices containerServices)
			throws ComponentLifecycleException {
		super.initialize(containerServices);
		
		{
			DataAccess<Double> doubleROPropDA = new MCTestDataAccess<Double>(-2.0, 134608945243381570L);
			ROdoubleImpl currentImpl =  new ROdoubleImpl("doubleProp", this, doubleROPropDA);
			ROdoublePOATie currentTie = new ROdoublePOATie(currentImpl);
			doubleProp = ROdoubleHelper.narrow(this.registerProperty(currentImpl, currentTie));
		}
		{
			DataAccess<Long> longROPropDA = new MCTestDataAccess<Long>(15L, 134608945243381570L);
			RWlongImpl currentImpl =  new RWlongImpl("longProp", this, longROPropDA);
			RWlongPOATie currentTie = new RWlongPOATie(currentImpl);
			longProp = RWlongHelper.narrow(this.registerProperty(currentImpl, currentTie));
		}
		{
			DataAccess<longSeqHolder> longSeqROPropDA = new MCTestDataAccessSeq<longSeqHolder>(
					new longSeqHolder(new int[25]), 134608945243381570L);
			RWlongSeqImpl currentImpl =  new RWlongSeqImpl("longSeqProp", this, longSeqROPropDA);
			RWlongSeqPOATie currentTie = new RWlongSeqPOATie(currentImpl);
			longSeqProp = RWlongSeqHelper.narrow(this.registerProperty(currentImpl, currentTie));
		}
		{
			DataAccess<doubleSeqHolder> doubleSeqROPropDA = new MCTestDataAccessSeq<doubleSeqHolder>(
					new doubleSeqHolder(new double[25]), 134608945243381570L);
			ROdoubleSeqImpl currentImpl =  new ROdoubleSeqImpl("doubleSeqProp", this, doubleSeqROPropDA);
			ROdoubleSeqPOATie currentTie = new ROdoubleSeqPOATie(currentImpl);
			doubleSeqProp = ROdoubleSeqHelper.narrow(this.registerProperty(currentImpl, currentTie));
		}
		{
			DataAccess<Long> patternROPropDA = new MCTestDataAccess<Long>(0x23L, 134608945243381570L);
			ROpatternImpl currentImpl =  new ROpatternImpl("patternProp", this, patternROPropDA);
			ROpatternPOATie currentTie = new ROpatternPOATie(currentImpl);
			patternProp = ROpatternHelper.narrow(this.registerProperty(currentImpl, currentTie));
		}
		
	}

	@Override
	public ROdoubleSeq doubleSeqProp() {
		return doubleSeqProp;
	}

	@Override
	public ROdouble doubleProp() {
		return doubleProp;
	}

	@Override
	public RWlongSeq longSeqProp() {
		return longSeqProp;
	}

	@Override
	public RWlong longProp() {
		return longProp;
	}

	@Override
	public ROpattern patternProp() {
		return patternProp;
	}

	@Override
	public void reset() {
		try {
			((ROdoubleImpl)doubleProp).getDataAccess().set(-2.0, null);
		} catch (AcsJException e) {
			e.printStackTrace();
		}
		try {
			((ROdoubleSeqImpl) doubleSeqProp).getDataAccess().set(new doubleSeqHolder(new double[25]), null);
		} catch (AcsJException e) {
			e.printStackTrace();
		}
	}


}
