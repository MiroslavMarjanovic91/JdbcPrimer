package model;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

//anotacija 
@Entity //entiteti su objekti relagno sveta koje zelimo mapirati 

public class Car {
	@Id //primary key not null
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
	//ovaj idCar baza radi preko hibernata, ali moramo ga generisati
	private int idCar;
	private String marka; 
	private String model;  
	private int godiste; 
	private double cena; 
	private boolean registracija;
	
	//enum koji dodajemo 
   @Enumerated
   private VrstaVozila vrstaVozila;
   
   @ManyToOne
   private User korisnik; 
  
	public User getKorisnik() {
	return korisnik;
}

public void setKorisnik(User korisnik) {
	this.korisnik = korisnik;
}

	public Car() {
		
	}
	
	public Car(String marka, String model, int godiste, double cena, boolean registracija, VrstaVozila vrstaVozila) {
		this.marka = marka;
		this.model = model;
		this.godiste = godiste;
		this.cena = cena; 
		this.registracija = registracija;
		this.vrstaVozila = vrstaVozila;
	}
	
	public VrstaVozila getVrstaVozila() {
		return vrstaVozila;
	}

	public void setVrstaVozila(VrstaVozila vrstaVozila) {
		this.vrstaVozila = vrstaVozila;
	}

	public int getIdCar() {
		return idCar;
	}

	public void setIdCar(int idCar) {
		this.idCar = idCar;
	}

	public String getMarka() {
		return marka;
	}
	public void setMarka(String marka) {
		this.marka = marka;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getGodiste() {
		return godiste;
	}
	public void setGodiste(int godiste) {
		this.godiste = godiste;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public boolean isRegistracija() {
		return registracija;
	}
	public void setRegistracija(boolean registracija) {
		this.registracija = registracija;
	} 
	

}
