
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

        <!-- title -->
        <title>Single Product</title>

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
                            <p>See more Details</p>
                            <h1>Single Product</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end breadcrumb section -->

        <!-- single product -->
        <div class="single-product mt-150 mb-150">
            <div class="container">
                <div class="row">
                    <div class="col-md-5">
                        <div class="single-product-img">
                            <img src="${product.p_img}" alt="">
                        </div>
                    </div>
                    <div class="col-md-7">
                        <div class="single-product-content">
                            <h3>${product.p_name}</h3>
                            <p class="single-product-pricing"><span>Per Kg</span> ${product.p_price}$</p>
                            <p> ${product.desc}</p>  
                            <p><b> production date - expiry date: ${product.date_manufacture} - ${product.date_expriration}</p>  
                            <div class="single-product-form">
                                <!--                                <form action="index.html">
                                                                    <input type="number" placeholder="0">
                                                                </form>-->
                                <a href="add-to-cart?productId=${product.p_id}" class="cart-btn"><i class="fas fa-shopping-cart"></i> Add to Cart</a>
                                <p><strong>Categories: </strong>${cate}</p>
                            </div>
                            <h4>Share:</h4>
                            <ul class="product-share">
                                <li><a href=""><i class="fab fa-facebook-f"></i></a></li>
                                <li><a href=""><i class="fab fa-twitter"></i></a></li>
                                <li><a href=""><i class="fab fa-google-plus-g"></i></a></li>
                                <li><a href=""><i class="fab fa-linkedin"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end single product -->

        <!-- more products -->
        <div class="more-products mb-150">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 offset-lg-2 text-center">
                        <div class="section-title">	
                            <h3><span class="orange-text">Related</span> Products</h3>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid, fuga quas itaque eveniet beatae optio.</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <c:forEach var="p" items="${ListR}">
                        <div class="col-lg-4 col-md-6 text-center">
                            <div class="single-product-item">
                                <div class="product-image">
                                    <a href="detail?productId=${p.p_id}"><img src="${p.p_img}" alt=""></a>
                                </div>
                                <h3>${p.p_name}</h3>
                                <p class="product-price"><span>Per Kg</span> ${p.p_price}$ </p>
                                <a href="add-to-cart?productId=${p.p_id}" class="cart-btn"><i class="fas fa-shopping-cart"></i> Add to Cart</a>
                            </div>
                        </div>

                    </c:forEach>
                </div>
                
            </div>
        </div>
        <!-- end more products -->
        <!<!-- even more!!! -->
        
        <div class="more-products mb-150">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 offset-lg-2 text-center">
                        <div class="section-title">	
                            <h3><span class="orange-text">Random</span> Products</h3>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid, fuga quas itaque eveniet beatae optio.</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <c:forEach var="p" items="${ListR2}">
                        <div class="col-lg-4 col-md-6 text-center">
                            <div class="single-product-item">
                                <div class="product-image">
                                    <a href="detail?productId=${p.p_id}"><img src="${p.p_img}" alt=""></a>
                                </div>
                                <h3>${p.p_name}</h3>
                                <p class="product-price"><span>Per Kg</span> ${p.p_price}$ </p>
                                <a href="add-to-cart?productId=${p.p_id}" class="cart-btn"><i class="fas fa-shopping-cart"></i> Add to Cart</a>
                            </div>
                        </div>

                    </c:forEach>
                </div>
                
            </div>
        </div>
        
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