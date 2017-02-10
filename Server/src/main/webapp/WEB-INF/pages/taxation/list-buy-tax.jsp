<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name=".defaultTemplate">
    <tiles:putAttribute name="page">
        <div class="panel panel-primary">
            <div class="panel-heading">
                Listado IVA Compras
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">

                <div class="row">
                    <div class="col-lg-3">
                        <div class="form-group">
                            <label>Periodo desde</label>

                            <div class="input-group date form_date " data-date="${dateFrom}"
                                 data-date-format="dd/mm/yyyy"
                                 data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                <input type="text" value="${dateFrom}" id="date-from" class="form-control" size="16"
                                       placeholder=""/>
                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="form-group">
                            <label>Periodo hasta</label>

                            <div class="form-group input-group" style="margin-bottom: 0px;">
                                <input type="text" value="${dateTo}" id="date-to" class="form-control"/>
                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-2">
                        <label style="width:100%;">&nbsp;</label>
                        <button type="button" id="btn-search" class="btn btn-success" style="float:right;">
                            Buscar
                        </button>
                    </div>
                </div>


                <div class="table-responsive">
                    <div class="toolbar"></div>
                    <table class="table table-striped row-border table-hover" id="list-buy-tax">
                        <thead>
                        <tr>
                            <th rowspan="2">Nº</th>
                            <th rowspan="2">Nº Compr.</th>
                            <th rowspan="2">Tipo Compr.</th>
                            <th rowspan="2" id="razonSocial">Razón Social</th>
                            <th rowspan="2">CUIT</th>
                            <th rowspan="2">Emitida</th>
                            <th rowspan="2">Ingresada</th>
                            <th rowspan="2">Gravado</th>
                            <th rowspan="2">No Gravado</th>
                            <th colspan="2" style="text-align:center;">Percepciones</th>
                            <th colspan="3" style="text-align:center;">IVA</th>
                            <th rowspan="2">Total</th>
                        </tr>
                        <tr>
                            <th>3%</th>
                            <th>II.BB</th>
                            <th>10.5%</th>
                            <th>21%</th>
                            <th>27%</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th colspan="6"></th>
                            <th>Totales:</th>
                            <th id="total_subtotal">${total_subtotal}</th>
                            <th id="total_subtotal_exento">${total_subtotal_exento}</th>
                            <th id="total_iva3">${total_iva3}</th>
                            <th id="total_iva_rr_ii">${total_iva_rrii}</th>
                            <th id="total_iva10_5">${total_iva10_5}</th>
                            <th id="total_iva21">${total_iva21}</th>
                            <th id="total_iva27">${total_iva27}</th>
                            <th id="total_total">${total_total}</th>
                        </tr>

                        </tfoot>
                        <tbody>
                        <c:if test="${not empty purchases}">
                            <c:forEach var="item" items="${purchases}" varStatus="loop">
                                <tr data-supplier-bill-id="${item.id}">
                                    <td>${loop.index + 1}</td>
                                    <td>${item.billNumber}</td>
                                    <td>${item.ivaCategory}</td>
                                    <td>${item.supplier.name}</td>
                                    <td>${item.supplier.cuit}</td>
                                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${item.issueDate}"/></td>
                                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${item.registerDate}"/></td>
                                    <td><fmt:formatNumber currencySymbol="$" maxFractionDigits="2"
                                                          value="${item.subtotal}"/></td>
                                    <td><fmt:formatNumber currencySymbol="$" maxFractionDigits="2"
                                                          value="${item.total}"/></td>
                                    <!-- Percepciones -->
                                    <td><fmt:formatNumber currencySymbol="$" maxFractionDigits="2"
                                                          value="${item.ivaPercent3}"/></td>
                                    <td><fmt:formatNumber currencySymbol="$" maxFractionDigits="2"
                                                          value="${item.ivaTaxBBII}"/></td>
                                    <!-- Retenciones -->
                                    <td><fmt:formatNumber currencySymbol="$" maxFractionDigits="2"
                                                          value="${item.ivaPercent10_5}"/></td>
                                    <td><fmt:formatNumber currencySymbol="$" maxFractionDigits="2"
                                                          value="${item.ivaPercent21}"/></td>
                                    <td><fmt:formatNumber currencySymbol="$" maxFractionDigits="2"
                                                          value="${item.ivaPercent27}"/></td>
                                    <!-- IVA TOTAL -->
                                    <td><fmt:formatNumber currencySymbol="$" maxFractionDigits="2"
                                                          value="${item.total}"/></td>
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
        <script src="js/controllers/taxation/listBuyTaxationPageController.js"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>