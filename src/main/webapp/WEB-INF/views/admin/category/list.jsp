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
<header><h2 style="text-align: center;">Danh Sách Category</h2></header>
<main>
    <section class="container">
        <a class="btn btn-success" href="/admin/category/form">Add</a>
    </section>
    <section>
        <div class="container">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listCategory}" var="ct">
                    <tr>
                        <td>${ct.id}</td>
                        <td>${ct.name}</td>
                        <td>
                            <a class="btn btn-primary" href="/admin/category/edit/${ct.id}">update</a>
                            <a class="btn btn-danger" href="/admin/category/delete/${ct.id}">xóa</a>
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