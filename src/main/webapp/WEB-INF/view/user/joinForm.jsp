<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>

        <div class="container my-3" style="width: 400px;">
            <form action="/join" method="post" onsubmit="return valid()">
                <div class="form-group mb-2 d-flex">
                    <input type="text" name="username" class="form-control" placeholder="Enter username" id="username"
                        required>
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
            <button type="submit" class="btn btn-dark">회원가입</button>
            </form>

     
        </div>

        <script>

            let checkedPassword = false;

            function valid() {
                if (checkedPassword == ture) {
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