function NewDeliveryOrderPageController() {
    PageController.call(this);
    this.prepearedItemsDataTable = {};
    this.deliveryItemsDataTable = {};
    this.deliveryOrder = {clientId: 0, items: [], print: false};
    this._initializeListings = function () {
        var language = {
            "lengthMenu": "Mostrando _MENU_ registros por p�gina",
            "zeroRecords": "Seleccione bultos a entregar",
            "info": "Mostrando página _PAGE_ de _PAGES_",
            "infoEmpty": "No hay informaci�n para mostrar",
            "infoFiltered": "(Filtrado sobre _MAX_ registros)",
            "search": "Buscar: ",
            "decimal": ",",
            "thousands": "."
        };

        this.prepearedItemsDataTable = $("#list_items_prepeared").DataTable({
            "language": language,
            "scrollY": "450px",
            "filter": false,
            "paging": false,
            "ordering": false,
            "info": false,
            "columnDefs": [
                { "width": "19%", "targets": 0 },
                { "width": "56%", "targets": 1 },
                { "width": "25%", "targets": 2 }
            ],
            footerCallback: function (row, data, start, end, display) {
                var api = this.api(), data;

                // Remove the formatting to get integer data for summation
                var counterNonKilograms = function (i) {
                    if (typeof i === 'string') {
                        var res = i.match(/kg/g);
                        if (res == null) {
                            i = 0;
                        } else {
                            i = 1;
                        }
                    }

                    return i;
                };
                var priceVal = function (i) {
                    var res = 0;
                    if (typeof i === 'string') {
                        if ($(i).is(".item-price")) {
                            res = $(i).text().trim();
                            res = res.replace(/[\$\.]/g, '');
                            i = res.replace(',', '.') * 1;
                        } else {
                            i = 0;
                        }
                    }
                    return i;
                };

                // Total over all pages
                data = api.column(0).data();
                total = data.length ? data.reduce(function (a, b) {
                    return counterNonKilograms(a) + counterNonKilograms(b);
                }) : 0;

                // Update footer
                $(api.column(0).footer()).html('<label style="float:left;">' + parseFloat(total) + ' Bultos</label>');

                data = api.column(2).data();
                total = data.length ? data.reduce(function (a, b) {
                    return priceVal(a) + priceVal(b);
                }) : 0;

                // Update footer
                $(api.column(2).footer()).html('<label style="float:right;">' + formatCurrencyFromDouble(total) + '</label>');
            },
            "drawCallback": function (settings) {
                pageController._fnDrawCallBack();
            }
        });

        this.deliveryItemsDataTable = $("#list_items_delivery").DataTable({
            "language": language,
            "scrollY": "450px",
            "filter": false,
            "paging": false,
            "ordering": false,
            "info": false,
            "columnDefs": [
                { "width": "19%", "targets": 0 },
                { "width": "56%", "targets": 1 },
                { "width": "25%", "targets": 2 }
            ],
            footerCallback: function (row, data, start, end, display) {
                var api = this.api(), data;
                var cant = 0, totalAmount;
                // Remove the formatting to get integer data for summation
                var intVal = function (i) {
                    if (typeof i === 'string') {
                        var res = i.match(/kg/g);
                        if (res == null) {
                            i = parseInt(i);
                        } else {
                            i = 0;
                        }
                    }

                    return i;
                };
                var priceVal = function (i) {
                    var res = 0;
                    if (typeof i === 'string') {
                        if ($(i).is(".item-price")) {
                            res = $(i).text().trim();
                            res = res.replace(/[\$\.]/g, '');
                            i = res.replace(',', '.') * 1;
                        } else {
                            i = 0;
                        }
                    }
                    return i;
                };


                // Total over all pages
                data = api.column(0).data();
                cant = data.length ? data.reduce(function (a, b) {
                    return intVal(a) + intVal(b);
                }) : 0;

                // Update footer
                $(api.column(0).footer()).html('<label style="float:left;">' + parseFloat(cant) + ' Bultos </label>');

                data = api.column(2).data();
                totalAmount = data.length ? data.reduce(function (a, b) {
                    return priceVal(a) + priceVal(b);
                }) : 0;

                // Update footer
                $(api.column(2).footer()).html('<label style="float:right;padding:8px 0px;" id="total-amount-delivery">' + formatCurrencyFromDouble(totalAmount) + '</label>');
            },
            "order": [
                [ 0, "desc" ]
            ],
            "drawCallback": function (settings) {
                pageController._fnDrawCallBack();
            }
        });
        this.deliveryOrder.clientId = $("#name").data("client-id");
        this.deliveryOrder.orderId = $("#name").data("order-id");

        $("tr td").click(function () {
            var row = $(this).parent("tr");
            if ($(row).is(".active")) {
                $(row).removeClass("active");
                if ($(row).is(".row-group")) {
                    $(row).addClass("group");
                }
            } else {
                $("tr.active.row-group").addClass("group");
                $("tr").removeClass("active");

                $(row).addClass("active");
                if ($(row).is(".row-group")) {
                    $(row).removeClass("group");
                }
            }
            $(".btn-move").prop("disabled", false);
            $(".btn-move").removeClass("disabled");
            if ($("tr.active").is(".row-group")) {
                $("#add-single-element").addClass("disabled");
                $("#add-single-element").prop("disabled", true);
                $("#remove-single-element").addClass("disabled");
                $("#remove-single-element").prop("disabled", true);
            } else {
                $("#add-single-item").addClass("disabled");
                $("#add-single-item").prop("disabled", true);
                $("#remove-single-item").addClass("disabled");
                $("#remove-single-item").prop("disabled", true);
            }
        });
        $(".package-total").each(function (i, e) {
            var price = parseFloat($(e).data("price"));
            var weight = parseFloat($(e).data("weight"));
            $(e).text(formatCurrencyFromDouble(price * weight));
        });


    };
    this._fnDrawCallBack = function () {
        $(".dataTables_scrollHead .table").css("margin-bottom", "0px");
        $(".dataTables_scrollBody .table").css("margin-bottom", "0px");
        $(".dataTables_scrollBody thead th").removeClass("sorting");
        $(".dataTables_scrollBody thead th").removeClass("sorting_asc");
        $(".dataTables_scrollBody thead th").removeClass("sorting_desc");
        var heightHeader = $(".dataTables_scrollBody thead th").height();
        var height = $(".dataTables_scrollBody").height();
        $(".dataTables_scrollBody").height(height - heightHeader);
        $(".total-amount").css("padding", "8px 0px");
    };
    this._initializeButtons = function () {
        $(".balance").each(function (i, e) {
            var value = $(e).val().replace("$", "");
            if (parseFloat(value) < 0) {
                $(e).text($(e).val().replace("-", ""));
                $(e).css("color", " red");
            } else {
                $(e).css("color", "green");
            }

            $(e).css("font-weight", " bolder");
        });

        $("#name").prop("disabled", true);

        $("#add-single-element").click(function () {
            pageController._updateDeliveryOrder();
        });

        $("#add-single-item").click(function () {

            pageController._updateDeliveryOrder();
        });

        $("#add-all-items").click(function () {

            $("#list_items_prepeared tr").each(function (index, row) {
                if ($(row).is(".row-group") && $(row).data("status") == 'ORDERED') {
                    $(row).addClass("active");
                    pageController._updateDeliveryOrder();
                    $(row).removeClass("active");
                    $(row).addClass("group");
                }

            });
        });

        $("#remove-single-element").click(function () {
            pageController._updateDeliveryOrderReturnedItem();

        });
        $("#remove-single-item").click(function () {
            pageController._updateDeliveryOrderReturnedItem();
        });

        $("#remove-all-items").click(function () {
            pageController._removeAllSelectedItems();
        });

        $("#btn-accept").click(function () {
            var total = $("#total-amount-delivery").text();
            if (pageController.deliveryOrder.items.length == 0) {
                pageController.showErrorMessage("Debe seleccionar al menos 1 paqute.");
                return;
            }
            pageController.confirmMessage("Confirma que desea registrar una orden de entrega por " + total + " ?", function () {
                pageController.deliveryOrder.print = true;
                pageController.generateNewDeliveryOrder();
            }, pageController.doNothing());

//            pageController.confirmMessage("Desea imprimir el ticket/factura?",function(){
//                pageController.deliveryOrder.print=true;
//                pageController.generateNewDeliveryOrder();
//            },function(){
//                pageController.deliveryOrder.print = false;
//                pageController.generateNewDeliveryOrder();
//            });

        });


    };
    this._removeAllSelectedItems = function () {
        var isMarked = false;
        $("#list_items_prepeared tbody tr").each(function (index, row) {
            isMarked = !$(row).is(".row-group");

            isMarked = isMarked && $(row).find(".mark").css("display") != 'none';
            isMarked = isMarked && !$(row).is(".success");

            if (isMarked) {
                $(row).addClass("active");
                pageController._updateDeliveryOrderReturnedItem();
                $(row).removeClass("active");
            }

        });
    };
    this._updateRowStatusInPrepearedItemsTable = function (selectedRow) {
        var markName = "";

        if (selectedRow.is(".selected-for-delivery")) {
            if (selectedRow.is(".row-group")) {
                markName = selectedRow.attr("id");
                $("." + markName + " .check-mark").css("display", "none");
                $("." + markName + " .package-total").css("display", "inline-block");
                $("." + markName).removeClass("selected-for-delivery");
            } else {
                var classes = selectedRow.attr("class").split(' ');
                $.each(classes, function (index, element) {
                    var aux = element.match(/row_item_/g);
                    if (aux != null) {
                        markName = element;
                    }
                });
                selectedRow.find(".package-total").css("display", "inline-block");
                selectedRow.find(".check-mark").css("display", "none");
                $("#" + markName).removeClass("selected-for-delivery");
            }
            selectedRow.removeClass("selected-for-delivery");

        } else {
            if (selectedRow.is(".row-group")) {
                markName = selectedRow.attr("id");
                $("." + markName + " .check-mark").css("display", "inline-block");
                $("." + markName + " .package-total").css("display", "none");
                $("." + markName).addClass("selected-for-delivery");
            } else {
                selectedRow.find(".package-total").css("display", "none");
                selectedRow.find(".check-mark").css("display", "inline-block");
            }
            selectedRow.addClass("selected-for-delivery");
        }

    };
    this._updateDeliveryItemsTable = function () {
        pageController.deliveryItemsDataTable.clear();
        pageController.deliveryOrder.items.sort(function (elementA, elementB) {
            return elementA.productId - elementB.productId;
        });
        if (pageController.deliveryOrder.items.length == 0) {
            pageController.deliveryItemsDataTable.draw();
            return;
        }
        var productIdAux = pageController.deliveryOrder.items[0].productId;
        var total = 0;
        var counter = 0;
        var rowsToAdd = [];
        var items = pageController.deliveryOrder.items;
        var rowHeader;
        var createdRow;
        $.each(items, function (index, element) {

            if (element.productId != productIdAux) {
                createdRow = pageController.deliveryItemsDataTable.row.add(rowHeader).node();
                $(createdRow).addClass("group row-group");
                total = 0;
                counter = 0;
                pageController.deliveryItemsDataTable.rows.add(rowsToAdd);
                rowsToAdd = [];
                productIdAux = element.productId;
            }
            total = element.productPrice * element.productWeight + total;
            counter++;

            rowsToAdd.push([parseFloat(element.productWeight).toFixed(2) + "kg", element.productName, "<label>" + formatCurrencyFromDouble(element.productPrice * element.productWeight) + "</label>"]);
            var headerPriceColumn = "<label class='text-center item-price'>" + formatCurrencyFromDouble(total) + "</label>";
            rowHeader = [counter + "", element.productName, headerPriceColumn];
        });

        createdRow = pageController.deliveryItemsDataTable.row.add(rowHeader).node();
        $(createdRow).addClass("group row-group");
        $.each(rowsToAdd, function (i, e) {
            pageController.deliveryItemsDataTable.row.add(e);
        });


        pageController.deliveryItemsDataTable.draw();

    };
    this._updateDeliveryOrderReturnedItem = function () {
        var selectedRow = $("#list_items_prepeared tr.active");
        var productCell = selectedRow.find("td.product").length == 0 ? selectedRow.find("td.package") : selectedRow.find("td.product");


        if (!selectedRow.is(".selected-for-delivery") || productCell == null || productCell == "") {
            alert("El producto no está marcado para ser entregado.");
            return;
        }


        var indexesToRemove = [];
        var j = 0;
        var amountToRemove = 1;

        if (selectedRow.is(".row-group")) {
            amountToRemove = $("." + selectedRow.attr("id")).find("td.package").length;
        }
        $.each(pageController.deliveryOrder.items, function (index, element) {
            if (j < amountToRemove && element.productId == productCell.data("product-id")) {
                indexesToRemove[j++] = index;
            }
        });

        for (var i = indexesToRemove.length - 1; i >= 0; i--) {
            pageController.deliveryOrder.items.splice(i, 1);
        }


        pageController._updateDeliveryItemsTable();
        pageController._updateRowStatusInPrepearedItemsTable(selectedRow);
    };
    this._updateDeliveryOrder = function () {
        var selectedRow = $("#list_items_prepeared tr.active");
        var productCell = selectedRow.find("td.product").length == 0 ? selectedRow.find("td.package") : selectedRow.find("td.product");


        if (selectedRow.is(".selected-for-delivery")) {
            alert("El producto ya fue seleccionado para ser entregado.");
            return;
        }
        if (selectedRow.is(".success") || selectedRow.is(".danger")) {
            alert("Operación invalida sobre esta fila.");
            return;
        }
        if (productCell == null || productCell == "") {
            alert("Debe seleccionar un elemento a entregar.");
            return;
        }
        var weights = [];
        var orderItemDetailIds = [];
        var display = "";
        var h = 0;
        if (selectedRow.is(".row-group")) {
            $("." + selectedRow.attr("id")).find("td.package").each(function (i, e) {
                display = $(e).next().find(".check-mark").css("display");
                if (display == 'none') {
                    weights[h] = $(e).data("package-weight");
                    orderItemDetailIds[h++] = $(e).data("package-id");
                }
            });


        } else {
            weights[0] = productCell.data("package-weight");
            orderItemDetailIds[0] = productCell.data("package-id");
        }

        if (weights.length == 0) {
            alert("Ha seleccionado productos que ya fueron entregados.");
            return;
        }

        for (var i = 0; i < weights.length; i++) {

            var newElement = {
                productId: productCell.data("product-id"),
                productPrice: productCell.data("product-price"),
                productName: productCell.data("product-name"),
                orderItemDetailId: orderItemDetailIds[i],
                productWeight: parseFloat(weights[i])
            };
            pageController.deliveryOrder.items.push(newElement);
        }


        pageController._updateRowStatusInPrepearedItemsTable(selectedRow);
        pageController._updateDeliveryItemsTable();


    };
}
NewDeliveryOrderPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
NewDeliveryOrderPageController.prototype.constructor = NewDeliveryOrderPageController;

NewDeliveryOrderPageController.prototype.initialize = function () {
    this.initializePage();
    this._initializeListings();
    this._initializeButtons();
};
NewDeliveryOrderPageController.prototype.generateNewDeliveryOrder = function () {

    var url = "add-delivery.json";
    var list = JSON.stringify(pageController.deliveryOrder);

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
                document.location.href = "list-delivery-orders.html";
            } else {
                pageController.showErrorMessage(response.errorListDetailList);
            }


        }
    });
}

var pageController = new NewDeliveryOrderPageController();