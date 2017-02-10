function ListBuyTaxationPageController() {
    PageController.call(this);

    this.dataTable = '';
    this._initializeListing = function () {
        this.dataTable = $('#list-buy-tax').dataTable({
            "language": {
                "lengthMenu": "Mostrando _MENU_ registros por página",
                "zeroRecords": "Que verguenza no encontre nada :(",
                "info": "Mostrando página _PAGE_ de _PAGES_",
                "infoEmpty": "Que verguenza no encontre nada :(",
                "infoFiltered": "(Filtrado sobre _MAX_ registros)",
                "search": "Buscar: ",
                "decimal": ",",
                "thousands": "."
            },
            "scrollY": "600px",
            "scrollX": 1400,
            "scrollCollapse": true,
            "paging": false,
            "info": false,
            "filter": false

        });
        $(".dataTables_scrollHead .table").css("margin-bottom", "0px");
        $(".dataTables_scrollBody .table").css("margin-bottom", "0px");
        /*Elimina el margen que queda por el scroll*/
        var heightHeader = $(".dataTables_scrollBody thead th").height();
        $(".dataTables_scrollBody thead th").removeClass("sorting");
        var height = $(".dataTables_scrollBody").height();
        $(".dataTables_scrollBody").height(height - heightHeader);
    }

}
ListBuyTaxationPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
ListBuyTaxationPageController.prototype.constructor = ListBuyTaxationPageController;

ListBuyTaxationPageController.prototype.initialize = function () {
    this.initializePage();
    this._initializeListing();
};

var pageController = new ListBuyTaxationPageController();