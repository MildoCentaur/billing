function NewProductPageController(){
    PageController.call(this);


    this._initializeBehavior=function(){

        $("#is-stripe").change(function() {
            $( ".stripe" ).prop( "disabled", !this.checked );
        });

        $(".currency").blur(function(){
            $(this).next().val(formatDouble($(this).val()));
        });

        $("#btn-new-product").click(function(){pageController.addNewProductPrice();});
    };

    this._validateNewProduct=function(){
        $(":text").css("border-color","#ccc");

        var hasError= false;
        if(!isValidMoneyExpression($("#priceWhite").val())){
            $("#price-white").css("border-color","red");
            hasError=true;
        }
        if(!isValidMoneyExpression($("#priceLight").val())){
            $("#price-light").css("border-color","red");
            hasError=true;
        }
        if(!isValidMoneyExpression($("#priceDark").val())){
            $("#price-dark").css("border-color","red");
            hasError=true;
        }
        if(!isValidMoneyExpression($("#priceSpecial").val())){
            $("#price-special").css("border-color","red");
            hasError=true;
        }

        if(parseFloat($("#priceWhite").val()) > parseFloat($("#priceLight").val())){
            $("#price-light").css("border-color","red");
            $("#price-white").css("border-color","red");
            hasError=true;
        }
        if(parseFloat($("#priceWhite").val()) > parseFloat($("#priceDark").val())){
            $("#price-dark").css("border-color","red");
            $("#price-white").css("border-color","red");
            hasError=true;
        }
        if(parseFloat($("#priceWhite").val()) > parseFloat($("#priceSpecial").val())){
            $("#price-special").css("border-color","red");
            $("#price-white").css("border-color","red");
            hasError=true;
        }

        if(parseFloat($("#priceLight").val()) > parseFloat($("#priceDark").val())){
            $("#price-dark").css("border-color","red");
            $("#price-light").css("border-color","red");
            hasError=true;
        }
        if(parseFloat($("#priceLight").val()) > parseFloat($("#priceSpecial").val())){
            $("#price-special").css("border-color","red");
            $("#price-light").css("border-color","red");
            hasError=true;
        }

        if(parseFloat($("#priceDark").val()) > parseFloat($("#priceSpecial").val())){
            $("#price-special").css("border-color","red");
            $("#price-dark").css("border-color","red");
            hasError=true;
        }



        return hasError;
    };



}

NewProductPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
NewProductPageController.prototype.constructor = NewProductPageController;


NewProductPageController.prototype.initialize = function(){
    this.initializePage();
    this._initializeBehavior();
};

NewProductPageController.prototype.addNewProductPrice= function(){
    var priceErrorMsg="Error en el valor de los precios";
    var url = 'add/product-family.json';
    if(this._validateNewProduct()){
        pageController.showErrorMessage(priceErrorMsg);
        return;
    }
    $( ".stripe" ).prop( "disabled", false);
    pageController.doPost(url,$("form").serialize(),function(data){
        location.href='list-prices.html';
    });
};

var pageController = new NewProductPageController();