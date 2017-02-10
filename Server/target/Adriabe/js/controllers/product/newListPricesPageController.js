function NewListPricePageController(){
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
                { "width": "5%", "targets": 0 },
                { "width": "19%", "targets": 1 },
                { "width": "10%", "targets": 2 },
                { "width": "9%", "targets": 3 },
                { "width": "10%", "targets": 4 },
                { "width": "9%", "targets": 5 },
                { "width": "10%", "targets": 6 },
                { "width": "9%", "targets": 7 },
                { "width": "10%", "targets": 8 },
                { "width": "9%", "targets": 9 },
                { "className": "center", "targets": [3,5,7,9] },
                { "orderable": false, "targets": [2,3,4,5,6,7,8,9] }
            ],
            "scrollY": "600px",
            "scrollX": "120%",
            "scrollCollapse": true,
            "paging": false,
            "info":false,
            "order": [[ 0, "desc" ]],
            "drawCallback" : function(settings){
                var heightHeader = $(".dataTables_scrollBody thead th").height();
                $(".dataTables_scrollHead .table").css("margin-bottom","0px");
                $(".dataTables_scrollBody .table").css("margin-bottom","0px");
                $(".dataTables_scrollBody thead th").removeClass("sorting");
                $(".dataTables_scrollBody thead th").removeClass("sorting_asc");
                $(".dataTables_scrollBody thead th").removeClass("sorting_desc");

                var height =  $(".dataTables_scrollBody").height();
                $(".dataTables_scrollBody").height(height - heightHeader);
                $(".dataTables_filter").css("float","right");
            }
        });

        $("#btn-new-list-price").click(function(){
            pageController.newListPrice();
        });

        $("#btn-formula").click(function(){
            pageController.calculateListPrice();
        });
    }
}
NewListPricePageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
NewListPricePageController.prototype.constructor = NewListPricePageController;

NewListPricePageController.prototype.initialize = function(){
    this.initializePage();
    this._initializeListing();
};

NewListPricePageController.prototype.calculateListPrice = function(){

    var formula = "p" + $("#formula").val().replace(",",".");
    $("input.currency").each(function(index,element){
        var aux = $(element).val();
        if(aux==""){
            aux=$(element).parents("td").next().text();
        }
        var p = formatDouble(aux);
        if(p>0){
            formula = formula.replace("%","/100");
            formula = formula.replace("precio","p");
            p = eval(formula);

        }
        $(element).val(formatCurrencyFromDouble(p));
    });

};

NewListPricePageController.prototype.newListPrice = function(){
    var url="list-price.json";
    $(".currency").each(function(index,element){
        var aux = $(element).val();
        if(aux==""){
            aux=$(element).parents("td").next().text();
        }
        $(element).val(formatDouble(aux));
    });

    var prices = $("form").serialize();

    $(".currency").each(function(index,element){
        var aux = $(element).val();
        if(aux==""){
            aux=$(element).parents("td").next().text();
        }
        $(element).val(formatCurrencyFromDouble(aux));
    });

    pageController.doPost(url,prices,function(){
        location.href="list-prices.html";
    });
};

var pageController = new NewListPricePageController();