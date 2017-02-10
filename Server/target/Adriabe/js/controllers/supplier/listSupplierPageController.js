function ListSupplierPageController() {
    PageController.call(this);

    this.dataTable = '';
    this._initializeListing = function () {
        this.dataTable = $('#list_supplier').dataTable({
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
                    "class": "currency"
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
            ]
        });
        $(".dataTables_scrollHead .table").css("margin-bottom", "0px");
        $(".dataTables_scrollBody .table").css("margin-bottom", "0px");
    }

    this._listingAdjustments = function () {
        /*Elimina el margen que queda por el scroll*/
        var heightHeader = $(".dataTables_scrollBody thead th").height();
        $(".dataTables_scrollBody thead th").removeClass("sorting");
        var height = $(".dataTables_scrollBody").height();
        $(".dataTables_scrollBody").height(height - heightHeader);

        $('#list_supplier tbody').on('click', 'tr', function () {

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

        $("#list_supplier_filter").parent().prev().html('<button type="button" class="btn btn-outline btn-primary" id="btn-print"><i class="fa fa-print"></i></button>' +
            '<button type="button" class="btn btn-outline btn-primary" id="btn-print-libretita"><i class="fa fa-list"></i></button>' +
            '<button type="button" class="btn btn-outline btn-success" id="btn-add">Agregar</button>' +
            '<button type="button" class="btn btn-warning btn-outline" id="btn-edit">Editar</button>' +
            '<button type="button" class="btn btn-danger btn-outline" id="btn-delete">Eliminar</button>');
        $("#list_supplier_filter").parent().prev().addClass("btn-toolbar");
        $("#list_supplier_filter").css("text-align", "right");


        $("#btn-edit").addClass("disabled").removeClass("btn-outline");
        $("#btn-delete").addClass("disabled").removeClass("btn-outline");

        $("#btn-edit").click(function () {
            if ($('tr.active').length == 0) {
                pageController.showErrorMessage("Debe seleccionar un elemento de la lista.");
                return;
            }
            var oRow = pageController.dataTable.fnGetData($('tr.active'));
            var idSelected = $(oRow)[0];
            window.location.href = 'edit-supplier.html?id=' + idSelected;
        });

        $("#btn-add").click(function () {
            window.location.href = 'new-supplier.html';
        });

        $("#btn-delete").click(function () {
            if ($('tr.active').length == 0) {
                pageController.showErrorMessage("Debe seleccionar un elemento de la lista.");
                return;
            }

            var question = "Est� seguro que desea eliminar al proveedor seleccionado.\n Se realizar� un borrado log�co de la entidad.";
            pageController.confirmMessage(question, pageController.deleteSupplier, pageController.doNothing);
        });

    }

}
ListSupplierPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
ListSupplierPageController.prototype.constructor = ListSupplierPageController;

ListSupplierPageController.prototype.initialize = function () {
    this.initializePage();
    this._initializeListing();
    this._listingAdjustments();

};

ListSupplierPageController.prototype.deleteSupplier = function (idSupplier) {
    var url = "supplier/delete";

    var idSelected = $('tr.active').attr('id').replace("row_", "");
    pageController.doPost(url, {"id-supplier": idSelected}, function () {
        pageController.DataTable.row('.active').remove().draw();
    });

}


var pageController = new ListSupplierPageController();




