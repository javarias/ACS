/*
 * ALMA - Atacama Large Millimiter Array (c) European Southern Observatory, 2007
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
 * 
 */
package alma.acsplugins.alarmsystem.gui.table;

import java.util.HashMap;
import java.util.Vector;

import alma.alarmsystem.clients.CategoryClient;

import cern.laser.client.data.Alarm;

/**
 * A container for the alarms as needed by the AlarmTableModel
 * <P>
 * It is composed of 2 collections:
 * <OL>
 *  <LI> the <code>HashMap</code> stores each entry accessed by its alarmID (the key)
 *  <LI> the <code>Vector</code> of <code>Strings</code> used to remember the position of 
 *  						each alarm when the max number of alarms has been reached
 *                          It also allows to access the alarms by row
 * </OL>         
 * Basically the array stores the alarmID of each row in the table.
 * The content of the row i.e. the Alarm, is then obtained by the
 * HashMap passing the alarmID.
 * <BR>
 * In this way it is possible to get the entry of a row by getting
 * the key from the Vector. And it is possible to get an alarm
 * from the HashMap.
 * 
 * @author acaproni
 *
 */
public class AlarmsContainer {
	
	/**
	 * The exception generated by the Alarm Container 
	 * 
	 * @author acaproni
	 *
	 */
	public class AlarmContainerException extends Exception {

		/**
		 * Constructor
		 * 
		 * @see java.lang.Exception
		 */
		public AlarmContainerException() {
			super();
		}

		/**
		 * Constructor
		 * 
		 * @see java.lang.Exception
		 */
		public AlarmContainerException(String message, Throwable cause) {
			super(message, cause);
		}

		/**
		 * Constructor
		 * 
		 * @see java.lang.Exception
		 */
		public AlarmContainerException(String message) {
			super(message);
		}

		/**
		 * Constructor
		 * 
		 * @see java.lang.Exception
		 */
		public AlarmContainerException(Throwable cause) {
			super(cause);
		}
		
	}

	/**
	 * The entries in the table
	 */
	private HashMap<String, AlarmTableEntry> entries = new HashMap<String, AlarmTableEntry>();
	
	/**
	 * The index when the reduction rules are not applied
	 * <P>
	 * Each item in the vector represents the ID of the entry 
	 * shown in a table row when the reduction rules are not used.
	 */
	private final Vector<String> index = new Vector<String>();
	
	/**
	 * The maximum number of alarms to store in the container
	 */
	private final int maxAlarms;
	
	/**
	 * Build an AlarmContainer 
	 * 
	 * @param max The max number of alarms to store in the container
	 * @param panel The <code>AlarmPanel</code>
	 */
	public AlarmsContainer(int max) {
		maxAlarms=max;
	}
	
	/**
	 * Return the number of alarms in the container.
	 * 
	 * @return The number of alarms in the container
	 */
	public synchronized int size() {
		return index.size();
	}
	
	/**
	 * Add an entry (i.e a alarm) in the collection.
	 * <P>
	 * If there is no room available in the container,
	 * an exception is thrown.
	 * Checking if there is enough room must be done by the
	 * caller.
	 * 
	 * @param alarm The not null entry to add
	 * @throw {@link AlarmContainerException} If the entry is already in the container
	 */
	public synchronized void add(AlarmTableEntry entry) throws AlarmContainerException {
		if (entry==null) {
			throw new IllegalArgumentException("The entry can't be null");
		}
		if (entry.getAlarm().getAlarmId()==null ||entry.getAlarm().getAlarmId().length()==0) {
			throw new IllegalStateException("The alarm ID is invalid");
		}
		if (index.size()>=maxAlarms) {
			throw new ArrayIndexOutOfBoundsException("Container full");
		}
		if (entries.containsKey(entry.getAlarm().getAlarmId())) {
			throw new AlarmContainerException("Alarm already in the Container");
		}
		if (index.contains(entry.getAlarm().getAlarmId())) {
			// entries contains the key but index not!!!!
			throw new IllegalStateException("Inconsistency between index and entries");
		}
		index.add(entry.getAlarm().getAlarmId());
		entries.put(entry.getAlarm().getAlarmId(), entry);
	}

	/**
	 * Check if an alarm with the given ID is in the container
	 * 
	 * @param alarmID The ID of the alarm
	 * @return true if an entries for an alarm with the given ID exists
	 *              in the container
	 */
	public synchronized boolean contains(String alarmID) {
		if (alarmID==null) {
			throw new IllegalArgumentException("The ID can't be null");
		}
		return entries.containsKey(alarmID);
	}
	
