function NewOrderPageController() {
    PageController.call(this);

    this.DataTable = {};

    this._builder = new OrderBuilder();

    this._editor = new OrderEditor();


    this._inputPatternDetailList = [];


    this._initializeAddItemBehavior = function () {

        $(".edit-item").click(function () {
            pageController.editItemInOrder(this);
        });

        $(".delete-item").click(function () {
            pageController.deleteItemInOrder(this);
        });


        $(".input-pattern-detail").prop("disabled", "true");

        $(".input-pattern").blur(function () {
            if ($(this).val() != "") {
                $("#" + $(this).attr("id").replace("pattern", "combination")).prop("disabled", false);
            }
        });
        $("#item-main-fabric").blur(function () {
            if ($(this).val() != "") {
                $("#fieldset-puno").prop("disabled", false);
                $("#fieldset-cuello").prop("disabled", false);
                $("#fieldset-tira").prop("disabled", false);
            } else {
                $("#fieldset-puno").prop("disabled", true);
                $("#fieldset-cuello").prop("disabled", true);
                $("#fieldset-tira").prop("disabled", true);
            }
            $("#item-main-amount").focus();
        });
        $("#item-main-amount").blur(function () {
            $("#item-color").focus();
        });
        $("#item-color").blur(function () {
            $("#item-main-pattern").focus();
        });
        $("#item-main-pattern").blur(function () {
            if ($(this).val() == "") {

            }
            $("#item-main-combination").focus();
        });


        $("#item-cuello-amount").blur(function () {
            $("#item-cuello-fabric").focus();
        });
        $("#item-cuello-fabric").blur(function () {
            $("#item-cuello-pattern").focus();
        });
        $("#item-cuello-pattern").blur(function () {
            $("#item-cuello-combination").focus();
        });

        $("#item-tira-amount").blur(function () {
            $("#item-tira-fabric").focus();
        });
        $("#item-tira-fabric").blur(function () {
            $("#item-tira-pattern").focus();
        });
        $("#item-tira-pattern").blur(function () {
            $("#item-tira-combination").focus();
        });

        $("#item-puno-amount").blur(function () {
            $("#item-puno-fabric").focus();
        });
        $("#item-puno-fabric").blur(function () {
            $("#item-puno-pattern").focus();
        });
        $("#item-puno-pattern").blur(function () {
            $("#item-puno-combination").focus();
        });

        $(".item-amount").blur(function () {
            var id = $(this).attr("id");
            var pos = id.indexOf("-");
            var pos2 = id.lastIndexOf("-");
            pageController._builder.setItemQuantity($(this).val(), id.substring(pos + 1, pos2));
        });


        $("#add-order-item").click(function () {

            // nuevo elemento
            if ($("#selected-row").val() == -1) {
                var cuello = parseInt($("#item-cuello-amount").val());
                var puno = parseFloat($("#item-puno-amount").val());
                var tira = parseInt($("#item-tira-amount").val());

                var question = "No se detallan ni puño ni cuello para este tejido. ¿Desea Continuar?";
                if ((cuello + puno + tira) == 0) {
                    pageController.confirmMessage(question, pageController.addNewItemToOrder, pageController.doNothing);
                    return;
                }
                pageController.addNewItemToOrder();
            } else { // edito elemento
                var question = "Esta modificando un item de un pedido. ¿Desea Continuar?";

                pageController.confirmMessage(question, pageController.editNewItemToOrder, pageController.doNothing);
            }

        });

        $("#btn-add-item-table").click(function () {
            pageController._addOrderItemCleanup();
        });
        $("#btn-add-item-top").click(function () {
            pageController._addOrderItemCleanup();
        });

        $("#close-order-item").click(function () {
            pageController._addOrderItemCleanup();
        });

        if ($("#order-id").val() != 0) {
            $("#btn-add-item-table").prop("disabled", true);
            $("#btn-add-item-top").prop("disabled", true);
        }

        $("#btn-accept").click(function () {
            pageController.acceptOrder();
        });

        $("#btn-clear-order").click(function () {
            $('input[type=hidden]').val('');
            $('input[type=text]').val('');
            pageController._addOrderItemCleanup();
        });

    };
    this._addOrderItemCleanup = function () {
        $('#addOrderItemModal').modal('hide');
        $('#addOrderItemModal').find('input[type=hidden]').val('');
        $('#addOrderItemModal').find('input[type=text]').val('');

        $("#item-cuello-amount").val('0');
        $("#item-tira-amount").val('0');
        $("#item-puno-amount").val('0');
        $("#item-main-amount").val('0');
        pageController._inputPatternDetailList = [];
        pageController._itemToAdd = {};
        // in case it was an edition previously
        $("#addOrderItemModal").find(":text").prop("disabled", false);
        $(".input-pattern-detail").prop("disabled", true);
        $("#fieldset-puno").prop("disabled", true);
        $("#fieldset-cuello").prop("disabled", true);
        $("#fieldset-tira").prop("disabled", true);
        $('.modal-body a:first').tab('show');
    };


    this._initializeAutocompleteElements = function () {

        $(".autocomplete-client").catcomplete({
            delay: 10,
            source: function (request, response) {
                var name = request.term;
                return jQuery.get("search-entities.json?query=" + name, function (data) {
                    result = [];
                    pageController.sessionTime = 1;
                    $.each(data, function (i, e) {

                        if (i.toLowerCase() == "clientes") {
                            $.each(e, function (i2, e2) {
                                result.push({label: e2.name, category: i, id: e2.id});
                            });
                        }
                    });
                    return response(result);
                });
            },
            select: function (event, ui) {
                $(this).val(ui.item.label);
                pageController._builder.setClient(ui.item.id, ui.item.label);
                $(this).blur();
            },
            minLength: 2
        });
        $(".autocomplete-client").blur(function () {
            if ($(".autocomplete-client").val() != "") {
                $(".autocomplete-client").val($("#client-name").val());

                $("#btn-add-item-table").removeClass('disabled');
                $("#btn-add-item-top").removeClass('disabled');
                $("#btn-print").removeClass('disabled');
                $("#btn-accept").removeClass('disabled');
            } else {
                $("#btn-add-item-table").addClass('disabled');
                $("#btn-add-item-top").addClass('disabled');
                $("#btn-print").addClass('disabled');
                $("#btn-accept").addClass('disabled');
            }
        });

        $("#item-main-fabric").catcomplete({
            delay: 10,
            source: function (request, response) {
                var name = request.term;
                return jQuery.get("search-entities.json?query=" + name, function (data) {
                    result = [];
                    pageController.sessionTime = 1;

                    $.each(data, function (i, e) {
                        if (i.toLowerCase() == "tejidos") {
                            $.each(e, function (i2, e2) {
                                result.push({label: e2.code + " " + e2.longname, code: e2.code, longname: e2.longname, category: i, id: e2.id});
                            });
                        }
                    });
                    return response(result);
                });
            },
            select: function (event, ui) {
                $(this).val(ui.item.label);
                pageController._builder.setFabric(ui.item.id, ui.item.code, ui.item.longname, "main");
                $(this).trigger('blur');
            },
            minLength: 2
        });


        $("#item-puno-fabric").catcomplete({
            delay: 10,
            source: function (request, response) {
                var name = request.term;
                return jQuery.get("search-entities.json?query=" + name, function (data) {
                    result = [];
                    pageController.sessionTime = 1;

                    $.each(data, function (i, e) {
                        if (i.toLowerCase() == "tejidos") {
                            $.each(e, function (i2, e2) {
                                if (e2.puno == true) {
                                    result.push({label: e2.code + " " + e2.longname, code: e2.code, longname: e2.longname, category: i, id: e2.id});
                                }
                            });
                        }
                    });
                    return response(result);
                });
            },
            select: function (event, ui) {
                $(this).val(ui.item.label);
                pageController._builder.setFabric(ui.item.id, ui.item.code, ui.item.longname, "puno");
                $(this).blur();
            },
            minLength: 2
        });

        $("#item-cuello-fabric").catcomplete({
            delay: 10,
            source: function (request, response) {
                var name = request.term;
                return jQuery.get("search-entities.json?query=" + name, function (data) {
                    result = [];
                    pageController.sessionTime = 1;

                    $.each(data, function (i, e) {
                        if (i.toLowerCase() == "tejidos") {
                            $.each(e, function (i2, e2) {
                                if (e2.cuello == true) {
                                    result.push({label: e2.code + " " + e2.longname, code: e2.code, longname: e2.longname, category: i, id: e2.id});
                                }
                            });
                        }
                    });
                    return response(result);
                });
            },
            select: function (event, ui) {
                $(this).val(ui.item.label);
                pageController._builder.setFabric(ui.item.id, ui.item.code, ui.item.longname, "cuello");
                $(this).blur();
            },
            minLength: 2
        });

        $("#item-tira-fabric").catcomplete({
            delay: 10,
            source: function (request, response) {
                var name = request.term;
                return jQuery.get("search-entities.json?query=" + name, function (data) {
                    result = [];
                    pageController.sessionTime = 1;

                    $.each(data, function (i, e) {
                        if (i.toLowerCase() == "tejidos") {
                            $.each(e, function (i2, e2) {
                                if (e2.tira == true) {
                                    result.push({label: e2.code + " " + e2.longname, code: e2.code, longname: e2.longname, category: i, id: e2.id});
                                }
                            });
                        }
                    });
                    return response(result);
                });
            },
            select: function (event, ui) {
                $(this).val(ui.item.label);
                pageController._builder.setFabric(ui.item.id, ui.item.code, ui.item.longname, "tira");
                $(this).blur();
            },
            minLength: 2
        });


        $("#item-color").catcomplete({
            delay: 10,
            source: function (request, response) {
                var name = request.term;
                return jQuery.get("search-entities.json?query=" + name, function (data) {
                    result = [];
                    pageController.sessionTime = 1;

                    $.each(data, function (i, e) {
                        if (i.toLowerCase() == "colores") {
                            $.each(e, function (i2, e2) {
                                result.push({label: e2.name, category: i, id: e2.id});
                            });
                        }
                    });
                    return response(result);
                });
            },
            select: function (event, ui) {
                $(this).val(ui.item.label);
                pageController._builder.setColor(ui.item.id, ui.item.label);
                $(this).blur();
            },
            minLength: 2
        });


        $(".input-pattern-detail").each(function (index, element) {
            $(element).autocomplete({
                delay: 0,
                source: function (request, response) {
                    return response(pageController._inputPatternDetailList);
                },
                select: function (event, ui) {
                    $(this).val(ui.item.label);
                    var id = $(this).attr("id");
                    var pos = id.indexOf("-");
                    var pos2 = id.lastIndexOf("-");
                    pageController._builder.setPatternCombination(ui.item.id, ui.item.label, id.substring(pos + 1, pos2));
                    $(this).blur();

                },
                minLength: 0

            }).autocomplete("instance")._renderItem = function (ul, item) {
                var stripItem = "<a>" + item.label + "<br>";
                $.each(item.colors, function (i, e) {
                    stripItem = stripItem + "<span style='background-color:#" + e + ";padding:2px 10px;margin: 0 5px;'>&nbsp;&nbsp;&nbsp;" + "</span>";
                });
                stripItem = stripItem + "</a>"
                var result = $("<li>")
                    .append(stripItem)
                    .appendTo(ul);

                return result;
            };
        });
        $(".input-pattern").catcomplete({
            delay: 10,
            source: function (request, response) {
                return jQuery.get("search-entities.json?query=" + name, function (data) {
                    result = [];
                    pageController.sessionTime = 1;

                    $.each(data, function (i, e) {
                        if (i.toLowerCase() == "rayados") {
                            $.each(e, function (i2, e2) {
                                result.push({label: e2.name, category: i, id: e2.id});
                            });
                        }
                    });
                    return response(result);
                });
            },
            select: function (event, ui) {
                $(this).val(ui.item.label);
                var id = $(this).attr("id");
                var pos = id.indexOf("-");
                var pos2 = id.lastIndexOf("-");
                pageController._builder.setPattern(ui.item.id, ui.item.label, id.substring(pos + 1, pos2));

                var elementId = $(this).attr("id").replace("Pattern", "Combination");
                var combinationObj = $("#" + elementId);

                combinationObj.prop("disabled", false);

                jQuery.get("data/product/stripes/combinations.json?stripeid=" + ui.item.id, function (data) {
                    pageController.sessionTime = 1;
                    result = [];
                    $.each(data, function (i, e) {
                        var combinations = [];
                        combinations[0] = e.colorFingerA;
                        combinations[1] = e.colorFingerB;
                        combinations[2] = e.colorFingerC;
                        combinations[3] = e.colorFingerD;
                        combinations[4] = e.colorFingerE;
                        combinations[5] = e.colorFingerF;

                        result.push({label: e.name, colors: combinations, shortlabel: e.name, id: e.id});
                    });
                    pageController._inputPatternDetailList = result;

                });
                $(this).blur();
            },
            minLength: 2
        });
    };
    this._initializeListItems = function () {
        $('#list-items-order').DataTable({
            "language": {
                "zeroRecords": "No hay items asociados a este pedido",
                "infoEmpty": "No hay items asociados a este pedido",
                "decimal": ",",
                "thousands": "."
            },
            "columnDefs": [
                { "width": "5%", "targets": 0 },
                { "width": "10%", "targets": 1 },
                { "width": "50%", "targets": 2 },
                { "width": "9%", "targets": 3 },
                { "width": "9%", "targets": 4 },
                { "width": "9%", "targets": 5 },
                { "width": "8%", "targets": 6 },
                { "sClass": "no-padding", "targets": 6 }
            ],
            "scrollY": "600px",
            "scrollCollapse": true,
            "paging": false,
            "ordering": false,
            "info": false,
            "filter": false
        });
        $(".dataTables_scrollHead .table").css("margin-bottom", "0px");
        $(".dataTables_scrollBody .table").css("margin-bottom", "0px");

        this.DataTable = $('#list-items-order').DataTable();
    };


    this._validateOrderItem = function () {

        var errors = [];
        var notEmptyMainFabric = new NotEmptyMainFabricValidationRule();
        var notEmptyColor = new NotEmptyColorValidationRule();
        var amountMainItem = new AmountMainItemValidationRule();
        var amountCuelloItem = new AmountCuelloItemValidationRule();
        var amountTiraItem = new AmountTiraItemValidationRule();
        var amountPunoItem = new AmountPunoItemValidationRule();
        var patternMainItem = new PatternMainItemValidationRule();
        var patternCuelloItem = new PatternCuelloItemValidationRule();
        var patternTiraItem = new PatternTiraItemValidationRule();
        var patternPunoItem = new PatternPunoItemValidationRule();

        var validationRules = [notEmptyMainFabric, notEmptyColor, amountMainItem, amountCuelloItem,
            amountTiraItem, amountPunoItem, patternMainItem, patternCuelloItem,
            patternTiraItem, patternPunoItem];

        $(":text").css("border", "1px solid #ddd");
        var errorsAux = [];
        for (var i = validationRules.length - 1; i >= 0; i--) {
            errorsAux = validationRules[i].validate();
            if (errorsAux.length > 0) {
                errors = errors.concat(errorsAux);
            }

        }


        return errors;

    };

    this._renderOrder = function () {
        pageController.DataTable.clear();
        for (var i = 0; i < this._builder.order.items.length; i++) {
            var orderItem = this._builder.order.items[i];
            var itemId = orderItem.id;
            var description = orderItem.getProductName();

            var cuelloItem = orderItem.getCuelloItem();
            var cuelloItemId = cuelloItem == null ? 0 : cuelloItem.id;
            var cuello = cuelloItem == null ? ' - ' : cuelloItem.quantity;

            var tiraItem = orderItem.getTiraItem();
            var tiraItemId = tiraItem == null ? 0 : tiraItem.id;
            var tira = tiraItem == null ? ' - ' : tiraItem.quantity;

            var punoItem = orderItem.getPunoItem();
            var punoItemId = punoItem == null ? 0 : punoItem.id;
            var puno = punoItem == null ? ' - ' : punoItem.quantity;
            var action = "";
            if ($("#order-id").val() > 0) {
                action = "<span id='row_" + itemId + "' class='edit-item btn btn-warning btn-outline' onclick='pageController.editItemInOrder(this);' data-selected-row='" + i + "' " +
                    " data-puno-item-id='" + punoItemId + "' data-cuello-item-id='" + cuelloItemId + "' data-tira-item-id='" + tiraItemId + "' data-item-id='" + itemId + "' " +
                    " style='float:left;'> <i class='fa fa-pencil-square-o' ></i> </span>" +
                    "<span id='row_" + itemId + "' class='delete-item btn btn-danger btn-outline' onclick='pageController.deleteItemInOrder(this);' data-selected-row='" + i + "'" +
                    " data-puno-item-id='" + punoItemId + "' data-cuello-item-id='" + cuelloItemId + "' data-tira-item-id='" + tiraItemId + "' data-item-id='" + itemId + "' " +
                    " style='float:right;'> <i class='fa fa-trash-o' ></i> </span><input type='hidden' value='" + i + "'/>";

            } else {
                action = "<span class='edit-item btn btn-warning btn-outline' onclick='pageController.editItemInOrder(this);' data-selected-row='" + i + "' " +
                    "style='float:left;'>" +
                    "<i class='fa fa-pencil-square-o' ></i>" +
                    "</span>" +
                    "<span class='delete-item btn btn-danger btn-outline' onclick='pageController.deleteItemInOrder(this);' data-selected-row='" + i + "'  style='float:right;'>" +
                    "<i class='fa fa-trash-o' ></i>" +
                    "</span><input type='hidden' value='" + i + "'/>";
            }

            var row = [ pageController.DataTable.data().length + 1, orderItem.quantity, description, cuello, tira, puno, action];

            pageController.DataTable.row.add(row);
            pageController.DataTable.draw();

            $("td.no-padding").css("padding-left", "0px");
            $("td.no-padding").css("padding-right", "0px");
            $(".dataTables_scrollHead .table").css("margin-bottom", "0px");
            $(".dataTables_scrollBody .table").css("margin-bottom", "0px");
        }


        this._addOrderItemCleanup();

    };
    this._populateAddItemPopUp = function (type, item) {

        $("#item-color").val(item.product.color.name);

        $("#item-" + type + "-fabric").val(item.product.fabric.longname);
        $("#item-" + type + "-amount").val(item.quantity);
        if (item.product.stripe != null) {
            $("#item-" + type + "-pattern").val(item.product.stripe.name);
            $("#item-" + type + "-combination").val(item.product.stripeCombination.name);
        }

    };

    this.postNewOrder = function () {
        var url = "add-order.json";
        var list = JSON.stringify(pageController._builder.order);

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            url: url,
            dataType: "json",
            data: list,
            contentType: 'application/json',
            success: function (response) {
                if (response.hasErrors == 0) {
                    location.href = 'list-orders.html';
                } else {
                    pageController.showErrorMessage(response.errorListDetailList);
                }

            }
        });
    };

    this.postEditOrder = function () {
        var url = "edit-process-order.json";
        var list = JSON.stringify(pageController._editor.adjustments);

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            url: url,
            dataType: "json",
            data: list,
            contentType: 'application/json',
            success: function (response) {
                if (response.hasErrors == 0) {
                    location.href = 'list-orders.html';
                } else {
                    pageController.showErrorMessage(response.errorListDetailList);
                }

            }
        });
    };


}
//item-main-fabric
NewOrderPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
NewOrderPageController.prototype.constructor = NewOrderPageController;

