<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Database Query Result</title>
</head>
<body>
<h1 style="color:green;text-align:center;">The Result of your query from the Repository</h1>
<h1 style="color:green;text-align:center;">5 Question Exam Generated</h1>
<hr style="width:50%">
<h4 style="text-align:center;"><input type="button" onclick="shows()" value="SHOW ANSWERS"></h4>
<c:choose>
	<c:when test="${list==null}">
		<h2>${message}</h2>
	</c:when>
<c:otherwise>
	<c:forEach items="${list}" var="u">
		
		<h2 style="text-align:center;"><font color ="purple">Question :</font><br><c:out value="${u.question}"/>
		<br><br><font color ="purple">Enter answer  </font><input type=text></h2>
		<p style="visibility:hidden;text-align:center;"><font color ="blue">Correct Answer :</font><c:out value="${u.answer}"/></p>
		
		<h2 style="text-align:center;"><font color ="purple">Knowledge : </font><c:out value="${u.knowledge}"/></h2>
		
		<hr style="width:50%">
	</c:forEach>
	</c:otherwise>
</c:choose>

<script>function shows(){
	let l = document.getElementsByTagName("p");
	for(let i= 0;i<l.length;i++){
		l[i].style.visibility = "visible";
	}
}
</script>

<h4 style="text-align:center;"><a href ="${pageContext.request.contextPath}/"><button type="button">Return to main page</button></a></h4>
</body>
</html>