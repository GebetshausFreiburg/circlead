/*
 * Circlead - Develop and structure evolutionary Organisations
 * 
 * @author Matthias Wegner
 * @version 0.1
 * @since 01.07.2018
 * 
 */
package org.rogatio.circlead.model.work;

import static org.rogatio.circlead.model.Parameter.ACCOUNTABLE;
import static org.rogatio.circlead.model.Parameter.ACTIVITY;
import static org.rogatio.circlead.model.Parameter.ACTIVITYID;
import static org.rogatio.circlead.model.Parameter.BPMN;
import static org.rogatio.circlead.model.Parameter.CONSULTANT;
import static org.rogatio.circlead.model.Parameter.DESCRIPTION;
import static org.rogatio.circlead.model.Parameter.INFORMED;
import static org.rogatio.circlead.model.Parameter.PARENTACTIVITY;
import static org.rogatio.circlead.model.Parameter.RESPONSIBLE;
import static org.rogatio.circlead.model.Parameter.RESULT;
import static org.rogatio.circlead.model.Parameter.SUCCESSOR;
import static org.rogatio.circlead.model.Parameter.SUPPORTER;
import static org.rogatio.circlead.model.Parameter.USEDROLES;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;
import org.rogatio.circlead.control.synchronizer.ISynchronizer;
import org.rogatio.circlead.control.synchronizer.SynchronizerFactory;
import org.rogatio.circlead.control.synchronizer.atlassian.parser.ActivityTableParserElement;
import org.rogatio.circlead.control.synchronizer.atlassian.parser.Parser;
import org.rogatio.circlead.control.synchronizer.file.FileSynchronizer;
import org.rogatio.circlead.control.validator.IValidator;
import org.rogatio.circlead.control.validator.ValidationMessage;
import org.rogatio.circlead.model.data.ActivityDataitem;
import org.rogatio.circlead.model.data.HowTo;
import org.rogatio.circlead.model.data.IDataitem;
import org.rogatio.circlead.util.ObjectUtil;
import org.rogatio.circlead.util.PropertyUtil;
import org.rogatio.circlead.util.StringUtil;
import org.rogatio.circlead.view.renderer.ISynchronizerRendererEngine;
import org.rogatio.circlead.view.renderer.IWorkitemRenderer;

/**
 * The Class Activity is the working item for a activity.
 *
 * @author Matthias Wegner
 */
public class Activity extends DefaultWorkitem implements IWorkitemRenderer, IValidator {

	/** The Constant LOGGER. */
	@SuppressWarnings("unused")
	private final static Logger LOGGER = LogManager.getLogger(Activity.class);

	/**
	 * Instantiates a new activity. Has not set an id.
	 */
	public Activity() {
		this.dataitem = new ActivityDataitem();
	}

	/**
	 * Instantiates a new activity from dataitem
	 *
	 * @param dataitem the dataitem of the activity of class
	 *                 {@link org.rogatio.circlead.model.data.ActivityDataitem}
	 */
	public Activity(IDataitem dataitem) {
		super(dataitem);

		/*
		 * throws exception if dataitem is not ActivityDataitem
		 */
		if (!(dataitem instanceof ActivityDataitem)) {
			throw new IllegalArgumentException("IDataitem must be of type ActivityDataitem");
		}
	}

	/**
	 * Instantiates a new activity from mapped data. Key must be a
	 * string-representation of {@link org.rogatio.circlead.model.Parameter}.
	 *
	 * @param data the data to set the item
	 */
	public Activity(Map<String, String> data) {
		this.dataitem = new ActivityDataitem();
		this.setAid(data.get(ACTIVITYID.toString()));
		this.setBPMN(data.get(BPMN.toString()));
		this.setChild(data.get(SUCCESSOR.toString()));
		this.setDescription(data.get(DESCRIPTION.toString()));
		this.setTitle(data.get(ACTIVITY.toString()));
		this.setResults(data.get(RESULT.toString()));
		this.setResponsibleIdentifier(data.get(RESPONSIBLE.toString()));
		this.setSupplierIdentifier(data.get(SUPPORTER.toString()));
		this.setConsultantIdentifier(data.get(CONSULTANT.toString()));
		this.setInformedIdentifier(data.get(INFORMED.toString()));
	}

