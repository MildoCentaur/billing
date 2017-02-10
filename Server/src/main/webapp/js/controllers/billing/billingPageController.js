function BillingPageController(){
    PageController.call(this);
    this._prices = [];
    this.DataTable ={};
    this._initializeWebElements= function() {

    };
    this._initializeListing= function (){
        this.DataTable=$('#list-bill-items').DataTable({
            "language": {
                "lengthMenu": "Mostrando _MENU_ registros por página",
                "zeroRecords": "No hay items para facturar.",
                "info": "Mostrando página _PAGE_ de _PAGES_",
                "infoEmpty": "No hay items para facturar.",
                "infoFiltered": "(Filtrado sobre _MAX_ registros)",
                "search":"Buscar: ",
                "decimal": ",",
                "thousands": "."
            },
            "scrollY": "600px",
            "scrollCollapse": true,
            "paging":   false,
            "ordering": false,
            "info":     false,
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
            footerCallback: function ( row, data, start, end, display ) {
                var api = this.api(), data;

                // Remove the formatting to get integer data for summation
                var doubleVal = function ( i ) {
                    if ( typeof i === 'string'){
                        i =  formatDouble(i);
                    }else{
                        i = (typeof i === 'number') ? i : 0;
                    }

                    return i;
                };
                // Total over all pages
                data = api.column( 4 ).data();
                total = data.length ?
                    data.reduce( function (a, b) {
                        return doubleVal(a) + doubleVal(b);
                    } ) : 0;

                // Update footer
                $(".bill-subtotal").val(total);
                $( api.column( 4 ).footer() ).html( formatCurrency(total) );

                // Total over all pages
                data = api.column( 5 ).data();
                total = data.length ?
                    data.reduce( function (a, b) {
                        return doubleVal(a) + doubleVal(b);
                    } ) :
                    0;

                // Update footer
                $(".bill-tax").val(total);
                $( api.column( 5 ).footer() ).html( formatCurrency(total) );

                // Total over all pages
                data = api.column( 6 ).data();
                total = data.length ?
                    data.reduce( function (a, b) {
                        return doubleVal(a) + doubleVal(b);
                    } ) :
                    0;

                // Update footer
                $(".bill-total").val(total);
                $( api.column( 6 ).footer() ).html( formatCurrency(total) );
            },
            "order": [[ 0, "desc" ]],
            "drawCallback" : function(settings){
                $(".dataTables_scrollHead .table").css("margin-bottom","0px");
                $(".dataTables_scrollBody .table").css("margin-bottom","0px");
                $(".dataTables_scrollBody thead th").removeClass("sorting");
                $(".dataTables_scrollBody thead th").removeClass("sorting_asc");
                $(".dataTables_scrollBody thead th").removeClass("sorting_desc");
                var heightHeader = $(".dataTables_scrollBody thead th").height();
                var height =  $(".dataTables_scrollBody").height();
                $(".dataTables_scrollBody").height(height - heightHeader)
            }
        });

        $('#list-bill-items tbody').on('click', 'tr', function () {
            var id = this.id;

            $('#list-bill-items tbody tr').each(function(index,element){
                $(element).removeClass("active");
            });
            $(this).toggleClass('active');
        });
    };
    this._initializeWebElements=function(){
        if($(".bill-id").val()>0){
            $(".autocomplete-client").prop("disabled",true);
        }
        $("#item-product").blur(function(){
            $("#item-color").focus();
        });
        $("#item-color").blur(function(){
            $("#item-product-amount").focus();
        });

        $("#item-product-amountr").blur(function(){
            $("#item-price").focus();
        });

        $("#item-price").blur(function(){
            $("#add-bill-item").focus();
        });

        if($(".bill-id").val()==0){
            $("#btn-print").css("display","none");
        }

        $("#btn-clear-bill").click(function(){
            location.reload();
        });
        $("#btn-add-item-table").addClass("disabled");
        $("#btn-accept").addClass("disabled");

        $("#close-bill-item").click(function(){
            pageController.clearAddNewItemForm();
        });

        $("#btn-accept").click(function(){
            pageController.acceptBill();
        });

        $("#add-bill-item").click(function(){
            pageController.addBillItem();
        });

    };
    this._initializeAutocompleteElements= function(){

        $(".autocomplete-client").catcomplete({
            delay: 10,
            source: function(request, response) {
                var name = request.term;
                return jQuery.get("search-entities.json?query=" + name, function(data) {
                    result = [];
                    pageController.sessionTime = 1;
                    $.each(data,function(i,e){

                        if(i.toLowerCase()=="clientes"){
                            $.each(e,function(i2,e2){
                                result.push({label:e2.name,category:i,id:e2.id,address:e2.address,cuit:e2.cuit,iva:e2.ivacondition});
                            });
                        }
                    });
                    return response(result);
                });
            },
            select: function( event, ui ) {
                $(this).val(ui.label);
                $(".client-id").val(ui.item.id);
                $(".autocomplete-client").data("client-name",ui.item.label);
                $(".client-address").val(ui.item.address);
                $(".nro-cuit").val(ui.item.cuit);
                $("#ivaCategory").val(ui.item.iva);
                if(ui.item.iva=="RI"){
                    $("#boxBillType").text("A");
                    $("#orderNumber").val($("#orderNumber").data("order-a-number"));
                    $("#billNumber").val($("#billNumber").data("bill-a-number"));
                }else{
                    $("#orderNumber").val($("#orderNumber").data("order-b-number"));
                    $("#billNumber").val($("#billNumber").data("bill-b-number"));
                    $("#boxBillType").text("B");
                }
                $("#btn-add-item-table").removeClass('disabled');
                $("#btn-add-item-top").removeClass('disabled');
                $("#btn-print").removeClass('disabled');

            },
            minLength : 2
        });
        $(".autocomplete-client").blur(function(){
            if($(".autocomplete-client").val()!="") {
                $(".autocomplete-client").val($(".autocomplete-client").data("client-name"));
            }else{
                $("#btn-add-item-table").addClass('disabled');
                $("#btn-add-item-top").addClass('disabled');
                $("#btn-accept").addClass('disabled');
            }
        });
        $("#item-color").catcomplete({
            delay: 10,
            source: function(request, response) {
                var name = request.term;
                return jQuery.get("search-entities.json?query=" + name, function(data) {
                    result = [];
                    pageController.sessionTime = 1;

                    $.each(data,function(i,e){
                        if(i.toLowerCase()=="colores"){
                            $.each(e,function(i2,e2){
                                result.push({label:e2.name,category:i,id:e2.id,colorType:e2.type});
                            });
                        }
                    });
                    return response(result);
                });
            },
            select: function( event, ui ) {
                $(this).val(ui.item.label);
                $("#item-color").data("color-id",ui.item.id);
                $("#item-color").data("color-name",ui.item.label);
                $("#item-color").data("color-type",ui.item.colorType);
                $.each(pageController._prices,function(familyName,familyList){
                    if(familyName==$("#item-product").data("product-family-name")){
                        $.each(familyList,function(i,family){
                            if(family.colorType.toLowerCase() == $("#item-color").data("color-type").toLowerCase()){
                                $("#item-product").data("product-family-price",family.price);
                                $("#item-product").data("product-family-id",family.id);
                                $("#item-price").val(formatCurrency(family.price));
                            }
                        });
                    }
                });
            },
            minLength : 2
        });
        $("#item-color").blur(function(){
            if($("#item-color").val()!="") {
                $("#item-color").val($("#item-color").data("color-name"));
            }
        });

        $("#item-product").catcomplete({
            delay: 5,
            source: function(request, response) {
                var term = request.term;
                var result=[];
                var data = pageController.loadPrices(term);
                $.each(data,function(i,e){
                    result.push({label:e,category:'Productos'});
                });
                return response(result);
            },
            select: function( event, ui ) {
                $(this).val(ui.item.label);
                $("#item-product").data("product-family-id",ui.item.id);
                $("#item-product").data("product-family-name",ui.item.label);
            },
            minLength : 2
        });
        $("#item-color").prop("disabled",true);
        $("#item-product").blur(function(){
            if($("#item-product").val()!="") {
                $("#item-product").val($("#item-product").data("product-family-name"));
                $("#item-color").prop("disabled",false);
            }

        });

    };
}



BillingPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
BillingPageController.prototype.constructor = BillingPageController;

BillingPageController.prototype.initialize = function(){
    this.initializePage();
    this._initializeListing();
    this._initializeAutocompleteElements();
    this._initializeWebElements();
    this.loadPrices("");
};

BillingPageController.prototype.clearAddNewItemForm = function(){
    $('#add-bill-item-modal').modal('hide');
    $('#add-bill-item-modal').find('input[type=hidden]').val('');
    $('#add-bill-item-modal').find('input[type=text]').val('');

    $("#product-amount").val('0');

};


BillingPageController.prototype.deleteBillItem = function(obj){
    var rowToDelete = $(obj).parents('tr');
    var cell = $(rowToDelete.children()[0]);
    var rowNumber =  cell.text();
    var items = pageController.DataTable.rows().nodes().length;

    if (items == 1 ) {
        $("#btn-accept").addClass("disabled");
    }

    pageController.DataTable.row(rowToDelete).remove();
    pageController.DataTable.draw();
    $(".bill-item-" + rowNumber).val(0);

};

BillingPageController.prototype.addBillItem = function(){
    var quantity = $("#item-product-amount").val();
    var priceStr = $("#item-price").val();
    var price = formatDouble(priceStr);

    var description = $("#item-product").data("product-family-name") + " " +  $("#item-color").data("color-name");
    var subtotal = price * parseFloat(quantity);
    var IVAValue = (price  * parseFloat(quantity))*0.21;
    var total = (price  * parseFloat(quantity))*1.21 ;

    if($("#boxBillType").val()== "B"){
        subtotalWithoutIVA = "";
        IVAValue = "";
        total = "";
    }

    var action ="<span class='deleteItem btn btn-danger btn-outline' onclick='pageController.deleteBillItem(this);'>"+
        "<i class='fa fa-trash-o' ></i>"+
        "</span>";

    var realItemNumber = pageController.DataTable.data().length;
    var rowNumber = realItemNumber + 1;

    var item = "<input type='hidden' class='bill-item-"+rowNumber+"' id='items"+realItemNumber+".amount' name='items["+realItemNumber+"].amount' value='"+quantity+"' />" +
        "<input type='hidden' class='bill-item-"+rowNumber+"' id='items"+realItemNumber+".productFamily.id' name='items["+realItemNumber+"].productFamily.id' value='"+$("#item-product").data("product-family-id")+"' />" +
        "<input type='hidden' class='bill-item-"+rowNumber+"' id='items"+realItemNumber+".color.id' name='items["+realItemNumber+"].color.id' value='"+$("#item-color").data("color-id")+"' />" +
        "<input type='hidden' class='bill-item-"+rowNumber+"' id='items"+realItemNumber+".price' name='items["+realItemNumber+"].price' value='"+price+"' />" +
        "<input type='hidden' class='bill-item-"+rowNumber+"' id='items"+realItemNumber+".subtotal' name='items["+realItemNumber+"].subtotal' value='"+subtotal+"' />"+
        "<input type='hidden' class='bill-item-"+rowNumber+"' id='items"+realItemNumber+".tax' name='items["+realItemNumber+"].tax' value='"+IVAValue+"' />"+
        "<input type='hidden' class='bill-item-"+rowNumber+"' id='items"+realItemNumber+".total' name='items["+realItemNumber+"].total' value='"+total+"' />";

    $("form").append(item);
    subtotal = formatCurrency (subtotal);
    IVAValue = formatCurrency (IVAValue);
    total = formatCurrency (total);

    var row = [ rowNumber,quantity, description,priceStr,subtotal,IVAValue,total,action];
    this.DataTable.row.add(row).draw();
    this.clearAddNewItemForm();
    $("#btn-accept").removeClass("disabled");
};



