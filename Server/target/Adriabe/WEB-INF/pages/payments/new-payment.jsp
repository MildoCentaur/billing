<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name=".defaultTemplate">
<tiles:putAttribute name="page">

<div class="panel panel-primary">
<style type="text/css">
    td, th {
        text-align: center;
    }

</style>
<div class="panel-heading">
        ${mainPanelLabel}
</div>
<div class="panel-body">
<div class="row">
<form role="form">
<div class="col-lg-6">
    <div class="form-group">
        <label>Razón Social*</label>
        <input id="entity-name" class="form-control " placeholder="Razón Social"
               value="" data-client-id="0" id="name" name="name"/>
    </div>
</div>
<div class="col-lg-3">
    <div class="form-group">
        <label>Saldo</label>
        <input class="form-control" placeholder="0,00 $" id="balanceBilling">
    </div>
</div>
<div class="col-lg-3">
    <div class="form-group">
        <label>Saldo Actividades</label>
        <input class="form-control" placeholder="0,00 $" id="balanceActivities">
    </div>
</div>
<div class="col-lg-6">
<div class="panel panel-green">
<div class="panel-heading">
    Medios de Pago
</div>
<div class="panel-body">
<div class="col-lg-6">
    <div class="form-group">
        <label>Efectivo</label>
        <input class="form-control" placeholder="0,00 $" id="cash" data-value="0">
    </div>
</div>
<div class="col-lg-6">
    <div class="form-group">
        <label style="visibility:hidden;">do not show this element</label>
        <button type="button" style="visibility:hidden;margin-bottom: 15px;">Cancelar</button>
    </div>
</div>

<div class="col-lg-6">
    <div class="form-group">
        <label>Deposito</label>
        <input class="form-control" placeholder="0,00 $" disabled="disabled" id="total-deposit-value" data-value="0">
    </div>
</div>
<div class="col-lg-6">
    <div class="form-group">
        <label style="visibility:hidden;">do not show this element</label>
        <button type="button" class="btn btn-warning btn-modal disabled" data-toggle="modal"
                data-target="#editDepositToPaymentModal"
                style="float:right;margin-bottom: 15px;">Editar
        </button>
        <button type="button" class="btn btn-primary btn-modal disabled" data-toggle="modal"
                data-target="#addDepositToPaymentModal"
                style="float:right;margin-bottom: 15px;margin-right:10px;">Regristrar
        </button>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="addDepositToPaymentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title" id="myModalLabel">Agregar Deposito</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label>Número de transacción*</label>

                            <div class="form-group input-group" style="margin-bottom: 0px;">
                                <input id="transaction-number" class="form-control" placeholder=""/>
                                <span class="input-group-addon"><i class="fa fa-barcode"></i></span>
                            </div>
                            <p class="help-block">Número de la transacción.</p>
                        </div>
                        <div class="form-group">
                            <label>Monto*</label>

                            <div class="form-group input-group" style="margin-bottom: 0px;">
                                <input id="transaction-amount" class="form-control" placeholder="0,00 $"/>
                                <span class="input-group-addon"><i class="fa fa-barcode"></i></span>
                            </div>
                            <p class="help-block">Monto de la transacción.</p>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label>Nombre de Banco* </label>

                            <div class="form-group input-group" style="margin-bottom: 0px;">
                                <input id="transaction-bank-source" class="form-control bank-name" placeholder=""/>
                                <span class="input-group-addon"><i class="fa fa-bank"></i></span>
                            </div>
                            <p class="help-block">Nombre del Banco de Origen.</p>
                        </div>
                        <div class="form-group">
                            <label>Número de cuenta*</label>

                            <div class="form-group input-group" style="margin-bottom: 0px;">
                                <select id="transaction-account" class="form-control" name="account">
                                    <c:if test="${not empty bankAccounts}">
                                        <c:forEach items="${bankAccounts}" var="account">
                                            <option value="${account.id}">${account.number} ${account.name}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                                <span class="input-group-addon"><i class="fa fa-bank"></i></span>
                            </div>
                            <p class="help-block">Número de cuenta destino.</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                <button type="button" id="btn-register-deposit" class="btn btn-success">Registrar Deposito</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- Modal -->
