<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name=".defaultTemplate">
    <tiles:putAttribute name="page">
        <div class="panel panel-primary">
            <div class="panel-heading">
                Movimientos de la cuenta
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="col-lg-6">
                    <div class="form-group">
                        <label>Nombre*</label>
                        <input type="text" id="entity-name" class="form-control" placeholder="Enter text">

                        <p class="help-block">Nombre o razón social de la empresa.</p>
                    </div>
                </div>
                <div class="col-lg-6" style="margin:5px 0;">
                    <div class="well">
                        <p class="form-control-static">Presionar la lupita para ver el documento</p>
                    </div>
                </div>
                <div class="table-responsive">
                    <div class="toolbar"></div>
                    <table class="table table-striped row-border table-hover" id="list-account">
                        <thead>
                        <tr>
                            <th rowspan="2" style="text-align:center;">id</th>
                            <th rowspan="2" style="text-align:center;">Fecha</th>
                            <th rowspan="2" style="text-align:center;">Concepto</th>
                            <th colspan="2" style="text-align:center;">Movimiento</th>
                            <th rowspan="2" style="text-align:center;">Saldo</th>
                        </tr>
                        <tr>
                            <th style="text-align:center;">Debe</th>
                            <th style="text-align:center;">Haber</th>
                        </tr>

                        </thead>
                        <tbody>
                        <c:if test="${not empty list}">
                            <c:forEach var="item" items="${list}" varStatus="loop">
                                <tr id="row_${item.id}">
                                    <td>${item.id}</td>
                                    <td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${item.date}"/></td>
                                    <td>${item.concept}</td>
                                    <td style="text-align:right;">${item.debit}</td>
                                    <td style="text-align:right;">${item.credit}</td>
                                    <td style="text-align:right;">${item.balance}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                        <tfoot>
                        <tr>
                            <th colspan="3" style="text-align:right">Total:</th>
                            <th style="text-align:right;"></th>
                            <th style="text-align:right;"></th>
                            <th style="text-align:right;"></th>
                        </tr>
                        </tfoot>
                    </table>
                </div>
                <!-- /.table-responsive -->

            </div>
            <!-- /.panel-body -->
        </div>
        <input type="hidden" id="account-type" value="${accountType}"/>
        <input type="hidden" id="accountable-name" value="${entityName}"/>
        <input type="hidden" id="accountable-id" value="${entityId}"/>
        <input type="hidden" id="accountable-entity" value="${accountableEntity}"/>
        <!-- /.panel -->
    </tiles:putAttribute>
    <tiles:putAttribute name="page-controller">
        <script src="js/controllers/accountables/listAccountablePageController.js"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>