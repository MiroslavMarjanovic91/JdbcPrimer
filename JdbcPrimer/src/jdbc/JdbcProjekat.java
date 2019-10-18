package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kontroler.MetodeJdbc;

public class JdbcProjekat {

	public static void main(String[] args) {
		
	
		MetodeJdbc metode = new MetodeJdbc();
		Scanner scenner = new Scanner(System.in);
		
		System.out.println("Unesite ime kursa");
		String imeKursa = scenner.nextLine();
		
		System.out.println("Unesite cenu: ");
		
		int Cena = Integer.parseInt(scenner.nextLine());
		
		
		
		metode.izmeniCenuKursa(imeKursa, Cena);
		
		
		
		
	
	
		
		
		
		
		
		
		
		

	}

}
