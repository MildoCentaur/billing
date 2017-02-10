function ListProductPageController() {
    PageController.call(this);

    this.selected = '';
    this.DataTable = '';
    this._initializeListing = function () {
        this.dataTable = $('#list_product').DataTable({
            "language": {
                "lengthMenu": "Mostrando _MENU_ registros por página",
                "zeroRecords": "Que verguenza no encontre nada :(",
                "info": "Mostrando página _PAGE_ de _PAGES_",
                "infoEmpty": "No hay información para mostrar",
                "infoFiltered": "(Filtrado sobre _MAX_ registros)",
                "search": "Buscar: ",
                "decimal": ",",
                "thousands": "."
            },
            "columnDefs": [
                {   "type": "num",
                    "render": function (data, type, full, meta) {
                        return parseInt(data);
                    },
                    "targets": 0},
                { "width": "7%", "targets": 0 },
                { "width": "54%", "targets": 1 },
                { "width": "12%", "targets": 2 },
                { "width": "12%", "targets": 3 },
                { "width": "15%", "targets": 4 },
                { "visible": false, "targets": 5 },
                { "class": "deleteItem", "targets": 5 },
            ],
            "scrollY": "550px",
            "scrollCollapse": false,
            "paging": false,
            "info": false,
            "order": [
                [ 5, "desc" ]
            ],
            "drawCallback": function (settings) {
                $(".dataTables_scrollHead .table").css("margin-bottom", "0px");
                $(".dataTables_scrollBody .table").css("margin-bottom", "0px");
                $(".dataTables_scrollBody thead th").removeClass("sorting");
                $(".dataTables_scrollBody thead th").removeClass("sorting_asc");
                $(".dataTables_scrollBody thead th").removeClass("sorting_desc");
                var heightHeader = $(".dataTables_scrollBody thead th").height();
                var height = $(".dataTables_scrollBody").height();
                $(".dataTables_scrollBody").height(height - heightHeader);


                var api = this.api();
                var rows = api.rows().nodes();
                var last = null;
                var rowAddedIndex = 0;

                api.column(5).data().each(function (group, i) {
                    if (last !== group) {
                        $(rows).eq(i).before(
                                '<tr class="group">'
                                + '<td colspan="2">' + group + '</td>'
                                + '<td id="row_amount_' + rowAddedIndex + '">0</td>'
                                + '<td colspan="2"></td>'
                                + '</tr>'
                        );
                        last = group;
                        rowAddedIndex++;
                    }
                    if (last === group) {
                        var amount = parseInt($("#row_amount_" + (rowAddedIndex - 1)).text()) + parseInt(this.column(2).data()[i]);
                        $("#row_amount_" + (rowAddedIndex - 1)).text(amount);
                    }
                });

            }
        });
        // Order by the grouping
        $('#list_product tbody').on('click', 'tr.group', function () {
            var currentOrder = $('#list_product').order()[0];
            if (currentOrder[0] === 2 && currentOrder[1] === 'asc') {
                $('#list_product').order([ 2, 'desc' ]).draw();
            }
            else {
                $('#list_product').order([ 2, 'asc' ]).draw();
            }
        });

        $("#list_product_filter").css("text-align", "right");
    }

    this._listingAdjustments = function () {

        $('#list_product tbody').on('click', 'tr', function () {

            if ($(this).hasClass('active')) {
                $(this).removeClass('active');
            } else {
                $('tr.active').removeClass('active');
                $(this).addClass('active');
            }

        });

        $("#list_product_filter").parent().prev().html('<button type="button" class="btn btn-outline btn-primary" id="btn-print"><i class="fa fa-print"></i></button>');
        $("#list_product_filter").parent().prev().addClass("btn-toolbar");
        $("#list_product_filter").css("text-align", "right");

        $("#btn-edit-stock").click(function(){
            $("#barcode").focus();
        });

        $("#btn-confirm-stock").click(function(){
            var barcode = $("#barcode").val();
            if (barcode == "" || barcode.length != 16) {
                alert("Error de lectura del código de barras.");
                return;
            }
            if (!isValidNumber($('#amount').val()) || $('#amount').val() <= 0) {
                alert("Error la cantidad que desea incrementar es invalida.");
                return;
            }
            pageController.doPost("update-stock.json", {"barcode": barcode, "amount": $('#amount').val()}, function () {
                location.reload();
            });
        });

    }

}
ListProductPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
ListProductPageController.prototype.constructor = ListProductPageController;

ListProductPageController.prototype.initialize = function () {
    this.initializePage();
    this._initializeListing();
    this._listingAdjustments();

};

var pageController = new ListProductPageController();