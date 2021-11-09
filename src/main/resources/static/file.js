getAllUsers();


let roleList = []; // глобальная переменная для хранения массива ролей


function getAllUsers() {
    $.getJSON("http://localhost:8080/api/users", function (data) { // по ссылки получаем юзеров и добавляем их в дата
        console.log('1) данные с бэка /allUsers: ', JSON.stringify(data)) // для проверки в консоли
        let rows = '';
        $.each(data, function (key, user) { // проходимся по юзерам (получаем юзар)
            rows += createRows(user); // из цикла полученного юзера добавляем в строку
        });
        $('#tableAllUsers').append(rows); //строку добавляем в таблицу

//         // получение ролей по url из json, добовляем в массив ролей
        $.ajax({
            url: '/admin/authorities',
            method: 'GET',
            dataType: 'json',
            success: function (roles) {
                roleList = roles;
            }
        });
    });
}

//метод создания строк для таблицы
function createRows(user) {

    let user_data = '<tr id=' + user.id + '>';
    user_data += '<td>' + user.id + '</td>';
    user_data += '<td>' + user.name + '</td>';
    user_data += '<td>' + user.age + '</td>';
    user_data += '<td>' + user.email + '</td>';
    user_data += '<td>';
    let roles = user.roles; // через getJSON получаем массив ролей
    for (let role of roles) {
        user_data += role.name+ ' ';
    }
    user_data += '</td>' +
        '<td>' + '<input id="btnEdit" value="Edit" type="button"' +
        'class="btn-info btn edit-btn" data-toggle="modal" data-target="#edit" ' +
        'data-id="' + user.id + '">' + '</td>' +

        '<td>' + '<input id="btnDelete" value="Delete" type="button" class="btn btn-danger del-btn" ' +
        'data-toggle="modal" data-target="#delete" data-id=" ' + user.id + ' ">' + '</td>';
    user_data += '</tr>';

    return user_data;
}
