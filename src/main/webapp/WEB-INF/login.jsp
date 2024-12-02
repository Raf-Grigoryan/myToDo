<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>To Do Login Page</title>
</head>
<style>
    body {
        font-family: "Poppins", sans-serif;
        background: #f2f4f7;
        margin: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .content {
        text-align: center;
    }

    .flex-div {
        display: flex;
        flex-direction: column;
        align-items: center;
        background: #fff;
        padding: 2rem;
        border-radius: 0.5rem;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1), 0 8px 16px rgba(0, 0, 0, 0.1);
    }

    .name-content .logo {
        font-size: 2.5rem;
        color: #1877f2;
        margin-bottom: 1rem;
    }

    .name-content p {
        font-size: 1.2rem;
        font-weight: 500;
        margin-bottom: 2rem;
    }

    form {
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    form input {
        width: 80%;
        padding: 0.8rem;
        margin: 0.5rem 0;
        border: 1px solid #ccc;
        border-radius: 0.3rem;
        font-size: 1rem;
    }

    form .login {
        background: #1877f2;
        color: #fff;
        padding: 0.8rem 1rem;
        border: none;
        border-radius: 0.3rem;
        font-size: 1rem;
        margin-top: 1rem;
        cursor: pointer;
    }

    form .login:hover {
        background: #165ec7;
    }

    a.create-account {
        display: inline-block;
        margin-top: 1rem;
        text-decoration: none;
        background: #06b909;
        color: #fff;
        padding: 0.8rem 1rem;
        border-radius: 0.3rem;
        font-size: 1rem;
        transition: background 0.3s;
    }

    a.create-account:hover {
        background: #049d07;
        cursor: pointer;
    }
</style>
<body>
<% String loginMsg = (String) session.getAttribute("loginMsg");%>
<div class="content">
    <div class="flex-div">
        <div class="name-content">
            <%if (loginMsg != null) {%>
            <h1 class="logo" style="color: red"><%=loginMsg%>
                <%} else {%>
                <h1 class="logo">My To Do</h1>
                <p>Create Your ToDo In Browser</p>
            </h1>
            <%}%>
        </div>
        <form action="/login" method="post">
            <input type="email" placeholder="Email" required name="email"/>
            <input type="password" placeholder="Password" required name="password">
            <button class="login" type="submit">Log In</button>
            <hr>
        </form>
        <a href="/register" class="create-account">Create New Account</a>
    </div>
</div>
</body>