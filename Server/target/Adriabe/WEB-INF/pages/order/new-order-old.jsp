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
<div class="row">
    <div class="col-lg-6">
        <div class="form-group">
            <label>Señores</label>

            <div class="form-group input-group" style="margin-bottom: 0px;">
                <input type="text " id="client-name" data-client-id="${client.id}" data-client-name="${client.name}"
                       class="form-control autocomplete-client" placeholder="Razón Social"/>
                <input type="hidden" id="client-name-hidden" data-client-id="${order.client.id}"
                       data-client-name="${order.client.name}">
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
            <th colspan="3" style="text-align:center;">Accesorios</th>
            <th rowspan="2" style="text-align:center;">
                    <span class='panel-yellow' style="float: left;"><a onclick="return false;">
                        <i class='fa fa-pencil-square-o'></i></a>
                    </span>
                <!-- style="padding:3px 6px;margin-left: 5px;" -->
                    <span class='panel-red' style="float: right;"><a onclick="return false;">
                        <i class='fa fa-trash-o'></i></a>
                    </span>
            </th>
        </tr>
        <tr>
            <th>Cuellos</th>
            <th>Tiras</th>
            <th>Puño</th>
        </tr>
        </thead>
        <tbody>

        <c:if test="${not empty order.items}">
            <c:forEach var="item" items="${order.items}" varStatus="loop">

                <tr id="row_${item.id}" data-item-id="${item.id}" data-selected-row="${loop.index}"
                    class="item-rows">
                    <td>${loop.index + 1}</td>
                    <td>${item.quantity}</td>
                    <c:set var="stripeId" value="${0}"/>
                    <c:set var="combinationId" value="${0}"/>
                    <c:set var="stripeName" value="${0}"/>
                    <c:set var="combinationName" value="${0}"/>
                    <c:set var="isStripe" value="false"/>
                    <c:if test="${not empty item.product.stripe}">
                        <c:set var="isStripe" value="true"/>
                        <c:set var="stripeId" value="${item.product.stripe.id}"/>
                        <c:set var="stripeName" value="${item.product.stripe.name}"/>
                        <c:set var="combinationName" value="${item.product.stripeCombination.name}"/>
                        <c:set var="combinationId" value="${item.product.stripeCombination.id}"/>
                    </c:if>

                    <td class="item-main product-cell"
                        data-item-id="${item.id}"
                        data-quantity="${item.quantity}"
                        data-product-id="${item.product.id}"
                        data-product-name="${item.product.productName}"
                        data-fabric-id="${item.product.fabric.id}"
                        data-fabric-name="${item.product.fabric.shortname}"
                        data-fabric-code="${item.product.fabric.code}"
                        data-color-name="${item.product.color.name}"
                        data-color-id="${item.product.color.id}"
                        data-is-stripe="${isStripe}"
                        data-stripe-id="${stripeId}"
                        data-stripe-name="${stripeName}"
                        data-combination-name="${combinationName}"
                        data-combination-id="${combinationId}">${item.product.productName}</td>

                    <!-- Columna de cuellos -->
                    <c:if test="${not empty item.cuelloAccesoryItem}">
                        <c:set var="stripeId" value="${0}"/>
                        <c:set var="combinationId" value="${0}"/>
                        <c:set var="combinationName" value="${0}"/>
                        <c:set var="isStripe" value="false"/>
                        <c:if test="${not empty item.cuelloAccesoryItem.product.stripe}">
                            <c:set var="isStripe" value="true"/>
                            <c:set var="stripeId" value="${item.cuelloAccesoryItem.product.stripe.id}"/>
                            <c:set var="combinationId"
                                   value="${item.cuelloAccesoryItem.product.stripeCombination.id}"/>
                            <c:set var="combinationName"
                                   value="${item.cuelloAccesoryItem.product.stripeCombination.name}"/>
                        </c:if>
                        <td class="item-cuello product-cell"
                            data-item-id="${item.cuelloAccesoryItem.id}"
                            data-quantity="${item.cuelloAccesoryItem.quantity}"
                            data-product-id="${item.cuelloAccesoryItem.product.id}"
                            data-product-name="${item.cuelloAccesoryItem.product.productName}"
                            data-fabric-name="${item.cuelloAccesoryItem.product.fabric.shortname}"
                            data-fabric-id="${item.cuelloAccesoryItem.product.fabric.id}"
                            data-fabric-code="${item.product.fabric.code}"
                            data-color-name="${item.cuelloAccesoryItem.product.color.name}"
                            data-color-id="${item.cuelloAccesoryItem.product.color.id}"
                            data-isStripe="${isStripe}"
                            data-stripe-id="${stripeId}"
                            data-stripe-name="${stripeName}"
                            data-combination-id="${combinationId}"
                            data-combination-name="${combinationName}">
                                ${item.cuelloAccesoryItem.quantity}</td>
                    </c:if>
                    <c:if test="${empty item.cuelloAccesoryItem}">
                        <td class="center" data-accesory-cuello="false">-</td>
                    </c:if>
                    <!-- Fin Columna de cuellos-->
                    <!-- Comienzo Columna de tiras-->
                    <c:if test="${not empty item.tiraAccesoryItem}">
                        <c:set var="stripeId" value="${0}"/>
                        <c:set var="combinationId" value="${0}"/>
                        <c:set var="combinationName" value="${0}"/>
                        <c:set var="isStripe" value="false"/>
                        <c:if test="${not empty item.tiraAccesoryItem.product.stripe}">
                            <c:set var="isStripe" value="true"/>
                            <c:set var="stripeId" value="${item.tiraAccesoryItem.product.stripe.id}"/>
                            <c:set var="combinationId"
                                   value="${item.tiraAccesoryItem.product.stripeCombination.id}"/>
                            <c:set var="combinationName"
                                   value="${item.tiraAccesoryItem.product.stripeCombination.name}"/>
                        </c:if>
                        <td class="item-tira product-cell"
                            data-item-id="${item.tiraAccesoryItem.id}"
                            data-quantity="${item.tiraAccesoryItem.quantity}"
                            data-product-id="${item.tiraAccesoryItem.product.id}"
                            data-product-name="${item.tiraAccesoryItem.product.productName}"
                            data-fabric-name="${item.tiraAccesoryItem.product.fabric.shortname}"
                            data-fabric-id="${item.tiraAccesoryItem.product.fabric.id}"
                            data-fabric-code="${item.product.fabric.code}"
                            data-color-name="${item.tiraAccesoryItem.product.color.name}"
                            data-color-id="${item.tiraAccesoryItem.product.color.id}"
                            data-is-stripe="${isStripe}"
                            data-stripe-id="${stripeId}"
                            data-stripe-name="${stripeName}"
                            data-combination-id="${combinationId}"
                            data-combination-name="${combinationName}">
                                ${item.tiraAccesoryItem.quantity}</td>
                    </c:if>
                    <c:if test="${empty item.tiraAccesoryItem}">
                        <td class="center" data-accesory-tira="false">-</td>
                    </c:if>
                    <!-- Fin Columna de tiras-->
                    <!-- Comienzo Columna de puno-->
                    <c:if test="${not empty item.punoAccesoryItem}">
                        <c:set var="stripeId" value="${0}"/>
                        <c:set var="combinationId" value="${0}"/>
                        <c:set var="combinationName" value="${0}"/>
                        <c:set var="isStripe" value="false"/>
                        <c:if test="${not empty item.punoAccesoryItem.product.stripe}">
                            <c:set var="isStripe" value="true"/>
                            <c:set var="stripeId" value="${item.punoAccesoryItem.product.stripe.id}"/>
                            <c:set var="combinationId"
                                   value="${item.punoAccesoryItem.product.stripeCombination.id}"/>
                            <c:set var="combinationName"
                                   value="${item.punoAccesoryItem.product.stripeCombination.name}"/>
                        </c:if>
                        <td class="item-puno product-cell"
                            data-item-id="${item.punoAccesoryItem.id}"
                            data-quantity="${item.punoAccesoryItem.quantity}"
                            data-product-id="${item.punoAccesoryItem.product.id}"
                            data-product-name="${item.punoAccesoryItem.product.productName}"
                            data-fabric-name="${item.punoAccesoryItem.product.fabric.shortname}"
                            data-fabric-id="${item.punoAccesoryItem.product.fabric.id}"
                            data-fabric-code="${item.product.fabric.code}"
                            data-color-name="${item.punoAccesoryItem.product.color.name}"
                            data-color-id="${item.punoAccesoryItem.product.color.id}"
                            data-is-stripe="${isStripe}"
                            data-stripe-id="${stripeId}"
                            data-stripe-name="${stripeName}"
                            data-combination-id="${combinationId}"
                            data-combination-name="${combinationName}">
                                ${item.punoAccesoryItem.quantity}</td>
                    </c:if>
                    <c:if test="${empty item.punoAccesoryItem}">
                        <td class="center" data-accesory-puno="false">-</td>
                    </c:if>
                    <!-- Fin Columna de puno-->

                    <td style="text-align:center;padding-left:0px;">
                            <span id="row_${item.id}" class='edit-item btn btn-warning btn-outline'
                                  data-selected-row='${loop.index}' data-puno-item-id="${item.punoAccesoryItem.id}"
                                  data-cuello-item-id="${item.cuelloAccesoryItem.id}"
                                  data-tira-item-id="${item.tiraAccesoryItem.id}" data-item-id="${item.id}"
                                  style="float:left;">
                                <i class='fa fa-pencil-square-o'></i>
                            </span>
                        <!-- style="padding:3px 6px;margin-left: 5px;" -->
                            <span id="row_${item.id}" class='delete-item btn btn-danger btn-outline'
                                  data-selected-row='${loop.index}' data-puno-item-id="${item.punoAccesoryItem.id}"
                                  data-cuello-item-id="${item.cuelloAccesoryItem.id}"
                                  data-tira-item-id="${item.tiraAccesoryItem.id}" data-item-id="${item.id}"
                                  style="float:right;">
                                <i class='fa fa-trash-o'></i>
                            </span>
                    </td>
                </tr>

            </c:forEach>
        </c:if>
        </tbody>

    </table>


    <input type="hidden" id="order-id" value="${order.id}"/>
    <input type="hidden" id="selected-item-id" value="-1"/>
    <input type="hidden" id="selected-row" value="-1"/>


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
    <li><a href="#cuellos" data-toggle="tab">Cuellos</a>
    </li>
    <li><a href="#tiras" data-toggle="tab">Tiras</a>
    </li>
    <li><a href="#puno" data-toggle="tab">Puño</a>
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
                <input type="text" id="item-main-fabric" class="form-control" placeholder=""/>
                <span class="input-group-addon"><i class="fa fa-barcode"></i></span>
            </div>
            <p class="help-block">Ingrese el tejido del item.</p>
        </div>
        <div class="first-column">
            <div class="form-group">
                <label>Cantidad*</label>

                <div class="form-group input-group" style="margin-bottom: 0px;">
                    <input type="text" id="item-main-amount" class="form-control item-amount" placeholder="0"/>
                    <span class="input-group-addon"><i class="fa fa-sort-numeric-asc"></i></span>
                </div>
                <p class="help-block">Ingrese la cantidad solicitada.</p>
            </div>

            <div class="form-group disabled">
                <label>Patrón de Rayado*</label>

                <div class="form-group input-group" style="margin-bottom: 0px;">
                    <input type="text" id="item-main-pattern" class="form-control input-pattern"
                           placeholder=""/>
                    <span class="input-group-addon"><i class="fa fa-align-justify"></i></span>
                </div>
                <p class="help-block">Ingrese el patrón de rayado.</p>
            </div>
        </div>
        <div class="second-column">

            <div class="form-group">
                <label>Color*</label>
                <input type="text" id="item-color" class="form-control" placeholder=""/>

                <p class="help-block">Ingrese el color del tejido.</p>
            </div>
            <div class="form-group disabled">
                <label>Combinación*</label>

                <div class="form-group input-group" style="margin-bottom: 0px;">
                    <input type="text" class="input-pattern-detail form-control disabled" id="item-main-combination">
                    <span class="input-group-addon"><i class="fa fa-bars"></i></span>
                </div>
                <p class="help-block">Ingrese colores del rayado.</p>
            </div>
        </div>
    </fieldset>

