



import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ReadProfesori extends DefaultHandler {

	
		private boolean bNume;
		private boolean bPrenume;
		private boolean bTelefon;
		private boolean bSex;
		private boolean bSalariu;
		private boolean bFacultate;
		
		private Profesor profesor;
		private List<Profesor> profesori;
		
		public List<Profesor> getProfesori(){
			return profesori;
		}
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			// TODO Auto-generated method stub
			super.startElement(uri, localName, qName, attributes);
			if(qName.equalsIgnoreCase("Profesor")) {
				profesor = new Profesor();
				if (attributes.getValue("Id") != null) {
					profesor.setId(Integer.parseInt(attributes.getValue("Id")));
				}
			} else if (qName.equalsIgnoreCase("Nume")) {
				bNume = true;
			} else if (qName.equalsIgnoreCase("Prenume")) {
				bPrenume = true;
			} else if (qName.equalsIgnoreCase("Sex")) {
				bSex = true;
			} else if (qName.equalsIgnoreCase("Telefon")) {
				bTelefon = true;
			}
			else if (qName.equalsIgnoreCase("Salariu")) { 
				bSalariu = true;
			}
			else if (qName.equalsIgnoreCase("Facultate")) { 
				bFacultate = true;
			}
		}
		
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			// TODO Auto-generated method stub
			super.characters(ch, start, length);
			if(bNume) {
				profesor.setNume(new String(ch, start, length));
			} else if(bPrenume) {
				profesor.setPrenume(new String(ch, start, length));
			} else if(bSex) {
				profesor.setSex(new String(ch, start, length));
			} else if(bTelefon) {
				profesor.setTelefon(new String(ch, start, length));
			} 
			else if(bSalariu) {
				profesor.setSalariu(new String(ch, start, length));
			}
			else if(bFacultate) {
				profesor.setFacultate(new String(ch, start, length));
			}
			
		}
		
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			// TODO Auto-generated method stub
			super.endElement(uri, localName, qName);
			if(qName.equalsIgnoreCase("profesor")) {
				if(profesori == null) {
					profesori = new ArrayList<Profesor>();
				}
				profesori.add(profesor);
			} else if (qName.equalsIgnoreCase("Nume")) {
				bNume = false;
			} else if (qName.equalsIgnoreCase("Prenume")) {
				bPrenume = false;
			} else if (qName.equalsIgnoreCase("Sex")) {
				bSex = false;
			} else if (qName.equalsIgnoreCase("Telefon")) {
				bTelefon = false;
			}
			else if (qName.equalsIgnoreCase("Salariu")) {
				bSalariu = false;
			}
			else if (qName.equalsIgnoreCase("Facultate")) {
				bFacultate = false;
			}
		}
	
}
