var pageCurr;
var tableIns;

$(function () {
    layui.use('table', function () {
        var table = layui.table;
//给v会比较精彩发挥借记卡
        tableIns = table.render({
            elem: '#uesrList',
            url: '/subjects',
            method: 'post', //默认：get请求
            cellMinWidth: 60,
            page: true,
            request: {
                pageName: 'pageNum', //页码的参数名称，默认：pageNum
                limitName: 'pageSize' //每页数据量的参数名，默认：pageSize
            },
            response: {
                statusName: 'code', //数据状态的字段名称，默认：code
                statusCode: 200, //成功的状态码，默认：0
                countName: 'totals', //数据总数的字段名称，默认：count
                dataName: 'list' //数据列表的字段名称，默认：data
            },
            cols: [[
                {type: 'numbers'}
                , {field: 'name', title: '商品名', align: 'center'}
                , {field: 'price', title: '价格', align: 'center'}
                , {field: 'author', title: '配料', align: 'center'}
                , {field: 'bookStatus', title: '是否可出售', align: 'center'}
                , {title: '操作', align: 'center', toolbar: '#optBar'}
            ]],
            done: function (res, curr, count) {
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                //console.log(res);
                //得到当前页码
                //console.log(curr);
                $("[data-field='bookStatus']").children().each(function () {
                    if ($(this).text() == '1') {
                        $(this).text("不可出售")
                    } else if ($(this).text() == '0') {
                        $(this).text("可出售")
                    }
                });
                //得到数据总量
                pageCurr = curr;
            }

        });

        //监听工具条
        table.on('tool(userTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                //删除
                delBook(data.id, data.name);
            } else if (obj.event === 'edit') {
                //编辑
                openBook(data, "编辑");
            }
        });

    });

    //搜索框
    layui.use(['form'], function () {
        var form = layui.form;
        //监听搜索框
        form.on('submit(searchSubmit)', function (data) {
            //重新加载table
            load(data);
            return false;
        });

        //新增或修改提交
        form.on('submit(bookSubmit)', function (data) {
            formSubmit(data);
            return false;
        });
    });
});

function load(obj) {
    //重新加载table
    tableIns.reload({
        where: obj.field,
        page: {
            curr: pageCurr //从当前页码开始
        }
    });
}

//新增商品
function addBook() {
    openBook(null, "新增商品");
}

function openBook(data, title) {
    if (data == null || data == "") {
        $("#id").val("");
    } else {
        $("#id").val(data.id);
        $("#name").val(data.name);
        $("#author").val(data.author);
        $("#price").val(data.price);
    }

    layer.open({
        type: 1,
        title: title,
        fixed: false,
        resize: false,
        shadeClose: true,
        area: ['550px'],
        content: $('#setBook'),
        end: function () {
            cleanBook();
        }
    });
}

function cleanUser() {
    $("#name").val("");
    $("#author").val("");
    $("#price").val("");
}

//提交表单
function formSubmit(obj) {
    $.ajax({
        type: "POST",
        data: obj.field,
        url: "/book/setBook",
        success: function (data) {
            if (data.code == 1) {
                layer.alert(data.msg, function () {
                    layer.closeAll();
                    tableIns.reload();
                });
            } else {
                layer.alert(data.msg);
            }
        },
        error: function () {
            layer.alert("操作请求错误，请您稍后再试", function () {
                layer.closeAll();
                //加载load方法
                tableIns.reload();
            });
        }
    });
}

function delBook(id, name) {
    if (null != id) {
        layer.confirm('您确定要删除商品【' + name + '】吗？', {
            btn: ['确认', '返回'] //按钮
        }, function () {
            $.post("/book/delBook", {"id": id}, function (data) {
                if (data.code == 1) {
                    layer.alert(data.msg, function () {
                        layer.closeAll();
                        tableIns.reload();
                    });
                } else {
                    layer.alert(data.msg);
                }
            });
        }, function () {
            layer.closeAll();
        });
    }
}