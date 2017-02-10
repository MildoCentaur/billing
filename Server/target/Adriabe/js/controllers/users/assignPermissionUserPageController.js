function AssignPermissionUserPageController() {
    PageController.call(this);
    this._initializeAddUserFormPage = function () {
        $("#btn-assign-permission").click(function () {
            pageController.assignPermitionButtonAction();
        });

        this.DataTable = $('#list-user-permission').DataTable({
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
            "scrollX": "160%",
            "scrollCollapse": true,
            "paging": false,
            "ordering": false,
            "filter": false,
            "info": false,
            "stateSave": true,
            "columnDefs": [

            ],
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
    };

}
AssignPermissionUserPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
AssignPermissionUserPageController.prototype.constructor = AssignPermissionUserPageController;

AssignPermissionUserPageController.prototype.initialize = function () {
    this.initializePage();
    this._initializeAddUserFormPage()
};

AssignPermissionUserPageController.prototype.assignPermitionButtonAction = function () {
    var permissions = []; //map userid

    $("#list-user-permission tbody tr").each(function (index, element) {
        var user = $(element).find(".user-permission");
        var userPermissions = $(element).find(":checked");

        $(userPermissions).each(function (i, e) {
            permissions.push({idUser: $(user).data("user-id"),
                username: $(user).text(),
                idPermission: $(e).data("permission-id"),
                permission: $(e).data("permission-name")});
        });

    });


    permissions = JSON.stringify(permissions);

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: "assign-permission-process.json",
        dataType: "json",
        data: permissions,
        contentType: 'text/html',
        success: function (html) {
            location.href = "list-users.html";
        }
    });
};

var pageController = new AssignPermissionUserPageController();