package fr.univ.lille.fil.mbprestservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ami")
@IdClass(value=AmiPk.class)
public class Ami {
	
	 @Id
	 @OneToOne
	 @JoinColumn(name = "pidun")
	 private User pidun;
	 

	 @Id
	 @OneToOne
	 @JoinColumn(name = "piddeux")
	 private User piddeux;


	 public Ami() {
		// TODO Auto-generated constructor stub
	}
	 
	public Ami(User pidun, User piddeux) {
		this.pidun = pidun;
		this.piddeux = piddeux;
	}


	public User getPidun() {
		return pidun;
	}


	public User getPiddeux() {
		return piddeux;
	}
	 
	 
	 

	
	
}
