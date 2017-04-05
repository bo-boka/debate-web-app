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
        <link href="https://fonts.googleapis.com/css?family=Orbitron" rel="stylesheet">
        <link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel = "stylesheet">
        <title>deft debate | search</title>
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
                <div class="col-lg-3">
                    <div class="heading">Categories</div>
                    <div class="margin">
                        <c:forEach items="${categories}" var="category">
                            <div id="side-div">
                                <a href="${pageContext.request.contextPath}/categories/${category}">${category}</a><br>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                
                <div class="col-lg-6">
                    <div class="heading">Debates</div>
                    <c:if test="${badInput}">
                        <h1 style="color: red;">No results found. Please choose a search option and try again.</h1><br><br>
                    </c:if>
                    <c:if test="${!badInput}">
                        <c:forEach items="${debates}" var="deb">
                            <div class="deb-div">
                                <h4><a href="${pageContext.request.contextPath}/debate/${deb.id}">${deb.resolution}</a></h4>
                                <hr>
                                <div><strong>Affirmative:</strong> ${deb.affirmativeUser}</div>
                                <div><strong>Status:</strong> ${deb.status}</div>
                                <div align="right">${deb.date}</div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
                
                <div class="col-lg-3">
                    <div class="heading">Featured Users</div>
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
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/homeJS.js"></script>
        <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    </body>
</html>
