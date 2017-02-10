function NewStripedPageController() {
    PageController.call(this);

    this.DataTable = '';
    this._initializeAddStripeFormPage = function () {
        $("#addStripeButton").css("float", "right");
        $("#addStripeButton").css("margin-left", "10px");
        $("#resetStripeButton").css("float", "right");
        $("#resetStripeButton").css("margin-left", "10px");

        $("#addStripeButton").click(function () {
            pageController.addStripeButtonAction();
        });
        $("#addStripeLineButton").click(function () {
            pageController.addStripeLineButtonAction();
        });
        $("#addStripeCombinationButton").click(function () {
            pageController.addStripeCombinationButtonAction();
        });
        $("#refresh").click(function () {
            pageController.updateStripe();
        });
        $("#listingFormula").blur(function () {
            pageController.updateStripe();
        });

        $("#btn-zoom-in").click(function () {
            pageController.zoomIn();
        });
        $("#btn-zoom-out").click(function () {
            pageController.zoomOut();
        });

        this.DataTable = $('#list_combination').DataTable({
            "language": {
                "lengthMenu": "Mostrando _MENU_ registros por página",
                "zeroRecords": "No se registran combinaciones para este patrón",
                "info": "Mostrando página _PAGE_ de _PAGES_",
                "infoEmpty": "No hay información para mostrar",
                "infoFiltered": "(Filtrado sobre _MAX_ registros)",
                "search": "Buscar: ",
                "decimal": ",",
                "thousands": "."
            },
            "columnDefs": [
                { "width": "34%", "targets": 0 },
                { "width": "11%", "targets": 1 },
                { "width": "11%", "targets": 2 },
                { "width": "11%", "targets": 3 },
                { "width": "11%", "targets": 4 },
                { "width": "11%", "targets": 5 },
                { "width": "11%", "targets": 6 },

            ],
            "scrollY": "300px",
            "scrollCollapse": true,
            "paging": false,
            "filter": false,
            "info": false,
            "ordering": false
        });
        $(".dataTables_scrollHead .table").css("margin-bottom", "0px");
        $(".dataTables_scrollBody .table").css("margin-bottom", "0px");
        /*Elimina el margen que queda por el scroll*/
        var heightHeader = $(".dataTables_scrollBody thead th").height();
        $(".dataTables_scrollBody thead th").removeClass("sorting");
        var height = $(".dataTables_scrollBody").height();
        $(".dataTables_scrollBody").height(height - heightHeader);

        $('#list_combination tbody').on('click', 'tr', function () {
            $("#fingerA").val($(this).data("fingerA"));
            $("#fingerB").val($(this).data("fingerB"));
            $("#fingerC").val($(this).data("fingerC"));
            $("#fingerD").val($(this).data("fingerD"));
            $("#fingerE").val($(this).data("fingerE"));
            $("#fingerF").val($(this).data("fingerF"));
            pageController.updateStripe();
        });


    }
    this.validateNewStripe = function () {
        var errors = [];

        var bValid = isValidAlphaNumeric($("#name").val());
        if (!bValid) {
            $("#name").css("border", "1px solid red");
            errors.push("El nombre del rayado es invalido.");
        }

        bValid = isValidInternalCode($("#code").val());
        if (!bValid) {
            $("#code").css("border", "1px solid red");
            errors.push("El código del rayado es invalido.");
        }

        return errors;
    };

    this._initializeEditablePage = function () {
        $("#code").prop("disabled", true);
        $("#name").prop("disabled", true);
        $("#colors").prop("disabled", true);
        $("#listingFormula").prop("disabled", true);
        $("#lineRows").prop("disabled", true);
        $("#addStripeLineButton").prop("disabled", true);

        if ($(".deleteItem").length > 0) {
            $(".deleteItem").click(function () {
                pageController.removeStripeCombinationButtonAction(this);
            });
        }
    };


}

NewStripedPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
NewStripedPageController.prototype.constructor = NewStripedPageController;

