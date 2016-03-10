package tmcdb.test;

import alma.ACS.jbaci.DataAccess;
import alma.ACS.jbaci.DataAccessSupport;
import alma.ACSErr.CompletionHolder;
import alma.acs.exceptions.AcsJException;

public class MCTestDataAccess<T extends Number> extends DataAccessSupport<T> implements DataAccess<T> {

	private long timestamp = 0L;
	private T value;
	
	public MCTestDataAccess (T value, long timstamp) {
		this.value = value;
		this.timestamp = timstamp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T get(CompletionHolder completionHolder) throws AcsJException {
		timestamp += 1000; //increment 1 seg
		Double tmp = value.doubleValue() + 1;
		value = (T)tmp;
		return value;
	}

	@Override
	public boolean initializeValue() {
		return true;
	}

	@Override
	public void set(T value, CompletionHolder completion)
			throws AcsJException {
		timestamp = System.currentTimeMillis();
		this.value = value;
	}

}
