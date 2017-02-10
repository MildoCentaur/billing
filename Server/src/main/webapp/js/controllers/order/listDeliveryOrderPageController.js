/**
 * Created by Mildo on 9/25/14.
 */

function ListOrderPageController() {
    PageController.call(this);
    this.DataTable = [];
    this._initializeListing = function () {
        this.DataTable = $('#list-delivery-orders').DataTable({
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

        $('#list-delivery-orders tbody').on('click', 'tr', function () {

            if ($(this).hasClass('active')) {
                $(this).removeClass('active');
                $(this).removeClass('group');

                $("#btn-delete").addClass("disabled").removeClass("btn-outline");
                $("#btn-show").addClass("disabled").removeClass("btn-outline");

            } else {
                $('tr.active').removeClass('group');
                $('tr.active').removeClass('active');
                $(this).addClass('group');
                $(this).addClass('active');
                $(this).removeClass($(this).data("data-item-status-class"));
                if ($(this).data("item-status").trim() != 'DELIVERED') {
                    $("#btn-delete").removeClass("disabled").addClass("btn-outline");
                }
                $("#btn-show").removeClass("disabled").addClass("btn-outline");
            }

        });

        $("#list-delivery-orders_filter").parent().prev().html('<button type="button" class="btn btn-outline btn-primary" id="btn-print"><i class="fa fa-print"></i></button>' +
            '<button type="button" class="btn  btn-info disabled" id="btn-show"><i class="fa fa-search"></i></button>' +
            '<button type="button" class="btn  btn-danger disabled" id="btn-delete">Eliminar</button>');
        $("#list-delivery-orders_filter").parent().prev().addClass("btn-toolbar");
        $("#list-delivery-orders_filter").css("text-align", "right");


        $("#btn-show").click(function () {
            var idSelected = $('tr.active').attr('id').replace("row_", "");
            window.location.href = 'show-delivery-order.html?id=' + idSelected;
        });

        $("#btn-delete").click(function () {
            var question = "Está seguro que desea eliminar la orden de entrega seleccionada.";
            pageController.confirmMessage(question, pageController.deleteOrder, pageController.doNothing);
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
    var url = "delete/delivery-order";
    var idSelected = $('tr.active').attr('id').replace("row_", "");
    pageController.doPost(url, {"id-order": idSelected}, function () {
        pageController.DataTable.row('.active').remove().draw();
    });
}

var pageController = new ListOrderPageController();