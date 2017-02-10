<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Adriabe</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div class="container">
    <div class="row">
        <c:if test="${not empty param.login_error}">
            <div class="alert alert-danger alert-dismissable" style="margin-top:30px;">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                Error al autenticar al usuario. Por favor revise el Usuario y Password.
            </div>
        </c:if>
        <c:if test="${not empty param.forgot_password}">
            <div class="alert alert-success alert-dismissable" style="margin-top:30px;">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                Recibirá un correo con las instrucciones para cambiar su contraseña.
            </div>
        </c:if>

        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Ingresar</h3>
                </div>
                <div class="panel-body">
                    <form name="login" id="login" style="padding: 2px;" method="post" role="form" action="<c:url value="/loginProcess" />">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="Nombre" name="j_username" id="j_username"
                                       type="name" autofocus <c:if test="${not empty param.login_error}"> value="${session.getAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY)}"</c:if> />

                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Password" name="j_password" id="j_password"  type="password" value="">
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <a href="#" class="btn btn-lg btn-success btn-block" id="loginBtn">Ingresar</a>
                            <a href="forgot.html">¿Olvidó su contraseña?</a>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>



<!-- jQuery Version 1.11.0 -->
<script type="text/javascript" language="javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" language="javascript" src="js/jquery-ui.js"></script>

<!-- Bootstrap Core JavaScript -->
<script type="text/javascript" language="javascript" src="js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script type="text/javascript" language="javascript" src="js/plugins/metisMenu/metisMenu.min.js"></script>

<script type="text/javascript" language="javascript" src="js/plugins/dataTables/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript" src="js/plugins/dataTables/dataTables.fixedColumns.js"></script>
<script type="text/javascript" language="javascript" src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script type="text/javascript" language="javascript" src="js/plugins/tinycolor-0.9.15.min.js"></script>
<script type="text/javascript" language="javascript" src="js/plugins/pick-a-color-1.2.3.min.js"></script>
<script type="text/javascript" language="javascript" src="js/plugins/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" language="javascript" src="js/plugins/locales/bootstrap-datetimepicker.es.js" charset="UTF-8"></script>


<!-- Custom Theme JavaScript -->
<script src="js/sb-admin-2.js"></script>

<script src="js/controllers/pageController.js"></script>
<script src="js/controllers/admin/adminPageController.js"></script>
<script type="text/javascript" language="javascript" src="js/plugins/tinycolor-0.9.15.min.js"></script>
<script type="text/javascript" language="javascript" src="js/plugins/pick-a-color-1.2.3.min.js"></script>

<!-- end loginBox -->
<script type="text/javascript" >

    $(document).ready(
        function() {
            $('#j_password').bind('keydown',
                    function(e) {
                        if (e.keyCode == 13) {
                            $('#login').submit();
                        }
                    });


            $('#loginBtn').click(function() {
                $('#login').submit();
            });

            var pageController = new AdminPageController();
            pageController.initialize();
        });
</script>
</body>

</html>


