<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Test</title>
    </head>
    <body>
     <%@ include file="menu.jsp" %>
     <c:set var="pseudo" scope="page"> diby18 </c:set>
        <p>
			<c:out value= "${ pseudo }" /> 
        </p>
     <c:set var="pseudo" scope="page"> zazou33 </c:set>
        <p>
			<c:out value= "${ pseudo }" /> 
        </p>
        
 <!-- 
     <c:choose>
     	<c:when test="${ variable }">Du texte</c:when>
     	<c:when test="${ autreVariable }">Du texte</c:when>
     	<c:when test="${ encoreAutreVariable }">Du texte</c:when>
     	<c:otherwise>a</c:otherwise>
     </c:choose>
     
     <c:forEach items="${ titres }" var="i" begin="0" end="1" varStatus="status">
     	 <p>
     	 	N°<c:out value="${ status.index }" /> : <c:out value="${ i }" /> !
    	 </p>
     </c:forEach>
      
      
     <c:forTokens var="morceau" items="Un élément/Un autre/encore un !" delims="/ ">
     	<p>${ morceau }</p>
     </c:forTokens>
     
     <c:if test="${ !empty connectionForm.resultat }"><p><c:out value="${ connectionForm.resultat }"></c:out></p></c:if>

	<form method="post" action="bonjour">
		<p>
			<label for="login">Login : </label>
			<input type="text" id="login" name="login" />
		</p>	
		<p>
			<label for="pass">Mot de passe : </label>
			<input type="password" id="pass" name="pass" />
		</p>	
		<input type="submit" />
	</form>
	
	<c:if test="${ !empty fichier }"><p><c:out value="Le fichier ${ fichier } (${ description }) a été uploadé !" /></p></c:if>
    <form method="post" action="bonjour" enctype="multipart/form-data">
        <p>
            <label for="description">Description du fichier : </label>
            <input type="text" name="description" id="description" />
        </p>
        <p>
            <label for="fichier">Fichier à envoyer : </label>
            <input type="file" name="fichier" id="fichier" />
        </p>
        
        <input type="submit" />
    </form>
    
    
    <c:out value="${ prenom }"></c:out>
    <c:if test="${ !empty sessionScope.prenom && !empty sessionScope.nom }">
        <p>Vous êtes ${ sessionScope.prenom } ${ sessionScope.nom } !</p>
    </c:if>
-->
    
    <form method="post" action="bonjour">
        <p>
            <label for="nom">Nom : </label>
            <input type="text" name="nom" id="nom" />
        </p>
        <p>
            <label for="prenom">Prénom : </label>
            <input type="text" name="prenom" id="prenom" />
        </p>
        
        <input type="submit" />
    </form>
    
    <ul>
        <c:forEach var="utilisateur" items="${ utilisateurs }">
            <li><c:out value="${ utilisateur.prenom }" /> <c:out value="${ utilisateur.nom }" /></li>
        </c:forEach>
    </ul>    
     
    </body>
</html>







