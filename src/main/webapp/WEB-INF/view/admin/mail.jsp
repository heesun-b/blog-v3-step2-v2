<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>

        <div class="container">
            <!-- CHANGE THE URL HERE -->
            <form action="/admin/sendMail" method="post">
                <h3 style="text-align: center;">Email</h3>

                <div class="form-group mb-3">
                    <label for="address">이메일</label>
                    <input name="address" type="email" class="form-control" id="address" placeholder="Enter email"
                        value="${user.email}" required>
                </div>

                <div class="form-group mb-3">
                    <label for="title">제목</label>
                    <input name="title" type="text" class="form-control" id="title" placeholder="Enter title" required>
                </div>

                <div class="form-group mb-3">
                    <label for="message">내용</label>
                    <textarea name="message" class="form-control" id="message" rows="5" placeholder="Enter message"
                        required></textarea>
                </div>
                <div class="d-flex justify-content-end">
                    <button type="submit" class="btn btn-dark float-right">전송</button>
                </div>
            </form>
        </div>


        <%@ include file="../layout/footer.jsp" %>