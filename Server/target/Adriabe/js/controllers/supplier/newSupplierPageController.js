function NewSupplierPageController() {
    PageController.call(this);
    this.tableStores;
    this.tableContacts;

    this._initializeAddSupplierFormPage = function () {
        $("#IVACondition").blur(function () {
            if ($("#IVACondition").val() == "SNC") {
                $("#cuit").val("00-00000000-0");
            }
        });

        this.tableStores = $('#stores').dataTable({
            scrollY: "100px",
            scrollX: true,
            scrollCollapse: true,
            paging: false,
            ordering: false,
            filter: false,
            info: false,
            language: {
                lengthMenu: "Mostrando _MENU_ registros por p?gina",
                zeroRecords: "Que verguenza no encontre nada :(",
                info: "Mostrando p�gina _PAGE_ de _PAGES_",
                infoEmpty: "Que verguenza no encontre nada :(",
                infoFiltered: "(Filtrado sobre _MAX_ registros)"
            }
        });

        $(".dataTables_scrollHead .table").css("margin-bottom", "0px");
        this.tableContacts = $('#contacts').dataTable({
            scrollY: "100px",
            scrollX: true,
            scrollCollapse: true,
            paging: false,
            ordering: false,
            filter: false,
            info: false,
            language: {
                lengthMenu: "Mostrando _MENU_ registros por p�gina",
                zeroRecords: "Que verguenza no encontre nada :(",
                info: "Mostrando p�gina _PAGE_ de _PAGES_",
                infoEmpty: "Que verguenza no encontre nada :(",
                infoFiltered: "(Filtrado sobre _MAX_ registros)"
            }
        });


        $("#addSupplierButton").css("float", "right");
        $("#addSupplierButton").css("margin-left", "10px");
        $("#cleanAddSupplierForm").css("float", "right");
        $("#cleanAddSupplierForm").css("margin-left", "10px");

        $("#addSupplierButton").click(function () {
            pageController.addSupplierButtonAction();
        });
        $("#addStoreButton").click(function () {
            pageController.addStoreButtonAction();
        });
        $("#addContactButton").click(function () {
            pageController.addContactButtonAction();
        });

        $(".dataTables_scrollHead .table").css("margin-bottom", "0px");

    };
    /**
     * Validates if the values inserted for the store are correct
     */
    this._validateNewStore = function () {
        var result = true;
        var bValid = !isEmpty($("#storeFeria").val()) && isValidName($("#storeFeria").val());
        if (!bValid) {
            $("#storeFeria").css("border", "1px solid red");
        }
        bValid = !isEmpty($("#storePasillo").val()) && isValidNumber($("#storePasillo").val());
        result = bValid && result;
        if (!bValid) {
            $("#storePasillo").css("border", "1px solid red");
        }
        bValid = !isEmpty($("#storePuesto").val()) && isValidNumber($("#storePuesto").val());
        result = bValid && result;
        if (!bValid) {
            $("#storePuesto").css("border", "1px solid red");
        }
        return result;
    };

    this._validateNewSupplierContact = function () {
        var result = true;
        var bValid = !isEmpty($("#contactOffice").val()) && isValidName($("#contactOffice").val());
        if (!bValid) {
            $("#contactOffice").css("border", "1px solid red");
        }
        bValid = !isEmpty($("#contactName").val()) && isValidName($("#contactName").val());
        result = bValid && result;
        if (!bValid) {
            $("#contactName").css("border", "1px solid red");
        }

        bValid = $("#contactAlias").val() == "" || isValidName($("#contactAlias").val());
        result = bValid && result;
        if (!bValid) {
            $("#contactAlias").css("border", "1px solid red");
        }
        bValid = !isEmpty($("#contactPhone").val()) && isValidPhoneNextelNumber($("#contactPhone").val());
        result = bValid && result;
        if (!bValid) {
            $("#contactPhone").css("border", "1px solid red");
        }
        bValid = $("#contactNextel").val() == "" || isValidPhoneNextelNumber($("#contactNextel").val());
        result = bValid && result;
        if (!bValid) {
            $("#contactNextel").css("border", "1px solid red");
        }
        bValid = $("#contactEmail").val() == "" || isValidEmailAddress($("#contactEmail").val());
        result = bValid && result;
        if (!bValid) {
            $("#contactEmail").css("border", "1px solid red");
        }
        return result;
    };

    this._validateNewSupplierForm = function () {

        var result = true;
        var bValid = !isEmpty($("#name").val()) && isValidName($("#name").val());
        result = bValid && result;
        if (!bValid) {
            $("#name").css("border", "1px solid red");
        }
        bValid = !isEmpty($("#postalCode").val()) && isValidPostalCode($("#postalCode").val());
        result = bValid && result;
        if (!bValid) {
            $("#postalCode").css("border", "1px solid red");
        }
        bValid = !isEmpty($("#country").val()) && isValidName($("#country").val());
        result = bValid && result;
        if (!bValid) {
            $("#country").css("border", "1px solid red");
        }

        bValid = $("#comments").val() == "" || isValidAlphaNumeric($("#comments").val());
        result = bValid && result;
        if (!bValid) {
            $("#addressDelivery").css("border", "1px solid red");
        }
        bValid = !isEmpty($("#cuit").val()) && isValidCuitNumber($("#cuit").val());
        result = bValid && result;
        if (!bValid) {
            $("#cuit").css("border", "1px solid red");
        }
        bValid = !isEmpty($("#credit").val()) && isValidMoneyExpression($("#credit").val());
        result = bValid && result;
        if (!bValid) {
            $("#credit").css("border", "1px solid red");
        }

        bValid = !isEmpty($("#balanceActivity").val()) && isValidMoneyExpression($("#balanceActivity").val());
        result = bValid && result;
        if (!bValid) {
            $("#balanceActivity").css("border", "1px solid red");
        }
        bValid = !isEmpty($("#balanceBilling").val()) && isValidMoneyExpression($("#balanceBilling").val());
        result = bValid && result;
        if (!bValid) {
            $("#balanceBilling").css("border", "1px solid red");
        }
        bValid = !isEmpty($("#address").val()) && isValidAlphaNumeric($("#address").val());
        result = bValid && result;
        if (!bValid) {
            $("#address").css("border", "1px solid red");
        }
        bValid = !isEmpty($("#state").val()) && isValidAlpha($("#state").val());
        result = bValid && result;
        if (!bValid) {
            $("#state").css("border", "1px solid red");
        }
        bValid = !isEmpty($("#localidad").val()) && isValidAlpha($("#localidad").val());
        result = bValid && result;
        if (!bValid) {
            $("#localidad").css("border", "1px solid red");
        }
        bValid = !isEmpty($("#nickname").val()) || isValidName($("#nickname").val());
        result = bValid && result;
        if (!bValid) {
            $("#nickname").css("border", "1px solid red");
        }
        bValid = !isEmpty() && isValidPhoneNextelNumber($("#phone").val());
        result = bValid && result;
        if (!bValid) {
            $("#phone").css("border", "1px solid red");
        }
        bValid = $("#nextel").val() == "" || isValidPhoneNextelNumber($("#nextel").val());
        result = bValid && result;
        if (!bValid) {
            $("#nextel").css("border", "1px solid red");
        }
        bValid = $("#fax").val() == "" || isValidPhoneNextelNumber($("#fax").val());
        result = bValid && result;
        if (!bValid) {
            $("#fax").css("border", "1px solid red");
        }
        bValid = $("#email").val() == "" || isValidEmailAddress($("#email").val());
        result = bValid && result;
        if (!bValid) {
            $("#email").css("border", "1px solid red");
        }
        /*
         bValid = $("#IVACondition").val()!=null;
         result = bValid && result;
         if(!bValid){
         $("#IVACondition").css("border", "1px solid red");
         }
         */
        if ($("#credit").val() == "") {
            $("#credit").val("0.00 $");
        }
        bValid = !isEmpty($("#credit").val()) && isValidMoneyExpression($("#credit").val());
        result = bValid && result;
        if (!bValid) {
            $("#credit").css("border", "1px solid red");
        }

        if ($("#creditInCheques").val() == "") {
            $("#creditInCheques").val("0.00 $");
        }
        bValid = !isEmpty($("#creditInCheques").val()) && isValidMoneyExpression($("#creditInCheques").val());
        result = bValid && result;
        if (!bValid) {
            $("#creditInCheques").css("border", "1px solid red");
        }

        if ($("#balanceBilling").val() == "") {
            $("#balanceBilling").val("0.00 $");
        }
        bValid = !isEmpty($("#balanceBilling").val()) && isValidMoneyExpression($("#balanceBilling").val());
        result = bValid && result;
        if (!bValid) {
            $("#balanceBilling").css("border", "1px solid red");
        }

        if ($("#balanceActivity").val() == "") {
            $("#balanceActivity").val("0.00 $");
        }
        bValid = !isEmpty($("#balanceActivity").val()) && isValidMoneyExpression($("#balanceActivity").val());
        result = bValid && result;
        if (!bValid) {
            $("#balanceActivity").css("border", "1px solid red");
        }


        return result;

    };
}
NewSupplierPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
NewSupplierPageController.prototype.constructor = NewSupplierPageController;

