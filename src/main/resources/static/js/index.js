function bindEventHandler() {
    const navTab = $('#nav').children('li');
    navTab.eq(0).bind('click', function () {
        if (!navTab.eq(0).hasClass('active')) {
            navTab.eq(0).addClass('active');
            navTab.eq(1).removeClass('active');
            $('#orderService').hide();
            $('#roomService').show();
        }
    });
    navTab.eq(1).bind('click', function () {
        if (!navTab.eq(1).hasClass('active')) {
            navTab.eq(1).addClass('active');
            navTab.eq(0).removeClass('active');
            $('#roomService').hide();
            $('#orderService').show();
        }
    });

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
    });
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
    });
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
                    '             </div>';
                addRoom.prepend(successBar);
            },
            error: function () {
                let addRoom = $('#addRoom');
                let failBar = '<div class="alert alert-danger">\n' +
                    '              <a href="#" class="close" data-dismiss="alert">&times;</a>\n' +
                    '              <strong>失败！</strong>发生了一些错误，请稍后再试\n' +
                    '          </div>';
                addRoom.prepend(failBar);
            }
        });
        return false;
    });
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
                    '             </div>';
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
                    '          </div>';
                findRoom.prepend(failBar);
            }
        });
        return false;
    });
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
                    '             </div>';
                updateRoom.prepend(successBar);
            },
            error: function () {
                let updateRoom = $('#updateRoom');
                let failBar = '<div class="alert alert-danger">\n' +
                    '              <a href="#" class="close" data-dismiss="alert">&times;</a>\n' +
                    '              <strong>失败！</strong>发生了一些错误，请稍后再试\n' +
                    '          </div>';
                updateRoom.prepend(failBar);
            }
        });
        return false;
    });
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
                    '             </div>';
                deleteRoom.prepend(successBar);
            },
            error: function () {
                let deleteRoom = $('#deleteRoom');
                let failBar = '<div class="alert alert-danger">\n' +
                    '              <a href="#" class="close" data-dismiss="alert">&times;</a>\n' +
                    '              <strong>失败！</strong>发生了一些错误，请稍后再试\n' +
                    '          </div>';
                deleteRoom.prepend(failBar);
            }
        });
        return false;
    });

    const getOrderNav = $("#orderTab").children('li').eq(0);
    getOrderNav.on('click', function () {
        $.get("./order/", function (orders, status) {
            const table = $("#getOrderTable");
            const orderNum = orders.length;
            if (orderNum !== 0) {
                table.empty();
                for (let i = 0; i < orderNum; i++) {
                    var tr = '<tr>' +
                        '<td>' + orders[i].orderId + '</td>' +
                        '<td>' + orders[i].roomName + '</td>' +
                        '<td>' + orders[i].borrowerId + '</td>' +
                        '<td>' + orders[i].borrowerName + '</td>' +
                        '<td>' + orders[i].borrowerAcademyName + '</td>' +
                        '<td>' + orders[i].note + '</td>' +
                        '<td>' + orders[i].time + '</td>' +
                        '<td>' + orders[i].startTime + '</td>' +
                        '<td>' + orders[i].endTime + '</td>' +
                        '</tr>';
                    table.append(tr)
                }
            } else {
                table.empty();
                var tr = '<tr>' +
                    '<td>null</td>' +
                    '<td>null</td>' +
                    '<td>null</td>' +
                    '<td>null</td>' +
                    '<td>null</td>' +
                    '<td>null</td>' +
                    '<td>null</td>' +
                    '<td>null</td>' +
                    '<td>null</td>' +
                    '</tr>';
                table.append(tr)
            }
        })
    });
    getOrderNav.ready(function () {
        $.get("./order/", function (orders, status) {
            const table = $("#getOrderTable");
            const orderNum = orders.length;
            if (orderNum !== 0) {
                table.empty();
                for (let i = 0; i < orderNum; i++) {
                    var tr = '<tr>' +
                        '<td>' + orders[i].orderId + '</td>' +
                        '<td>' + orders[i].roomName + '</td>' +
                        '<td>' + orders[i].borrowerId + '</td>' +
                        '<td>' + orders[i].borrowerName + '</td>' +
                        '<td>' + orders[i].borrowerAcademyName + '</td>' +
                        '<td>' + orders[i].note + '</td>' +
                        '<td>' + orders[i].time + '</td>' +
                        '<td>' + orders[i].startTime + '</td>' +
                        '<td>' + orders[i].endTime + '</td>' +
                        '</tr>';
                    table.append(tr)
                }
            } else {
                table.empty();
                var tr = '<tr>' +
                    '<td>null</td>' +
                    '<td>null</td>' +
                    '<td>null</td>' +
                    '<td>null</td>' +
                    '<td>null</td>' +
                    '<td>null</td>' +
                    '<td>null</td>' +
                    '<td>null</td>' +
                    '<td>null</td>' +
                    '</tr>';
                table.append(tr)
            }
        })
    });

    const addOrderForm = $('#addOrder').children('form');
    const addOrderSubmit = addOrderForm.children('button');
    addOrderSubmit.on('click', function () {
        let args = addOrderForm.serializeArray();
        let orderVo = {};
        for (let arg in args) {
            orderVo[args[arg].name] = args[arg].value;
        }
        orderVo.time = getCurrentTime();
        alert(JSON.stringify(orderVo).toString());
        $.ajax({
            type: 'post',
            url: './order/',
            data: JSON.stringify(orderVo).toString(),
            contentType: 'application/json',
            dataType: 'json',
            success: function (data) {
                let addRoom = $('#addOrder');
                let successBar = '<div class="alert alert-success">\n' +
                    '               <a href="#" class="close" data-dismiss="alert">&times;</a>\n' +
                    '               <strong>成功！</strong>添加成功\n' +
                    '             </div>';
                addRoom.prepend(successBar);
            },
            error: function (XMLHttpRequest) {
                alert(XMLHttpRequest.getAllResponseHeaders());
                let addRoom = $('#addOrder');
                let failBar = '<div class="alert alert-danger">\n' +
                    '              <a href="#" class="close" data-dismiss="alert">&times;</a>\n' +
                    '              <strong>失败！</strong>发生了一些错误，请稍后再试\n' +
                    '          </div>';
                addRoom.prepend(failBar);
            }
        });
        return false;
    })
}

$(document).ready(function () {
    bindEventHandler();
});

function getCurrentTime() {
    let date = new Date();
    let month = zeroFill(date.getMonth() + 1);
    let day = zeroFill(date.getDate());
    let hour = zeroFill(date.getHours());
    let minute = zeroFill(date.getMinutes());
    let second = zeroFill(date.getSeconds());
    let curTime = date.getFullYear() + "-" + month + "-" + day
        + " " + hour + ":" + minute + ":" + second;
    return curTime;
}

function zeroFill(i) {
    if (i >= 0 && i <= 9) {
        return "0" + i;
    } else {
        return i;
    }
}