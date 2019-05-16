package fr.epsi.seanProject.dao;

import java.util.List;

import fr.epsi.seanProject.beans.Statut;

public interface IStatutDao {

	public final static Integer PRIVEE = 2;
	public final static Integer PUBLIE = 1;
	public final static Integer ARCHIVE = 3;
	public final static Integer ANNULE = 4;
	
	Statut getStatut(Integer id);
	List<Statut> getListOfStatuts();
}
