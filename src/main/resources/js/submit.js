/**
 * Created by liuhongyu.louie on 2016/9/12.
 */
$(function () {

    $('form').on('submit', function (e) {

        e.preventDefault();

        $.ajax({
            type: 'post',
            url: 'choose',
            data: $('form').serialize(),
            success: function () {
                location.reload();
            }
        });

    });

});