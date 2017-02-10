function PageController() {
    this.sessionTime = 1;
    this.sessionTimeWarning = 20;
    var sessionIntervalTimer;

    PageController.prototype.doPost = function (url, map, fnSuccess) {
        if (url.match(/.html|.json/) == null) {
            url = url + ".json";
        }
        console.log("Doing the post with: ");
        console.log("url=" + url + " data=" + map + " fnSuccess=" + fnSuccess);

        $.post(url, map, function (data) {
            console.log("posting responded:" + data);
            pageController.sessionTime = 0;
            if (data.hasErrors == 0) {
                if (typeof(fnSuccess) == "function") {
                    fnSuccess(data.ajaxResponse);
                } else {
                    pageController.showSuccessMessage();
                }
            } else {
                pageController.showErrorMessage(data.errorListDetailList);
            }
        });
    };

    PageController.prototype.doGet = function (url, map, fnSuccess) {
        console.log("Doing the post with: ");
        console.log("url=" + url + " data=" + map + " fnSuccess=" + fnSuccess);
        $.get(url, map, function (data) {
            pageController.sessionTime = 0;
            console.log("posting responded:" + data);
            if (data.hasErrors == 0) {
                if (typeof(fnSuccess) == "function") {
                    fnSuccess(data.ajaxResponse);
                } else {
                    pageController.showSuccessMessage();
                }
            } else {
                pageController.showErrorMessage(data.errorListDetailList);
            }
        });
    };
    PageController.prototype.doNothing = function () {
    };


    PageController.prototype.menuSeletion = function () {
        $("#side-menu li a").each(function (index, element) {
            var array = document.location.href.split("/");

            var last = array.length;
            array[last - 1] = array[last - 1].replace("edit", "new");
            array[last - 1] = array[last - 1].replace("show-order", "list-orders");
            array[last - 1] = array[last - 1].split('?')[0];
            if ($(element).attr("href").match(array[last - 1])) {
                $(element).parents("ul").addClass("in");
                $(element).parents("li").addClass("active");
                $(element).addClass("active");
            }
        });
    };

    PageController.prototype.showErrorMessage = function (errors) {
        if (!(errors instanceof Array)) {
            errors = [errors];
        }
        this._showMultipleErrorMessage(errors);
    };
    PageController.prototype.confirmMessage = function (question, fnConfirmation, fnCancellation) {
        if (question == "" || typeof question !== 'string') {
            this._showSingleErrorMessage("Debería solicitar una confirmación pero me olvide de que... :((");
            return;
        }

        if (confirm(question)) {
            if (typeof fnConfirmation === 'function') {
                fnConfirmation();
            }
            return true;
        } else {
            if (typeof fnCancellation === 'function') {
                fnCancellation();
            }
            return false;
        }
    };
    PageController.prototype.showSuccessMessage = function (success) {
        if (typeof success != 'undefined') {
            $("#success-label").text(success);
        }
        $("#success-notice").show();
        $("#success-notice").focus();
    };
    PageController.prototype.checkSessionClose = function () {
        sessionIntervalTimer = setInterval(function () {
            pageController.updateSessionTime(pageController.sessionTime)
        }, 60000);
    };
    PageController.prototype.keepAlive = function () {
        this.doGet('time.json', [], function () {
            pageController.sessionTime = 1
        });

    };
    PageController.prototype.initializePage = function () {
        this._initializeSearchNavBar();
        this.defaultFormInitialization();

        $('.alert').on('closed.bs.alert', function () {
            $(this).hide();
        });
        //initialize all pick a color elements
        $(".pick-a-color").pickAColor({
            showSpectrum: true,
            showSavedColors: true,
            saveColorsPerElement: true,
            fadeMenuToggle: true,
            showAdvanced: true,
            showBasicColors: true,
            showHexInput: true,
            allowBlank: true,
            inlineDropdown: true
        });
        $('.form_date').datetimepicker({
            language: 'es',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0
        });
        $('.form_datetime').datetimepicker({
            language: 'es',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 0,
            maxView: 2,
            forceParse: 0,
            showMeridian: 0

        });
        $("button:reset").click(function () {
            var form = $(this).parents("form");
            form.find("input:text").val("");
            form.find(".pick-a-color").val("fff");
        });
        $(".modal").each(function (index, modal) {
            $(modal).on('hidden.bs.modal', function () {
                $(modal).find("input:text").val("");
                $(modal).find(".pick-a-color").val("fff");
            });
        });


        $(".admin-lock-text").prop("disabled", true);
        $(".admin-lock-text").blur(function () {
            $(".admin-lock-text").prop("disabled", true);
        });
        $(".admin-lock").click(function () {
            $(this).addClass("active");
            pageController.confirmMessage("Está a punto de modificar un campo reservado, esta operación no es aconcejada.", function () {
                $(".admin-lock.active").prev().prop("disabled", false);
            }, pageController.doNothing());
        });

    };
    PageController.prototype.defaultFormInitialization = function () {
        $(".currency").blur(function () {
            $(this).val(formatCurrency($(this).val()));
        });

        $(".currency").each(function (i, e) {
            if ($(e).val() != "") {
                $(e).val(formatCurrencyFromDouble($(e).val()));
            } else {
                $(e).text(formatCurrencyFromDouble($(e).text()));
            }
        });

        $(".formatCurrencyFromDouble").each(function (i, e) {
            if ($(e).val() != "") {
                $(e).val(formatCurrencyFromDouble($(e).val()));
            } else {
                $(e).text(formatCurrencyFromDouble($(e).text()));
            }
        });
        this.menuSeletion();
    };
    PageController.prototype.reloadTable = function (sServiceUrl) {

        this.doGet(sServiceUrl, {}, pageController.reloadTableCallBack);

    };
    PageController.prototype.reloadTableCallBack = function (data) {
        var row = [];

        pageController.DataTable.clear().draw();

        $.each(data, function (rowIndex, element) {

            row = pageController.reloadTableRowMapping(rowIndex + 1, element);
            pageController.DataTable.row.add(row);
        });
        pageController.DataTable.draw();
        if (jQuery.isFunction(pageController.reloadedTableCallback)) {
            pageController.reloadedTableCallback();
        }


    };
    PageController.prototype.hideErrorMessage = function (data) {
        $("#multiple-error-notice").hide();
    };
    this.updateSessionTime = function (sessionTime) {
        this.sessionTime++;
        if (pageController.sessionTime == pageController.sessionTimeWarning) {
//            if (confirm("Su sesion est� por expirar. �Desea mantenerla?")) {
//                this.keepAlive();
//            } else {
            this.sessionTime = 0;
            location.href = 'home.html';
//            }

        }
    };

    this._initializeSearchNavBar = function () {
        if ($("#search-autocomplete").length == 0) {
            return;
        }

        $("#search-autocomplete").catcomplete({
            delay: 10,
            source: function (request, response) {
                var name = request.term;
                return jQuery.get("search-entities.json?query=" + name, function (data) {
                    result = [];
                    pageController.sessionTime = 0;
                    $.each(data, function (i, e) {

                        if (i.toLowerCase() == "clientes") {
                            $.each(e, function (i2, e2) {
                                result.push({label: e2.name, category: i, id: e2.id});
                            });
                        }
                        if (i.toLowerCase() == "proveedores") {
                            $.each(e, function (i2, e2) {
                                result.push({label: e2.name, category: i, id: e2.id});
                            });
                        }
                        if (i.toLowerCase() == "tejidos") {
                            $.each(e, function (i2, e2) {
                                result.push({label: e2.code + " " + e2.shortname, category: i, id: e2.id});
                            });
                        }
                        if (i.toLowerCase() == "colores") {
                            $.each(e, function (i2, e2) {
                                result.push({label: e2.name, category: i, id: e2.id});
                            });
                        }
                        if (i.toLowerCase() == "rayados") {
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
    this._showMultipleErrorMessage = function (errors) {
        $(".error-item").remove();

        $.each(errors, function (i, e) {
            $("#error-list").append("<li class='error-item'>" + e + "</li>");
        });

        $("#multiple-error-notice").show();
        $("#multiple-error-notice").focus();
    };

}
