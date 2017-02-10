<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insertDefinition name=".defaultTemplate">
    <tiles:putAttribute name="page">
        <div class="panel panel-primary">
            <div class="panel-heading">
                Formulario de alta de Colores
            </div>
            <div class="panel-body">
                <div class="row">
                    <form:form id="addColorForm" method="POST" action="add/color" commandName="color" role="form">
                        <form:hidden path="id"/>
                        <div class="col-lg-6">
                            <h3>Datos del Color</h3>

                            <div class="form-group">
                                <label>Código*</label>

                                <div class="form-group input-group" style="margin-bottom: 0px;">
                                    <form:input path="code" class="form-control" placeholder="Enter text"/>
                                    <span class="input-group-addon"><i class="fa fa-barcode"></i></span>
                                </div>
                                <p class="help-block">Código del color.</p>
                            </div>
                            <div class="form-group">
                                <label>Nombre*</label>

                                <div class="form-group input-group" style="margin-bottom: 0px;">
                                    <form:input path="name" class="form-control" placeholder="Enter text"/>
                                    <span class="input-group-addon"><i class="fa fa-paint-brush"></i></span>
                                </div>

                                <p class="help-block">Nombre del color.</p>
                            </div>
                            <div class="form-group">
                                <label>Tipo de Color*</label>
                                <form:select class="form-control" path="type" items="${listColorTypes}"
                                             itemLabel="label">

                                </form:select>
                                <p class="help-block">Tipo de colores.</p>
                            </div>
                            <div class="form-group">
                                <label>Método de teñido*</label>
                                <form:select path="method" class="form-control">
                                    <form:option value="Directo">Directo</form:option>
                                    <form:option value="Reactivo">Reactivo</form:option>
                                </form:select>
                                <p class="help-block">Método utilizado en el proceso de teñido.</p>
                            </div>
                        </div>
                        <!-- /.col-lg-6 (nested) -->
                        <div class="col-lg-6">
                            <h3>Colorimetría</h3>

                            <div class="form-group">
                                <label>Coordenada de Color</label>
                                <form:input path="coordinate" class="form-control pick-a-color"
                                            placeholder="Enter text"/>
                                <p class="help-block">Seleccione la coordenada del pantone.</p>
                            </div>
                            <div class="well well-lg" style="padding: 15px 24px;">
                                <h4>Nota!</h4>

                                <p>La coordenada de color seleccionada utiliza el sistema RGB de colorimetría.
                                    La coordenada ingresada hacer referencía al recinto dentro del cual se puede esperar
                                    que se encuentre el color en el tejido.</p>
                            </div>
                            <div class="form-group">
                                <label>Comentarios</label>
                                <form:textarea path="comments" class="form-control" rows="3"/>
                            </div>
                        </div>
                    </form:form>
                    <!-- /.col-lg-6 (nested) -->
                </div>
                <button type="submit" id="addColorButton" class="btn btn-primary">Agregar Color</button>
                <button type="reset" id="resetColorButton" class="btn btn-default">Limpiar</button>
                <!-- /.row (nested) -->
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </tiles:putAttribute>
    <tiles:putAttribute name="page-controller">
        <script src="js/controllers/product/color/newColorPageController.js"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>