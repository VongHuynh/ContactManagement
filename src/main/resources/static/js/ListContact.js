
getListContacts();
function getListContacts() {
    console.log('hello');
    $.ajax({
        url: "http://localhost:8080/contacts",
        type: "GET",
        contentType: "application/json;",
        success: function(data) {
            let response = "";
            data.forEach(el => {
                response += `<tr>
                <td>${el.fullName}</td>
                <td>${el.phone}</td>
                <td>${el.email}</td>
                <td>${el.message}</td>
            </tr>`
            })
            document.getElementById("tbody").innerHTML = response;
        },
        error: function (jqXHR, exception) {
            console.log(jqXHR, exception);
        },
    })
}