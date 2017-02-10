function NewUserPageController(){
    PageController.call(this);
    this._initializeAddUserFormPage= function(){
        $("#btn-new-user").click(function(){
            pageController.addUserButtonAction();
        });
    };
    this.validateNewUser= function(){
        var result = true;
        var bValid = isValidName($("#username").val());
        if(!bValid){
            $("#name").css("border", "1px solid red");
        }
        bValid = isValidEmailAddress($("#email").val());
        result = bValid && result;
        if(!bValid){
            $("#email").css("border", "1px solid red");
        }

        bValid = $("#password").val() == $("#repassword").val() && $("#password").val() != "";
        result = bValid && result;
        if(!bValid){
            $("#password").css("border", "1px solid red");
            $("#repassword").css("border", "1px solid red");
        }

        return result;
    };
}
NewUserPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
NewUserPageController.prototype.constructor = NewUserPageController;

NewUserPageController.prototype.initialize = function(){
    this.initializePage();
    this._initializeAddUserFormPage()
};

NewUserPageController.prototype.addUserButtonAction = function(){
    var form = $('#new-user-form');
    form.find("input").css("border","1px solid #ccc");
    if(this.validateNewUser()){
        this.doPost(form.attr('action'),form.serialize(),function(){
            location.href="list-users.html";
        });

    }else{
        this.showErrorMessage("Se han encontrado errores en el formulario.<br/> Corrija los campos marcados.");
    }

};

var pageController = new NewUserPageController();