<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp"/>

       <div class="span9">
		<form class="form-horizontal" method="POST" action="">
			<fieldset>
				<legend>Contact Us:</legend>
				<div class="control-group">
                	<label class="control-label" >Email</label>
                	<div class="controls">
                		<input name="email" id="email"/>
                	</div>
                </div>
                <div class="control-group">
                	<label class="control-label">Subject</label>
                	<div class="controls">
                		<input name="subject" id="subject"/>
                	</div>
                </div>
                <div class="control-group">
                	<label class="control-label" >Body</label>
                	<div class="controls">
                		<textarea name="body" id="body"></textarea>
                	</div>
                </div>
                <button class="btn" type="submit">Send Email</button>
             </fieldset>   
    	</form>
    </div>
 <jsp:include page="footer.jsp"/>