</div>
<div class="tab-pane fade" id="cuellos">
    <fieldset id="fieldset-cuello" disabled>
        <h4 style="text-align:center">Selección de cuello</h4>

        <div class="first-column">
            <div class="form-group">
                <label>Cantidad*</label>

                <div class="form-group input-group" style="margin-bottom: 0px;">
                    <input type="text" id="item-cuello-amount" class="form-control item-amount" placeholder="0"/>
                    <span class="input-group-addon"><i class="fa fa-sort-numeric-asc"></i></span>
                </div>
                <p class="help-block">Ingrese la cantidad deseada.</p>
            </div>

            <div class="form-group disabled">
                <label>Patrón de Rayado*</label>

                <div class="form-group input-group" style="margin-bottom: 0px;">
                    <input type="text" id="item-cuello-pattern" class="form-control input-pattern"
                           placeholder=""/>
                    <span class="input-group-addon"><i class="fa fa-align-justify"></i></span>
                </div>
                <p class="help-block">Ingrese el patrón de rayado.</p>
            </div>
        </div>
        <div class="second-column">
            <div class="form-group">
                <label>Tejido*</label>

                <div class="form-group input-group" style="margin-bottom: 0px;">
                    <input type="text" id="item-cuello-fabric" class="form-control" placeholder=""/>
                    <span class="input-group-addon"><i class="fa fa-barcode"></i></span>
                </div>
                <p class="help-block">Ingrese el tipo de cuello.</p>
            </div>
            <div class="form-group disabled">
                <label>Combinación*</label>

                <div class="form-group input-group" style="margin-bottom: 0px;">
                    <input type="text" class="input-pattern-detail form-control disabled"
                           id="item-cuello-combination"/>
                    <span class="input-group-addon"><i class="fa fa-bars"></i></span>
                </div>
                <p class="help-block">Ingrese colores del rayado.</p>
            </div>
        </div>
    </fieldset>
