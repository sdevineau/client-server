<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.emn.app.model.CompilationResult" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Plateforme de Téléchargements</title>
<link rel=stylesheet
	href="<%=request.getContextPath()%>/resources/style/design.css">
</head>
</head>
<body>
	<c:import url="header.jsp" />
	<c:import url="menu.jsp" />
 	<div class="file_box">
		<h2>Etat de l'upload de votre fichier</h2>
	Le fichier " <strong> ${fileName} </strong>" a bien été uploadé.
	</div>
	<div class="file_box">
		<h3>Compilation :</h3>
		<c:choose>
			<c:when test="${result.getCompilationError().isEmpty()}">
					Compilation correctement effectuée!<br/>
					TimeElapsed : ${result.getCompilationTimeElapsed()}<br/>
			</c:when>
			<c:otherwise>
					Output : ${result.getCompilationOutput()}<br/>
					Errors : ${result.getCompilationError()}<br/>
			</c:otherwise>
		</c:choose>
		<h3>Execution :</h3>
		<c:choose>
			<c:when test="${result.getExecutionError().isEmpty()}">
					Output : ${result.getExecutionOutput()}
					TimeElapsed : ${result.getExecutionTimeElapsed()} s
			</c:when>
			<c:otherwise>Errors : ${result.getExecutionError()}</c:otherwise>
		</c:choose>
	</div>
</body>
</html>