/**
 * Created by Mildo on 9/25/14.
 */

function ListOrderPageController() {
    PageController.call(this);
    this.DataTable = [];
    this._initializeListing = function () {
        this.DataTable = $('#list_orders').DataTable({
            "language": {
                "lengthMenu": "Mostrando _MENU_ registros por página",
                "zeroRecords": "Que verguenza no encontre nada :(",
                "info": "Mostrando página _PAGE_ de _PAGES_",
                "infoEmpty": "No hay informaci�n para mostrar",
                "infoFiltered": "(Filtrado sobre _MAX_ registros)",
                "search": "Buscar: ",
                "decimal": ",",
                "thousands": "."
            },
            "scrollY": "450px",
            "filter": true,
            "paging": false,
            "info": false,
            footerCallback: function (row, data, start, end, display) {
                var api = this.api(), data;

                // Remove the formatting to get integer data for summation
                var intVal = function (i) {
                    if (typeof i === 'string') {
                        i = i.replace(/[\$\.]/g, '');
                        i = i.replace(',', '.') * 1;
                    }
                    else {
                        i = (typeof i === 'number') ? i : 0;
                    }

                    return i;
                };


                // Total over all pages
                data = api.column(6).data();
                total = data.length ?
                    data.reduce(function (a, b) {
                        return intVal(a) + intVal(b);
                    }) :
                    0;

                // Update footer
                $(api.column(6).footer()).html('<label style="float:right;" class="currency">' + formatCurrency(total) + '</label>');
            },
            "order": [
                [ 0, "desc" ]
            ],
            "drawCallback": function (settings) {
                $(".dataTables_scrollHead .table").css("margin-bottom", "0px");
                $(".dataTables_scrollBody .table").css("margin-bottom", "0px");
                $(".dataTables_scrollBody thead th").removeClass("sorting");
                $(".dataTables_scrollBody thead th").removeClass("sorting_asc");
                $(".dataTables_scrollBody thead th").removeClass("sorting_desc");
                var heightHeader = $(".dataTables_scrollBody thead th").height();
                var height = $(".dataTables_scrollBody").height();
                $(".dataTables_scrollBody").height(height - heightHeader)
            }
        });
    }

    this._listingAdjustments = function () {

        $('#list_orders tbody').on('click', 'tr', function () {
            $("#btn-delete").addClass("disabled").removeClass("btn-outline");
            $("#btn-edit").addClass("disabled").removeClass("btn-outline");
            $("#btn-show").addClass("disabled").removeClass("btn-outline");
            $("#btn-deliver").addClass("disabled").removeClass("btn-outline");

            if ($(this).hasClass('active')) {
                $(this).removeClass('active');
                $(this).removeClass('group');
            } else {
                $('tr.active').removeClass('group');
                $('tr.active').removeClass('active');
                $(this).addClass('group');
                $(this).addClass('active');
                $(this).removeClass($(this).data("data-item-status-class"));
                if ($(this).data("item-status").trim() != 'DELIVERED' && $(this).data("item-status").trim() != 'PARTIALLY_DELIVERED') {
                    $("#btn-edit").removeClass("disabled").addClass("btn-outline");
                    $("#btn-delete").removeClass("disabled").addClass("btn-outline");
                }

                if ($(this).data("item-status") == 'WORKING' || $(this).data("item-status") == 'DONE') {
                    $("#btn-deliver").removeClass("disabled").addClass("btn-outline");
                }
                if ($(this).data("item-status") == 'DONE') {
                    $("#btn-edit").addClass("disabled").removeClass("btn-outline");
                }
                $("#btn-show").removeClass("disabled").addClass("btn-outline");

            }

        });

        $("#list_orders_filter").parent().prev().html('<button type="button" class="btn btn-outline btn-primary" id="btn-print"><i class="fa fa-print"></i></button>' +
            '<button type="button" class="btn btn-outline btn-success" id="btn-add">Nuevo</button>' +
            '<button type="button" class="btn  btn-info disabled" id="btn-show"><i class="fa fa-search"></i></button>' +
            '<button type="button" class="btn  btn-warning disabled" id="btn-edit">Editar</button>' +
            '<button type="button" class="btn  btn-danger disabled" id="btn-delete">Eliminar</button>' +
            '<button type="button" class="btn  btn-primary disabled" id="btn-deliver">Entregar</button>');
        $("#list_orders_filter").parent().prev().addClass("btn-toolbar");
        $("#list_orders_filter").css("text-align", "right");

        $("#btn-edit").click(function () {
            var idSelected = $('tr.active').attr('id').replace("row_", "");
            window.location.href = 'edit-order.html?id=' + idSelected;
        });
        $("#btn-show").click(function () {
            var idSelected = $('tr.active').attr('id').replace("row_", "");
            window.location.href = 'show-order.html?id=' + idSelected;
        });

        $("#btn-add").click(function () {
            window.location.href = 'new-order.html';
        });

        $("#btn-delete").click(function () {
            var question = "Está seguro que desea eliminar el pedido seleccionado.";
            pageController.confirmMessage(question, pageController.deleteOrder, pageController.doNothing);
        });

        $("#btn-deliver").click(function () {
            var idSelected = $('tr.active').attr('id').replace("row_", "");
            var value = $('tr.active td label.currency').text();
            value = value.replace("$", "").trim();
            value = parseFloat(value);
            if (value == 0) {
                alert("Este pedido no tiene bultos asignados.");
            } else {
                window.location.href = 'new-delivery-order.html?orderId=' + idSelected;
            }

        });

    }
}
ListOrderPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
ListOrderPageController.prototype.constructor = ListOrderPageController;

ListOrderPageController.prototype.initialize = function () {
    this.initializePage();
    this._initializeListing();
    this._listingAdjustments();
};

ListOrderPageController.prototype.deleteOrder = function () {
    var url = "delete/order";
    var idSelected = $('tr.active').attr('id').replace("row_", "");
    pageController.doPost(url, {"id-order": idSelected}, function () {
        pageController.DataTable.row('.active').remove().draw();
    });
}

var pageController = new ListOrderPageController();