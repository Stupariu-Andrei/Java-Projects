

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class ConnectionDB {
	
	private Connection con;
	private Profesor profesor=null;
	
	public ConnectionDB(){
		try{
			
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdatabase","root","");
			
			
		}catch(Exception ex){
			System.out.println("Exception" + ex );
		}
		
		
	}
	
	public void getData(List<Profesor> profesori){

		try{
			
			String s = "SELECT * FROM employee";
			Statement statement = con.createStatement();
	        
			ResultSet rs = statement.executeQuery(s);
			while(rs.next()){
				profesor = new Profesor();
				profesor.setId(rs.getInt("Id"));
				profesor.setNume(rs.getString("Name"));
				profesor.setPrenume(rs.getString("Prename"));
				profesor.setSex(rs.getString("Gender"));	
				profesor.setSalariu(rs.getString("Salary"));
				
				profesori.add(profesor);
				
			}
			rs.close();
		}catch(Exception ex){
			System.out.println("Error " + ex);
		}
	}
	
	public void delete(int Id){
		try{
			
			Statement stmt = con.createStatement();
			String SQL = "DELETE FROM employee WHERE Id = "+Id;
			stmt.executeUpdate(SQL);
			
		}catch(SQLException e){
			System.out.println("Error " +e);
		}
	}
	
	public void update(int id,Profesor profesor){
		try{
			String s = "INSERT INTO employee(Id,Name,Prename,Gender,Salary) VALUES(?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(s);
			preparedStmt.setInt(1, profesor.getId());
			preparedStmt.setString(2, profesor.getNume());
			preparedStmt.setString(3, profesor.getPrenume());
			preparedStmt.setString(4, profesor.getSalariu());
			preparedStmt.setString(5, profesor.getSex());
			preparedStmt.executeUpdate();
			
			
		}catch(SQLException e){
			System.out.println("Error " +e);
		}
	}
	
	public List<Profesor> parseXML(String file) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			ReadProfesori saxReader = new ReadProfesori();
			parser.parse(file, saxReader);
			return saxReader.getProfesori();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void parseData(){
		List<Profesor> profesori = parseXML("Profesori.xml");

		try {
			String querry = " insert into employee (Id, Name ,Prename , Salary, Gender) values (?,?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(querry);
			for (int i = 0; i < profesori.size(); i++) {
				
				preparedStmt.setInt(1, profesori.get(i).getId());
				preparedStmt.setString(2, profesori.get(i).getNume());
				preparedStmt.setString(3, profesori.get(i).getPrenume());
				preparedStmt.setInt(4, Integer.parseInt(profesori.get(i).getSalariu()));
				preparedStmt.setString(5, profesori.get(i).getSex());
				preparedStmt.executeUpdate();
				
			}
		} catch (Exception ex) {
			System.out.println("Error " + ex);
		}
	}
	
	public void up(int id,Profesor profesor) throws SQLException{
		 String query="UPDATE employee set id,Name,Prename,Gender,Sex where Id="+id;
		 PreparedStatement preparedStmt = con.prepareStatement(query);

		preparedStmt.setInt(1, profesor.getId());
		preparedStmt.setString(2, profesor.getNume());
		preparedStmt.setString(3, profesor.getPrenume());
		preparedStmt.setString(4, profesor.getSalariu());
		preparedStmt.setString(5, profesor.getSex());
		preparedStmt.executeUpdate();
	        
	}
	
}
