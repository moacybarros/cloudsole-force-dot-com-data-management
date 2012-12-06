<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>RocketForce</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="RocketForce.com ~ Salesforce Portal">
    <meta name="author" content="Thys Michels ~ http://thysmichels.com">
    
    <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
    <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="http://twitter.github.com/bootstrap/assets/css/docs.css" rel="stylesheet">
    <link href="http://twitter.github.com/bootstrap/assets/js/google-code-prettify/prettify.css" rel="stylesheet">
    
    <link rel="shortcut icon" href="/assets/public/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="/assets/public/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="/assets/public/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="/assets/public/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="/assets/public/ico/apple-touch-icon-57-precomposed.png">
</head>

<body data-spy="scroll" data-target=".bs-docs-sidebar">
<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
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
              <li><a href="./login/about/">About</a></li>
              <li><a href="./login/contact/">Contact</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
    
 <header class="jumbotron subhead" id="overview">
  <div class="container">
    <h1>Welcome ${loggedinUser.getFirstName()} to RocketForce</h1>
    <p class="lead">Manage basic objects in Salesforce</p>
  </div>
</header>

<div class="container">
	<div class="row">
        <div class="span3 bs-docs-sidebar">
        	<ul class="nav nav-list bs-docs-sidenav">
              <li><a href="#manage-users"><i class="icon-chevron-right"></i>Manage Users</a></li>
              <li><a href="#manage-account"><i class="icon-chevron-right"></i>Manage Account</a></li>
              <li><a href="#manage-contact"><i class="icon-chevron-right"></i>Manage Contact</a></li>
              <li><a href="#manage-opportunity"><i class="icon-chevron-right"></i>Manage Opportunity</a></li>
              <li><a href="#manage-campaign"><i class="icon-chevron-right"></i>Manage Campaign</a></li>
              <li><a href="#manage-case"><i class="icon-chevron-right"></i>Manage Case</a></li>
              <li><a href="#manage-chatter"><i class="icon-chevron-right"></i>Manage Chatter</a></li>
              <li><a href="#manage-lead"><i class="icon-chevron-right"></i>Manage Lead</a></li>
              <li><a href="#manage-task"><i class="icon-chevron-right"></i>Manage Task</a></li>
            </ul>
         </div>
      <div class="span9">
         <section id="manage-users">
          <div class="page-header">
            <h1>1. Manage Users</h1>
          </div>
          <p class="lead">Before downloading, be sure to have a code editor (we recommend <a href="http://sublimetext.com/2">Sublime Text 2</a>) and some working knowledge of HTML and CSS. We won't walk through the source files here, but they are available for download. We'll focus on getting started with the compiled Bootstrap files.</p>

          <div class="row-fluid">
            <div class="span6">
              <h2>Download compiled</h2>
              <p><strong>Fastest way to get started:</strong> get the compiled and minified versions of our CSS, JS, and images. No docs or original source files.</p>
              <p><a class="btn btn-large btn-primary" href="//assets/public/public/bootstrap.zip" onclick="_gaq.push(['_trackEvent', 'Getting started', 'Download', 'Download compiled']);">Download Bootstrap</a></p>
            </div>
            <div class="span6">
              <h2>Download source</h2>
              <p>Get the original files for all CSS and JavaScript, along with a local copy of the docs by downloading the latest version directly from GitHub.</p>
              <p><a class="btn btn-large" href="https://github.com/twitter/bootstrap/zipball/master" onclick="_gaq.push(['_trackEvent', 'Getting started', 'Download', 'Download source']);">Download Bootstrap source</a></p>
            </div>
          </div>
        </section>
        <section id="#manage-account">
          <div class="page-header">
            <h1>2. Manage Account</h1>
          </div>
          <p class="lead">Before downloading, be sure to have a code editor (we recommend <a href="http://sublimetext.com/2">Sublime Text 2</a>) and some working knowledge of HTML and CSS. We won't walk through the source files here, but they are available for download. We'll focus on getting started with the compiled Bootstrap files.</p>

          <div class="row-fluid">
            <div class="span6">
              <h2>Download compiled</h2>
              <p><strong>Fastest way to get started:</strong> get the compiled and minified versions of our CSS, JS, and images. No docs or original source files.</p>
              <p><a class="btn btn-large btn-primary" href="//assets/public/public/bootstrap.zip" onclick="_gaq.push(['_trackEvent', 'Getting started', 'Download', 'Download compiled']);">Download Bootstrap</a></p>
            </div>
            <div class="span6">
              <h2>Download source</h2>
              <p>Get the original files for all CSS and JavaScript, along with a local copy of the docs by downloading the latest version directly from GitHub.</p>
              <p><a class="btn btn-large" href="https://github.com/twitter/bootstrap/zipball/master" onclick="_gaq.push(['_trackEvent', 'Getting started', 'Download', 'Download source']);">Download Bootstrap source</a></p>
            </div>
          </div>
        </section>
        <section id="#manage-account">
          <div class="page-header">
            <h1>3. Manage Contact</h1>
          </div>
          <p class="lead">Before downloading, be sure to have a code editor (we recommend <a href="http://sublimetext.com/2">Sublime Text 2</a>) and some working knowledge of HTML and CSS. We won't walk through the source files here, but they are available for download. We'll focus on getting started with the compiled Bootstrap files.</p>

          <div class="row-fluid">
            <div class="span6">
                <c:if  test="${!empty peopleList}">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>&nbsp;</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${peopleList}" var="person">
                        <tr>
                            <td>${person.lastName}, ${person.firstName}</td>
                            <td><form action="delete/${person.id}" method="post"><input type="submit" class="btn btn-danger btn-mini" value="Delete"/></form></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
          </div>
         </div>
       </section>
     </div>
   </div>
