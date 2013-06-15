<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="span8">
         <section id="manage-sobjects">
          <div class="page-header">
            <h1>Manage ${currentSObject} Object</h1>
          </div>
		   <c:if  test="${!empty success}">
           <div class="alert alert-success">
          	 <button type="button" class="close" data-dismiss="alert">×</button>
 			 <strong>Success:</strong>
 			 	${success}
		   </div>
		   </c:if>
		   <c:if  test="${!empty error}">
		   <div class="alert alert-error">
             <button type="button" class="close" data-dismiss="alert">×</button>
 			 <strong>Error:</strong>
 			 	${error}
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
          	  	<div class="accordion" id="accordion2">
  					<div class="accordion-group">
    					<div class="accordion-heading">
      						<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
       						 Build SOQL Query
      						</a>
    					</div>
    			<div id="collapseOne" class="accordion-body collapse">
      				<div class="accordion-inner">
      				<form action="POST">
      				<table>
      				
      			 	<c:forEach items="${sobjectFieldNamesSOQL}" var="fieldNames" varStatus="status">
      			 	<c:choose>
      			 	 <c:when test="${status.first}">
      			 	 <tr><td><input type="checkbox" name="${fieldNames}" id="${fieldNames}">${fieldNames}</td>
      			 	 </c:when>
      			 	 <c:when test="${status.last}">
      			 	 </tr>
      			 	 </c:when>
                     <c:when test="${(status.count % 5) == 0}">
                     	<td>
      			 				<input type="checkbox" name="${fieldNames}" id="${fieldNames}">${fieldNames}
      			 		</td>
                     </tr>
                     </c:when>
                     <c:when test="${(status.count % 5) == 1}">
                     <tr>
                     	<td>
      			 				<input type="checkbox" name="${fieldNames}" id="${fieldNames}">${fieldNames}
      			 		</td>
                     </c:when>
                    <c:otherwise>
                     		<td>
      			 				<input type="checkbox" name="${fieldNames}" id="${fieldNames}">${fieldNames}
      			 			</td>
                    	 </c:otherwise>
                     </c:choose>
  
      			 	</c:forEach>
      				
      			 </table>
      			 <a href="/login/sobject/query/${currentSObject}/soqlbuilder" class="btn btn-primary btn-mini">Build Query</a>
      			 </form>
        		</div>
      			</div>
   			 </div>
  			</div>
          	
          	<form method="POST" action=""> <textarea name="soqlquery" id="soqlquery">${sobjectQuery}</textarea><br />
          	  <button class="btn btn-primary" type="submit">Run</button></form>
          	  	</c:if>
          	  	</div>
          	  	</div>
          	  	<div class="tab-content">
          	  	<div id="/login/sobject/view/${currentSObject}" class="tab-pane active">
          	  	 <c:if test="${!empty sobjectRecords}">
          	  	 	<ul class="nav nav-pills line_sep">
          	  	 	<a href="/login/sobject/query/download/${currentSObject}" class="btn btn-primary btn-mini pull-left">Download</a>
          	  	 	</ul>
          	  	 </c:if>
          	 	<c:if  test="${!empty sobjectFieldNames}">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                    <th>Delete</th>
                    <th>Edit</th>
                    <c:forEach items="${sobjectFieldNames}" var="fieldNames" varStatus="innerColumn">
                    <c:choose>
                     <c:when test="${innerColumn.first}"></c:when>
                    	<c:otherwise>
                     		 <th>${fieldNames}</th>
                    	 </c:otherwise>
                     </c:choose>
         
                    </c:forEach>
                    <c:if test="${!empty sobjectRecords}">
                   	 	<c:forEach items="${sobjectRecords}" var="sobjectRecords" varStatus="outer">
                   	 	<tr>
                   	 		 <c:forEach items="${sobjectRecords}" var="sobjectRecord" varStatus="inner">
                   	 		 <c:choose>
                   	 		 <c:when test="${inner.first}">
                   
                   	 		 </c:when>
                   	 		 <c:when test="${inner.index == 1}">
                   	 		 	<td><form action="/login/sobject/delete/${currentSObject}/${sobjectRecord.value}" method="post"><input type="submit" class="btn btn-danger btn-mini" value="Delete"/></form></td>
                   	 		 	<td><form action="/login/sobject/edit/${currentSObject}/${sobjectRecord.value}" method="post"><input type="submit" class="btn btn-danger btn-mini" value="Edit"/></form></td>
                   	 		 	<td>
                    				${sobjectRecord.value}
                    			</td>
                   	 		 </c:when>
                   	 		 <c:otherwise>
                   	 		 	<td>
                    				${sobjectRecord.value}
                    			</td>
                   	 		 </c:otherwise>
                   	 		 </c:choose>
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
  					<c:forEach items="${pagination}" var="pagination" >
  						<li><a href="${pagination.value}">${pagination.key}</a></li>
  					</c:forEach>
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
                			<input type="text" name="${requiredSobjectFieldNames.key}" id="${requiredSobjectFieldNames.key}" required />
                		</div>
                	</c:forEach>
                    </div> 
                    <div class="control-group">
          	 			<c:forEach items="${optionalSobjectFieldNames}" var="optionalSobjectFieldNames">
                		<label class="control-label">${optionalSobjectFieldNames.key}</label>
                		<div class="controls">
                			<input type="text" name="${optionalSobjectFieldNames.key}" id="${optionalSobjectFieldNames.key}" />
                		</div>
                	</c:forEach>
                	</div>
                	 
                	<button class="btn" type="submit">Save ${currentSObject}</button>
           		 </form>
           		 </c:if>
          	 	</div>
          	</div>
          	<div class="tab-content">
          	  	<div id="/login/sobject/edit/${currentSObject}/${sobjectRecord}" class="tab-pane active">
          	 	  <c:if  test="${!empty requiredEditSobjectFieldNames}">
          	 		<form method="post" action="/login/sobject/save/${currentSObject}/${sobjectRecord}" class="form-horizontal">
          	 		<div class="control-group">
          	 		<c:forEach items="${requiredEditSobjectFieldNames}" var="requiredEditSobjectFieldNames">
                		<label class="control-label">${requiredEditSobjectFieldNames.key}</label>
                		<div class="controls">
                			<input type="text" name="${requiredEditSobjectFieldNames.key}" id="${requiredEditSobjectFieldNames.key}" value="${requiredEditSobjectFieldNames.value}">
                		</div>
                	</c:forEach>
                    </div> 
                   
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
  