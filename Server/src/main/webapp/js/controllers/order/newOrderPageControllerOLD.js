function NewOrderPageController() {
    PageController.call(this);

    this.DataTable = {};

    this._initializeAddItemBehavior = function () {
        $(".input-pattern-detail").prop("disabled", "true");

        $(".input-pattern").blur(function () {
            if ($(this).val() != "") {
                $("#" + $(this).attr("id").replace("Pattern", "Combination")).prop("disabled", false);
            }
        });
        $("#itemMainFabric").blur(function () {
            if ($(this).val() != "") {
                // el 0 es el main que no se toca!
                $("fieldset")[2].disabled = false;
                $("fieldset")[1].disabled = false;
                $("#itemMainFabric").val($("#item-main-fabric-label").val());
            } else {
                // el 0 es el main que no se toca!
                $("fieldset")[2].disabled = true;
                $("fieldset")[1].disabled = true;
            }
        });

        $("#add-order-item").click(function () {
            var cuellos = $("#itemCuellosAmount").val();
            var puno = $("#itemPunoAmount").val();
            var question = "No se detallan ni puño ni cuello para este tejido. ¿Desea Continuar?";
            if ((cuellos == "" || cuellos == 0) && (puno == "" || puno == 0)) {
                pageController.confirmMessage(question, pageController.addNewItemToOrder, pageController.doNothing);
                return;
            }
            pageController.addNewItemToOrder();
        });

        $("#btn-add-item-table").click(function () {
            pageController._addOrderItemCleanup();
        });
        $("#btn-add-item-top").click(function () {
            pageController._addOrderItemCleanup();
        });

        $("#close-order-item").click(function () {
            pageController.clearAddNewItemForm();
        });

        if ($(".orderId").val() != 0) {
            $("#btn-add-item-table").prop("disabled", true);
            $("#btn-add-item-top").prop("disabled", true);
        }

        $("#btn-accept").click(function () {
            pageController.acceptOrder();
        });

        $("#btn-clear-order").click(function () {
            location.relod();
        });

    };
    this._validateOrderItem = function () {
        var errors = [];
        $(":text").css("border", "1px solid #ddd");
        if ($("#itemMainFabric").val() == "" || $("#itemMainFabricId").val() <= 0) {
            errors.push("El tejido del producto es invalido.");
            $("#itemMainFabric").css("border", "1px solid red");
        }

        if ($("#itemPunoFabric").val() == "") {
            $("#itemPunoFabricId").val(0);
        }

        if ($("#itemCuellosFabric").val() == "") {
            $("#itemCuellosFabricId").val(0);
        }

        if ($("#itemColor").val() == "" || $("#itemColorId").val() <= 0) {
            errors.push("El color elegido es invalido.");
            $("#itemColor").css("border", "1px solid red");
        }

        if (!isValidNumber($("#itemMainAmount").val()) || $("#itemMainAmount").val() <= 0) {
            errors.push("Cantidad de rollos invalida");
            $("#itemMainAmount").css("border", "1px solid red");
        }
        if (($("#itemCuellosAmount").val() != "" && !isValidNumber($("#itemCuellosAmount").val())) || $("#itemCuellosAmount").val() < 0) {
            errors.push("Cantidad de Cuellos y Tiras es invalida");
            $("#itemCuellosAmount").css("border", "1px solid red");
        }
        if (($("#itemPunoAmount").val() != "" && !isValidNumber($("#itemPunoAmount").val()) ) || $("#itemPunoAmount").val() < 0) {
            errors.push("Cantidad de Puño invalida");
            $("#itemPunoAmount").css("border", "1px solid red");
        }

        if ($("#itemMainAmount").val() == 0 && $("#itemMainFabric").val() != "") {
            errors.push("Ingrese la cantidad de rollos del tejido que desea.");
            $("#itemMainFabric").css("border", "1px solid red");
        }
        if ($("#itemPunoAmount").val() == 0 && $("#itemPunoFabric").val() != "") {
            errors.push("Ingrese la cantidad de de puño que desea.");
            $("#itemPunoFabric").css("border", "1px solid red");
        }
        if ($("#itemCuellosAmount").val() == 0 && $("#itemCuellosFabric").val() != "") {
            errors.push("Ingrese el tejido de puño que desea.");
            $("#itemCuellosAmount").css("border", "1px solid red");
        }

        if ($("#itemMainAmount").val() > 0 && $("#itemMainFabric").val() == "") {
            errors.push("Ingrese el tejido que desea.");
            $("#itemMainFabric").css("border", "1px solid red");
        }
        if ($("#itemPunoAmount").val() > 0 && $("#itemPunoFabric").val() == "") {
            errors.push("Ingrese el tejido de puño que desea.");
            $("#itemPunoFabric").css("border", "1px solid red");
        }
        if ($("#itemCuellosAmount").val() > 0 && $("#itemCuellosFabric").val() == "") {
            errors.push("Ingrese el tejido de puño que desea.");
            $("#itemCuellosAmount").css("border", "1px solid red");
        }

        if ($("#itemCuellosPattern").val() != "" && $("#itemCuellosCombination").val() == "") {
            errors.push("Ingrese la combinación de rayado para el cuello.");
            $("#itemCuellosCombination").css("border", "1px solid red");
        } else if ($("#itemCuellosPattern").val() == "") {
            $("#itemCuellosCombinationId").val(0);
            $("#itemCuellosPatternId").val(0);
            $("#itemCuellosCombination").val("");
        }

        if ($("#itemPunoPattern").val() != "" && $("#itemPunoCombination").val() == "") {
            errors.push("Ingrese la combinación de rayado para el puño.");
            $("#itemPunoCombination").css("border", "1px solid red");
        } else if ($("#itemPunoPattern").val() == "") {
            $("#itemPunoCombinationId").val(0);
            $("#itemPunoPatternId").val(0);
            $("#itemPunoCombination").val("");
        }
        if ($("#itemMainPattern").val() != "" && $("#itemMainCombination").val() == "") {
            errors.push("Ingrese la combinación de rayado para el tejido.");
            $("#itemMainCombination").css("border", "1px solid red");
        } else if ($("#itemMainPattern").val() == "") {
            $("#itemMainCombinationId").val(0);
            $("#itemMainPatternId").val(0);
            $("#itemMainCombination").val("");
        }

        return errors;

    };
    this._editOrderItem = function () {
        var itemClass = ".order-item-" + $("#edit-item-number").val();
        var mainItemClass = itemClass + ".is-main";
        var punoItemClass = itemClass + ".is-puno";
        var cuelloItemClass = itemClass + ".is-cuellos";

        $(mainItemClass + ".fabric-amount").val($("#itemMainAmount").val());
        pageController.DataTable.cell($("#edit-row-number").val(), 1).data($("#itemMainAmount").val());

        if ($(cuelloItemClass).length > 0) {
            $(cuelloItemClass + ".fabric-amount").val($("#itemCuellosAmount").val());
            var cuelloStr = pageController.DataTable.cell($("#edit-row-number").val(), 3).data();
            cuelloStr = cuelloStr.split(" ");
            cuelloStr[0] = $("#itemCuellosAmount").val();
            pageController.DataTable.cell($("#edit-row-number").val(), 3).data(cuelloStr.join(" "));
        }
        if ($(punoItemClass).length > 0) {
            $(punoItemClass + ".fabric-amount").val($("#itemPunoAmount").val());
            var punoStr = pageController.DataTable.cell($("#edit-row-number").val(), 4).data();
            punoStr = punoStr.split(" ");
            punoStr[0] = $("#itemPunoAmount").val();
            pageController.DataTable.cell($("#edit-row-number").val(), 4).data(punoStr.join(" "));
        }

        $($("#list-items-order tbody tr")[$("#edit-row-number").val()]).find(".deleteItem").click(function () {
            pageController.deleteItemInOrder(this);
        });
        $($("#list-items-order tbody tr")[$("#edit-row-number").val()]).find(".editItem").click(function () {
            pageController.editItemInOrder(this);
        });
    };
    this._addOrderItem = function () {


        if ($("#edit-item-number").val() >= 0) {

            this._editOrderItem();
            $("#edit-item-number").val(-1);
            this._addOrderItemCleanup();
            return;
        }


        var rowNumber = pageController.DataTable.data().length;
        var quantity = $("#itemMainAmount").val();
        var description = $("#item-main-fabric-short-label").val() + " " + $("#item-color-label").val();
        var stripe = $("#itemMainPatternId").val();
        var cuellos = " - ";
        var puno = " - ";
        var associationCounter = 0;
        var action = "<span class='editItem btn btn-warning btn-outline' onclick='pageController.editItemInOrder(this);' style='margin-right: 4px; margin-left: 8px;'>" +
            "<i class='fa fa-pencil-square-o' ></i>" +
            "</span>" +
            "<span class='deleteItem btn btn-danger btn-outline' onclick='pageController.deleteItemInOrder(this);'>" +
            "<i class='fa fa-trash-o' ></i>" +
            "</span><input type='hidden' value='" + rowNumber + "'/>";


        var hiddenMainItem = "<input type='hidden' class='order-item-" + rowNumber + " is-main fabric-amount'            id='items" + rowNumber + ".quantity'                    name='items[" + rowNumber + "].quantity'                       value='" + quantity + "' />" +
            "<input type='hidden' class='order-item-" + rowNumber + " is-main fabric-id'                id='items" + rowNumber + ".product.fabricId'            name='items[" + rowNumber + "].product.fabricId'               value='" + $("#itemMainFabricId").val() + "' />" +
            "<input type='hidden' class='order-item-" + rowNumber + " is-main fabric-name'              id='items" + rowNumber + ".product.fabric'              name='items[" + rowNumber + "].product.fabric'                 value='" + $("#item-main-fabric-label").val() + "' />" +
            "<input type='hidden' class='order-item-" + rowNumber + " is-main color-id'                 id='items" + rowNumber + ".product.colorId'             name='items[" + rowNumber + "].product.colorId'                value='" + $("#itemColorId").val() + "' />" +
            "<input type='hidden' class='order-item-" + rowNumber + " is-main color-name'               id='items" + rowNumber + ".product.color'               name='items[" + rowNumber + "].product.color'                  value='" + $("#item-color-label").val() + "' />" +
            "<input type='hidden' class='order-item-" + rowNumber + " is-main stripe-id'                id='items" + rowNumber + ".product.stripeId'            name='items[" + rowNumber + "].product.stripeId'               value='" + $("#itemMainPatternId").val() + "' />" +
            "<input type='hidden' class='order-item-" + rowNumber + " is-main stripe-name'              id='items" + rowNumber + ".product.stripe'              name='items[" + rowNumber + "].product.stripe'                 value='" + $("#item-main-pattern-label").val() + "' />" +
            "<input type='hidden' class='order-item-" + rowNumber + " is-main stripe-combination-id'    id='items" + rowNumber + ".product.stripeCombinationId' name='items[" + rowNumber + "].product.stripeCombinationId'    value='" + $("#itemMainCombinationId").val() + "' />" +
            "<input type='hidden' class='order-item-" + rowNumber + " is-main stripe-combination-name'  id='items" + rowNumber + ".product.stripeCombinationName'   name='items[" + rowNumber + "].product.stripeCombinationName'      value='" + $("#item-main-combination-label").val() + "' />";
        $("form").append(hiddenMainItem);

        if (stripe > 0) {
            stripe = " R: " + $("#item-main-pattern-label").val() + " Comb: " + $("#item-main-combination-label").val();
            description = description + " " + stripe;
        }

        if ($("#itemPunoAmount").val() > 0 && $("#itemPunoFabric").val() != "") {
            puno = $("#itemPunoAmount").val() + " Kg. " + (($("#itemPunoPatternId").val() > 0) ? "R" : "");

            var hiddenPunoItem =
                "<input type='hidden' class='order-item-" + rowNumber + " is-puno fabric-amount'            id='items" + rowNumber + ".accesories" + associationCounter + ".quantity'            name='items[" + rowNumber + "].accesories[" + associationCounter + "].quantity'                       value='" + $("#itemPunoAmount").val() + "' />" +
                "<input type='hidden' class='order-item-" + rowNumber + " is-puno fabric-id'                id='items" + rowNumber + ".accesories" + associationCounter + ".product.fabricId'            name='items[" + rowNumber + "].accesories[" + associationCounter + "].product.fabricId'               value='" + $("#itemPunoFabricId").val() + "' />" +
                "<input type='hidden' class='order-item-" + rowNumber + " is-puno fabric-name'              id='items" + rowNumber + ".accesories" + associationCounter + ".product.fabric'              name='items[" + rowNumber + "].accesories[" + associationCounter + "].product.fabric'                 value='" + $("#item-puno-fabric-label").val() + "' />" +
                "<input type='hidden' class='order-item-" + rowNumber + " is-puno color-id'                 id='items" + rowNumber + ".accesories" + associationCounter + ".product.colorId'             name='items[" + rowNumber + "].accesories[" + associationCounter + "].product.colorId'                value='" + $("#itemColorId").val() + "' />" +
                "<input type='hidden' class='order-item-" + rowNumber + " is-puno color-name'               id='items" + rowNumber + ".accesories" + associationCounter + ".product.color'               name='items[" + rowNumber + "].accesories[" + associationCounter + "].product.color'                  value='" + $("#item-color-label").val() + "' />" +
                "<input type='hidden' class='order-item-" + rowNumber + " is-puno stripe-id'                id='items" + rowNumber + ".accesories" + associationCounter + ".product.stripeId'            name='items[" + rowNumber + "].accesories[" + associationCounter + "].product.stripeId'     value='" + $("#itemPunoPatternId").val() + "' />" +
                "<input type='hidden' class='order-item-" + rowNumber + " is-puno stripe-name'              id='items" + rowNumber + ".accesories" + associationCounter + ".product.stripe'              name='items[" + rowNumber + "].accesories[" + associationCounter + "].product.stripe'                 value='" + $("#item-puno-pattern-label").val() + "' />" +
                "<input type='hidden' class='order-item-" + rowNumber + " is-puno stripe-combination-id'    id='items" + rowNumber + ".accesories" + associationCounter + ".product.stripeCombinationId' name='items[" + rowNumber + "].accesories[" + associationCounter + "].product.stripeCombinationId'    value='" + $("#itemPunoCombinationId").val() + "' />" +
                "<input type='hidden' class='order-item-" + rowNumber + " is-puno stripe-combination-name'  id='items" + rowNumber + ".accesories" + associationCounter + ".product.stripeCombinationName'   name='items[" + rowNumber + "].accesories[" + associationCounter + "].product.stripeCombinationName'      value='" + $("#item-puno-combination-label").val() + "' />";
            associationCounter++;
            $("form").append(hiddenPunoItem);


        }

        if ($("#itemCuellosAmount").val() > 0 && $("#itemCuellosFabric").val() != "") {
            cuellos = $("#itemCuellosAmount").val() + " Ud. " + (($("#itemCuellosPatternId").val() > 0) ? "R" : "");

            var hiddenCuellosItem =
                "<input type='hidden' class='order-item-" + rowNumber + " is-cuellos fabric-amount'            id='items" + rowNumber + ".accesories" + associationCounter + ".quantity'                    name='items[" + rowNumber + "].accesories[" + associationCounter + "].quantity'                         value='" + $("#itemCuellosAmount").val() + "' />" +
                "<input type='hidden' class='order-item-" + rowNumber + " is-cuellos fabric-id'                id='items" + rowNumber + ".accesories" + associationCounter + ".product.fabricId'            name='items[" + rowNumber + "].accesories[" + associationCounter + "].product.fabricId'               value='" + $("#itemCuellosFabricId").val() + "' />" +
                "<input type='hidden' class='order-item-" + rowNumber + " is-cuellos fabric-name'              id='items" + rowNumber + ".accesories" + associationCounter + ".product.fabric'              name='items[" + rowNumber + "].accesories[" + associationCounter + "].product.fabric'                 value='" + $("#item-cuellos-fabric-label").val() + "' />" +
                "<input type='hidden' class='order-item-" + rowNumber + " is-cuellos color-id'                 id='items" + rowNumber + ".accesories" + associationCounter + ".product.colorId'             name='items[" + rowNumber + "].accesories[" + associationCounter + "].product.colorId'                value='" + $("#itemColorId").val() + "' />" +
                "<input type='hidden' class='order-item-" + rowNumber + " is-cuellos color-name'               id='items" + rowNumber + ".accesories" + associationCounter + ".product.color'               name='items[" + rowNumber + "].accesories[" + associationCounter + "].product.color'                  value='" + $("#item-color-label").val() + "' />" +
                "<input type='hidden' class='order-item-" + rowNumber + " is-cuellos stripe-id'                id='items" + rowNumber + ".accesories" + associationCounter + ".product.stripeId'            name='items[" + rowNumber + "].accesories[" + associationCounter + "].product.stripeId'               value='" + $("#itemCuellosPatternId").val() + "' />" +
                "<input type='hidden' class='order-item-" + rowNumber + " is-cuellos stripe-name'              id='items" + rowNumber + ".accesories" + associationCounter + ".product.stripe'              name='items[" + rowNumber + "].accesories[" + associationCounter + "].product.stripe'                 value='" + $("#item-cuellos-pattern-label").val() + "' />" +
                "<input type='hidden' class='order-item-" + rowNumber + " is-cuellos stripe-combination-id'    id='items" + rowNumber + ".accesories" + associationCounter + ".product.stripeCombinationId' name='items[" + rowNumber + "].accesories[" + associationCounter + "].product.stripeCombinationId'    value='" + $("#itemCuellosCombinationId").val() + "' />" +
                "<input type='hidden' class='order-item-" + rowNumber + " is-cuellos stripe-combination-name'  id='items" + rowNumber + ".accesories" + associationCounter + ".product.stripeCombinationName'   name='items[" + rowNumber + "].accesories[" + associationCounter + "].product.stripeCombinationName'      value='" + $("#item-cuellos-combination-label").val() + "' />";
            $("form").append(hiddenCuellosItem);
        }

        var row = [ pageController.DataTable.data().length + 1, quantity, description, cuellos, puno, action];

        this.DataTable.row.add(row).draw();

        this._addOrderItemCleanup();

    };
    this._addOrderItemCleanup = function () {
        $('#addOrderItemModal').modal('hide');
        $('#addOrderItemModal').find('input[type=hidden]').val('');
        $('#addOrderItemModal').find('input[type=text]').val('');

        $("#itemCuellosAmount").val('0');
        $("#itemPunoAmount").val('0');
        $("#itemMainAmount").val('0');
        pageController._inputPatternDetailList = [];

        // in case it was an edition previously
        $("#addOrderItemModal").find(":text").prop("disabled", false);
        $(".input-pattern-detail").prop("disabled", true);
        $("fieldset")[2].disabled = true;
        $("fieldset")[1].disabled = true;
        $('.modal-body a:first').tab('show');
        $("#addOrderItemModal #add-order-item").text("Agregar item");

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
                $(this).val(ui.label);
                $(".client-id").val(ui.item.id);
                $("#client-name").val(ui.item.label);
                $("#btn-add-item-table").removeClass('disabled');
                $("#btn-add-item-top").removeClass('disabled');
                $("#btn-print").removeClass('disabled');
                $("#btn-accept").removeClass('disabled');

            },
            minLength: 2
        });
        $(".autocomplete-client").blur(function () {
            if ($(".autocomplete-client").val() != "") {
                $(".autocomplete-client").val($("#client-name").val());
            } else {
                $("#btn-add-item-table").addClass('disabled');
                $("#btn-add-item-top").addClass('disabled');
                $("#btn-print").addClass('disabled');
                $("#btn-accept").addClass('disabled');
            }
        });

        $("#itemMainFabric").catcomplete({
            delay: 10,
            source: function (request, response) {
                var name = request.term;
                return jQuery.get("search-entities.json?query=" + name, function (data) {
                    result = [];
                    pageController.sessionTime = 1;

                    $.each(data, function (i, e) {
                        if (i.toLowerCase() == "tejidos") {
                            $.each(e, function (i2, e2) {
                                result.push({label: e2.code + " " + e2.longname, shortlabel: e2.code + " " + e2.shortname, category: i, id: e2.id});
                            });
                        }
                    });
                    return response(result);
                });
            },
            select: function (event, ui) {
                $(this).val(ui.item.label);
                $("#itemMainFabricId").val(ui.item.id);
                $("#item-main-fabric-label").val(ui.item.label);
                $("#item-main-fabric-short-label").val(ui.item.shortlabel);
            },
            minLength: 2
        });


        $("#itemPunoFabric").catcomplete({
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
                                    result.push({shortlabel: e2.code + " " + e2.shortname, label: e2.code + " " + e2.longname, category: i, id: e2.id});
                                }
                            });
                        }
                    });
                    return response(result);
                });
            },
            select: function (event, ui) {
                $(this).val(ui.label);
                $("#itemPunoFabricId").val(ui.item.id);
                $("#item-puno-fabric-label").val(ui.item.label);
                $("#item-puno-fabric-short-label").val(ui.item.shortlabel);
            },
            minLength: 2
        });
        $("#itemPunoFabric").blur(function () {
            if ($("#itemPunoFabric").val() != "") {
                $("#itemPunoFabric").val($("#item-puno-fabric-label").val());
            }
        });
        $("#itemCuellosFabric").catcomplete({
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
                                    result.push({shortlabel: e2.code + " " + e2.shortname, label: e2.code + " " + e2.longname, category: i, id: e2.id});
                                }
                            });
                        }
                    });
                    return response(result);
                });
            },
            select: function (event, ui) {
                $(this).val(ui.label);
                $("#itemCuellosFabricId").val(ui.item.id);
                $("#item-cuellos-fabric-label").val(ui.item.label);
                $("#item-cuellos-fabric-short-label").val(ui.item.shortlabel);
            },
            minLength: 2
        });
        $("#itemCuellosFabric").blur(function () {
            if ($("#itemCuellosFabric").val() != "") {
                $("#itemCuellosFabric").val($("#item-cuellos-fabric-label").val());
            }
        });

        $("#itemColor").catcomplete({
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
                $("#itemColorId").val(ui.item.id);
                $("#item-color-label").val(ui.item.label);
            },
            minLength: 2
        });
        $("#itemColor").blur(function () {
            if ($("#itemColor").val() != "") {
                $("#itemColor").val($("#item-color-label").val());
            }
        });
        this._inputPatternDetailList = [];
        $(".input-pattern-detail").autocomplete({
            delay: 0,
            source: function (request, response) {
                return response(pageController._inputPatternDetailList);
            },
            select: function (event, ui) {
                $(this).val(ui.item.label);
                $(this).next().val(ui.item.id);
                $(this).next().next().val(ui.item.label);
                $(this).next().next().next().val(ui.item.label);
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
        $(".input-pattern-detail").blur(function () {
            if ($(this).val() != "") {
                $(this).val($(this).next().next().val());
            }
        });
        $("#itemPunoCombination").autocomplete({
            delay: 0,
            source: function (request, response) {
                return response(pageController._inputPatternDetailList);
            },
            select: function (event, ui) {
                $(this).val(ui.label);
                $(this).next().val(ui.item.id);
                $(this).next().next().val(ui.item.label);
            },
            minLength: 0

        }).autocomplete("instance")._renderItem = function (ul, item) {
            var index = ($(ul).children().length + 1);
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
        $("#itemPunoCombination").blur(function () {
            if ($(this).val() != "") {
                $(this).val($(this).next().next().val());
            }

        });
        $("#itemCuellosCombination").autocomplete({
            delay: 0,
            source: function (request, response) {
                return response(pageController._inputPatternDetailList);
            },
            select: function (event, ui) {
                $(this).val(ui.label);
                $(this).next().val(ui.item.id);
                $(this).next().next().val(ui.item.label);
            },
            minLength: 0

        }).autocomplete("instance")._renderItem = function (ul, item) {
            var index = ($(ul).children().length + 1);
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
        $("#itemCuellosCombination").blur(function () {
            if ($(this).val() != "") {
                $(this).val($(this).next().next().val());
                //$("#itemCuellosCombination").val($(this).next().next().val());
            }

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
                $(this).val(ui.label);
                $(this).next().val(ui.item.id);
                $(this).next().next().val(ui.item.label);
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
            },
            minLength: 2
        });
        $(".input-pattern").blur(function () {
            if ($(this).val() != "") {
                $(this).val($(this).next().next().val());
            }

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
                { "width": "12%", "targets": 3 },
                { "width": "12%", "targets": 4 },
                { "width": "11%", "targets": 5 }
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

        //behavior of elements inside the dataTable    
        this._actionButtonsBehavior();

    };
    this._actionButtonsBehavior = function () {
        $(".deleteItem").click(function () {
            pageController.deleteItemInOrder(this);
        });
        $(".editItem").click(function () {
            pageController.editItemInOrder(this);
        });
    }


}
//itemMainFabric
NewOrderPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
NewOrderPageController.prototype.constructor = NewOrderPageController;

NewOrderPageController.prototype.initialize = function () {
    this.initializePage();
    this._initializeAutocompleteElements();
    this._initializeAddItemBehavior();
    this._initializeListItems();

    if ($("#id").val() > 0) {
        $(".autocomplete-client").prop("disabled", true);
        $("#btn-add-item-table").removeClass('disabled');
        $("#btn-add-item-top").removeClass('disabled');
        $("#btn-print").removeClass('disabled');
        $("#btn-accept").removeClass('disabled');
    }
};
NewOrderPageController.prototype.acceptOrder = function () {
    var url = $("form").attr("action");

    pageController.doPost(url, $("form").serialize(), function (data) {
        window.location.href = 'list-orders.html'
    });
};
NewOrderPageController.prototype.clearAddNewItemForm = function () {
    pageController._addOrderItemCleanup();
};

NewOrderPageController.prototype.addNewItemToOrder = function () {
    var errors = pageController._validateOrderItem();
    if (errors.length == 0) {
        pageController._addOrderItem();
        pageController.hideErrorMessage();
    } else {
        pageController.showErrorMessage(errors);
    }
};
NewOrderPageController.prototype.deleteItemInOrder = function (obj) {
    var rowToDelete = $(obj).parents('tr');
    var rowNumber = $(obj).next().val();
    var items = pageController.DataTable.rows().nodes().length;
    if (items > 1) {
        pageController.DataTable.row(rowToDelete).remove();
        pageController.DataTable.draw();
        if ($(".order-item-" + rowNumber + ".order-item-accesory").length > 0) {
            $(".order-item-" + rowNumber + ".order-item-accesory").remove();
        }

        $(".order-item-" + rowNumber).val(0);
    } else {
        pageController.showErrorMessage("Único item en el pedido no se puede eliminar.");
    }
};


NewOrderPageController.prototype.editItemInOrder = function (obj) {
    var rowToDelete = $(obj).parents('tr');
    var itemNumber = $(obj).next().next().val();
    var rowNumber = parseInt($($(rowToDelete).children()[0]).text() - 1);

    $("#edit-row-number").val(rowNumber);
    $("#edit-item-number").val(itemNumber);
    var itemClass = ".order-item-" + itemNumber;
    var mainItemClass = itemClass + ".is-main";
    $("#itemMainAmount").val(parseInt($(mainItemClass + ".fabric-amount").val()));
    $("#item-main-fabric-short-label").val($(mainItemClass + ".fabric-name").val())
    $("#item-main-fabric-label").val($(mainItemClass + ".fabric-longname").val());
    $("#item-color-label").val($(mainItemClass + ".color-name").val());
    $("#itemColorId").val($(mainItemClass + ".color-id").val());
    $("#itemColor").val($(mainItemClass + ".color-name").val());
    $("#itemMainPatternId").val($(mainItemClass + ".stripe-id").val());
    $("#itemMainPattern").val($(mainItemClass + ".stripe-name").val());
    $("#itemMainFabricId").val($(mainItemClass + ".fabric-id").val());
    $("#itemMainCombinationId").val($(mainItemClass + ".stripe-combination-id").val());
    $("#item-main-combination-label").val($(mainItemClass + ".stripe-combination-name").val());
    $("#itemMainFabric").val($(mainItemClass + ".fabric-name").val());
    $("#itemMainCombination").val($(mainItemClass + ".stripe-combination-name").val());


    if ($(itemClass + ".is-puno").length > 0) {

        var punoItemClass = itemClass + ".is-puno";
        $("#itemPunoAmount").val($(punoItemClass + ".fabric-amount").val());
        $("#itemPunoFabric").val($(punoItemClass + ".fabric-name").val());
        $("#itemPunoPatternId").val($(punoItemClass + ".stripe-id").val());
        $("#itemPunoPattern").val($(punoItemClass + ".stripe-name").val());
        $("#itemPunoFabricId").val($(punoItemClass + ".fabric-id").val());
        $("#itemPunoCombinationId").val($(punoItemClass + ".stripe-combination-id").val());
        $("#item-puno-combination-label").val($(punoItemClass + ".stripe-combination-name").val());
        $("#item-puno-fabric-short-label").val($(punoItemClass + ".fabric-name").val());
        $("#itemPunoCombination").val($(punoItemClass + ".stripe-combination-name").val());

    }


    if ($(itemClass + ".is-cuellos").length > 0) {

        var cuellosItemClass = itemClass + ".is-cuellos";

        $("#itemCuellosAmount").val(parseInt($(cuellosItemClass + ".fabric-amount").val()));
        $("#itemCuellosFabric").val($(cuellosItemClass + ".fabric-name").val());
        $("#itemCuellosPatternId").val($(cuellosItemClass + ".stripe-id").val());
        $("#itemCuellosPattern").val($(cuellosItemClass + ".stripe-name").val());
        $("#itemCuellosFabricId").val($(cuellosItemClass + ".fabric-id").val());
        $("#itemCuellosCombination").val($(cuellosItemClass + ".stripe-combination-name").val());
        $("#itemCuellosCombinationId").val($(cuellosItemClass + ".stripe-combination-id").val());
        $("#item-cuellos-pattern-label").val($(cuellosItemClass + ".stripe-combination-name").val());
        $("#item-cuellos-combination-label").val($(cuellosItemClass + ".stripe-combination-name").val());
        $("#item-cuellos-fabric-short-label").val($(cuellosItemClass + ".fabric-name").val());


    }

    $("#addOrderItemModal").find(":text").prop("disabled", true);
    $("fieldset")[2].disabled = false;
    $("fieldset")[1].disabled = false;
    $("#itemMainAmount").prop("disabled", false);
    $("#itemPunoAmount").prop("disabled", false);
    $("#itemCuellosAmount").prop("disabled", false);
    $("#addOrderItemModal").modal('toggle');
    $("#addOrderItemModal #add-order-item").text("Modificar item");

};


var pageController = new NewOrderPageController();