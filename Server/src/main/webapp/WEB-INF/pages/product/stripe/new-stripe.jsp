<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insertDefinition name=".defaultTemplate">
<tiles:putAttribute name="page">
<div class="panel panel-primary">
<div class="panel-heading">
    Registrar nuevo patrón de rayado
</div>
<div class="panel-body">
<div class="row">
<form:form id="addStripeForm" method="POST" action="add/stripe" commandName="stripe" role="form">
<form:hidden path="id" cssClass="stripe-id"/>
<div class="col-lg-12">
    <h3>Datos del Listado</h3>
</div>
<div class="col-lg-6">

    <div class="form-group">
        <label>Código*</label>
        <form:input class="form-control" path="code" placeholder="Enter text"/>
        <p class="help-block">Código con el cual identificar el listado.</p>
    </div>
    <div class="form-group">
        <label>Nombre*</label>
        <form:input class="form-control" path="name" placeholder="Enter text"/>
        <p class="help-block">Nombre con el cual identificar el listado.</p>
    </div>
</div>
<div class="col-lg-6">
    <div class="form-group">
        <label>Colores</label>
        <form:select path="colors" class="form-control">
            <form:option value="1">1</form:option>
            <form:option value="2">2</form:option>
            <form:option value="3">3</form:option>
            <form:option value="4">4</form:option>
            <form:option value="5">5</form:option>
            <form:option value="6">6</form:option>
        </form:select>
        <p class="help-block">Cantidad de colores.</p>
    </div>
</div>
<!-- /.col-lg-6 (nested) -->
<div class="col-lg-12">
<h3>Patrón de rayado</h3>

<div class="panel panel-default">
<div class="panel-heading">
    <i class="fa fa-list fa-fw"></i>Diseño de nuevo patrón
</div>
<div class="panel-body">
<div class="col-lg-5">
    <!-- /.panel-heading -->
    <h3>Vista previa del patrón</h3>

    <div class="list-group"
         style="overflow-y: auto; overflow-x: hidden;height:514px;">
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
        <a href="#" class="list-group-item uncolored"></a>
    </div>
    <div class="col-lg-6" style="margin:0;padding:0;"></div>
    <div class="col-lg-6" style="margin:0;padding:0;">
        <div class="col-lg-6" style="margin:0;padding:0;">
            <a class="btn btn-block btn-primary" id="btn-zoom-in">
                <i class="fa fa-search-plus"></i>
            </a>
        </div>
        <div class="col-lg-6" style="margin:0;padding:0;">
            <a class="btn btn-block btn-primary" id="btn-zoom-out">
                <i class="fa fa-search-minus"></i>
            </a>
        </div>
    </div>
    <!-- /.list-group -->

