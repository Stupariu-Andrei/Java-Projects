


public class Profesor {
	
		private int m_id;
		private String m_nume;
		private String m_prenume;
		private String m_telefon;
		private String m_sex;
		private String m_salariu;
		private String m_facultate;
		
		
		public String getSalariu() 
		{
			return m_salariu;
		}
		public void setSalariu(String salariu) 
		{
			this.m_salariu = salariu;
		}
		public int getId() 
		{
			return m_id;
		}
		public void setId(int id) 
		{
			this.m_id = id;
		}
		public String getNume() 
		{
			return m_nume;
		}
		public void setNume(String nume) 
		{
			this.m_nume = nume;
		}
		public String getPrenume() 
		{
			return m_prenume;
		}
		public void setPrenume(String prenume) 
		{
			this.m_prenume = prenume;
		}
		public String getTelefon() 
		{
			return m_telefon;
		}
		public void setTelefon(String telefon) 
		{
			this.m_telefon = telefon;
		}
		public String getSex()
		{
			return m_sex;
		}
		public void setSex(String sex)
		{
			this.m_sex = sex;
		}
		
		public void setFacultate(String fac)
		{
			this.m_facultate=fac;
		}
		
		public String getFacultate()
		{
			return this.m_facultate;
		}
		
		
}