NewStripedPageController.prototype.initialize = function () {
    this.initializePage();
    this._initializeAddStripeFormPage();
    if ($(".stripe-id").val() != "" && $(".stripe-id").val() > 0) {
        this._initializeEditablePage();
    }

};
NewStripedPageController.prototype.zoomOut = function () {
    var padding = $(".list-group-item").css("padding-top");
    padding = padding.replace("px", "");
    if (parseInt(padding) == 2) {
        $("#btn-zoom-out").addClass("disabled");
    } else {
        $(".list-group-item").css("padding-top", (padding - 1) + "px");
        $(".list-group-item").css("padding-bottom", (padding - 1) + "px");
        $("#btn-zoom-in").removeClass("disabled");
    }
};
NewStripedPageController.prototype.zoomIn = function () {
    var padding = $(".list-group-item").css("padding-top");
    padding = padding.replace("px", "");
    if (parseInt(padding) == 15) {
        $("#btn-zoom-in").addClass("disabled");
    } else {
        padding = parseInt(padding) + 1;
        $(".list-group-item").css("padding-top", padding + "px");
        $(".list-group-item").css("padding-bottom", padding + "px");
        $("#btn-zoom-out").removeClass("disabled");
    }
};

NewStripedPageController.prototype.addStripeCombinationButtonAction = function () {
    $("#combination-name").css("border", "1px solid #ccc");
    if (!isValidAlphaNumeric($("#combination-name").val()) || $("#combination-name").val() == "") {
        $("#combination-name").css("border", "1px solid red");
        pageController.showErrorMessage("Nombre de combinación invalido");
        return;
    }
    var number = 0;
    if ($(".dataTables_empty").length == 0) {
        number = $("#list_combination tbody tr").length;
    }

    var fingerA = "<div style='height:20px;background-color:#" + $("#fingerA").val() + ";'></div>";
    var fingerB = "<div style='height:20px;background-color:#" + $("#fingerB").val() + ";'></div>";
    var fingerC = "<div style='height:20px;background-color:#" + $("#fingerC").val() + ";'></div>";
    var fingerD = "<div style='height:20px;background-color:#" + $("#fingerD").val() + ";'></div>";
    var fingerE = "<div style='height:20px;background-color:#" + $("#fingerE").val() + ";'></div>";
    var fingerF = "<div style='height:20px;background-color:#" + $("#fingerF").val() + ";'></div>";
    var action = "<span class='deleteItem' style='padding:0 12px; color: #d9534f;margin:0;'>" +
        " <i class='fa fa-trash-o' ></i></span>" +
        " <input type='hidden' value='" + number + "'/>";

    var row = [$("#combination-name").val(), fingerA, fingerB, fingerC, fingerD, fingerE, fingerF, action];
    var tableRow = this.DataTable.row.add(row).draw().node();

    $(tableRow).data("finger-a", $("#fingerA").val());
    $(tableRow).data("finger-b", $("#fingerB").val());
    $(tableRow).data("finger-c", $("#fingerC").val());
    $(tableRow).data("finger-d", $("#fingerD").val());
    $(tableRow).data("finger-e", $("#fingerE").val());
    $(tableRow).data("finger-f", $("#fingerF").val());


    $('<input>').attr({
        type: 'hidden',
        id: 'combinations' + number + '.colorFingerA',
        name: 'combinations[' + number + '].colorFingerA',
        class: 'combination_' + number,
        value: $('#fingerA').val()
    }).appendTo($('#addStripeForm'));
    $('<input>').attr({
        type: 'hidden',
        id: 'combinations' + number + '.colorFingerB',
        name: 'combinations[' + number + '].colorFingerB',
        class: 'combination_' + number,
        value: $('#fingerB').val()
    }).appendTo($('#addStripeForm'));
    $('<input>').attr({
        type: 'hidden',
        id: 'combinations' + number + '.colorFingerC',
        name: 'combinations[' + number + '].colorFingerC',
        class: 'combination_' + number,
        value: $('#fingerC').val()
    }).appendTo($('#addStripeForm'));
    $('<input>').attr({
        type: 'hidden',
        id: 'combinations' + number + '.colorFingerD',
        name: 'combinations[' + number + '].colorFingerD',
        class: 'combination_' + number,
        value: $('#fingerD').val()
    }).appendTo($('#addStripeForm'));
    $('<input>').attr({
        type: 'hidden',
        id: 'combinations' + number + '.colorFingerE',
        name: 'combinations[' + number + '].colorFingerE',
        class: 'combination_' + number,
        value: $('#fingerE').val()
    }).appendTo($('#addStripeForm'));
    $('<input>').attr({
        type: 'hidden',
        id: 'combinations' + number + '.colorFingerF',
        name: 'combinations[' + number + '].colorFingerF',
        class: 'combination_' + number,
        value: $('#fingerF').val()
    }).appendTo($('#addStripeForm'));
    $('<input>').attr({
        type: 'hidden',
        id: 'combinations' + number + '.name',
        name: 'combinations[' + number + '].name',
        class: 'combination_' + number,
        value: $('#combination-name').val()
    }).appendTo($('#addStripeForm'));
    $('<input>').attr({
        type: 'hidden',
        id: 'combinations' + number + '.id',
        name: 'combinations[' + number + '].id',
        class: 'combination_' + number,
        value: 0
    }).appendTo($('#addStripeForm'));

    $('#combination-name').val("");


    $("#fingerA").val("FFFFFF");
    $("#fingerB").val("FFFFFF");
    $("#fingerC").val("FFFFFF");
    $("#fingerD").val("FFFFFF");
    $("#fingerE").val("FFFFFF");
    $("#fingerF").val("FFFFFF");
    $("#fingerA").blur();
    $("#fingerB").blur();
    $("#fingerC").blur();
    $("#fingerD").blur();
    $("#fingerE").blur();
    $("#fingerF").blur();

    if ($(".deleteItem").length > 0) {
        $(".deleteItem").click(function () {
            pageController.removeStripeCombinationButtonAction(this);
        });
    }


};

