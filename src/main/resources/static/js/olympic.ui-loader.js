/**@Author Li Lingen (Bruce) 20165254
 * Olympic basic universal JS lib.
 * Async load template HTML file to Container
 * Fade transition text effect
 * Basic template compile
 */
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
loadTo('/footer.html','footer');

// Transition animation of text
function fadeToText(id, text){
    var target = $('#'+id);
    target.fadeOut(500);
    setTimeout(function () {
        target.text(text);
        target.fadeIn(500);
    },480)
}

// Compile templates to code in HTML5
function compileTemplate(template, data) {
    for(var name in data){
        // RegExp multiple match
        template = template.replace(RegExp('__'+ name +'__','g'), data[name]);
    }
    return template;
}
