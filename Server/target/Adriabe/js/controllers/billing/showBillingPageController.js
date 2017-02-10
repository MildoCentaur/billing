/**
 * Created by Mildo on 5/24/15.
 */
function ShowBillingPageController() {
    PageController.call(this);
    this._prices = [];
    this.DataTable = {};
    this._initializeWebElements = function () {

    };
    this._initializeListing = function () {
        this.DataTable = $('#list-bill-items').DataTable({
            "language": {
                "lengthMenu": "Mostrando _MENU_ registros por página",
                "zeroRecords": "No hay items para facturar.",
                "info": "Mostrando p�gina _PAGE_ de _PAGES_",
                "infoEmpty": "No hay items para facturar.",
                "infoFiltered": "(Filtrado sobre _MAX_ registros)",
                "search": "Buscar: ",
                "decimal": ",",
                "thousands": "."
            },
            "scrollY": "600px",
            "scrollCollapse": true,
            "paging": false,
            "ordering": false,
            "info": false,
            "filter": false,
            "columnDefs": [
                { "width": "6%", "targets": 0 },
                { "width": "10%", "targets": 1 },
                { "width": "30%", "targets": 2 },
                { "width": "12%", "targets": 3 },
                { "width": "12%", "targets": 4 },
                { "width": "12%", "targets": 5 },
                { "width": "12%", "targets": 6 },
                { "width": "6%", "targets": 7 }
            ],
            footerCallback: function (row, data, start, end, display) {
                var api = this.api(), data;

                // Remove the formatting to get integer data for summation
                var doubleVal = function (i) {
                    if (typeof i === 'string') {
                        i = formatDouble(i);
                    } else {
                        i = (typeof i === 'number') ? i : 0;
                    }

                    return i;
                };
                // Total over all pages
                data = api.column(4).data();
                total = data.length ?
                    data.reduce(function (a, b) {
                        return doubleVal(a) + doubleVal(b);
                    }) : 0;

                // Update footer
                $(".bill-subtotal").val(total);
                $(api.column(4).footer()).html(formatCurrency(doubleVal(total)));

                // Total over all pages
                data = api.column(5).data();
                total = data.length ?
                    data.reduce(function (a, b) {
                        return doubleVal(a) + doubleVal(b);
                    }) :
                    0;

                // Update footer
                $(".bill-tax").val(total);
                $(api.column(5).footer()).html(formatCurrency(doubleVal(total)));

                // Total over all pages
                data = api.column(6).data();
                total = data.length ?
                    data.reduce(function (a, b) {
                        return doubleVal(a) + doubleVal(b);
                    }) :
                    0;

                // Update footer
                $(".bill-total").val(total);
                $(api.column(6).footer()).html(formatCurrency(doubleVal(total)));
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

    };
    this._initializeWebElements = function () {

        $(".autocomplete-client").prop("disabled", true);
        $("#btn-print").css("display", "block");
        $("#btn-print").removeClass("disabled");
        $(":text").prop("disabled", true);
        $("#btn-print").click(function () {
            pageController.doPost("print-bill.json",
                {billid: $("#id").val()}, function (data) {
                    window.location.href = 'list-bills.html'
                });
        });

        $("#btn-add-item-table").addClass("disabled");
        $("#add-bill-item").addClass("disabled");
        $("#btn-accept").addClass("disabled");

    };

}

ShowBillingPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
ShowBillingPageController.prototype.constructor = ShowBillingPageController;

ShowBillingPageController.prototype.initialize = function () {
    $(".input-group-addon").each(function (i, e) {
        $(e).removeClass("admin-lock");
    });
    this.initializePage();
    this._initializeListing();
    this._initializeWebElements();
};

ShowBillingPageController.prototype.acceptBill = function () {
    var url = $("form").attr("action");
    $(".bill-number").val(removeStartingZeros($(".bill-number").val()));
    $(".order-number").val(removeStartingZeros($(".order-number").val()));
    $(".order-number").prop("disabled", false);
    $(".bill-number").prop("disabled", false);
    pageController.doPost(url, $("form").serialize(), function (data) {
        window.location.href = 'list-bills.html'
    });
    $(".order-number").prop("disabled", true);
    $(".bill-number").prop("disabled", true);
};

var pageController = new ShowBillingPageController();