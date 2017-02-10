function ListUserPageController() {
    PageController.call(this);

    this.DataTable = '';
    this._initializeListing = function () {
        this.DataTable = $('#list_users').DataTable({
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
            "scrollCollapse": true,
            "paging": false,
            "ordering": false,
            "filter": true,
            "info": false,
            "stateSave": true,
            "order": [
                [ 0, "desc" ]
            ],
            "drawCallback": function (settings) {
                var heightHeader = $(".dataTables_scrollBody thead th").height();
                var height = $(".dataTables_scrollBody").height();
                $(".dataTables_scrollHead .table").css("margin-bottom", "0px");
                $(".dataTables_scrollBody .table").css("margin-bottom", "0px");
                $(".dataTables_scrollBody thead th").removeClass("sorting");
                $(".dataTables_scrollBody thead th").removeClass("sorting_asc");
                $(".dataTables_scrollBody thead th").removeClass("sorting_desc");
                $(".dataTables_scrollBody").height(height - heightHeader);
            }
        });

    }

    this._listingAdjustments = function () {

        $('#list_users tbody').on('click', 'tr', function () {

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

        $("#list_users_filter").parent().prev().html('<button type="button" class="btn btn-outline btn-primary" id="btn-print"><i class="fa fa-print"></i></button>' +
            '<button type="button" class="btn btn-outline btn-success" id="btn-add">Agregar</button>' +
            '<button type="button" class="btn btn-warning btn-outline" id="btn-edit">Editar</button>' +
            '<button type="button" class="btn btn-danger btn-outline" id="btn-delete">Eliminar</button>');
        $("#list_users_filter").parent().prev().addClass("btn-toolbar");
        $("#list_users_filter").css("text-align", "right");


        $("#btn-edit").addClass("disabled").removeClass("btn-outline");
        $("#btn-delete").addClass("disabled").removeClass("btn-outline");


        $("#btn-edit").click(function () {
            if ($('tr.active').length == 0) {
                pageController.showErrorMessage("Debe seleccionar un elemento de la lista.");
                return;
            }
            var idSelected = $('tr.active').attr('id').replace("row_", "");
            window.location.href = 'edit-user.html?id=' + idSelected;
        });

        $("#btn-add").click(function () {
            window.location.href = 'new-user.html';
        });

        $("#btn-delete").click(function () {
            if ($('tr.active').length == 0) {
                pageController.showErrorMessage("Debe seleccionar un elemento de la lista.");
                return;
            }

            var question = "Está seguro que desea eliminar al usuario seleccionado.";
            pageController.confirmMessage(question, pageController.deleteUser, pageController.doNothing);
        });

    }

}
ListUserPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
ListUserPageController.prototype.constructor = ListUserPageController;

ListUserPageController.prototype.initialize = function () {
    this.initializePage();
    this._initializeListing();
    this._listingAdjustments();

};

ListUserPageController.prototype.deleteUser = function () {
    var url = "user/delete";

    var idSelected = $('tr.active').attr('id').replace("row_", "");
    pageController.doPost(url, {"id-user": idSelected}, function () {
        pageController.DataTable.row('.active').remove().draw();
    });
}


var pageController = new ListUserPageController();




