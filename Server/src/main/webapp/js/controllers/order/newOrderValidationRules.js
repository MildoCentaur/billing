function NotEmptyMainFabricValidationRule() {

    this.validate = function () {
        var errors = [];
        if ($("#item-main-fabric").val() == "") {
            errors.push("El tejido del producto es invalido.");
            $("#item-main-fabric").css("border", "1px solid red");
        }
        return errors;

    }
}

function NotEmptyColorValidationRule() {

    this.validate = function () {
        var errors = [];
        if ($("#item-color").val() == "") {
            errors.push("El color elegido es invalido.");
            $("#item-color").css("border", "1px solid red");
        }
        return errors;
    }
}
function AmountMainItemValidationRule() {
    this.validate = function () {
        var errors = [];
        if (!isValidNumber($("#item-main-amount").val()) || $("#item-main-amount").val() <= 0) {
            errors.push("Cantidad de rollos invalida");
            $("#item-main-amount").css("border", "1px solid red");
        }

        if ($("#item-main-amount").val() == 0 && $("#item-main-fabric").val() != "") {
            errors.push("Ingrese la cantidad de rollos del tejido que desea.");
            $("#item-main-fabric").css("border", "1px solid red");
        }
        if ($("#item-main-amount").val() > 0 && $("#item-main-fabric").val() == "") {
            errors.push("Ingrese el tejido que desea.");
            $("#item-main-fabric").css("border", "1px solid red");
        }
        return errors;
    }
}
function AmountCuelloItemValidationRule() {
    this.validate = function () {
        var errors = [];
        if (($("#item-cuello-amount").val() != "" && !isValidNumber($("#item-cuello-amount").val())) || $("#item-cuello-amount").val() < 0) {
            errors.push("Cantidad de cuellos es invalida");
            $("#item-cuello-amount").css("border", "1px solid red");
        }
        if ($("#item-cuello-amount").val() == 0 && $("#item-cuello-fabric").val() != "") {
            errors.push("Ingrese el tejido de cuello que desea.");
            $("#item-cuello-amount").css("border", "1px solid red");
        }

        if ($("#item-cuello-amount").val() > 0 && $("#item-cuello-fabric").val() == "") {
            errors.push("Ingrese el tejido de cuello que desea.");
            $("#item-cuello-amount").css("border", "1px solid red");
        }
        return errors;
    }
}
function AmountTiraItemValidationRule() {
    this.validate = function () {
        var errors = [];
        if (($("#item-tira-amount").val() != "" && !isValidNumber($("#item-tira-amount").val())) || $("#item-tira-amount").val() < 0) {
            errors.push("Cantidad de tiras es invalida");
            $("#item-tira-amount").css("border", "1px solid red");
        }
        if ($("#item-tira-amount").val() == 0 && $("#item-tira-fabric").val() != "") {
            errors.push("Ingrese el tejido de tira que desea.");
            $("#item-tira-amount").css("border", "1px solid red");
        }

        if ($("#item-tira-amount").val() > 0 && $("#item-tira-fabric").val() == "") {
            errors.push("Ingrese el tejido de tira que desea.");
            $("#item-tira-amount").css("border", "1px solid red");
        }
        return errors;
    }
}
function AmountPunoItemValidationRule() {
    this.validate = function () {
        var errors = [];
        if (($("#item-puno-amount").val() != "" && !isValidNumber($("#item-puno-amount").val()) ) || $("#item-puno-amount").val() < 0) {
            errors.push("Cantidad de Puño invalida");
            $("#item-puno-amount").css("border", "1px solid red");
        }

        if ($("#item-puno-amount").val() == 0 && $("#item-puno-fabric").val() != "") {
            errors.push("Ingrese la cantidad de de puño que desea.");
            $("#item-puno-fabric").css("border", "1px solid red");
        }
        if ($("#item-puno-amount").val() > 0 && $("#item-puno-fabric").val() == "") {
            errors.push("Ingrese el tejido de puño que desea.");
            $("#item-puno-fabric").css("border", "1px solid red");
        }

        return errors;
    }
}

function PatternMainItemValidationRule() {
    this.validate = function () {
        var errors = [];

        if ($("#item-main-pattern").val() != "" && $("#item-main-combination").val() == "") {
            errors.push("Ingrese la combinación de rayado para el tejido.");
            $("#item-main-combination").css("border", "1px solid red");
        }

        return errors;
    }
}
function PatternCuelloItemValidationRule() {
    this.validate = function () {
        var errors = [];

        if ($("#item-cuello-pattern").val() != "" && $("#item-cuello-combination").val() == "") {
            errors.push("Ingrese la combinación de rayado para el cuello.");
            $("#item-cuello-combination").css("border", "1px solid red");
        }
        return errors;
    }
}
function PatternTiraItemValidationRule() {
    this.validate = function () {
        var errors = [];

        if ($("#item-tira-pattern").val() != "" && $("#item-tira-combination").val() == "") {
            errors.push("Ingrese la combinación de rayado para el tiras.");
            $("#item-tira-combination").css("border", "1px solid red");
        }
        return errors;
    }
}
function PatternPunoItemValidationRule() {
    this.validate = function () {
        var errors = [];
        if ($("#item-puno-pattern").val() != "" && $("#item-puno-combination").val() == "") {
            errors.push("Ingrese la combinación de rayado para el puño.");
            $("#item-puno-combination").css("border", "1px solid red");
        }
        return errors;
    }
}

