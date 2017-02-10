function NewFabricPageController(){
    PageController.call(this);
    this.tableFiber = "";
    this._initializeAddFabricFormPage= function(){


        this.tableFiber = $('#list_fiber').DataTable( {
            scrollY:        "100px",
            scrollX:        true,
            scrollCollapse: true,
            paging:         false,
            ordering: false,
            filter: false,
            info:     false,
            language: {
                lengthMenu: "Mostrando _MENU_ registros por p�gina",
                zeroRecords: "Que verguenza no encontre nada :(",
                info: "Mostrando p�gina _PAGE_ de _PAGES_",
                infoEmpty: "Que verguenza no encontre nada :(",
                infoFiltered: "(Filtrado sobre _MAX_ registros)"
            }
        } );

        $(".dataTables_scrollHead .table").css("margin-bottom","0px");
        $("#addFabricButton").css("float","right");
        $("#addFabricButton").css("margin-left","10px");
        $("#resetFabricButton").css("float","right");
        $("#resetFabricButton").css("margin-left","10px");
        $("#addFabricButton").click(function(){ pageController.addFabricButtonAction(); });
        $("#addFiberButton").click(function(){ pageController.addFiberButtonAction(); });
        $(".deleteItem").click(function(){pageController.removeFiberButtonAction(this)});
        $("#width").blur(function(){
            var str = $(this).val();
            if(str.match(/(\.|,)/g) == null){
                $(this).val(str + ".00");
            }else{
                str = str.replace(",",".");
                $(this).val(str);
            }
        });
        $("#rinde").blur(function(){
            var str = $(this).val();
            if(str.match(/(\.|,)/g) == null){
                $(this).val(str + ".00");
            }else{
                str = str.replace(",",".");
                $(this).val(str);
            }
        });

    };
    this.validateNewFiber= function(){

        var form = $('#addFabricForm');
        form.find("input").css("border","1px solid #ccc");

        var result = true;
        var value = $("#percentage").val();
        value = value.replace("%","");
        var bValid = isValidNumber(value );
        result = bValid && result;
        if(!bValid){
            $("#percentage").css("border", "1px solid red");
        }
        bValid = isValidName( $("#supplierName").val());
        result = bValid && result;
        if(!bValid){
            $("#supplierName").css("border", "1px solid red");
        }
        value = $("#fiberTitle").val().replace("/","");
        bValid = isValidAlphaNumeric(value);
        result = bValid && result;
        if(!bValid){
            $("#fiberTitle").css("border", "1px solid red");
        }
        return result;
    };
    this.validateNewFabric= function(){

        var form = $('#addFabricForm');
        form.find("input").css("border","1px solid #ccc");

        var result = true;

        var bValid = isValidName( $("#shortname").val());
        result = bValid && result;
        if(!bValid){
            $("#shortname").css("border", "1px solid red");
        }
        bValid = isValidName( $("#longname").val());
        result = bValid && result;
        if(!bValid){
            $("#longname").css("border", "1px solid red");
        }
        bValid = isValidInternalCode( $("#code").val());
        result = bValid && result;
        if(!bValid){
            $("#code").css("border", "1px solid red");
        }

        bValid = isValidIntegerNumber($("#mayas").val());
        result = bValid && result;
        if(!bValid){
            $("#mayas").css("border", "1px solid red");
        }

        bValid = isValidIntegerNumber($("#pasadas").val());
        result = bValid && result;
        if(!bValid){
            $("#pasadas").css("border", "1px solid red");
        }

        var value = $("#polyesterPercent").val().replace("%","");

        bValid = isValidNumber(value) && value >= 0 && value<=100;
        result = bValid && result;
        if(!bValid){
            $("#polyesterPercent").css("border", "1px solid red");
        }
        bValid = isValidNumber( value) && value >= 0 && value<=100;
        result = bValid && result;
        if(!bValid){
            $("#lycraPercent").css("border", "1px solid red");
        }
        bValid = isValidNumber( $("#galga").val());
        result = bValid && result;
        if(!bValid){
            $("#galga").css("border", "1px solid red");
        }

        bValid = isValidDecimal( $("#width").val());
        result = bValid && result;
        if(!bValid){
            $("#width").css("border", "1px solid red");
        }
        bValid = isValidDecimal( $("#rinde").val());
        result = bValid && result;
        if(!bValid){
            $("#rinde").css("border", "1px solid red");
        }

        return result;
    };
}

NewFabricPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
NewFabricPageController.prototype.constructor = NewFabricPageController;

NewFabricPageController.prototype.initialize = function(){
    this.initializePage();
    this._initializeAddFabricFormPage();
};

NewFabricPageController.prototype.addFabricButtonAction = function(){
    var form = $('#addFabricForm');
    form.find("input").css("border","1px solid #ccc");
    if(this.validateNewFabric()){
        this.doPost(form.attr('action'),form.serialize(),function(){
            location.href='list-fabrics.html';
        });
    }else{
        this.showErrorMessage("Se han encontrado errores en el formulario.<br/> Corrija los campos marcados.");
    }

};

NewFabricPageController.prototype.removeFiberButtonAction= function(element){
    var erase = $(element).next().val();
    $(".fiber_"+erase).remove();
    $(element).parents("tr").addClass("active");
    pageController.tableFiber.row('.active').remove().draw();


}
NewFabricPageController.prototype.addFiberButtonAction = function(){
    var isValid = this.validateNewFiber();
    if(!isValid){
        return;
    }

    var index =  this.tableFiber.row().indexes().length+1;
    var action ="<span id='deleteItem_"+index+"' style='padding:0 12px; color: #d9534f;margin:0;'>"+
                    "<i class='fa fa-trash-o' ></i></span>"+
                 "<input type='hidden' value='"+index+"'/>";
    var row = [index,$("#fiberTitle").val(),$("#supplierName").val(),$("#percentage").val(),action];
    this.tableFiber.row.add(row).draw();

    $("#deleteItem_"+index).click(function(){pageController.removeFiberButtonAction(this)});

    var number = $("#list_fiber tbody tr").length -1;
    $('<input>').attr({
        type: 'hidden',
        id: 'fibers'+number+'.titulo',
        name: 'fibers['+number+'].titulo',
        class:'fiber_'+index,
        value:$('#fiberTitle').val()
    }).appendTo($('#addFabricForm'));

    $('<input>').attr({
        type: 'hidden',
        id: 'fibers'+number+'.supplierName',
        name: 'fibers['+number+'].supplierName',
        class:'fiber_'+index,
        value:$('#supplierName').val()
    }).appendTo($('#addFabricForm'));
    var porcentageValue = parseFloat($('#percentage').val());

    $('<input>').attr({
        type: 'hidden',
        id: 'fibers'+number+'.percentage',
        name: 'fibers['+number+'].percentage',
        class:'fiber_'+index,
        value: porcentageValue
    }).appendTo($('#addFabricForm'));

    $("#addFiberModal input").val("");
    $('#addFiberModal').modal('toggle');






};

var pageController = new NewFabricPageController();