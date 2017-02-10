<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<tiles:insertDefinition name=".defaultTemplate">
    <tiles:putAttribute name="page">
        <style>
            input{
                width:100%;
            }
            th{
                text-align: center;
            }
        </style>
        <div class="panel panel-primary">
            <div class="panel-heading">
                Listado de Precios General<span style="float:right;"> Actualizado al <span class="text-danger">${date}</span></span>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="table-responsive">
                    <div class="toolbar"></div>
                    <form:form id="newListPriceForm" method="POST" action="new/list-price" commandName="listPriceForm" role="form">
                        <table class="table table-striped row-border table-hover" id="list_product">
                            <thead>
                            <tr>
                                <th rowspan="2">Nº</th>
                                <th rowspan="2" >Tejido</th>
                                <th colspan="2">Blanco</th>
                                <th colspan="2">Claro</th>
                                <th colspan="2">Oscuro</th>
                                <th colspan="2">Especial</th>
                            </tr>
                            <tr>
                                <th >Nuevo</th>
                                <th >Anterior</th>
                                <th >Nuevo</th>
                                <th >Anterior</th>
                                <th >Nuevo</th>
                                <th >Anterior</th>
                                <th >Nuevo</th>
                                <th >Anterior</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${not empty list}">
                                <c:forEach var="item" items="${list}" varStatus="loop">
                                    <tr >
                                        <td>${loop.count}</td>
                                        <td>
                                                ${item.name}
                                            <input type="hidden" id="list${loop.count-1}.fabric"
                                                   name="list[${loop.count-1}].fabric" value="${item.fabric}"/>
                                            <input type="hidden" id="list${loop.count-1}.stripe"
                                                   name="list[${loop.count-1}].stripe" value="${item.stripe}"/>
                                        </td>
                                        <td>
                                            <input type="text" id="list${loop.count-1}.priceWhite"
                                                   name="list[${loop.count-1}].priceWhite" class="currency"/>
                                        </td>
                                        <td><span class="formatCurrencyFromDouble">${item.priceWhite}</span></td>

                                        <td><input type="text" id="list${loop.count-1}.priceLight"
                                                   name="list[${loop.count-1}].priceLight" class="currency"/>
                                        </td>
                                        <td><span class="formatCurrencyFromDouble">${item.priceLight}</span></td>

                                        <td><input type="text" id="list${loop.count-1}.priceDark"
                                                   name="list[${loop.count-1}].priceDark" class="currency"/>
                                        </td>
                                        <td><span class="formatCurrencyFromDouble">${item.priceDark}</span></td>
                                        <td><input type="text" id="list${loop.count-1}.priceSpecial"
                                                   name="list[${loop.count-1}].priceSpecial" class="currency"/>
                                        </td>
                                        <td><span class="formatCurrencyFromDouble">${item.priceSpecial}</span></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>

                        </table>
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label>Ingrese fórmula:</label>

                                    <div class="form-group">
                                        <input type="text" id="formula" style="width:75%;padding:4px;"/>
                                        <button type="button" class="btn btn-primary" id="btn-formula"><i class=" fa fa-refresh"></i></button>
                                    </div>
                                    <p class="help-block">Para usar el valor anterior en la fórmula, debe usar la letra
                                        'p'.</p>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <button type="button" class="btn btn-success" id="btn-new-list-price" style="margin-top:20px;float: right;">
                                    Actualizar Lista de Precios
                                </button>
                            </div>
                        </div>
                    </form:form>
                </div>
                <!-- /.table-responsive -->

            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </tiles:putAttribute>
    <tiles:putAttribute name="page-controller">
        <script src="js/controllers/product/newListPricesPageController.js"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>