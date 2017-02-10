<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name=".defaultTemplate">
    <tiles:putAttribute name="page">
        <div class="panel panel-primary">
            <div class="panel-heading">
                Formulario de alta de Usuarios
            </div>
            <div class="panel-body">
                <div class="row">
                    <form:form id="new-user-form" role="form" commandName="user" action="user/add" method="POST">
                        <form:hidden path="id"/>
                        <div class="col-lg-3"></div>
                        <div class="col-lg-6">
                            <h3>Datos del Usuario</h3>

                            <div class="form-group">
                                <label>Nombre*</label>
                                <form:input path="username" class="form-control" placeholder="Enter text"/>
                                <p class="help-block">Nombre del Usuario.</p>
                            </div>
                            <div class="form-group">
                                <label>Password*</label>
                                <form:password path="password" class="form-control" placeholder="Enter text"/>
                                <p class="help-block">Contraseña de acceso.</p>
                            </div>
                            <div class="form-group">
                                <label>Confirmar Password*</label>
                                <input type="password" id="repassword" class="form-control" value="${user.password}"/>

                                <p class="help-block">Repita contraseña de acceso.</p>
                            </div>
                            <div class="form-group">
                                <label>Mail*</label>
                                <form:input path="email" class="form-control" placeholder="Enter text"/>
                                <p class="help-block">Dirección de correo electrónico.</p>
                            </div>

                        </div>
                        <!-- /.col-lg-6 (nested) -->
                        <div class="col-lg-3"></div>
                    </form:form>

                    <!-- /.col-lg-6 (nested) -->
                </div>
                <button type="button" id="btn-new-user" class="btn btn-success" style="float:right;margin-left:10px;">
                    Agregar Usuario
                </button>
                <button type="reset" class="btn btn-default" style="float:right;margin-left:10px;">Limpiar</button>
                <!-- /.row (nested) -->
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
        </div>

    </tiles:putAttribute>

    <tiles:putAttribute name="page-controller">
        <script src="js/controllers/users/newUserPageController.js"></script>
    </tiles:putAttribute>

</tiles:insertDefinition>