$(document).ready(function(){
$("#rstPw").validate({
    rules: {
        pw: {
            required: true,
            minlength: 6
        },
        newPw: {
            required: true,
            minlength: 6
        },
        reTypPw: {
            required: true,
            minlength: 6,
            equalTo: "#newPw"
        },
        agree: "required"
    },
    messages: {
        password: {
            required: "Please provide a password",
            minlength: "Your password must be at least 6 characters long"
        },
        confirm_password: {
            required: "Please provide a password",
            minlength: "Your password must be at least 6 characters long",
            equalTo: "New password and re typed password dont match"
        },
    }
});
});