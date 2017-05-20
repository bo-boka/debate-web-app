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
        <title>deft debate | home</title>
        <link href="https://fonts.googleapis.com/css?family=Orbitron" rel="stylesheet">
        <link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel = "stylesheet">
        <script LANGUAGE="JavaScript">
            function getCookie(c_name) {
                var c_value = document.cookie;
                var c_start = c_value.indexOf(" " + c_name + "=");
                if (c_start == -1) {
                    c_start = c_value.indexOf(c_name + "=");
                }
                if (c_start == -1) {
                    c_value = null;
                } else {
                    c_start = c_value.indexOf("=", c_start) + 1;
                    var c_end = c_value.indexOf(";", c_start);
                    if (c_end == -1) {
                        c_end = c_value.length;
                    }
                    c_value = unescape(c_value.substring(c_start, c_end));
                }
                return c_value;
            }
            var visit=GetCookie("cookie");
            if (visit==null){
                alert("Welcome to Mode:Test of Deft Debate! \n\
                        Some functions are disabled for functionality previewing purposes. \n\
                        Moderators have '1_' before their username. \n\
                        All other precreated acconts have a '2' before their name.\n\
                        All passwords to precreated user accounts are 'password'.\n\
                        Moderator functionality:\n\
                            \t 1_debatinNotHatin\n\
                            \t 1_SmoothDeb\n\
                        ");
                var expire=new Date();
                expire=new Date(expire.getTime()+7776000000);
                document.cookie="cookie=here; expires="+expire;
            }
        </script>
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
        </div>
        <div class="container-fluid main">
            <div class="row">
                <div style='padding-left: 15px;'>
                    <div>Mode:TEST</div>
                    <div>To preview site, please login as a moderator(1_ before name) or a user(2 before name).</div>
                    <div>All passwords are 'password'.</div>
                    <div>Or register as a new user. Change your status to moderator by logging in as mod.</div>
                </div>
                <div class="col-lg-3">
                    <div class="heading">categories</div>
                    <div class="margin">
                        <c:forEach items="${categories}" var="category">
                            <div id="side-div">
                                <a href="${pageContext.request.contextPath}/categories/${category}">${category}</a><br>
                            </div>
                        </c:forEach>
                    </div>

                </div>
                <div class="col-lg-6">
                    <div class="heading">debates</div>
                    
                    <select name="statusChoice" id="status-choice" class="ddbar" style=" border: 1px solid #33001a;">
                        <option value="all">all</option>
                        <option value="intro">challenge</option>
                        <option value="live">live</option>
                        <option value="voting">vote</option>
                        <option value="fin">completed</option>
                    </select><br>
                    <table class="table table-hover">
                        <tr>
                            <th width="50%">Resolution</th>
                            <th>User</th>
                            <th>Date</th>
                        </tr>
                        <tbody id="home-rows"></tbody>
                    </table>
                </div>
                <div class="col-lg-3">
                    <div class="heading">users</div>
                    <div class="margin">
                        <c:forEach items="${users}" var="user">
                            <div id="side-div">
                                <a href="${pageContext.request.contextPath}/profile/${user.username}">${user.username}</a><br>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="footerFragment.jsp" %>
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/homeJS.js"></script>
        <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    </body>
</html>
