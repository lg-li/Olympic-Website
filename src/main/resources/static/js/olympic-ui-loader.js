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

loadTo('/pages/header.html','header');
loadTo('/pages/footer.html','footer');
