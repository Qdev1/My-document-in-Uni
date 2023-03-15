

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>

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
                <form class="login100-form validate-form" method ="post" action="ConfirmController">
                    <div class="text-center" style ="color:red;">
                        ${returnMsg}
                    </div>
                    <br>
                    <span class="login100-form-title p-b-37">
                        Confirm your email
                    </span>
                    <h6 style="text-align: center">We've just sent a code to ${confEmail}.</h6>
                    <h6 style="text-align: center">Please, enter that code to continue.</h6>
                    <br>
                    <div class="wrap-input100 validate-input m-b-20" >
                        <input class="input100" type="text" name="confCode" value="${confValue}" placeholder="Confirm code:">
                        <span class="focus-input100"></span>
                    </div>

                    <div class="container-login100-form-btn">
                        <input type="submit" value="CONFIRM" name="confirm" class="login100-form-btn">
                    </div>
                    <br><!-- comment -->
                    <div class="container-login100-form-btn">
                        <input type="submit" value="Send again" name="retry" class="login100-form-btn">
                    </div>
                    <br>
                    <div class="text-center">
                        <a href="./Register.jsp" class="txt2 hov1">
                            Back
                        </a>
                    </div>



                </form>
            </div>
        </div>
    </body></html>