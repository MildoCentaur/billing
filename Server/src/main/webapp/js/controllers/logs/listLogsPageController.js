function ListLogsPageController(){
    PageController.call(this);
}
ListLogsPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
ListLogsPageController.prototype.constructor = ListLogsPageController;

ListLogsPageController.prototype.initialize = function(){
  this.initializePage();
};


var pageController = new ListLogsPageController();

