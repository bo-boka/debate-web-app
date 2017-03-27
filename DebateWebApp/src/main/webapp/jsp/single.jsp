<%-- 
    Document   : single
    Created on : Feb 25, 2017, 11:09:37 PM
    Author     : Sarah
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/debateCSS.css" rel="stylesheet">
        
        <script src='//cdn.tinymce.com/4/tinymce.min.js'></script>
        <script type="text/javascript">
            tinymce.init({
                selector: '#add-challenge-content',
                min_width: 400,
                min_height: 300,
                plugins: [
                    'advlist autolink autosave charmap hr link lists print preview ',
                    ' wordcount visualblocks visualchars image imagetools',
                    'table contextmenu emoticons template',
                    'paste save searchreplace textcolor'
                ],
                contextmenu: "link image",
                imagetools_toolbar: "rotateleft rotateright | flipv fliph | editimage imageoptions",
                toolbar: 'insertfile undo redo | styleselect | forecolor backcolor bold italic underline \n\
                | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent \n\
                | link charmap image emoticons | preview save',
                images_upload_base_path: '${pageContext.request.contextPath}/img'
            });
            
            tinymce.init({
                selector: '#add-rebuttal-content',
                min_width: 400,
                min_height: 300,
                plugins: [
                    'advlist autolink autosave charmap hr link lists print preview ',
                    ' wordcount visualblocks visualchars image imagetools',
                    'table contextmenu emoticons template',
                    'paste save searchreplace textcolor'
                ],
                contextmenu: "link image",
                imagetools_toolbar: "rotateleft rotateright | flipv fliph | editimage imageoptions",
                toolbar: 'insertfile undo redo | styleselect | forecolor backcolor bold italic underline \n\
                | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent \n\
                | link charmap image emoticons | preview save',
                images_upload_base_path: '${pageContext.request.contextPath}/img'
            });
        </script>
        <title>Deft Debate</title>
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
            <center>
                <div class="row" style="margin: 50px;">
                    <div class="col-sm-2">
                        <div>${oneDebate.affirmativeUser}</div>
                    </div>
                    <div class="col-sm-8">
                        Resolved: <div><h3>${oneDebate.resolution}</h3></div>
                        Status: <div>${oneDebate.status}</div>
                        <div class="row">
                            <div class="col-sm-6">
                                
                                Pro Votes: <div>${oneDebate.proVotes}</div>
                            </div>
                            <div class="col-sm-6">
                                Con Votes: <div>${oneDebate.conVotes}</div>
                            </div>
                        </div>
                        Category: <div>${oneDebate.category}</div>
                        <div>${oneDebate.date}</div>
                    </div>
                    <div class="col-sm-2">
                        <div>${oneDebate.negativeUser}</div>
                    </div>
                </div>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#edit-modal" data-debate-id="${oneDebate.id}">Edit</button>
                    <button type="button" class="btn btn-danger" ><a href="${pageContext.request.contextPath}/debate/del/${oneDebate.id}">Delete</a></button>
                </sec:authorize>
                <!-- Modal -->
                <%@include file="editDebateModalFrag.jsp" %>
                
                <div style="border: solid 2px; padding: 5px; margin: 20px 150px;">${oneDebate.content}</div>
                
                <sec:authorize access="isFullyAuthenticated()"> 
                    <c:if test="${oneDebate.status == 'voting'}">
                        <div class="row" id="vote-buttons">
                            <div class="col-sm-6">
                                <button type="button" class="btn btn-success" id="pro-vote">Pro</button>
                            </div>
                            <div class="col-sm-6">
                                <button type="button" class="btn btn-danger" id="con-vote">Con</button>
                            </div>
                        </div>
                    </c:if>
                    <sec:authentication var="user" property="principal.username" />
                    <c:if test="${empty oneDebate.rebuttals && user != oneDebate.affirmativeUser}">
                        <form class="form-horizontal" id="challengeForm">
                            <div class="form-group">
                                <div>
                                    <textarea name="addChallengeContent" id="add-challenge-content"></textarea>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-lg btn-default addButton" id="challenge">Challenge</button>
                        </form>
                    </c:if>
                </sec:authorize>
                
                <c:forEach items="${oneDebate.rebuttals}" var="rebute">
                    <br>
                    ${rebute.id}
                    ${rebute.position}
                    ${rebute.content}
                    ${rebute.user}               
                    ${rebute.date}
                    <br>
                </c:forEach>
                    
                <sec:authorize access="isFullyAuthenticated()">
                    <sec:authentication var="user" property="principal.username" /> 
                    <c:if test="${not empty oneDebate.rebuttals && ((user == oneDebate.negativeUser && oneDebate.rebuttals.size() % 2 == 0) || (user == oneDebate.affirmativeUser && oneDebate.rebuttals.size() % 2 != 0))}">
                        <form class="form-horizontal" id="challengeForm">
                            <div class="form-group">
                                <div>
                                    <textarea name="addRebuttalContent" id="add-rebuttal-content"></textarea>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-lg btn-default addButton" id="rebute">Reply</button>
                        </form>
                    </c:if>
                </sec:authorize>
            </center>
        </div>
        
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/singleDebJS.js"></script>       
        
    </body>
</html>
