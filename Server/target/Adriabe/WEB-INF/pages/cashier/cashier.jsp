<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name=".defaultTemplate">
    <tiles:putAttribute name="page">
        <div class="panel panel-primary">
            <div class="panel-heading">
                Movimientos del Dia
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-6">
                        <label>Ingresos:</label>

                        <div class="table-responsive">
                            <table class="table table-striped row-border table-hover" id="list_incomes">
                                <thead>
                                <tr>
                                    <th>Hora</th>
                                    <th>Concepto</th>
                                    <th>Monto</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${not empty incomes}">
                                    <c:forEach var="movement" items="${incomes}" varStatus="status">
                                        <tr>
                                            <td>${movement.accountabletDateTime}</td>
                                            <td>${movement.concept} ${movement.client.name}</td>
                                            <td><span class="currency">${movement.credit}</span></td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty incomes}">
                                    <tr>
                                        <td colspan="3">No hay movimientos</td>
                                    </tr>
                                </c:if>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th colspan="2" style="text-align:right">Total:</th>
                                    <th><span class="currency">${totalIncome}</span></th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                        <!-- /.table-responsive -->
                        <button type="button" id="btn-add-input" class="btn btn-primary"
                                style="float:right;margin-left:10px;">Agregar Ingreso
                        </button>
                    </div>
                    <!-- /.col-lg-4 (nested) -->

                    <div class="col-lg-6">
                        <label>Egresos:</label>

                        <div class="table-responsive">
                            <table class="table table-striped row-border table-hover" id="list_outcomes">
                                <thead>
                                <tr>
                                    <th>Hora</th>
                                    <th>Concepto</th>
                                    <th>Monto</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${not empty outcomes}">
                                    <c:forEach var="movement" items="${outcomes}" varStatus="status">
                                        <tr>
                                            <td>${movement.accountabletDateTime}</td>
                                            <td>${movement.concept} ${movement.supplier.name}</td>
                                            <td><span class="currency">${movement.debit}</span></td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty outcomes}">
                                    <tr>
                                        <td colspan="3">No hay movimientos</td>
                                    </tr>
                                </c:if>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th colspan="2" style="text-align:right">Total:</th>
                                    <th><span class="currency">${totalOutcome}</span></th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                        <!-- /.table-responsive -->
                        <button type="button" id="btn-add-output" class="btn btn-primary"
                                style="float:right;margin-left:10px;">Agregar Egreso
                        </button>
                    </div>
                    <div class="col-lg-8" style="margin-top: 40px;"></div>
                    <!-- /.col-lg-4 (nested) -->
                    <div class="col-lg-4" style="margin-top: 40px;">
                        <label>Resumen:</label>

                        <div class="table-responsive">
                            <table class="table table-striped row-border table-hover" id="list_items_to_deliver">
                                <thead>
                                <tr>
                                    <th colspan="3">Movimientos totalizados</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr class="odd gradeX" id="row_1">
                                    <td>${now}</td>
                                    <td>Ingresos</td>
                                    <td><span class="currency">${totalIncome}</span></td>
                                </tr>
                                <tr class="even gradeC" id="row_2">
                                    <td>${now}</td>
                                    <td>Egresos</td>
                                    <td><span class="currency">${totalOutcome}</span></td>
                                </tr>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th colspan="2" style="text-align:right">Total:</th>
                                    <th><span class="currency">${totalSum}</span></th>
                                </tr>
                                </tfoot>
                            </table>

                        </div>
                        <!-- /.table-responsive -->
                    </div>
                    <!-- /.col-lg-4 (nested) -->

                </div>
                <button type="button" id="btn-close-cashier" class="btn btn-success disabled"
                        style="float:right;margin-left:10px;">Cerrar Caja
                </button>

                <!-- /.row (nested) -->
            </div>
            <!-- /.panel-body -->


        </div>
        <!-- /.panel -->
    </tiles:putAttribute>
    <tiles:putAttribute name="page-controller">
        <script src="js/controllers/cashier/listCashierPageController.js"></script>
    </tiles:putAttribute>

</tiles:insertDefinition>