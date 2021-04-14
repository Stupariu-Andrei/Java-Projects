package procesare;

import java.util.List;


public class Profesor {
	
		private int id;
		private String nume;
		private String prenume;
		private String telefon;
		private String sex;
		private String salariu;
		private String facultate;
		private List<Profesor> profesori;
		
		
		public String getSalariu() {
			return salariu;
		}
		public void setSalariu(String salariu) {
			this.salariu = salariu;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNume() {
			return nume;
		}
		public void setNume(String nume) {
			this.nume = nume;
		}
		public String getPrenume() {
			return prenume;
		}
		public void setPrenume(String prenume) {
			this.prenume = prenume;
		}
		public String getTelefon() {
			return telefon;
		}
		public void setTelefon(String telefon) {
			this.telefon = telefon;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		
		public void setFacultate(String fac){
			this.facultate=fac;
		}
		
		public String getFacultate(){
			return this.facultate;
		}
		
		public List<Profesor> getProfesori(){
			return this.profesori;
		}
		
		
	}

