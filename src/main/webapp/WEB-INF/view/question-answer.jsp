<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Secure Coding - SIT 218 - Q and A app</title>
<!-- Inline CSS -->
<style type="text/css">
	.error{color:red}
</style>
</head>
<body>
<h3 style="color:green;">Question and Answer Builder</h3>
<h3 style="color:Tomato;">Please use the following form to create Question and Answer to be added to the repository</h3>

<form:form action="${pageContext.request.contextPath}/question-answer/process-qanda"
	modelAttribute ="qanda">
	Question:<br><form:input path="question" size="50" style="height:50px"/>
	<form:errors path="question" cssClass="error"></form:errors>
	<br><br>
	Answer:<br><form:input path ="answer"/>
	<form:errors path="answer" cssClass="error"></form:errors>
	<br><br>
	Knowledge Area: <br><form:select path="knowledge">  
        <form:option value="SIT218" label="SIT218 Secure coding"/>  
        <form:option value="SIT282" label="SIT282 Digital Forensics"/>  
        <form:option value="SIT382" label="SIT382 Ethical Hacking"/>    
        </form:select> 
        <br><br>
	<input type ="submit" value="Add to database"/>
</form:form><br><br>
<h4><a href ="${pageContext.request.contextPath}/"><button type="button">Return to main page</button></a></h4>
</body>
</html>