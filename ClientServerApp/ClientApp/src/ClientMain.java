import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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

public class ClientMain extends JFrame{
	private JTextArea areaMesaje;
	private Socket socket;
	private BufferedWriter writer;
	private BufferedReader reader;
	
	public ClientMain() {
		super("Client");
		
		connect();
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JLabel lblMesaj = new JLabel();
		lblMesaj.setText("Mesaj:");
		lblMesaj.setBounds(10,  10,  100, 30);
		panel.add(lblMesaj);
		
		JTextField txtMesaj = new JTextField();
		txtMesaj.setBounds(120, 10, 200, 30);
		panel.add(txtMesaj);
		
		
		JButton btnSend = new JButton("Send");
		btnSend.setBounds(340, 10, 100, 30);
		panel.add(btnSend);
		btnSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					sendMessage(txtMesaj.getText());
				} catch (ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (TransformerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				readMessage();
			}
		}); 
		
		areaMesaje = new JTextArea();
		areaMesaje.setBounds(10, 50, 440, 200);
		panel.add(areaMesaje);
		
		
		add(panel);
		pack();
		setSize(500, 300); 
		setVisible(true);
	}
	
	public void connect() {
		InetAddress address;
		try {
			address = InetAddress.getLocalHost();
			
			socket = new Socket(address, 9090);
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			InputStreamReader dis = new InputStreamReader(socket.getInputStream());
			reader = new BufferedReader(dis);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void sendMessage(String mesaj) throws ParserConfigurationException, TransformerException {
		String msg = "<comand><action>"+mesaj+"</action>";
		if(mesaj.equalsIgnoreCase("update")) {
			Profesor prof = new Profesor();
			prof.setId(1);
			prof.setNume("dasd");
			prof.setPrenume("dsad");
			prof.setSalariu("2131");
			prof.setFacultate("dsad");
			createXMLForm(prof);
			msg = msg +"<content><profesor>"+ "id"+prof.getId()+">"+"<nume>"+prof.getNume()+"</nume>"+"<prenume>"+prof.getPrenume()+"</prenume>"+"<salariu>"+prof.getSalariu()+"</salariu>"+"</profesor></content>";
		}
		msg += "</comand>";
		if(writer != null) {
			try {
				writer.write(msg);
				writer.newLine();
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			areaMesaje.append("Client: "+msg+"\n");
		}
	}
	
	public void readMessage() {
		if(reader != null) {
			try {
				String mesaj = reader.readLine();
				areaMesaje.append("Server: "+mesaj+"\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new ClientMain();
		
	}
	
	public static void createXMLForm(Profesor prof) throws ParserConfigurationException, TransformerException
	{
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		
		Element root = doc.createElement("Profesori");
		Element elem = doc.createElement("Profesor");
		doc.appendChild(root);

		elem.setAttribute("id", Integer.toString(prof.getId()));
		elem.setAttribute("nume", prof.getNume());
		elem.setAttribute("prenume", prof.getPrenume());
		elem.setAttribute("salariu", prof.getSalariu());
		elem.setAttribute("facultate", prof.getFacultate());
		root.appendChild(elem);


	}

	/* clientul:
	 * - impacheteaza cererile catre server in XML 
	 * - parseaza raspunsul serverului 
	 * - actualizeaza interfata
	 * 
	 * Cererile:
	 * - update
	 * <comand><action>update</action><content><elev id=1><nume>ion</nume><prenume>ion</prenume><varsta>10</varsta></elev></content>
	 * - insert
	 * <comand><action>insert</action><content><elev id=1><nume>ion</nume><prenume>ion</prenume><varsta>10</varsta></elev></content>
	 * - delete
	 * <comand><action>delete</action><content><elev id=1/></content>
	 * - retrieve
	 * <comand><action>delete</action></comand>
	 * -clear
	 * <comand><action>clear</action></comand>
	 * 
	 */
}
