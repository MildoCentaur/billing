function NewColorPageController(){
    PageController.call(this);
    this._initializeAddClientFormPage= function(){
        $("#addColorButton").click(function(){ pageController.addColorButtonAction(); });
        $("#addColorButton").css("float","right");
        $("#addColorButton").css("margin-left","10px");
        $("#resetColorButton").css("float","right");
        $("#resetColorButton").css("margin-left","10px");

        $("#code").catcomplete({
            delay: 2,
            source: function (request, response) {
                var name = request.term;
                return jQuery.get("search-entities.json?query=" + name, function (data) {
                    result = [];
                    pageController.sessionTime = 1;
                    $.each(data, function (i, e) {


                        if (i.toLowerCase() == "colores") {
                            $.each(e, function (i2, e2) {
                                result.push({label: e2.name, category: i, id: e2.id});
                            });
                        }

                    });
                    return response(result);
                });
            },
            select: function (event, ui) {
                var uiEntity = ui.item.category.toLowerCase();
                var entity = uiEntity == 'clientes' ? 'cliente' : (uiEntity == 'Proveedores' ? 'supplier' : 'fabric');
                location.href = "edit-" + entity + ".html?id=" + ui.item.id;
            },
            minLength: 2
        });
    };
    this.validateNewColor= function(){
        var result = true;
        var bValid = isValidName( $("#name").val());
        if(!bValid){
            $("#name").css("border", "1px solid red");
        }
        bValid = isValidNumber( $("#code").val());
        result = bValid && result;
        if(!bValid){
            $("#code").css("border", "1px solid red");
        }
        var value = $("input#coordinate").val();
        bValid = isValidColorCordinate( value.replace('#',''));
        result = bValid && result;
        if(!bValid){
            $("#coordinate").css("border", "1px solid red");
        }

        return result;
    };
}
NewColorPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
NewColorPageController.prototype.constructor = NewColorPageController;

NewColorPageController.prototype.initialize = function(){
    this.initializePage();
    this._initializeAddClientFormPage()
};

NewColorPageController.prototype.addColorButtonAction = function(){
    var form = $('#addColorForm');
    form.find("input").css("border","1px solid #ccc");
    if(this.validateNewColor()){
        this.doPost(form.attr('action'),form.serialize(),function(){
            location.href="list-colors.html";
        });

    }else{
        this.showErrorMessage("Se han encontrado errores en el formulario.<br/> Corrija los campos marcados.");
    }

};

var pageController = new NewColorPageController();