<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="span9">
         <section id="manage-users">
          <div class="page-header">
            <h1>Manage ${currentSObject} Object</h1>
          </div>
          <c:if  test="${!empty sobjectsuccesscreate}">
           <div class="alert alert-success">
          	 <button type="button" class="close" data-dismiss="alert">×</button>
 			 <strong>Success:</strong>
 			 	Your record has been created - Id: ${sobjectsuccesscreate}
		   </div>
		   </c:if>
		   <c:if  test="${!empty sobjectsuccessdelete}">
           <div class="alert alert-success">
          	 <button type="button" class="close" data-dismiss="alert">×</button>
 			 <strong>Success:</strong>
 			 	Your record was deleted successfully
		   </div>
		   </c:if>
		   <c:if  test="${!empty sobjecterror}">
		   <div class="alert alert-error">
             <button type="button" class="close" data-dismiss="alert">×</button>
 			 <strong>Error:</strong>
 			 	Message: ${sobjecterror}
		   </div>
		    </c:if>
		    <c:if  test="${!empty soqlqueryerror}">
		   <div class="alert alert-error">
             <button type="button" class="close" data-dismiss="alert">×</button>
 			 <strong>Error:</strong>
 			 	Message: ${soqlqueryerror}
		   </div>
		    </c:if>
           <div class="tabbable">              
   			 <ul class="nav nav-tabs" id="getStartedTab">
          	  	<li><a href="/login/sobject/query/${currentSObject}" onClick="return objvalidate.checkIfSObjectSelected('${currentSObject}')">Query</a></li>
          	 	<li><a href="/login/sobject/view/${currentSObject}" onClick="return objvalidate.checkIfSObjectSelected('${currentSObject}')">View</a></li>
          	 	<li><a href="/login/sobject/create/${currentSObject}" onClick="return objvalidate.checkIfSObjectSelected('${currentSObject}')">Create</a></li>
          	 	<li><a href="/login/sobject/edit/${currentSObject}/${sobjectRecord}" onClick="return objvalidate.checkIfSObjectSelected('${currentSObject}')">Edit</a></li>
          	 </ul>
          	   <div class="tab-content">
          	    <div id="/login/sobject/query/${currentSObject}" class="tab-pane active">
          	  	<c:if  test="${!empty sobjectQuery}">
          	  		<form method="POST" action=""> <textarea name="soqlquery" id="soqlquery">${sobjectQuery}</textarea><br />
          	  		<button class="btn" type="submit">Run</button></form>
          	  	</c:if>
          	  	</div>
          	  	</div>
          	  	<div class="tab-content">
          	  	<div id="/login/sobject/view/${currentSObject}" class="tab-pane active">
          	 	<c:if  test="${!empty sobjectFieldNames}">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                    <th>Delete</th>
                    <th>Edit</th>
                    <c:forEach items="${sobjectFieldNames}" var="fieldNames">
                        <th>${fieldNames}</th>
                     </c:forEach>
                    <c:if test="${!empty sobjectRecords}">
                   	 	<c:forEach items="${sobjectRecords}" var="sobjectRecords" varStatus="outer">
                   	 	<tr>
                   	 		 <c:forEach items="${sobjectRecords}" var="sobjectRecord" varStatus="inner">
                   	 		 <c:if test="${inner.first}">
                   	 		 	<td><form action="/login/sobject/delete/${currentSObject}/${sobjectRecord}" method="post"><input type="submit" class="btn btn-danger btn-mini" value="Delete"/></form></td>
                   	 		 	<td><form action="/login/sobject/edit/${currentSObject}/${sobjectRecord}" method="post"><input type="submit" class="btn btn-danger btn-mini" value="Edit"/></form></td>
                   	 		 </c:if>
                   	 	 	<td>
                    			${sobjectRecord}
                    		</td>
                    		</c:forEach>
                    	</tr> 
                    	</c:forEach>	
                    </c:if>
                    </tr>
                    </thead>
                </table>
                <div class="pagination">
  				<ul>
  					<c:if  test="${!empty pagination}">
  					<li><a href="#">Prev</a></li>
  					<c:forEach items="${pagination}" var="pagination">
  					<li><a href="${pagination.value}">${pagination.key}</a></li>
  					</c:forEach>
  					<li><a href="#">Next</a></li>
  					</c:if>
  				</ul>
  				</div>
           		</c:if>
           		</div>
          	  </div>
          	  <div class="tab-content">
          	  	<div id="/login/sobject/create/${currentSObject}" class="tab-pane active">
          	 	  <c:if  test="${!empty requiredSobjectFieldNames}">
          	 		<form method="post" action="" class="form-horizontal">
          	 		<div class="control-group">
          	 		<c:forEach items="${requiredSobjectFieldNames}" var="requiredSobjectFieldNames">
                		<label class="control-label">${requiredSobjectFieldNames.key}</label>
                		<div class="controls">
                			<input type="text" name="${requiredSobjectFieldNames.key}" id="${requiredSobjectFieldNames.key}" required>${requiredSobjectFieldNames.value}</input>
                		</div>
                	</c:forEach>
                    </div> 
          	 		<c:forEach items="${optionalSobjectFieldNames}" var="optionalSobjectFieldNames">
          	 		<div class="control-group">
                		<label class="control-label">${optionalSobjectFieldNames.key}</label>
                		<div class="controls">
                			<input type="text" name="${optionalSobjectFieldNames.key}" id="${optionalSobjectFieldNames.key}">${optionalSobjectFieldNames.value}</input>
                		</div>
                	</div>
                	</c:forEach>
                	 
                	<button class="btn" type="submit">Save ${currentSObject}</button>
           		 </form>
           		 </c:if>
          	 	</div>
          	</div>
          	<div class="tab-content">
          	  	<div id="/login/sobject/edit/${currentSObject}/${sobjectRecord}" class="tab-pane active">
          	 	  <c:if  test="${!empty requiredSobjectFieldNames}">
          	 		<form method="post" action="" class="form-horizontal">
          	 		<div class="control-group">
          	 		<c:forEach items="${requiredSobjectFieldNames}" var="requiredSobjectFieldNames">
                		<label class="control-label">${requiredSobjectFieldNames.key}</label>
                		<div class="controls">
                			<input type="text" name="${requiredSobjectFieldNames.key}" id="${requiredSobjectFieldNames.key}" required>${requiredSobjectFieldNames.value}</input>
                		</div>
                	</c:forEach>
                    </div> 
          	 		<c:forEach items="${optionalSobjectFieldNames}" var="optionalSobjectFieldNames">
          	 		<div class="control-group">
                		<label class="control-label">${optionalSobjectFieldNames.key}</label>
                		<div class="controls">
                			<input type="text" name="${optionalSobjectFieldNames.key}" id="${optionalSobjectFieldNames.key}">${optionalSobjectFieldNames.value}</input>
                		</div>
                	</div>
                	</c:forEach>
                	 
                	<button class="btn" type="submit">Save ${currentSObject}</button>
           		 </form>
           		 </c:if>
          	 	</div>
          	</div>
        
   			</div>
        </section>
     </div>
      <script type="text/javascript">
    var objvalidate = {
    		checkIfSObjectSelected: function(name) {
            if (!name) {
            	alertbutton = alert('Please select an Object');
            	if (alertbutton == true)
            		{
            			window.location = '/login/';
            		}
                return false;
            }
           
        }
    };
</script>
  