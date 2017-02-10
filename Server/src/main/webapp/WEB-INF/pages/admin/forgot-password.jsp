<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Recupero de password</h3>
                </div>
                <div class="panel-body">
                    <form role="form" action="forgot-password-process.json" method="POST" id="form">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="Nombre" name="username" id="username"
                                       type="user" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Email" name="email" id="email" type="Email"
                                       value="">
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <a href="#" id="forgot-btn" class="btn btn-lg btn-success btn-block">Recuperar Password</a>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery Version 1.11.0 -->
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
<script type="text/javascript" language="javascript" src="js/plugins/bootstrap-datetimepicker.js"
        charset="UTF-8"></script>
<script type="text/javascript" language="javascript" src="js/plugins/locales/bootstrap-datetimepicker.es.js"
        charset="UTF-8"></script>

<!-- Custom Theme JavaScript -->
<script src="js/sb-admin-2.js"></script>

<!-- Kendo JavaScript Common libraries-->
<!-- <script src="js/stringUtils.js"></script> -->
<script type="text/javascript" language="javascript" src="js/validationLibrary.js" charset="UTF-8"></script>
<script type="text/javascript" language="javascript" src="js/stringLibrary.js" charset="UTF-8"></script>

<script src="js/controllers/admin/adminPageController.js"></script>

<script type="text/javascript">

    $(document).ready(function () {
        var pageController = new AdminPageController();
        pageController.initializePageForgotPage();
    });
</script>
</body>

</html>
