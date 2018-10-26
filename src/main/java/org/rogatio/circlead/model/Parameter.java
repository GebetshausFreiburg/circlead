package org.rogatio.circlead.model;

/**
 * The Enum Parameter.
 */
public enum Parameter {

	/** The abbreviation. */
	ABBREVIATION("Abkürzung"),
	
	/** The title. */
	TITLE("Titel"),
	
	/** The bussiness. */
	BUSSINESS("Geschäftlich"),
	
	/** The private. */
	PRIVATE("Privat"),
	
	/** The secondname. */
	SECONDNAME("Zweitname"),
	
	/** The image. */
	IMAGE("Bild"),
	
	/** The abbreviation2. */
	ABBREVIATION2("Kürzel"),
	
	/** The rolepersons. */
	ROLEPERSONS("Rollenträger"),
	
	/** The rolepersonsinorganisation. */
	ROLEPERSONSINORGANISATION("Rollenträger in Organisation"),
	
	/** The rolepersonsinteam. */
	ROLEPERSONSINTEAM("Rollenträger im Team"),
	
	/** The tasks. */
	TASKS("Aufgaben"),
	
	/** The id. */
	ID("Id"),
	
	/** The unrelated. */
	UNRELATED("Unbesetzt"),
	
	/** The purpose. */
	PURPOSE("Zweck / Kurzbeschreibung"),
	
	/** The purposeshort. */
	PURPOSESHORT("Zweck"),
	
	/** The childs. */
	CHILDS("Erben"),
	
	/** The version. */
	VERSION("Version"),
	
	/** The modified. */
	MODIFIED("Geändert"),
	
	/** The created. */
	CREATED("Erstellt"),
	
	/** The contactperson. */
	CONTACTPERSON("Ansprechpartner"),
	
	/** The activityid. */
	ACTIVITYID("AID"),
	
	/** The type. */
	TYPE("Typ"),
	
	/** The subtype. */
	SUBTYPE("Subtyp"),
	
	/** The activity. */
	ACTIVITY("Aktivität"),
	
	/** The activities. */
	ACTIVITIES("Aktivitäten"),
	
	/** The roles. */
	ROLES("Rollen"),
	
	/** The rolesinteam. */
	ROLESINTEAM("Rollen im Team"),
	
	/** The rolesinorganisation. */
	ROLESINORGANISATION("Rollen in der Organisation"),
	
	/** The responsible. */
	RESPONSIBLE("Durchführender"),
	
	/** The responsible2. */
	RESPONSIBLE2("Verantwortlicher"),
	
	/** The parentactivity. */
	PARENTACTIVITY("Übergeordnete Aktivität"),
	
	/** The result. */
	RESULT("Erwartetes Ergebnis"),
	
	/** The description. */
	DESCRIPTION("Beschreibung"),
	
	/** The recurrencerule. */
	RECURRENCERULE("Wiederholungsmuster"),
	
	/** The category. */
	CATEGORY("Kategorie"),
	
	/** The startdate. */
	STARTDATE("Start"),
	
	/** The enddate. */
	ENDDATE("Ende"),
	
	/** The bpmn. */
	BPMN("BPMN"),
	
	/** The firstname. */
	FIRSTNAME("Vorname"),
	
	/** The familyname. */
	FAMILYNAME("Nachname"),
	
	/** The successor. */
	SUCCESSOR("Nachfolger"),
	
	/** The rolegroup. */
	ROLEGROUP("Rollengruppe"),
	
	/** The role. */
	ROLE("Rolle"),
	
	/** The predecessor. */
	PREDECESSOR("Vorgänger"),
	
	/** The accountable. */
	ACCOUNTABLE("Rechenschaftsgebender"),
	
	/** The supporter. */
	SUPPORTER("Unterstützer"),
	
	/** The consultant. */
	CONSULTANT("Berater"),
	
	/** The informed. */
	INFORMED("Informierte"),
	
	/** The teamfraction. */
	TEAMFRACTION("Teamanteil"),
	
	/** The fte. */
	FTE("Vollzeitäquivalent"),
	
	/** The subactivities. */
	SUBACTIVITIES("Teilaktivitäten"),
	
	/** The persons. */
	PERSONS("Personen"),
	
	/** The teamroles. */
	TEAMROLES("Teamrollen"),
	
	/** The howtos. */
	HOWTOS("HowTos"),
	
	/** The usedroles. */
	USEDROLES("Beteiligte Rollen"),
	
	/** The responsiblerole. */
	RESPONSIBLEROLE("Verantwortliche Rolle"),
	
	/** The persondata. */
	PERSONDATA("Stammdaten"),
	
	/** The summary. */
	SUMMARY("Zusammenfassung"),
	
	/** The contacts. */
	CONTACTS("Kontaktdaten"),
	
	/** The contacts2. */
	CONTACTS2("Kontakte"),
	
	/** The data. */
	DATA("Daten"),
	
	/** The name. */
	NAME("Name"),
	
	/** The responsibilities. */
	RESPONSIBILITIES("Verantwortungen"),
	
	/** The responsibility. */
	RESPONSIBILITY("Verantwortung"),
	
	/** The competencies. */
	COMPETENCIES("Kompetenzen"),
	
	/** The opportunities. */
	OPPORTUNITIES("Befugnisse"),
	
	/** The rules. */
	RULES("Regeln"),
	
	/** The adress. */
	ADRESS("Adresse"),
	
	/** The mail. */
	MAIL("Mail"),
	
	/** The mobile. */
	MOBILE("Mobil"),
	
	/** The unknown. */
	UNKNOWN("Unknown"),
	
	/** The phone. */
	PHONE("Festnetz"),
	
	/** The carryrolegroup. */
	CARRYROLEGROUP("Merkmalstragende Rollengruppe"),
	
	/** The brandrole. */
	BRANDROLE("Merkmalsgebende Rolle"),
	
	/** The parent. */
	PARENT("Vererber"),
	
	/** The synonyms. */
	SYNONYMS("Synonyme"),
	
	/** The status. */
	STATUS("Status"),
	
	/** The needed. */
	NEEDED("Mindestanzahl"),
	
	/** The level. */
	LEVEL("Mindestlevel"),
	
	/** The person. */
	PERSON("Person"),
	
	/** The organisation. */
	ORGANISATION("Organisation");
	
	/** The param. */
	private final String param;
	
	/**
	 * Instantiates a new parameter.
	 *
	 * @param param the param
	 */
	Parameter(final String param) {
		this.param = param;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return param;
	}
	
}