<div class="modal fade" id="editDepositToPaymentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title" id="myModalLabel">Listar Depositos</h3>
            </div>
            <div class="modal-body">

                <table class="table table-striped row-border table-hover list-payments" id="list-deposit">
                    <thead>
                    <tr>
                        <th>Nº</th>
                        <th>Cuenta Origen</th>
                        <th>Cuenta Destino</th>
                        <th>Monto</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th colspan="3"></th>
                        <th style="background:green;"></th>
                    </tr>
                    </tfoot>
                    <tbody>

                    </tbody>
                </table>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<div class="col-lg-6">
    <div class="form-group">
        <label>Cheque</label>
        <input class="form-control" placeholder="0,00 $" disabled="disabled" id="total-cheques-value" data-value="0">
    </div>
</div>
<div class="col-lg-6">
    <div class="form-group">
        <label style="visibility:hidden;">do not show this element</label>
        <button type="button" class="btn btn-warning btn-modal disabled" data-toggle="modal"
                data-target="#editchequeToPaymentModal"
                style="float:right;">Editar
        </button>
        <button type="button" class="btn btn-primary btn-modal disabled" data-toggle="modal"
                data-target="#addchequeToPaymentModal"
                style="float:right;margin-bottom: 15px;margin-right:10px;">Regristrar
        </button>
    </div>
</div>
<!-- Edit cheque Modal-->
<div class="modal fade" id="editchequeToPaymentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title" id="myModalLabel">Listar Cheques</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12">
                        <table class="table table-striped row-border table-hover list-payments" id="list-cheque">
                            <thead>
                            <tr>
                                <th>Banco</th>
                                <th>Número</th>
                                <th>Fecha Deposito</th>
                                <th>Monto</th>

                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th colspan="3"></th>
                                <th style="background:green;"></th>

                            </tr>
                            </tfoot>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- Modal -->
<div class="modal fade" id="addchequeToPaymentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title" id="myModalLabel">Agregar cheque</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label>Código de Cheque*</label>

                            <div class="form-group input-group" style="margin-bottom: 0px;">
                                <input id="cheque-code" class="form-control" placeholder=""/>
                                <span class="input-group-addon"><i class="fa fa-barcode"></i></span>
                            </div>
                            <p class="help-block">Código para uso interno.</p>
                        </div>
                        <div class="form-group">
                            <label>Nombre de Banco</label>

                            <div class="form-group input-group" style="margin-bottom: 0px;">
                                <input id="cheque-bank" class="form-control bank-name" placeholder="Enter text"/>
                                <span class="input-group-addon"><i class="fa fa-users"></i></span>
                            </div>
                            <p class="help-block">Nombre del Banco emisor.</p>
                        </div>
                        <div class="form-group">
                            <label>Numero de Cheque*</label>

                            <div class="form-group input-group" style="margin-bottom: 0px;">
                                <input id="cheque-number" class="form-control" placeholder="Enter text"/>
                                <span class="input-group-addon"><i class="fa fa-map-marker"></i></span>
                            </div>
                            <p class="help-block">Número de Cheque.</p>
                        </div>
                        <div class="form-group">
                            <label>Número de Cuenta</label>

                            <div class="form-group input-group" style="margin-bottom: 0px;">
                                <input id="cheque-account-number" class="form-control" placeholder="Enter text"/>
                                <span class="input-group-addon"><i class="fa fa-map-marker"></i></span>
                            </div>
                            <p class="help-block">Número de Cuenta emisora.</p>
                        </div>
                        <div class="form-group">
                            <label>Monto</label>

                            <div class="form-group input-group" style="margin-bottom: 0px;">
                                <input id="cheque-value" class="form-control" placeholder="Enter text"/>
                                <span class="input-group-addon"><i class="fa fa-map-marker"></i></span>
                            </div>
                            <p class="help-block">Valor del cheque.</p>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label>Firmante</label>

                            <div class="form-group input-group" style="margin-bottom: 0px;">
                                <input id="cheque-name-signer" class="form-control" placeholder="Enter text"/>
                                <span class="input-group-addon"><i class="fa fa-map-marker"></i></span>
                            </div>
                            <p class="help-block">Nombre del firmante.</p>
                        </div>
                        <div class="form-group">
                            <label>Número de CUIT</label>

                            <div class="form-group input-group" style="margin-bottom: 0px;">
                                <input id="cheque-cuit-signer" class="form-control" placeholder="Enter text"/>
                                <span class="input-group-addon"><i class="fa fa-map-marker"></i></span>
                            </div>
                            <p class="help-block">Número de CUIT del firmante.</p>
                        </div>
                        <div class="form-group">
                            <label>Fecha de Vencimiento</label>

                            <div class="input-group date form_date " data-date-format="dd/mm/yyyy"
                                 data-link-field="dtp_input2" data-link-format="yyyy-mm-dd" style="margin-bottom: 0px;">
                                <input id="cheque-expiration-date" class="form-control" placeholder="DD/MM/YYYY"
                                       size="16" value=""/>
                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                            </div>
                            <p class="help-block">Fecha de Vencimiento.</p>
                        </div>
                        <div class="form-group">
                            <label>Observaciones</label>

                            <div class="form-group">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" id="cheque-is-third-party" value="0"/>Terceros
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" id="cheque-is-crossed" value="0"/>Cruzado
                                    </label>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                <button type="button" id="btn-register-cheque" class="btn btn-success">Registrar Cheque</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<div class="col-lg-6">
    <div class="form-group">
        <label>Total</label>
        <input class="form-control" placeholder="0,00 $" disabled="disabled" id="total-payment-value">
    </div>
