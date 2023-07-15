<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
<header><h2 style="text-align: center;">Thêm/sửa Category</h2></header>
<main>
    <form action="/admin/category/update?id=${category.id}" method="post" class="container">
        <div class="mb-3">
            <label class="form-label">ID</label>
            <input type="text" class="form-control" name="id" value="${category.id}">
        </div>
        <div class="mb-3">
            <label class="form-label">Name</label>
            <input type="text" class="form-control" name="name" value="${category.name}">
        </div>
        <button class="btn btn-success" type="submit">Update</button>
    </form>
</main>
<footer><p style="text-align: center;">Congdvph28728</p></footer>
</body>
</html>
