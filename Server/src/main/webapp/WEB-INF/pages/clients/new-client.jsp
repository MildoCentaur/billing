<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insertDefinition name=".defaultTemplate">
<tiles:putAttribute name="page">
<div class="panel panel-primary">
<div class="panel-heading">
    Formulario de alta de clientes
</div>
<div class="panel-body">
<div class="row">
<form:form id="addClientForm" method="POST" action="add/client" commandName="client" role="form">
<div class="col-lg-6">
    <h3>Datos de la empresa</h3>

    <div class="form-group">
        <label>Nombre*</label>

        <div class="form-group input-group" style="margin-bottom: 0px;">
            <form:input path="name" class="form-control" placeholder="Enter text"/>
            <span class="input-group-addon"><i class="fa fa-user"></i></span>
        </div>
        <p class="help-block">Nombre o razón social de la empresa.</p>
    </div>
    <div class="form-group">
        <label>Nombre de fantasía</label>

        <div class="form-group input-group" style="margin-bottom: 0px;">
            <form:input path="nickname" class="form-control" placeholder="Enter text"/>
            <span class="input-group-addon"><i class="fa fa-users"></i></span>
        </div>
        <p class="help-block">Nombre de fantasía o apodo de la empresa.</p>
    </div>
    <div class="form-group">
        <label>Domicilio*</label>

        <div class="form-group input-group" style="margin-bottom: 0px;">
            <form:input path="address" class="form-control" placeholder="Enter text"/>
            <span class="input-group-addon"><i class="fa fa-map-marker"></i></span>
        </div>
        <p class="help-block">Dirección/Domicilio legal de la empresa.</p>
    </div>
    <div class="form-group">
        <label>Domicilio entrega</label>

        <div class="form-group input-group" style="margin-bottom: 0px;">
            <form:input path="addressDelivery" class="form-control" placeholder="Enter text"/>
            <span class="input-group-addon"><i class="fa fa-map-marker"></i></span>
        </div>
        <p class="help-block">Dirección/Domicilio de entrega.</p>
    </div>
    <div class="form-group">
        <label>Localidad*</label>

        <div class="form-group input-group" style="margin-bottom: 0px;">
            <form:input path="localidad" class="form-control" placeholder="Enter text"/>
            <span class="input-group-addon"><i class="fa fa-map-marker"></i></span>
        </div>

        <p class="help-block">Localidad de la empresa.</p>
    </div>
    <div class="form-group">
        <label>Provincia*</label>

        <div class="form-group input-group" style="margin-bottom: 0px;">
            <form:input path="state" class="form-control" placeholder="Enter text"/>
            <span class="input-group-addon"><i class="fa fa-globe"></i></span>
        </div>
        <p class="help-block">Provincia en la que está radicada empresa.</p>
    </div>
    <div class="form-group">
        <label>Pais*</label>

        <div class="form-group input-group" style="margin-bottom: 0px;">
            <form:input path="country" class="form-control" placeholder="Argentina"/>
            <span class="input-group-addon"><i class="fa fa-globe"></i></span>
        </div>
        <p class="help-block">Pais en el que está radicada la empresa.</p>
    </div>
    <div class="form-group">
        <label>Código Postal*</label>

        <div class="form-group input-group" style="margin-bottom: 0px;">
            <form:input path="postalCode" class="form-control" placeholder="Enter text"/>
            <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
        </div>
        <p class="help-block">Código Postal de la empresa.</p>
    </div>
    <div class="form-group">
        <label>Teléfono*</label>

        <div class="form-group input-group" style="margin-bottom: 0px;">
            <form:input path="phone" class="form-control" placeholder="Enter text"/>
            <span class="input-group-addon"><i class="fa fa-phone"></i></span>
        </div>
        <p class="help-block">Teléfono de la empresa.</p>
    </div>
    <div class="form-group">
        <label>Nextel</label>

        <div class="form-group input-group" style="margin-bottom: 0px;">
            <form:input path="nextel" class="form-control" placeholder="Enter text"/>
            <span class="input-group-addon"><i class="fa fa-mobile-phone" style="padding:0 4px;"></i></span>
        </div>
        <p class="help-block">Nextel de la empresa.</p>
    </div>
    <div class="form-group">
        <label>Fax</label>

        <div class="form-group input-group" style="margin-bottom: 0px;">
            <form:input path="fax" class="form-control" placeholder="Enter text"/>
            <span class="input-group-addon"><i class="fa fa-fax"></i></span>
        </div>
        <p class="help-block">Fax de la empresa.</p>
    </div>
    <div class="form-group">
        <label>Email</label>

        <div class="form-group input-group" style="margin-bottom: 0px">
            <form:input path="email" type="text" class="form-control" placeholder="Enter text"/>
            <span class="input-group-addon">@</span>
        </div>
        <p class="help-block">Dirección de correo electronico de la empresa.</p>
    </div>
