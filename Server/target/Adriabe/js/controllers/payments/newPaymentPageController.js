/**
 * Created by Mildo on 10/4/14.
 */
function NewPaymentPageController() {
    PageController.call(this);


    this.payment = {entityId: 0, entityType: 0, deposits: [], cheques: [], cash: 0, paying: [], total: 0};

    this._initializeListing = function () {
        $('.list-payments').DataTable({
            "language": {
                "lengthMenu": "Mostrando _MENU_ registros por página",
                "zeroRecords": "No se registran operaciones en la cuenta",
                "info": "Mostrando _MAX_ registros",
                "infoEmpty": "No hay información para mostrar",
                "infoFiltered": "(Filtrado sobre _MAX_ registros)",
                "search": "Buscar: ",
                "decimal": ",",
                "thousands": "."
            },
            "columnDefs": [
                { "width": "10%", "targets": 0 },
                { "width": "35%", "targets": 1 },
                { "width": "35%", "targets": 2 },
                { "width": "20%", "targets": 3 }
            ],
            "scrollY": "200px",
            "scrollCollapse": true,
            "info": false,
            "filter": true,
            "paging": false,
            "ordering": false,
            "footerCallback": function (row, data, start, end, display) {
                var api = this.api(), data;

                // Remove the formatting to get integer data for summation
                var intVal = function (i) {
                    if (typeof i === 'string') {
                        i = i.replace(",", ".");
                        i = parseFloat(i);
                    }
                    else {
                        i = (typeof i === 'number') ? i : 0;
                    }

                    return i;
                };
                // Total over all pages
                data = api.column(3).data();
                total = data.length ?
                    data.reduce(function (a, b) {
                        return intVal(a) + intVal(b);
                    }) : 0;

                // Update footer
                $(api.column(3).footer()).html(formatCurrency(total));


            },
            "drawCallback": function (settings) {
                $(".dataTables_scrollHead .table").css("margin-bottom", "0px");
                $(".dataTables_scrollBody .table").css("margin-bottom", "0px");
                $(".dataTables_scrollBody thead th").removeClass("sorting");
                $(".dataTables_scrollBody thead th").removeClass("sorting_asc");
                $(".dataTables_scrollBody thead th").removeClass("sorting_desc");
                $(".dataTables_scrollHeadInner").css("width", "100%");
                $(".dataTables_scrollFootInner").css("width", "100%");
                var heightHeader = $(".dataTables_scrollBody thead th").height();
                var height = $(".dataTables_scrollBody").height();
                $(".dataTables_scrollBody").height(height - heightHeader);
            }

        });
        $(".dataTables_scrollHeadInner").css("width", "100%");
        $(".dataTables_scrollFootInner").css("width", "100%");
        $($("#list-deposit_wrapper table")[0]).css("width", "100%");
        $($("#list-deposit_wrapper table")[2]).css("width", "100%");
        $($("#list-cheque_wrapper table")[0]).css("width", "100%");
        $($("#list-cheque_wrapper table")[2]).css("width", "100%");
    };
    this._addPageBehavior = function () {
        $("#btn-register-deposit").click(function () {
            pageController.addDepositPayment();
        });
        $("#btn-register-cheque").click(function () {
            pageController.addChequePayment();
        });
        $("#btn-register-payment").click(function () {
            pageController.registerPayment();
        });
        $("#cash").click(function () {
            $(this).css("border", "1px solid #ccc");
            $(this).val("");
        });
        $("#cheque-value").blur(function () {
            var value = parseFloat($(this).val().replace(",", "."));
            if (isNaN(value)) {
                $(this).css("border", "1px solid red");
                return;
            }
            $(this).data("value", value);
            $(this).val(formatCurrencyFromDouble(value));
        });
        $("#transaction-amount").blur(function () {
            var value = parseFloat($(this).val().replace(",", "."));
            if (isNaN(value)) {
                $(this).css("border", "1px solid red");
                return;
            }
            $(this).data("value", value);
            $(this).val(formatCurrencyFromDouble(value));
        });

        $("#cash").blur(function () {

            var value = parseFloat($(this).val().replace(",", "."));
            if (isNaN(value)) {
                $(this).css("border", "1px solid red");
                return;
            }
            $(this).data("value", value);
            pageController.payment.cash = value;
            $(this).val(formatCurrencyFromDouble(value));
            pageController._updateTotalPaymentValue();
        });


        $("#entity-name").catcomplete({
            delay: 10,
            source: function (request, response) {
                var name = request.term;
                return jQuery.get("search-entities.json?query=" + name, function (data) {
                    result = [];
                    pageController.sessionTime = 1;
                    $.each(data, function (i, e) {

                        if ($("#accountable-entity").val() == "client" && i.toLowerCase() == "clientes" ||
                            $("#accountable-entity").val() == "supplier" && i.toLowerCase() == "proveedores") {

                            $.each(e, function (i2, e2) {
                                result.push({label: e2.fullName, category: i, id: e2.id, balance: e2.balanceBilling,
                                    activities: e2.balanceActivity});
                            });
                        }
                    });
                    return response(result);
                });
            },
            select: function (event, ui) {
                $(this).val(ui.label);
                $("#accountable-id").val(ui.item.id);
                pageController.payment.entityId = ui.item.id;

                pageController.payment.entityType = $("#accountable-entity").val();
                $("#balanceBilling").val(formatCurrencyFromDouble(ui.item.balance));
                $("#balanceActivities").val(formatCurrencyFromDouble(ui.item.activities));
                $("#balanceBilling").prop("disabled", true);
                $("#balanceActivities").prop("disabled", true);
                $(".btn-modal").removeClass("disabled");
                $("#btn-register-payment").removeClass("disabled");
                var url = "data/list-unpaids/" + pageController.payment.entityType + "/" + ui.item.id + ".json";
                pageController.doGet(url, {}, pageController.loadTableUnpaid);
            },
            minLength: 2
        });
        $(".bank-name").catcomplete({
            delay: 10,
            source: function (request, response) {
                var name = request.term;
                return jQuery.get("search-bank.json?query=" + name, function (data) {
                    result = [];
                    pageController.sessionTime = 1;
                    $.each(data, function (i, e) {
                        result.push({label: e.code + " " + e.shortName, category: 'Bancos', id: e.id, shortname: e.shortName});
                    });
                    return response(result);
                });
            },
            select: function (event, ui) {
                $(this).val(ui.label);
                $(this).data("bank-id", ui.item.id);
                $(this).data("bank-shortname", ui.item.shortname);
            },
            minLength: 2
        });

    };
    this._listingAdjustments = function () {

        $('#list-deposit tbody').on('click', 'tr', function () {
            if ($(this).hasClass('active')) {
                $(this).removeClass('active');
                $("#btn-edit-deposit").addClass("disabled").removeClass("btn-outline");
                $("#btn-delete-deposit").addClass("disabled").removeClass("btn-outline");
            } else {
                $('tr.active').removeClass('active');
                $(this).addClass('active');
                $("#btn-edit-deposit").addClass("btn-outline").removeClass("disabled");
                $("#btn-delete-deposit").addClass("btn-outline").removeClass("disabled");
            }
        });


        $("#list-deposit_filter").parent().prev().addClass("btn-toolbar").html(
                '<button type="button" class="btn btn-warning disabled btn-edit" id="btn-edit-deposit"><i class="fa fa-pencil"></i>Editar</button>' +
                '<button type="button" class="btn btn-danger disabled btn-delete" id="btn-delete-deposit"><i class="fa fa-trash"></i>Eliminar</button>');

        $('#list-cheque tbody').on('click', 'tr', function () {
            if ($(this).hasClass('active')) {
                $(this).removeClass('active');
                $("#btn-edit-cheque").addClass("disabled").removeClass("btn-outline");
                $("#btn-delete-cheque").addClass("disabled").removeClass("btn-outline");
            } else {
                $('tr.active').removeClass('active');
                $(this).addClass('active');
                $("#btn-edit-cheque").addClass("btn-outline").removeClass("disabled");
                $("#btn-delete-cheque").addClass("btn-outline").removeClass("disabled");
            }
        });


        $("#list-cheque_filter").parent().prev().addClass("btn-toolbar").html(
                '<button type="button" class="btn btn-warning disabled btn-edit" id="btn-edit-cheque"><i class="fa fa-pencil"></i>Editar</button>' +
                '<button type="button" class="btn btn-danger disabled btn-delete" id="btn-delete-cheque"><i class="fa fa-trash"></i>Eliminar</button>');
        $(".dataTables_filter").html("");
        $(".btn-edit").click(function () {
            alert("funcionalidad no implementada todavía");
        });
    }

    this._validateDeposit = function () {
        var result = true;
        var bValid = $("#transaction-account").val() > 0;
        result = bValid && result;
        if (!bValid) {
            $("#transaction-account").css("border", "1px solid red");
        }
        bValid = isValidNumber($("#transaction-number").val());
        result = bValid && result;
        if (!bValid) {
            $("#transaction-number").css("border", "1px solid red");
        }
        bValid = isValidMoneyExpression($("#transaction-amount").val());
        result = bValid && result;
        if (!bValid) {
            $("#transaction-amount").css("border", "1px solid red");
        }
        bValid = isValidAlphaNumeric($("#transaction-bank-source").val());
        result = bValid && result;
        if (!bValid) {
            $("#transaction-bank-source").css("border", "1px solid red");
        }
        return result;
    };

    this._addDepositToPayment = function () {

        var deposit = {
            accountId: $("#transaction-account").val(),
            number: $("#transaction-number").val(),
            amount: $("#transaction-amount").data("value"),
            bank: $("#transaction-bank-source").data("bank-id")
        }
        pageController.payment.deposits.push(deposit);
        var destination = $("#account").text();
        var index = $('#list-deposit').DataTable().row().indexes().length + 1;
        var row = [index,
            $("#transaction-bank-source").val(),
            destination,
            $("#transaction-amount").val()];
        $('#list-deposit').DataTable().row.add(row).draw();


        var prev = $("#total-deposit-value").data("value");
        var aux = $("#transaction-amount").data("value");
        $("#total-deposit-value").data("value", prev + aux);
        $("#total-deposit-value").val(formatCurrencyFromDouble(prev + aux));
        pageController._updateTotalPaymentValue();
    };

    this._validateCheque = function () {
        var result = true;
        var bValid = isValidAlphaNumeric($("#cheque-code").val());
        result = bValid && result;
        if (!bValid) {
            $("#cheque-code").css("border", "1px solid red");
        }
        bValid = isValidAlphaNumeric($("#cheque-bank").val());
        result = bValid && result;
        if (!bValid) {
            $("#cheque-bank").css("border", "1px solid red");
        }
        bValid = isValidNumber($("#cheque-number").val());
        result = bValid && result;
        if (!bValid) {
            $("#cheque-number").css("border", "1px solid red");
        }
        bValid = isValidNumber($("#cheque-account-number").val());
        result = bValid && result;
        if (!bValid) {
            $("#cheque-account-number").css("border", "1px solid red");
        }
        bValid = isValidName($("#cheque-name-signer").val());
        result = bValid && result;
        if (!bValid) {
            $("#cheque-name-signer").css("border", "1px solid red");
        }
        bValid = isValidCuitNumber($("#cheque-cuit-signer").val());
        result = bValid && result;
        if (!bValid) {
            $("#cheque-cuit-signer").css("border", "1px solid red");
        }
        bValid = !isEmpty($("#cheque-expiration-date").val());
        result = bValid && result;
        if (!bValid) {
            $("#cheque-expiration-date").css("border", "1px solid red");
        }

        return result;

    };


    this._addChequeToPayment = function () {
        var cheque = {
            bankId: $("#cheque-bank").data("bank-id"),
            bank: $("#cheque-bank").data("bank-shortname"),
            expirationDate: $("#cheque-expiration-date").val(),
            value: $("#cheque-value").data("value"),
            clientId: $("#accountable-id").val(),
            nameSigner: $("#cheque-name-signer").val(),
            cuitSigner: $("#cheque-cuit-signer").val(),
            number: $("#cheque-number").val(),
            account: $("#cheque-account-number").val(),
            code: $("#cheque-code").val(),
            others: $("#cheque-is-third-party").prop("checked"),
            crossed: $("#cheque-is-crossed").prop("checked")
        }

        pageController.payment.cheques.push(cheque);


        var row = [$("#cheque-bank").data("bank-shortname"),
            $("#cheque-number").val(),
            $("#cheque-expiration-date").val(),
            $("#cheque-value").val()];
        $('#list-cheque').DataTable().row.add(row).draw();

        var prev = $("#total-cheques-value").data("value");
        var aux = $("#cheque-value").data("value") + prev;

        $("#total-cheques-value").data("value", aux);
        $("#total-cheques-value").val(formatCurrencyFromDouble(aux));
        pageController._updateTotalPaymentValue();

    };
    this._updateTotalPaymentValue = function () {
        var total = $("#cash").data("value");
        total = total + $("#total-cheques-value").data("value");
        total = total + $("#total-deposit-value").data("value");
        $("#total-payment-value").val(formatCurrencyFromDouble(total));
        pageController.payment.total = total;

    };
    this._validatePayment = function () {
        var errors = [];
        var bValid;

        $(":text").css("border", "1px solid #ccc");

        bValid = !isEmpty($("#entity-name").val()) && $("#accountable-id").val() > 0;

        if (!bValid) {
            errors.push("Debe ingresar una razón social valida.");
            $("#entity-name").css("border", "1px solid red");
        }
        var checkedUpaids = $(".chk-unpaid:checked");
        if (checkedUpaids.length == 0) {
            errors.push("Debe seleccionar un item de la lista de " + $("#payables-header").text());
        }
        var found = false;
        checkedUpaids.each(function (index, element) {
            $(".chk-unpaid").each(function (i, element2) {
                if ($(element2).data("unpaid-id") < $(element).data("unpaid-id") && !found) {
                    if (!$(element2).prop("checked")) {
                        errors.push("Debe seleccionar " + $("#payables-header").text() + " anteriores.");
                        found = true;
                    }
                }
            });
        });

        if (pageController.payment.total == 0) {
            errors.push("No ha ingresado ningún pago.");
        }

        return errors;

    };

}
NewPaymentPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
NewPaymentPageController.prototype.constructor = NewPaymentPageController;

