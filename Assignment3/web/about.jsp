<%@page import="Web.ShoppingCart"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Noble &amp Barnes</title>
	<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
    </head>
    <body>
        <header>
            <h1>Noble &amp Barnes</h1>
            <nav>
                <ul>
                    <a href="Products"><li class="col-4">Products</li></a>
                    <a href=""><li class="col-4">About</li></a>
                    <a href="Checkout"><li class="col-4">Checkout ( <span id="itemsInCart"><% 
                       LinkedList<ShoppingCart> cart = (LinkedList<ShoppingCart>)session.getAttribute("cart");
                       if(cart == null)
                        out.println(0);
                       else
                        out.println(cart.size());%></span>) </li></a>
                </ul>
            </nav>
	</header>
        
        <div id="about-body" class="col-12">
            <h1>About Us!</h1>

            <br/>
            <p>
                Our company is located in Irvine, California. We are a new, innovative, and passionate startup.
                We focus on selling our core products, which are books. These books are selectively collected from
                a lot of sellers in order to give our customers the best prices that they can get. Even though our
                company is only one year old, we have been receiving a lot of positive feedbacks from our customers.
                Here is our staff:
            </p>
            <br/>

            <img src="img/john_cena.jpg" width="300" height="300">
            <p> Jason Lam </p>
            <br/>
            <br/>

            <img src="img/zlatan.jpeg" width="300" height="300">
            <p> Adrian Padilla </p>
            <br/>
            <br/>

            <img src="img/messi.jpeg" width="300" height="300">
            <p> Tim Nguyen </p>
            <br/>
            <br/>

            <img src="img/ronaldo.jpg" width="300" height="300">
            <p> Ruben Columbus </p>
            <br/>
            <br/>
	</div>
        
        <footer>
            <h3>Support #: 1-800-555-1632</h3>
            <h3>Support Email: <a href="mailto:support@noble.com">support@noble.com</a></h3>
            <h3>Noble &amp Barnes &copy 2016</h3>
	</footer>
    </body>
</html>