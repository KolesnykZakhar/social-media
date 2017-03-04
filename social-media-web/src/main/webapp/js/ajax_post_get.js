function get(url) {
    window.location.href = "#result";
    $.get(url, {}, function (responseText) {
        $('#result').html(responseText);
    });
}
function postAuthorization(url, loginOrEmail, password) {
    window.location.href = "#result'";
    $.post(url, {
            loginOrEmail: loginOrEmail,
            password: password
        },
        function (responseText) {
            $('#result').html(responseText);
        }
    );
}