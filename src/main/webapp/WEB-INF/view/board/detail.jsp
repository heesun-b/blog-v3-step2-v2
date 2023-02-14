<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container my-3">
            <c:if test="${boardDto.userId == principal.id}">
                <div class="mb-3">
                    <a href="/board/${boardDto.id}/updateForm" class="btn btn-dark">수정</a>
                    <button id="btn-delete" class="btn btn-danger" onClick="deleteById(${boardDto.id})">삭제</button>
                </div>
            </c:if>

            <div class="mb-2">
                글 번호 : <span id="id"><i>${boardDto.id} </i></span> 작성자 : <span><i>${boardDto.username} </i></span>
                <i id="heart" class="fa-regular fa-heart my-cursor my-xl"></i>

            </div>

            <div>
                <h3>${boardDto.title}</h3>
            </div>
            <hr />
            <div>
                <div>${boardDto.content}</div>
            </div>
            <hr />

            <div class="card">
                <form action="/reply" method="post">
                    <input type="hidden" name="boardId" value="${boardDto.id}">
                    <div class="card-body">
                        <textarea id="reply-comment" name="comment" class="form-control" rows="1"></textarea>
                    </div>
                    <div class="card-footer">
                        <button type="submit" id="btn-reply-save" class="btn btn-primary">등록</button>
                    </div>
                </form>
            </div>

            <br />
            <div class="card">
                <div class="card-header">댓글 리스트</div>
                <ul id="reply-box" class="list-group">
                    <c:forEach items="${replyDtos}" var="reply">
                        <li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
                            <div>${reply.comment}</div>
                            <div class="d-flex">
                                <div class="font-italic">작성자 : ${reply.username} &nbsp;</div>
                                <c:if test="${principal.id == reply.userId}">
                                    <button onClick="DeleteByReplyId(${reply.id})" class="badge bg-danger">삭제</button>
                                </c:if>
                            </div>
                        </li>
                    </c:forEach>

                </ul>
            </div>
        </div>

        </div>
        <script>

            function deleteById(id) {
                $.ajax({
                    type: "delete",
                    url: "/board/" + id,
                    dataType: "json"
                }).done((res) => {
                    alert(res.msg);
                    location.href = "/";
                }).fail((err) => {
                    alert(err.responseJSON.msg);
                });
            }
            function DeleteByReplyId(id) {
                $.ajax({
                    type: "delete",
                    url: "/reply/" + id,
                    dataType: "json"
                }).done((res) => {
                    $("#reply-" + id).remove();
                }).fail((err) => {
                    alert(err.responseJSON.msg);
                });
            }
        </script>



        <%@ include file="../layout/footer.jsp" %>