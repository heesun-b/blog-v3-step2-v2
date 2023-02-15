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
              <th>TITLE</th>
              <th>USERNAME</th>
              <th>CREATED</th>
              <th></th>
              <th></th>
            </tr>
          </thead>

          <tbody>
            <c:forEach items="${boardList}" var="board">
              <tr id="board-${board.id}"">
                <td>${board.id}</td>
                <td class=" my-text-ellipsis">${board.title}</td>
                <td>${board.username}</td>
                <td>${board.createdAt}</td>

                <td></td>
                <td>
                  <button id="btn-delete" class="btn btn-dark" onClick=" viewBoard(${board.id})">view</button>
                  <button id="btn-delete" class="btn btn-danger" onClick="deleteBoardById(${board.id})">삭제</button>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>

      </div>
    </div>


    <script>

      function viewBoard(id) {
        location.href = "/board/" + id;
      }

      function deleteBoardById(id) {
        $.ajax({
          type: "delete",
          url: "/admin/board/delete/" + id,
          dataType: "Json"
        }).done((res) => {
          alert(res.msg);
          $("#board-" + id).remove();
        }).fail((err) => {
          alert(err.responseJSON.msg);
        });
      }
    </script>
    <%@ include file="../layout/footer.jsp" %>