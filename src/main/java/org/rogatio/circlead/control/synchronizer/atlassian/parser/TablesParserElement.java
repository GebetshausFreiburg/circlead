package org.rogatio.circlead.control.synchronizer.atlassian.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.rogatio.circlead.model.data.ContactDataitem;

public class TablesParserElement implements IParserElement {

	public TablesParserElement(Object text) {
		parse(text);
	}
	
	public List<ContactDataitem> getContacts() {
		List<ContactDataitem> list = new ArrayList<ContactDataitem>();
		
		for (Map<String, String> map : dataList) {
			ContactDataitem c = new ContactDataitem();
			
			for (String key : map.keySet()) {
				String value = map.get(key);
				if (key.equalsIgnoreCase("Typ") || key.equalsIgnoreCase("Type")) {
					c.setType(value);
				} else if (key.equalsIgnoreCase("Adresse") || key.equalsIgnoreCase("Adress")) {
					c.setAddress(value);
				} else if (key.equalsIgnoreCase("Mail")) {
					c.setMail(value);
				}  else if (key.equalsIgnoreCase("Subtyp")||key.equalsIgnoreCase("Subtype")) {
					c.setSubtype(value);
				} else if (key.equalsIgnoreCase("Mobil") || key.equalsIgnoreCase("Mobile")) {
					c.setMobile(value);
				} else if (key.equalsIgnoreCase("Organisation")) {
					c.setOrganisation(value);
				} else if (key.equalsIgnoreCase("Festnetz") || key.equalsIgnoreCase("Phone")) {
					c.setPhone(value);
				}
			}
			
			list.add(c);
			
		}
		
		
		return list;
	}
	
	private List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
	
	public List<Map<String, String>> getData() {
		return dataList;
	}
	
//	private Map<String, String> map = new HashMap<String, String>();
	
	private void parse(Object text) {

		if (text instanceof Element) {
			Element element = (Element) text;
			Elements tables = element.getElementsByTag("tbody");
			if (tables.size() > 0) {

				for (Element table : tables) {
					
					Map<String, String> map = new HashMap<String, String>();
					
//					ContactDataitem c = new ContactDataitem();
					for (Element pair : table.getElementsByTag("tr")) {
						String key = pair.getElementsByTag("th").get(0).text();
						String value = pair.getElementsByTag("td").get(0).text();

						map.put(key, value);
						
						//System.out.println("KEY=" + key + ", VALUE=" + value);
					}
					dataList.add(map);
				}
				// System.out.println(element);

			}
		}
	}

	public String toString() {
		return dataList.toString();
	}

}
