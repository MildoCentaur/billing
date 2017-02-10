<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insertDefinition name=".defaultTemplate">
    <tiles:putAttribute name="page">
        <div class="panel panel-primary">
            <div class="panel-heading">
                Formulario de alta de productos
            </div>
            <div class="panel-body">
                <div class="row">
                    <form:form id="addProductForm" method="POST" action="add/product-family" commandName="productForm" role="form">
                        <div class="col-lg-6">
                            <h3>Datos del lazo</h3>
                            <div class="form-group">
                                <div class="checkbox">
                                    <label class="radio-inline"><input type="radio"  name="is-main" value="0" checked/>Es un rollo de tela?</label>
                                    <label class="radio-inline"><input type="radio" name="is-main" value="1"/>Es un
                                        puño?</label>
                                    <label class="radio-inline"><input type="radio"  name="is-main" value="2" />Son un cuellos y Tiras?</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Tipo de Tejido</label>
                                <form:select path="fabric" class="form-control">
                                    <form:option value="0" label="Seleccione un Tejido"></form:option>
                                    <form:options items="${fabrics}" itemValue="id" itemLabel="shortname"></form:options>
                                </form:select>
                                <p class="help-block">Tipo de tejido del producto.</p>
                            </div>

                        </div>
                        <div class="col-lg-6">
                            <h3>Datos del rayado</h3>
                            <div class="form-group">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" id="is-stripe" value="0" />Es un tejido rayado?
                                    </label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Patrón de Rayado</label>
                                <form:select path="stripe" class="form-control stripe" disabled="true">
                                    <form:option value="0" label="Seleccione un Patrón de rayado"></form:option>
                                    <form:options items="${stripes}" itemValue="id" itemLabel="name"></form:options>
                                </form:select>
                                <p class="help-block">Patrón de Rayado</p>
                            </div>


                        </div>

                        <div class="col-lg-12">
                            <label>Precio</label>
                        </div>
                        <div class="col-lg-3">
                            <div class="form-group">
                                <input type="text" id="price-white" class="form-control currency" />
                                <form:hidden path="priceWhite" class="form-control" />
                                <p class="help-block">Precio del blanco.</p>
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <div class="form-group">
                                <input type="text" id="price-light" class="form-control currency" />
                                <form:hidden path="priceLight" class="form-control" />
                                <p class="help-block">Precio del claro.</p>
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <div class="form-group">
                                <input type="text" id="price-dark" class="form-control currency" />
                                <form:hidden path="priceDark" class="form-control" />
                                <p class="help-block">Precio del oscuros.</p>
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <div class="form-group">
                                <input type="text" id="price-special" class="form-control currency" />
                                <form:hidden path="priceSpecial" class="form-control" />
                                <p class="help-block">Precio del especiales.</p>
                            </div>
                        </div>

                    </form:form>
                </div>
                <button type="button" id="btn-new-product" class="btn btn-success" style="float:right;margin-left:10px;">Agregar Producto
                </button>
                <button type="reset" class="btn btn-default" style="float:right;margin-left:10px;">Limpiar</button>
                <!-- /.row (nested) -->
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </tiles:putAttribute>
    <tiles:putAttribute name="page-controller">
        <script src="js/controllers/product/newProductPageController.js"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>