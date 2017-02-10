<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Adriabe Manager</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/jquery-ui.css" rel="stylesheet">
    <link href="css/jquery-ui.theme.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin-2.css" rel="stylesheet">
    <!-- Pick a color plugin CSS-->
    <link rel="stylesheet" href="css/plugins/pick-a-color-1.2.3.min.css">
    <link rel="stylesheet" href="css/plugins/bootstrap-datetimepicker.min.css">


    <!-- Proyect styles -->
    <link href="css/kendo-styles.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script>
        // Load the Visualization API and the piechart package.
        google.load('visualization', '1.0', {'packages': ['corechart']});

    </script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css" media="print">
        @page {
            size: auto;   /* auto is the initial value */
            margin: 0mm;  /* this affects the margin in the printer settings */
        }
    </style>
</head>

<body>

<div id="wrapper">

<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
<div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="index.html">Adriabe Manager</a>

    <div class="sidebar-search">
        <div class="input-group custom-search-form" id="search-box">
            <input type="text" class="form-control" id="search-autocomplete" placeholder="Buscar...">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                <i class="fa fa-search"></i>
                            </button>
                        </span>
            <!-- /input-group -->
        </div>
    </div>
</div>
<!-- /.navbar-header -->
<ul class="nav navbar-top-links navbar-right">
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <i class="fa fa-tasks fa-fw"></i> <i class="fa fa-caret-down"></i>
        </a>
        <ul class="dropdown-menu dropdown-tasks">
            <li><a href="#">
                <div>
                    <span class="text-muted small">Funcionalidad no disponible</span>
                </div>
            </a>
            </li>
        </ul>
        <!-- /.dropdown-tasks -->
    </li>
    <!-- /.dropdown -->
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <i class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
        </a>
        <ul class="dropdown-menu dropdown-alerts">
            <li><a href="#">
                <div>
                    <span class="text-muted small">Funcionalidad no disponible</span>
                </div>
            </a>
            </li>
        </ul>
        <!-- /.dropdown-alerts -->
    </li>
    <!-- /.dropdown -->
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
        </a>
        <ul class="dropdown-menu dropdown-user">
            <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
            </li>
            <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
            </li>
            <li class="divider"></li>
            <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
            </li>
        </ul>
        <!-- /.dropdown-user -->
    </li>
    <!-- /.dropdown -->
</ul>
<!-- /.navbar-top-links -->

