/**
 * Created by Mildo on 9/25/14.
 */

function ListCashierPageController(){
    PageController.call(this);

    this._initializeListCashierButtons = function () {
        $("#btn-add-input").click(function () {
            location.href = 'new-payment-client.html';
        });
        $("#btn-add-output").click(function () {
            location.href = 'new-payment-supplier.html';
        });
    };

}
ListCashierPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
ListCashierPageController.prototype.constructor = ListCashierPageController;

ListCashierPageController.prototype.initialize = function(){
    this.initializePage();
    this._initializeListCashierButtons();

};


var pageController = new ListCashierPageController();
