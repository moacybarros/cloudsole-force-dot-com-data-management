<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%> 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>RocketForce Contact Form</title>
</head>
<body>
	 Contact Me:
	<form:form method="post" action="send" commandName="contact" class="form-vertical">
                <form:label path="Email">Email</form:label>
                <form:input path="Email" />
                <form:label path="Subject">Subject</form:label>
                <form:input path="Subject" />
                <form:label path="Body">Body</form:label>
                <form:input path="Body" />
                <input type="submit" value="Send Email" class="btn"/>
    </form:form>
</body>
</html>