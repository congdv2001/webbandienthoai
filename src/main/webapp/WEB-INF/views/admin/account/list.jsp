<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
<header><h2 style="text-align: center;">Danh Sách Account</h2></header>
<main>
    <section class="container">
        <a class="btn btn-success" href="/admin/account/form">Add</a>
    </section>
    <section>
        <div class="container">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Username</th>
                    <th scope="col">Password</th>
                    <th scope="col">Fullname</th>
                    <th scope="col">Email</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listAccount}" var="ac">
                    <tr>
                        <td>${ac.username}</td>
                        <td>${ac.password}</td>
                        <td>${ac.fullname}</td>
                        <td>${ac.email}</td>
                        <td>
                            <a class="btn btn-primary" href="/admin/account/edit/${ac.username}">update</a>
                            <a class="btn btn-danger" href="/admin/account/delete/${ac.username}">xóa</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
</main>
<footer><p style="text-align: center;">Congdvph28782</p></footer>
</body>
</html>