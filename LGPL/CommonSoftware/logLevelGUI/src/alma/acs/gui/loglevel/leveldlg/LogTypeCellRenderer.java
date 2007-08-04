/*
 *    ALMA - Atacama Large Millimiter Array
 *    (c) European Southern Observatory, 2002
 *    Copyright by ESO (in the framework of the ALMA collaboration)
 *    and Cosylab 2002, All rights reserved
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 2.1 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public
 *    License along with this library; if not, write to the Free Software
 *    Foundation, Inc., 59 Temple Place, Suite 330, Boston, 
 *    MA 02111-1307  USA
 */
package alma.acs.gui.loglevel.leveldlg;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.cosylab.logging.engine.log.LogTypeHelper;
import com.cosylab.logging.settings.LogTypeRenderer;

/**
 * The renderer for log types
 * 
 * @author acaproni
 *
 */
public class LogTypeCellRenderer implements TableCellRenderer {
	
	private JComboBox levelCB=new JComboBox(LogTypeHelper.getAllTypesDescriptions());
	private LogTypeRenderer levelRenderer = new LogTypeRenderer();
	
	public LogTypeCellRenderer() {
		levelCB.setSelectedIndex(0);
		levelCB.setEditable(false);
		levelCB.setMaximumRowCount(LogTypeHelper.getNumberOfTypes());
		levelCB.setRenderer(levelRenderer);
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected) {
			levelCB.setBackground(table.getSelectionBackground());
			levelCB.setForeground(table.getSelectionForeground());
        } else {
        	levelCB.setBackground(table.getBackground());
        	levelCB.setForeground(table.getForeground());
        }
		// Set the text
		//minimum.setText(value.toString());
		levelCB.setFont(table.getFont());
        
        // If the type of log is known, set the icon
		Integer logType;
		try {
			logType=Integer.parseInt(value.toString());
		} catch (Exception e) {
			System.err.println("Error parsing a log type: "+value.toString());
			e.printStackTrace(System.err);
			logType=null;
		}
        
        if (logType!=null) {
	            levelCB.setSelectedIndex(logType);
        } 
		return levelCB;
	}
	
}
