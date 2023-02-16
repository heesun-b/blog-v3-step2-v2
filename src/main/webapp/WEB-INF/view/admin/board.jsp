<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ include file="../layout/header.jsp" %>

    <div class="d-flex justify-content-end">
      <nav class="navbar bg-body-tertiary">
        <div class="container">
          <form class="d-flex" role="search">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="q">
            <button class="btn btn-outline-dark" type="button" onclick="searching()">Search</button>
          </form>
        </div>
      </nav>
    </div>

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

          <tbody id="t-box">
            <c:forEach items="${boardList}" var="board">
              <tr id="board-${board.id}">
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

      function searching() {
        let q = $("#q").val();

        $.ajax({
          type: "post",
          dataType: "json",
          data: q,
          url: "/admin/board/search",
          headers: {
            "Content-Type": "text/plain; charset=UTF-8"
          }
        }).done((res) => {
          // alert("D");
          // console.log(res.data);
          $("#t-box").empty();
          render(res.data);
        }).fail((err) => {
          alert("검색 실패");
        });

      }


      function render(boards) {
        boards.forEach((board) => {
          let el = ` <tr id="board-` + board.id + `}">
                <td>`+ board.id + `</td>
                <td>`+ board.title + `</td>
                <td>`+ board.username + `</td>
                <td>`+ board.createdAt + `</td>
                <td></td>
                <td>
                  <button id="btn-delete" class="btn btn-dark" onClick=" viewBoard(`+ board.id + `)">view</button>
                  <button id="btn-delete" class="btn btn-danger" onClick="deleteBoardById(`+ board.id + `)">삭제</button>
                </td>
              </tr>`;

          $("#t-box").prepend(el);
        });
      }
    </script>
    <%@ include file="../layout/footer.jsp" %>