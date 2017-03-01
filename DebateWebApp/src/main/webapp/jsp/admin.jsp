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
        
        <title>Deft Debate - Admin</title>
    </head>
    <body>
        <%@include file="headerFragment.jsp" %>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-3">
                    <div class="heading">Categories</div>
                    <div id="cat-divs"></div>
                </div>
                <div class="col-lg-6">
                    <div class="heading">Debates</div>
                    <br>Dropdown<br><br>
                    <table class="table table-hover">
                        <tr>
                            <th width="44%">Resolution</th>
                            <th width="14%">User</th>
                            <th width="14%">Date</th>
                            <th width="14%">Edit</th>
                            <th width="14%">Delete</th>
                        </tr>
                        <tbody id="adminRows"></tbody>
                    </table>
                </div>
                <div class="col-lg-3">
                    <div class="heading">Featured Users</div>
                    <div id="user-divs"></div>
                </div>
            </div>
        </div>
       
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/adminJS.js"></script>
        
    </body>
</html>
