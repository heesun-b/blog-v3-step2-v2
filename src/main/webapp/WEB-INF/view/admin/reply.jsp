<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ include file="../layout/header.jsp" %>

  
    <div class="d-flex justify-content-end">
      <nav class="navbar bg-body-tertiary">
        <div class="container">
          <form class="d-flex" role="search" action="/admin/search" method="post">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="q">
            <button class="btn btn-outline-dark" type="submit">Search</button>
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
              <th>BOARD_ID</th>
              <th>COMMENT</th>
              <th>USER</th>
              <th>CREATED</th>
               <th></th>
            </tr>
          </thead>
          <tbody>
          <c:forEach items="${replyList}" var="reply">
            <tr id="reply-${reply.id}">
              <td>${reply.id}</td>
              <td>${reply.boardId}</td>
              <td class="my-text-ellipsis">${reply.comment}</td>
              <td>${reply.username}</td>
              <td>${reply.createdAt}</td>
              <td>
                    <button id="btn-delete" class="btn btn-dark" onClick="viewReplyById(${reply.boardId})">view</button>
                    <button id="btn-delete" class="btn btn-danger" onClick="deleteReplyById(${reply.id})">삭제</button>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
    </div>

  <script>

  function viewReplyById(boardId) {
    location.href = "/board/" +boardId;
  }
    function deleteReplyById(id) {
      $.ajax({
        type : "delete",
        url : "/admin/reply/delete/" +id, 
        dataType : "Json"
      }).done((res) => {
          alert(res.msg);
          $("#reply-"+id).remove();
      }).fail((err)=>{
        alert(err.responseJSON.msg);
      });
    }
  
  </script>

    <%@ include file="../layout/footer.jsp" %>