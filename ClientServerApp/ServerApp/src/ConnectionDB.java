


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;






public class ConnectionDB 
{
	
	private Connection con;
	private Profesor profesor=null;
	
	public ConnectionDB(){
		try{
			
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/salariati","root","root");
			
			
		}catch(Exception ex){
			System.out.println("Exception" + ex );
		}
		
		
	}
	
	public void getData(ListaProfesori profesori)
	{

		try{
			
			String s = "SELECT * FROM salariatifacultate";
			Statement statement = con.createStatement();
	        
			ResultSet rs = statement.executeQuery(s);
			while(rs.next())
			{
				profesor = new Profesor();
				profesor.setId(rs.getInt("Id"));
				profesor.setNume(rs.getString("Nume"));
				profesor.setPrenume(rs.getString("Prenume"));
				profesor.setSex(rs.getString("Sex"));	
				profesor.setSalariu(rs.getString("Salariu"));
				
				profesori.Add(profesor);
				
			}
			rs.close();
		}catch(Exception ex)
		{
			System.out.println("Error " + ex);
		}
	}
	
	public synchronized void delete(Profesor prof)
	{
		try
		{
			
			Statement stmt = con.createStatement();
			String SQL = "DELETE FROM salariatifacultate WHERE Id = "+prof.getId();
			stmt.executeUpdate(SQL);
			
		}catch(SQLException e)
		{
			System.out.println("Error " +e);
		}
	}
	
	public synchronized void insert(Profesor profesor)
	{
		try
		{
			String s = "INSERT INTO salariatifacultate(Id,Nume,Prenume,Salariu,Sex) VALUES(?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(s);
			preparedStmt.setInt(1, profesor.getId());
			preparedStmt.setString(2, profesor.getNume());
			preparedStmt.setString(3, profesor.getPrenume());
			preparedStmt.setString(4, profesor.getSalariu());
			preparedStmt.setString(5, profesor.getSex());
			preparedStmt.executeUpdate();
			
			
		}catch(SQLException e)
		{
			System.out.println("Error " +e);
		}
	}
	
	
	
	public synchronized void parseData()
	{
		MySAXParser parser= new MySAXParser();
		ListaProfesori profesori = parser.parseXML("Profesori.xml");

		try
		{
			String querry = " insert into salariatifacultate (Id, Nume ,Prenume , Salariu, Sex) values (?,?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(querry);
			for (int i = 0; i < profesori.GetSize(); i++) 
			{
				
				preparedStmt.setInt(1, profesori.Get(i).getId());
				preparedStmt.setString(2, profesori.Get(i).getNume());
				preparedStmt.setString(3, profesori.Get(i).getPrenume());
				preparedStmt.setInt(4, Integer.parseInt(profesori.Get(i).getSalariu()));
				preparedStmt.setString(5, profesori.Get(i).getSex());
				preparedStmt.executeUpdate();
				
			}
		} catch (Exception ex) 
		{
			System.out.println("Error " + ex);
		}
	}
	
	public synchronized void update(Profesor profesor) throws SQLException
	{
		 String query="UPDATE salariatifacultate set Nume=?,Prenume=?,Salariu=?,Sex=? where Id="+profesor.getId();
		 PreparedStatement preparedStmt = con.prepareStatement(query);

		
		preparedStmt.setString(1, profesor.getNume());
		preparedStmt.setString(2, profesor.getPrenume());
		preparedStmt.setString(3, profesor.getSalariu());
		preparedStmt.setString(4, profesor.getSex());
		preparedStmt.executeUpdate();
	        
	}
	
}
