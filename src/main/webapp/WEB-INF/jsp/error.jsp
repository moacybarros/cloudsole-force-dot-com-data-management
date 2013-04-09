<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp"/>
<jsp:include page="sidemenu.jsp"/>
<jsp:include page="topmenu.jsp" />
<div class="span8">
      <div class="page-header">
            <h1>Oops something went wrong</h1>
        </div>
		<div class="error_box">
			 <p><c:out value="${requestScope['javax.servlet.error.message']}"/></p>

			<a href="javascript:history.back()" class="back_link btn btn-small">Go back</a>
		</div>
	</div>
    </div>
   </div>
 
<jsp:include page="footer.jsp"/>