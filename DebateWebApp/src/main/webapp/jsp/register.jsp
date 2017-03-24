<%-- 
    Document   : register
    Created on : Mar 24, 2017, 3:14:18 AM
    Author     : Sarah
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/debateCSS.css" rel="stylesheet">

        <title>Deft Debate - Login</title>
    </head>
    <body>
        <%@include file="headerFragment.jsp" %>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6">
                    <h2>Register</h2>
                </div>
                <hr>
                <div class="col-md-6">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="add-first-name" class="col-md-4 control-label">First Name:</label>
                            <div class="col-md-8">
                                <input id="add-first-name" name="firstName" type="text" class="form-control" placeholder="first name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-last-name" class="col-md-4 control-label">Last Name:</label>
                            <div class="col-md-8">
                                <input id="add-last-name" name="lastName" type="text" class="form-control" placeholder="last name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-username" class="col-md-4 control-label">Username:</label>
                            <div class="col-md-8">
                                <input id="add-username" name="username" type="text" class="form-control" placeholder="username"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-password" class="col-md-4 control-label">Password:</label>
                            <div class="col-md-8">
                                <input id="add-password" name="password" type="password" class="form-control" placeholder="password"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-email" class="col-md-4 control-label">Email:</label>
                            <div class="col-md-8">
                                <input id="add-email" name="email" type="text" class="form-control" placeholder="Email"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <button type="submit" id="user-register-button" class="btn btn-default">Register</button>
                            </div>
                        </div>
                    </form>
                    <!--<div id="validationErrors" class="alert alert-danger" style="display:none"></div>-->
                </div>    
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        
    </body>
</html>
