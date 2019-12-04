package model;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
/*grupa anotacija koja menja u tabeli imena i kolone umesto naziva klase 
ide iznad klase, a ispod entiteta odnosi se na strukturu*/
@Table(name = "korisnik")
@Inheritance(strategy = InheritanceType.JOINED) //za nasledjivanje baze u hibernate u kasama Prodavac i Kupac nasledjenjim iz klase user

/*@DiscriminatorColumn(name = "VrstaUsera",  
discriminatorType  = DiscriminatorType.STRING) //u bazi menja ime koje je hibernate sam dodelio DTYPE u VrstaUsera
*/ //ovo je samo za single table strategy
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	
	//primitivni tipovi podataka
	private int idUser;
	//menjamo ime u tabeli 
	@Column(name = "user_name")
	private String userName;
	private String password;
	private double novcanik;
	
	//imoportujemo iz clase VizitCar
	//@Embedded //ovo je polje vezuje sa klasom VizitCar 
	
	@ElementCollection //Anotacija za listu podataka, kada se vezujemo za velju objekat
	private List<VizitCar> vizitCars = new ArrayList<VizitCar>();
	
	//veza jedan na vise
	//List je interfesj, a ArryLista je klasa koja implementira List
	//interfejs klasa metoda 
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "korisnik") //lista usera u automobili i listu automobila u useru
	//svaki put kad trazi usera iz baze ucitava i listu, ako radimo sa velikim bazama
	private List<Car> automobili = new ArrayList<Car>();
	//ovo je lista entiteta

	public List<Car> getAutomobili() {
		return automobili;
	}
	public List<VizitCar> getVizitCars() {
		return vizitCars;
	}
	public void setVizitCars(List<VizitCar> vizitCars) {
		this.vizitCars = vizitCars;
	}
	public void setAutomobili(List<Car> automobili) {
		this.automobili = automobili;
	}
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getNovcanik() {
		return novcanik;
	}
	public void setNovcanik(double novcanik) {
		this.novcanik = novcanik;
	}
	
}
