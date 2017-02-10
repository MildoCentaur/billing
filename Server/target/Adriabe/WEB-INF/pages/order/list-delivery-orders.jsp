<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name=".defaultTemplate">
    <tiles:putAttribute name="page">
        <div class="panel panel-primary">
            <div class="panel-heading">
                Listado de pedidos
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="table-responsive">
                    <div class="toolbar"></div>
                    <table class="table table-striped row-border table-hover" id="list-delivery-orders">
                        <thead>
                        <tr>
                            <th></th>
                            <th>Fecha</th>
                            <th>Número</th>
                            <th>Estado</th>
                            <th>Razón social</th>
                            <th>Bultos</th>
                            <th>Total</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${not empty list}">
                            <c:forEach var="item" items="${list}" varStatus="loop">
                                <%--choosing the type of background-color needs to be used--%>
                                <c:choose>
                                    <c:when test="${item.status=='WAITING'}">
                                        <tr class="warning" id="row_${item.id}" data-item-status="${item.status}" data-item-status-class="warning">
                                        <td><i class="text-warning fa fa-exclamation-circle "></i></td>
                                    </c:when>
                                    <c:when test="${item.status=='DELIVERED'}">
                                        <tr class="success" id="row_${item.id}" data-item-status="${item.status}" data-item-status-class="success">
                                        <td><i class="text-success fa fa-truck"></i></td>
                                    </c:when>
                                    <c:when test="${item.status=='CANCELLED'}">
                                        <tr class="danger" id="row_${item.id}" data-item-status="${item.status}" data-item-status-class="cancelled">
                                        <td><i class="text-success fa fa-truck"></i></td>
                                    </c:when>
                                </c:choose>
                                <td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${item.date}"/></td>
                                <td>${item.id}</td>
                                <td>${item.status.label}</td>
                                <td>${item.client.name}</td>
                                <td style="text-align:center;">${item.packagesAmount}</td>
                                <td><label style="float:right;" class="currency">${item.totalAmount}</label></td>
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
        <script src="js/controllers/order/listDeliveryOrderPageController.js"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>

