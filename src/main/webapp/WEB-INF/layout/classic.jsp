<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<script type="text/javascript" 
		src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>



<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title><tiles:getAsString name="title" /></title>
</head>
<body role="document">
<%@ include file="taglib.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>

<tilesx:useAttribute name="current"/>


    <nav class="navbar navbar-default navbar-static-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">LMPM</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="${current=='index' ? 'active':''}"><a href='<spring:url value="/" />'>Home</a></li>
            <security:authorize access="hasRole('ROLE_ADMIN')">
            	<li class="${current=='users' ? 'active':''}"><a href='<spring:url value="/users.html" />'>Users</a></li>
            </security:authorize>
            <security:authorize access="! isAuthenticated()">
            	<li class="${current=='register' ? 'active':''}"><a href='<spring:url value="/register.html" />'>Register</a></li>
           	</security:authorize>
            <%-- <security:authorize access="hasRole('ROLE_TEACHER')">
	            <li class="${current=='upload' ? 'active':''}"><a href='<spring:url value="/upload.html" />'>Upload</a></li>
            </security:authorize> --%>
            <security:authorize access="isAuthenticated()">
	           	<li class="${current=='account' ? 'active':''}"><a href='<spring:url value="/account.html" />'>Account</a></li>
	        </security:authorize>
            <security:authorize access="isAuthenticated()">
	           	<li class="${current=='allsub' ? 'active':''}"><a href='<spring:url value="/allsubject.html" />'>All Subject</a></li>
	        </security:authorize>
            <!-- <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Drop down <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <li><a href="#">Separated link</a></li>
                <li><a href="#">One more separated link</a></li>
              </ul>
            </li> -->
           </ul>
           
          <ul class="nav navbar-nav navbar-right">
          	
          	<!-- <li>
          	<form class="navbar-form " role="form">
	            <div class="form-group">
	              <input type="text" placeholder="Search" class="form-control">
	            </div>
	            <button type="submit" class="btn btn-primary">Search</button>
	          </form>
          	</li> -->
            <security:authorize access="! isAuthenticated()">
            	<li class="${current=='login' ? 'active':''}"><a href='<spring:url value="/login.html" />'>Login</a></li>
            </security:authorize>
            <security:authorize access="isAuthenticated()">
            	<li><a href='<spring:url value="/logout" />'>Logout</a></li>
            </security:authorize>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
<div class="container theme-showcase" role="main">
<tiles:insertAttribute name="body"/>
</div>
<div align="center">
<tiles:insertAttribute name="footer"/>
</div>
</body>
</html>