NewOrderPageController.prototype.initialize = function () {
    this.initializePage();
    this._initializeAutocompleteElements();
    this._initializeAddItemBehavior();
    this._initializeListItems();

    if ($("#order-id").val() > 0) {
        $("#client-name").val($("#client-name-hidden").data("client-name"));
        $(".autocomplete-client").prop("disabled", true);
        $("#btn-add-item-table").removeClass('disabled');
        $("#btn-add-item-top").removeClass('disabled');
        $("#btn-print").removeClass('disabled');
        $("#btn-accept").removeClass('disabled');
        this._builder.setClient($("#client-name-hidden").data("client-id"), $("#client-name-hidden").data("client-name"));
        $(".item-rows").each(function (index, item) {
            pageController._builder.loadOrderItem(item);
        });

    }
};
NewOrderPageController.prototype.acceptOrder = function () {

    if ($("#order-id").val() == 0) {
        pageController.postNewOrder();
    } else {
        pageController.postEditOrder();
    }


};

NewOrderPageController.prototype.editNewItemToOrder = function () {
    pageController.hideErrorMessage();

    var errors = pageController._validateOrderItem();
    if (errors.length == 0) {
        var index = $("#selected-row").val();
        item = pageController._builder.getOrderItemByIndex(index);
        if ($("#order-id").val() > 0) {
            pageController._editor.addEditAdjustment($("#order-id").val(), $("#selected-item-id").val(), item);
        }

        pageController._builder.editItemQuantitiesInOrder(index);
        pageController._renderOrder();
        $("#selected-item-id").val(-1);
        $("#selected-row").val(-1);
    } else {
        pageController.showErrorMessage(errors);
    }

};
NewOrderPageController.prototype.addNewItemToOrder = function () {
    pageController.hideErrorMessage();

    var errors = pageController._validateOrderItem();
    if (errors.length == 0) {
        pageController._builder.addItem();
        pageController._renderOrder();
        $("#selected-item-id").val(-1);
        $("#selected-row").val(-1);
    } else {
        pageController.showErrorMessage(errors);
    }
};
NewOrderPageController.prototype.deleteItemInOrder = function (btn) {
    var index = $(btn).data("selected-row");
    if ($("#order-id").val() > 0) {
        $("#selected-item-id").val($(btn).data("item-id"));
    }

    $("#selected-row").val($(btn).data("selected-row"));
    var question = "¿Desea eleminar la fila " + (index + 1 ) + "?";
    pageController.confirmMessage(question, function () {
        pageController._builder.deleteItem($("#selected-row").val());
        if ($("#order-id").val() > 0) {

            var accItemId = $(btn).data("puno-item-id");
            if (typeof accItemId != 'undefined' && accItemId > 0) {
                pageController._editor.addDeleteAdjustment($("#order-id").val(), accItemId);
            }
            accItemId = $(btn).data("cuello-item-id");
            if (typeof accItemId != 'undefined' && accItemId > 0) {
                pageController._editor.addDeleteAdjustment($("#order-id").val(), accItemId);
            }
            accItemId = $(btn).data("tira-item-id");
            if (typeof accItemId != 'undefined' && accItemId > 0) {
                pageController._editor.addDeleteAdjustment($("#order-id").val(), accItemId);
            }
            pageController._editor.addDeleteAdjustment($("#order-id").val(), $("#selected-item-id").val());
        }
        pageController._renderOrder();
        $("#selected-item-id").val(-1);
        $("#selected-row").val(-1);
    }, pageController.doNothing);

};


