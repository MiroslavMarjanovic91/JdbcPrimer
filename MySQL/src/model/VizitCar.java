package model;
import javax.persistence.Embeddable;

//ova klasa je Value Object koja ce se nakaciti na nasu klasu User 
@Embeddable
public class VizitCar {
	
	private String ime; 
	private String brTelefona;
	private String email;
	
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getBrTelefona() {
		return brTelefona;
	}
	public void setBrTelefona(String brTelefona) {
		this.brTelefona = brTelefona;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
