<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name=".defaultTemplate">
    <tiles:putAttribute name="page">
        <fmt:setLocale value="ES_es" scope="session"/>
        <div class="panel panel-primary">
            <div class="panel-heading">
                Ver estado del pedido
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label>Señores</label>

                            <div class="form-group input-group" style="margin-bottom: 0px;">
                                <input type="text" id="txt-client-name" disabled="disabled" value="${order.client.name}"
                                       class="form-control" placeholder="Razón Social"/>
                                <input type="hidden" id="client-id" value="${order.client.id}" class="client-id"/>

                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                            </div>
                            <p class="help-block">Razón social, Nombre del contacto</p>
                        </div>
                    </div>
                    <div class="col-lg-5" style="margin-top:40px;">

                    </div>
                </div>
                <div class="table-responsive">
                    <div class="toolbar"></div>
                    <table class="table table-striped row-border table-hover" id="list-item-order">
                        <thead>
                        <tr>
                            <th></th>
                            <th>Producto</th>
                            <th>Kilos</th>
                            <th>Precio</th>
                            <th>Total</th>
                        </tr>
                        </thead>
                        <tbody>


                        <c:if test="${not empty order.items}">
                            <c:forEach var="item" items="${order.items}" varStatus="loop">
                                <tr class="group">
                                    <td>${item.ratio}</td>
                                    <td>${item.product.productFamilyName} ${item.product.colorName}</td>
                                    <td><fmt:formatNumber type="number" minFractionDigits="2" value="${item.weight}"/>
                                        Kg.
                                    </td>
                                    <td><fmt:formatNumber type="currency" currencySymbol="$" minFractionDigits="2"
                                                          value="${item.price}"/></td>
                                    <td><fmt:formatNumber type="currency" currencySymbol="$" minFractionDigits="2"
                                                          value="${item.itemAmount}"/></td>
                                </tr>
                                <c:if test="${ not empty item.packages}">
                                    <c:forEach var="packageItem" items="${item.packages}" varStatus="inner">
                                        <tr>
                                            <td>${inner.index + 1}</td>
                                            <td>${item.product.productName}</td>
                                            <td><fmt:formatNumber type="number" minFractionDigits="2"
                                                                  value="${packageItem.weight}"/> Kg.
                                            </td>
                                            <td>${packageItem.status.label}</td>
                                            <td></td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                <c:forEach var="accesory" items="${item.accesories}" varStatus="accesoriesLoop">
                                    <tr class="group">
                                        <td>${accesory.ratio}</td>
                                        <td>${accesory.product.productFamilyName} ${accesory.product.colorName}</td>
                                        <td><fmt:formatNumber type="number" minFractionDigits="2"
                                                              value="${accesory.weight}"/>
                                            Kg.
                                        </td>
                                        <td><fmt:formatNumber type="currency" currencySymbol="$" minFractionDigits="2"
                                                              value="${accesory.price}"/></td>
                                        <td><fmt:formatNumber type="currency" currencySymbol="$" minFractionDigits="2"
                                                              value="${accesory.itemAmount}"/></td>
                                    </tr>
                                    <c:if test="${ not empty accesory.packages}">
                                        <c:forEach var="accesoryPackageItem" items="${accesory.packages}"
                                                   varStatus="accesoriesInner">
                                            <tr>
                                                <td>${accesoriesInner.index + 1}</td>
                                                <td>${accesory.product.productName}</td>
                                                <td><fmt:formatNumber type="number" minFractionDigits="2"
                                                                      value="${accesoryPackageItem.weight}"/> Kg.
                                                </td>
                                                <td>${accesoryPackageItem.status.label}</td>
                                                <td></td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                        </c:if>
                        </tbody>
                        <tfoot>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
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
        <script src="js/controllers/order/showOrderPageController.js"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>

