<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

        <%@ include file="../layout/header.jsp" %>

                <div class="card m-5" style="width:400px">
                        <img class="card-img-top p-2" src="/images/dora.png" alt="Card image" style="width:100%">
                        <hr>
                        <div class="card-body">
                                <h4 class="card-title my-text-ellipsis">제목</h4>
                                <div class="d-flex justify-content-between">
                                        <p>작성자: </p>
                                        <a href="#" class="btn btn-primary">상세보기</a>
                                </div>
                        </div>


                </div>

                <ul class="pagination mt-3 d-flex justify-content-center">
                        <li class="page-item disabled"><a class="page-link" href="#">◀</a></li>
                        <li class="page-item"><a class="page-link" href="#">▶</a></li>
                </ul>
                <%@ include file="../layout/footer.jsp" %>