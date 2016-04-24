package tmcdb.test.properties;

import alma.ACS.ROboolean;
import alma.ACS.RObooleanHelper;
import alma.ACS.RObooleanPOATie;
import alma.ACS.RObooleanSeq;
import alma.ACS.RObooleanSeqHelper;
import alma.ACS.RObooleanSeqPOATie;
import alma.ACS.ROdouble;
import alma.ACS.ROdoubleHelper;
import alma.ACS.ROdoublePOATie;
import alma.ACS.ROdoubleSeq;
import alma.ACS.ROdoubleSeqHelper;
import alma.ACS.ROdoubleSeqPOATie;
import alma.ACS.ROfloat;
import alma.ACS.ROfloatHelper;
import alma.ACS.ROfloatPOATie;
import alma.ACS.ROfloatSeq;
import alma.ACS.ROfloatSeqHelper;
import alma.ACS.ROfloatSeqPOATie;
import alma.ACS.ROlong;
import alma.ACS.ROlongHelper;
import alma.ACS.ROlongLong;
import alma.ACS.ROlongLongHelper;
import alma.ACS.ROlongLongPOATie;
import alma.ACS.ROlongPOATie;
import alma.ACS.ROlongSeq;
import alma.ACS.ROlongSeqHelper;
import alma.ACS.ROlongSeqPOATie;
import alma.ACS.ROpattern;
import alma.ACS.ROpatternHelper;
import alma.ACS.ROpatternPOATie;
import alma.ACS.ROstring;
import alma.ACS.ROstringHelper;
import alma.ACS.ROstringPOATie;
import alma.ACS.ROuLong;
import alma.ACS.ROuLongLong;
import alma.ACS.ROuLongSeq;
import alma.ACS.ROuLongSeqHelper;
import alma.ACS.ROuLongSeqPOATie;
import alma.ACS.RWboolean;
import alma.ACS.RWbooleanSeq;
import alma.ACS.RWdouble;
import alma.ACS.RWdoubleSeq;
import alma.ACS.RWfloat;
import alma.ACS.RWfloatSeq;
import alma.ACS.RWlong;
import alma.ACS.RWlongLong;
import alma.ACS.RWlongSeq;
import alma.ACS.RWpattern;
import alma.ACS.RWstring;
import alma.ACS.RWuLong;
import alma.ACS.RWuLongLong;
import alma.ACS.RWuLongSeq;
import alma.ACS.impl.CharacteristicComponentImpl;
import alma.ACS.impl.RObooleanImpl;
import alma.ACS.impl.RObooleanSeqImpl;
import alma.ACS.impl.ROdoubleImpl;
import alma.ACS.impl.ROdoubleSeqImpl;
import alma.ACS.impl.ROfloatImpl;
import alma.ACS.impl.ROfloatSeqImpl;
import alma.ACS.impl.ROlongImpl;
import alma.ACS.impl.ROlongLongImpl;
import alma.ACS.impl.ROlongSeqImpl;
import alma.ACS.impl.ROpatternImpl;
import alma.ACS.impl.ROstringImpl;
import alma.ACS.impl.ROulongSeqImpl;
import alma.ACS.jbaci.DataAccess;
import alma.TMCDB.EnumTest;
import alma.TMCDB.MCtestPropertiesComponentOperations;
import alma.TMCDB.ROEnumTest;
import alma.TMCDB.RWEnumTest;
import alma.acs.component.ComponentLifecycleException;
import alma.acs.container.ContainerServices;