</div>
<!-- /.col-lg-6 (nested) -->
<div class="col-lg-6">
<h3>Datos de la cuenta</h3>

<div class="form-group">
    <label>Condición IVA*</label>
    <form:select path="IVACondition" class="form-control" items="${conditions}" itemLabel="label"/>
    <p class="help-block">Condición de IVA de la empresa.</p>
</div>
<div class="form-group">
    <label>CUIT*</label>

    <div class="form-group input-group" style="margin-bottom: 0px;">
        <form:input path="cuit" class="form-control" placeholder="Enter text"/>
        <span class="input-group-addon"><i class="fa fa-bank"></i></span>
    </div>
    <p class="help-block">Número de CUIT de la empresa.</p>
</div>
<div class="form-group">
    <label>Monto Crédito*</label>

    <div class="form-group input-group" style="margin-bottom: 0px;">
        <form:input path="credit" class="form-control currency" placeholder="0.00 $"/>
        <span class="input-group-addon"><i class="fa fa-money"></i></span>
    </div>
    <p class="help-block">Maximo valor de crédito que se le puede dar.</p>
</div>
<div class="form-group">
    <label>Monto Crédito Cheques*</label>

    <div class="form-group input-group" style="margin-bottom: 0px;">
        <form:input path="creditInCheques" class="form-control currency" placeholder="0.00 $"/>
        <span class="input-group-addon"><i class="fa fa-money"></i></span>
    </div>
    <p class="help-block">Maximo valor de crédito en cheques que se le puede dar.</p>
</div>
    <%--<div class="form-group">--%>
    <%--<label>Lista de precios*</label>--%>
    <%--<select id="listPrices" class="form-control" >--%>
    <%--<c:if test="${not empty listPricesNames}">--%>
    <%--<c:forEach var="item" items="${listPricesNames.prices}" varStatus="status">--%>
    <%--<option value="${item.id}" >${item.name}</option>--%>
    <%--</c:forEach>--%>
    <%--</c:if>--%>
    <%--</select>--%>
    <%--<p class="help-block">Lista de precios para el cliente.</p>--%>
    <%--</div>--%>
<div class="form-group">
    <label>Cuenta Corriente*</label>

    <div class="form-group input-group" style="margin-bottom: 0px;">
        <form:input path="balanceBilling" class="form-control currency" placeholder="0.00 $"/>
        <span class="input-group-addon"><i class="fa fa-money"></i></span>
    </div>
    <p class="help-block">Saldo actual en la cuenta corriente.</p>
</div>
<div class="form-group">
    <label>Saldo Actividades*</label>

    <div class="form-group input-group" style="margin-bottom: 0px;">
        <form:input path="balanceActivity" class="form-control currency" placeholder="0.00 $"/>
        <span class="input-group-addon"><i class="fa fa-money"></i></span>
    </div>
    <p class="help-block">Maximo valor de crédito que se le puede dar.</p>
