package Contorlor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import model.Car;
import model.User;

public class HibernateDAO {
	
	SessionFactory factory = new Configuration().configure().buildSessionFactory();
	
	
	public boolean snimiAutoUBazu(Car car) {
		
		Session sesija = factory.openSession();
			sesija.beginTransaction();
			
			try {
				//lazy initalization - rucno preuzimanje list
				sesija.save(car);
				sesija.getTransaction().commit();
				System.out.println();
				return true;
			} catch (Exception e) {
				sesija.getTransaction().rollback();
				return false;
			} finally {
				sesija.close();
			}
	}
	
	public Car vratiAutoPoID (int id) {
		
		Session sesija = factory.openSession();
		sesija.beginTransaction();
		//dodeljujemo vrednost objektu null;
		Car car = null;
		try {
			//ovo pravimo da nam objekat ne bi vratio nall;
			car = sesija.get(Car.class, id);
			sesija.getTransaction().commit();
			System.out.println();
			return car;
		} catch (Exception e) {
			sesija.getTransaction().rollback();
			return null;
			//moze i return car; 
		} finally {
			sesija.close();
		}
	}
   public User vratiUseraPoID (int id) {
		
		Session sesija = factory.openSession();
		sesija.beginTransaction();
		//dodeljujemo vrednost objektu null;
		User user = null;
		try {
			//ovo pravimo da nam objekat ne bi vratio nall;
			user = sesija.get(User.class, id);
			sesija.getTransaction().commit();
			System.out.println();
			return user;
		} catch (Exception e) {
			sesija.getTransaction().rollback();
			return null;
			//moze i return car; 
		} finally {
			sesija.close();
		}
	}
	
	public void updateCarPrice (int id, double novaCena) {
		
		Session sesija = factory.openSession();
		sesija.beginTransaction();
		//dodeljujemo vrednost objektu null;
		Car car = null;
		try {
			//ovo pravimo da nam objekat ne bi vratio nall;
			car = sesija.get(Car.class, id);
			car.setCena(novaCena);
			sesija.update(car);
			sesija.getTransaction().commit();
			System.out.println();
		
		} catch (Exception e) {
			sesija.getTransaction().rollback();
		
		} finally {
			sesija.close();
		}
	}
	
