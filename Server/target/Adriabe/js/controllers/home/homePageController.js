function HomePageController() {
    PageController.call(this);
    this.productSummaryDataTable = {};

    this._initializeWebElements = function () {
        $("#orders-table").click(function () {
            $("#orders_chart_div").css("display", "block");
            $("#orders_table_div").css("display", "block");
        });
        $("#orders-graphics").click(function () {
            $("#orders_chart_div").css("display", "block");
            $("#orders_table_div").css("display", "block");
        });

        $("#new-event-button").click(function () {
            pageController.addNotificationButton();
        });
        this._initializeOrderSummaryReport();
        this._initializeProductSummaryReport();
        this._initializeColorSummaryReport();
    };
    this._initializeOrderSummaryReport = function () {

    };
    this._initializeProductSummaryReport = function () {
    };
    this._initializeColorSummaryReport = function () {
    };
    this._initializeDataTables = function () {
        this.productSummaryDataTable = $('#list-product-summary').DataTable({
            "language": {
                "lengthMenu": "Mostrando _MENU_ registros por p�gina",
                "zeroRecords": "Que verguenza no encontre nada :(",
                "info": "Mostrando p�gina _PAGE_ de _PAGES_",
                "infoEmpty": "No hay informaci�n para mostrar",
                "infoFiltered": "(Filtrado sobre _MAX_ registros)",
                "search": "Buscar: ",
                "decimal": ",",
                "thousands": "."
            },
            "columnDefs": [
                { "width": "43%", "targets": 0 },
                { "width": "15%", "targets": 1 },
                { "width": "15%", "targets": 2 },
                { "width": "15%", "targets": 3 },
                { "width": "12%", "targets": 4 },
            ],
            "scrollY": "400px",
            "scrollCollapse": true,
            "paging": false,
            "info": false,
            "filter": false,
            "ordering": false,
            "drawCallback": function (settings) {
                $(".dataTables_scrollHead .table").css("margin-bottom", "0px");
                $(".dataTables_scrollBody .table").css("margin-bottom", "0px");
                $(".dataTables_scrollBody thead th").removeClass("sorting");
                $(".dataTables_scrollBody thead th").removeClass("sorting_asc");
                $(".dataTables_scrollBody thead th").removeClass("sorting_desc");
                var heightHeader = $(".dataTables_scrollBody thead th").height();
                var height = $(".dataTables_scrollBody").height();
                $(".dataTables_scrollBody").height(height - heightHeader - 20);
            }
        });
    };

}
HomePageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
HomePageController.prototype.constructor = HomePageController;

HomePageController.prototype.initialize = function () {
    this.initializePage();
    this._initializeWebElements();
    this._initializeDataTables();
};

HomePageController.prototype.addNotificationButton = function () {
    var form = $("#add-notification-form");

    var result = true;
    var bValid = isValidAlphaNumeric($("#notification-title").val());
    result = bValid && result;
    if (!bValid) {
        $("#notification-title").css("border", "1px solid red");
    }
    bValid = isValidAlphaNumeric($("#notification-place").val());
    result = bValid && result;
    if (!bValid) {
        $("#notification-place").css("border", "1px solid red");
    }
    bValid = $("#notification-date").val() != "";
    result = bValid && result;
    if (!bValid) {
        $("#notification-date").css("border", "1px solid red");
    }
    if (result) {
        pageController.doPost("add/event.json", form.serialize(), function () {
            $("#add-event-modal").modal("hide");
        });
    }

};


var pageController = new HomePageController();

