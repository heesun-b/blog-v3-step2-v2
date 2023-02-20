<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <input type="hidden" id="boardId" value="${boardDto.id}" />

        <div class="container my-3">
            <c:if test="${boardDto.userId == principal.id}">
                <div class="mb-3">
                    <a href="/board/${boardDto.id}/updateForm" class="btn btn-dark">수정</a>
                    <button id="btn-delete" class="btn btn-danger" onClick="deleteById(${boardDto.id})">삭제</button>
                </div>
            </c:if>

            <div class="d-flex justify-content-between">
                <div>
                    <h3>${boardDto.title}</h3>
                </div>

                <div class="mb-2">
                    글 번호 : <span id="id"><i>${boardDto.id} </i></span> 작성자 : <span><i>${boardDto.username} </i></span>

                    <c:choose>
                        <c:when test="${likeDto == null}">
                            <i id="heart" class="fa-regular fa-heart my-cursor my-xl" value="${likeDto.id}"
                                onclick="likeOrCancel()"></i>
                        </c:when>

                        <c:otherwise>
                            <i id="heart" class="fa-solid fa-heart my-cursor my-xl" value="${likeDto.id}"
                                onclick="likeOrCancel()"></i>
                        </c:otherwise>
                    </c:choose>


                    <script>
                        let boardId = $("#boardId").val();
                        // 자바스크립트는 정의만 하면 null이 아니라 undefined이 뜸
                        function likeOrCancel() {
                            let id = $("#heart").attr("value");

                            if (id == undefined) {

                                let data = {
                                    "boardId": boardId
                                }

                                //좋아요 통신 요청 (post)
                                $.ajax({
                                    type: "post",
                                    url: "/like",
                                    data: JSON.stringify(data),
                                    dataType: "Json",
                                    headers: {
                                        "Content-Type": "application/json; charset=UTF-8"
                                    }
                                }).done((res) => {
                                    // 좋아요된 갹체 받아야 함
                                    $("#heart").attr("value", res.data);
                                    $("#heart").addClass("fa-solid");
                                    $("#heart").removeClass("fa-regular");
                                }).fail((err) => {
                                    alert(err.responseJSON.msg);
                                });
                            } else {

                                // 좋아요 취소 통신 요청(delete)
                                $.ajax({
                                    type: "delete",
                                    url: "/like/" + id,
                                    dataType: "Json"
                                }).done((res) => {
                                    // 좋아요된 유저 아이디 받아야 함
                                    $("#heart").attr("value", undefined);
                                    $("#heart").addClass("fa-regular");
                                    $("#heart").removeClass("fa-solid");
                                }).fail((err) => {
                                    alert(err.responseJSON.msg);
                                });
                            }

                        }

                    </script>
                </div>
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
                                <c:if test="${principal.id == reply.userId || principal.roll == 'ADMIN'}">
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
                    dataType: "Json"
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
                    dataType: "Json"
                }).done((res) => {
                    $("#reply-" + id).remove();
                }).fail((err) => {
                    alert(err.responseJSON.msg);
                });
            }

        </script>


        <%@ include file="../layout/footer.jsp" %>