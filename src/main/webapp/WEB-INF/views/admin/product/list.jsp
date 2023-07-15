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
<header><h2 style="text-align: center;">Danh Sách Prodcut</h2></header>
<main>
    <section class="container">
        <a class="btn btn-success" href="/admin/product/form">Add</a>
    </section>
    <section>
        <div class="container">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Creatdate</th>
                    <th scope="col">Category</th>
                </tr>
                </thead>
                <tbody>
               <c:forEach items="${listProduct}" var="pr">
                    <tr>
                        <td>${pr.id}</td>
                        <td>${pr.name}</td>
                        <td>${pr.price}</td>
                        <td>${pr.createDate}</td>
                        <td>${pr.category.name}</td>
                        <td>
                            <a class="btn btn-primary" href="/admin/product/edit/${pr.id}">update</a>
                            <a class="btn btn-danger" href="/admin/product/delete/${pr.id}">xóa</a>
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