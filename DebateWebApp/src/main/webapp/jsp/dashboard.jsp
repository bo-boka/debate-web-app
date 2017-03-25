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
        <title>Deft Debate</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/debateCSS.css" rel="stylesheet">
        
        <script src='//cdn.tinymce.com/4/tinymce.min.js'></script>
        <script type="text/javascript">
            tinymce.init({
                selector: '#addDebateContent',
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
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="headerFragment.jsp" %>
            
            <sec:authentication var="user" property="principal.username" /> 
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#edit-user-modal" data-user-name="${user}">Edit Account</button>
            <%@include file="editUserModalFrag.jsp" %>
            
            <h3>My Debates</h3>
            <table class="table table-hover">
                <tr>
                    <th width="50%">Resolution</th>
                    <th>User</th>
                    <th>Date</th>
                </tr>
                <tbody id="dashRows"></tbody>
            </table>
            <br><br>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <h3>Unpublished Debates</h3>
            <table class="table table-hover">
                <tr>
                    <th width="50%">Resolution</th>
                    <th>User</th>
                    <th>Date</th>
                </tr>
                <tbody id="unpubRows"></tbody>
            </table>
            <div class="row">
                <div class="col-md-6">
                    <h2>Create Moderator</h2>
                </div>
                <hr>
                <div class="col-md-6">
                    <form class="form-horizontal" role="form">
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
                                <button type="submit" id="add-moderator-button" class="btn btn-default">Add Moderator</button>
                            </div>
                        </div>
                    </form>
                    <!--<div id="validationErrors" class="alert alert-danger" style="display:none"></div>-->
                </div>    
            </div>
            </sec:authorize>
            <form class="form-horizontal" id="debateForm">
                <h4>Add Debate</h4>
                <hr>
                <div class="form-group">
                    <label for="addResolution" class="col-sm-1 control-label">Resolution</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="addResolution" placeholder="Resolution">
                    </div>
                </div>
                <div class="form-group">
                    <label for="addDebateContent" class="weak col-sm-1 control-label">Content</label>
                    <div class="col-sm-11">
                        <textarea name="addDebateContent" id="addDebateContent"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="addDebateCategory" class="col-sm-1 control-label">Category</label>
                    <div class="col-sm-4">
                        <select name="add-debate-category" class="form-control" id="addCategory">
                            <c:forEach items="${categories}" var="category">
                                <option value="${category}">${category}</option>
                            </c:forEach>
                        </select>
                    </div>
                    
                </div>
                <div class="form-group">
<!--                    <div id="validationErrors" class="alert alert-danger" style="display:none">
                        <p>Make sure all fields are filled out!</p>
                    </div>   -->
                    <div class="pull-right addButton">
                        <button type="submit" class="btn btn-lg btn-default" id="submitDebate">Submit</button>
                    </div> 
                </div>
            </form>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/dashboardJS.js"></script>
        
    </body>
</html>
