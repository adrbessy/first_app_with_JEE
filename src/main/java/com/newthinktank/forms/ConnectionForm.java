package com.newthinktank.forms;

import jakarta.servlet.http.HttpServletRequest;

public class ConnectionForm {
	private String resultat;
	
	public String getResultat() {
		return resultat;
	}
	
	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
	
	public void verifierIdentifiants(HttpServletRequest request) {
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");
		if (pass!=null) {
			if(pass.equals(login + "123")) {
				resultat = "Vous êtes bien connecté !";
			}
			else {
				resultat = "Identifiants incorrects";
			}
		}
	}
}
