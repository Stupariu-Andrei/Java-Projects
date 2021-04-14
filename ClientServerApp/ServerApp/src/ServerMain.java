import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ServerMain {

	public ServerMain() {
		
	}

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(9090);
			System.out.println("Start server");
			while (true) {
				try {
					Socket socket = server.accept();
					new Thread(new Runnable() {

						@Override
						public void run() {
							try {
								PrintWriter pw = new PrintWriter(socket.getOutputStream());
								InputStreamReader dis = new InputStreamReader(socket.getInputStream());
								BufferedReader br = new BufferedReader(dis);
								while (true) {
									StringBuffer sf = new StringBuffer();
									String s;
									while(true) {
										s = br.readLine();
										sf.append(s);
										if(s.endsWith("</comand>")){
											break;
										}
									}
									
									List<Object> r = parseXML(sf);
									
									String message = (String)r.get(0);
									System.out.println("Clientul spune:" + sf);
									
									if (message.equalsIgnoreCase("data")) {
										String msg = "<response><action>"+message+"</action><content>"+Calendar.getInstance().getTime().toString()+"</content></response>";
										
										pw.println(msg);
									} else if (message.equalsIgnoreCase("insert")) {
										
										String msg = "Am inserat"+"<content><elev id=10><nume>ion</nume><prenume>ion</prenume><varsta>10</varsta></elev></content>";
										pw.println(msg);
										pw.flush();
										break;
									} else {
										String msg = "<response><action>"+message+"</action><content>ok</content></response>";
										pw.println(msg);
									}
									pw.flush();
								}
							} catch (Exception ex) {

							} finally {
								try {
									socket.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

						}
					}).start();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static public List<Object> parseXML(StringBuffer sb) throws Exception{
		List<Object> r = new ArrayList<Object>();
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(new InputSource(new StringReader(sb.toString())));
		Element root = doc.getDocumentElement();
		NodeList childrenList = root.getElementsByTagName("action");
		Node action = childrenList.item(0);
		String valAction = action.getTextContent();
		r.add(valAction);

		return r;
	}
	

	
	
	/*cerinta pe partea Server-ului:
	 * - se conecteaza la DB si in functie de actiunea trimisa de client: update, insert, retrieve, delete, clear 
	 * - rezolva cererea 
	 * - intoarce raspunsul clientului impachetat in format XML
	 */
}