<div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">
            <li>
                <a href="home.html"><i class="fa fa-home fa-fw"></i>Inicio</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-user fa-fw"></i>Clientes<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="new-client.html">Agregar Cliente...</a>

                    </li>
                    <li>
                        <a href="list-clients.html">Listar</a>
                    </li>
                    <li>
                        <a href="show-account-client.html">Listar cuenta corriente</a>
                    </li>
                    <li>
                        <a href="show-movement-client.html">Listar cuenta movimientos</a>
                    </li>
                    <li>
                        <a href="new-payment-client.html">Cobros</a>
                    </li>
                </ul>
                <!-- /.nav-second-level -->
            </li>
            <li>
                <a href="#"><i class="fa fa-truck fa-fw"></i>Proveedores<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="new-supplier.html">Agregar Proveedor...</a>

                    </li>
                    <li>
                        <a href="list-suppliers.html">Listar Proveedores</a>
                    </li>
                    <li>
                        <a href="show-account-supplier.html">Listar cuenta corriente</a>
                    </li>
                    <li>
                        <a href="show-movement-supplier.html">Listar cuenta movimientos</a>
                    </li>
                    <li>
                        <a href="new-payment-supplier.html">Nueva orden de Pago</a>
                    </li>
                </ul>
                <!-- /.nav-second-level -->
            </li>
            <li>
                <a href="forms.html"><i class="fa fa-edit fa-fw"></i>Pedidos<span class="fa arrow"></span></a></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="new-order.html">Nuevo Pedido</a>
                    </li>
                    <li>
                        <a href="list-orders.html">Listar Pedidos</a>
                    </li>
                    <li>
                        <a href="list-delivery-orders.html">Listar Entregas</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="cashier.html"><i class="fa fa-money fa-fw"></i>Caja</a>
            </li>
            <li>
                <a href="show-reports.html"><i class="fa fa-table fa-fw"></i>Reportes</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-file fa-fw"></i>Facturación<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="billing.html">Facturar...</a>
                    </li>
                    <li>
                        <a href="list-bills.html">Listar Facturas</a>
                    </li>
                </ul>
                <!-- /.nav-second-level -->
            </li>
            <li>
                <a href="#"><i class="fa fa-cogs fa-fw"></i>Producción<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="tejeduria.html">Tejeduría</a>
                    </li>
                    <li>
                        <a href="tintoreria.html">Tintorería</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#"><i class="fa fa-barcode fa-fw"></i>Productos<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="#">Tejidos<span class="fa arrow"></span></a>
                        <ul class="nav nav-third-level">
                            <li>
                                <a href="new-fabric.html">Agregar Tejido</a>
                            </li>
                            <li>
                                <a href="list-fabrics.html">Listar Tejidos</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">Colores<span class="fa arrow"></span></a>
                        <ul class="nav nav-third-level">
                            <li>
                                <a href="new-color.html">Agregar Color</a>
                            </li>
                            <li>
                                <a href="list-colors.html">Listar Colores</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">Rayados <span class="fa arrow"></span></a>
                        <ul class="nav nav-third-level">
                            <li>
                                <a href="new-stripe.html">Agregar Rayado</a>
                            </li>
                            <li>
                                <a href="list-stripes.html">Listar Rayados</a>
                            </li>
                        </ul>
                        <!-- /.nav-third-level -->
                    </li>
                    <li>
                        <a href="#">Productos<span class="fa arrow"></span></a>
                        <ul class="nav nav-third-level">
                            <li>
                                <a href="new-product.html">Agregar Producto</a>
                            </li>
                            <li>
                                <a href="list-products.html">Listar Existencias</a>
                            </li>
                            <li>
                                <a href="list-prices.html">Lista de Precios</a>
                            </li>
                            <li>
                                <a href="new-list-price.html">Nueva Lista de Precios</a>
                            </li>
                        </ul>
                        <!-- /.nav-third-level -->
                    </li>
                </ul>
                <!-- /.nav-second-level -->
            </li>
            <li>
                <a href="list-users.html"><i class="fa fa-edit fa-fw"></i>Usuarios<span class="fa arrow"></span></a></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="new-user.html">Nuevo Usuario</a>
                    </li>
                    <li>
                        <a href="list-users.html">Listar Usuarios</a>
                    </li>
                    <li>
                        <a href="assign-permission.html">Asignar Permisos</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#"><i class="fa fa-list fa-fw"></i>Logs<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="list-logs">Listar logs</a>
                    </li>
                </ul>
                <!-- /.nav-second-level -->
            </li>
            <li>
                <a href="settings.html"><i class="fa fa-wrench fa-fw"></i>Configuraciones</a>
            </li>
        </ul>
    </div>
    <!-- /.sidebar-collapse -->
</div>
<!-- /.navbar-static-side -->
</nav>

<!-- Page Content -->
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">${page.title}</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row header-->
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2">
            <div id="success-notice" class="alert alert-success " style="display: none;">
                <button type="button" class="closeAlert close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <strong>Muy Bien! <i class="fa fa-smile-o"></i> </strong><label id="success-label">La operación se
                realizó con éxito.</label>
            </div>
            <div id="multiple-error-notice" class="alert alert-danger " style="display: none;">
                <button type="button" class="closeAlert close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <strong style="margin-right: 10px; ">Errores! <i class="fa fa-frown-o"></i></strong><label
                    id="multiple-error-label">Se encontraron
                los siguientes errores:</label>
                <ol id="error-list">
                    <c:if test="${not empty page.errorListDetailList}">
                        <c:forEach var="error" items="${page.errorListDetailList}" varStatus="index">
                            <li class="error-item">${error}</li>
                        </c:forEach>
                    </c:if>
                </ol>
            </div>
        </div>
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <tiles:insertAttribute name="page"/>
        </div>
    </div>
    <input type="hidden" id="moduleName" value="${page.moduleName}"/>
    <input type="hidden" id="optionMenu" value="${page.optionMenu}"/>
    <!-- /.row -->
</div>
<!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

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

<!-- Custom Page Controller -->
<script type="text/javascript" language="javascript" src="js/controllers/pageController.js" charset="UTF-8"></script>

<tiles:insertAttribute name="page-controller"/>

<script type="text/javascript">
    $(document).ready(
            function () {
                if (typeof pageController != 'undefined') {
                    pageController.initialize();
                    pageController.checkSessionClose();
                } else {
                    alert("Error de comunicación con el Servidor.\nPresione 'Aceptar' para salir.");
                    window.location.href = "logout.html";
                }

            });

</script>
</body>

</html>

