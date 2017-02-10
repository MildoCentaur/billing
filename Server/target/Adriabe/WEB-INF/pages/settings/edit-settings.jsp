<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<tiles:insertDefinition name=".defaultTemplate">
    <tiles:putAttribute name="page">

        <div class="panel panel-primary">
            <div class="panel-heading">
                Configuración de parámetros
            </div>
            <div class="panel-body">
                <div class="row">
                    <form:form role="form" action="edit-settings.json" method="POST" commandName="map">
                        <div class="panel-group" id="accordion" role="tablist" style="margin: 5px 10px ">
                            <c:forEach items="${map}" var="category" varStatus="loop">
                                <div class="panel panel-default">
                                    <div class="panel-heading" role="tab" id="headingOne${loop.index}">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion"
                                               href="#collapseOne${loop.index}" aria-expanded="true"
                                               class="accordion-toggle">
                                            ${category.key}
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapseOne${loop.index}" class="panel-collapse collapse" role="tabpanel"
                                         aria-labelledby="headingOne${loop.index}">
                                    <div class="panel-body">
                                            <c:forEach items="${category.value}" var="setting">
                                                <div class="col-lg-6">
                                                    <div class="form-group">
                                                        <label>${setting.label}</label>
                                                        <div class="form-group input-group" style="margin-bottom: 0px;">
                                                            <input type="text"  value="${setting.value}" class="form-control setting-element admin-lock-text" data-setting-id="${setting.id}" data-setting-name="${setting.name}" disabled="disabled"/>
                                                            <span class="input-group-addon admin-lock"><i class="fa fa-lock"></i></span>
                                                        </div>
                                                        <p class="help-block">${setting.description}</p>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <button type="button" id="btn-update-settings" class="btn btn-success" style="float:right;margin:15px 0;">Actualizar Configuraciones</button>
                        </div>

                    </form:form>
                </div>
            </div>
        </div>
    </tiles:putAttribute>
    <tiles:putAttribute name="page-controller">
        <script src="js/controllers/settings/editSettingsPageController.js"></script>
    </tiles:putAttribute>

</tiles:insertDefinition>