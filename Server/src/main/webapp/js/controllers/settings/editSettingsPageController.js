function EditSettingsPageController(){
    PageController.call(this);

    this.updateSettings= function(){
        var list = [];
        $(".setting-element").each(function(index,element){
            var setting = {id:$(element).data("setting-id"),value:$(element).val(), name:$(element).data("setting-name"),description:'',label:'',category:''};
            list.push(setting);
        });

        list = JSON.stringify(list);

        $.ajax({
            headers: {
                'Accept' : 'application/json',
                'Content-Type' : 'application/json'
            },
            type: "POST",
            url: $("form").attr('action'),
            dataType: "json",
            data: list,
            contentType: 'text/html',
            success: function(html) {
                location.href='home.html';
            }
        });


    };
}
EditSettingsPageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
EditSettingsPageController.prototype.constructor = EditSettingsPageController;

EditSettingsPageController.prototype.initialize = function(){
    this.initializePage();
    $("#btn-update-settings").click(function(){
        pageController.updateSettings();
    });
    $("#collapseOne0").addClass("in");
};


var pageController = new EditSettingsPageController();

