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
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/deft-logo3.png">
        <link href="https://fonts.googleapis.com/css?family=Orbitron" rel="stylesheet">
        <link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel = "stylesheet">
        <script>
            $('#search-option').change(function() {
                var option = $('#search-option').val();
                if (option === 'date'){
                    $('#search-info').datepicker({
                        showAnim: "slide",
                        dateFormat: 'yy-mm-dd'
                    });
                } else {
                    $("#search-info").datepicker('destroy');
                }
            });
        </script>
        <title>deft debate | register</title>
    </head>
    <body>
        <%@include file="headerFragment.jsp" %>
        <div>
            <header>
                <div class="container">
                    <h1 style="font-size: 42px; font-family: Orbitron, Verdana;">deft debate</h1>
                </div>
            </header>
            <div id="head1"></div>
            <div id="head2"></div>
        </div><!--end of head-->
        <div class="container-fluid main">
            <div class="row">
                <div class="col-md-8">
                    <form class="form-horizontal" role="form">
<!--                        <input type='hidden' id='dat' />-->
                        <center>
                        <h2>Register</h2>
                        </center>
                        <hr>
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
                            <label for="add-email" class="col-md-4 control-label">Email:</label>
                            <div class="col-md-8">
                                <input id="add-email" name="email" type="text" class="form-control" placeholder="Email"/>
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
                            <div class="col-md-offset-4 col-md-8">
                                <c:if test="${duplicate}">
                                    <p class="alert alert-danger">I'm sorry that username is already taken.</p>
                                </c:if>
                                <div id="validationRegisterUserErrors" class="alert alert-danger" style="display:none"></div>
                                <button type="submit" id="user-register-button" class="btn btn-primary">Register</button>
                            </div>
                        </div>
                    </form>
                        
                </div>   
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/dashboardJS.js"></script>
        <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    </body>
</html>
