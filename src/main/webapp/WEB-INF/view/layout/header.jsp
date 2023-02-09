<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>📃Blog</title>
            <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
                crossorigin="anonymous">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
                crossorigin="anonymous"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
            <script src="https://kit.fontawesome.com/32aa2b8683.js" crossorigin="anonymous"></script>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
            <script src="https://kit.fontawesome.com/0d1c1758fd.js" crossorigin="anonymous"></script>
            <link rel="stylesheet" href="https://kit.fontawesome.com/0d1c1758fd.css" crossorigin="anonymous">
            <link rel="stylesheet" href="css/style.css">
        </head>

        <body>

            <nav class="navbar navbar-expand-sm bg-warning navbar-dark">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">Blog</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapsibleNavbar">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse justify-content-between" id="collapsibleNavbar">

                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link" href="#">로그인</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/joinForm">회원가입</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">글쓰기</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">회원정보</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">로그아웃</a>
                            </li>
                        </ul>
                        <div>
                            <a href="/user/profileUpdate"><img src="/images/profile.png" style="width: 35px;"
                                    class="rounded-circle" alt="Cinque Terre"></a>
                        </div>

                    </div>
                </div>
            </nav>