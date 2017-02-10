<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name=".defaultTemplate">

<tiles:putAttribute name="page">
<style>
    .help-block {
        margin-bottom: 1px;
        margin-top: 1px;
        font-size: 13px;
    }

    .form-group {
        margin-bottom: 3px;

    }

    .form-group label {
        margin-bottom: 1px;
        margin-top: 1px;
    }
</style>
<div class="panel panel-primary">
<div class="panel-heading">
    Formulario de Facturación
</div>
<div class="panel-body">
<form:form action="add/bill.json" id="addBillForm" method="POST" commandName="bill" role="form">
    <div class="row">
        <div class="col-lg-12" style="text-align:center;">
            <div class="form-group">
                <button type="button" class="btn btn-outline btn-default active" id="boxBillType">${billType}</button>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-5">
            <div class="form-group">
                <label>Señores</label>
                <form:input path="client.name" class="form-control autocomplete-client" placeholder="Razón Social"/>
                <p class="help-block">Razón social, Nombre del contacto</p>
            </div>
        </div>
        <div class="col-lg-4">
        </div>
        <div class="col-lg-3">
            <div class="form-group">
                <label>Fecha</label>

                <div class="input-group date form_date " data-date="${page.formatedDate}" data-date-format="dd/mm/yyyy"
                     data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <form:input path="date" class="form-control" size="16" placeholder="" value="${page.formatedDate}"/>
                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                </div>
                <p class="help-block">Fecha emisión</p>
            </div>

        </div>
    </div>
    <div class="row">
        <div class="col-lg-5">
            <div class="form-group">
                <label>Domicilio</label>
                <form:input path="address" class="form-control client-address" placeholder="Domicilio"/>
                <p class="help-block">Domicilio de facturación</p>
            </div>
            <div class="col-lg-7" style="padding-left:0px;">
                <label>IVA</label>
                <form:select path="ivaCategory" class="form-control" items="${ivaCategories}" itemLabel="label"
                             itemValue="value">

                </form:select>
                <p class="help-block">Seleccionar categoría de IVA</p>
            </div>

            <div class="col-lg-5" style="padding-right:0px;">
                <label>CUIT</label>
                <form:input path="cuit" class="form-control nro-cuit" placeholder="Número de CUIT"/>
                <p class="help-block">Número de CUIT</p>
            </div>
        </div>
        <div class="col-lg-4"></div>
        <div class="col-lg-3">
            <div class="form-group">
                <label>Nº Remito</label>

                <div class="form-group input-group" style="margin-bottom: 0px;">
                    <form:input path="orderNumber" class="form-control admin-lock-text order-number"
                                data-order-a-number="${orderANumber}" data-order-b-number="${orderBNumber}"
                                disabled="disabled"/>

                    <span class="input-group-addon admin-lock"><i class="fa fa-lock"></i></span>
                </div>
                <p class="help-block">Número de Remito</p>
            </div>
            <div class="form-group">
                <label>Nº Factura</label>

                <div class="form-group input-group" style="margin-bottom: 0px;">
                    <form:input path="billNumber" class="form-control admin-lock-text bill-number"
                                data-bill-a-number="${billANumber}" data-bill-b-number="${billBNumber}"
                                disabled="disabled"/>
                    <span class="input-group-addon admin-lock"><i class="fa fa-lock"></i></span>
                </div>
                <p class="help-block">Número de Factura</p>
            </div>
        </div>
    </div>
    <br/>

    <div class="row">
        <div class="col-lg-12">
            <div class="table-responsive">
                <table class="row-border table table-striped table-hover" class="display dataTable" cellspacing="0"
                       width="100%"
                       role="grid" id="list-bill-items">
                    <thead>

                    <tr>
                        <th rowspan="2">Nº</th>
                        <th rowspan="2">Cantidad</th>
                        <th rowspan="2">Descripción</th>
                        <th rowspan="2">Pcio. Unit.</th>
                        <th>Subtotal</th>
                        <th>Recargos</th>
                        <th>Subtotal</th>
                        <th rowspan="2"></th>
                    </tr>
                    <tr>
                        <th>(Neto)</th>
                        <th>IVA</th>
                        <th>(Bruto)</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:if test="${not empty bill.items}">
                        <c:forEach var="item" items="${bill.items}" varStatus="loop">
                            <tr id="row_${item.id}">
                                <td>${loop.count}</td>
                                <td class="format-kilograms"><fmt:formatNumber value="${item.amount}"
                                                                               minFractionDigits="2"/>Kg.
                                </td>
                                <td>${item.productDescription}</td>
                                <td class=" formatCurrencyFromDouble">${item.price}</td>
                                <td class=" formatCurrencyFromDouble">${item.subtotal}</td>
                                <td class=" formatCurrencyFromDouble">${item.tax}</td>
                                <td class=" formatCurrencyFromDouble">${item.total}</td>
                                <td>
                                    <c:if test="${not isShowBill}">
                                        <p style="padding:0 12px; color: #d9534f;margin:0;"><i
                                                class="fa fa-trash-o"></i>
                                        </p>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                    <tfoot>
                    <tr>
                        <th></th>
                        <th colspan="3" style="text-align:right">Total:</th>
                        <th style="text-align:right"></th>
                        <th style="text-align:right"></th>
                        <th style="text-align:right"></th>
                        <th><p style="padding:0 12px; color: #d9534f;margin:0;visibility:hidden;	">
                            <i class="fa fa-trash-o"></i></p></th>
                    </tr>
                    </tfoot>
                </table>
            </div>
            <!-- /.table-responsive -->

            <div class="col-lg-12">
                <button type="button" id="btn-print" class="btn btn-primary disabled" style="float:left;">Imprimir
                </button>

                <button type="reset" id="btn-clear-bill" class="btn btn-danger" style="float:right;margin:0 5px;">
                    Cancelar
                </button>
                <button type="button" id="btn-accept" class="btn btn-success disabled"
                        style="float:right;margin:0 5px;">
                    Aceptar
                </button>
                <button type="button" id="btn-add-item-table" data-toggle="modal" data-target="#add-bill-item-modal"
                        class="btn btn-primary" style="float:right;">Agregar Item
                </button>
            </div>
        </div>
    </div>
    <form:hidden path="ivaTax" class="bill-tax" value="0"/>
    <form:hidden path="total" class="bill-total" value="0"/>
    <form:hidden path="subTotal" class="bill-subtotal" value="0"/>

    <form:hidden path="client.id" class="client-id" value="0"/>
    <form:hidden path="id" class="bill-id" value="0"/>
