package fr.univ.lille.fil.mbprestservice.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "personne")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pid;
	private String firstName;
	private String lastName;
	private Date bornDate;
	private String sex;
	private String email;
	private String password;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "sid")
	private Gym sid;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "adresse")
	private Place adresse;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + "; lastName=" + lastName + "; bornDate= " + bornDate.toString()
				+ "; sex=" + sex + "; email=" + email + "; password=" + password + "]";
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public Gym getSid() {
		return sid;
	}

	public void setSid(Gym sid) {
		this.sid = sid;
	}

	public Place getAdresse() {
		return adresse;
	}

	public void setAdresse(Place adresse) {
		this.adresse = adresse;
	}

}