	/**
	 * Gets the role identifier for the responsible role of the activity
	 *
	 * @return the responsible role identifier
	 */
	public String getResponsibleIdentifier() {
		return this.getDataitem().getResponsible();
	}

	/**
	 * Gets the accountable identifier for the accountable role of the activity
	 *
	 * @return the accountable role identifier
	 */
	public String getAccountableIdentifier() {
		return this.getDataitem().getAccountable();
	}

	/**
	 * Gets the informed role identifiers for the informed roles of the activity
	 *
	 * @return the informed role identifiers as list
	 */
	public List<String> getInformedIdentifiers() {
		return this.getDataitem().getInformed();
	}

	/**
	 * Gets the supplier role identifiers.
	 *
	 * @return the supplier role identifiers as list
	 */
	public List<String> getSupplierIdentifiers() {
		return this.getDataitem().getSupplier();
	}

	/**
	 * Gets the consultant role identifiers for the activity
	 *
	 * @return the consultant role identifiers of the activity
	 */
	public List<String> getConsultantIdentifiers() {
		return this.getDataitem().getConsultant();
	}

	/**
	 * Sets the role identifier of the responsible role for the activity.
	 *
	 * @param roleIdentifier the new responsible role identifier
	 */
	public void setResponsibleIdentifier(String roleIdentifier) {
		this.getDataitem().setResponsible(roleIdentifier);
	}

