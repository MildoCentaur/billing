<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name=".defaultTemplate">
    <tiles:putAttribute name="page">
        <div class="panel panel-primary">
            <div class="panel-heading">
                Listado de Usuarios
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">

                <div class="table-responsive">
                    <div class="toolbar"></div>
                    <table class="table table-striped table-hover" id="list-user-permission">
                        <thead>
                        <tr>
                            <th rowspan="2" style="text-align:center;">Usuarios</th>
                            <th colspan="7" style="text-align:center;">Roles</th>
                        </tr>
                        <tr>
                            <c:forEach var="role" items="${roles}" varStatus="loop">
                                <th>${role.name}</th>
                            </c:forEach>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${users}" varStatus="loop">
                            <tr>
                                <td><label data-user-id="${user.id}" class="user-permission">${user.username}</label>
                                </td>
                                <c:forEach var="role" items="${roles}" varStatus="loop">
                                    <td>
                                        <div class="form-group col-lg-12">
                                            <c:forEach var="permission" items="${role.permissions}" varStatus="loop">
                                                <div class="checkbox">
                                                    <label>
                                                        <c:if test="${user.hasPermission(permission.id)}">
                                                            <input type="checkbox" class="permission" checked="checked"
                                                                   value="${permission.name}"
                                                                   data-permission-name="${permission.name}"
                                                                   data-permission-id="${permission.id}">${permission.name}
                                                            <br/>
                                                        </c:if>
                                                        <c:if test="${not user.hasPermission(permission.id)}">
                                                            <input type="checkbox" class="permission"
                                                                   value="${permission.name}"
                                                                   data-permission-name="${permission.name}"
                                                                   data-permission-id="${permission.id}">${permission.name}
                                                            <br/>
                                                        </c:if>
                                                    </label>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- /.table-responsive -->
                <button type="button" id="btn-assign-permission" class="btn btn-success"
                        style="float:right;margin-left:10px;">Aceptar
                </button>
                <button type="reset" class="btn btn-default" style="float:right;margin-left:10px;">Limpiar</button>

            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </tiles:putAttribute>

    <tiles:putAttribute name="page-controller">
        <script src="js/controllers/users/assignPermissionUserPageController.js"></script>
    </tiles:putAttribute>

</tiles:insertDefinition>