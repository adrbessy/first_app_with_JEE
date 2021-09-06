package com.newthinktank.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.newthinktank.beans.Auteur;
import com.newthinktank.beans.Utilisateur;
import com.newthinktank.dao.DaoException;
import com.newthinktank.dao.DaoFactory;
import com.newthinktank.dao.UtilisateurDao;
import com.newthinktank.forms.ConnectionForm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class SampServlet
 */
public class SampServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private UtilisateurDao utilisateurDao;
	
	public static final int TAILLE_TAMPON = 10240;
    public static final String CHEMIN_FICHIERS = "/home/adrien/Documents/OCR/java/javaEE/fichiers/"; // A changer

    public SampServlet() {
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		Auteur auteur = new Auteur();
		auteur.setPrenom("Adrien");
		auteur.setNom("Bessy");
		auteur.setActif(true);
		request.setAttribute("auteur", auteur);
		
		String name = request.getParameter("name");
		request.setAttribute("name", name);
		String[] noms = {"Adrien", "Isabelle", "Anne", "Dominique"};
		request.setAttribute("noms", noms);
		
		String[] titres = {"Nouvel incendie", "Pikachu", "Raichu"};
		request.setAttribute("titres", titres);
		
		HttpSession session = request.getSession();
		String prenom = (String) session.getAttribute("prenom");
		
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("prenom")) {
					request.setAttribute("prenom",cookie.getValue());
				}
			}
		}
		
		Noms tableNoms = new Noms();
        request.setAttribute("utilisateurs", tableNoms.recupererUtilisateurs());
        */
        
		try {
			request.setAttribute("utilisateurs", utilisateurDao.lister());
		}
		catch (DaoException e){
			request.setAttribute("erreur", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		ConnectionForm connectionForm = new ConnectionForm();
		connectionForm.verifierIdentifiants(request);
		request.setAttribute("connectionForm", connectionForm);
		
		// On récupère le champ description comme d'habitude
        String description = request.getParameter("description");
        request.setAttribute("description", description );

        // On récupère le champ du fichier
        Part part = request.getPart("fichier");
            
        // On vérifie qu'on a bien reçu un fichier
        String nomFichier = getNomFichier(part);

        // Si on a bien un fichier
        if (nomFichier != null && !nomFichier.isEmpty()) {
            String nomChamp = part.getName();
            // Corrige un bug du fonctionnement d'Internet Explorer
             nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
                    .substring(nomFichier.lastIndexOf('\\') + 1);

            // On écrit définitivement le fichier sur le disque
            ecrireFichier(part, nomFichier, CHEMIN_FICHIERS);

            request.setAttribute(nomChamp, nomFichier);
        }
        */
		
		try {
        Utilisateur utilisateur = new Utilisateur();
		
        utilisateur.setNom(request.getParameter("nom"));
        utilisateur.setPrenom(request.getParameter("prenom"));  
        utilisateurDao.ajouter(utilisateur);
        
        request.setAttribute("utilisateurs", utilisateurDao.lister());
		}
        catch (Exception e) {
            request.setAttribute("erreur", e.getMessage());
        }
        
        /*
        Noms tableNoms = new Noms();
        tableNoms.ajouterUtilisateur(utilisateur);
        
        request.setAttribute("utilisateurs", tableNoms.recupererUtilisateurs());
        
        HttpSession session = request.getSession();
        
        session.setAttribute("nom", nom);
        session.setAttribute("prenom", prenom);
      
        Cookie cookie = new Cookie("prenom",prenom);
        cookie.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(cookie);
          */
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
	}
	
	private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            try {
                sortie.close();
            } catch (IOException ignore) {
            }
            try {
                entree.close();
            } catch (IOException ignore) {
            }
        }
    }
    
    private static String getNomFichier( Part part ) {
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
    }   

}



