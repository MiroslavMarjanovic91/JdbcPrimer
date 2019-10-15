package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import kontroler.MetodeJdbc;

public class JdbcProjekat {

	public static void main(String[] args) {
		
		
		//prvo importujemo paket 
		//istanaca metode 
		//MetodeJdbc metodeJdbc = new MetodeJdbc();
		//ili ako ovu metodu zbog static pozivamo direktno  
		
		
		//napravili samo promenjljivu i mesto u memoriji za konekciju, njen slozeni tip je Connection
		Connection konekcija = null;
		
		//pravimo interfejs Statement
		//inportujemo iz java.sql
		Statement statement = null;
		
		
		System.out.println("Uspešan unos ;) ");
		
		
	try {
		//konekcija = metodeJdbc.UspostaviKonekciju();
		konekcija = MetodeJdbc.UspostaviKonekciju("kursevi");
		//ovde moramo da navedemo na koju bazu da ide
		System.out.println("Uspostavio konekciju ;) ");
		String query = "Insert into courses values(null,'C#',10000)";
		statement = konekcija.createStatement();
		statement.execute(query);
		
		
	} catch (SQLException e) {
		System.err.println("Nema konekcije!");
		e.printStackTrace();
	} 
	
		
		
		
		
	
		
		
		
		
		
		
		
		
		
		

	}

}
