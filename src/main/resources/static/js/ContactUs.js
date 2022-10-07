function sendContact() {
    var response = grecaptcha.getResponse();
    if (response){
        $.ajax({
            url: "http://localhost:8080/contacts?g-recaptcha-response="+response,
            type: "POST",
            contentType: "application/json;",
            data: JSON.stringify({
                fullName: $('#fullname').val(),
                email: $('#email').val(),
                phone: $('#phone').val(),
                message: $('#message').val()
            }),
            dataType: 'json',
            success: function(data) {
                alert("Thanks your question!");
            },
            error: function (jqXHR, exception) {
                console.log(jqXHR, exception);
            },
        })
        return true;
    } else{
        alert("Please prove that you're not a robot!")
    }

}