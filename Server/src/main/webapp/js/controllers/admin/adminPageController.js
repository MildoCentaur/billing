function AdminPageController() {
    this.initializePageForgotPage = function () {
        $('#email').bind('keyup',
            function (e) {
                if (e.keyCode == 13 && isValidEmailAddress($('#email').val())) {
                    $('#form').submit();
                }
            });
        $('#forgot-btn').click(function () {
            if (isValidEmailAddress($('#email').val())) {
                $('#form').submit();
            }
        });
    };
}

// Set the "constructor" property to refer to Student
AdminPageController.prototype.constructor = AdminPageController;

AdminPageController.prototype.initialize = function () {

};

AdminPageController.prototype.initializePageForgotPage = function () {
    pageController.initializePageForgotPage();
};