</div>
<div class="col-lg-7">
    <h3>Nueva Franja</h3>

    <div class="col-lg-4">
        <div class="form-group">
            <label>Dedo*</label>
            <select id="lineFinger" class="form-control">
                <option value="A">Dedo A</option>
                <option value="B">Dedo B</option>
                <option value="C">Dedo C</option>
                <option value="D">Dedo D</option>
                <option value="E">Dedo E</option>
                <option value="F">Dedo F</option>
            </select>
        </div>
    </div>
    <div class="col-lg-4">
        <div class="form-group">
            <label>Pasadas*</label>
            <input class="form-control" id="lineRows" data-toggle="tooltip" data-placement="bottom" title=""
                   data-original-title="Altura en cantidad de pasadas"/>
        </div>
    </div>
    <div class="col-lg-4">
        <div class="form-group">
            <label style="visibility:hidden;">Pasadas*</label>
            <button type="button" class="btn btn-success" id="addStripeLineButton"><i class="fa fa-plus-circle"></i>
                Agregar Franja
            </button>
        </div>
    </div>
    <div class="col-lg-8">
        <div class="form-group">
            <label>Formula del patrón diseñado</label>

            <div class="form-group input-group" style="margin-bottom: 0px;">
                <form:input path="listingFormula" class="form-control" disabled="disabled"/>
                <span class="input-group-addon admin-lock"><i class="fa fa-lock"></i></span>
            </div>
        </div>
    </div>
    <div class="col-lg-4">
        <div class="form-group">
            <label style="visibility:hidden;">Pasadas*</label>
            <button type="button" class="btn btn-block btn-info" id="refresh"><i class="fa fa-refresh"></i></button>
        </div>
    </div>
    <div class="col-lg-4">
        <div class="form-group">
            <label>Dedo A</label>
            <input id="fingerA" class="form-control pick-a-color" value="#FFF">
        </div>
        <div class="form-group">
            <label>Dedo B</label>
            <input id="fingerB" class="form-control pick-a-color" value="#FFF">
        </div>
    </div>
    <div class="col-lg-4">
        <div class="form-group">
            <label>Dedo C</label>
            <input id="fingerC" class="form-control pick-a-color" value="#FFF">
        </div>
        <div class="form-group">
            <label>Dedo D</label>
            <input id="fingerD" class="form-control pick-a-color" value="#FFF">
        </div>
    </div>
    <div class="col-lg-4">
        <div class="form-group">
            <label>Dedo E</label>
            <input id="fingerE" class="form-control pick-a-color" value="#FFF">
        </div>
        <div class="form-group">
            <label>Dedo F</label>
            <input id="fingerF" class="form-control pick-a-color" value="#FFF">
        </div>
    </div>
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Combinaciones de colores
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="table table-striped table-hover" id="list_combination">
                        <thead>
                        <tr>
                            <th rowspan="2" style="text-align:center;">Nombre</th>
                            <th colspan="6" style="text-align:center;">Dedos</th>
                        </tr>
                        <tr>
                            <th>A</th>
                            <th>B</th>
                            <th>C</th>
                            <th>D</th>
                            <th>E</th>
                            <th>F</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${not empty stripe.combinations}">
                            <c:forEach var="item" items="${stripe.combinations}" varStatus="status">
                                <tr data-finger-A="${item.colorFingerA}"
                                    data-finger-B="${item.colorFingerB}"
                                    data-finger-C="${item.colorFingerC}"
                                    data-finger-D="${item.colorFingerD}"
                                    data-finger-E="${item.colorFingerE}"
                                    data-finger-F="${item.colorFingerF}">
                                    <td>${item.name}</td>
                                    <td>
                                        <div style='height:20px;background-color:#${item.colorFingerA};'></div>
                                    </td>
                                    <td>
                                        <div style='height:20px;background-color:#${item.colorFingerB};'></div>
                                    </td>
                                    <td>
                                        <div style='height:20px;background-color:#${item.colorFingerC};'></div>
                                    </td>
                                    <td>
                                        <div style='height:20px;background-color:#${item.colorFingerD};'></div>
                                    </td>
                                    <td>
                                        <div style='height:20px;background-color:#${item.colorFingerE};'></div>
                                    </td>
                                    <td>
                                        <div style='height:20px;background-color:#${item.colorFingerF};'></div>
                                    </td>
                                    <input type="hidden" id="combinations${status.index}.colorFingerA"
                                           name="combinations[${status.index}].colorFingerA"
                                           class="combination_${status.index}" value="${item.colorFingerA}"/>
                                    <input type="hidden" id="combinations${status.index}.colorFingerB"
                                           name="combinations[${status.index}].colorFingerB"
                                           class="combination_${status.index}" value="${item.colorFingerB}"/>
                                    <input type="hidden" id="combinations${status.index}.colorFingerC"
                                           name="combinations[${status.index}].colorFingerC"
                                           class="combination_${status.index}" value="${item.colorFingerC}"/>
                                    <input type="hidden" id="combinations${status.index}.colorFingerD"
                                           name="combinations[${status.index}].colorFingerD"
                                           class="combination_${status.index}" value="${item.colorFingerD}"/>
                                    <input type="hidden" id="combinations${status.index}.colorFingerE"
                                           name="combinations[${status.index}].colorFingerE"
                                           class="combination_${status.index}" value="${item.colorFingerE}"/>
                                    <input type="hidden" id="combinations${status.index}.colorFingerF"
                                           name="combinations[${status.index}].colorFingerF"
                                           class="combination_${status.index}" value="${item.colorFingerF}"/>

                                    <input type="hidden" id="combinations${status.index}.name"
                                           name="combinations[${status.index}].name"
                                           class="combination_${status.index}" value="${item.name}"/>

                                    <input type="hidden" id="combinations${status.index}.id"
                                           name="combinations[${status.index}].id"
                                           class="combination_${status.index}" value="${item.id}"/>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                </div>
                <div class="col-lg-6">
                    <div class="form-group">
                        <label>Nombre*</label>
                        <input class="form-control" id="combination-name" data-toggle="tooltip" data-placement="bottom"
                               title="" data-original-title="Nombre de la combinacion"/>
                    </div>
                </div>
                <div class="col-lg-6">
                    <label style="visibility:hidden;">hidden</label>
                    <button type="button" class="btn btn-success" id="addStripeCombinationButton">Agregar Combinación
                    </button>
                </div>

                <!-- /.table-responsive -->
            </div>
        </div>
    </div>
</div>
<!-- /.panel -->
</div>
<!-- /.panel-body -->
</div>

<!-- /.panel -->


</div>
</form:form>

<!-- /.col-lg-6 (nested) -->
</div>
<button type="button" class="btn btn-primary" id="addStripeButton">Agregar Rayado</button>
<button type="reset" class="btn btn-default" id="resetStripeButton">Limpiar</button>
<!-- /.row (nested) -->
</div>
<!-- /.panel-body -->
</div>
<!-- /.panel -->
</tiles:putAttribute>
<tiles:putAttribute name="page-controller">
    <script src="js/controllers/product/stripe/newStripePageController.js"></script>
</tiles:putAttribute>
</tiles:insertDefinition>