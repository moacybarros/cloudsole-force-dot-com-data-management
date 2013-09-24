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
   <!-- Styles and icons -->
    
    <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap-responsive.min.css">
    <link rel="stylesheet" href="/resources/bootstrap/css/prettify.css" />
    <link rel="stylesheet" href="/resources/bootstrap/css/css.css">
    <style type="text/css" media="screen">
    textarea{
    	 width: 99%;
  		 height: 300px;
	}
    </style>
    
</head>

<body data-spy="scroll" data-target=".bs-docs-sidebar">
<div id="wrapper">
	<div id="wrapper-inner">
	<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="/login/">CloudSole Data</a>
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">
              Logged in as ${userName}
            </p>
            <ul class="nav">
              <li class="active"><a href="/login/">Home</a></li>
              <li><a href="/login/batch/job">Batch</a></li>
              <li><a href="/login/about">About</a></li>
              <li><a href="/login/contact">Contact</a></li>
              
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
<div id="content">

    