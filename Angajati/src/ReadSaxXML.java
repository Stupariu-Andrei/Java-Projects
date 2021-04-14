import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReadSaxXML extends DefaultHandler{
	private boolean bName;
	private boolean bAge;
	private boolean bSex;
	private boolean bRole;
	
	private Angajat angajat;
	private List<Angajat> angajati;
	
	public List<Angajat> getAngajati(){
		return angajati;
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		if(qName.equalsIgnoreCase("Employee")) {
			angajat = new Angajat();
			if (attributes.getValue("id") != null) {
				angajat.setId(Integer.parseInt(attributes.getValue("id")));
			}
		} else if (qName.equalsIgnoreCase("Name")) {
			bName = true;
		} else if (qName.equalsIgnoreCase("Age")) {
			bAge = true;
		} else if (qName.equalsIgnoreCase("Sex")) {
			bSex = true;
		} else if (qName.equalsIgnoreCase("Role")) {
			bRole = true;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		if(bName) {
			angajat.setName(new String(ch, start, length));
		} else if(bAge) {
			angajat.setAge(Integer.parseInt(new String(ch, start, length)));
		} else if(bSex) {
			angajat.setSex(new String(ch, start, length));
		} else if(bRole) {
			angajat.setRole(new String(ch, start, length));
		} 
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if(qName.equalsIgnoreCase("Employee")) {
			if(angajati == null) {
				angajati = new ArrayList<Angajat>();
			}
			angajati.add(angajat);
			angajat = null;
		} else if (qName.equalsIgnoreCase("Name")) {
			bName = false;
		} else if (qName.equalsIgnoreCase("Age")) {
			bAge = false;
		} else if (qName.equalsIgnoreCase("Sex")) {
			bSex = false;
		} else if (qName.equalsIgnoreCase("Role")) {
			bRole = false;
		}
	}
}
