function ListStripePageController(){
    PageController.call(this);

    this.selected = '';
    this.DataTable = '';
    this._initializeListing= function (){
        this.DataTable=$('#list_stripe').DataTable({
            "language": {
                "lengthMenu": "Mostrando _MENU_ registros por página",
                "zeroRecords": "Que verguenza no encontre nada :(",
                "info": "Mostrando página _PAGE_ de _PAGES_",
                "infoEmpty": "Que verguenza no encontre nada :(",
                "infoFiltered": "(Filtrado sobre _MAX_ registros)",
                "search":"Buscar: ",
                "decimal": ",",
                "thousands": "."
            },
            "columnDefs": [
                { "width": "8%", "targets": 0 },
                { "width": "12%", "targets": 1 },
                { "width": "30%", "targets": 2 },
                { "width": "10%", "targets": 3 },
                { "width": "25%", "targets": 4 },
                { "width": "15%", "targets": 5 },
            ],
            "scrollY": "600px",
            "scrollCollapse": true,
            "info":false,
            "paging": false,
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

        $('#list_stripe tbody').on('click', 'tr', function () {

            if ( $(this).hasClass('active') ) {
                $(this).removeClass('active');
                $("#btn-delete").addClass("disabled").removeClass("btn-outline");
                $("#btn-edit").addClass("disabled").removeClass("btn-outline");
            }else {
                $('tr.active').removeClass('active');
                $(this).addClass('active');
                $("#btn-edit").removeClass("disabled").addClass("btn-outline");
                $("#btn-delete").removeClass("disabled").addClass("btn-outline");
            }

        });

        $("#list_stripe_filter").parent().prev().html('<button type="button" class="btn btn-outline btn-primary" id="btn-print"><i class="fa fa-print"></i></button>'+
                                                      '<button type="button" class="btn btn-outline btn-success" id="btn-add">Agregar</button>'+
                                                      '<button type="button" class="btn btn-warning btn-outline" id="btn-edit">Editar</button>'+
                                                      '<button type="button" class="btn btn-danger btn-outline" id="btn-delete">Eliminar</button>');
        $("#list_stripe_filter").parent().prev().addClass("btn-toolbar");
        $("#list_stripe_filter").css("text-align","right");



        $("#btn-edit").addClass("disabled").removeClass("btn-outline");
        $("#btn-delete").addClass("disabled").removeClass("btn-outline");

        $("#btn-edit").click(function(){
            if($('tr.active').length==0){
                pageController.showErrorMessage("Debe seleccionar un elemento de la lista.");
                return;
            }

            var idSelected =  $('tr.active').attr('id').replace("row_","");
            window.location.href='edit-stripe.html?id='+idSelected;
        });

        $("#btn-add").click(function(){
            window.location.href='new-stripe.html';
        });

        $("#btn-delete").click(function(){
            if($('tr.active').length==0){
                pageController.showErrorMessage("Debe seleccionar un elemento de la lista.");
                return;
            }

            var question = "Está seguro que desea eliminar el patrón de rayado seleccionado.\n Se realizará un borrado logíco de la entidad.";
            pageController.confirmMessage(question,pageController.deleteStripe,pageController.doNothing);
        });

    }

}
ListStripePageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
ListStripePageController.prototype.constructor = ListStripePageController;

ListStripePageController.prototype.initialize = function(){
  this.initializePage();
  this._initializeListing();
  this._listingAdjustments();

};

ListStripePageController.prototype.deleteStripe= function(){
    var url="stripe/delete";
    var idSelected =  $('tr.active').attr('id').replace("row_","");
    pageController.doPost(url,{"id-stripe":idSelected},function(){
        pageController.DataTable.row('.active').remove().draw();
    });
}


var pageController = new ListStripePageController();




