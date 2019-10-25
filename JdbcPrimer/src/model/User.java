package model;

public class User {
	
	//ne moraju da budu isti nazivi kao iz tabele SQL-a
	private int idUser;
	private String userName;
	private String password;
	private int madBroj;
	
	
	
	
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
	public int getMadBroj() {
		return madBroj;
	}
	public void setMadBroj(int madBroj) {
		this.madBroj = madBroj;
	}
	
	
	
	
	
	
	

}