</form:form>
<!-- Modal -->
<div class="modal fade" id="add-bill-item-modal" tabindex="-1" role="dialog" aria-labelledby="my-modal-label"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Agregar item</h4>
            </div>
            <div class="modal-body">
                <div class="tab-pane fade in active" id="main">
                    <fieldset id="fieldset-main">
                        <h4 style="text-align:center">Ingrese el producto a facturar</h4>

                        <div class="first-column">
                            <div class="form-group">
                                <label>Producto*</label>

                                <div class="form-group input-group" style="margin-bottom: 0px;">
                                    <input type="text" id="item-product" class="form-control" placeholder=""/>
                                    <span class="input-group-addon"><i class="fa fa-barcode"></i></span>
                                </div>
                                <p class="help-block">Ingrese el producto del item.</p>
                            </div>
                            <div class="form-group">
                                <label>Cantidad*</label>

                                <div class="form-group input-group" style="margin-bottom: 0px;">
                                    <input type="text" id="item-product-amount" class="form-control"
                                           placeholder="0"/>
                                            <span class="input-group-addon"><i
                                                    class="fa fa-sort-numeric-asc"></i></span>
                                </div>
                                <p class="help-block">Ingrese la cantidad solicitada (Kg).</p>
                            </div>
                        </div>
                        <div class="second-column">
                            <div class="form-group">
                                <label>Color*</label>
                                <input type="text" id="item-color" class="form-control" placeholder=""/>

                                <p class="help-block">Ingrese el color del tejido.</p>
                            </div>
                            <div class="form-group">
                                <label>Precio*</label>

                                <div class="form-group input-group" style="margin-bottom: 0px;">
                                    <input type="text" id="item-price" class="form-control" placeholder="0,00 $"/>
                                    <span class="input-group-addon"><i class="fa fa-money"></i></span>
                                </div>
                                <p class="help-block">Ingrese el precio por Kg.</p>
                            </div>
                        </div>
                    </fieldset>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="close-bill-item" class="btn btn-default" data-dismiss="modal">Cerrar
                </button>
                <button type="button" id="add-bill-item" class="btn btn-success">Agregar Item</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
</div>
</div>


</tiles:putAttribute>

<tiles:putAttribute name="page-controller">
    <c:if test="${not isShowBill}">
        <script src="js/controllers/billing/billingPageController.js"></script>
    </c:if>
    <c:if test="${isShowBill}">
        <script src="js/controllers/billing/showBillingPageController.js"></script>
    </c:if>

</tiles:putAttribute>

</tiles:insertDefinition>