public class MCtestPropertiesComponentImpl extends CharacteristicComponentImpl
		implements MCtestPropertiesComponentOperations {

	//RO
	private ROdouble m_doubleROProp_p;
	private double m_doubleROVal = 0.0;
	private long m_time1 = 134608945243381570L;
	private DataAccess<Double> m_doubleRODevIO = new MCtestDevIONoIncremental<Double>(m_time1, m_doubleROVal);
	
	private ROfloat m_floatROProp_p;
	private float m_floatROVal = 0.0F;
	private long m_time2 = 134608945243381570L;
	private DataAccess<Float> m_floatRODevIO = new MCtestDevIONoIncremental<Float>(m_time2, m_floatROVal);
	
	private ROlong m_longROProp_p;
	private long m_longROVal = 0;
	private long m_time3 = 134608945243381570L;
	private DataAccess<Long> m_longRODevIO = new MCtestDevIONoIncremental<Long>(m_time3, m_longROVal);
	
	private ROuLong m_uLongROProp_p;
	private long m_uLongROVal = 0;
	private long m_time4 = 134608945243381570L;
	private DataAccess<Long> m_uLongRODevIO = new MCtestDevIONoIncremental<Long>(m_time4, m_uLongROVal);
	
	private ROpattern m_patternROProp_p;
	private long m_patternROVal = 0;
	private long m_time5 = 134608945243381570L;
	private DataAccess<Long> m_patternRODevIO = new MCtestDevIONoIncremental<Long>(m_time5, m_patternROVal);
	
	private ROstring m_stringROProp_p;
	private String m_stringROVal = "";
	private long m_time6 = 134608945243381570L;
	private DataAccess<String> m_stringRODevIO = new MCtestDevIONoIncremental<String>(m_time6, m_stringROVal);
	
	private ROlongLong m_longLongROProp_p;
	private long m_longLongROVal = 0;
	private long m_time7 = 134608945243381570L;
	private DataAccess<Long> m_longLongRODevIO = new MCtestDevIONoIncremental<Long>(m_time7, m_longLongROVal);
	
	private ROuLongLong m_uLongLongROProp_p;
	private long m_uLongLongROVal = 0;
	private long m_time8 = 134608945243381570L;
	private DataAccess<Long> m_ulongLongRODevIO = new MCtestDevIONoIncremental<Long>(m_time8, m_uLongLongROVal);
	
	private ROboolean m_booleanROProp_p;
	private boolean m_booleanROVal = false;
	private long m_time9 = 134608945243381570L;
	private DataAccess<Boolean> m_booleanRODevIo = new MCtestDevIONoIncremental<Boolean>(m_time9, m_booleanROVal);
	
	private ROdoubleSeq m_doubleSeqROProp_p;
	private double[] m_doubleSeqROVal = new double[] {0.0, 0.0};
	private long m_time10 = 134608945243381570L;
	private DataAccess<double[]> m_doubleSeqRODevIO = new MCtestDevIONoIncremental<double[]>(m_time10, m_doubleSeqROVal);
	
	private ROfloatSeq m_floatSeqROProp_p;
	private float[] m_floatSeqROVal  = new float[] {0.0F, 0.0F};
	private long m_time11 = 134608945243381570L;
	private DataAccess<float[]> m_floatSeqRODevIO = new MCtestDevIONoIncremental<float[]>(m_time11, m_floatSeqROVal);
	
	private ROlongSeq m_longSeqROProp_p;
	private long[] m_longSeqROVal = new long[] {0, 0};
	private long m_time12 = 134608945243381570L;
	private DataAccess<long[]> m_longSeqRODevIO = new MCtestDevIONoIncremental<>(m_time12, m_longSeqROVal);
	
	private ROuLongSeq m_uLongSeqROProp_p;
	private long[] m_uLongSeqROVal = new long[] {0, 0};
	private long m_time13 = 134608945243381570L;
	private DataAccess<long[]> m_ulongSeqRODevIO = new MCtestDevIONoIncremental<long[]>(m_time13, m_uLongSeqROVal);
	
	private RObooleanSeq m_booleanSeqROProp_p;
	private boolean[] m_booleanSeqROVal = new boolean[]{false, false};
	private long m_time14 = 134608945243381570L;
	private DataAccess<boolean[]> m_booleanSeqRODevIO = new MCtestDevIONoIncremental<boolean[]>(m_time14, m_booleanSeqROVal);
	
	private ROEnumTest m_EnumTestROProp_p;
	private EnumTest m_EnumTestROVal = EnumTest.STATE1;
	private long m_time15 = 134608945243381570L;
	private DataAccess<EnumTest> m_EnumTestRODevIO = new MCtestDevIONoIncremental<EnumTest>(m_time15, m_EnumTestROVal);
	
	//RW
	private RWdouble m_doubleRWProp_p;
	private long m_time16;
	private RWfloat m_floatRWProp_p;
	private long m_time17;
	private RWlong m_longRWProp_p;
	private long m_time18;
	private RWuLong m_uLongRWProp_p;
	private long m_time19;
	private RWpattern m_patternRWProp_p;
	private long m_time20;
	private RWstring m_stringRWProp_p;
	private long m_time21;
	private RWlongLong m_longLongRWProp_p;
	private long m_time22;
	private RWuLongLong m_uLongLongRWProp_p;
	private long m_time23;
	private RWboolean m_booleanRWProp_p;
	private long m_time24;
	private RWdoubleSeq m_doubleSeqRWProp_p;
	private long m_time25;
	private RWfloatSeq m_floatSeqRWProp_p;
	private long m_time26;
	private RWlongSeq m_longSeqRWProp_p;
	private long m_time27;
	private RWuLongSeq m_uLongSeqRWProp_p;
	private long m_time28;
	private RWbooleanSeq m_booleanSeqRWProp_p;
	private long m_time29;
	private RWEnumTest m_EnumTestRWProp_p;
	private long m_time30;
	
	private EnumTest m_EnumTestRWVal;
	
	public MCtestPropertiesComponentImpl() {
		
	}
	
	@Override
	public void initialize(ContainerServices containerServices) throws ComponentLifecycleException {
		super.initialize(containerServices);
		{
			ROdoubleImpl impl = new ROdoubleImpl("doubleROProp", this, m_doubleRODevIO);
			ROdoublePOATie tie = new ROdoublePOATie(impl);
			m_doubleROProp_p = ROdoubleHelper.narrow(registerProperty(impl, tie));
		}
		{
			ROfloatImpl impl =  new ROfloatImpl("floatROProp", this, m_floatRODevIO);
			ROfloatPOATie tie = new ROfloatPOATie(impl);
			m_floatROProp_p =  ROfloatHelper.narrow(registerProperty(impl, tie));
		}
		{
			ROlongImpl impl = new ROlongImpl("longROProp", this, m_longRODevIO);
			ROlongPOATie tie = new ROlongPOATie(impl);
			m_longROProp_p = ROlongHelper.narrow(registerProperty(impl, tie));
		}
		{
			RObooleanImpl impl = new RObooleanImpl("booleanROProp", this, m_booleanRODevIo);
			RObooleanPOATie tie = new RObooleanPOATie(impl);
			m_booleanROProp_p = RObooleanHelper.narrow(registerProperty(impl, tie));
		}
		{
			ROpatternImpl impl = new ROpatternImpl("patterROProp", this, m_patternRODevIO);
			ROpatternPOATie tie = new ROpatternPOATie(impl);
			m_patternROProp_p = ROpatternHelper.narrow(registerProperty(impl, tie));
		}
		{
			ROstringImpl impl = new ROstringImpl("stringROProp", this, m_stringRODevIO);
			ROstringPOATie tie = new ROstringPOATie(impl);
			m_stringROProp_p = ROstringHelper.narrow(registerProperty(impl, tie));
		}
		{
			ROlongLongImpl impl = new ROlongLongImpl("longlongROProp", this, m_longLongRODevIO);
			ROlongLongPOATie tie = new ROlongLongPOATie(impl);
			m_longLongROProp_p = ROlongLongHelper.narrow(registerProperty(impl, tie));
		}
		{
			RObooleanImpl impl = new RObooleanImpl("booleanROProp", this, m_booleanRODevIo);
			RObooleanPOATie tie = new RObooleanPOATie(impl);
			m_booleanROProp_p = RObooleanHelper.narrow(registerProperty(impl, tie));
		}
		{
			ROdoubleSeqImpl impl = new ROdoubleSeqImpl("doubleSeqROProp", this, m_doubleSeqRODevIO);
			ROdoubleSeqPOATie tie = new ROdoubleSeqPOATie(impl);
			m_doubleSeqROProp_p = ROdoubleSeqHelper.narrow(registerProperty(impl, tie));
		}
		{
			ROfloatSeqImpl impl = new ROfloatSeqImpl("floatSeqROProp", this, m_floatSeqRODevIO);
			ROfloatSeqPOATie tie = new ROfloatSeqPOATie(impl);
			m_floatSeqROProp_p = ROfloatSeqHelper.narrow(registerProperty(impl, tie));
		}
		{
			ROlongSeqImpl impl = new ROlongSeqImpl("longSeqROProp", this, m_longSeqRODevIO);
			ROlongSeqPOATie tie = new ROlongSeqPOATie(impl);
			m_longSeqROProp_p = ROlongSeqHelper.narrow(registerProperty(impl, tie));
		}
		{
			ROulongSeqImpl impl = new ROulongSeqImpl("ulongSeqROProp", this, m_ulongSeqRODevIO);
			ROuLongSeqPOATie tie = new ROuLongSeqPOATie(impl);
			m_uLongSeqROProp_p = ROuLongSeqHelper.narrow(registerProperty(impl, tie));
		}
		{
			RObooleanSeqImpl impl = new RObooleanSeqImpl("booleanSeqROProp", this, m_booleanSeqRODevIO);
			RObooleanSeqPOATie tie = new RObooleanSeqPOATie(impl);
			m_booleanSeqROProp_p = RObooleanSeqHelper.narrow(registerProperty(impl, tie));
		}
	}

	@Override
	public ROdouble doubleROProp() {
		return m_doubleROProp_p;
	}

	@Override
	public ROfloat floatROProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ROlong longROProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ROuLong uLongROProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ROpattern patternROProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ROstring stringROProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ROlongLong longLongROProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ROuLongLong uLongLongROProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ROboolean booleanROProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ROdoubleSeq doubleSeqROProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ROfloatSeq floatSeqROProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ROlongSeq longSeqROProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ROuLongSeq uLongSeqROProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RObooleanSeq booleanSeqROProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ROEnumTest EnumTestROProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RWdouble doubleRWProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RWfloat floatRWProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RWlong longRWProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RWuLong uLongRWProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RWpattern patternRWProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RWstring stringRWProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RWlongLong longLongRWProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RWuLongLong uLongLongRWProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RWboolean booleanRWProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RWdoubleSeq doubleSeqRWProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RWfloatSeq floatSeqRWProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RWlongSeq longSeqRWProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RWuLongSeq uLongSeqRWProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RWbooleanSeq booleanSeqRWProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RWEnumTest EnumTestRWProp() {
		// TODO Auto-generated method stub
		return null;
	}

}