	public boolean delateCar (int id) {
	
	Session sesija = factory.openSession();
	sesija.beginTransaction();
	//dodeljujemo vrednost objektu null;
	Car car = null;
	try {
		car = sesija.get(Car.class, id);
		sesija.delete(car);
		sesija.getTransaction().commit();
		System.out.println();
		return true;
	
	} catch (Exception e) {
		sesija.getTransaction().rollback();
		return false;
		
	} finally {
		sesija.close();
	}
}
	
	
public boolean snimiUsera(User user) {
		
		Session sesija = factory.openSession();
			sesija.beginTransaction();
			
			try {
				sesija.save(user);
				sesija.getTransaction().commit();
				System.out.println();
				return true;
			} catch (Exception e) {
				sesija.getTransaction().rollback();
				return false;
			} finally {
				sesija.close();
			}
	}
public boolean daLiUserImaDovoljnoParaZaAutomobile(User user , List<Car>automobili) {
	
	Session sesija = factory.openSession();
	sesija.beginTransaction();
	
	double novcanik=user.getNovcanik();
	double krajnjaCena=0;
	
	for(Car car : automobili) {
		krajnjaCena+=car.getCena();
		
	}
	
	try {
		if(novcanik>=krajnjaCena) {
			System.out.println("Kupili ste automobile");
			sesija.getTransaction().commit();
			return true;
		}else {
			System.out.println("Nemate dovoljno novca ");
			sesija.getTransaction().commit();
			return true;
		}
	} catch (Exception e) {
		sesija.getTransaction().rollback();
		return false;
	} finally {
		sesija.close();
	}
	
}


public void spojiUseraIcar(User user,List<Car>ListaAutomobila) {
	Session sesija = factory.openSession();
	sesija.beginTransaction();
	
	user.setAutomobili(ListaAutomobila);
	 double krajnjaCena=0;
	try {
		for(Car car : ListaAutomobila) {
			car.setKorisnik(user);
			krajnjaCena+=car.getCena();
		}
		user.setNovcanik(user.getNovcanik()-krajnjaCena);
		    sesija.update(user);
		    System.out.println("Sve je ok !");
			sesija.getTransaction().commit();
	} catch (Exception e) {
		sesija.getTransaction().rollback();
	} finally {
		sesija.close();
	}
	
}
public void izlistajAutomobile(User user) {
	Session sesija = factory.openSession();
	sesija.beginTransaction();
	List<Car>automobili=new ArrayList<Car>();
	
	try {
		/*//lazy initialization - rucno preuzimanje list
		User u = sesija.get(User.class, user.getIdUser());
		//u.getAutomobili().size();
		Hibernate.initialize(u);*/
		
		    automobili=user.getAutomobili();
		    Hibernate.initialize(automobili);
		    System.out.println("Korisnik "+ user.getUserName() +" je kupio : ");
	        for(Car car : automobili) {
	        	System.out.println(car.getMarka()+" "+ car.getModel());
	        }
	        
			sesija.getTransaction().commit();
	} catch (Exception e) {
		e.printStackTrace();
		sesija.getTransaction().rollback();
	} finally {
		sesija.close();
	}
	
}
	
//metoda za vezu 1 na 1 OneToOne
	/*public void linkujUseraIauto(int idCar, int idUser) {
		
		Session sesija = factory.openSession();
		sesija.beginTransaction();
		Car car;
		User user;
		try {
			//preuzmi usera i car iz baze
			car = sesija.get(Car.class, idCar);
			user = sesija.get(User.class, idUser);
			//linkuj ih
			car.setKorisnik(user);
			user.setAuto(car);
			//update-uj u bazi
			sesija.update(user);
			sesija.update(car);
			
			sesija.getTransaction().commit();
				
		} catch (Exception e) {
			sesija.getTransaction().rollback();
		
		} finally {
			sesija.close();
		}
		
	}*/
	
	
	public List<Car> dajMiSveAutomobileIzBaze() {
	Session sesija = factory.openSession();
	sesija.beginTransaction();

	List<Car> automobili = new ArrayList<Car>();
//Hibernate Query Language ili HQL(HAKUEL) - Hajbernetov jezik koji se obraca direkno kasi
//Nikada se ne obracamo tabeli, obracamo se dirkeno klasi - class, zaravo je to proksi objekat
//from User; 
//Select userName from User; 
//set iz jdbc-a u hajbernetu je Query object.getResultList - ovo uvek vraca Listu onoga sto zelimo
	
	try {
		String upit = "FROM car";
		Query query = sesija.createQuery(upit);
		automobili = query.getResultList();
		
				sesija.getTransaction().commit();
		return automobili;
	
	} catch (Exception e) {
		e.printStackTrace();
		sesija.getTransaction().rollback();
		return null;
	} finally {
		sesija.close();
	}
	
}
	public List<Car> dajMiSveAutomobileIzBazeKojiSuJeftinijiOdZadateCene(double odabranaCena) {
	//U SQL bi bilo Select * from car where cena ?
	//U hibernetu bice from Car where cena < :Snupi (bilo koji naziv) 
	Session sesija = factory.openSession();
	sesija.beginTransaction();

	List<Car> automobili = new ArrayList<Car>();
//Hibernate Query Language ili HQL(HAKUEL) - Hajbernetov jezik koji se obraca direkno kasi
//Nikada se ne obracamo tabeli, obracamo se dirkeno klasi - class, zaravo je to proksi objekat
//from User; 
//Select userName from User; 
//set iz jdbc-a u hajbernetu je Query object.getResultList - ovo uvek vraca Listu onoga sto zelimo
	try {
		String upit = "FROM Car WHERE cena < :Snupi"; //ovde umesto znaka (?) ide (:)
		Query query = sesija.createQuery(upit);
		query.setParameter("Snupi", odabranaCena);
		
		automobili = query.getResultList();
		
				sesija.getTransaction().commit();
		return automobili;
	
	} catch (Exception e) {
		e.printStackTrace();
		sesija.getTransaction().rollback();
		return null;
	} finally {
		sesija.close();
	}
	
  }

	
}
