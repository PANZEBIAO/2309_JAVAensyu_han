document.addEventListener("DOMContentLoaded", function() {
//ユーザー名のinputとspanを取得
const usernameInput = document.querySelector("#userName");
const username = document.querySelector(".username");

//メールのinputとspanを取得
const emailInput = document.querySelector("#email");
const email = document.querySelector(".email");

//パスワードのinputとspanを取得
const passwordInput = document.querySelector("#password");
const password = document.querySelector(".password");

//パスワード確認のinputとspanを取得
const passwordtooInput = document.querySelector("#passwordtoo");
const passwordtoo = document.querySelector(".passwordtoo");

//checkboxとsubmitを取得
const checkbox = document.querySelector(".checkbox")
const submitButton = document.querySelector(".btn")


//ユーザー名が使えるかどうかを判断

usernameInput.addEventListener("input", () => {
    const usernameValue = usernameInput.value;
    if (usernameValue.trim() !== "" && usernameValue.length >= 6 && usernameValue.length <= 16) {
        username.innerHTML = "使えます";
        username.className = "success";
    } else {
        username.innerHTML = "6~16字のアルファベット数字記号を入力してください";
        username.className = "error";
    }
});

//メールアドレスが使えるかどうかを判断

emailInput.addEventListener("input", () => {
    const emailValue = emailInput.value;
    if (emailValue.trim() !== "" && /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(emailValue)) {
        email.innerHTML = "使えるメールアドレスです";
        email.className = "success";
    } else {
        email.innerHTML = "正しいメールアドレスを入力してください";
        email.className = "error";
    }
});

//パスワードが使えるかどうかを判断

passwordInput.addEventListener("input", () => {
    const passwordValue = passwordInput.value;
    if (passwordValue.trim() !== "" && passwordValue.length >= 6 && passwordValue.length <= 16) {
        password.innerHTML = "合ってます";
        password.className = "success";
    } else {
        password.innerHTML = "6~16字のアルファベット数字記号を入力してください";
        password.className = "error";
    }
});

//パスワードが同じかどうかを判断

passwordtooInput.addEventListener("input", () => {
    const passwordtooValue = passwordtooInput.value;
    const passwordValue = passwordInput.value;
    if (passwordValue.trim() === passwordtooValue.trim()) {
        passwordtoo.innerHTML = "合ってます";
        passwordtoo.className = "success";
    } else {
        passwordtoo.innerHTML = "6~16字のアルファベット数字記号を入力してください";
        passwordtoo.className = "error";
    }
});

//すべての入力が合ってるかどうかをチェック

function checkInputs() {
    const usernameValue = usernameInput.value.trim();
    const emailValue = emailInput.value.trim();
    const passwordValue = passwordInput.value.trim();
    const passwordtooValue = passwordtooInput.value.trim();

    const isUsernameValid = usernameValue.length >= 6 && usernameValue.length <= 16;
    const isEmailValid = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(emailValue);
    const isPasswordValid = passwordValue.length >= 6 && passwordValue.length <= 16;
    const isPasswordMatch = passwordValue === passwordtooValue;

    return isUsernameValid && isEmailValid && isPasswordValid && isPasswordMatch;
}

//入力する文字とcheckboxの状態をチェック

usernameInput.addEventListener("input", updateSubmitButton);
emailInput.addEventListener("input", updateSubmitButton);
passwordInput.addEventListener("input", updateSubmitButton);
passwordtooInput.addEventListener("input", updateSubmitButton);
checkbox.addEventListener("change", updateSubmitButton);

// button状態を更新

function updateSubmitButton() {
    if (checkInputs() && checkbox.checked) {
        submitButton.disabled = false;
        submitButton.style.backgroundColor = "skyblue"
    } else {
        submitButton.disabled = true;
        submitButton.style.backgroundColor = "#ececec"
    }
}
});