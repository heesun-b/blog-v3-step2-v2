<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>📃Blog</title>
              <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
            <script src="https://kit.fontawesome.com/32aa2b8683.js" crossorigin="anonymous"></script>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
            <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>

            <script src="https://kit.fontawesome.com/0d1c1758fd.js" crossorigin="anonymous"></script>
            <link rel="stylesheet" href="https://kit.fontawesome.com/0d1c1758fd.css" crossorigin="anonymous">
            <link rel="stylesheet" href="css/style.css">
        </head>

        <body>

            <nav class="navbar navbar-expand-sm bg-warning navbar-dark">
                <div class="container-fluid">
                    <a class="navbar-brand" href="/">Blog</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapsibleNavbar">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse justify-content-between" id="collapsibleNavbar">
                    <c:choose>
                       <c:when test="${principal == null}">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link" href="/loginForm">로그인</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/joinForm">회원가입</a>
                            </li>           
                        </ul>
                       </c:when>
                    
                       <c:otherwise>
                       <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link" href="/saveForm">글쓰기</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">회원정보</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/logout">로그아웃</a>
                            </li>
                        </ul>
                       </c:otherwise>
                    </c:choose>
                      
                        <div>
                            <a href="/user/profileUpdate"><img src="/images/profile.png" style="width: 35px;"
                                    class="rounded-circle" alt="Cinque Terre"></a>
                        </div>

                    </div>
                </div>
            </nav>