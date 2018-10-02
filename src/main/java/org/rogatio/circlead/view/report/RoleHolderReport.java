/*
 * Circlead - Develop and structure evolutionary Organisations
 * 
 * @author Matthias Wegner
 * @version 0.1
 * @since 01.07.2018
 * 
 */
package org.rogatio.circlead.view.report;

import java.util.ArrayList;

import java.util.List;

import org.jsoup.nodes.Element;
import org.rogatio.circlead.control.Repository;
import org.rogatio.circlead.control.synchronizer.ISynchronizer;
import org.rogatio.circlead.control.synchronizer.atlassian.AtlassianSynchronizer;
import org.rogatio.circlead.control.synchronizer.file.FileSynchronizer;
import org.rogatio.circlead.model.WorkitemStatusParameter;
import org.rogatio.circlead.model.work.Person;
import org.rogatio.circlead.model.work.Role;
import org.rogatio.circlead.util.ObjectUtil;
import org.rogatio.circlead.view.ISynchronizerRendererEngine;

public class RoleHolderReport extends DefaultReport {

	public RoleHolderReport() {
		this.setName("RoleHolder Report");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rogatio.circlead.view.DefaultReport#render(org.rogatio.circlead.control.
	 * synchronizer.ISynchronizer)
	 */
	@Override
	public Element render(ISynchronizer synchronizer) {
		ISynchronizerRendererEngine renderer = synchronizer.getRenderer();
		Element element = new Element("p");

		renderer.addH2(element, "Unbesetzte Rollen");
		renderer.addStatus(element, WorkitemStatusParameter.UNASSIGNED.toString());

		List<Role> foundUnrelatedRoles = new ArrayList<Role>();
		List<Role> roles = Repository.getInstance().getRoles();
		for (Role role : roles) {
			if (!ObjectUtil.isListNotNullAndEmpty(role.getPersonIdentifiers())) {
				foundUnrelatedRoles.add(role);
			}
		}
		renderer.addRoleList(element, foundUnrelatedRoles);
		
		addRoles("Unklare Rollen", WorkitemStatusParameter.CRITICAL, synchronizer, element);
		addRoles("Entstehende Rollen", WorkitemStatusParameter.DRAFT, synchronizer, element);
		addRoles("Zeitweise Rollen", WorkitemStatusParameter.TEMPORARY, synchronizer, element);
		addRoles("Überarbeitete Rollen", WorkitemStatusParameter.INPROGRESS, synchronizer, element);
		addRoles("Aktive Rollen - Untrainiert", WorkitemStatusParameter.ACTIVE, WorkitemStatusParameter.UNSKILLED, "0", synchronizer, element);
		addRoles("Aktive Rollen - Anfänger", WorkitemStatusParameter.ACTIVE, WorkitemStatusParameter.STARTER, "25", synchronizer, element);
		addRoles("Aktive Rollen - Beginner", WorkitemStatusParameter.ACTIVE, WorkitemStatusParameter.BEGINNER, "50", synchronizer, element);
		addRoles("Aktive Rollen - Experte", WorkitemStatusParameter.ACTIVE, WorkitemStatusParameter.EXPERT, "75", synchronizer, element);

		return element;
	}
	
	private void addRoles(String title, WorkitemStatusParameter wis, WorkitemStatusParameter wiStatus, String skillLevel,  ISynchronizer synchronizer, Element element) {
		ISynchronizerRendererEngine renderer = synchronizer.getRenderer();
		renderer.addH2(element, title);
		renderer.addStatus(element, wis.toString());
		renderer.addStatus(element, wiStatus.toString());
		Element ul = element.appendElement("ul");
		List<Role> roles = Repository.getInstance().getRoles();
		for (Role role : roles) {
			if (ObjectUtil.isListNotNullAndEmpty(role.getPersonIdentifiers())) {
				for (String personIdentifier : role.getPersonIdentifiers()) {
					Person person = Repository.getInstance().getPerson(personIdentifier);
					if (person != null) {
						if (role.getDataitem().hasRepresentation(personIdentifier)) {
							String representation = role.getDataitem().getRepresentation(personIdentifier);
							WorkitemStatusParameter status = WorkitemStatusParameter.get(representation);
							if (status != null) {
								if (status == wis) {
									if (role.getDataitem().hasSkill(personIdentifier)) {
										String skill = role.getDataitem().getSkill(personIdentifier);
										if (skill != null) {
											if (skill.equals(skillLevel)) {
												addLink(synchronizer, ul, role, person);
											}
										}
									}

								}
							}
						}
					}
				}
			}
		}
	}
	
	private void addRoles(String title, WorkitemStatusParameter wiStatus, ISynchronizer synchronizer, Element element) {
		ISynchronizerRendererEngine renderer = synchronizer.getRenderer();
		renderer.addH2(element, title);
		renderer.addStatus(element, wiStatus.toString());
		Element ul = element.appendElement("ul");
		List<Role> roles = Repository.getInstance().getRoles();
		for (Role role : roles) {
			if (ObjectUtil.isListNotNullAndEmpty(role.getPersonIdentifiers())) {
				for (String personIdentifier : role.getPersonIdentifiers()) {
					Person person = Repository.getInstance().getPerson(personIdentifier);
					if (person != null) {
						if (role.getDataitem().hasRepresentation(personIdentifier)) {
							String representation = role.getDataitem().getRepresentation(personIdentifier);
							WorkitemStatusParameter status = WorkitemStatusParameter.get(representation);
							if (status != null) {
								if (status == wiStatus) {
									addLink(synchronizer, ul, role, person);
								}
							}
						}
					}
				}
			}
		}
	}

	private void addLink(ISynchronizer synchronizer, Element ul, Role role, Person person) {
		if (synchronizer.getClass().getSimpleName()
				.equals(FileSynchronizer.class.getSimpleName())) {
			Element li = ul.appendElement("li");
			li.appendElement("a").attr("href", "../web/" + role.getId(synchronizer) + ".html")
			.appendText(role.getTitle());
			li.append("&nbsp;-&nbsp;");
			li.appendElement("a").attr("href", "../web/" + person.getId(synchronizer) + ".html")
			.appendText(person.getTitle());
		}

		if (synchronizer.getClass().getSimpleName()
				.equals(AtlassianSynchronizer.class.getSimpleName())) {
			Element li = ul.appendElement("li");
			li.appendElement("ac:link")
					.append("<ri:page ri:content-title=\"" + role.getTitle()
							+ "\" ri:version-at-save=\"1\" />");
			li.append("&nbsp;-&nbsp;");
			li.appendElement("ac:link")
			.append("<ri:page ri:content-title=\"" + person.getTitle()
					+ "\" ri:version-at-save=\"1\" />");
		}
	}
	
}