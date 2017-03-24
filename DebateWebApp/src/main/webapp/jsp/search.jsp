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

        <title>Deft Debate - Search</title>
    </head>
    <body>
        <%@include file="headerFragment.jsp" %>

        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-3">
                    <div class="heading">Categories</div>
                    
                    <c:forEach items="${categories}" var="category">
                        <div id="cat-div">"${category}"</div>
                    </c:forEach>

                </div>
                <div class="col-lg-6">
                    <div class="heading">Debates</div>
                    <br>Dropdown<br><br>
<!--                    <table class="table table-hover">
                        <tr>
                            <th width="50%">Resolution</th>
                            <th>User</th>
                            <th>Date</th>
                        </tr>
                        <tbody id="home-rows"></tbody>
                    </table>-->

                    <c:if test="${badInput}">
                    <h1>You done it wrong. Try again.</h1><br><br>
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
                    <div id="user-divs"></div>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <!--<script src="${pageContext.request.contextPath}/js/homeJS.js"></script>-->
        <script src="${pageContext.request.contextPath}/js/searchJS.js"></script>

    </body>
</html>
