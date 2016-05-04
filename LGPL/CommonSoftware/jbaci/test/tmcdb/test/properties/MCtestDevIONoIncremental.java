package tmcdb.test.properties;

import alma.ACS.jbaci.DataAccess;
import alma.ACS.jbaci.DataAccessSupport;
import alma.ACSErr.CompletionHolder;
import alma.acs.exceptions.AcsJException;

public class MCtestDevIONoIncremental<T> extends DataAccessSupport<T> implements DataAccess<T> {

	private T value;
	private final long timestamp;
	
	public MCtestDevIONoIncremental(long timestamp, T value) {
		this.value = value;
		this.timestamp = timestamp;
	}

	@Override
	public T get(CompletionHolder completionHolder) throws AcsJException {
		return value;
	}

	@Override
	public boolean initializeValue() {
		return true;
	}

	@Override
	public void set(T value, CompletionHolder completion) throws AcsJException {
		this.value = value;
	}

}
