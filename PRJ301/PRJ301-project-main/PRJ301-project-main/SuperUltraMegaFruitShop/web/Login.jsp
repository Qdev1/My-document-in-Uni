<%-- 
    Document   : Login
    Created on : Jun 23, 2022, 7:19:52 PM
    Author     : Nezio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="icon" type="image/png" href="https://colorlib.com/etc/lf/Login_v9/images/icons/favicon.ico">

        <link rel="stylesheet" type="text/css" href="./Login V9_files/bootstrap.min.css">

        <link rel="stylesheet" type="text/css" href="./Login V9_files/font-awesome.min.css">

        <link rel="stylesheet" type="text/css" href="./Login V9_files/material-design-iconic-font.min.css">

        <link rel="stylesheet" type="text/css" href="./Login V9_files/animate.css">



        <link rel="stylesheet" type="text/css" href="./Login V9_files/animsition.min.css">

        <link rel="stylesheet" type="text/css" href="./Login V9_files/select2.min.css">


        <link rel="stylesheet" type="text/css" href="./Login V9_files/util.css">
        <link rel="stylesheet" type="text/css" href="./Login V9_files/main.css">

        <meta name="robots" content="noindex, follow">
    <body>
        <div class="container-login100" style="background-image: url(&#39;img/login_bg_img.jpg&#39;);">
            <div class="wrap-login100 p-l-55 p-r-55 p-t-80 p-b-30">
                <form class="login100-form validate-form" method ="post" action="LoginController">
                    <div class="text-center" style ="color:red;">
                        ${returnMsg}
                    </div>
                    <br>
                    <span class="login100-form-title p-b-37">
                        Sign In
                    </span>
                    <div class="wrap-input100 validate-input m-b-20" data-validate="Enter username or email">
                        <input class="input100" type="text" name="account" placeholder="username or email">
                        <span class="focus-input100"></span>
                    </div>
                    <div class="wrap-input100 validate-input m-b-25" data-validate="Enter password">
                        <input class="input100" type="password" name="pass" placeholder="password">
                        <span class="focus-input100"></span>
                    </div>
                    <div class="container-login100-form-btn">
                        <input type="submit" value="SIGN IN" name="login" class="login100-form-btn">
                    </div>
                    
                    
                    <br>
                    <div class="container-login100-form-btn">
                        <input type="submit" value="My dog ate my password" name="forgotPass" class="login100-form-btn">
                    </div>
                    <br>
                    <div class="text-center">
                        <a href="./Register.jsp" class="txt2 hov1">
                            Sign Up
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </body></html>