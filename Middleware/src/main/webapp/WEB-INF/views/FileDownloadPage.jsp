<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Plateforme de Téléchargements</title>
<link rel=stylesheet
	href="<%=request.getContextPath()%>/resources/style/design.css">
</head>
<body>
	<c:import url="header.jsp" />
	<c:import url="menu.jsp" />

	<div class="file_box">
		<h2>Liste des fichiers</h2>
			<ul>
			<c:forEach items="${fileList}" var="fileName">
				<li><a href="#">${fileName}</a></li>
			</c:forEach>
			</ul>
	</div>

</body>
</html>