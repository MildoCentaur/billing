<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name=".defaultTemplate">
<tiles:putAttribute name="page">
<div class="row">
    <div class="col-lg-3 col-md-6">
        <div class="panel panel-green">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-xs-3">
                        <i class="fa fa-paper-plane fa-5x"></i>
                    </div>
                    <div class="col-xs-9 text-right">
                        <div class="huge" id="orders-delivered">${ordersDelivered}</div>
                        <div>Pedidos Entregados</div>
                    </div>
                </div>
            </div>
            <a href="list-orders.html">
                <div class="panel-footer">
                    <span class="pull-left">Ver Detalles</span>
                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>

                    <div class="clearfix"></div>
                </div>
            </a>
        </div>
    </div>
    <div class="col-lg-3 col-md-6">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-xs-3">
                        <i class="fa fa-tasks fa-5x"></i>
                    </div>
                    <div class="col-xs-9 text-right">
                        <div class="huge" id="orders-in-production">${ordersInProduction}</div>
                        <div>En Producción</div>
                    </div>
                </div>
            </div>
            <a href="list-orders.html">
                <div class="panel-footer">
                    <span class="pull-left">Ver Detalles</span>
                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>

                    <div class="clearfix"></div>
                </div>
            </a>
        </div>
    </div>
    <div class="col-lg-3 col-md-6">
        <div class="panel panel-yellow">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-xs-3">
                        <i class="fa fa-shopping-cart fa-5x"></i>
                    </div>
                    <div class="col-xs-9 text-right">
                        <div class="huge" id="new-sales">${newSales}</div>
                        <div>Nuevas Ventas</div>
                    </div>
                </div>
            </div>
            <a href="list-orders.html">
                <div class="panel-footer">
                    <span class="pull-left">Ver Detalles</span>
                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>

                    <div class="clearfix"></div>
                </div>
            </a>
        </div>
    </div>
    <div class="col-lg-3 col-md-6">
        <div class="panel panel-red">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-xs-3">
                        <i class="fa fa-support fa-5x"></i>
                    </div>
                    <div class="col-xs-9 text-right">
                        <div class="huge" id="missing-items">${missingProducts}</div>
                        <div>Productos Faltantes</div>
                    </div>
                </div>
            </div>
            <a href="list-products.html">
                <div class="panel-footer">
                    <span class="pull-left">View Details</span>
                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>

                    <div class="clearfix"></div>
                </div>
            </a>
        </div>
    </div>
