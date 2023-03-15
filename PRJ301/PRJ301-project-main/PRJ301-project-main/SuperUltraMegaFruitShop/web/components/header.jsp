<%-- 
    Document   : header
    Created on : Jun 26, 2022, 8:49:10 AM
    Author     : duonn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- header -->
<div class="top-header-area" id="sticker">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 col-sm-12 text-center">
                <div class="main-menu-wrap">
                    <!-- logo -->
                    <div class="site-logo">
                        <a href="home">
                            <img src="assets/img/logo.png" alt="">
                        </a>
                    </div>
                    <!-- logo -->

                    <!-- menu start -->
                    <nav class="main-menu">
                        <ul>
                            <li class="current-list-item"><a href="home">Home</a></li>
                            <li><a href="about.jsp">About</a></li>
                            <li><a href="contact.jsp">Contact</a></li>
                            <li><a href="shop">Shop</a>
                            </li>
                            <li>
                                <c:if test="${sessionScope.account != null}">
                                <li class="nav-item">
                                    <a class="nav-link" href="UserDetail?account=${sessionScope.account}">Hello ${sessionScope.account}</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="logout">Logout</a>
                                </li> 
                            </c:if>
                            <c:if test="${sessionScope.account == null}">
                                <li class="nav-item">
                                    <a class="nav-link" href="Login.jsp">Login</a>
                                </li>
                            </c:if>                       
                            <li>
                                <form class="d-flex">
                                    <a class="btn btn-outline-dark" href="carts">
                                        <i class="bi-cart-fill me-1"></i>
                                        Cart
                                        <span id="cart_number" class="badge badge-info text-white ms-1 rounded-pill">${sessionScope.carts.size()}</span>
                                    </a>
                                </form>
                            </li>
                            <li>
                                <a class="mobile-show search-bar-icon" href="#"><i class="fas fa-search"></i></a>
                            </li>
                        </ul>
                    </nav>
                    <a class="mobile-show search-bar-icon" href="#"><i class="fas fa-search"></i></a>
                    <div class="mobile-menu"></div>
                    <!-- menu end -->
                </div>
            </div>
        </div>
    </div>
</div>
<!-- end header -->

<!-- search area -->
<form action="search" >
    <div class="search-area">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <span class="close-btn"><i class="fas fa-window-close"></i></span>
                    <div class="search-bar">
                        <div class="search-bar-tablecell">
                            <h3>Search For:</h3>
                            <input type="text" placeholder="Keywords" name="keyword">
                            <button type="submit">Search <i class="fas fa-search"></i></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<!-- end search arewa -->