NewStripedPageController.prototype.removeStripeCombinationButtonAction = function (element) {
    var erase = $(element).next().val();

    $(element).parents("tr").addClass("active");
    pageController.DataTable.row('.active').remove().draw();

    $(".combination_" + erase).remove();
};
NewStripedPageController.prototype.addStripeLineButtonAction = function () {
    var formula = $("#listingFormula").val();
    formula = formula + $("#lineRows").val() + $("#lineFinger").val();
    $("#listingFormula").val(formula);
    this.updateStripe();
    $("#lineRows").val("");
};
NewStripedPageController.prototype.updateStripe = function () {
    var paddingtop = parseInt($(".list-group-item").css("padding-top").replace("px", "").trim());
    var listGroup = $(".list-group");

    $(".list-group-item").remove();

    var addRow = "";
    var pattern = /([0-9]+[a-fA-F])/g;
    var tokens = $("#listingFormula").val().match(pattern);
    var i = 0, j = 0;
    var color = "", finger, rows = 0;
    if (tokens instanceof  Array) {
        while (i < tokens.length) {
            finger = tokens[i].charAt(tokens[i].length - 1);
            finger = finger.toUpperCase();
            rows = parseInt(tokens[i].substring(0, tokens[i].length - 1));
            addRow = '<a href="#" class="list-group-item colored" ';
            addRow = addRow + ' style="background-color:#' + $("#finger" + finger).val() + ';"></a>';
            for (j = 0; j < rows; j++) {
                listGroup.append(addRow);
            }
            i++;
        }

    }

    addRow = '<a href="#" class="list-group-item uncolored"></a>';
    addRow = addRow + '<a href="#" class="list-group-item uncolored"></a>';
    addRow = addRow + '<a href="#" class="list-group-item uncolored"></a>';
    addRow = addRow + '<a href="#" class="list-group-item uncolored"></a>';
    addRow = addRow + '<a href="#" class="list-group-item uncolored"></a>';
    listGroup.append(addRow);
    $(".list-group-item").css("padding-top", paddingtop + "px");
    $("#btn-zoom-in").removeClass('disabled');
    $("#btn-zoom-out").removeClass('disabled');
};

NewStripedPageController.prototype.addStripeButtonAction = function () {
    var form = $('#addStripeForm');
    form.find("input").css("border", "1px solid #ccc");
    $(".pick-a-color").prop("disabled", true);
    $("#code").prop("disabled", false);
    $("#name").prop("disabled", false);
    $("#colors").prop("disabled", false);
    $("#listingFormula").prop("disabled", false);
    $("#lineRows").prop("disabled", false);


    var errors = this.validateNewStripe();
    if (errors.length == 0) {
        this.doPost(form.attr('action'), form.serialize(), function () {
            location.href = "list-stripes.html";
        });

    } else {
        this.showErrorMessage(errors);
    }


};

var pageController = new NewStripedPageController();