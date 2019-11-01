package kontroler;

import java.net.SocketTimeoutException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import model.Kurs;
import model.User;

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
		// ojekeat Connection i i tip podatka konekcija
		Connection konekcija = null;
		PreparedStatement pst = null;
		
		try {
			konekcija = UspostaviKonekciju("Kursevi");
			System.out.println("Konekcija uspostavljena");
			
			String query = "Update courses set cena = ? where ime_kursa = ?";
			pst = konekcija.prepareStatement(query);
			//ovo 1 i 2 se odnosi na znak ? koliko ima znak pitanja u upitu, toliko ima brojeva
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
		
		
		public void prikaziSveKurseve() {
			
			
			Connection konekcija = null;
			PreparedStatement pst = null;
			ResultSet res = null;
			
			try {
				konekcija = UspostaviKonekciju("kursevi");
				System.out.println("Konekcija uspostavljena");
				String query = "Select * from courses";
				pst = konekcija.prepareStatement(query);
				
				res = pst.executeQuery();
				System.out.println("id	ime	cena");
				System.out.println("__________________");
				
				//ovde čita prvu kolonu, umesto stringova id, ime, cena
				while(res.next()) {
					
					
					int id = res.getInt("id_courses");
					String ime = res.getString("ime_kursa");
					double cena = res.getDouble("cena");
					System.out.println(id + " " + ime + " " + cena);
					
					
					
					
					
				}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		
		
		
		//importujemo iz drugog paketa - klase Kurs;
	public Kurs vratiKursPoID(int id) {
			
			
			Connection konekcija = null;
			PreparedStatement pst = null;
			ResultSet res = null;
			
			//ovo je instaca, objekat je posle kada ga koristimo. malo kurs je referenca
			Kurs kurs = new Kurs();
			
			try {
				konekcija = UspostaviKonekciju("kursevi");
				System.out.println("Konekcija uspostavljena");
				String query = "Select * from courses where id_courses = ? ";
				pst = konekcija.prepareStatement(query);
				pst.setInt(1, id);
				
				res = pst.executeQuery();
				
				//System.out.println("id	ime	cena");
				//System.out.println("__________________");
				
				//ovde čita prvu kolonu, umesto stringova id, ime, cena
				while(res.next()) {
					
					
					int idKursa = res.getInt("id_courses");
					kurs.setIdKursa(idKursa);
					//ili kurs.setIdKursa(res.getInt("id_courses"));
					
					kurs.setImeKursa(res.getString("ime_kursa"));
					kurs.setCena(res.getInt("cena"));
					
					
					
					
					
				}
				return kurs;
				
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
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
	
	
	
	
	public User vratiUseraPoID(int id) {
		
		Connection konekcija = null;
		PreparedStatement pst = null;
		ResultSet res = null;
		
		User userKorisnik = new User();
		
		
		try {
			konekcija = UspostaviKonekciju("kursevi");
			System.out.println("Konekcija uspostavljena...");
			
			String query = "Select * from users where id_users = ?";
			pst = konekcija.prepareStatement(query);
			pst.setInt(1, id);
			//poput neke liste
			res = pst.executeQuery();
			
			
			while(res.next()) {
				//ovde radimo rucno mapiranje 
				userKorisnik.setIdUser(res.getInt("id_users"));
				userKorisnik.setUserName(res.getString("username"));
				userKorisnik.setPassword(res.getString("password"));
				userKorisnik.setMadBroj(res.getInt("mat_br"));
			
			}
			return userKorisnik; 
			
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
			
			
		} finally {
			
			
			
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
	
	public int vratiIdPoUserneme(String userName) {

		Connection konekcija = null;
		PreparedStatement pst = null;
		ResultSet res = null;

		int id = 0;

		try {
			konekcija = UspostaviKonekciju("Kursevi");
			String query = "SELECT id_users FROM users WHERE username = ?";
			pst = konekcija.prepareStatement(query);
				pst.setString(1, userName );
			res = pst.executeQuery();

			while(res.next()) {

				id = res.getInt("id_users");
			}

			return id;

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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





	public List<String> vratiBrojTelefona(int idUser) {

		Connection konekcija = null;
		PreparedStatement pst = null;
		ResultSet res = null;

		List<String> listaBrojevaTelefona = new ArrayList<String>();

		try {
			konekcija = UspostaviKonekciju("Kursevi");
			String query = "SELECT broj_telefona FROM brojevi_telefona WHERE user = ?";
			pst = konekcija.prepareStatement(query);
				pst.setInt(1, idUser);
			res = pst.executeQuery();

			while(res.next()) {

				listaBrojevaTelefona.add(res.getString("broj_telefona"));

			}
			return listaBrojevaTelefona;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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


	public List<Integer> vratiIdKursaPoIdUsera(int idUser) {

		Connection konekcija = null;
		PreparedStatement pst = null;
		ResultSet res = null;

		List<Integer> listaIdKurseva = new ArrayList<Integer>();

		try {
			konekcija = UspostaviKonekciju("Kursevi");
			String query = "SELECT id_c FROM users_courses WHERE id_u = ?";
			pst = konekcija.prepareStatement(query);
				pst.setInt(1, idUser);
			res = pst.executeQuery();

			while(res.next()) {

				listaIdKurseva.add(res.getInt("id_c"));

			}
			return listaIdKurseva;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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


