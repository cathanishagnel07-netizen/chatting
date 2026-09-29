<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width,initial-scale=1"><title>Chat Login</title>
<style>body{margin:0;font-family:Arial,sans-serif;background:#eef2f7;display:flex;min-height:100vh;align-items:center;justify-content:center}.box{width:340px;background:#fff;padding:32px;border-radius:14px;box-shadow:0 10px 35px #0002}h1{text-align:center;margin:0 0 24px}input{width:100%;box-sizing:border-box;padding:12px;margin:7px 0;border:1px solid #ccd3dc;border-radius:8px}button{width:100%;padding:12px;margin-top:10px;border:0;border-radius:8px;background:#1677ff;color:#fff;font-size:16px;cursor:pointer}.error{color:#c62828;text-align:center}</style></head><body><div class="box"><h1>Chat Login</h1>
<% String error=request.getParameter("error"); if(error!=null){ %><div class="error"><%= error %></div><% } %>
<form action="login" method="post"><input name="username" placeholder="Username" autocomplete="username" required><input type="password" name="password" placeholder="Password" autocomplete="current-password" required><button type="submit">Login</button></form>
</div></body></html>
