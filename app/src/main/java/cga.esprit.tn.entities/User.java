package cga.esprit.tn.entities;

import java.io.Serializable;

/**
 * Entity implementation class for Entity: User
 *
 */

public class User implements Serializable {

	
	private static final long serialVersionUID = 1L;


	protected int id;
	protected String lastName;
	protected String firstName;
	protected String password;
	protected String email;
	protected String role;
	
	public User(){
	}

	

	public User(String lastName, String firstName, String password, String email, String role) {
		
		this.lastName = lastName;
		this.firstName = firstName;
		this.password = password;
		this.email = email;
		this.role = role;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
	
   
}
