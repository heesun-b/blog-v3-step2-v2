<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

        <%@ include file="../layout/header.jsp" %>
                <div class="container my-3">
                        <div class="my-board-box row">
                                <c:forEach items="${dtos}" var="dto">
                                        <div class="card col-lg-3 p-2 ">
                                                <img class="card-img-top p-2" src="${dto.thumbnail}" alt="Card image"
                                                        style="height:100%">
                                                <hr>
                                                <div class="card-body">
                                                        <h4 class="card-title my-text-ellipsis">${dto.title}</h4>
                                                        <div class="d-flex justify-content-between">
                                                                <p>작성자: ${dto.username} </p>
                                                                <a href="/board/${dto.id}" class="btn btn-primary">상세보기</a>
                                                        </div>
                                                </div>
                                        </div>
                                </c:forEach>


                        </div>
                </div>

                <ul class="pagination mt-3 d-flex justify-content-center">
                        <li class="page-item disabled"><a class="page-link" href="#">◀</a></li>
                        <li class="page-item"><a class="page-link" href="#">▶</a></li>
                </ul>
                <%@ include file="../layout/footer.jsp" %>