	/**
	 * Return the entry in the given position
	 * 
	 * @param pos The position of the alarm in the container
	 * @param reduced <code>true</code> if the alarms in the table are reduced
	 * @return The AlarmTableEntry in the given position
	 */
	public synchronized AlarmTableEntry get(int pos) {
		if (pos<0) {
			throw new IllegalArgumentException("The position must be greater then 0");
		}
		if (pos>index.size()) {
			throw new IndexOutOfBoundsException("Can't acces item at pos "+pos+": [0,"+index.size()+"]");
		}
		String ID = index.get(pos);
		AlarmTableEntry ret = get(ID);
		if (ret==null) {
			throw new IllegalStateException("Inconsistent state of container");
		}
		return ret;
	}
	
	/**
	 * Return the entry with the given ID
	 * 
	 * @param id The not null ID of the alarm in the container
	 * @return The AlarmTableEntry with the given position
	 *         <code>null</code>if the container does not contain an entry for the given id
	 */
	public synchronized AlarmTableEntry get(String id) {
		if (id==null) {
			throw new IllegalArgumentException("The ID can't be null");
		}
		return entries.get(id);
	}
	
	/**
	 * Remove all the elements in the container
	 */
	public synchronized void clear() {
		index.clear();
		entries.clear();
	}
	
	/**
	 * Remove the oldest entry in the container
	 * 
	 * @return The removed item
	 * @throws AlarmContainerException If the container is empty
	 */
	public synchronized AlarmTableEntry removeOldest() throws AlarmContainerException {
		if (index.size()==0) {
			throw new AlarmContainerException("The container is empty");
		}
		String ID = index.remove(0);
		if (ID==null) {
			throw new IllegalStateException("The index vector returned a null item");
		}
		AlarmTableEntry ret = entries.remove(ID);
		if (ret==null) {
			throw new IllegalStateException("The entries  HashMap contains a null entry");
		}
		return ret;
	}
	
	/**
	 * Remove the entry for the passed alarm
	 * 
	 * @param alarm The alarm whose entry must be removed
	 * @throws AlarmContainerException If the alarm is not in the container
	 */
	public synchronized void remove(Alarm alarm) throws AlarmContainerException {
		if (alarm==null) {
			throw new IllegalArgumentException("The alarm can't be null");
		}
		String ID=alarm.getAlarmId();
		int pos=index.indexOf(ID);
		if (pos<0) {
			throw new AlarmContainerException("Alarm not in the container");
		}
		index.remove(pos);
		AlarmTableEntry oldEntry = entries.remove(ID);
		if (oldEntry==null) {
			throw new IllegalStateException("The ID was in index but not in entries");
		}
	}
	
	/**
	 * Remove all the inactive alarms of a given type.
	 * <P>
	 * If the type is INACTIVE all inactive alarms are deleted
	 * regardless of their priority
	 * 
	 * @param type The type of the inactive alarms
	 * @return The number of alarms removed
	 */
	public synchronized int removeInactiveAlarms(AlarmGUIType type) throws AlarmContainerException {
		if (type==null) {
			throw new IllegalArgumentException("The type can't be null");
		}
		Vector<String> keys = new Vector<String>();
		for (String key: entries.keySet()) {
			keys.add(new String(key));
		}
		int ret=0;
		for (String key: keys) {
			AlarmTableEntry alarm = entries.get(key);
			if (alarm==null) {
				throw new IllegalStateException("Got a null alarm for key "+key);
			}
			if (alarm.getAlarm().getStatus().isActive()) {
				continue;
			}
			if (type==AlarmGUIType.INACTIVE || alarm.getAlarm().getPriority()==type.id) {
				// Remove the alarm
				remove(alarm.getAlarm());
				ret++;
			}
		}
		return ret;
	}
	
	/**
	 * Replace the alarm in a row with passed one.
	 * <P>
	 * The entry to replace the alarm is given by the alarm ID of the parameter.
	 * 
	 * @param newAlarm The not null new alarm 
	 * @throws AlarmContainerException if the entry is not in the container
	 */
	public synchronized void replace(Alarm newAlarm) throws AlarmContainerException {
		if (newAlarm==null) {
			throw new IllegalArgumentException("The alarm can't be null");
		}
		int pos = index.indexOf(newAlarm.getAlarmId());
		if (pos<0) {
			throw new AlarmContainerException("Entry no present in the container");
		}
		AlarmTableEntry entry = entries.get(newAlarm.getAlarmId());
		if (entry==null) {
			// There was no entry for this ID
			throw new IllegalStateException("Inconsistent state of index and entries");
		}
		entry.updateAlarm(newAlarm);
		// If active, move the item in the head of the container
		if (newAlarm.getStatus().isActive()) {
			String key = index.remove(pos);
			index.insertElementAt(key, 0);
		}
	}
	
	
}
