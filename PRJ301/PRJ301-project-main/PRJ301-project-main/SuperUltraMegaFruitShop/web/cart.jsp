<%-- 
    Document   : cart
    Created on : Jul 3, 2022, 11:19:24 PM
    Author     : duonn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

        <!-- title -->
        <title>Cart</title>

        <!-- favicon -->
        <link rel="shortcut icon" type="image/png" href="assets/img/favicon.png">
        <!-- google font -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
        <!-- fontawesome -->
        <link rel="stylesheet" href="assets/css/all.min.css">
        <!-- bootstrap -->
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <!-- owl carousel -->
        <link rel="stylesheet" href="assets/css/owl.carousel.css">
        <!-- magnific popup -->
        <link rel="stylesheet" href="assets/css/magnific-popup.css">
        <!-- animate css -->
        <link rel="stylesheet" href="assets/css/animate.css">
        <!-- mean menu css -->
        <link rel="stylesheet" href="assets/css/meanmenu.min.css">
        <!-- main style -->
        <link rel="stylesheet" href="assets/css/main.css">
        <!-- responsive -->
        <link rel="stylesheet" href="assets/css/responsive.css">

    </head>
    <body>

        <!--PreLoader-->
        <div class="loader">
            <div class="loader-inner">
                <div class="circle"></div>
            </div>
        </div>
        <!--PreLoader Ends-->

        <%@include file="components/header.jsp" %>

        <!-- breadcrumb-section -->
        <div class="breadcrumb-section breadcrumb-bg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 offset-lg-2 text-center">
                        <div class="breadcrumb-text">
                            <p>Fresh and Organic</p>
                            <h1>Cart</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end breadcrumb section -->

        <!-- cart -->
        <div class="cart-section mt-150 mb-150">
            <c:choose>
                <c:when test="${sessionScope.carts==null||sessionScope.carts.size()==0}">
                    <div class="alert alert-warning" role="alert">
                        <h3> No products in cart!! </h3>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-8 col-md-12">
                                <div class="cart-table-wrap">

                                    <table class="cart-table">
                                        <thead class="cart-table-head">
                                            <tr class="table-head-row">
                                                <th class="product-remove"></th>
                                                <th class="product-image">Product Image</th>
                                                <th class="product-name">Name</th>
                                                <th class="product-price">Price</th>
                                                <th class="product-quantity">Quantity</th>
                                                <th class="product-total">Total</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="c" items="${carts}">

                                            <form action="update-quantity">
                                                <tr class="table-body-row">
                                                <input type="hidden" name="productId" value="${c.value.product.p_id}">
                                                <td class="product-remove"><a href="delete-cart?productId=${c.value.product.p_id}"><i class="far fa-window-close"></i></a></td>
                                                <td class="product-image"><img src="${c.value.product.p_img}" alt=""></td>
                                                <td class="product-name">${c.value.product.p_name}</td>
                                                <td class="product-price">$${c.value.product.p_price}</td>
                                                <td class="product-quantity"><input onchange="this.form.submit()" type="number" name="quantity" value="${c.value.quantity}"/></td>
                                                <td class="product-total">${c.value.quantity}</td>
                                                </tr>
                                            </form>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <a href="delete-cart-all" class="btn btn-outline-danger m-3"><i class="bi bi-trash"></i>Delete all cart</a>


                                    <h5 class="text-center text-danger">${mess}</h5>


                                </div>
                            </div>

                            <div class="col-lg-4">
                                <div class="total-section">
                                    <table class="total-table">
                                        <thead class="total-table-head">
                                            <tr class="table-total-row">
                                                <th>Total</th>
                                                <th>Price</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr class="total-data">
                                                <td><strong>Subtotal: </strong></td>
                                                <td>$${totalMoney}</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <div class="cart-buttons">
                                        <a href="checkout" class="boxed-btn black">Check Out</a>
                                    </div>
                                </div>

                                <div class="coupon-section">
                                    <h3>Apply Coupon</h3>
                                    <div class="coupon-form-wrap">
                                        <form action="index.html">
                                            <p><input type="text" placeholder="Coupon"></p>
                                            <p><input type="submit" value="Apply"></p>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        <!-- end cart -->

        <!-- logo carousel -->
        <div class="logo-carousel-section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="logo-carousel-inner">
                            <div class="single-logo-item">
                                <img src="assets/img/company-logos/1.png" alt="">
                            </div>
                            <div class="single-logo-item">
                                <img src="assets/img/company-logos/2.png" alt="">
                            </div>
                            <div class="single-logo-item">
                                <img src="assets/img/company-logos/3.png" alt="">
                            </div>
                            <div class="single-logo-item">
                                <img src="assets/img/company-logos/4.png" alt="">
                            </div>
                            <div class="single-logo-item">
                                <img src="assets/img/company-logos/5.png" alt="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end logo carousel -->

        <%@include file="components/footer.jsp" %>

        <!-- jquery -->
        <script src="assets/js/jquery-1.11.3.min.js"></script>
        <!-- bootstrap -->
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <!-- count down -->
        <script src="assets/js/jquery.countdown.js"></script>
        <!-- isotope -->
        <script src="assets/js/jquery.isotope-3.0.6.min.js"></script>
        <!-- waypoints -->
        <script src="assets/js/waypoints.js"></script>
        <!-- owl carousel -->
        <script src="assets/js/owl.carousel.min.js"></script>
        <!-- magnific popup -->
        <script src="assets/js/jquery.magnific-popup.min.js"></script>
        <!-- mean menu -->
        <script src="assets/js/jquery.meanmenu.min.js"></script>
        <!-- sticker js -->
        <script src="assets/js/sticker.js"></script>
        <!-- main js -->
        <script src="assets/js/main.js"></script>

    </body>
</html>