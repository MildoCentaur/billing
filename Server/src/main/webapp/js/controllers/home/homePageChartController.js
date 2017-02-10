function HomePageController() {
    PageController.call(this);

    this._initializeWebElements = function () {
        $("#orders-table").click(function () {
            $("#orders_chart_div").css("display", "block");
            $("#orders_table_div").css("display", "block");
        });
        $("#orders-graphics").click(function () {
            $("#orders_chart_div").css("display", "block");
            $("#orders_table_div").css("display", "block");
        });

        $("#new-event-button").click(function () {
            pageController.addNotificationButton();
        });
    };
    this._initializeGraph = function () {

        // Set a callback to run when the Google Visualization API is loaded.
        google.setOnLoadCallback(this._drawChart);
    };

    // Callback that creates and populates a data table,
    // instantiates the pie chart, passes in the data and
    // draws it.
    this._drawChart = function () {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Cliente');
        //data.addColumn('number', 'Bultos Pedidos');
        data.addColumn('number', 'En cola');
        data.addColumn('number', 'En Producción');
        data.addColumn('number', 'Preparados');
        data.addColumn('number', 'Entregados');
        data.addRows([
            ['Cliente 1', 2, 0, 7, 2],
            ['Cliente 2', 1, 1, 0, 18],
            ['Cliente 3', 6, 0, 8, 0],
            ['Cliente 4', 4, 4, 0, 0],
            ['Cliente 5', 3, 5, 5, 9],
            ['Cliente 6', 2, 0, 5, 2],
            ['Cliente 7', 1, 1, 0, 18],
        ]);

        // Set chart options
        var options = {'title': 'Resumen de estado de los pedidos. \n Sobre el total de bultos solicitado.',
            'subtitle': 'Sobre el total de bultos solicitado.',
            'width': "80%",
            'height': 400,
            'bar': {'groupWidth': "90%"},
            'legend': { 'position': 'pull-left'},
            'isStacked': true,
        };

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.BarChart(document.getElementById('orders_chart_div'));
        chart.draw(data, options);

        /************************************************************************************************************/

        var data = google.visualization.arrayToDataTable([
            ['Colores', 'Rollos pedidos'],
            ['Azul Marino', 12],
            ['Blanco', 9.1],
            ['Negro', 12.2],
            ['Celeste', 22.9],
            ['Cemento Oscuro', 0.9],
            ['Cemento Claro', 36.6],
            ['Petroleo', 9.1],
            ['Azul Francia', 30.5],
            ['Verde', 6.1],
            ['Gabardina', 2.7],
            ['Rojo', 0.9],
            ['Fuxia', 2.7],
            ['Amarillo', 27.1],
            ['Rosa', 3.4],
            ['Violeta', 5.5],
            ['VerdeAgue', 21.0],
            ['Verde Menta', 7.9],
            ['Turqueza', 1.2],
            ['Gris', 4.6],
            ['Naranja', 1.5],
            ['Marron', 7.9],
            ['Salmon', 2.0],
            ['Chocolate', 45.7],
            ['Tiza', 12.2],
            ['Supersaurus (super lizard)', 30.5],
            ['Tyrannosaurus (tyrant lizard)', 15.2],
            ['Ultrasaurus (ultra lizard)', 30.5],
            ['Velociraptor (swift robber)', 1.8]
        ]);

        var options = {
            title: 'Distribucion de colores pedidos',
            legend: { position: 'pull-left' },
            width: "80%",
            height: 400,
            bar: {'groupWidth': "100%"}
        };

        var chart = new google.visualization.Histogram(document.getElementById('products_chart_div'));
        chart.draw(data, options);


    };
}
HomePageController.prototype = Object.create(PageController.prototype); // See note below

// Set the "constructor" property to refer to Student
HomePageController.prototype.constructor = HomePageController;

HomePageController.prototype.initialize = function () {
    this.initializePage();
    this._initializeGraph();
    this._initializeWebElements();
};

HomePageController.prototype.addNotificationButton = function () {
    var form = $("#add-notification-form");

    var result = true;
    var bValid = isValidAlphaNumeric($("#notification-title").val());
    result = bValid && result;
    if (!bValid) {
        $("#notification-title").css("border", "1px solid red");
    }
    bValid = isValidAlphaNumeric($("#notification-place").val());
    result = bValid && result;
    if (!bValid) {
        $("#notification-place").css("border", "1px solid red");
    }
    bValid = $("#notification-date").val() != "";
    result = bValid && result;
    if (!bValid) {
        $("#notification-date").css("border", "1px solid red");
    }
    if (result) {
        pageController.doPost("add/event.json", form.serialize(), function () {
            $("#add-event-modal").modal("hide");
        });
    }

};


var pageController = new HomePageController();

var chartController = new HomePageChartController();