NewSupplierPageController.prototype.initialize = function () {
    this.initializePage();
    this._initializeAddSupplierFormPage();
    //this._listingAdjustments();

};
NewSupplierPageController.prototype.addSupplierButtonAction = function () {
    var form = $('#addSupplierForm');
    form.find("input").css("border", "1px solid #ccc");
    var isValidSupplier = this._validateNewSupplierForm();


    if (isValidSupplier) {
        $(".currency").each(function (i, e) {
            $(e).val($(e).val().replace('$', '').trim());
            $(e).val($(e).val().replace('.', '').trim());
            $(e).val($(e).val().replace(',', '.').trim());
        });
        this.doPost(form.attr('action'), form.serialize(), function () {
            location.href = 'list-suppliers.html';
        });

    } else {
        this.showErrorMessage("Se han encontrado errores en el formulario.<br/> Corrija los campos marcados.");
    }
};

NewSupplierPageController.prototype.addContactButtonAction = function () {


    var isValid = this._validateNewSupplierContact();
    if (!isValid) {
        return;
    }


    this.tableContacts.fnAddData([$("#contactName").val(), $("#contactAlias").val(), $("#contactOffice").val(),
        $("#contactPhone").val(), $("#contactNextel").val(), $("#contactEmail").val()], true);


    var number = $("#contacts tbody tr").length - 1;
    $('<input>').attr({
        type: 'hidden',
        id: 'contacts' + number + '.phone',
        name: 'contacts[' + number + '].phone',
        value: $('#contactPhone').val()
    }).appendTo($('#addSupplierForm'));

    $('<input>').attr({
        type: 'hidden',
        id: 'contacts' + number + '.nextel',
        name: 'contacts[' + number + '].nextel',
        value: $('#contactNextel').val()
    }).appendTo($('#addSupplierForm'));

    $('<input>').attr({
        type: 'hidden',
        id: 'contacts' + number + '.email',
        name: 'contacts[' + number + '].email',
        value: $('#contactEmail').val()
    }).appendTo($('#addSupplierForm'));

    $('<input>').attr({
        type: 'hidden',
        id: 'contacts' + number + '.name',
        name: 'contacts[' + number + '].name',
        value: $('#contactName').val()
    }).appendTo($('#addSupplierForm'));

    $('<input>').attr({
        type: 'hidden',
        id: 'contacts' + number + '.nickname',
        name: 'contacts[' + number + '].nickname',
        value: $('#contactAlias').val()
    }).appendTo($('#addSupplierForm'));

    $('<input>').attr({
        type: 'hidden',
        id: 'contacts' + number + '.office',
        name: 'contacts[' + number + '].office',
        value: $('#contactOffice').val()
    }).appendTo($('#addSupplierForm'));
    $("#addContactsModal input").val("");
    $('#addContactsModal').modal('toggle');
};

NewSupplierPageController.prototype.addStoreButtonAction = function () {
    var isValid = this._validateNewStore();
    if (!isValid) {
        return;
    }

    this.tableStores.fnAddData([$("#storeFeria").val(), $("#storePasillo").val(), $("#storePuesto").val()], true);

    var number = $("#stores tbody tr").length - 1;
    $('<input>').attr({
        type: 'hidden',
        id: 'stores' + number + '.feria',
        name: 'stores[' + number + '].feria',
        value: $('#storeFeria').val()
    }).appendTo($('#addSupplierForm'));

    $('<input>').attr({
        type: 'hidden',
        id: 'stores' + number + '.pasillo',
        name: 'stores[' + number + '].pasillo',
        value: $('#storePasillo').val()
    }).appendTo($('#addSupplierForm'));

    $('<input>').attr({
        type: 'hidden',
        id: 'stores' + number + '.puesto',
        name: 'stores[' + number + '].puesto',
        value: $('#storePuesto').val()
    }).appendTo($('#addSupplierForm'));

    $("#addStoreModal input").val("");
    $('#addStoreModal').modal('toggle');

};

var pageController = new NewSupplierPageController();
