function ListClientPageController() {
    PageController.call(this);

    this.DataTable = '';
    this._initializeListing = function () {
        this.DataTable = $('#list_client').DataTable({
            "language": {
                "lengthMenu": "Mostrando _MENU_ registros por p�gina",
                "zeroRecords": "Que verguenza no encontre nada :(",
                "info": "Mostrando p�gina _PAGE_ de _PAGES_",
                "infoEmpty": "Que verguenza no encontre nada :(",
                "infoFiltered": "(Filtrado sobre _MAX_ registros)",
                "search": "Buscar: ",
                "decimal": ",",
                "thousands": "."
            },
            "scrollY": "600px",
            "scrollX": 1100,
            "scrollCollapse": true,
            "paging": false,
            "info": false,
            "stateSave": true,
            "columnDefs": [
                {
                    "targets": [ 0 ],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [ 3, 4, 5 ],
                    "class": "formatCurrencyFromDouble"
                }
            ],

            footerCallback: function (row, data, start, end, display) {
                var api = this.api(), data;

                // Remove the formatting to get integer data for summation
                var intVal = function (i) {
                    if (typeof i === 'string') {
                        i = i.replace(/[\$]/g, '') * 1;
                    }
                    else {
                        i = (typeof i === 'number') ? i : 0;
                    }

                    return i;
                };

                // Total over all pages
                data = api.column(7).data();
                total = data.length ?
                    data.reduce(function (a, b) {
                        return intVal(a) + intVal(b);
                    }) : 0;


                // Update footer
                $(api.column(7).footer()).html(
                    formatCurrency(total)
                );
                // Total over all pages
                data = api.column(8).data();
                total = data.length ?
                    data.reduce(function (a, b) {
                        return intVal(a) + intVal(b);
                    }) : 0;

                // Update footer
                $(api.column(8).footer()).html(
                    formatCurrency(total)
                );
            },
            "order": [
                [ 0, "desc" ]
            ],
            "drawCallback": function (settings) {
                var heightHeader = $(".dataTables_scrollBody thead th").height();
                var height = $(".dataTables_scrollBody").height();
                $(".dataTables_scrollHead .table").css("margin-bottom", "0px");
                $(".dataTables_scrollBody .table").css("margin-bottom", "0px");
                $(".dataTables_scrollBody thead th").removeClass("sorting");
                $(".dataTables_scrollBody thead th").removeClass("sorting_asc");
                $(".dataTables_scrollBody thead th").removeClass("sorting_desc");
                $(".dataTables_scrollBody").height(height - heightHeader);
            }
        });

    }

    this._listingAdjustments = function () {

        $('#list_client tbody').on('click', 'tr', function () {

            if ($(this).hasClass('active')) {
                $(this).removeClass('active');
                $("#btn-delete").addClass("disabled").removeClass("btn-outline");
                $("#btn-edit").addClass("disabled").removeClass("btn-outline");
            } else {
                $('tr.active').removeClass('active');
                $(this).addClass('active');
                $("#btn-edit").removeClass("disabled").addClass("btn-outline");
                $("#btn-delete").removeClass("disabled").addClass("btn-outline");
            }

        });

        $("#list_client_filter").parent().prev().html('<button type="button" class="btn btn-outline btn-primary" id="btn-print"><i class="fa fa-print"></i></button>' +
            '<button type="button" class="btn btn-outline btn-primary" id="btn-print-libretita"><i class="fa fa-list"></i></button>' +
            '<button type="button" class="btn btn-outline btn-success" id="btn-add">Agregar</button>' +
            '<button type="button" class="btn btn-warning btn-outline" id="btn-edit">Editar</button>' +
            '<button type="button" class="btn btn-danger btn-outline" id="btn-delete">Eliminar</button>');
        $("#list_client_filter").parent().prev().addClass("btn-toolbar");
        $("#list_client_filter").css("text-align", "right");


        $("#btn-edit").addClass("disabled").removeClass("btn-outline");
        $("#btn-delete").addClass("disabled").removeClass("btn-outline");

        $("#btn-edit").click(function () {
            if ($('tr.active').length == 0) {
                pageController.showErrorMessage("Debe seleccionar un elemento de la lista.");
                return;
            }
            var oRow = pageController.DataTable.row('.active').node();
            var idSelected = $(oRow).data("row-id");
            window.location.href = 'edit-client.html?id=' + idSelected;
        });

        $("#btn-add").click(function () {
            window.location.href = 'new-client.html';
        });

        $("#btn-delete").click(function () {
            if ($('tr.active').length == 0) {
                pageController.showErrorMessage("Debe seleccionar un elemento de la lista.");
                return;
            }

            var question = "Está seguro que desea eliminar al cliente seleccionado.\n Se realizará un borrado logíco de la entidad.";
            pageController.confirmMessage(question, pageController.deleteClient, pageController.doNothing);
        });

    }

}
ListClientPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
ListClientPageController.prototype.constructor = ListClientPageController;

ListClientPageController.prototype.initialize = function () {
    this.initializePage();
    this._initializeListing();
    this._listingAdjustments();

};

ListClientPageController.prototype.deleteClient = function () {
    var url = "client/delete";
    var oRow = pageController.DataTable.row('.active').node();
    var idSelected = $(oRow).data("row-id");
    pageController.doPost(url, {"id-client": idSelected}, function () {
        pageController.DataTable.row('.active').remove().draw();
    });
}


var pageController = new ListClientPageController();




