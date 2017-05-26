function postMainDiv(url, currentARef) {
    $.post(url, function (responseText) {
            $('#mainDiv').html(responseText);
        }
    );

    updateMarks();

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

function updateUser(url) {
    $.post(url, {
            birthDate: $('#birthDate').val(),
            gender: $('input[name=gender]:checked').val(),
            visibility: $('input[name=visibility]:checked').val(),
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
}

function searchUser(url) {
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
}

function addRemoveAcceptFriendship(url) {
    $.post(url, function (responseText) {
            $('#mainDiv').html(responseText);
        }
    );

    updateMarks();

    $('html, body').animate({
        scrollTop: $("#mainDiv").offset().top
    }, 1000);

    $('#sb_menu').children().each(function () {
        if (this.classList.contains("active")) {
            $(this).toggleClass("active");
        }
    });
}

function sendMessage(url) {
    $.post(url, {
            textMessage: $('#message-to-send').val(),
            idInterlocutor: $('#idInterlocutor').val()
        }, function (responseText) {
            $('#mainDiv').html(responseText);
        }
    );

    updateMarks();

    $('html, body').animate({
        scrollTop: $("#mainDiv").offset().top
    }, 1000);
}

function bookmarksAction(idPost, checker) {
    var url;
    if (checker.checked) {
        url = '/user/add_bookmark/' + idPost;
    } else {
        url = '/user/remove_bookmark/' + idPost;
    }
    $.post(url);
}

function passwordChecker(url, obj) {
    var pass = obj.value;
    var strength = 0;
    var arr = [/.{6,16}/, /[a-z]+/, /[0-9]+/, /[A-Z]+/];
    jQuery.map(arr, function (regexp) {
        if (pass.match(regexp)) {
            strength++;
        }
    });
    var color = 'grey';
    switch (strength) {
        case 1: {
            color='grey';
            break;
        }
        case 2: {
            color='red';
            break;
        }
        case 3: {
            color='green';
            break;
        }
        case 4: {
            color='blue';
            break;
        }
    }
    $('#passChecker').attr('style', 'color: ' + color);
    $.post(url, {
            strength: strength
        }, function (responseText) {
            $('#passChecker').text(responseText);
        }
    );

}

/* private */
function updateMarks() {
    updateInvitationMark();
    updateMessageMark();
}

/* private */
function updateMessageMark() {
    $.post('/user/update_message_mark', function (responseText) {
            $('#amountUnreadMessages').html(responseText);
        }
    );
}

/* private */
function updateInvitationMark() {
    $.post('/user/update_invitation_mark', function (responseText) {
            $('#amountOfInvitations').html(responseText);
        }
    );
}
