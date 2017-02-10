function formatCurrencyFromDouble(value){
    var result = parseFloat(value).toFixed(2);
    result= formatThousandSeparator(result);
    return result + " $";
}
function formatCurrency(value){
    var result ;
    if(typeof value === 'string'){
        var aux = value.replace("$","").trim();
        aux = aux.replace(".","").trim();
        aux = aux.replace(",",".").trim();
        result= formatThousandSeparator(aux);
    }else{
        if(isNaN(value)){
            result = "0,00";
        }else{
            result= formatThousandSeparator(value);
        }
    }
    return result + " $";
}


function formatDouble(value){
    var result ;
    if(typeof value === 'string'){
        var aux = value.replace("$","").trim();
        aux = aux.replace(".","").trim();
        result = aux.replace(",",".").trim();
    }else{
        result = "0.00";

    }
    return parseFloat(result);
}

function formatNumberLength(num, length) {
    var r = "" + num;
    while (r.length < length) {
        r = "0" + r;
    }
    return r;
}

function removeStartingZeros(value) {
    return parseInt(value);
}

function formatThousandSeparator(nNumber){
    nNumber=parseFloat(nNumber);
    if(isNaN(nNumber)){
        nNumber=0;
    }
    var sign = (nNumber >= 0)? 1:-1;
    nNumber = Math.abs(nNumber);
    var iNumber= parseInt(nNumber);
    var result = ","+(parseFloat(nNumber) - iNumber).toFixed(2).substring(2);
    var miles= iNumber%1000;
    miles = ((miles>99 || iNumber<1000) ? "" : (miles<10) ? "00": "0") + miles;
    result = miles + result;
    iNumber=parseInt(iNumber/1000);
    if (iNumber>0){
        miles = iNumber%1000 ;
        miles = ((miles>99|| iNumber<1000) ? "" : (miles<10) ? "00": "0") + miles;
        result = miles+ "."+ result;
    }
    iNumber=parseInt(iNumber/1000);
    if (iNumber>0){
        miles = iNumber%1000 ;
        miles = ((miles>99|| iNumber<1000) ? "" : (miles<10) ? "00": "0") + miles;
        result = miles+ "."+ result;
    }
    result = ((sign==-1)?  "-":"") + result
    return result;
}
