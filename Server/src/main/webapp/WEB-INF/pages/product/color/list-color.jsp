<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name=".defaultTemplate">
    <tiles:putAttribute name="page">
        <div class="panel panel-primary">
            <div class="panel-heading">
                Listado de Colores
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="table-responsive">
                    <div class="toolbar"></div>
                    <table class="table table-striped row-border table-hover" id="list_color">
                        <thead>
                        <tr>
                            <th>Código</th>
                            <th>Color</th>
                            <th>Nombre</th>
                            <th>Coordenada</th>
                            <th>Tipo</th>
                            <th>Método</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${not empty list}">
                            <c:forEach var="item" items="${list}" varStatus="loop">
                                <tr id="row_${item.id}">
                                    <td>${item.code}</td>
                                    <td>
                                        <div style="background-color:#${item.coordinate}">&nbsp;</div>
                                    </td>
                                    <td>${item.name}</td>
                                    <td>${item.coordinate}</td>
                                    <td>${item.colorType}</td>
                                    <td>${item.method}</td>
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
        <script src="js/controllers/product/color/listColorPageController.js"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>