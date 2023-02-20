<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>

        <div class="container my-3" style="width: 400px;">
            <form action="/join" method="post" onsubmit="return valid()">
                <div class="d-flex justify-content-between">
                    <div class="form-group mb-2 d-flex w-75">
                        <input type="text" name="username" class="form-control" placeholder="Enter username"
                            id="username" required>
                    </div>
                    <button type="button" class="btn btn-dark mb-2" onclick="usernameChecking()">중복체크</button>
                </div>
                <div class="form-group mb-2">
                    <input type="password" name="password" class="form-control" placeholder="Enter password"
                        id="password" required>
                </div>

                <div class="form-group mb-2">
                    <input type="password" class="form-control" placeholder="Enter passwordCheck" id="passwordCheck"
                        required onchange="passwordChecking()">
                </div>

                <div id="passwordCheckAlert"></div>

                <div class="form-group mb-2">
                    <input type="email" name="email" class="form-control" placeholder="Enter Email" id="emil" required>
                </div>
                <button type="submit" class="btn btn-dark w-100">회원가입</button>
            </form>
        </div>

        <script>

            function usernameChecking() {

                let e = $("#username");
                let username = e.val();

                $.ajax({
                    type: "get",
                    data: username,
                    url: "/join/usernameCheck?username=" + username,
                }).done((res) => {
                    alert(res.msg);
                }).fail((err) => {
                    alert(err.responseJSON.msg);
                });
            }

            let checkingPassword = false;

            function valid() {
                if (checkingPassword == ture) {
                    return ture;
                } else {
                    return false;
                }
            }

            function passwordChecking() {
                let pw1 = $("#password").val();
                let pw2 = $("#passwordCheck").val();

                if (pw1 == pw2) {
                    checkingPassword = true;
                    $("#passwordCheckAlert").empty();
                    let passwordAlertBox = `<div class="alert alert-info">
                            <strong>Complete!</strong> 비밀번호 일치</div>`
                    $("#passwordCheckAlert").append(passwordAlertBox);
                } else {
                    checkingPassword = false;
                    $("#passwordCheckAlert").empty();
                    let passwordAlertBox = `<div class="alert alert-danger">
                     <strong>Check!</strong> 비밀번호 불일치 </div>`
                    $("#passwordCheckAlert").append(passwordAlertBox);
                }
            }
        </script>

        <%@ include file="../layout/footer.jsp" %>