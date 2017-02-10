/**
 * Created by Mildo on 9/25/14.
 */

function ListBillingPageController(){
    PageController.call(this);
    this.DataTable={};
    this._deactivateRow= function (row){
        $(row).removeClass('active');
        $("#btn-cancel-bill").addClass("disabled");
        $("#btn-cancel-bill").removeClass("btn-outline");
        $("#btn-cancel-bill").text("Anular");
    };
    this._activateRow= function (row){
        $("#list_bills tbody tr.active").removeClass('active');
        $(row).addClass('active');
        $("#btn-cancel-bill").removeClass("disabled");
        $("#btn-cancel-bill").addClass("btn-outline");

        if($("tr.active td.cancelled").data("cancelled")){
            $("#btn-cancel-bill").text("Restablecer");
        }else{
            $("#btn-cancel-bill").text("Anular");
        }
    };
    this._selectRowBehavior= function (row){
        if( $(row).is(".active") ){
            pageController._deactivateRow(row);
        }else{
            pageController._activateRow(row);
        }
    };

    this._initializeListing= function (){
        this.DataTable=$('#list_bills').DataTable({
            "language": {
                "lengthMenu": "Mostrando _MENU_ registros por p�gina",
                "zeroRecords": "Que verguenza no encontre nada :(",
                "info": "Mostrando p�gina _PAGE_ de _PAGES_",
                "infoEmpty": "No hay informaci�n para mostrar",
                "infoFiltered": "(Filtrado sobre _MAX_ registros)",
                "search":"Buscar: ",
                "decimal": ",",
                "thousands": "."
            },
            "scrollY": "600px",
            "scrollCollapse": true,
            "paging": false,
            "info":false,

            "footerCallback": function ( row, data, start, end, display ) {
                var api = this.api(), data;

                // Remove the formatting to get integer data for summation
                var intVal = function ( i ) {
                    if ( typeof i === 'string'){
                        i =  i.replace(/[\$\.]/g,'');
                        i =  i.replace(',', '.') *1;
                    }
                    else{
                        i = (typeof i === 'number') ? i : 0;
                    }

                    return i;
                };
                // Total over all pages
                data = api.column( 6 ).data();
                var total = data.length ?
                    data.reduce( function (a, b) {
                        return intVal(a) + intVal(b);
                    } ) : 0;

                // Update footer
                $( api.column( 6 ).footer() ).html( formatCurrency(total) );

                // Total over all pages
                data = api.column( 7 ).data();
                total = data.length ?
                    data.reduce( function (a, b) {
                        return intVal(a) + intVal(b);
                    } ) :
                    0;

                // Update footer
                $( api.column( 7 ).footer() ).html( formatCurrency(total) );

                // Total over all pages
                data = api.column( 5 ).data();
                total = data.length ?
                    data.reduce( function (a, b) {
                        return intVal(a) + intVal(b);
                    } ) :
                    0;

                // Update footer
                $( api.column( 5 ).footer() ).html( formatCurrency(total) );
            },
            "order": [[ 1, "desc" ]],
            "drawCallback" : function(settings){
                $(".dataTables_scrollHead .table").css("margin-bottom","0px");
                $(".dataTables_scrollBody .table").css("margin-bottom","0px");
                $(".dataTables_scrollBody thead th").removeClass("sorting");
                $(".dataTables_scrollBody thead th").removeClass("sorting_asc");
                $(".dataTables_scrollBody thead th").removeClass("sorting_desc");
                var heightHeader = $(".dataTables_scrollBody thead th").height();
                var height =  $(".dataTables_scrollBody").height();
                $(".dataTables_scrollBody").height(height - heightHeader - 20);
            }
        });

        $("#list_bills_filter").css("float","right");

        $("#list_bills_filter").parent().prev().html('<button type="button" id="btn-cancel-bill">Anular</button>' +
            '<button type="button" class="btn disabled btn-primary" id="btn-show"><i class="fa fa-search"></i></button>' +
            '<button type="button" id="btn-new-bill">Nueva</button>');

        $("#btn-cancel-bill").addClass("btn btn-danger disabled");
        $("#btn-cancel-bill").css("margin-right","5px");
        $("#btn-cancel-bill").css("float","right");
        $("#btn-show").css("float", "right");

        $("#btn-new-bill").addClass("btn btn-outline btn-primary");
        $("#btn-new-bill").css("margin-right","5px");
        $("#btn-new-bill").css("float","right");
        $("#btn-new-bill").css("margin-left","20px");

        $('#list_bills tbody').on('click', 'tr', function () {
            pageController._selectRowBehavior(this);
        });

        $("#btn-new-bill").click(function(){
            location.href='billing.html';
        });

        $("#btn-cancel-bill").click(function(){
            var url='';
            if($("tr.active td.cancelled").data("cancelled")){
                url='reestablish/bill.json';
            }else{
                url='cancel/bill.json';
            }
            var id = $("tr.active").data("bill-id");
            pageController.doPost(url,{"id":id},function(){
                location.reload();
            });

        });

        $("#btn-show").click(function () {
            var id = $("tr.active").data("bill-id");
            location.href = "show-bill.html?id=" + id;
        });

    }
}
ListBillingPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
ListBillingPageController.prototype.constructor = ListBillingPageController;

ListBillingPageController.prototype.initialize = function(){
    this.initializePage();
    this._initializeListing();

};


var pageController = new ListBillingPageController();
