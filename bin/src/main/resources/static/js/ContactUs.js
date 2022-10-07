// jQuery(function($){
//
//     $('#btn_submit').click(function (event) {
//         sendContact()
//     });
// });
function sendContact() {
    console.log('hello');
    $.ajax({
        url: "http://localhost:8080/contacts",
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
            console.log(data);
            // $('#messFullname').html("");
            // $('#messEmail').html("");
            // $('#messPhone').html("");
            // $('#mess').html("");
            // if (data.result==-1) {
            //     $('#messFullname').html(data.error.fullname);
            //     $('#messEmail').html(data.error.email);
            //     $('#messPhone').html(data.error.phone);
            //     $('#mess').html(data.error.mess);
            // }
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
        },
    })
}