function ListPricePageController(){
    PageController.call(this);

    this.selected = '';
    this.DataTable = '';
    this._initializeListing= function (){
        this.DataTable=$('#list_product').DataTable({
            "language": {
                "lengthMenu": "Mostrando _MENU_ registros por página",
                "zeroRecords": "Que verguenza no encontre nada :(",
                "info": "Mostrando página _PAGE_ de _PAGES_",
                "infoEmpty": "No hay información para mostrar",
                "infoFiltered": "(Filtrado sobre _MAX_ registros)",
                "search":"Buscar: ",
                "decimal": ",",
                "thousands": "."
            },
            "columnDefs": [
                {   "type":"num",
                    "render":function ( data, type, full, meta ) {
                        return parseInt(data);
                    },
                    "targets":0},
                { "width": "6%", "targets": 0 },
                { "width": "34%", "targets": 1 },
                { "width": "15%", "targets": 2 },
                { "width": "15%", "targets": 3 },
                { "width": "15%", "targets": 4 },
                { "width": "15%", "targets": 5 },
                { "class": "deleteItem", "targets": 5 }
            ],
            "scrollY": "600px",
            "scrollCollapse": true,
            "paging": false,
            "info":false,
            "order": [[ 0, "desc" ]],
            "drawCallback" : function(settings){
                $(".dataTables_scrollHead .table").css("margin-bottom","0px");
                $(".dataTables_scrollBody .table").css("margin-bottom","0px");
                $(".dataTables_scrollBody thead th").removeClass("sorting");
                $(".dataTables_scrollBody thead th").removeClass("sorting_asc");
                $(".dataTables_scrollBody thead th").removeClass("sorting_desc");
                var heightHeader = $(".dataTables_scrollBody thead th").height();
                var height =  $(".dataTables_scrollBody").height();
                $(".dataTables_scrollBody").height(height - heightHeader);
            }
        });
    }

    this._listingAdjustments=function(){

        $('#list_product tbody').on('click', 'tr', function () {

            if ( $(this).hasClass('active') ) {
                $(this).removeClass('active');
                $("#btn-edit").addClass("disabled").removeClass("btn-outline");
            }else {
                $('tr.active').removeClass('active');
                $(this).addClass('active');
                $("#btn-edit").removeClass("disabled").addClass("btn-outline");
            }

        });

        $("#list_product_filter").parent().prev().html('<button type="button" class="btn btn-outline btn-primary" id="btn-print"><i class="fa fa-print"></i></button>'+
            '<button type="button" class="btn btn-outline btn-success" id="btn-add">Agregar</button>'+
            '<button type="button" class="btn btn-warning btn-outline" id="btn-edit">Nueva/Editar</button>');
        $("#list_product_filter").parent().prev().addClass("btn-toolbar");
        $("#list_product_filter").css("text-align","right");


        $("#btn-edit").click(function(){
            window.location.href='new-list-price.html';
        });

        $("#btn-add").click(function(){
            window.location.href='new-product.html';
        });
        //imprimir listados. Requiere que se configure por CSS que se imprime y que no
        $("#btn-print").click(function(){
            pageController.printListing();
            window.print();
            location.reload(true);
        });
    }

}
ListPricePageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
ListPricePageController.prototype.constructor = ListPricePageController;

ListPricePageController.prototype.printListing = function(){
    $(".btn-toolbar").css("display","none");
    $(".dataTables_filter").css("display","none");
};


ListPricePageController.prototype.initialize = function(){
    this.initializePage();
    this._initializeListing();
    this._listingAdjustments();

};

var pageController = new ListPricePageController();