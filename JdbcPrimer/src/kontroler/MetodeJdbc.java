package kontroler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MetodeJdbc {
	
	//modifikatori pristupa su public, private i naziv Metode UpostaviKonekciju
	private static Connection UspostaviKonekciju(String imeBaze) throws SQLException {
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
	
	
	//ako neka metoda treba nesto da vrati, umesto void moze da bude boolean
	
	//modifikator pristupa, privat ili public
	
	public boolean ubaciUTabebluKursevi(String imeKursa, String cena) {
		
		//pravimo objekat 
		
		Connection konekcija = null;
		PreparedStatement statement = null;
		int cenaZaUpis = Integer.parseInt(cena);
		
		
		try {
			konekcija = UspostaviKonekciju("Kursevi");
			System.out.println("Konekcija je uspostavljena ;) ");
			
			String query = "Insert into courses values(null,?,?)";
			statement = konekcija.prepareStatement(query);
			statement.setString(1, imeKursa);
			statement.setInt(2, cenaZaUpis);
			statement.execute();
			
			
		    System.out.println("Uspesn ubacen kurs! ");
			return true; 
		} catch (SQLException e) {
			System.err.println("Konekcija nije uspostavljena ");
			e.printStackTrace();
			return false; 
		}
		
	//u fajnli bloku prvo zatvaramo stjtmen, pa konekciju, obrnuto 
		
		finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
			try {
			konekcija.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		}
		
		
	}
	
	
	public boolean izmeniCenuKursa(String imeKursa, int Cena) {
		
		Connection konekcija = null;
		PreparedStatement pst = null;
		
		try {
			konekcija = UspostaviKonekciju("Kursevi");
			System.out.println("Konekcija uspostavljena");
			
			String query = "Update courses set cena = ? where ime_kursa = ?";
			pst = konekcija.prepareStatement(query);
			pst.setInt(1, Cena);
			pst.setString(2, imeKursa);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
		
		
		finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				konekcija.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
	}
	

}
