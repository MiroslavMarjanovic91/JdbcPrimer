package model;
import javax.persistence.Entity;

@Entity //novi entitet

/*@DiscriminatorValue("sales_manager") // ovo u bazi menja Prodavca u sales_mangera
//ovo je samo za single table strategy */
public class Kupac extends User {

private String jmbg;

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	
}
