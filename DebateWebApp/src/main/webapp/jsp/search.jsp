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
        
        <title>deft debate | search</title>
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
        </div>
        <div class="container-fluid main">
            <div class="row">
                <div class="col-lg-3">
                    <div class="heading">Categories</div>
                    
                    <c:forEach items="${categories}" var="category">
                        <div id="cat-div">
                            <a href="${pageContext.request.contextPath}/categories/${category}">${category}</a><br>
                        </div>
                    </c:forEach>

                </div>
                <div class="col-lg-6">
                    <div class="heading">Debates</div>
                    <br>Dropdown<br><br>
                    <c:if test="${badInput}">
                    <h1>No results found. Please choose a search option and try again.</h1><br><br>
                    </c:if>
                    <c:if test="${!badInput}">
                        <c:forEach items="${debates}" var="deb">
                            
                            <a href="${pageContext.request.contextPath}/debate/${deb.id}">${deb.resolution}</a><br>
                            ${deb.affirmativeUser} <br>
                            ${deb.date} <br><br>
                        
                        </c:forEach>
                    </c:if>
                </div>
                <div class="col-lg-3">
                    <div class="heading">Featured Users</div>
                    <c:forEach items="${users}" var="user">
                        <div id="cat-div">
                            <a href="${pageContext.request.contextPath}/profile/${user.username}">${user.username}</a><br>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
