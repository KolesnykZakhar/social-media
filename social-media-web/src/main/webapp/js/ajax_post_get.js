function getUser(url, idFriend) {
    $.get(url, {
        idFriend: idFriend
    }, function (responseText) {
        $('#mainDiv').html(responseText);
    });
    $('html, body').animate({
        scrollTop: $("#mainDiv").offset().top
    }, 1000);
}
function postMainDiv(url, currentARef, pageNumber) {
    $.post(url, {
            pageNumber: pageNumber
        },
        function (responseText) {
            $('#mainDiv').html(responseText);
        }
    );
    $('html, body').animate({
        scrollTop: $("#mainDiv").offset().top
    }, 1000);

    $('#sb_menu').children().each(function () {
        if (this.classList.contains("active")) {
            $(this).toggleClass("active");
        }
    });
    $(currentARef).parent().toggleClass("active");
}