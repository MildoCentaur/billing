function ShowOrderPageController() {
    PageController.call(this);
}
ShowOrderPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
ShowOrderPageController.prototype.constructor = ShowOrderPageController;

ShowOrderPageController.prototype.initialize = function () {
    this.initializePage();
    var height = "350px";
    if ($("#print").val() == "true") {
        height = "auto";
    }
    this.DataTable = $('#list-item-order').DataTable({
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
        "scrollY": height,
        "filter": true,
        "paging": false,
        "info": false,
        "ordering": false,
        footerCallback: function (row, data, start, end, display) {
            var api = this.api(), data;

            // Remove the formatting to get integer data for summation
            var intVal = function (i) {
                if (typeof i === 'string') {
                    i = i.replace(/[\$\.]/g, '');
                    i = i.replace(',', '.');
                    i = parseFloat(i);
                    i = isNaN(i) ? 0 : i;
                }
                else {
                    i = (typeof i === 'number') ? i : 0;
                }

                return i;
            };


            // Total over all pages
            data = api.column(4).data();
            total = data.length ?
                data.reduce(function (a, b) {
                    return intVal(a) + intVal(b);
                }) :
                0;

            // Update footer
            $(api.column(4).footer()).html('<label style="float:right;" class="currency">' + formatCurrency(total) + '</label>');
        },
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
    $("#list-item-order_filter").css("text-align", "right");
    $(".package-total").each(function (index, element) {
        var price = $(element).data("price");
        var weight = $(element).data("weight");
        $(element).text(formatCurrencyFromDouble(price * weight));
    });

    if ($("#print").val() == "true") {
        pageController.print();
    }
}

ShowOrderPageController.prototype.print = function () {
    $("#list-item-order_filter").css("display", "none");
    window.print();
}
var pageController = new ShowOrderPageController();

