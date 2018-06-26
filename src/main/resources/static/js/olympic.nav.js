//Olympic olympic.nav.js
var ww = document.body.clientWidth; // display portal width

// Hide the long navigation bar when width is lower than 768
var adjustMenu = function () {
    // toggle at 768px
    if (ww < 768) {
        var toggleMenu = $(".toggleMenu");
        toggleMenu.css("display", "inline-block");
        if (!toggleMenu.hasClass("active")) {
            $("#navigator").hide();
        } else {
            $("#navigator").show();
        }
        $("#navigator li").unbind('mouseenter mouseleave');
        $("#navigator li a.parent").unbind('click').bind('click', function (e) {
            // must be attached to anchor element to prevent bubbling
            e.preventDefault();
            $(this).parent("li").toggleClass("hover");
        });
    }else if (ww >= 768) {
        $(".toggleMenu").css("display", "none");
        $("#navigator").show();
        var nav_li = $("#navigator li");
        nav_li.removeClass("hover");
        $("#navigator li a").unbind('click');
        nav_li.unbind('mouseenter mouseleave').bind('mouseenter mouseleave', function () {
            // must be attached to li so that mouseleave is not triggered when hover over submenu
            $(this).toggleClass('hover');
        });
    }
}

// ready event
$(document).ready(function () {
    $("#navigator li a").each(function () {
        if ($(this).next().length > 0) {
            $(this).addClass("parent");
        }
        ;
    })

    $(".toggleMenu").click(function (e) {
        e.preventDefault();
        $(this).toggleClass("active");
        $("#navigator").toggle();
    });
    adjustMenu();
});

$(window).bind('resize orientationchange', function () {
    ww = document.body.clientWidth;
    adjustMenu();
});

