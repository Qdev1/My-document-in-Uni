<%-- 
    Document   : UserDetail
    Created on : Jul 15, 2022, 3:17:50 PM
    Author     : Nezio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UserDetail</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="./Login V9_files/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="./Login V9_files/main.css">
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/main.css">
    </head>
    <style>
        .topbar {
            padding-top: 5vh;
            background-color: blue;
            color :white;
        }
        .table_container {
            background-color: white;
            margin-left:  22vw;
            margin-right: 2vw;
            margin-bottom: 10vh;
            height: 85vh;
        }
        .button {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            -webkit-transition-duration: 0.4s; /* Safari */
            transition-duration: 0.4s;
        }

        .button1 {
            box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
        }
        .button2{
            border-radius: 8px;
            text-align: center;
            width: 15vw;
            margin-left: 2.5vw;

        }
        .button2:hover {
            box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);
        }
        .sidenav {
            height: 85%;
            width: 20vw;
            position: fixed;
            z-index: 1;
            top: 0;
            left: 0;
            background-color: white;
            overflow-x: hidden;
            padding-top: 20px;
            margin-top: 10vh;
        }
        input[type=text], select, textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }

        label {
            padding: 12px 12px 12px 0;
            display: inline-block;
        }

        input[type=submit] {
            background-color: #04AA6D;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            float: right;
        }

        input[type=submit]:hover {
            background-color: #45a049;
        }

        .container {
            border-radius: 5px;
            background-color: #f2f2f2;
            padding: 20px;
        }

        .col-25 {
            float: left;
            width: 25%;
            margin-top: 6px;
        }

        .col-75 {
            float: left;
            width: 75%;
            margin-top: 6px;
        }

        /* Clear floats after the columns */
        .row:after {
            content: "";
            display: table;
            clear: both;
        }
    </style>
    <body>
        <div class="container"  style="background-color: aqua;">
            <h2 style="text-align: center">User Infomation</h2>
            <form action="updateGoesBrrr">
                <div class="row">
                    <div class="col-25">
                        <label for="uname">UserID</label>
                    </div>
                    <div class="col-75">
                        <input readonly='true' type="text" id="uID" name="uID" value='${user.getUserID()}'>
                    </div></div>
                <div class="row">
                    <div class="col-25">
                        <label for="uname">Username</label>
                    </div>
                    <div class="col-75">
                        <input readonly="true" required="true" type="text" id="uname" name="username" value='${user.getAccount()}'>
                    </div></div>
                <div class="row">
                    <div class="col-25">
                        <label for="fname">Fullname</label>
                    </div>
                    <div class="col-75">
                        <input required="true" type="text" id="fname" name="fullname" value='${user.getFullname()}'>
                    </div></div>
                <div class="row">
                    <div class="col-25">
                        <label for="fname">Phone number</label>
                    </div>
                    <div class="col-75">
                        <input required="true" type="text" id="fname" name="phone" value='${user.getPhoneNumber()}'>
                    </div></div>
                <div class="row">
                    <div class="col-25">
                        <label for="fname">Email</label>
                    </div>
                    <div class="col-75">
                        <input required="true" type="text" id="fname" name="email" value='${user.getEmail()}'>
                    </div></div>
                <div class="row">
                    <div class="col-25">
                        <label for="fname">City</label>
                    </div>
                    <div class="col-75">
                        <input required="true" type="text" id="fname" name="city" value='${user.getCity()}'>
                    </div>
                </div>

                <br>
                <div class="row">
                    <input type="submit" name='customer' value="Submit">
                </div>
                <div class="text-center">
                    <a href="./home" class="txt2 hov1">
                        Home
                    </a>
                </div>
            </form>
        </div>
    </body>
</html>
