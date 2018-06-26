// Author: Li Lingen (Bruce) 20165254
// Async load template HTML file to Container
function loadTo(url, id) {
    $.ajax({
        url: url, //URL Requested
        type: "GET", // Get only
        success: function (data) {
            $("#" + id).html(data);
        }
    });
}

// load header and footer of every pages
loadTo('/pages/header.html','header');
loadTo('/pages/footer.html','footer');

// Transition animation of text
function fadeToText(id, text){
    var target = $('#'+id);
    target.fadeOut(500);
    setTimeout(function () {
        target.text(text);
        target.fadeIn(500);
    },500)
}

// Compile templates to code in HTML5
function compileTemplate(template, data) {
    for(var name in data){
        // RegExp multiple match
        template = template.replace(RegExp('__'+ name +'__','g'), data[name]);
    }
    return template;
}