</div>

	<footer class="footer">
      <div class="container">
        <p class="pull-right"><a href="#">Back to top</a></p>
        <p>Designed and built with all the love in the world by <a href="http://twitter.com/mdo" target="_blank">@mdo</a> and <a href="http://twitter.com/fat" target="_blank">@fat</a>.</p>
        <p>Code licensed under <a href="http://www.apache.org/licenses/LICENSE-2.0" target="_blank">Apache License v2.0</a>, documentation under <a href="http://creativecommons.org/licenses/by/3.0/">CC BY 3.0</a>.</p>
        <p><a href="http://glyphicons.com">Glyphicons Free</a> licensed under <a href="http://creativecommons.org/licenses/by/3.0/">CC BY 3.0</a>.</p>
        <ul class="footer-links">
          <li><a href="http://blog.getbootstrap.com">Blog</a></li>
          <li class="muted">&middot;</li>
          <li><a href="https://github.com/twitter/bootstrap/issues?state=open">Issues</a></li>
          <li class="muted">&middot;</li>
          <li><a href="https://github.com/twitter/bootstrap/wiki">Roadmap and changelog</a></li>
        </ul>
      </div>
    </footer>
	
    <script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>
    <script src="http://twitter.github.com/bootstrap/assets/js/jquery.js"></script>
    <script src="http://twitter.github.com/bootstrap/assets/js/google-code-prettify/prettify.js"></script>
    <script src="http://twitter.github.com/bootstrap/assets/js/bootstrap-transition.js"></script>
    <script src="http://twitter.github.com/bootstrap/assets/js/bootstrap-alert.js"></script>
    <script src="http://twitter.github.com/bootstrap/assets/js/bootstrap-modal.js"></script>
    <script src="http://twitter.github.com/bootstrap/assets/js/bootstrap-dropdown.js"></script>
    <script src="http://twitter.github.com/bootstrap/assets/js/bootstrap-scrollspy.js"></script>
    <script src="http://twitter.github.com/bootstrap/assets/js/bootstrap-tab.js"></script>
    <script src="http://twitter.github.com/bootstrap/assets/js/bootstrap-tooltip.js"></script>
    <script src="http://twitter.github.com/bootstrap/assets/js/bootstrap-popover.js"></script>
    <script src="http://twitter.github.com/bootstrap/assets/js/bootstrap-button.js"></script>
    <script src="http://twitter.github.com/bootstrap/assets/js/bootstrap-collapse.js"></script>
    <script src="http://twitter.github.com/bootstrap/assets/js/bootstrap-carousel.js"></script>
    <script src="http://twitter.github.com/bootstrap/assets/js/bootstrap-typeahead.js"></script>
    <script src="http://twitter.github.com/bootstrap/assets/js/bootstrap-affix.js"></script>
    <script src="http://twitter.github.com/bootstrap/assets/js/application.js"></script>

    
</body>
</html>