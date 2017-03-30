<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${pageContext.request.contextPath}/home" style="letter-spacing: 14px;">deft debate</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
        <form action="search" method="POST" class="navbar-form navbar-left" style="padding-right: 100px;">
            <div class="form-group">
                <select name="searchOption" id="search-option" class="ddbar">
                    <option value="---">...choose...</option>
                    <option value="resolution" style="background-color: #d6e9c6">resolution</option>
                    <option value="category" style="background-color: #d6e9c6">category</option>
                    <option value="user" style="background-color: #d6e9c6">username</option>
                    <option value="date" style="background-color: #d6e9c6">date</option>
                </select>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="searchInfo" id="search-info" placeholder="keywords">
            </div>
            <button type="submit" class="btn btn-default" id="search-button">search</button>
        </form>
        <sec:authorize access="!isFullyAuthenticated()">
        <li><a href="${pageContext.request.contextPath}/login">login</a></li>
        <li><a>|</a></li>
        <li><a href="${pageContext.request.contextPath}/signup">sign up</a></li>
        </sec:authorize>
        <sec:authorize access="isFullyAuthenticated()">
        <sec:authentication var="user" property="principal.username" /> 
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${user}<span class="caret"></span></a>
          <ul class="dropdown-menu">                
            <li><a href="${pageContext.request.contextPath}/dashboard">dashboard</a></li>
            <li><a href="${pageContext.request.contextPath}/profile/${user}">profile</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="${pageContext.request.contextPath}/j_spring_security_logout">log out</a></li> 
          </ul>
        </li>
        </sec:authorize>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
        