</div>
<div class="form-group">
    <label>Tipo de cliente</label>
    <label class="radio-inline"><form:radiobutton path="clientType" value="0"/>M</label>
    <label class="radio-inline"><form:radiobutton path="clientType" value="1"/>5</label>
    <label class="radio-inline"><form:radiobutton path="clientType" value="2"/>1</label>
</div>
<div class="panel panel-default">
    <div class="panel-heading">
        Puestos en ferias
    </div>
    <!-- /.panel-heading -->
    <div class="panel-body">
        <div class="table-responsive">
            <table id="stores" class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>Feria</th>
                    <th>Pasillo</th>
                    <th>Puesto</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${not empty client.stores}">
                    <c:forEach var="store" items="${client.stores}" varStatus="status">
                        <tr>
                            <td>${store.feria}</td>
                            <td>${store.pasillo}</td>
                            <td>${store.puesto}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </div>
        <!-- /.table-responsive -->
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addStoreModal"
                style="margin-top:3px;">
            Agregar Puestos
        </button>
        <!-- Modal -->
        <div class="modal fade" id="addStoreModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">Agregar Puesto</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Feria*</label>
                            <input type="text" id="storeFeria" class="form-control" placeholder="Entrer text"/>

                            <p class="help-block">Feria en la que esta el puesto.</p>
                        </div>
                        <div class="form-group">
                            <label>Pasillo</label>
                            <input type="text" id="storePasillo" class="form-control" placeholder="Enter text"/>

                            <p class="help-block">Pasillo de la feria en el que esté.</p>
                        </div>
                        <div class="form-group">
                            <label>Puesto Nº*</label>
                            <input type="text" id="storePuesto" class="form-control" placeholder="Enter text"/>

                            <p class="help-block">Número de puesto.</p>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        <button type="button" id="addStoreButton" class="btn btn-primary">Agregar Puesto</button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->
    </div>
    <!-- /.panel-body -->
</div>
<!-- /.panel -->
<div class="panel panel-default">
    <div class="panel-heading">
        Contactos
    </div>
    <!-- /.panel-heading -->
    <div class="panel-body">
        <div class="table-responsive">
            <table id="contacts" class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Alias</th>
                    <th>Puesto</th>
                    <th>Teléfono</th>
                    <th>Nextel</th>
                    <th>Email</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${not empty client.contacts}">
                    <c:forEach var="contact" items="${client.contacts}" varStatus="status">
                        <tr>
                            <td>${contact.name}</td>
                            <td>${contact.nickname}</td>
                            <td>${contact.office}</td>
                            <td>${contact.phone}</td>
                            <td>${contact.nextel}</td>
                            <td>${contact.email}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </div>
        <!-- /.table-responsive -->
        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addContactsModal"
                style="margin-top:3px;">
            Agregar Contactos
        </button>
        <!-- Modal -->
        <div class="modal fade" id="addContactsModal" tabindex="-1" role="dialog"
             aria-labelledby="addContactsModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="addContactsModalLabel">Agregar Contactos</h4>
                    </div>
                    <div class="modal-body" style="height:320px;">
                        <div class="first-column">
                            <div class="form-group">
                                <label>Nombre*</label>
                                <input type="text" id="contactName" class="form-control" placeholder="Entrer text"/>

                                <p class="help-block">Nombre del contacto con la empresa.</p>
                            </div>
                            <div class="form-group">
                                <label>Alias</label>
                                <input type="text" id="contactAlias" class="form-control" placeholder="Enter text"/>

                                <p class="help-block">Alias del contacto con la empresa.</p>
                            </div>
                            <div class="form-group">
                                <label>Teléfono*</label>

                                <div class="form-group input-group" style="margin-bottom: 0px;">
                                    <input type="text" id="contactPhone" class="form-control" placeholder="Enter text"/>
                                    <span class="input-group-addon"><i class="fa fa-mobile-phone"></i></span>
                                </div>
                                <p class="help-block">Teléfono personal del contacto.</p>
                            </div>
                        </div>
                        <div class="second-column">
                            <div class="form-group">
                                <label>Nextel</label>

                                <div class="form-group input-group" style="margin-bottom: 0px;">
                                    <input type="text" id="contactNextel" class="form-control"
                                           placeholder="Enter text"/>
                                    <span class="input-group-addon"><i class="fa fa-mobile-phone"></i></span>
                                </div>
                                <p class="help-block">Nextel personal del contacto.</p>
                            </div>
                            <div class="form-group">
                                <label>Email</label>

                                <div class="form-group input-group" style="margin-bottom: 0px;">
                                    <input type="text" id="contactEmail" class="form-control" placeholder="Enter text"/>
                                    <span class="input-group-addon">@</span>
                                </div>
                                <p class="help-block">Email personal del contacto.</p>
                            </div>
                            <div class="form-group">
                                <label>Cargo*</label>

                                <div class="form-group input-group" style="margin-bottom: 0px;">
                                    <input type="text" id="contactOffice" class="form-control"
                                           placeholder="Enter text"/>
                                    <span class="input-group-addon"><i class="fa fa-briefcase"></i></span>
                                </div>
                                <p class="help-block">Cargo en el que se desempeña.</p>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" id="addContactButton">Agregar Contacto</button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->
    </div>
    <!-- /.panel-body -->
