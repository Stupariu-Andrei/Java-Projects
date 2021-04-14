package parsare;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.spi.XmlWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import procesare.Profesor;

public class WriteXML {
	public void createXMLFrom(List<Profesor> prof, String outputFile) throws ParserConfigurationException, TransformerException
	{
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		
		Element root = doc.createElement("Profesori");
		doc.appendChild(root);
		for(int i = 0 ; i<prof.size();i++){
			
			Element elem = doc.createElement("Profesor");
			elem.setAttribute("id", Integer.toString(prof.get(i).getId()));
			elem.setAttribute("nume", prof.get(i).getNume());
			elem.setAttribute("prenume", prof.get(i).getPrenume());
			elem.setAttribute("salariu", prof.get(i).getSalariu());
			elem.setAttribute("facultate", prof.get(i).getFacultate());
			
		}
		
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer t = tFactory.newTransformer();
		Result result = new StreamResult(new File(outputFile));
		Source source = new DOMSource(doc);
		t.transform(source, result);
	}
}
