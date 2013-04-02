<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>CloudSole Data Management</title>

  	<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  	 <!-- Bootstrap framework -->
    <link href="/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="/resources/bootstrap/css/docs.css" rel="stylesheet">
    <link href="/resources/bootstrap/css/prettify.css" rel="stylesheet">
    <style type="text/css" media="screen">
    textarea{ 
  		width: 800px; 
 		 min-width:200px; 
 		 max-height:800px; 

  		height:500px; 
  		min-height:200px;  
  		max-height:500px;
	}
    </style>
    
</head>
	<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="/login/">CloudSole</a>
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">
              Logged in as ${userName}
            </p>
            <ul class="nav">
              <li class="active"><a href="/login/">Home</a></li>
              <li><a href="/login/about">About</a></li>
              <li><a href="/login/contact">Contact</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
 
  <header class="jumbotron subhead" id="overview">
  <div class="container">
    <h1>Welcome to CloudSole</h1>
    <p class="lead">Salesforce Data Management Tool</p>
  </div>
</header>
<body data-spy="scroll" data-target=".bs-docs-sidebar">
    