<%-- 
    Document   : single
    Created on : Feb 25, 2017, 11:09:37 PM
    Author     : Sarah
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/debateCSS.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/deft-logo3.png">
        <link href="https://fonts.googleapis.com/css?family=Orbitron" rel="stylesheet">
        <link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel = "stylesheet">
        <script src='//cdn.tinymce.com/4/tinymce.min.js'></script>
        
        <title>deft | ${oneDebate.resolution}</title>
        
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
        </div> <!--end of head-->
        <div class="container-fluid main">
            <center>
                <div class="row">
                    <div id="deb-main">
                        <div class="col-sm-3">
                            <div class="heading">Affirmative</div>
                            <div class="deb-user" style="background-color: #c2c2a3;">
                                <h5><strong>${oneDebate.affirmativeUser}</strong></h5>
                                <div>
                                    Votes: <div>${oneDebate.proVotes}</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="heading">Resolved</div>
                            <div id="deb-deets">
                                <h3><strong>${oneDebate.resolution}</strong></h3>
                                <div id="deb-status">
                                    <i><u>Status:</u></i>
                                    <div><strong>${message}</strong></div>
                                </div>
                                <sec:authorize access="isFullyAuthenticated()"> 
                                    <c:if test="${oneDebate.status == 'voting'}">
                                        <div class="row" id="vote-buttons">
                                            <div class="col-sm-3">
                                                <button type="button" class="btn btn-success btn-lg" id="pro-vote"><span class="glyphicon glyphicon-hand-left" aria-hidden="true"></span></button>
                                            </div>
                                            <div class="col-sm-6" style="color: red;"><strong>First person to reach 10 votes wins!</strong></div>
                                            <div class="col-sm-3">
                                                <button type="button" class="btn btn-danger btn-lg" id="con-vote"><span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span></button>
                                            </div>
                                        </div>
                                    </c:if>
                                </sec:authorize>
                                <br>            
                                <div><strong>Category: </strong>${oneDebate.category}</div>
                                <div><strong>Date: </strong>${oneDebate.date}</div><br>

                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#edit-modal" data-debate-id="${oneDebate.id}">Edit</button>
                                    <button type="button" class="btn btn-danger"><a href="${pageContext.request.contextPath}/debate/del/${oneDebate.id}" style="color: #fff;">Delete</a></button>
                                </sec:authorize>
                                <br><br>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="heading">Challenger</div>
                            <div class="deb-user" style="background-color: #998066;">
                                <h5><strong>${oneDebate.negativeUser}</strong></h5>
                                <div>
                                    Votes: <div>${oneDebate.conVotes}</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> <!--debate info row close-->
                <div class="deb-reb">
                    ${oneDebate.content}
                    <div><strong>${oneDebate.affirmativeUser}</strong></div>
                </div>
                <sec:authorize access="isFullyAuthenticated()"> 
                    <sec:authentication var="user" property="principal.username" />
                    <c:if test="${empty oneDebate.rebuttals && user != oneDebate.affirmativeUser}">
                        <form class="form-horizontal" id="challengeForm">
                            <div class="form-group">
                                <div>
                                    <textarea name="addChallengeContent" id="add-challenge-content"></textarea>
                                </div>
                            </div>
                            <div id="validationChallengeError" class="alert alert-danger" style="display:none"></div>
                            <button type="submit" class="btn btn-lg btn-warning addButton" id="challenge">Challenge</button>
                        </form>
                    </c:if>
                </sec:authorize>
                
                <c:forEach items="${oneDebate.rebuttals}" var="rebute">
                    <hr>
                    <div class="deb-reb" <c:if test="${!rebute.position}"> style="background-color: #998066;" </c:if>>
                        <sec:authorize access="isFullyAuthenticated()">
                            <sec:authentication var="user" property="principal.username" /> 
                            <c:if test="${rebute.user == user}">
                                <button class="pull-right btn" data-toggle="modal" data-target="#edit-rebuttal-modal" data-rebuttal-id="${rebute.id}"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button>
                            </c:if>
                        </sec:authorize>
                        ${rebute.content}
                        <div>
                            <strong>${rebute.user}</strong><br>
                            ${rebute.date}
                        </div>
                        
                    </div>
                </c:forEach>
                    
                <sec:authorize access="isFullyAuthenticated()">
                    <sec:authentication var="user" property="principal.username" /> 
                    <c:if test="${not empty oneDebate.rebuttals && (fn:length(oneDebate.rebuttals) lt 5) && ((user == oneDebate.negativeUser && oneDebate.rebuttals.size() % 2 == 0) || (user == oneDebate.affirmativeUser && oneDebate.rebuttals.size() % 2 != 0))}">
                        <form class="form-horizontal" id="rebuteForm">
                            <div class="form-group">
                                <div>
                                    <textarea name="addRebuttalContent" id="add-rebuttal-content"></textarea>
                                </div>
                            </div>
                            <div id="validationRebuttalError" class="alert alert-danger" style="display:none"></div>
                            <button type="submit" class="btn btn-lg btn-warning addButton" id="rebute">Reply</button>
                        </form>
                    </c:if>
                </sec:authorize>
            </center>
             
            <%@include file="editDebateModalFrag.jsp" %>
            <%@include file="editRebuttalModal.jsp" %>
        </div>
        <%@include file="footerFragment.jsp" %>
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/singleDebJS.js"></script>       
        <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    </body>
</html>
