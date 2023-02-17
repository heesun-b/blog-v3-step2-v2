<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
        <style>
            .container {
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            h2 {
                margin-top: 2rem;
            }

            form {
                width: 50%;
                margin-top: 2rem;
                margin-bottom: 2rem;
                display: flex;
                flex-direction: column;
                align-items: center;
                border: 1px solid gray;
                padding: 1rem;
                border-radius: 10px;
            }

            .form-group {
                margin-bottom: 1rem;
                text-align: center;
            }

            .form-group img {
                width: 300px;
                height: 300px;
                border-radius: 50%;
                margin-bottom: 1rem;
                border: 1px solid gray;

            }

            .btn {
                margin-top: 1rem;
                width: 20%;
            }
        </style>

        <div class="container my-3">
            <h2 class="text-center"><b>프로필 사진 변경</b></h2>
            <form action="/user/profileUpdate" method="post" enctype="multipart/form-data" id="profileForm">
                <div class="form-group">
                    <img src="${user.profile == null ? '/images/profile.png' : user.profile}" id="imagePreview"
                        alt="Current Photo" class="img-fluid">
                </div>
                <div class="form-group">
                    <input type="file" class="form-control" id="profile" name="profile" onchange="chooseImage(this)">
                </div>
                <button type="submit" class="btn btn-dark">사진변경</button>
            </form>
        </div>

        <script>
            //ajax
            function updateImage(params) {
                let profileForm = $("#profileForm")[0]; // id임에도 배열로 찾아야함
                let formData = new FormData(profileForm);

                $.ajax({
                    type: "put",
                    url: "/uesr/profileUpdate",
                    data: formData,
                    dataType: "Json",
                    contentType: false, // 필수 : x-www-form-urlencoded로 파싱되는 것을 방지 - contentType부터 확인하기 때문에 기본값으로 자동 적용해버림
                    processData: false, // 필수 : contentType을 false로 줬을 때 쿼리 스트링 자동 설정됨 
                    enctype: "Multipart/form-data" // 마지막으로 확인
                }).done((res) => {
                    alert(res.msg);
                    location.href = "/";
                }).fail((err) => {
                    alert(err.responseJSON.msg);
                });
            }

            function chooseImage(obj) {
                let f = obj.files[0];
                console.log(f);

                if (!f.type.match("image.*")) {
                    alert("이미지를 등록해야 합니다.");
                    return;
                }

                let reader = new FileReader();
                reader.readAsDataURL(f);
                // 리턴 타입이 void인 이유 : 싱글 스레드이기 때문에 파일을 받는동안 화면 구현을 할 수 없음 - io는 동작에 시간이 오래 걸림 

                // 콜스택이 다 비워지고 이벤트 루프로 가서 readAsDataURL 이번트가 끝나면 callback 시켜주는 함수 
                reader.onload = function (e) {
                    console.log(e);
                    console.log(e.target.result);
                    $("#imagePreview").attr("src", e.target.result);
                }

            }
        </script>


        <%@ include file="../layout/footer.jsp" %>