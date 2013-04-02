<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<div class="row-fluid">
       <div class="span3">
           <div class="bs-docs-example">
           <form method="post">
          	  <ul class="nav nav-list">
          	  	<li class="nav-header">SObject List</li>
             	<c:forEach items="${showSObjects}" var="showSObjects">    
                  <li><a href="/login/sobject/query/${showSObjects}">${showSObjects}</a></li>
               </c:forEach>	
            </ul>
            </form>
           </div>
         </div>
