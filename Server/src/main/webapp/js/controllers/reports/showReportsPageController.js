function ShowReportsPageController() {
    PageController.call(this);
}
ShowReportsPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
ShowReportsPageController.prototype.constructor = ShowReportsPageController;

ShowReportsPageController.prototype.initialize = function () {
    this.initializePage();

};


var pageController = new ShowReportsPageController();

