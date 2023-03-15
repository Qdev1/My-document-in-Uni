<%-- 
    Document   : Register
    Created on : Jun 26, 2022, 8:56:50 PM
    Author     : Nezio
--%>

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
                <form class="login100-form validate-form" method='get' action="RegisterController">
                    <div class="text-center" style = "color:red;">
                        ${returnMsg}
                    </div>
                    <br>
                    <span class="login100-form-title p-b-37">
                        Continue To Sign Up
                    </span>
                    <div class="wrap-input100 validate-input m-b-20" data-validate="Enter your full name">
                        <input required="true"class="input100" type="text" name="Fullname" value="${retFullname}" placeholder="Fullname">
                        <span class="focus-input100"></span>
                    </div>
                    <div class="wrap-input100 validate-input m-b-20" data-validate="Enter your phone number">
                        <input required="true" class="input100" type="text" name="Phone" value="${retPhone}" placeholder="Phone Number">
                        <span class="focus-input100"></span>
                    </div>
                    <div class="wrap-input100 validate-input m-b-20" data-validate="Give your father your address">
                        <input required="true" class="input100" type="text" name="Address" value="${retAddress}" placeholder="Your address">
                        <span class="focus-input100"></span>
                    </div>
                    <br>
<!--                    <div style="text-align: center">
                        <select style = "display:inline-block;" onchange="submit" name="select">
                            <option name="Select_role">Choose your role: </option>
                            <option name="customer">Customer</option>
                            <option name="shipper">Shipper</option>
                        </select>
                        <span class="focus-input100"></span>
                    </div>-->
                    <br><!-- comment -->
                    <div class="container-login100-form-btn">
                        <input type="submit" value="SIGN UP" name="register2" class="login100-form-btn">
                    </div>


                    <br>
                    <div class="container-login100-form-btn">
                        <input type="submit" value="You had an account?" name="login" class="login100-form-btn">
                    </div>

                </form>
            </div>
        </div>
    </body></html>
