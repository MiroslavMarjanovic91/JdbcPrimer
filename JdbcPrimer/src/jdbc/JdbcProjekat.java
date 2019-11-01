package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

import kontroler.MetodeJdbc;
import model.Kurs;
import model.User;

public class JdbcProjekat {

	public static void main(String[] args) {
		
	
		MetodeJdbc metode = new MetodeJdbc();
		/*Scanner scenner = new Scanner(System.in);
		
		System.out.println("Unesite ime kursa");
		String imeKursa = scenner.nextLine();
		System.out.println("Unesite cenu: ");
		int Cena = Integer.parseInt(scenner.nextLine());
		//-----------------------------------------------
		
		metode.izmeniCenuKursa(imeKursa, Cena);*/
		//-----------------------------------------------
		//metode.prikaziSveKurseve();
		
		/*Kurs k = metode.vratiKursPoID(3);
		System.out.println("ID: " + k.getIdKursa());
		System.out.println("Ime: " + k.getImeKursa());
		System.out.println("Cena " + k.getCena());*/
		//-----------------------------------------------
		
		//jedanesti čas
		/*System.out.println("Unesite id ");
		Scanner ucitaj = new Scanner(System.in);
		String id = ucitaj.nextLine();
		ucitaj.close();	
		
		User user = metode.vratiUseraPoID(Integer.parseInt(id));
		if(user.getIdUser() != 0) {
		
		System.out.println("ID: " + user.getIdUser());
		System.out.println("User name : " + user.getUserName());
		System.out.println("Password : " + user.getPassword());
		System.out.println("Maticni broj : " + user.getMadBroj());
		} else {
			System.out.println("Ne postoji taj user! " );
		}*/
	
		
		Scanner scanner = new Scanner(System.in);
		
		 String userName = scanner.nextLine();

		  int id = metode.vratiIdPoUserneme(userName);
		  System.out.println(" Telefoni od : " + userName);

		  List<String> listaBrojevaTelefona = new ArrayList<String>();

		  if(id != 0) {

		  listaBrojevaTelefona = metode.vratiBrojTelefona(id);

		  for(String s : listaBrojevaTelefona) { System.out.println(s); }
		  for(String s : listaBrojevaTelefona) { 

			  System.out.println(s); 

		  }

		  System.out.println(" kursevi koje pohadja: " + userName);

		  List<Integer> listaIdKurseva = new ArrayList<Integer>();

			listaIdKurseva = metode.vratiIdKursaPoIdUsera(id);

			for(int i: listaIdKurseva) {
				Kurs kurs = metode.vratiKursPoID(i);
				System.out.println(kurs.getImeKursa() + " " + kurs.getCena());
			}

		  }else { System.out.println("Nepostojeci user!"); }




		List<Integer> listaIdKurseva = new ArrayList<Integer>();

		listaIdKurseva = metode.vratiIdKursaPoIdUsera(id);

		for(int i: listaIdKurseva) {
			Kurs kurs = metode.vratiKursPoID(i);
			System.out.println(kurs.getImeKursa() + " " + kurs.getCena());
		}

	}

  }

