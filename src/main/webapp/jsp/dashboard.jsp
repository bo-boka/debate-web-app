<%-- 
    Document   : dashboard
    Created on : Feb 25, 2017, 12:46:34 PM
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
        <title>deft | <sec:authentication property="principal.username" /> </title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/debateCSS.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/deft-logo3.png">
        <link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel = "stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Orbitron" rel="stylesheet">
        <script src='//cdn.tinymce.com/4/tinymce.min.js'></script>
    </head>
    <body>
        <%@include file="headerFragment.jsp" %>
        <div>
            <header></header>
            <div id="head1"></div>
            <div id="head2"></div>
        </div><!--end dash head-->
        <div class="container-fluid main">
             
            <div class="row" id="dash-main">
                <div align="right">
                    <sec:authentication var="user" property="principal.username" />
                    <button type="button" class="btn" data-toggle="modal" data-target="#edit-user-modal" data-user-name="${user}" style="border: 1px solid black"><span class="glyphicon glyphicon-wrench" aria-hidden="true"></span></button>
                </div>
                <div class="col-sm-6">
                    <form class="form-horizontal" id="debateForm">
                        <center><div class="heading">Add Debate</div></center>
                        <div class="form-group">
                            <label for="addResolution" class="col-sm-2 control-label">Resolution</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="addResolution" placeholder="Resolution">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-11">
                                <textarea name="addDebateContent" id="addDebateContent"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="addDebateCategory" class="col-sm-2 control-label">Category</label>
                            <div class="col-sm-4">
                                <select name="add-debate-category" class="form-control" id="addCategory">
                                    <c:forEach items="${categories}" var="category">
                                        <option value="${category}">${category}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div id="validationErrors" class="alert alert-danger" style="display:none">
                                <p>Make sure all fields are filled out!</p>
                            </div>   
                            <div class="col-sm-3 pull-right addButton">
                                <button type="submit" class="btn btn-lg btn-primary" id="submitDebate">Submit</button>
                            </div> 
                        </div>
                    </form>
                    <!--mod stuff-->
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <form class="form-horizontal" role="form">
                            <center><br><br><br>
                            <div class="heading">Create Moderator</div>
                            </center>
                            <div class="form-group">
                                <label for="add-mod-first-name" class="col-md-4 control-label">First Name:</label>
                                <div class="col-md-8">
                                    <input id="add-mod-first-name" name="modFirstName" type="text" class="form-control" placeholder="first name"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-mod-last-name" class="col-md-4 control-label">Last Name:</label>
                                <div class="col-md-8">
                                    <input id="add-mod-last-name" name="modLastName" type="text" class="form-control" placeholder="last name"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-mod-email" class="col-md-4 control-label">Email:</label>
                                <div class="col-md-8">
                                    <input id="add-mod-email" name="modEmail" type="text" class="form-control" placeholder="email"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-mod-username" class="col-md-4 control-label">Username:</label>
                                <div class="col-md-8">
                                    <input id="add-mod-username" name="modUsername" type="text" class="form-control" placeholder="username"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-mod-password" class="col-md-4 control-label">Password:</label>
                                <div class="col-md-8">
                                    <input id="add-mod-password" name="modPassword" type="password" class="form-control" placeholder="password"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <div id="validationErrorsMod" class="alert alert-danger" style="display:none"></div>
                                    <button type="submit" id="add-moderator-button" class="btn btn-primary">Add Moderator</button>
                                </div>
                            </div>
                        </form>
                        
                    </sec:authorize>
                </div>
                <div class="col-sm-6">
                    <div class="heading">My Debates</div>
                    <table class="table table-hover">
                        <tr>
                            <th width="50%">Resolution</th>
                            <th>User</th>
                            <th>Date</th>
                        </tr>
                        <tbody id="dashRows"></tbody>
                    </table>
                    <!--mod stuff-->
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <div class="heading">Unpublished Debates</div>
                        <table class="table table-hover">
                            <tr>
                                <th width="50%">Resolution</th>
                                <th>User</th>
                                <th>Date</th>
                            </tr>
                            <tbody id="unpubRows"></tbody>
                        </table>
                    </sec:authorize>
                </div>
            </div>
            
            <%@include file="editUserModalFrag.jsp" %>
        </div>
        <%@include file="footerFragment.jsp" %>
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/dashboardJS.js"></script>
        <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    </body>
</html>
