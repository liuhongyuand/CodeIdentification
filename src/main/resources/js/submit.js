/**
 * Created by liuhongyu.louie on 2016/9/12.
 */

function submit() {

    $.ajax({
        type: 'post',
        url: 'choose',
        data: $('form').serialize(),
        success: function () {
            location.reload();
        }
    });
}


function isSubmit() {

    var isSubmit = true;
    var code = $("#code").val();
    var length = $("#letter_length").val();
    var input_length = code.length;
    var regex = /^[.a-z]*$/;
    $('#submit_info').text("");

    if((length == input_length || input_length == 0)) {
        if (input_length != 0) {
            if (!regex.test(code)) {
                isSubmit = false;
                $('#submit_info').text("Error format");
            }
        }
    } else {
        isSubmit = false;
        $('#submit_info').text("Error length");
    }

    if (isSubmit){
        submit();
    }

    return false;

}
