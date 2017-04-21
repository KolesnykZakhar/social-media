function getUser(url) {
    $.get(url, function (responseText) {
        $('#mainDiv').html(responseText);
    });
    $('html, body').animate({
        scrollTop: $("#mainDiv").offset().top
    }, 1000);
}
function postMainDiv(url, currentARef) {
    $.post(url, function (responseText) {
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

function updateUser(url, currentARef) {
    $.post(url, {
        birthDate: $('#birthDate').val(),
        gender: $('input[name=gender]:checked').val(),
        firstName: $('#firstName').val(),
        lastName: $('#lastName').val(),
        phone: $('#phone').val()
        }, function (responseText) {
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