<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name=".defaultTemplate">
    <tiles:putAttribute name="page">
        <div class="panel panel-primary">
            <div class="panel-heading">
                Listado de clientes
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="table-responsive">
                    <div class="toolbar"></div>
                    <table class="table table-striped row-border table-hover" id="list_client">
                        <thead>
                        <tr>
                            <th>Número</th>
                            <th>Razón social</th>
                            <th>Apodo</th>
                            <th>Dirección</th>
                            <th>Teléfono</th>
                            <th>CUIT</th>
                            <th>Categoría IVA</th>
                            <th>Cuenta Corriente</th>
                            <th>Actividades</th>
                        </tr>
                        </thead>
                        <tbody>
                        <fmt:setLocale value="es_ES"/>
                        <c:if test="${not empty list}">
                            <c:forEach var="item" items="${list}" varStatus="loop">
                                <tr id="row_${loop.index}" data-row-id="${item.id}">
                                    <td>${item.id}</td>
                                    <td>${item.name}</td>
                                    <td>${item.nickname}</td>
                                    <td>${item.address}</td>
                                    <td>${item.phone}</td>
                                    <td>${item.cuit}</td>
                                    <td>${item.IVAConditionLabel}</td>
                                    <td style="text-align:right;"
                                            ><fmt:formatNumber value="${item.balanceBilling}" currencySymbol="$"
                                                               currencyCode="es" minFractionDigits="2"/></td>
                                    <td style="text-align:right;"><fmt:formatNumber value="${item.balanceActivity}"
                                                                                    currencySymbol="$" currencyCode="es"
                                                                                    minFractionDigits="2"/></td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                        <tfoot>
                        <tr>
                            <th colspan="7" style="text-align:right">Total:</th>
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
        <!-- /.panel -->
    </tiles:putAttribute>
    <tiles:putAttribute name="page-controller">
        <script src="js/controllers/client/listClientPageController.js"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>