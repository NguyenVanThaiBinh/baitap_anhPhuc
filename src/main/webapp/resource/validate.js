$(() => {
    $(".form_validate").validate({

        errorElement: 'div',
        onclick: false,
        rules: {

            fullName: {
                required: true,
                minlength: 3,
                maxlength: 40
            },

            email: {
                required: true,
                email: true,

            },
            phone: {
                required: true,
                validatePhone: true,
                number: true,
                minlength: 9,
                maxlength: 11
            },


        },
        messages: {
            fullName: {
                required: "Bắt buộc nhập tên đầy đủ",
                minlength: "Hãy nhập tối thiểu 5 ký tự",
                maxlength: "Hãy nhập tối đa 50 ký tự"
            },
            email: {
                required: "Bắt buộc email tên đầy đủ",
                email: "Hãy Email hợp lệ"
            },

            phone: {
                required: "Bắt buộc nhập số điện thoại",
                minlength: "Số điện thoại là 10 số",
                maxlength: "Số điện thoại là 10 số",

            },


        },



    });
    $.validator.addMethod("validatePhone", function (value, element) {
        return this.optional(element) || /((09|03|07|08|05)+([0-9]{8})\b)/g.test(value);
    }, "Hãy nhập số điện thoại hợp lệ!!!");





});