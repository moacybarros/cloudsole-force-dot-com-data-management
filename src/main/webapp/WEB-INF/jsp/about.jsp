<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
        <div class="span3">
        <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">Administration</li>
              <li><a href="#">VisualForce Builder</a></li>
              <li><a href="#">SOQL</a></li>
              <li><a href="#">Metadata Export</a></li>
              <li><a href="#">REST</a></li>
              <li class="nav-header">Testing</li>
              <li><a href="#">Create Test</a></li>
              <li><a href="#">Run Scheduled Test</a></li>
              <li class="nav-header">Monitoring</li>
              <li><a href="#">Records Created</a></li>
              <li><a href="#">Debugger</a></li>
              <li><a href="#">Set Trigger</a></li>
              <li class="nav-header">Data</li>
              <li><a href="#">Export To CSV</a></li>
              <li><a href="#">Import to CSV</a></li>
              <li><a href="#">Create Batch Job</a></li>
              <li class="nav-header">Connect</li>
              <li><a href="#">MySQL</a></li>
              <li><a href="#">RabbitMQ</a></li>
              <li><a href="#">StreamingAPI</a></li>
              <li><a href="#">Box</a></li>
              <li><a href="#">Dropbox</a></li>
              <li class="nav-header">Extra</li>
              <li><a href="#">Social</a></li>
              <li><a href="#">Maps</a></li>
              <li><a href="#">Mass Emails</a></li>
              <li><a href="#">Chatter Feed Update</a></li>
            </ul>
          </div>
        
        </div>
        <div class="span9">
         <div class="hero-unit">
                <h1>Author: ${Author}</h1>
                <h1>Email:  ${Email}</h1>
            </div>
         </div>
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