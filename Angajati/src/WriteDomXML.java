import java.io.File;
import java.util.List;


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

public class WriteDomXML {
	public void createXMLFrom(List<Angajat> angajati, String outputFile) throws ParserConfigurationException, TransformerException
	{
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		
		Element root = doc.createElement("Employees");
		doc.appendChild(root);
		for (Angajat angajat : angajati) {
			Element elem = doc.createElement("Employee");
			elem.setAttribute("id", Integer.toString(angajat.getId()));
			elem.setAttribute("name", angajat.getName());
			elem.setAttribute("Age", Integer.toString(angajat.getAge()));
			elem.setAttribute("Sex", angajat.getSex());
			elem.setAttribute("Role", angajat.getRole());
			root.appendChild(elem);
		}
		
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer t = tFactory.newTransformer();
		Result result = new StreamResult(new File(outputFile));
		Source source = new DOMSource(doc);
		t.transform(source, result);
	}
}
