package fr.univ.lille.fil.mbprestservice.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fr.univ.lille.fil.mbprestservice.enumeration.Role;
import fr.univ.lille.fil.mbprestservice.enumeration.Sexe;
/**
 * Classe permettant d'intéragir avec la table utilisateur
 * @author Anthony Bliecq
 *
 */
@Entity
@Table(name = "user")
public class User implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pid;
	private String nom;
	private String prenom;
	@Column(name="date_de_naissance")
	private Date bornDate;
	@Enumerated(EnumType.STRING)
	private Sexe sexe;
	private String username;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;

	@ManyToOne
	@JoinColumn(name = "sid")
	private Salle sid;

	private String adresse;


	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public Salle getSid() {
		return sid;
	}

	public void setSid(Salle sid) {
		this.sid = sid;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Sexe getSexe() {
		return sexe;
	}

	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return "User [pid=" + pid + ", nom=" + nom + ", prenom=" + prenom + ", bornDate=" + bornDate + ", sexe=" + sexe
				+ ", username=" + username + ", password=" + password + ", sid=" + sid + ", adresse=" + adresse + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    List<GrantedAuthority> authorities
	      = new ArrayList<>();


	    authorities.add(new SimpleGrantedAuthority("ROLE_"+role.toString()));

	    return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}



	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}



}
