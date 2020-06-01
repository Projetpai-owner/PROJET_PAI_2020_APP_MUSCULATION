package fr.univ.lille.fil.mbprestservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "support")
public class Support {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sid;
	private String object;
	private String description;
	
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
