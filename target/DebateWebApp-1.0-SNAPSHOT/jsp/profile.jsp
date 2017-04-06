<%-- 
    Document   : profile
    Created on : Mar 18, 2017, 1:45:06 AM
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
        <title>deft | ${oneUser.username}</title>
        <link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel = "stylesheet">
        
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
        </div> <!--header end-->
        
        <div class="container-fluid main">
            
            <div align="center" style="padding: 40px; font-size: 40px;">${oneUser.username}</div>
<!--            <center>-->
            <div class="row" style="padding: 20px;">
                
                <div class="col-sm-4">
                    <div align="center" style="font-size: 20px; padding: 20px; border: 1px solid black">
                        <strong>First name: </strong> ${oneUser.firstName}<br>
                        <strong>Last name: </strong>${oneUser.lastName}<br>
                        <strong>Wins: </strong>${oneUser.wins}<br>
                        <strong>Losses: </strong>${oneUser.losses}<br>
                        <strong>Ties: </strong>${oneUser.ties}<br>
                    </div>
                </div>
                <div class="col-sm-8">
                    <table class="table table-hover">
                        <tr>
                            <th width="50%">Resolution</th>
                            <th>User</th>
                            <th>Date</th>
                        </tr>
                        <tbody id="profileRows">
                            <c:forEach items="${debs}" var="deb">
                            <tr>
                                <td>${deb.resolution}</td>
                                <td>${deb.affirmativeUser}</td>
                                <td>${deb.date}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!--</center>-->
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <div class="heading">Moderator Settings</div>
                <!--disable passed with username because controller method is also used in user dash that passes username-->
                <a href="editRole?userid=${oneUser.id}"><button type="button" class="btn btn-lg btn-primary" id="change-to-moderator">Add Moderator</button></a>
                <a href="disableUser?username=${oneUser.username}"><button type="button" class="btn btn-lg btn-warning" id="disable-user">Disable Account</button></a>
                <a href="deleteUser?userid=${oneUser.id}"><button type="button" class="btn btn-lg btn-danger" id="delete-user">Delete Account</button></a>
                <span style="color: red;">NOTE: </span> You can only delete nonactive users.
            </sec:authorize>
            <c:if test="${badInput}">
                <div class="alert alert-danger">You cannot delete a user that has engaged in debates as it affects other debator stats.</div>
            </c:if>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/homeJS.js"></script>
        <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    </body>
</html>