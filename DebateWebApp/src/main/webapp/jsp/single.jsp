<%-- 
    Document   : single
    Created on : Feb 25, 2017, 11:09:37 PM
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
        <title>Deft Debate</title>
    </head>
    <body>
        <div class="container-fluid">
            <div>${oneDebate.resolution}</div>
            <div>${oneDebate.content}</div>
            
            <c:forEach items="${oneDebate.rebuttals}" var="rebute">
                <br>
                
                ${rebute.id}
                ${rebute.position}
                ${rebute.type}
                ${rebute.content}
                ${rebute.user}               
                ${rebute.date}
                
                <br>
            </c:forEach>
            
        </div>
        
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/homeJS.js"></script>       
    </body>
</html>
