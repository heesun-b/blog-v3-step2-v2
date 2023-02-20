<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container my-3" style="width: 400px">
            <div class="container ">
                <form action="/login" method="post">

                    <div class="form-group mb-2 align-center">
                        <input type="text" name="username" class="form-control" placeholder="Enter username"
                            id="username">
                    </div>

                    <div class="form-group mb-2">
                        <input type="password" name="password" class="form-control" placeholder="Enter password"
                            id="password">
                    </div>

                    <button type="submit" class="btn btn-dark w-100">로그인</button>

                </form>
            </div>
        </div>
        <%@ include file="../layout/footer.jsp" %>