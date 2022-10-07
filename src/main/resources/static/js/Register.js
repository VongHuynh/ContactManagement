
function sendRegistration() {
    console.log('hello');
    $.ajax({
        url: "http://localhost:8080/account",
        type: "POST",
        contentType: "application/json;",
        data: JSON.stringify({
            userName:$('#usernameR').val(),
            fullName: $('#fullnameR').val(),
            email: $('#emailR').val(),
            password: $('#passwordR').val()
        }),
        dataType: 'json',
        success: function(data) {
            alert('Registration successfully');
            console.log(data);
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
        },
    })
}