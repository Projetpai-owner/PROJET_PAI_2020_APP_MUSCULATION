package fr.univ.lille.fil.mbprestservice.entity;

import java.io.Serializable;

/**
 * Classe permettant de représenter la clé primaire de la table ami
 * @author Anthony Bliecq
 *
 */
public class AmiPk implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pidun;
	private int piddeux;
	
	public AmiPk() {
		// TODO Auto-generated constructor stub
	}
	
	public AmiPk(int pidun, int piddeux) {
		this.pidun = pidun;
		this.piddeux = piddeux;
	}


	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return ((AmiPk)obj).pidun==this.pidun&&((AmiPk)obj).piddeux==this.piddeux;
	}


	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	
}
