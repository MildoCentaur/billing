<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insertDefinition name=".defaultTemplate">
<tiles:putAttribute name="page">
<style type="text/css">
    #list-items-order td:nth-child(6) {
        text-align: center !important;
    }
</style>
<div class="panel panel-primary">
<div class="panel-heading">
    Formulario de alta de ordenes de pedidos
</div>
<div class="panel-body">
<form:form id="addOrderForm" method="POST" action="add/order" commandName="order" role="form">
<div class="row">
    <div class="col-lg-6">
        <div class="form-group">
            <label>Señores</label>

            <div class="form-group input-group" style="margin-bottom: 0px;">
                <form:input path="clientName" class="form-control autocomplete-client" placeholder="Razón Social"/>
                <form:hidden path="clientId" class="client-id"/>
                <input type="hidden" id="client-name"/>
                <span class="input-group-addon"><i class="fa fa-user"></i></span>
            </div>
            <p class="help-block">Razón social, Nombre del contacto</p>
        </div>
    </div>
    <div class="col-lg-5" style="margin-top:40px;">
        <button type="button" id="btn-add-item-top" class="btn btn-primary disabled" data-toggle="modal"
                data-target="#addOrderItemModal" style="float:right;">Agregar Item
        </button>
    </div>
</div>
<br/>
<!-- end row -->
<div class="row">
<div class="col-lg-12">
    <label>
        Listado de items pedidos
    </label>

    <div class="table-responsive">
        <table class="row-border table table-striped table-hover" class="display dataTable" cellspacing="0" width="100%"
               role="grid" id="list-items-order">
            <thead>

            <tr>
                <th rowspan="2">Nº</th>
                <th rowspan="2">Cantidad</th>
                <th rowspan="2">Producto</th>
                <th colspan="2" style="text-align:center;">Accesorios</th>
                <th rowspan="2" style="text-align:center;">
                    <span class='panel-yellow' style="margin-left: 10px;"><a onclick="return false;">
                        <i class='fa fa-pencil-square-o'></i></a>
                    </span>
                    <!-- style="padding:3px 6px;margin-left: 5px;" -->
                    <span class='panel-red' style="margin-left: 24px;"><a onclick="return false;">
                        <i class='fa fa-trash-o'></i></a>
                    </span>
                </th>
            </tr>
            <tr>
                <th>Cuello y Tira</th>
                <th>Puño</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="index" value="${0}"/>
            <c:if test="${not empty order.items}">
                <c:forEach var="item" items="${order.items}" varStatus="loop">
                    <c:set var="index" value="${index + 1}"/>
                    <tr id="row_${item.id}">
                        <td>${index}</td>
                        <td>${item.quantityInteger}</td>
                        <td>${item.product.productName}</td>
                        <td class="center">${item.requestedCuellos}</td>
                        <td class="center">${item.requestedPuno}</td>
                        <td style="text-align:center;">
                            <span class='editItem btn btn-warning btn-outline'>
                                <i class='fa fa-pencil-square-o'></i>
                            </span>
                            <!-- style="padding:3px 6px;margin-left: 5px;" -->
                            <span class='deleteItem btn btn-danger btn-outline'>
                                <i class='fa fa-trash-o'></i>
                            </span>
                            <input type='hidden' value="${item.id}"/>
                        </td>
                    </tr>

                </c:forEach>
            </c:if>
            </tbody>

        </table>

        <c:if test="${not empty order.items}">
            <c:forEach var="item" items="${order.items}" varStatus="loop">
                <form:hidden path="items[${loop.index}].quantity" class="order-item-${item.id} is-main fabric-amount"
                             value="${item.quantity}"/>
                <form:hidden path="items[${loop.index}].product.fabricId"
                             cssClass="order-item-${item.id} is-main fabric-id"/>
                <form:hidden path="items[${loop.index}].product.fabric"
                             cssClass="order-item-${item.id} is-main fabric-name"/>
                <form:hidden path="items[${loop.index}].product.colorId"
                             cssClass="order-item-${item.id} is-main color-id"/>
                <form:hidden path="items[${loop.index}].product.color"
                             cssClass="order-item-${item.id} is-main color-name"/>
                <c:if test="${not empty item.product.stripe}">
                    <form:hidden path="items[${loop.index}].product.stripeId"
                                 cssClass="order-item-${item.id} is-main stripe-id"/>
                    <form:hidden path="items[${loop.index}].product.stripeName"
                                 cssClass="order-item-${item.id} is-main stripe-name"/>
                    <form:hidden path="items[${loop.index}].product.stripeCombinationId"
                                 cssClass="order-item-${item.id} is-main stripe-combination-id"/>
                    <form:hidden path="items[${loop.index}].product.stripeCombinationName"
                                 cssClass="order-item-${item.id} is-main stripe-combination-name"/>

                </c:if>
                <c:if test="${empty item.product.stripe}">
                    <input type="hidden" name="items[${loop.index}].product.stripeId"
                           class="order-item-${item.id} is-main stripe-id" value="0"/>
                    <input type="hidden" name="items[${loop.index}].product.stripe"
                           class="order-item-${item.id} is-main stripe-name"/>
                    <input type="hidden" name="items[${loop.index}].product.stripeCombinationId"
                           class="order-item-${item.id} is-main stripe-combination-id" value="0"/>
                    <input type="hidden" name="items[${loop.index}].product.stripeCombinationName"
                           class="order-item-${item.id} is-main stripe-combination-name"/>
                </c:if>
                <form:hidden path="items[${loop.index}].id" cssClass="order-item-${item.id} is-main"/>


                <c:if test="${not empty item.accesories}">
                    <c:forEach var="accesory" items="${item.accesories}" varStatus="loopAcc">

                        <c:if test="${accesory.product.puno}">
                            <form:hidden path="items[${loop.index}].accesories[${loopAcc.index}].quantity"
                                         class="order-item-${item.id} is-puno fabric-amount"
                                         value="${accesory.quantity}"/>
                            <form:hidden path="items[${loop.index}].accesories[${loopAcc.index}].product.fabricId"
                                         cssClass="order-item-${item.id}  is-puno fabric-id"/>
                            <form:hidden path="items[${loop.index}].accesories[${loopAcc.index}].product.fabric"
                                         cssClass="order-item-${item.id} is-puno fabric-name"/>
                            <form:hidden path="items[${loop.index}].accesories[${loopAcc.index}].product.colorId"
                                         cssClass="order-item-${item.id} is-puno color-id"/>
                            <form:hidden path="items[${loop.index}].accesories[${loopAcc.index}].product.color"
                                         cssClass="order-item-${item.id} is-puno color-name"/>
                            <c:if test="${not empty accesory.product.stripe}">
                                <form:hidden path="items[${loop.index}].accesories[${loopAcc.index}].product.stripeId"
                                             cssClass="order-item-${item.id} is-puno stripe-id"/>
                                <form:hidden path="items[${loop.index}].accesories[${loopAcc.index}].product.stripeName"
                                             cssClass="order-item-${item.id} is-puno stripe-name "/>
                                <form:hidden
                                        path="items[${loop.index}].accesories[${loopAcc.index}].product.stripeCombinationId"
                                        cssClass="order-item-${item.id} is-puno stripe-combination-id"/>
                                <form:hidden
                                        path="items[${loop.index}].accesories[${loopAcc.index}].product.stripeCombinationName"
                                        cssClass="order-item-${item.id} is-puno stripe-combination-name"/>

                            </c:if>
                            <c:if test="${empty accesory.product.stripe}">
                                <input type="hidden"
                                       name="items[${loop.index}].accesories[${loopAcc.index}].product.stripeId"
                                       class="order-item-${item.id} is-puno stripe-id" value="0"/>
                                <input type="hidden"
                                       name="items[${loop.index}].accesories[${loopAcc.index}].product.stripeName"
                                       class="order-item-${item.id} is-puno stripe-name"/>
                                <input type="hidden"
                                       name="items[${loop.index}].accesories[${loopAcc.index}].product.stripeCombinationId"
                                       class="order-item-${item.id} is-puno stripe-combination-id" value="0"/>
                                <input type="hidden"
                                       name="items[${loop.index}].accesories[${loopAcc.index}].product.stripeCombinationName"
                                       class="order-item-${item.id} is-puno stripe-combination-name"/>
                            </c:if>
                            <form:hidden path="items[${loop.index}].accesories[${loopAcc.index}].id"
                                         cssClass="order-item-${item.id} is-puno"/>
                        </c:if>
                        <c:if test="${accesory.product.cuello}">
                            <form:hidden path="items[${loop.index}].accesories[${loopAcc.index}].quantity"
                                         class="order-item-${item.id} is-cuellos fabric-amount"
                                         value="${accesory.quantity}"/>
                            <form:hidden path="items[${loop.index}].accesories[${loopAcc.index}].product.fabricId"
                                         cssClass="order-item-${item.id} is-cuellos fabric-id"/>
                            <form:hidden path="items[${loop.index}].accesories[${loopAcc.index}].product.fabric"
                                         cssClass="order-item-${item.id} is-cuellos  fabric-name"/>
                            <form:hidden path="items[${loop.index}].accesories[${loopAcc.index}].product.colorId"
                                         cssClass="order-item-${item.id} is-cuellos  color-id"/>
                            <form:hidden path="items[${loop.index}].accesories[${loopAcc.index}].product.color"
                                         cssClass="order-item-${item.id} is-cuellos  color-name "/>
                            <c:if test="${not empty accesory.product.stripe}">
                                <form:hidden path="items[${loop.index}].accesories[${loopAcc.index}].product.stripeId"
                                             cssClass="order-item-${item.id} is-cuellos stripe-id"/>
                                <form:hidden path="items[${loop.index}].accesories[${loopAcc.index}].product.stripeName"
                                             cssClass="order-item-${item.id} is-cuellos stripe-name"/>
                                <form:hidden
                                        path="items[${loop.index}].accesories[${loopAcc.index}].product.stripeCombinationId"
                                        cssClass="order-item-${item.id} is-cuellos stripe-combination-id"/>
                                <form:hidden
                                        path="items[${loop.index}].accesories[${loopAcc.index}].product.stripeCombinationName"
                                        cssClass="order-item-${item.id} is-cuellos stripe-combination-name"/>

                            </c:if>
                            <c:if test="${empty accesory.product.stripe}">
                                <input type="hidden"
                                       name="items[${loop.index}].accesories[${loopAcc.index}].product.stripeId"
                                       class="order-item-${item.id} is-cuellos stripe-id" value="0"/>
                                <input type="hidden"
                                       name="items[${loop.index}].accesories[${loopAcc.index}].product.stripeName"
                                       class="order-item-${item.id} is-cuellos stripe-name"/>
                                <input type="hidden"
                                       name="items[${loop.index}].accesories[${loopAcc.index}].product.stripeCombinationId"
                                       class="order-item-${item.id} is-cuellos stripe-combination-id" value="0"/>
                                <input type="hidden"
                                       name="items[${loop.index}].accesories[${loopAcc.index}].product.stripeCombinationName"
                                       class="order-item-${item.id} is-cuellos stripe-combination-name"/>
                            </c:if>
                            <form:hidden path="items[${loop.index}].accesories[${loopAcc.index}].id"
                                         cssClass="order-item-${item.id} is-cuellos "/>
                        </c:if>
                    </c:forEach>
                </c:if>
            </c:forEach>
        </c:if>
        <form:hidden path="id" class="orderId"/>
        <input type="hidden" id="edit-row-number" value="-1"/>
        <input type="hidden" id="edit-item-number" value="-1"/>

    </div>
    <!-- /.table-responsive -->
