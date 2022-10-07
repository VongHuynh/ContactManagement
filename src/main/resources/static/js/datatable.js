//
// $(document).ready(function () {
//     $("#tableSwapi").dataTable();
//     $.ajax({
//         url: 'http://localhost:8080/table/contacts',
//         type: 'get',
//         dataType: 'json',
//         success: function (result) {
//             console.log(result)
//             let daftar = result.data;
//             var html = '';
//             $.each(daftar, function (i, data) {
//                 html += `<tr>
//                                         <td> ` + data.idUser + `</td>
//                                         <td>` + data.userName + `</td>
//                                         <td>` + data.fullName + `</td>
//                                         <td>` + data.email + `</td>
//                                         <td> ` + data.password + ` </td>
//
//                                     </tr>`;
//                 //This is selector of my <tbody> in my table
//                 $("#list-list").html(html);
//             });
//         }
//     });
// })

$('table#tableSwapi').DataTable({
    ajax: 'http://localhost:8080/table/contacts',
    serverSide: true,
    columns: [
        {
            data: 'idUser'
        },
        {
            data: 'userName'
        },
        {
            data: 'fullName'
        },
        {
            data: 'email'

        },
        {
            data: 'password'
        }
    ]
});