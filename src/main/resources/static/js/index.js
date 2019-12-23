function bindEventHandler() {
    const getRoomNav = $("#roomTab").children('li').eq(0);
    getRoomNav.on('click', function () {
        $.get("./room/", function (rooms, status) {
            const table = $("#getRoomTable");
            const roomNum = rooms.length;
            if (roomNum !== 0) {
                table.empty();
                for (let i = 0; i < roomNum; i++) {
                    var tr = '<tr>' +
                        '<td>' + rooms[i].name + '</td>' +
                        '<td>' + rooms[i].note + '</td>' +
                        '<td>' + rooms[i].availabilityInfo + '</td>' +
                        '</tr>';
                    table.append(tr)
                }
            } else {
                table.empty();
                var tr = '<tr>' +
                    '<td>null</td>' +
                    '<td>null</td>' +
                    '<td>null</td>' +
                    '</tr>';
                table.append(tr)
            }
        })
    })
    getRoomNav.ready(function () {
        $.get("./room/", function (rooms, status) {
            const table = $("#getRoomTable");
            const roomNum = rooms.length;
            if (roomNum !== 0) {
                table.empty();
                for (let i = 0; i < roomNum; i++) {
                    var tr = '<tr>' +
                        '<td>' + rooms[i].name + '</td>' +
                        '<td>' + rooms[i].note + '</td>' +
                        '<td>' + rooms[i].availabilityInfo + '</td>' +
                        '</tr>';
                    table.append(tr)
                }
            } else {
                table.empty();
                var tr = '<tr>' +
                    '<td>null</td>' +
                    '<td>null</td>' +
                    '<td>null</td>' +
                    '</tr>';
                table.append(tr)
            }
        })
    })
    const addRoomForm = $('#addRoom').children('form');
    const addRoomSubmit = addRoomForm.children('button');
    addRoomSubmit.on('click', function () {
        let args = addRoomForm.serializeArray();
        let roomVo = {};
        for (let arg in args) {
            roomVo[args[arg].name] = args[arg].value;
        }
        if (roomVo.availabilityValue == undefined) {
            roomVo.availabilityValue = 0;
        } else {
            roomVo.availabilityValue = 1;
        }
        $.ajax({
            type: 'post',
            url: './room/',
            data: JSON.stringify(roomVo).toString(),
            contentType: 'application/json',
            dataType: 'json',
            success: function (data) {
                let addRoom = $('#addRoom');
                let successBar = '<div class="alert alert-success">\n' +
                    '               <a href="#" class="close" data-dismiss="alert">&times;</a>\n' +
                    '               <strong>成功！</strong>添加成功\n' +
                    '             </div>'
                addRoom.prepend(successBar);
            },
            error: function () {
                let addRoom = $('#addRoom');
                let failBar = '<div class="alert alert-danger">\n' +
                    '              <a href="#" class="close" data-dismiss="alert">&times;</a>\n' +
                    '              <strong>失败！</strong>发生了一些错误，请稍后再试\n' +
                    '          </div>'
                addRoom.prepend(failBar);
            }
        });
        return false;
    })
    const findRoomForm = $('#findRoom').children('form');
    const findRoomSubmit = findRoomForm.children('button');
    findRoomSubmit.on('click', function () {
        let args = findRoomForm.serializeArray();
        let roomVo = {};
        for (let arg in args) {
            roomVo[args[arg].name] = args[arg].value;
        }
        $.ajax({
            type: 'get',
            url: './room/' + encodeURI(roomVo.name),
            success: function (roomVo) {
                let findRoom = $('#findRoom');
                let successBar = '<div class="alert alert-success">\n' +
                    '               <a href="#" class="close" data-dismiss="alert">&times;</a>\n' +
                    '               <strong>成功！</strong>查询成功\n' +
                    '             </div>'
                findRoom.prepend(successBar);
                const table = $("#findRoomTable");
                if (roomVo != null) {
                    table.empty();
                    var tr = '<tr>' +
                        '<td>' + roomVo.name + '</td>' +
                        '<td>' + roomVo.note + '</td>' +
                        '<td>' + roomVo.availabilityInfo + '</td>' +
                        '</tr>';
                    table.append(tr)
                } else {
                    table.empty();
                    var tr = '<tr>' +
                        '<td>null</td>' +
                        '<td>null</td>' +
                        '<td>null</td>' +
                        '</tr>';
                    table.append(tr)
                }
            },
            error: function () {
                let findRoom = $('#findRoom');
                let failBar = '<div class="alert alert-danger">\n' +
                    '              <a href="#" class="close" data-dismiss="alert">&times;</a>\n' +
                    '              <strong>失败！</strong>发生了一些错误，请稍后再试\n' +
                    '          </div>'
                findRoom.prepend(failBar);
            }
        })
        return false;
    })
    const updateRoomForm = $('#updateRoom').children('form');
    const updateRoomSubmit = updateRoomForm.children('button');
    updateRoomSubmit.on('click', function () {
        let args = updateRoomForm.serializeArray();
        let roomVo = {};
        for (let arg in args) {
            roomVo[args[arg].name] = args[arg].value;
        }
        if (roomVo.availabilityValue == undefined) {
            roomVo.availabilityValue = 0;
        } else {
            roomVo.availabilityValue = 1;
        }
        $.ajax({
            type: 'put',
            url: './room/',
            data: JSON.stringify(roomVo).toString(),
            contentType: 'application/json',
            dataType: 'json',
            success: function (roomVo) {
                let updateRoom = $('#updateRoom');
                let successBar = '<div class="alert alert-success">\n' +
                    '               <a href="#" class="close" data-dismiss="alert">&times;</a>\n' +
                    '               <strong>成功！</strong>更改成功\n' +
                    '             </div>'
                updateRoom.prepend(successBar);
            },
            error: function () {
                let updateRoom = $('#updateRoom');
                let failBar = '<div class="alert alert-danger">\n' +
                    '              <a href="#" class="close" data-dismiss="alert">&times;</a>\n' +
                    '              <strong>失败！</strong>发生了一些错误，请稍后再试\n' +
                    '          </div>'
                updateRoom.prepend(failBar);
            }
        })
        return false;
    })
    const deleteRoomForm = $('#deleteRoom').children('form');
    const deleteRoomSubmit = deleteRoomForm.children('button');
    deleteRoomSubmit.on('click', function () {
        let args = deleteRoomForm.serializeArray();
        let roomVo = {};
        for (let arg in args) {
            roomVo[args[arg].name] = args[arg].value;
        }
        $.ajax({
            type: 'delete',
            url: './room/' + encodeURI(roomVo.name),
            success: function (roomVo) {
                let deleteRoom = $('#deleteRoom');
                let successBar = '<div class="alert alert-success">\n' +
                    '               <a href="#" class="close" data-dismiss="alert">&times;</a>\n' +
                    '               <strong>成功！</strong>删除成功\n' +
                    '             </div>'
                deleteRoom.prepend(successBar);
            },
            error: function () {
                let deleteRoom = $('#deleteRoom');
                let failBar = '<div class="alert alert-danger">\n' +
                    '              <a href="#" class="close" data-dismiss="alert">&times;</a>\n' +
                    '              <strong>失败！</strong>发生了一些错误，请稍后再试\n' +
                    '          </div>'
                deleteRoom.prepend(failBar);
            }
        });
        return false;
    })
}

$(document).ready(function () {
    bindEventHandler();
})