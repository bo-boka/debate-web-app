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

        <title>Deft Debate - Home</title>
    </head>
    <body>
        <%@include file="headerFragment.jsp" %>

        <div class="container-fluid">
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
                    
                    <select name="statusChoice" id="status-choice">
                        <option value="all">All</option>
                        <option value="intro">Challenge</option>
                        <option value="live">Live</option>
                        <option value="voting">Vote</option>
                        <option value="fin">Completed</option>
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
        <script src="${pageContext.request.contextPath}/js/homeJS.js"></script>
        
    </body>
</html>
