<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insertDefinition name=".defaultTemplate">
<tiles:putAttribute name="page">
<div class="panel panel-primary">
<div class="panel-heading">
    Formulario de alta de Tejidos
</div>
<div class="panel-body">
<div class="row">
<form:form id="addFabricForm" method="POST" action="add/fabric" commandName="fabric" role="form">
<form:hidden path="id" cssClass="id-fabric"/>
<div class="col-lg-6">
    <h3>Datos del Lazo</h3>

    <div class="form-group">
        <label>Código*</label>
        <form:input path="code" class="form-control" placeholder="Enter text"/>
        <p class="help-block">Código del lazo.</p>
    </div>
    <div class="form-group">
        <label>Nombre*</label>
        <form:input path="shortname" class="form-control" placeholder="Enter text"/>
        <p class="help-block">Nombre del tipo de lazo.</p>
    </div>
    <div class="form-group">
        <label>Descripción*</label>
        <form:input path="longname" class="form-control" placeholder="Ej: Piquet Algodón Mercerizado"/>
        <p class="help-block">Descripción del tipo de lazo.</p>
    </div>
    <div class="form-group">
        <label>Rinde*</label>
        <form:input path="rinde" class="form-control" placeholder="Enter text"/>
        <p class="help-block">Rinde del tejido.</p>
    </div>
    <div class="form-group">
        <label>Ancho*</label>
        <form:input path="width" class="form-control" placeholder="Enter text"/>
        <p class="help-block">Ancho del tejido.</p>
    </div>
    <div class="form-group">
        <label>Mayas*</label>
        <form:input path="mayas" class="form-control" placeholder="Enter text"/>
        <p class="help-block">Mayas del tejido.</p>
    </div>
        <%--<div class="form-group">--%>
        <%--<label>Columnas*</label>--%>
        <%--<form:input path="columnas" class="form-control" placeholder="Enter text"/>--%>
        <%--<p class="help-block">Columnas del tejido.</p>--%>
        <%--</div>--%>
    <div class="form-group">
        <label>Pasadas*</label>
        <form:input path="pasadas" class="form-control" placeholder="Enter text"/>
        <p class="help-block">Pasadas del tejido.</p>
    </div>

    <div class="form-group">
        <label>Es cuello*</label>
        <label class="radio-inline">
            <form:radiobutton path="cuello" value="true"/>Sí
        </label>
        <label class="radio-inline">
            <form:radiobutton path="cuello" value="false"/>No
        </label>

        <p class="help-block">El tejido es un tipo de cuello o tira.</p>
    </div>
    <div class="form-group">
        <label>Es puño*</label>
        <label class="radio-inline">
            <form:radiobutton path="puno" value="true"/>Sí
        </label>
        <label class="radio-inline">
            <form:radiobutton path="puno" value="false"/>No
        </label>

        <p class="help-block">El tejido es un tipo de puño.</p>
    </div>
</div>
<!-- /.col-lg-6 (nested) -->
<div class="col-lg-6">
    <h3>Datos de la fibra</h3>

    <div class="form-group">
        <label>Cantidad de Fibras*</label>
        <form:input path="fibras" class="form-control" placeholder="Enter text"/>
        <p class="help-block">Cantidad de fibras distintas en el tejido.</p>
    </div>
    <div class="form-group">
        <label>Polyester*</label>
        <form:input path="polyesterPercent" class="form-control" placeholder="Enter text"/>
        <p class="help-block">Porcentaje de Polyester.</p>
    </div>
    <div class="form-group">
        <label>Elastano*</label>
        <form:input path="lycraPercent" class="form-control" placeholder="Enter text"/>
        <p class="help-block">Porcentaje de Elastano (Lycra).</p>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            Fibras presentes en el Tejido
        </div>
        <!-- /.panel-heading -->
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-striped table-hover" id="list_fiber">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Titulo</th>
                        <th>Proveedor</th>
                        <th>Porcentaje</th>
                        <th><p style="padding:0 12px; color: #d9534f;margin:0;">
                            <i class="fa fa-trash-o"></i></p></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${not empty fabric.fibers}">
                        <c:forEach var="fiber" items="${fabric.fibers}" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>${fiber.titulo}</td>
                                <td>${fiber.supplierName}</td>
                                <td>${fiber.percentage}</td>
                                <td><p style="padding:0 12px; color: #d9534f;margin:0;" class="deleteItem">
                                    <i class="fa fa-trash-o"></i></p></td>
                                <input type="hidden" id="fibers${status.index}.percentage"
                                       name="fibers[${status.index}].percentage"
                                       class="fiber_${status.index}"
                                       value="${fiber.percentage}"/>
                                <input type="hidden" id="fibers${status.index}.supplierName"
                                       name="fibers[${status.index}].supplierName"
                                       class="fiber_${status.index}"
                                       value="${fiber.supplierName}"/>

                                <input type="hidden" id="fibers${status.index}.titulo"
                                       name="fibers[${status.index}].titulo"
                                       class="fiber_${status.index}"
                                       value="${fiber.titulo}"/>

                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
            <!-- /.table-responsive -->
            <button type="button" class="btn btn-default" data-toggle="modal"
                    data-target="#addFiberModal" style="margin-top:3px;">
                Agregar Fibras
            </button>
            <!-- Modal -->
            <div class="modal fade" id="addFiberModal" tabindex="-1" role="dialog"
                 aria-labelledby="addFiberModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="addFiberModalLabel">Agregar Fibras</h4>
                        </div>
                        <div class="modal-body" style="height:320px;">
                            <div class="first-column">
                                <div class="form-group">
                                    <label>Titulo*</label>
                                    <input type="text" id="fiberTitle" class="form-control"
                                           placeholder="Entrer text"/>

                                    <p class="help-block">Titulo de la fibra.</p>
                                </div>
                                <div class="form-group">
                                    <label>Proveedor</label>
                                    <input type="text" id="supplierName" class="form-control"
                                           placeholder="Enter text"/>

                                    <p class="help-block">Proveedor del hilado.</p>
                                </div>
                                <div class="form-group">
                                    <label>Porcentaje*</label>
                                    <input type="text" id="percentage" class="form-control"
                                           placeholder="Enter text"/>

                                    <p class="help-block">Porcentaje de la fibra.</p>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close
                            </button>
                            <button type="button" class="btn btn-primary" id="addFiberButton">Agregar
                                Fibra
                            </button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->
        </div>
    </div>
    <!-- /.panel -->
    <h3>Datos de la Máquina</h3>

    <div class="form-group">
        <label>Galga*</label>
        <form:input path="galga" class="form-control" placeholder="Enter text"/>
        <p class="help-block">Galga de la Máquina.</p>
    </div>
    <div class="form-group">
        <label>Diámetro</label>
        <form:input path="diametro" class="form-control" placeholder="Enter text"/>
        <p class="help-block">Diámetro de la máquina (en centimetros).</p>
    </div>
    <div class="form-group">
        <label>Comentarios</label>
        <form:textarea path="comments" class="form-control" rows="3"/>
    </div>

</div>

</form:form>

<!-- /.col-lg-6 (nested) -->
</div>
<button type="submit" class="btn btn-primary" id="addFabricButton">Agregar Tejido
</button>
<button type="reset" class="btn btn-default" id="resetFabricButton">Limpiar</button>
<!-- /.row (nested) -->
</div>
<!-- /.panel-body -->
</div>
<!-- /.panel -->
</tiles:putAttribute>
<tiles:putAttribute name="page-controller">
    <script src="js/controllers/product/fabric/newFabricPageController.js"></script>
</tiles:putAttribute>
</tiles:insertDefinition>