package com.newthinktank.dao;

import java.util.List;

import com.newthinktank.beans.Utilisateur;

public interface UtilisateurDao {

    void ajouter( Utilisateur utilisateur ) throws DaoException;
    List<Utilisateur> lister() throws DaoException;
	
}
