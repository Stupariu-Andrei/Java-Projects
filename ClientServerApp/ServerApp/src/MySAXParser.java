
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class MySAXParser {
	
	private SAXParserFactory factory;
	private SAXParser parser;
	private ReadProfesori saxReader ;
	
	public MySAXParser(){
		
		factory = SAXParserFactory.newInstance();
		saxReader = new ReadProfesori();
		try 
		{
			parser = factory.newSAXParser();
		} catch (ParserConfigurationException e) 
		{
			
			e.printStackTrace();
			
		} catch (SAXException e) 
		{
			
			e.printStackTrace();
			
		}
		
	}
	public ListaProfesori parseXML(String file) 
	{

		try 
		{
			parser.parse(file, saxReader);
			return saxReader.getProfesori();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		
	}
}
