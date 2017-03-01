<%-- 
    Document   : dashboard
    Created on : Feb 25, 2017, 12:46:34 PM
    Author     : Sarah
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
            <table class="table table-hover">
                <tr>
                    <th width="50%">Resolution</th>
                    <th>User</th>
                    <th>Date</th>
                </tr>
                <tbody id="dashRows"></tbody>
            </table>
            
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