BillingPageController.prototype.loadPrices = function(name){
    if(!$.isEmptyObject( this._prices) ){
        var result = [];

        $.each(this._prices, function(index, element) {
            var pattern = "(.*)"+name+"(.*)";
            var aux = index.match(pattern);
            if (aux!=null || name.trim() == ""){
                result.push(index);
            }
        });

        return result;
    }
    jQuery.get("data/product-prices.json", function(data) {
        var aux = [];
        var result = {};
        var prev ="";
        pageController.sessionTime = 1;

        $.each(data,function(i,e){

            if(e.name!=prev && prev != ""){
                result[aux[0].label]= aux;
                aux=[];
            }

            var name = e.name.trim();
            aux.push({label:name,colorType:e.colorType,id:e.id,price:e.price});
            prev = e.name;
        });

        pageController._prices= result;
    });


};

BillingPageController.prototype.acceptBill = function(){
    var url = $("form").attr("action");
    $(".bill-number").val(removeStartingZeros($(".bill-number").val()));
    $(".order-number").val(removeStartingZeros($(".order-number").val()));
    $(".order-number").prop("disabled",false);
    $(".bill-number").prop("disabled",false);
    pageController.doPost(url, $("form").serialize(),function(data){
        window.location.href='list-bills.html'
    });
    $(".order-number").prop("disabled",true);
    $(".bill-number").prop("disabled",true);
};

var pageController = new BillingPageController();

