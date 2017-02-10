<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tiles:insertDefinition name=".defaultTemplate">
    <tiles:putAttribute name="page">
        <div class="panel panel-primary">
            <div class="panel-heading">
                Listado de Productos
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="table-responsive">
                    <div class="toolbar"></div>
                    <table class="table table-striped row-border table-hover" id="list_product">
                        <thead>
                        <tr style="text-align: center;">
                            <th>Nº</th>
                            <th>Tejido</th>
                            <th>Stock</th>
                            <th>Precio</th>
                            <th>Tipo de Color</th>
                            <th>Grouping</th>
                        </tr>
                        </thead>
                        <tbody>
                        <fmt:setLocale value="es_ES"/>
                        <c:if test="${not empty list}">
                            <c:forEach var="item" items="${list}" varStatus="loop">
                                <tr id="row_${item.id}">
                                    <td>${item.id}</td>
                                    <td>${item.productName}</td>
                                    <td>${item.stock}</td>
                                    <td><fmt:formatNumber type="currency" currencySymbol="$" minFractionDigits="2"
                                                          value="${item.price}"/></td>
                                    <td>${item.colorTypeLabel}</td>
                                    <td>${item.fabric.code} ${item.fabric.shortname}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                </div>
                    <%--<sec:authorize  access="hasRole('EDIT_STOCK')">--%>
                <div class="row">
                    <div class="col-lg-6">
                        <button type="button" class="btn btn-primary" id="btn-edit-stock" data-toggle="modal"
                                data-target="#edit-stock-modal" style="margin-top:20px;float: right;">
                            Actualizar Stock
                        </button>
                    </div>

                    <!-- Modal -->
                    <div class="modal fade" id="edit-stock-modal" tabindex="-1" role="dialog"
                         aria-labelledby="edit-stock-modal-label" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-hidden="true">&times;</button>
                                    <h4 class="modal-title" id="edit-stock-modal-label">Ingrese el producto a
                                        actualizar</h4>
                                </div>
                                <div class="modal-body">
                                    <fieldset id="fieldset-main">
                                        <div class="first-column">
                                            <div class="form-group">
                                                <label>Barcode*</label>

                                                <div class="form-group input-group" style="margin-bottom: 0px;">
                                                    <input type="text" id="barcode" class="form-control"
                                                           placeholder=""/>
                                                    <span class="input-group-addon"><i class="fa fa-barcode"></i></span>
                                                </div>
                                                <p class="help-block">Código de barras.</p>
                                            </div>
                                        </div>
                                        <div class="second-column">
                                            <div class="form-group">
                                                <label>Cantidad*</label>
                                                <input type="text" id="amount" class="form-control"
                                                       placeholder=""/>

                                                <p class="help-block">Cantidad a incrementar el stock.</p>
                                            </div>

                                        </div>
                                    </fieldset>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close
                                    </button>
                                    <button type="button" class="btn btn-success" id="btn-confirm-stock">Editar
                                    </button>
                                </div>
                            </div>
                            <!-- /.modal-content -->
                        </div>
                        <!-- /.modal-dialog -->
                    </div>
                    <!-- /.modal -->
                </div>
                    <%--</sec:authorize>--%>
            </div>
            <!-- /.panel -->
            <!-- /.table-responsive -->
        </div>
        <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </tiles:putAttribute>
    <tiles:putAttribute name="page-controller">
        <script src="js/controllers/product/listProductPageController.js"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>