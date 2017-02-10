function isEmpty(value){
    return value=="" || value=='undefined';
}
function isValidName(value){
    var valid = true;
    var pattern = /^[a-zA-ZáéíóúüÁÉÍÓÚÜñÑ][a-zA-Z\.\-_\s,áéíóúüÁÉÍÓÚÜñÑ]+$/g;
    if (value == "" || value.match(pattern)==null) {
        valid = false;
    }
    return valid;
}
function isValidColorCordinate(value){
    if (isValidDecimal(value)){
        return true;
    }

    var pattern = /([0-9a-fA-F])+/g;
    var aux = value.match(pattern);
    return aux != null && aux.length == 1 && aux[0].length == value.length;
}
function isValidNumber(value){

    if (isValidDecimal(value)){
        return true;
    }
    return isValidIntegerNumber(value);
}
function isValidIntegerNumber(value) {
    var pattern = /[0-9]+/g;
    var aux = value.match(pattern);
    return aux != null && aux.length == 1 && aux[0].length == value.length;
}

function isValidDecimal(value){
    var pattern = /[0-9]+(\,|\.){0,1}[0-9]+/g;
    var aux = value.match(pattern);
    return aux != null && aux.length == 1 && aux[0].length == value.length
}
//1.000,00 $
function isValidMoneyExpression(value){
    var aux = value.replace("$","").trim();
    aux=aux.replace(".","");
    aux=aux.replace(",",".");
    if (aux.indexOf("-") > 0 ){
        return false;
    }
    aux=aux.replace("-","");
    return isValidNumber(aux);
}
function isValidEmailAddress(value){
    var pattern = /^[0-9a-zA-Z\.\-_]+@[a-zA-Z]+?(\.[a-zA-Z]{2,3})+$/g;
    return (value.match(pattern) != null);
}
function isValidPhoneNextelNumber(value){
    var pattern = /^[0-9_*\-\s ]+$/g;
    return (value.match(pattern) != null);
}

function isValidCuitNumber(value){
    var valid = true;
    if (value==""){
        valid = false;
    }else{
        value= value.replace(/-/g, "");
        if(value.length!=11){
            valid = false;
        }
    }

    return valid;
}

function isValidAlphaNumeric(value){
    var valid = true;
    pattern = /[a-zA-Z0-9áéíóúüÁÉÍÓÚÜñÑ/\s -_]+/g;
    var match = value.match(pattern);
    if (value == "" || match==null || match[0].length != value.length) {
        valid = false;
    }
    return valid;
}

function isValidAlpha(value){
    var valid = true;
    pattern = /[a-zA-ZáéíóúüÁÉÍÓÚÜñÑ\s ]+/g;
    var match = value.match(pattern);
    if (value == "" || match==null || match[0].length != value.length) {
        valid = false;
    }
    return valid;
}

function isValidPostalCode(value){
    var valid = true;
    pattern = /[a-zA-Z]{0,1}[0-9]{4}[a-zA-Z]{0,3}/g;
    var match = value.match(pattern);
    if (value == "" || match==null || match[0].length != value.length) {
        valid = false;
    }
    return valid;
}

function isValidInternalCode(value){
    var valid = true;
    pattern = /([0-9]{0,5}[a-zA-Z]{0,3})+/g;
    var match = value.match(pattern);
    if (value == "" || match==null || match[0].length != value.length) {
        valid = false;
    }
    return valid;
}