package fr.epsi.seanProject.beans;

import java.sql.Date;
import java.util.List;

import fr.epsi.seanProject.mbeans.BlogMXBean;

public class Blog implements BlogMXBean {

	private Integer id;
	private String titre;
	private String description;
	private Utilisateur createur;
	private Date dateCreation;
	private Date dateModification;
	private Statut statut;
	private List<Reponse> listOfReponses;
	
	public List<Reponse> getListOfReponses() {
		return listOfReponses;
	}
	public void setListOfReponses(List<Reponse> listOfReponses) {
		this.listOfReponses = listOfReponses;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Utilisateur getCreateur() {
		return createur;
	}
	public void setCreateur(Utilisateur createur) {
		this.createur = createur;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Date getDateModification() {
		return dateModification;
	}
	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getNbPost() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
