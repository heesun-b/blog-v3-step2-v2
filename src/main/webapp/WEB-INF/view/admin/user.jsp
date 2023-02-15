<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ include file="../layout/header.jsp" %>

    <div class="d-flex">
      <div class="container mt-3" style="width: 200px;">
        <div class="list-group text-center">
          <a href="/admin/user" class="list-group-item list-group-item-action">회원 관리</a>
          <a href="/admin/board" class="list-group-item list-group-item-action">게시글 관리</a>
          <a href="/admin/reply" class="list-group-item list-group-item-action">댓글 관리</a>
        </div>
      </div>

      <div class="w-100 mt-3 mx-3">
        <table class="table table-hover">
          <thead>
            <tr>
              <th>ID</th>
              <th>USERNAME</th>
              <th>EMAIL</th>
              <th>CREATED</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${userList}" var="user">
              <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.createdAt}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
    </div>
    <%@ include file="../layout/footer.jsp" %>