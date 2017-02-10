<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name=".defaultTemplate">
    <tiles:putAttribute name="page">
        <div class="panel panel-primary">
            <div class="panel-heading">
                Listado de Precios General<span style="float:right;"> Actualizado al <span
                    class="text-danger">${date}</span></span>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="table-responsive">
                    <div class="toolbar"></div>
                    <table class="table table-striped row-border table-hover" id="list_product">
                        <thead>
                        <tr>
                            <th>Nº</th>
                            <th>Tejido</th>
                            <th>Blanco</th>
                            <th>Claro</th>
                            <th>Oscuro</th>
                            <th>Especial</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${not empty list}">
                            <c:forEach var="item" items="${list}" varStatus="loop">
                                <tr>
                                    <td>${loop.count}</td>
                                    <td>${item.name}</td>
                                    <td><span class="formatCurrencyFromDouble">${item.priceWhite}</span></td>
                                    <td><span class="formatCurrencyFromDouble">${item.priceLight}</span></td>
                                    <td><span class="formatCurrencyFromDouble">${item.priceDark}</span></td>
                                    <td><span class="formatCurrencyFromDouble">${item.priceSpecial}</span></td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                </div>
                <!-- /.table-responsive -->

            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </tiles:putAttribute>
    <tiles:putAttribute name="page-controller">
        <script src="js/controllers/product/listPricesPageController.js"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>