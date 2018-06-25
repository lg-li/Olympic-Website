// Author Li Lingen (Bruce) 20165254
function loadTo(url, id) {
    $.ajax({
        url: url, //URL Requested
        type: "GET", // Get only
        success: function (data) {
            $("#" + id).html(data);
        }
    });
}

loadTo('./header.html','header');
loadTo('./footer.html','footer');