</div>

<!--<div class="col-lg-6">
    <div class="form-group">
        <label style="visibility:hidden;">do not show this element</label>

    </div>
</div>-->
</div>
<!-- /.panel-body -->
</div>
</div>

<div class="col-lg-6">
    <div class="panel panel-red" id="payables">
        <div class="panel-heading" id="payables-header">
                ${needToPayLabel}
        </div>
        <div class="panel-body" style="height:332px">
            <table class="table table-striped row-border table-hover" id="list_items_to_deliver">
                <thead>
                <tr>
                    <th style="width:15%">Pagar</th>
                    <th style="width:25%">Fecha</th>
                    <th style="width:25%">Número</th>
                    <th style="width:10%">Bultos</th>
                    <th style="width:25%">Importe</th>

                </tr>
                </thead>
                <tbody>
                <fmt:setLocale value="es_ES"/>
                <c:if test="${not empty orders}">
                    <c:forEach items="${orders}" var="order" varStatus="status">
                        <tr id="row_${status.index}" data-delivery-order-id="${order.id}">
                            <td><input type="checkbox" value="0"/>Si</td>
                            <td><fmt:formatDate value="${order.date}" pattern="dd/MM/yyyy"/></td>
                            <td><fmt:formatNumber pattern="########" value="${order.number}"/></td>
                            <td><fmt:formatNumber pattern="###" value="${order.packagesAmount}"/></td>
                            <td><fmt:formatNumber groupingUsed="." value="${order.totalAmount}" currencySymbol="$"
                                                  currencyCode="es"
                                                  minFractionDigits="2"/></td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${empty orders}">
                    <tr>
                        <td colspan="5">Debe ingresar la entidad.</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
    <button type="button" class="btn btn-success disabled" id="btn-register-payment"
            style="float:right;margin-left:10px;">Registrar Pago
    </button>
</div>

</form>
</div>
<!-- /.row (nested) -->
</div>
<!-- /.panel-body -->
</div>
<!-- /.panel -->
</div>
<input type="hidden" id="account-type" value="${accountType}"/>
<input type="hidden" id="accountable-name" value="${entityName}"/>
<input type="hidden" id="accountable-id" value="${entityId}"/>
<input type="hidden" id="accountable-entity" value="${accountableEntity}"/>
<!-- /.col-lg-12 -->
</tiles:putAttribute>
<tiles:putAttribute name="page-controller">
    <script src="js/controllers/payments/newPaymentPageController.js"></script>
</tiles:putAttribute>
</tiles:insertDefinition>