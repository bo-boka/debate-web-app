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
        <title>deft debate | login</title>
        <link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel = "stylesheet">
        <script>
            $('#search-option').change(function() {
                var option = $('#search-option').val();
                if (option === 'date'){
                    $('#search-info').datepicker({
                        showAnim: "slide",
                        dateFormat: 'yy-mm-dd'
                    });
                } else {
                    $("#search-info").datepicker('destroy');
                }
            });
        </script>
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
            <div id="login">
                <h1>Please log in</h1>
                <hr/>
                <c:if test="${param.login_error == 1}">
                    <h3 style="color: red;">Wrong Id or Password. Try Again.</h3>
                </c:if>
                <div class="row">
                    <div class="col-sm-12">
                    <form method="POST" action="j_spring_security_check">
                        <p>Username: </p> <input class="form-control" name="j_username" type="text" /><br>
                        <p>Password: </p> <input class="form-control" name="j_password" type="password"/><br>
                        <button class="btn btn-default pull-right" type="submit" >Sign In</button><br>
                    </form>
                    </div>
                </div>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    </body>
</html>
