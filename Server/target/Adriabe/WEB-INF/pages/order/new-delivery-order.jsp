<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name=".defaultTemplate">
<tiles:putAttribute name="page">
<style>
    .btn-move {
        margin-top: 5px;
        margin-bottom: 5px;
        width: 60px;
    }

    #list_items_prepeared tr td {
        padding: 8px 3px;
    }

    .check-mark {
        color: green;

    }

    .cancel-mark {
        color: red;
    }

    .delivery-mark {
        display: none;
    }

    th.total-amount {
        padding: 8px 0px;
    }
</style>
<div class="panel-heading">
    Registrar orden de entrega
</div>
<fmt:setLocale value="ES_es" scope="session"/>
<div class="panel-body">
    <div class="row">
        <div class="col-lg-6">
            <div class="form-group">
                <label>Nombre*</label>
                <input class="form-control autocomplete-client" placeholder="Razón Social"
                       value="${client.name}" data-client-id="${client.id}" data-client-name="${client.name}"
                       data-order-id="${orderId}"
                       id="name" name="name"/>

                <p class="help-block">Razón social, Nombre del contacto</p>
            </div>
        </div>
        <div class="col-lg-3"></div>
        <div class="col-lg-3">
            <div class="form-group">
                <label>Saldo</label>
                <input id="client-balance" class="form-control balance" placeholder="0,00 $"
                       value="<fmt:formatNumber type="currency" currencySymbol="$" minFractionDigits="2" value="${client.balanceBilling}" />">
            </div>
            <div class="form-group">
                <label>Actividades</label>
                <input id="client-balance-activities" class="form-control balance" placeholder="0,00 $"
                       value="<fmt:formatNumber type="currency" currencySymbol="$"  minFractionDigits="2" value="${client.balanceActivity}" />">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-5">
            <label>Items preparados:</label>

            <div class="table-responsive">
                <table class="table row-border table-hover" id="list_items_prepeared">
                    <thead>
                    <tr>
                        <th></th>
                        <th>Descripción</th>
                        <th>Monto</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${not empty list}">
                        <c:forEach var="item" items="${list}" varStatus="loop">
                            <tr id="row_item_${item.id}" class="group row-group" data-status="${item.status}">
                                <td>
                                    <fmt:formatNumber type="number" minFractionDigits="0"
                                                      value="${item.requestedPackages}"/>

                                </td>
                                <td class="product" data-product-id="${item.product.id}"
                                    data-product-price="${item.product.price}"
                                    data-product-name="${item.product.productName}">
                                        ${item.product.productName}
                                </td>
                                <td>
                                    <label class="text-center item-price">
                                        <fmt:formatNumber type="currency" currencySymbol="$"
                                                          minFractionDigits="2" value="${item.itemAmount}"/>
                                    </label>

                                </td>
                            </tr>
                            <c:forEach var="packageItem" items="${item.packages}" varStatus="innerLoop">

                                <c:if test="${packageItem.status == 'DELIVERED' || packageItem.status == 'READY_TO_DELIVER'}">
                                    <tr id="row_package_${packageItem.id}" class="success row_item_${item.id}">
                                        <td><fmt:formatNumber type="number" minFractionDigits="2"
                                                              value="${packageItem.weight}"/> kg
                                        </td>
                                        <td class="package" data-package-id="${packageItem.id}"
                                            data-product-price="${item.product.price}"
                                            data-product-id="${item.product.id}"
                                            data-product-name="${item.product.productName}"
                                            data-package-weight="${packageItem.weight}">
                                                ${item.product.productName}
                                        </td>
                                        <td style="padding: 8px 0px;"><label
                                                class="text-success text-center">Entregado</label>
                                            <label class="delivery-mark check-mark" style="display: inline-block;"><i
                                                    class="fa fa-check"></i></label>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${packageItem.status == 'RETURNED'}">
                                    <tr id="row_package_${packageItem.id}" class="danger row_item_${item.id}">
                                        <td><fmt:formatNumber type="number" minFractionDigits="2"
                                                              value="${packageItem.weight}"/> kg
                                        </td>
                                        <td class="package" data-package-id="${packageItem.id}"
                                            data-product-price="${item.product.price}"
                                            data-product-id="${item.product.id}"
                                            data-product-name="${item.product.productName}"
                                            data-package-weight="${packageItem.weight}">
                                                ${item.product.productName}
                                        </td>
                                        <td style="padding: 8px 0px;"><label
                                                class="text-danger text-center">Devuelto</label>
                                            <label class="delivery-mark cancel-mark" style="display: inline-block;"><i
                                                    class="fa fa-times"></i></label>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${packageItem.status == 'PREPEARED'}">
                                    <tr id="row_package_${packageItem.id}" class="row_item_${item.id}">
                                        <td><fmt:formatNumber type="number" minFractionDigits="2"
                                                              value="${packageItem.weight}"/> kg
                                        </td>
                                        <td class="package" data-package-id="${packageItem.id}"
                                            data-product-price="${item.product.price}"
                                            data-product-id="${item.product.id}"
                                            data-product-name="${item.product.productName}"
                                            data-package-weight="${packageItem.weight}">
                                                ${item.product.productName}
                                        </td>
                                        <td>
                                            <label class="text-center package-total"
                                                   data-price="${item.product.price}"
                                                   data-weight="${packageItem.weight}">

                                            </label>
                                            <label class="delivery-mark check-mark"><i class="fa fa-check"></i></label>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                    </c:if>
                    </tbody>
                    <tfoot>
                    <tr>
                        <th colspan="2" class="total-quantity"></th>

                        <th class="total-amount"></th>
                    </tr>
                    </tfoot>
                </table>
            </div>
            <label>El cliente posee <span class="text-danger">${ordersToDeliver}</span> sumando <span
                    class="text-danger" id="packages-to-deliver">${packagesToDeliver}</span>
                para entregar</label>
        </div>
        <!-- /.table-responsive -->
        <div class="col-lg-2 text-center">
            <input type="button" value=">" class="btn btn-primary btn-move disabled" style="margin-top:80px;"
                   id="add-single-element"/><br/>
            <input type="button" value=">>" class="btn btn-primary btn-move disabled"
                   id="add-single-item"/><br/>
            <input type="button" value=">>>" class="btn btn-primary btn-move" id="add-all-items"/><br/>
            <input type="button" value="<" class="btn btn-primary disabled btn-move"
                   id="remove-single-element"/><br/>
            <input type="button" value="<<" class="btn btn-primary disabled btn-move"
                   id="remove-single-item"/><br/>
            <input type="button" value="<<<" class="btn btn-primary btn-move"
                   id="remove-all-items"/><br/>
        </div>
        <div class="col-lg-5">
            <label>Items a ser entregados:</label>

            <div class="table-responsive">
                <table class="table table-striped row-border table-hover" id="list_items_delivery">
                    <thead>
                    <tr>
                        <th></th>
                        <th>Descripción</th>
                        <th>Monto</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                    <tfoot>
                    <tr>
                        <th colspan="2" class="total-quantity"></th>

                        <th class="total-amount"></th>
                    </tr>
                    </tfoot>
                </table>
                <!-- /.table-responsive -->
            </div>
            <!-- /.col-lg-6 (nested) -->
        </div>
    </div>

    <button type="button" id="btn-accept" class="btn btn-success" style="float:right;margin-left:10px;">
        Entregar
    </button>
    <button type="reset" id="btn-clean" class="btn btn-danger" style="float:right;margin-left:10px;">Cancelar
    </button>
</div>
<!-- /.row (nested) -->
</div>
<!-- /.panel-body -->
</div>
<!-- /.panel -->
</tiles:putAttribute>
<tiles:putAttribute name="page-controller">
    <script src="js/controllers/order/newDeliveryOrderPageController.js"></script>
</tiles:putAttribute>

</tiles:insertDefinition>



