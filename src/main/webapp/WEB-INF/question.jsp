<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Question</title>
</head>
<body>
<c:choose>
<c:when test="${sessionScope.partie ne null}">
<h1>Utilisateur: ${sessionScope.partie.joueur.prenom} (${sessionScope.partie.joueur.niveau.nom}), meilleur score: ${sessionScope.partie.joueur.meilleurScore} verbe<c:if test="${sessionScope.partie.joueur.meilleurScore>1}">s</c:if></h1>
<h2>Question ${sessionScope.partie.nbQuestions} : le verbe ${sessionScope.question.verbe.baseVerbale}</h2>
<h3>Il vous reste ${sessionScope.question.nbSecondesRestantes} seconde(s) pour répondre</h3>
	<form action="jeu" method="post">
		<table>
			<tr>
				<td>${sessionScope.question.verbe.baseVerbale}
				<c:if test="${sessionScope.partie.joueur.niveau.nom eq 'Débutant'}"> (${sessionScope.question.verbe.traduction})</c:if>
				</td>
				<c:choose>
				<c:when test="${sessionScope.partie.joueur.niveau.nom eq 'Expert'}">
				<td><input type="text" name="PRETERIT" placeHolder="preterit" required/></td>
				<td><input type="text" name="PARTICIPE_PASSE" placeHolder="participe Passe" required/></td>
				</c:when>
				<c:otherwise>
					<c:choose>
					<c:when test="${sessionScope.random>0.5}">
						<td><input type="text" name="PRETERIT" placeHolder="preterit" value="${sessionScope.question.verbe.preterit}" required/></td>
						<td><input type="text" name="PARTICIPE_PASSE" placeHolder="participe Passe" required/></td>
					</c:when>
					<c:otherwise>
						<td><input type="text" name="PRETERIT" placeHolder="preterit" required/></td>
						<td><input type="text" name="PARTICIPE_PASSE" placeHolder="participe Passe" value="${sessionScope.question.verbe.participePasse}" required/></td>
					</c:otherwise>
					</c:choose>
				</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Envoyer"/></td>
			</tr>
		</table>
	</form>
	<a href="deconnexion">Deconnexion</a>
</c:when>
<c:otherwise>Session expirée. Cliquez <a href="index.jsp">ici</a> pour vous connecter</c:otherwise>
</c:choose>
</body>
</html>