</div>
<div class="col-lg-12" style=" margin-top: 20px; ">
<button type="button" id="btn-print" class="btn btn-primary disabled" style="float:left;">Imprimir</button>

<button type="reset" id="btn-clear-order" class="btn btn-danger" style="float:right;margin:0 5px;">Cancelar</button>
<button type="button" id="btn-accept" class="btn btn-success disabled" style="float:right;margin:0 5px;">Aceptar
</button>
<button type="button" id="btn-add-item-table" data-toggle="modal" data-target="#addOrderItemModal"
        class="btn btn-primary disabled" style="float:right;">Agregar Item
</button>

<!-- Modal -->
<div class="modal fade" id="addOrderItemModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">


    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Agregar item</h4>
            </div>
            <div class="modal-body">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#main" data-toggle="tab">Tejido Principal</a>
                    </li>
                    <li><a href="#puno" data-toggle="tab">Puño</a>
                    </li>
                    <li><a href="#cuellos-y-tiras" data-toggle="tab">Cuellos y Tiras</a>
                    </li>
                </ul>
                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="main">
                        <input type="hidden" id="orderItemId" value="0"/>
                        <fieldset id="fieldset-main">
                            <h4 style="text-align:center">Ingrese el tejido pedido</h4>

                            <div class="form-group">
                                <label>Tejido*</label>

                                <div class="form-group input-group" style="margin-bottom: 0px;">
                                    <input type="text" id="itemMainFabric" class="form-control" placeholder=""/>
                                    <input type="hidden" id="itemMainFabricId"/>
                                    <input type="hidden" id="item-main-fabric-label"/>
                                    <input type="hidden" id="item-main-fabric-short-label"/>
                                    <span class="input-group-addon"><i class="fa fa-barcode"></i></span>
                                </div>
                                <p class="help-block">Ingrese el tejido del item.</p>
                            </div>
                            <div class="first-column">
                                <div class="form-group">
                                    <label>Cantidad*</label>

                                    <div class="form-group input-group" style="margin-bottom: 0px;">
                                        <input type="text" id="itemMainAmount" class="form-control" placeholder="0"/>
                                        <span class="input-group-addon"><i class="fa fa-sort-numeric-asc"></i></span>
                                    </div>
                                    <p class="help-block">Ingrese la cantidad solicitada.</p>
                                </div>

                                <div class="form-group disabled">
                                    <label>Patrón de Rayado*</label>

                                    <div class="form-group input-group" style="margin-bottom: 0px;">
                                        <input type="text" id="itemMainPattern" class="form-control input-pattern"
                                               placeholder=""/>
                                        <input type="hidden" id="itemMainPatternId"/>
                                        <input type="hidden" id="item-main-pattern-label"/>
                                        <span class="input-group-addon"><i class="fa fa-align-justify"></i></span>
                                    </div>
                                    <p class="help-block">Ingrese el patrón de rayado.</p>
                                </div>
                            </div>
                            <div class="second-column">

                                <div class="form-group">
                                    <label>Color*</label>
                                    <input type="text" id="itemColor" class="form-control" placeholder=""/>
                                    <input type="hidden" id="itemColorId"/>
                                    <input type="hidden" id="item-color-label"/>

                                    <p class="help-block">Ingrese el color del tejido.</p>
                                </div>
                                <div class="form-group disabled">
                                    <label>Combinación*</label>

                                    <div class="form-group input-group" style="margin-bottom: 0px;">
                                        <input type="text" class="input-pattern-detail form-control disabled"
                                               id="itemMainCombination" aria-labelledby="itemMainCombination">
                                        <input type="hidden" id="itemMainCombinationId"/>
                                        <input type="hidden" id="item-main-combination-label"/>
                                        <span class="input-group-addon"><i class="fa fa-bars"></i></span>
                                    </div>
                                    <p class="help-block">Ingrese colores del rayado.</p>
                                </div>
                            </div>
                        </fieldset>

                    </div>
                    <div class="tab-pane fade" id="puno">
                        <fieldset id="fieldset-puno" disabled>
                            <h4 style="text-align:center">Selección de puño</h4>

                            <div class="first-column">
                                <div class="form-group">
                                    <label>Cantidad*</label>

                                    <div class="form-group input-group" style="margin-bottom: 0px;">
                                        <input type="text" id="itemPunoAmount" class="form-control" placeholder="0"/>
                                        <span class="input-group-addon"><i class="fa fa-sort-numeric-asc"></i></span>
                                    </div>
                                    <p class="help-block">Ingrese la cantidad de puño deseada.</p>
                                </div>

                                <div class="form-group disabled">
                                    <label>Patrón de Rayado*</label>

                                    <div class="form-group input-group" style="margin-bottom: 0px;">
                                        <input type="text" id="itemPunoPattern" class="form-control input-pattern"
                                               placeholder=""/>
                                        <input type="hidden" id="itemPunoPatternId"/>
                                        <input type="hidden" id="item-puno-pattern-label"/>
                                        <span class="input-group-addon"><i class="fa fa-align-justify"></i></span>
                                    </div>
                                    <p class="help-block">Ingrese el patrón de rayado.</p>
                                </div>
                            </div>
                            <div class="second-column">
                                <div class="form-group">
                                    <label>Tejido*</label>

                                    <div class="form-group input-group" style="margin-bottom: 0px;">
                                        <input type="text" id="itemPunoFabric" class="form-control" placeholder=""/>
                                        <input type="hidden" id="itemPunoFabricId"/>
                                        <input type="hidden" id="item-puno-fabric-label"/>
                                        <input type="hidden" id="item-puno-fabric-short-label"/>
                                        <span class="input-group-addon"><i class="fa fa-barcode"></i></span>
                                    </div>
                                    <p class="help-block">Ingrese el tipo de puño.</p>
                                </div>
                                <div class="form-group disabled">
                                    <label>Combinación*</label>

                                    <div class="form-group input-group" style="margin-bottom: 0px;">
                                        <input type="text" class="input-pattern-detail form-control disabled"
                                               id="itemPunoCombination" aria-labelledby="itemCuellosCombination"/>

                                        <input type="hidden" id="itemPunoCombinationId"/>
                                        <input type="hidden" id="item-puno-combination-label"/>
                                        <span class="input-group-addon"><i class="fa fa-bars"></i></span>
                                    </div>
                                    <p class="help-block">Ingrese colores del rayado.</p>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                    <div class="tab-pane fade" id="cuellos-y-tiras">
                        <fieldset id="fieldset-cuellos" disabled>
                            <h4 style="text-align:center">Selección de cuello y tiras</h4>

                            <div class="first-column">
                                <div class="form-group">
                                    <label>Cantidad*</label>

                                    <div class="form-group input-group" style="margin-bottom: 0px;">
                                        <input type="text" id="itemCuellosAmount" class="form-control" placeholder="0"/>
                                        <span class="input-group-addon"><i class="fa fa-sort-numeric-asc"></i></span>
                                    </div>
                                    <p class="help-block">Ingrese la cantidad deseada.</p>
                                </div>

                                <div class="form-group disabled">
                                    <label>Patrón de Rayado*</label>

                                    <div class="form-group input-group" style="margin-bottom: 0px;">
                                        <input type="text" id="itemCuellosPattern" class="form-control input-pattern"
                                               placeholder=""/>
                                        <input type="hidden" id="itemCuellosPatternId"/>
                                        <input type="hidden" id="item-cuellos-pattern-label"/>
                                        <span class="input-group-addon"><i class="fa fa-align-justify"></i></span>
                                    </div>
                                    <p class="help-block">Ingrese el patrón de rayado.</p>
                                </div>
                            </div>
                            <div class="second-column">
                                <div class="form-group">
                                    <label>Tejido*</label>

                                    <div class="form-group input-group" style="margin-bottom: 0px;">
                                        <input type="text" id="itemCuellosFabric" class="form-control" placeholder=""/>
                                        <input type="hidden" id="itemCuellosFabricId"/>
                                        <input type="hidden" id="item-cuellos-fabric-label"/>
                                        <input type="hidden" id="item-cuellos-fabric-short-label"/>
                                        <span class="input-group-addon"><i class="fa fa-barcode"></i></span>
                                    </div>
                                    <p class="help-block">Ingrese el tipo de cuello y tira.</p>
                                </div>
                                <div class="form-group disabled">
                                    <label>Combinación*</label>

                                    <div class="form-group input-group" style="margin-bottom: 0px;">
                                        <input type="text" class="input-pattern-detail form-control disabled"
                                               id="itemCuellosCombination" aria-labelledby="itemCuellosCombination"/>
                                        <input type="hidden" id="itemCuellosCombinationId"/>
                                        <input type="hidden" id="item-cuellos-combination-label"/>
                                        <span class="input-group-addon"><i class="fa fa-bars"></i></span>
                                    </div>
                                    <p class="help-block">Ingrese colores del rayado.</p>
                                </div>
                            </div>
                        </fieldset>
                    </div>

                </div>


                </fieldset>
                <div class="modal-footer">
                    <button type="button" id="close-order-item" class="btn btn-default" data-dismiss="modal">Cerrar
                    </button>
                    <button type="button" id="add-order-item" class="btn btn-success">Agregar Item</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->

</div>
</form:form>

</div>
</div>
</tiles:putAttribute>
<tiles:putAttribute name="page-controller">
    <script src="js/controllers/order/newOrderPageControllerOLD.js"></script>
</tiles:putAttribute>
</tiles:insertDefinition>