NewOrderPageController.prototype.editItemInOrder = function (btn) {


    if ($("#order-id").val() > 0) {
        $("#selected-item-id").val($(btn).data("item-id"));
    }

    $("#selected-row").val($(btn).data("selected-row"));

    var index = $(btn).data("selected-row");
    var item = pageController._builder.getOrderItemByIndex(index);

    pageController._populateAddItemPopUp("main", item);
    if (item.accesories.length > 0) {
        for (var i = item.accesories.length - 1; i >= 0; i--) {
            var acc = item.accesories[i];

            pageController._populateAddItemPopUp(acc.getItemType(), acc);
        }
        ;
    }
    $('#addOrderItemModal').modal('show');


    if ($("#item-cuello-fabric").val() != "") {
        $("#item-cuello-amount").prop("disabled", false);
        $("#fieldset-cuello").prop("disabled", false);
        $("#item-cuello-fabric").prop("disabled", true);
        $("#item-cuello-combination").prop("disabled", true);
        $("#item-cuello-pattern").prop("disabled", true);
    }
    if ($("#item-tira-fabric").val() != "") {
        $("#item-tira-amount").prop("disabled", false);
        $("#fieldset-tira").prop("disabled", false);
        $("#item-tira-fabric").prop("disabled", true);
        $("#item-tira-combination").prop("disabled", true);
        $("#item-tira-pattern").prop("disabled", true);
    }
    if ($("#item-puno-fabric").val() != "") {
        $("#item-puno-amount").prop("disabled", false);
        $("#fieldset-puno").prop("disabled", false);
        $("#item-puno-fabric").prop("disabled", true);
        $("#item-puno-combination").prop("disabled", true);
        $("#item-puno-pattern").prop("disabled", true);
    }
    $("#item-main-fabric").prop("disabled", true);
    $("#item-main-combination").prop("disabled", true);
    $("#item-main-pattern").prop("disabled", true);
    $("#item-color").prop("disabled", true);


};

var pageController = new NewOrderPageController();