<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="utf-8">
    <title>RocketForce</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="RocketForce.com ~ Salesforce Portal">
    <meta name="author" content="Thys Michels ~ http://thysmichels.com">
    
    <link href="/assets/public/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }
    </style>
    <link rel="shortcut icon" href="/assets/public/ico/favicon.ico">
     <link rel="apple-touch-icon-precomposed" sizes="144x144" href="/assets/public/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="/assets/public/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="/assets/public/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="/assets/public/ico/apple-touch-icon-57-precomposed.png">
    
    <link href="/assets/public/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="/heroku.css" rel="stylesheet">
</head>

<body>
<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="#">RocketForce</a>
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">
              Logged in as ${loggedinUser.getFirstName()}
            </p>
            <ul class="nav">
              <li class="active"><a href="#">Home</a></li>
              <li><a href="/login/about/">About</a></li>
              <li><a href="/login/contact/">Contact</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span3 bs-docs-sidebar">
        	<ul class="nav nav-list bs-docs-sidenav">
              <li><a href="#"><i class="icon-chevron-right"></i>Manage Users</a></li>
              <li><a href="#"><i class="icon-chevron-right"></i>Manage Account</a></li>
              <li><a href="#"><i class="icon-chevron-right"></i>Manage Contact</a></li>
              <li><a href="#"><i class="icon-chevron-right"></i>Manage Opportunity</a></li>
              <li><a href="#"><i class="icon-chevron-right"></i>Manage Campaign</a></li>
              <li><a href="#"><i class="icon-chevron-right"></i>Manage Case</a></li>
              <li><a href="#"><i class="icon-chevron-right"></i>Manage Chatter</a></li>
              <li><a href="#"><i class="icon-chevron-right"></i>Manage Lead</a></li>
              <li><a href="#"><i class="icon-chevron-right"></i>Manage Task</a></li>
              <li><a href="#"><i class="icon-chevron-right"></i>Export To CSV</a></li>
              
            </ul>
          </div>
        <div class="span9">
		<form:form method="post" action="send" commandName="contact" class="form-horizontal">
			<fieldset>
				<legend>Contact Us:</legend>
				<div class="control-group">
                	<form:label class="control-label" path="email">Email</form:label>
                	<div class="controls">
                		<form:input path="email"/>
                	</div>
                </div>
                <div class="control-group">
                	<form:label class="control-label" path="subject">Subject</form:label>
                	<div class="controls">
                		<form:input path="subject"/>
                	</div>
                </div>
                <div class="control-group">
                	<form:label class="control-label" path="body">Body</form:label>
                	<div class="controls">
                		<form:textarea path="body" rows="5" cols="30"/>
                	</div>
                </div>
                <input type="submit" value="Send Email" class="btn"/>
             </fieldset>   
    	</form:form>
    </div>
</div>


   <script src="/assets/public/js/jquery.js"></script>
    <script src="/assets/public/js/bootstrap-transition.js"></script>
    <script src="/assets/public/js/bootstrap-alert.js"></script>
    <script src="/assets/public/js/bootstrap-modal.js"></script>
    <script src="/assets/public/js/bootstrap-dropdown.js"></script>
    <script src="/assets/public/js/bootstrap-scrollspy.js"></script>
    <script src="/assets/public/js/bootstrap-tab.js"></script>
    <script src="/assets/public/js/bootstrap-tooltip.js"></script>
    <script src="/assets/public/js/bootstrap-popover.js"></script>
    <script src="/assets/public/js/bootstrap-button.js"></script>
    <script src="/assets/public/js/bootstrap-collapse.js"></script>
    <script src="/assets/public/js/bootstrap-carousel.js"></script>
    <script src="/assets/public/js/bootstrap-typeahead.js"></script>

</body>
</html>