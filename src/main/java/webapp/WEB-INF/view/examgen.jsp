<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Database Query Form</title>
</head>
<!-- Inline CSS -->
<style type="text/css">i
	.error{color:red}
</style>
<body>
<h1 style="color:green;text-align:center;">Query the repository for exam Question</h1>
<hr style="width:50%">
<h3 style="color:Tomato;text-align:center;">Please select Knowledge Area to draw questions from</h3>

<form:form style="text-align:center;" action="${pageContext.request.contextPath}/question-answer/process-query"
	modelAttribute ="qanda">
	Knowledge Area: <br><form:input path ="knowledge"/>
         
        <br><br>
	<input type ="submit" value="Query Database"/>
</form:form>
<hr style="width:50%">
<br><br>
<h4 style="text-align:center;"><a href ="${pageContext.request.contextPath}/"><button type="button">Return to main page</button></a></h4>
</body>
</html>