document.addEventListener("DOMContentLoaded", function () {
    let username = document.getElementById("username");
    let email = document.getElementById("email");
    let password = document.getElementById("pwd");
    let profilepic = document.getElementById("profilepic");
    let editButton = document.getElementById("edit-settings");

    editButton.addEventListener("click", function() {
        if (username.hasAttribute("readonly")) {
            username.removeAttribute("readonly");
        } else {
            username.setAttribute('readonly', 'readonly');
        }

        if (email.hasAttribute("readonly")) {
            email.removeAttribute('readonly');
        } else {
            email.setAttribute('readonly', 'readonly');
        }

        if (password.hasAttribute("readonly")) {
            password.removeAttribute("readonly");
        } else {
            password.setAttribute('readonly', 'readonly');
        }

        if (profilepic.hasAttribute("disabled")) {
            profilepic.removeAttribute("disabled");
        } else {
            profilepic.setAttribute('disabled', 'disabled');
        }
    })
})