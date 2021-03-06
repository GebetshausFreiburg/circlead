/*
 * Circlead - Develop and structure evolutionary Organisations
 * 
 * @author Matthias Wegner
 * @version 0.1
 * @since 01.07.2018
 * 
 */
package org.rogatio.circlead.model.work;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.rogatio.circlead.control.synchronizer.ISynchronizer;

/**
 * The Interface IWorkitem.
 * 
 * @author Matthias Wegner
 */
public interface IWorkitem {

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType();

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle();

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public String getVersion();

	/**
	 * Sets the version.
	 *
	 * @param version
	 *            the new version
	 */
	public void setVersion(String version);

	/**
	 * Sets the created.
	 *
	 * @param date
	 *            the new created
	 */
	public void setCreated(Date date);

	/**
	 * Sets the created.
	 *
	 * @param xmlDate
	 *            the new created
	 */
	public void setCreated(String xmlDate);

	/**
	 * Gets the updateable.
	 *
	 * @return the updateable
	 */
	public boolean getUpdateable();
	
	/**
	 * Sets the updateable.
	 *
	 * @param updateFlag the new updateable
	 */
	public void setUpdateable(boolean updateFlag);
	
	/**
	 * Gets the modified.
	 *
	 * @return the modified
	 */
	public Date getModified();

	/**
	 * Gets the created.
	 *
	 * @return the created
	 */
	public Date getCreated();

	/**
	 * Sets the modified.
	 *
	 * @param date
	 *            the new modified
	 */
	public void setModified(Date date);

	/**
	 * Removes the id.
	 *
	 * @param synchronizer
	 *            the synchronizer
	 */
	public void removeId(ISynchronizer synchronizer);

	/**
	 * Removes the id.
	 *
	 * @param id
	 *            the id
	 */
	public void removeId(String id);
	
	/**
	 * Gets the referenced items.
	 *
	 * @return the referenced items
	 */
	public List<IWorkitem> getReferencedItems();

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public HashMap<String, ISynchronizer> getId();

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id
	 * @param synchronizer
	 *            the synchronizer
	 */
	public void setId(String id, ISynchronizer synchronizer);

	/**
	 * Sets the ids.
	 *
	 * @param ids
	 *            the ids
	 */
	public void setIds(HashMap<String, ISynchronizer> ids);

	/**
	 * Gets the id.
	 *
	 * @param synchronizer
	 *            the synchronizer
	 * @return the id
	 */
	public String getId(ISynchronizer synchronizer);

	/**
	 * Sets the modified.
	 *
	 * @param xmlDate
	 *            the new modified
	 */
	public void setModified(String xmlDate);

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus();

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the new status
	 */
	public void setStatus(String status);

	/**
	 * Sets the title.
	 *
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title);

}