</div>
<div class="row">
<div class="col-lg-8">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <i class="fa fa-bar-chart-o fa-fw"></i> Estado de Pedidos
            <div class="pull-right">
                <a class="btn btn-block btn-success  btn-xs" id="btn-new-order"><i class="fa fa-plus"
                                                                                   style="margin-right:5px;"></i>Nuevo</a>
            </div>
        </div>
        <!-- /.panel-heading -->
        <div class="panel-body">
            <div id="orders_table_div">
                <table class="table table-striped row-border table-hover dataTable" id="list-order-summary">
                    <thead>
                    <tr>
                        <th>Cliente</th>
                        <th>Estado</th>
                        <th>Pedidos</th>
                        <th>Preparados</th>
                        <th>Entregados</th>
                        <th>Importe</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${not empty orders}">
                        <c:forEach var="order" items="${orders}" varStatus="loop">
                            <tr data-order-id="${order.id}" data-order-status="${order.status.label}"
                                data-order-client="${order.client.name}"
                                data-order-requested="${order.totalOrderedRolls}"
                                data-order-prepared="${order.totalPreparedRolls}"
                                data-order-delivered="${order.totalDeliveredRolls}"
                                data-order-amount="${order.totalAmount}">
                                <td>${order.client.name}</td>
                                <td>${order.status.label}</td>
                                <td>${order.totalOrderedRolls}</td>
                                <td>${order.totalPreparedRolls}</td>
                                <td>${order.totalDeliveredRolls}</td>
                                <td><label style="float:right;" class="currency">${item.totalAmount}</label></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>

            <div id="orders_chart_div">

            </div>

        </div>
        <!-- /.panel-body -->
    </div>
    <!-- /.panel -->
    <div class="panel panel-primary">
        <div class="panel-heading">
            <i class="fa fa-bar-chart-o fa-fw"></i> Resumen de productos
        </div>
        <!-- /.panel-heading -->
        <div class="panel-body">
            <div id="products_table_div">
                <table class="table table-striped row-border table-hover dataTable" id="list-product-summary">
                    <thead>
                    <tr>
                        <th>Artículo</th>
                        <th>Pedidos</th>
                        <th>Tejeduría</th>
                        <th>Tintorería</th>
                        <th>Stock</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${not empty productsOrdered}">
                        <c:forEach var="product" items="${productsOrdered}" varStatus="loop">
                            <tr>
                                <td>${product.productName}</td>
                                <td>10</td>
                                <td>0</td>
                                <td>0</td>
                                <td>${product.stock}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>

                </table>
            </div>
            <div id="products_chart_div">

            </div>
            <div id="colors_table_div">
                <table class="table table-striped row-border table-hover dataTable" id="list-color-summary">
                    <thead>
                    <tr>
                        <th>Colores</th>
                        <th>Pedidos</th>
                        <th>Producción</th>
                        <th>Maquina(s)</th>
                        <th>Stock</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td colspan="5">
                            <i class="icon-spinner icon-4 icon-spin"></i>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>


        </div>
        <div id="colors_chart_div">

        </div>

        <!-- /.panel-body -->
    </div>
    <!-- /.panel -->