</div>
<div class="tab-pane fade" id="tiras">
    <fieldset id="fieldset-tira" disabled>
        <h4 style="text-align:center">Selección de tiras</h4>

        <div class="first-column">
            <div class="form-group">
                <label>Cantidad*</label>

                <div class="form-group input-group" style="margin-bottom: 0px;">
                    <input type="text" id="item-tira-amount" class="form-control item-amount" placeholder="0"/>
                    <span class="input-group-addon"><i class="fa fa-sort-numeric-asc"></i></span>
                </div>
                <p class="help-block">Ingrese la cantidad deseada.</p>
            </div>

            <div class="form-group disabled">
                <label>Patrón de Rayado*</label>

                <div class="form-group input-group" style="margin-bottom: 0px;">
                    <input type="text" id="item-tira-pattern" class="form-control input-pattern"
                           placeholder=""/>
                    <span class="input-group-addon"><i class="fa fa-align-justify"></i></span>
                </div>
                <p class="help-block">Ingrese el patrón de rayado.</p>
            </div>
        </div>
        <div class="second-column">
            <div class="form-group">
                <label>Tejido*</label>

                <div class="form-group input-group" style="margin-bottom: 0px;">
                    <input type="text" id="item-tira-fabric" class="form-control" placeholder=""/>
                    <span class="input-group-addon"><i class="fa fa-barcode"></i></span>
                </div>
                <p class="help-block">Ingrese el tipo de tiras.</p>
            </div>
            <div class="form-group disabled">
                <label>Combinación*</label>

                <div class="form-group input-group" style="margin-bottom: 0px;">
                    <input type="text" class="input-pattern-detail form-control disabled"
                           id="item-tira-combination"/>
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
                    <input type="text" id="item-puno-amount" class="form-control item-amount" placeholder="0"/>
                    <span class="input-group-addon"><i class="fa fa-sort-numeric-asc"></i></span>
                </div>
                <p class="help-block">Ingrese la cantidad deseada.</p>
            </div>

            <div class="form-group disabled">
                <label>Patrón de Rayado*</label>

                <div class="form-group input-group" style="margin-bottom: 0px;">
                    <input type="text" id="item-puno-pattern" class="form-control input-pattern"
                           placeholder=""/>
                    <span class="input-group-addon"><i class="fa fa-align-justify"></i></span>
                </div>
                <p class="help-block">Ingrese el patrón de rayado.</p>
            </div>
        </div>
        <div class="second-column">
            <div class="form-group">
                <label>Tejido*</label>

                <div class="form-group input-group" style="margin-bottom: 0px;">
                    <input type="text" id="item-puno-fabric" class="form-control" placeholder=""/>
                    <span class="input-group-addon"><i class="fa fa-barcode"></i></span>
                </div>
                <p class="help-block">Ingrese el tipo de puño.</p>
            </div>
            <div class="form-group disabled">
                <label>Combinación*</label>

                <div class="form-group input-group" style="margin-bottom: 0px;">
                    <input type="text" class="input-pattern-detail form-control disabled"
                           id="item-puno-combination"/>
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
    <button type="button" id="add-order-item" class="btn btn-success">Aceptar</button>
</div>
</div>
<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

</div>


</div>
</div>
</tiles:putAttribute>
<tiles:putAttribute name="page-controller">
    <script src="js/controllers/order/newOrderValidationRules.js"></script>
    <script src="js/controllers/order/orderBuilder.js"></script>
    <script src="js/controllers/order/newOrderPageController.js"></script>
</tiles:putAttribute>
</tiles:insertDefinition>