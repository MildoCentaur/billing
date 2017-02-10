function ListFabricPageController(){
    PageController.call(this);

    this.selected = '';
    this.DataTable = '';
    this._initializeListing= function (){
        this.DataTable=$('#list_fabric').DataTable({
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
                { "width": "4%", "targets": 0 },
                { "width": "10%", "targets": 1 },
                { "width": "20%", "targets": 2 },
                { "width": "10%", "targets": 3 },
                { "width": "8%", "targets": 4 },
                { "width": "8%", "targets": 5 },
                { "width": "8%", "targets": 6 },
                { "width": "8%", "targets": 7 },
                { "width": "8%", "targets": 8 },
                { "width": "8%", "targets": 9 },
                { "width": "8%", "targets": 10 },
            ],
            "scrollY": "600px",
            "scrollX": "150%",
            "autoWidth": false,
            "scrollCollapse": true,
            "ordering": true,
            "info":false,
            "paging": false,
            "order": [[ 0, "desc" ]],
            "drawCallback" : function(settings){
                var heightHeader = $(".dataTables_scrollBody thead th").height();
                var height =  $(".dataTables_scrollBody").height();
                $(".dataTables_scrollHead .table").css("margin-bottom","0px");
                $(".dataTables_scrollBody .table").css("margin-bottom","0px");
                $(".dataTables_scrollBody thead th").removeClass("sorting");
                $(".dataTables_scrollBody thead th").removeClass("sorting_asc");
                $(".dataTables_scrollBody thead th").removeClass("sorting_desc");
                $(".dataTables_scrollBody").height(height - heightHeader)
            }
        });

    }

    this._listingAdjustments=function(){

        $('#list_fabric tbody').on('click', 'tr', function () {

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

        $("#list_fabric_filter").parent().prev().html('<button type="button" class="btn btn-outline btn-primary" id="btn-print"><i class="fa fa-print"></i></button>'+
            '<button type="button" class="btn btn-outline btn-success" id="btn-add">Agregar</button>'+
            '<button type="button" class="btn btn-warning btn-outline" id="btn-edit">Editar</button>'+
            '<button type="button" class="btn btn-danger btn-outline" id="btn-delete">Eliminar</button>');
        $("#list_fabric_filter").parent().prev().addClass("btn-toolbar");
        $("#list_fabric_filter").css("text-align","right");



        $("#btn-edit").addClass("disabled").removeClass("btn-outline");
        $("#btn-delete").addClass("disabled").removeClass("btn-outline");

        $("#btn-edit").click(function(){
            if($('tr.active').length==0){
                pageController.showErrorMessage("Debe seleccionar un elemento de la lista.");
                return;
            }
            var idSelected =  $('tr.active').attr('id').replace("row_","");
            window.location.href='edit-fabric.html?id='+idSelected;
        });

        $("#btn-add").click(function(){
            window.location.href='new-fabric.html';
        });

        $("#btn-delete").click(function(){
            if($('tr.active').length==0){
                pageController.showErrorMessage("Debe seleccionar un elemento de la lista.");
                return;
            }

            var question = "Está seguro que desea eliminar el tejido seleccionado.\n Se realizará un borrado logíco de la entidad.";
            pageController.confirmMessage(question,pageController.deleteFabric,pageController.doNothing);
        });

    }

}
ListFabricPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
ListFabricPageController.prototype.constructor = ListFabricPageController;

ListFabricPageController.prototype.initialize = function(){
    this.initializePage();
    this._initializeListing();
    this._listingAdjustments();

};

ListFabricPageController.prototype.deleteFabric= function(){
    var url="fabric/delete";
    var idSelected =  $('tr.active').attr('id').replace("row_","");
    pageController.doPost(url,{"id-fabric":idSelected},function(){
        pageController.DataTable.row('.active').remove().draw();
    });
}


var pageController = new ListFabricPageController();