</div>
<!-- /.col-lg-8 -->
<div class="col-lg-4">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <i class="fa fa-bell fa-fw"></i> Panel de notificaciones
            <div class="pull-right">
                <a class="btn btn-block btn-success  btn-xs" data-toggle="modal" data-target="#add-event-modal"
                   id="btn-new-notification">
                    <i class="fa fa-plus" style="margin-right:5px;"></i>Nuevo</a>
            </div>
        </div>
        <!-- Button trigger modal -->
        <!-- Modal -->
        <div class="modal fade" id="add-event-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">Agregar Notificación</h4>
                    </div>
                    <div class="modal-body">
                        <form role="form" id="add-notification-form" action="" method="">
                            <div class="form-group">
                                <label>Título*</label>
                                <input type="text" id="notification-title" name="title" class="form-control"
                                       placeholder="Entrer text"/>

                                <p class="help-block">Título de la notificación.</p>
                            </div>
                            <div class="form-group">
                                <label>Lugar*</label>
                                <input type="text" id="notification-place" name="place" class="form-control"
                                       placeholder="Enter text"/>

                                <p class="help-block">Lugar de la notificación.</p>
                            </div>
                            <div class="form-group">
                                <label>Fecha*</label>

                                <div class="input-group date form_datetime">

                                    <input id="notification-date" name="date" data-date-format="dd/MM/yyyy'T'HH:mm"
                                           class="form-control" size="16" placeholder="" value=""/>
                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                </div>
                                <p class="help-block">Fecha de la notificación</p>
                            </div>
                            <div class="form-group">
                                <label>Descripción*</label>
                                <input type="text" id="notification-detail" name="detail" class="form-control"
                                       placeholder="Enter text"/>

                                <p class="help-block">Descripción de la notificación.</p>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        <button type="button" id="new-event-button" class="btn btn-primary">Agregar Notificación
                        </button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->
        <!-- /.panel-heading -->
        <div class="panel-body">
            <div class="list-group">
                <ul class="chat">
                    <c:if test="${not empty events}">
                        <c:forEach var="event" items="${events}" varStatus="loop">
                            <li class="left clearfix">
                        <span class="pull-left">
                            <div class="stacked-icons">
                                <span class="fa-stack fa-2x">
                                    <i class="fa fa-calendar-o fa-stack-2x"></i>
                                    <strong class="fa-stack-1x calendar-text">${event.day}</strong>
                                </span>
                            </div>
                        </span>

                                <div class="chat-body clearfix">
                                    <div class="header">
                                        <strong class="primary-font">${event.title}</strong>
                                        <small class="pull-right text-muted"><i
                                                class="fa fa-clock-o fa-fw"></i>${event.time}</small>
                                    </div>
                                    <p>${event.detail}</p>
                                    <br/>
                                </div>
                            </li>
                        </c:forEach>
                    </c:if>
                </ul>
            </div>
            <!-- /.list-group -->
        </div>
        <!-- /.panel-body -->
    </div>
    <!-- /.panel -->
    <div class="panel panel-primary">
        <div class="panel-heading">
            <i class="fa fa-sort-amount-desc fa-fw"></i> Tareas Tejeduría
            <div class="pull-right">
                <a class="btn btn-block btn-success  btn-xs" id="btn-new-fabric-order"><i class="fa fa-plus"
                                                                                          style="margin-right:5px;"></i>Nuevo</a>
            </div>
        </div>
        <!-- /.panel-heading -->
        <div class="panel-body">
            <ul class="chat">
                <c:if test="not empty ${taskTejeduria}">
                    <c:forEach var="task" items="${taskTejeduria}" varStatus="taskStatus">
                        <li class="left clearfix">
                        <span class="pull-left">
                            <div class="stacked-icons">
                                <span class="fa-stack fa-2x">
                                    <i class="fa fa-square-o fa-stack-2x"></i>
                                    <strong class="fa-stack-1x char-overlay">${task.machine}</strong>
                                </span>
                            </div>
                        </span>

                            <div class="chat-body clearfix">
                                <div class="header">
                                    <strong class="primary-font">${task.fabric}</strong>
                                </div>
                                <label>Avance:</label>

                                <p>${task.ratio}</p>
                                <label>Encargo:</label>

                                <p>${task.clientName}</p>
                                <br/>
                            </div>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
            <!-- /.list-group -->
        </div>
        <!-- /.panel-body -->
    </div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <i class="fa fa-sort-amount-desc fa-fw"></i>Tareas Tintorería
            <div class="pull-right">
                <a class="btn btn-block btn-success  btn-xs" id="btn-new-color-order"><i class="fa fa-plus"
                                                                                         style="margin-right:5px;"></i>Nuevo</a>
            </div>
        </div>
        <!-- /.panel-heading -->
        <div class="panel-body">
            <ul class="chat">
                <c:if test="not empty ${taskTintoreria}">
                    <c:forEach var="task" items="${taskTintoreria}" varStatus="taskStatus">
                        <li class="left clearfix">
                        <span class="pull-left">
                            <div class="stacked-icons">
                                <span class="fa-stack fa-2x">
                                    <i class="fa fa-square-o fa-stack-2x"></i>
                                    <strong class="fa-stack-1x char-overlay">${task.machine}</strong>
                                </span>
                            </div>
                        </span>

                            <div class="chat-body clearfix">
                                <div class="header">
                                    <strong class="primary-font">${task.color}</strong>
                                </div>
                                <label>Ingreso:</label>

                                <p>${task.date}</p>
                                <label>Encargo:</label>

                                <p>${task.clientName}</p>
                                <br/>
                            </div>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
            <!-- /.list-group -->

        </div>
        <!-- /.panel-body -->
    </div>
    <!-- /.panel -->
</div>
<!-- /.col-lg-4 -->
</div>
<!-- /.row -->
</tiles:putAttribute>
<tiles:putAttribute name="page-controller">
    <script src="js/controllers/home/homePageController.js"></script>
</tiles:putAttribute>

</tiles:insertDefinition>