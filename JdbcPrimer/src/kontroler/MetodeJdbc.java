package kontroler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MetodeJdbc {
	
	//modifikatori pristupa su public, private i naziv Metode UpostaviKonekciju
	public static Connection UspostaviKonekciju(String imeBaze) throws SQLException {
		//ovo imeBaze se zove konfigurabilno
		//throws SQLException - ovim metodama prebacujemo problem na druge,
		//tj, sledeci mora da uradi konekciju
		//a to samo dobili kad sam u return kliknuli na gresku i izbegli try i catch
		
		
		//konstata final - vidljiva je samo u okvriru svoje metode
		final String url = "jdbc:mysql://localhost:3306/"+ imeBaze;
		//ime baze ne seme da bude u Stringu " " 
		final String password = "root";
		final String user = "root";
		
		//može i ovako 
		//Connection UspostaviKonekciju() = DriverManager.getConnection(url, password, user);
		//return UspostaviKonekciju();
		return DriverManager.getConnection(url, password, user);		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