	/**
	 * Gets the results.
	 *
	 * @return the results
	 */
	public String getResults() {
		return this.getDataitem().getResults();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rogatio.circlead.model.work.DefaultWorkitem#getReferencedItems()
	 */
	@Override
	public List<IWorkitem> getReferencedItems() {
		List<IWorkitem> references = new ArrayList<IWorkitem>();

		if (StringUtil.isNotNullAndNotEmpty(this.getResponsibleIdentifier())) {
			Role role = R.getRole(getResponsibleIdentifier());
			if (role != null) {
				if (!references.contains(role)) {
					references.add(role);
				}
			}
		}

		if (StringUtil.isNotNullAndNotEmpty(this.getAccountableIdentifier())) {
			Role role = R.getRole(getAccountableIdentifier());
			if (role != null) {
				if (!references.contains(role)) {
					references.add(role);
				}
			}
		}

		List<String> ri = this.getSupplierIdentifiers();
		if (ObjectUtil.isListNotNullAndEmpty(ri)) {
			for (String r : ri) {
				Role role = R.getRole(r);
				if (role != null) {
					if (!references.contains(role)) {
						references.add(role);
					}
				}
			}
		}

		ri = this.getConsultantIdentifiers();
		if (ObjectUtil.isListNotNullAndEmpty(ri)) {
			for (String r : ri) {
				Role role = R.getRole(r);
				if (role != null) {
					if (!references.contains(role)) {
						references.add(role);
					}
				}
			}
		}

		ri = this.getInformedIdentifiers();
		if (ObjectUtil.isListNotNullAndEmpty(ri)) {
			for (String r : ri) {
				Role role = R.getRole(r);
				if (role != null) {
					if (!references.contains(role)) {
						references.add(role);
					}
				}
			}
		}

		List<ActivityDataitem> sa = this.getSubactivities();
		for (ActivityDataitem activityDataitem : sa) {

			Activity a = R.getActivity(activityDataitem.getTitle());
			if (a != null) {
				if (!references.contains(a)) {
					references.add(a);
				}
			}

			if (StringUtil.isNotNullAndNotEmpty(activityDataitem.getResponsible())) {
				Role role = R.getRole(activityDataitem.getResponsible());
				if (role != null) {
					if (!references.contains(role)) {
						references.add(role);
					}
				}
			}

			if (StringUtil.isNotNullAndNotEmpty(activityDataitem.getAccountable())) {
				Role role = R.getRole(activityDataitem.getAccountable());
				if (role != null) {
					if (!references.contains(role)) {
						references.add(role);
					}
				}
			}

			ri = activityDataitem.getConsultant();
			if (ObjectUtil.isListNotNullAndEmpty(ri)) {
				for (String r : ri) {
					Role role = R.getRole(r);
					if (role != null) {
						if (!references.contains(role)) {
							references.add(role);
						}
					}
				}
			}

			ri = activityDataitem.getSupplier();
			if (ObjectUtil.isListNotNullAndEmpty(ri)) {
				for (String r : ri) {
					Role role = R.getRole(r);
					if (role != null) {
						if (!references.contains(role)) {
							references.add(role);
						}
					}
				}
			}

			ri = activityDataitem.getInformed();
			if (ObjectUtil.isListNotNullAndEmpty(ri)) {
				for (String r : ri) {
					Role role = R.getRole(r);
					if (role != null) {
						if (!references.contains(role)) {
							references.add(role);
						}
					}
				}
			}

		}

		return references;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.getDataitem().getDescription();
	}

	/**
	 * Gets the child.
	 *
	 * @return the child
	 */
	public String getChild() {
		return this.getDataitem().getChild();
	}

	/**
	 * Gets the bpmn.
	 *
	 * @return the bpmn
	 */
	public String getBpmn() {
		return this.getDataitem().getBpmn();
	}

	/**
	 * Gets the id of activity (=aid).
	 *
	 * @return the aid
	 */
	public String getAid() {
		return this.getDataitem().getAid();
	}

	/**
	 * Sets the results.
	 *
	 * @param results the new results
	 */
	public void setResults(String results) {
		this.getDataitem().setResults(results);
	}

	/**
	 * Sets the bpmn.
	 *
	 * @param bpmn the new bpmn
	 */
	public void setBPMN(String bpmn) {
		this.getDataitem().setBpmn(bpmn);
	}

	/**
	 * Sets the child.
	 *
	 * @param child the new child
	 */
	public void setChild(String child) {
		this.getDataitem().setChild(child);
	}

	/**
	 * Sets the id of activity (aid).
	 *
	 * @param aid the new aid
	 */
	public void setAid(String aid) {
		this.getDataitem().setAid(aid);
	}

	/**
	 * Sets the description of the activity.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.getDataitem().setDescription(description);
	}

	/**
	 * Sets the consultant identifier.
	 *
	 * @param roleIdentifiers the new consultant identifier
	 */
	public void setConsultantIdentifier(String roleIdentifiers) {
		this.setConsultantIdentifier(StringUtil.toList(roleIdentifiers));
	}

	/**
	 * Sets the informed identifier.
	 *
	 * @param roleIdentifiers the new informed identifier
	 */
	public void setInformedIdentifier(String roleIdentifiers) {
		this.setInformedIdentifier(StringUtil.toList(roleIdentifiers));
	}

	/**
	 * Sets the supplier identifier.
	 *
	 * @param roleIdentifiers the new supplier identifier
	 */
	public void setSupplierIdentifier(String roleIdentifiers) {
		this.setSupplierIdentifier(StringUtil.toList(roleIdentifiers));
	}

	/**
	 * Sets the supplier identifier.
	 *
	 * @param roleIdentifiers the new supplier identifier
	 */
	public void setSupplierIdentifier(List<String> roleIdentifiers) {
		this.getDataitem().setSupplier(roleIdentifiers);
	}

	/**
	 * Sets the consultant identifier.
	 *
	 * @param roleIdentifiers the new consultant identifier
	 */
	public void setConsultantIdentifier(List<String> roleIdentifiers) {
		this.getDataitem().setConsultant(roleIdentifiers);
	}

	/**
	 * Sets the informed identifier.
	 *
	 * @param roleIdentifiers the new informed identifier
	 */
	public void setInformedIdentifier(List<String> roleIdentifiers) {
		this.getDataitem().setInformed(roleIdentifiers);
	}

	/**
	 * Sets the accountable identifier.
	 *
	 * @param roleIdentifier the new accountable identifier
	 */
	public void setAccountableIdentifier(String roleIdentifier) {
		this.getDataitem().setAccountable(roleIdentifier);
	}

	/**
	 * Sets the subactivities from html-parser.
	 *
	 * @param table the new subactivities
	 */
	public void setSubactivities(ActivityTableParserElement table) {
		this.setSubactivities(table.getActivities());
	}

	/**
	 * Sets the subactivities.
	 *
	 * @param subactivities the new subactivities
	 */
	public void setSubactivities(List<ActivityDataitem> subactivities) {
		this.getDataitem().setSubactivities(subactivities);
	}

	/**
	 * Gets the subactivities with responsible.
	 *
	 * @param responsible the responsible
	 * @return the subactivities with responsible
	 */
	public List<ActivityDataitem> getSubactivitiesWithResponsible(String responsible) {
		List<ActivityDataitem> list = new ArrayList<ActivityDataitem>();
		if (ObjectUtil.isListNotNullAndEmpty(this.getSubactivities())) {
			for (ActivityDataitem sub : this.getSubactivities()) {

				if (StringUtil.isNotNullAndNotEmpty(sub.getResponsible())) {
					if (sub.getResponsible().equals(responsible)) {
						list.add(sub);
					}
				}
			}
		}
		return list;
	}

	public String getNeighbourResponsibilitySubactivity(ActivityDataitem subactivity) {

		List<ActivityDataitem> list = this.getSubactivities();

		if (StringUtil.isNotNullAndNotEmpty(subactivity.getResponsible())) {
			return subactivity.getResponsible();
		}

		if (ObjectUtil.isListNotNullAndEmpty(list)) {
			int index = list.indexOf(subactivity);
			if (index + 1 < list.size()) {
				ActivityDataitem s = list.get(index + 1);
				if (StringUtil.isNotNullAndNotEmpty(s.getResponsible())) {
					return s.getResponsible();
				}
			}
			if (index > 1) {
				ActivityDataitem s = list.get(index - 1);
				if (StringUtil.isNotNullAndNotEmpty(s.getResponsible())) {
					return s.getResponsible();
				}
			}
			if (index + 2 < list.size()) {
				ActivityDataitem s = list.get(index + 2);
				if (StringUtil.isNotNullAndNotEmpty(s.getResponsible())) {
					return s.getResponsible();
				}
			}
			if (index > 2) {
				ActivityDataitem s = list.get(index - 2);
				if (StringUtil.isNotNullAndNotEmpty(s.getResponsible())) {
					return s.getResponsible();
				}
			}
		}

		return null;
	}

	public List<ActivityDataitem> getChildSubactivities(ActivityDataitem subactivity) {
		List<ActivityDataitem> list = new ArrayList<ActivityDataitem>();
		if (ObjectUtil.isListNotNullAndEmpty(subactivity.getLinkReferences())) {
			for (String childAid : subactivity.getLinkReferences()) {
				ActivityDataitem c = getSubactivityWithAid(childAid);
				if (!list.contains(c)) {
					list.add(c);
				}
			}
		}

		return list;
	}

	public ActivityDataitem getSubactivityWithAid(String aid) {
		if (StringUtil.isNotNullAndNotEmpty(aid)) {
			if (ObjectUtil.isListNotNullAndEmpty(this.getSubactivities())) {
				for (ActivityDataitem sub : this.getSubactivities()) {
					if (aid.equals(sub.getAid())) {
						return sub;
					}
				}
			}
		}
		return null;
	}

	public List<String> getResponsiblesFromSubactivities() {
		List<String> list = new ArrayList<String>();
		if (ObjectUtil.isListNotNullAndEmpty(this.getSubactivities())) {
			for (ActivityDataitem sub : this.getSubactivities()) {
				if (StringUtil.isNotNullAndNotEmpty(sub.getResponsible())) {
					if (!list.contains(sub.getResponsible())) {
						list.add(sub.getResponsible());
					}
				}
			}
		}
		return list;
	}

	/**
	 * Gets the subactivities with responsible.
	 *
	 * @return the subactivities with responsible
	 */
	public List<ActivityDataitem> getSubactivitiesWithResponsible() {
		List<ActivityDataitem> list = new ArrayList<ActivityDataitem>();
		if (ObjectUtil.isListNotNullAndEmpty(this.getSubactivities())) {
			for (ActivityDataitem sub : this.getSubactivities()) {
				if (StringUtil.isNotNullAndNotEmpty(sub.getResponsible())) {
					list.add(sub);
				}
			}
		}
		return list;
	}

	/**
	 * Gets the subactivities.
	 *
	 * @return the subactivities
	 */
	public List<ActivityDataitem> getSubactivities() {
		return this.getDataitem().getSubactivities();
	}

	/**
	 * Sets the how tos.
	 *
	 * @param howtos the new how tos
	 */
	public void setHowTos(List<String> howtos) {
		this.getDataitem().setHowtos(howtos);
	}

	/**
	 * Gets the how tos.
	 *
	 * @return the how tos
	 */
	public List<String> getHowTos() {
		return this.getDataitem().getHowtos();
	}

	/**
	 * Sets the how tos.
	 *
	 * @param howtos the new how tos
	 */
	public void setHowTos(String howtos) {
		List<String> list = Arrays.asList(howtos.split(","));
		this.setHowTos(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rogatio.circlead.model.work.DefaultWorkitem#getDataitem()
	 */
	@Override
	public ActivityDataitem getDataitem() {
		return (ActivityDataitem) dataitem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rogatio.circlead.model.work.DefaultWorkitem#toString()
	 */
	@Override
	public String toString() {
		return this.getDataitem().toString() + ", type=" + getType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rogatio.circlead.view.IRenderer#render()
	 */
	@Override
	public Element render(ISynchronizer synchronizer) {
		ISynchronizerRendererEngine renderer = synchronizer.getRenderer();

		Element element = new Element("p");
		if (StringUtil.isNotNullAndNotEmpty(getAid())) {
			renderer.addItem(element, ACTIVITYID.toString(), this.getAid());
		}

		Activity a = R.getActivityWithSubactivity(this.getTitle());
		if (a != null) {
			renderer.addActivityItem(element, PARENTACTIVITY.toString(), a.getTitle());
		}

		renderer.addH2(element, USEDROLES.toString());
		if (StringUtil.isNotNullAndNotEmpty(getResponsibleIdentifier())) {
			renderer.addRoleItem(element, RESPONSIBLE.toString(), this.getResponsibleIdentifier());
		} else {
			renderer.addRoleItem(element, RESPONSIBLE.toString(), "-");
		}

		if (StringUtil.isNotNullAndNotEmpty(getAccountableIdentifier())) {
			renderer.addRoleItem(element, ACCOUNTABLE.toString(), this.getAccountableIdentifier());
		} else {
			renderer.addRoleItem(element, ACCOUNTABLE.toString(), "-");
		}

		List<Role> roles = R.getRoles(this.getSupplierIdentifiers());
		if (ObjectUtil.isListNotNullAndEmpty(roles)) {
			renderer.addItem(element, SUPPORTER.toString() + ":", "");
			renderer.addRoleList(element, roles);
		}

		roles = R.getRoles(this.getConsultantIdentifiers());
		if (ObjectUtil.isListNotNullAndEmpty(roles)) {
			renderer.addItem(element, CONSULTANT.toString() + ":", "");
			renderer.addRoleList(element, roles);
		}

		roles = R.getRoles(this.getInformedIdentifiers());
		if (ObjectUtil.isListNotNullAndEmpty(roles)) {
			renderer.addItem(element, INFORMED.toString() + ":", "");
			renderer.addRoleList(element, roles);
		}

		if (StringUtil.isNotNullAndNotEmpty(this.getDescription())) {
			renderer.addH2(element, DESCRIPTION.toString());
			renderer.addItem(element, this.getDescription());
		}

		if (StringUtil.isNotNullAndNotEmpty(this.getResults())) {
			renderer.addH2(element, RESULT.toString());
			renderer.addItem(element, this.getResults());
		}

		List<HowTo> howtos = R.getIndexHowTos();
		if (this.getHowTos() != null) {
			for (String ht : this.getHowTos()) {
				for (HowTo howTo : howtos) {
					// LOGGER.debug("CHECK:::"+ht+"=="+howTo.getTitle() +
					// "("+howTo.getSynchronizer()+"=="+synchronizer.toString()+")");
					if (ht.equalsIgnoreCase(howTo.getTitle())) {
						if (howTo.getSynchronizer().equals(synchronizer.toString())) {
							renderer.addHowToItem(element, "HowTo", howTo.getTitle());
						}
					}
				}
			}
		}

		if (ObjectUtil.isListNotNullAndEmpty(this.getSubactivities())) {
			Element table = Parser.createActivityTable(this.getSubactivities(), synchronizer, true);
			table.appendTo(element);

			if (synchronizer.getClass().getSimpleName().equals(FileSynchronizer.class.getSimpleName())) {

				try {
					ClassLoader scl = ClassLoader.getSystemClassLoader();
					Class<?> clazz = scl.loadClass("org.rogatio.circlead.view.items.process.ProcessCanvas");

					Constructor<?> c = clazz.getConstructor(Activity.class);
					Object processCanvas = c.newInstance(this);
					Method initMethod = clazz.getMethod("init");
					initMethod.invoke(processCanvas);

					Method layoutMethod = clazz.getMethod("layout");
					layoutMethod.invoke(processCanvas);

					Method exportMethod = clazz.getMethod("export", String.class);
					exportMethod.invoke(processCanvas,
							PropertyUtil.getInstance().getWebserverDirectory() + File.separatorChar + ""
									+ this.getId(SynchronizerFactory.getInstance().getActual()) + ".svg");

//					element.appendElement("img").attr("style", "position: absolute;").attr("width", "100%").attr("src",
//							this.getId(SynchronizerFactory.getInstance().getActual()) + ".svg");
//element.append("<br/>");
					
					try {
						String svg = new String(Files.readAllBytes(
								Paths.get(PropertyUtil.getInstance().getWebserverDirectory() + File.separatorChar
										+ this.getId(SynchronizerFactory.getInstance().getActual()) + ".svg")));
						
						element.append("<a href=\""+this.getId(SynchronizerFactory.getInstance().getActual())+".svg\">"+svg+"</a>");
						
					} catch (IOException e) {
						e.printStackTrace();
					}

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				}
			}

		}

		return element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.rogatio.circlead.control.IValidator#validate()
	 */
	@Override
	public List<ValidationMessage> validate() {
		List<ValidationMessage> messages = new ArrayList<ValidationMessage>();

		if (!StringUtil.isNotNullAndNotEmpty(this.getResponsibleIdentifier())) {
			ValidationMessage m = new ValidationMessage(this);
			m.error("Role Responsible missing", "Activity '" + this.getTitle() + "' has no responsible role named");
			messages.add(m);
		}

		if (ObjectUtil.isListNotNullAndEmpty(this.getSubactivities())) {
			for (ActivityDataitem activity : this.getSubactivities()) {

				if (ObjectUtil.isListNotNullAndEmpty(activity.getSupplier())
						|| ObjectUtil.isListNotNullAndEmpty(activity.getConsultant())
						|| ObjectUtil.isListNotNullAndEmpty(activity.getInformed())) {
					if (!StringUtil.isNotNullAndNotEmpty(activity.getResponsible())) {
						ValidationMessage m = new ValidationMessage(this);
						m.error("Responsible missing",
								"Subactivity '" + activity.getTitle() + "' has no named responsible");
						messages.add(m);
					}
				}

				Activity a = R.getActivity(activity.getTitle());
				if (a != null) {
					String actR = a.getResponsibleIdentifier();
					String subR = activity.getResponsible();
					if (StringUtil.isNotNullAndNotEmpty(actR)) {
						if (!StringUtil.isNotNullAndNotEmpty(subR)) {
							ValidationMessage m = new ValidationMessage(this);
							m.error("Responsible missing", "Subactivity '" + activity.getTitle()
									+ "' has not named responsible '" + actR + "'");
							messages.add(m);
						}
					}
					if (StringUtil.isNotNullAndNotEmpty(subR)) {
						if (!StringUtil.isNotNullAndNotEmpty(actR)) {
							ValidationMessage m = new ValidationMessage(this);
							m.error("Responsible missing",
									"Activity '" + a.getTitle() + "' has not named responsible '" + subR + "'");
							messages.add(m);
						}
					}

					if (StringUtil.isNotNullAndNotEmpty(subR)) {
						if (StringUtil.isNotNullAndNotEmpty(actR)) {
							if (!subR.equals(actR)) {
								ValidationMessage m = new ValidationMessage(this);
								m.error("Responsible unequal", "Activity '" + a.getTitle()
										+ "' has unequal responsible '" + subR + "'!='" + actR + "'");
								messages.add(m);
							}
						}
					}
				}
			}
		}

		return messages;
	}

}
