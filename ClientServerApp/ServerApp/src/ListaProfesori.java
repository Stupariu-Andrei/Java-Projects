


import java.util.ArrayList;

public class ListaProfesori {
	
	private ArrayList<Profesor> m_profesori ;
	
	public ListaProfesori(){
		m_profesori = new ArrayList<Profesor>();
	}
	
	public void Add(Profesor profesor){
		m_profesori.add(profesor);
	}
	
	public int GetSize()
	{
		return m_profesori.size();
	}
	
	public Profesor Get(int index)
	{
		if (index < 0 || index >= m_profesori.size())
		{
			return null;
		}
		
		return m_profesori.get(index);
	}

	public void Remove(int row) {
		
		m_profesori.remove(row);
	}
}
