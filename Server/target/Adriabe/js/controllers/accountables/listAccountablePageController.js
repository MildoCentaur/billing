/**
 * Created by Mildo on 10/4/14.
 */
function ListAccountablePageController() {
    PageController.call(this);

    this.DataTable = '';
    this._initializeListing = function () {
        this.DataTable = $('#list-account').DataTable({
            "language": {
                "lengthMenu": "Mostrando _MENU_ registros por p�gina",
                "zeroRecords": "No se registran operaciones en la cuenta",
                "info": "Mostrando _MAX_ registros",
                "infoEmpty": "No hay informaci�n para mostrar",
                "infoFiltered": "(Filtrado sobre _MAX_ registros)",
                "search": "Buscar: ",
                "decimal": ",",
                "thousands": "."
            },
            "scrollY": "600px",
            "scrollCollapse": true,
            "paging": false,
            "info": false,
            stateSave: true,
            "columnDefs": [
                {
                    "targets": [ 0 ],
                    "visible": false,
                    "searchable": false

                },
                { "type": "string", "targets": [0, 1] },
                {
                    "render": function (data, type, full, meta) {
                        var color = "font-color:green;"
                        console.log(data.replace(".", "").replace(",", "."));
                        if (parseFloat(data.replace(".", "").replace(",", ".")) < 0) {
                            color = "font-color:red;"
                        }
                        return "<label style='float:right;" + color + "'>" + formatCurrencyFromDouble(data) + "</label>";
                    },
                    "targets": [5]
                },
                {
                    "render": function (data, type, full, meta) {
                        return "<label style='float:right;'>" + formatCurrencyFromDouble(data) + "</label>";
                    },
                    "targets": [3, 4]
                },
                {
                    "sClass": "greenText",
                    "aTargets": [ 3 ]
                },
                {
                    "sClass": "redText",
                    "aTargets": [ 4 ]
                }
            ],

            "footerCallback": function (row, data, start, end, display) {
                var api = this.api(), data;

                // Remove the formatting to get integer data for summation
                var intVal = function (i) {
                    if (typeof i === 'string') {
                        i = parseFloat(i);
                    }
                    else {
                        i = (typeof i === 'number') ? i : 0;
                    }

                    return i;
                };
                // Total over all pages
                data = api.column(4).data();
                var total = data.length ?
                    data.reduce(function (a, b) {
                        return intVal(a) + intVal(b);
                    }) : 0;

                // Update footer
                $(api.column(4).footer()).html(formatCurrency(intVal(total)));
                // Total over all pages
                data = api.column(3).data();
                total = data.length ?
                    data.reduce(function (a, b) {
                        return intVal(a) + intVal(b);
                    }) : 0;

                // Update footer
                $(api.column(3).footer()).html(formatCurrency(intVal(total)));


            },
            "order": [
                [ 0, "desc" ]
            ],
            "drawCallback": function (settings) {
                var heightHeader = $(".dataTables_scrollBody thead th").height();
                $(".dataTables_scrollHead .table").css("margin-bottom", "0px");
                $(".dataTables_scrollBody .table").css("margin-bottom", "0px");
                $(".dataTables_scrollBody thead th").removeClass("sorting");
                $(".dataTables_scrollBody thead th").removeClass("sorting_asc");
                $(".dataTables_scrollBody thead th").removeClass("sorting_desc");

                var height = $(".dataTables_scrollBody").height();
                $(".dataTables_scrollBody").height(height - heightHeader)
            }

        });
    };


    this._reloadTable = function (id, entity, callback) {

        var newUrl = "data/list-account/" + entity + "/" + $("#account-type").val() + "/" + id + ".json";

        if (id != 0) {
            this.reloadTable(newUrl, callback);

        } else {
            pageController.showErrorMessage("Debe ingresar el nombre de un cliente.");
        }


        return true;
    };
    this._listingAdjustments = function () {
        /*Elimina el margen que queda por el scroll*/
        ;


        $('#list-account tbody').on('click', 'tr', function () {
            if ($(this).hasClass('active')) {
                $(this).removeClass('active');
                $("#btn-show").addClass("disabled").removeClass("btn-outline");
            } else {
                $('tr.active').removeClass('active');
                $(this).addClass('active');
                $("#btn-show").removeClass("disabled").addClass("btn-outline");
            }

        });


        $("#list-account_filter").parent().prev().addClass("btn-toolbar").html(
                '<button type="button" class="btn btn-outline btn-primary" id="btn-print"><i class="fa fa-print"></i></button>' +
                '<button type="button" class="btn btn-outline btn-primary" id="btn-show"><i class="fa fa-search"></i></button>' +
                '<button type="button" class="btn btn-outline btn-success" id="btn-add-payment">Registrar Pago</button>');

        $("#list-account_filter").css("text-align", "right");


        $("#btn-show").addClass("disabled").removeClass("btn-outline");
        $("#btn-add-payment").addClass("disabled").removeClass("btn-outline");
        $("#btn-print").addClass("disabled").removeClass("btn-outline");


        $("#btn-show").click(function () {
            if ($('tr.active').length == 0) {
                pageController.showErrorMessage("Debe seleccionar un elemento de la lista.");
                return;
            }
            var oRow = pageController.DataTable.row('.active').data();

            var idSelected = $(oRow)[0];
            window.location.href = 'show-accountable-document.html?id=' + idSelected;
        });

        $("#btn-add-payment").click(function () {
            window.location.href = 'new-' + $("#accountable-entity").val() + '-payment.html';
        });

        $("#entity-name").catcomplete({
            delay: 10,
            source: function (request, response) {
                var name = request.term;
                return jQuery.get("search-entities.json?query=" + name, function (data) {
                    result = [];
                    pageController.sessionTime = 1;
                    $.each(data, function (i, e) {

                        if ($("#accountable-entity").val() == "client" && i.toLowerCase() == "clientes") {
                            $.each(e, function (i2, e2) {
                                result.push({label: e2.fullName, category: i, id: e2.id});
                            });
                        } else if ($("#accountable-entity").val() == "supplier" && i.toLowerCase() == "proveedores") {
                            $.each(e, function (i2, e2) {
                                result.push({label: e2.fullName, category: i, id: e2.id});
                            });
                        }
                    });
                    return response(result);
                });
            },
            select: function (event, ui) {
                $(this).val(ui.label);
                $("#accountable-id").val(ui.item.id);
                pageController._reloadTable(ui.item.id, $("#accountable-entity").val(), pageController.reloadTableCallback);
            },
            minLength: 2
        });

    }

}
ListAccountablePageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
ListAccountablePageController.prototype.constructor = ListAccountablePageController;

ListAccountablePageController.prototype.initialize = function () {
    this.initializePage();
    this._initializeListing();
    this._listingAdjustments();
};
ListAccountablePageController.prototype.reloadTableRowMapping = function (index, data) {
    return [data.id, data.accountabletDate, data.concept, data.debit, data.credit, data.balance];
};
ListAccountablePageController.prototype.reloadedTableCallback = function () {
    $("#btn-add-payment").removeClass("disabled").addClass("btn-outline");
    $("#btn-print").removeClass("disabled").addClass("btn-outline");

};


var pageController = new ListAccountablePageController();
