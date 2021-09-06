package com.newthinktank.beans;

public class Utilisateur {
	
	private String nom;
    private String prenom;
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) throws BeanException {
    	if (nom.isEmpty()) {
    		throw new BeanException("Il faut mettre un nom !");
    	}
    	else {
            if (nom.length() > 15) {
                throw new BeanException("Le nom est trop grand ! (15 caractères maximum)");
            }
            else {
                this.nom = nom; 
            }
    	}
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

}
