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
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/deft-logo2.png">
        <title>deft | ${oneUser.username}</title>
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
    </head>
    <body>
        <%@include file="headerFragment.jsp" %>
        <div>
            <header>
                <div class="container">
                    <h1 style="font-size: 55px;">deft debate</h1>
                </div>
            </header>
            <div id="head1"></div>
            <div id="head2"></div>
        </div> <!--header end-->
        
        <div class="container-fluid main">
            
            <div class="heading">${oneUser.username}</div>
            <center>
            <div>
            First name: ${oneUser.firstName}<br>
            Last name: ${oneUser.lastName}<br>
            Wins: ${oneUser.wins}<br>
            Losses: ${oneUser.losses}<br>
            Ties: ${oneUser.ties}<br>
            </div>
            </center>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/homeJS.js"></script>
        <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    </body>
</html>