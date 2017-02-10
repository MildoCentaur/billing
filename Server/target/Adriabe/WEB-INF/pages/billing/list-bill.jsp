<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name=".defaultTemplate">
    <tiles:putAttribute name="page">
        <div class="panel panel-primary">
            <div class="panel-heading">
                Listado de facturas
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="table-responsive">
                    <div class="toolbar"></div>
                    <table class="table table-striped row-border table-hover" id="list_bills">
                        <thead>
                        <tr>
                            <th>Fecha</th>
                            <th>Número</th>
                            <th>Tipo</th>
                            <th>Razón social</th>
                            <th>Anulada</th>
                            <th>Subtotal</th>
                            <th>IVA</th>
                            <th>Total</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${not empty list}">
                            <c:forEach var="item" items="${list}" varStatus="loop">
                                <tr data-bill-id="${item.id}">

                                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${item.date}"/></td>
                                    <td>${item.billNumber}</td>
                                    <td>${item.billType}</td>
                                    <td>${item.clientFullname}</td>
                                    <td style="text-align:center;" class="cancelled" data-cancelled="${item.cancelled}">
                                        <c:if test="${not item.cancelled}">
                                            <label style="color:green"><i class="fa fa-circle-o"></i></label>
                                        </c:if>
                                        <c:if test="${item.cancelled}">
                                            <label style="color:red"><i class="fa fa-check-circle-o"></i></label>
                                        </c:if>

                                    </td>
                                    <td class="formatCurrencyFromDouble" data-value-cancelled="${item.subTotal}">
                                        <c:if test="${not item.cancelled}">
                                            ${item.subTotal}
                                        </c:if>
                                        <c:if test="${item.cancelled}">
                                            0.0
                                        </c:if>
                                    </td>
                                    <td class="formatCurrencyFromDouble" data-value-cancelled="${item.ivaTax}">
                                        <c:if test="${not item.cancelled}">
                                            ${item.ivaTax}
                                        </c:if>
                                        <c:if test="${item.cancelled}">
                                            0.0
                                        </c:if>
                                    </td>
                                    <td class="formatCurrencyFromDouble" data-value-cancelled="${item.total}">
                                        <c:if test="${not item.cancelled}">
                                            ${item.total}
                                        </c:if>
                                        <c:if test="${item.cancelled}">
                                            0.0
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                        <tfoot>
                        <tr>
                            <th colspan="5" class="text-right">Total:</th>
                            <th class="text-right"></th>
                            <th class="text-right"></th>
                            <th class="text-right"></th>
                        </tr>
                        </tfoot>
                    </table>
                </div>
                <!-- /.table-responsive -->
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->

    </tiles:putAttribute>
    <tiles:putAttribute name="page-controller">
        <script src="js/controllers/billing/listBillingPageController.js"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>