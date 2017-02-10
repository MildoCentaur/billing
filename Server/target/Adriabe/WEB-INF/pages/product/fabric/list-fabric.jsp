<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name=".defaultTemplate">
    <tiles:putAttribute name="page">
        <div class="panel panel-primary">
            <div class="panel-heading">
                Listado de Tejidos
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="table-responsive">
                    <div class="toolbar"></div>
                    <table class="table table-striped row-border table-hover" id="list_fabric">
                        <thead>

                        <tr>
                            <th rowspan="2">Nº</th>
                            <th rowspan="2">Código</th>
                            <th rowspan="2">Tejido</th>
                            <th rowspan="2">Tipo</th>
                            <th colspan="4" style="text-align:center">Parámetros del lazo</th>
                            <th colspan="3" style="text-align:center">Composición de fibras</th>
                        </tr>
                        <tr>
                            <th>Ancho</th>
                            <th>Rinde</th>
                            <th>Mayas</th>
                            <th>Pasadas</th>
                            <th>Algodón</th>
                            <th>Polyester</th>
                            <th>Elastano</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${not empty list}">
                            <c:forEach var="item" items="${list}" varStatus="loop">
                                <tr id="row_${item.id}">
                                    <td>${loop.count}</td>
                                    <td>${item.code}</td>
                                    <td>${item.longname}</td>
                                    <c:if test="${item.puno}">
                                        <td>Puño</td>
                                    </c:if>
                                    <c:if test="${item.cuello || item.tira }">
                                        <td>CyT</td>
                                    </c:if>
                                    <c:if test="${item.mainFabric}">
                                        <td>Rollo</td>
                                    </c:if>
                                    <td>${item.width}</td>
                                    <td>${item.rinde}</td>
                                    <td>${item.mayas}</td>
                                    <td>${item.pasadas}</td>
                                    <td>${item.cottonPercent}</td>
                                    <td>${item.polyesterPercent}</td>
                                    <td>${item.lycraPercent}</td>
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
        <script src="js/controllers/product/fabric/listFabricPageController.js"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>