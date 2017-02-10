$(function() {

    $('#side-menu').metisMenu();

});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function() {
    $(window).bind("load resize", function() {
        topOffset = 50;
        width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse')
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse')
        }

        height = (this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height;
        height = height - topOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    })
})

jQuery.extend( jQuery.fn.dataTableExt.oSort, {
    "date-euro-pre": function ( a ) {
        if ($.trim(a) != '') {
            var frDatea = $.trim(a).split(' ');
            var frDatea2 = frDatea[0].split('/');
            var x = (frDatea2[2] + frDatea2[1] + frDatea2[0]) * 1;
            if(frDatea.length>1){
                var frTimea = frDatea[1].split(':');
                x = (frDatea2[2] + frDatea2[1] + frDatea2[0] + frTimea[0] + frTimea[1] + frTimea[2]) * 1;
            }
        } else {
            var x = 10000000000000; // = l'an 1000 ...
        }

        return x;
    },

    "date-euro-asc": function ( a, b ) {
        return a - b;
    },

    "date-euro-des": function ( a, b ) {
        return b - a;
    },
    "numeric-pre": function ( a ) {
        var x=$.trim(a);
        if (x != '') {
            x=x.replace(".","");
            x=x.replace(",",".");
        }
        return x;
    },

    "numeric-asc": function ( a, b ) {
        return a - b;
    },

    "numeric-des": function ( a, b ) {
        return b - a;
    },
    "string-pre": function ( a ) {
        return a.toLowerCase();
    },

    "string-asc": function ( a, b ) {
        return (a > b ) ? 1: ((a==b)? 0:-1);
    },

    "string-des": function ( a, b ) {
        return (b > a ) ? 1: ((a==b)? 0:-1);
    }
} );

$.widget( "custom.catcomplete", $.ui.autocomplete, {
    _create: function() {
        this._super();
        this.widget().menu( "option", "items", "> :not(.ui-autocomplete-category)" );
    },
    _renderMenu: function( ul, items ) {
        var that = this,
            currentCategory = "";
        $.each( items, function( index, item ) {
            var li;
            if ( item.category != currentCategory ) {
                ul.append( "<li class='ui-autocomplete-category'>" + item.category + "</li>" );
                currentCategory = item.category;
            }
            li = that._renderItemData( ul, item );
            if ( item.category ) {
                li.attr( "aria-label", item.category + " : " + item.label + " : " + item.id );
            }
        });
    }
});