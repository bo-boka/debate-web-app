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

        <title>Deft Debate - Profile</title>
    </head>
    <body>
        <%@include file="headerFragment.jsp" %>

        <div class="container-fluid"><br>
            Username: ${oneUser.username}<br>
            First name: ${oneUser.firstName}<br>
            Last name: ${oneUser.lastName}<br>
            Wins: ${oneUser.wins}<br>
            Losses: ${oneUser.losses}<br>
            Ties: ${oneUser.ties}<br>
            
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/homeJS.js"></script>

    </body>
</html>