</div>
<!-- /.panel -->
<div class="form-group">
    <label>Comentarios</label>
    <form:textarea class="form-control" rows="3" path="comments"></form:textarea>
</div>
<button type="button" id="addClientButton" class="btn btn-primary">${actionButtonLabel}</button>
<button type="reset" id="cleanAddClientForm" class="btn btn-default">Limpiar</button>
</div>
<!-- /.col-lg-6 (nested) -->
<form:hidden path="id"/>
<c:if test="${not empty client.contacts}">
    <c:forEach var="contact" items="${client.contacts}" varStatus="status">
        <input type="hidden" id="contacts${status.count -1}.phone" name='contacts[${status.count -1}].phone'
               value="${contact.phone}"/>
        <input type="hidden" id='contacts${status.count -1}.nextel' name='contacts[${status.count -1}].nextel'
               value="${contact.nextel}"/>
        <input type="hidden" id='contacts${status.count -1}.email' name='contacts[${status.count -1}].email'
               value="${contact.email}"/>
        <input type="hidden" id='contacts${status.count -1}.name' name='contacts[${status.count -1}].name'
               value="${contact.name}"/>
        <input type="hidden" id='contacts${status.count -1}.nickname' name='contacts[${status.count -1}].nickname'
               value="${contact.nickname}"/>
        <input type="hidden" id='contacts${status.count -1}.office' name='contacts[${status.count -1}].office'
               value="${contact.office}"/>
    </c:forEach>
</c:if>
<c:if test="${not empty client.stores}">
    <c:forEach var="store" items="${companyForm.stores}" varStatus="status">
        <input type="hidden" id="stores${status.count -1}.feria" name='stores[${status.count -1}].feria'
               value="${store.feria}" class="puestoHiddenData"/>
        <input type="hidden" id='stores${status.count -1}.pasillo' name='stores[${status.count -1}].pasillo'
               value="${store.pasillo}"/>
        <input type="hidden" id='stores${status.count -1}.puesto' name='stores[${status.count -1}].puesto'
               value="${store.puesto}"/>
    </c:forEach>
</c:if>
</form:form>
</div>
<!-- /.row (nested) -->
</div>
<!-- /.panel-body -->

</div>

</tiles:putAttribute>
<tiles:putAttribute name="page-controller">
    <script src="js/controllers/client/newClientPageController.js"></script>
</tiles:putAttribute>
</tiles:insertDefinition>