NewPaymentPageController.prototype.initialize = function () {
    this.initializePage();
    this._initializeListing();
    this._listingAdjustments();
    this._addPageBehavior();
};

NewPaymentPageController.prototype.loadTableUnpaid = function (data) {
    $("#list_items_to_deliver tbody tr").remove();
    var row = "";
    if (data.length > 0) {
        $.each(data, function (index, element) {
            row = "<tr id='row_" + index + "'>" +
                "<td><input type='checkbox' data-unpaid-id='" + element.id + "' class='chk-unpaid' value='0'/>Si</td>" +
                "<td>" + element.date + "</td>" +
                "<td>" + element.number + "</td>" +
                "<td>" + element.packagesAmount + "</td>" +
                "<td>" + element.totalAmount + "</td></tr>";
            $("#list_items_to_deliver tbody").append(row);

            if (index == 0 && element.documentType != 'DELIVERY_ORDER') {
                $("#payables-header").text("Facturas inpagas");
            } else if (index == 0 && element.documentType == 'DELIVERY_ORDER') {
                $("#payables-header").text("Ordenes de entrega inpagas");
            }
        });
    } else {
        row = "<tr id='row_1'>" +
            "<td><input type='checkbox' data-unpaid-id='-1' class='chk-unpaid' value='0'/>Si</td>" +
            "<td colspan='4'> Adelanto a cuenta de futuras operaciones</td></tr>";
        $("#list_items_to_deliver tbody").append(row);
    }
};
NewPaymentPageController.prototype.addDepositPayment = function () {
    $("input:text").css("border", "1px solid #ccc");

    if (pageController._validateDeposit()) {
        pageController._addDepositToPayment();
        $("#addDepositToPaymentModal").modal("hide");
        $("#addDepositToPaymentModal input:checkbox").prop("checked", false);
        $("#addDepositToPaymentModal input:text").val("");
    }

};
NewPaymentPageController.prototype.addChequePayment = function () {
    $("input:text").css("border", "1px solid #ccc");

    if (pageController._validateCheque()) {
        pageController._addChequeToPayment();
        $("#addchequeToPaymentModal").modal("hide");
        $("#addchequeToPaymentModal input:text").val("");
        $("#addchequeToPaymentModal input:checkbox").prop("checked", false);
    }
};


NewPaymentPageController.prototype.registerPayment = function () {
    var url = "register-payment.json";
    var errors = pageController._validatePayment();

    pageController.hideErrorMessage();
    if (errors.length > 0) {
        pageController.showErrorMessage(errors);
        return;
    }

    $(".chk-unpaid").each(function (index, element) {
        if ($(element).prop("checked")) {
            var unpaid = {
                id: $(element).data("unpaid-id")
            }
            pageController.payment.paying.push(unpaid);
        }
    });

    var payment = JSON.stringify(pageController.payment);
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: url,
        dataType: "json",
        data: payment,
        contentType: 'application/json',
        success: function (data) {

            if (data.hasErrors == 0) {
                pageController.showSuccessMessage();
                if ($("#accountable-entity").val() == 'client') {
                    //location.href="show-movement-client.html";
                } else {
                    location.href = "show-movement-supplier.html";
                }

            } else {
                pageController.showErrorMessage(data.errorListDetailList);
            }


        }
    });
};

var pageController = new NewPaymentPageController();