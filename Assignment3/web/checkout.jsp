<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<% 
   Map<String, Integer> cart = (Map<String, Integer>)session.getAttribute("cart");
   int size = cart.size();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Noble &amp Barnes: Checkout</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="css/stylesheet.css">

        <script src="js/jquery-1.12.4.min.js" type="text/javascript"></script>
	<!-- Create States and Month options for select -->
	<script type="text/javascript" src="js/my_script.js"></script>

	<!-- Check each field of the form -->
	<script type="text/javascript" src="js/formchecker.js"></script>
        
        <!-- Update the cart -->
        <script type="text/javascript" src="js/customerInteraction.js"></script>
    </head>
    <body onload="main()">
        <header>
            <h1>Noble &amp Barnes</h1>
            <nav>
                <ul>
                    <a href="Products"><li class="col-4">Products</li></a>
                    <a href="about.jsp"><li class="col-4">About</li></a>
                    <a href="Checkout"><li class="col-4">Checkout ( 
                            <span id="itemsInCart"><%= size %></span> )</li></a>
                </ul>
            </nav>
	</header>
        
        <!-- Billing -->
        <div class="order_form">
            <div class="step">
                <div class="number">
                    <span>1</span>
                </div>
                <div class="title">
                    <h1>Billing Information</h1>
                </div>
            </div>
        </div>
        <div class="content">
            <form name="Billing-Info">
                <div class="input-info">
                    <i class="fa fa-user fa-lg"></i>	
                    <input name="firstName" class="all-input" type="text" placeholder="First Name"></input>
                    <p id="first-name-error">Field is missing</p>
                </div>
                
                <div class="input-info">	
                    <input name="lastName" class="all-input" style="margin-left:39px" type="text" placeholder="Last Name"></input>
                    <p id="last-name-error">Field is missing</p>
                </div>
                
                <div class="input-info">
                    <i class="fa fa-location-arrow fa-lg"></i>
                    <input name="address" class="all-input" type="text" placeholder="Address"></input>
                    <p id="address-error">Incorrect Format: street number and street name</p>
                </div>

                <div class="input-info">	
                    <input name="city" class="all-input" style="margin-left:39px" type="text" placeholder="City"></input>
                    <p id="city-error">Field is missing</p>
                </div>
                <div class="input-info">	
                    <select name="state" class="input-select" id="selectElementId" style="margin-left:39px">
                        <option selected value="">State</option>
                    </select>
                    <p id="state-error">State was not selected</p>
                </div>
                <div class="input-info">	
                    <input name="areaCode" class="all-input" style="margin-left:39px" type="text" placeholder="Postal Code"></input>
                    <p id="zip-code-error">Incorrect format: five digits, +4 Zip Code Optional</p>
                </div>
            </form>
        </div>
        
        
        
        <!-- Shipping -->
        <div class="step">
            <div class="number">
                <span>2</span>
            </div>
            <div class="title">
                <h1>Shipping Information</h1>
            </div>
        </div>
        <div class="content">
            <form name="Shipping-Info">
                <div class="shipping">
                    <input class="radio" type="radio" id="shipping_1" value="1" name="rad_shipping" checked/>
                    <label for="shipping_1"> Standard Shipping <span class="price">$4.00</span></label>
                </div>
                <div class="shipping">		
                    <input class="radio" type="radio" id="shipping_2" value="2" name="rad_shipping"/>
                    <label for="shipping_2"> Express Shipping <span class="price">$8.00</span></label>
                </div>
                <div class="shipping">		
                    <input class="radio" type="radio" id="shipping_3" value="3" name="rad_shipping"/>
                    <label for="shipping_3"> Overnight Shipping <span class="price">$12.00</span></label>
                </div>
            </form>
        </div>
        
        
        
        <!-- Credit Card -->
        <div class="step">
            <div class="number">
                <span>3</span>
            </div>
            <div class="title">
                <h1>Credit Card Information</h1>
            </div>
        </div>
        <div class="content">
            <div id="left">
                <form name="Creditcard-Info">
                    <div class="input-info">	
                        <input name="card" class="all-input" style="margin-left:39px" type="text" placeholder="XXXX-XXXX-XXXX-XXXX"></input>
                        <p id="credit-card-error">Incorrect Format: 16 digits</p>
                    </div>
                    <div class="input-info" style="margin-left:39px;margin-top:5px;height:47px">
                        <select name="month" id="month">
                            <option selected value="">Month</option>
                        </select>
                        <span class="dividor">-</span>
                        <select name="year" id="year">
                            <option selected value="">Year</option>
                            <option value="2016">2016</option>
                            <option value="2017">2017</option>
                            <option value="2018">2018</option>
                            <option value="2019">2019</option>
                            <option value="2020">2020</option>
                        </select>
                    </div>
                    <div class="input-info" style="margin-top:5px">	
                        <input name="csv" class="all-input" style="margin-left:39px" type="text" placeholder="CSV"></input>
                        <p id="csv-error">Incorrect Format: 3 digits</p>
                    </div>
                </form>
            </div>
            <div id="right">
                <input type="image" src="img/amex.png">
                <input type="image" src="img/master.png">
                <input type="image" src="img/visa.png">
                <input type="image" src="img/discover.png">
            </div>	
        </div>
        
        <div class="step">
            <div class="number">
                <span>4</span>
            </div>
            <div class="title">
                <h1>Cart</h1>
            </div>
        </div>
        <div class="col-12">
            <table class="col-12 checkoutTable">
                <tr class="col-12">
                    <td class="col-2">Cover</td>
                    <td class="col-2">Name</td>
                    <td class="col-2">Price</td>
                    <td class="col-2">Quantity</td>
                    <td class="col-2">Update</td>
                    <td class="col-2">Remove</td>
                </tr>
            </table>
            <table clas="col-12" id="checkoutCart">
                
            </table>
            <div class="content">
                <p>Tax: <span id="tax">0%</span></p>
                <p>Total Price: <span id="total_price">$0</span></p>
            </div>
        </div>
        
        <footer>
            <h3>Support #: 1-800-555-1632</h3>
            <h3>Support Email: <a href="mailto:support@noble.com">support@noble.com</a></h3>
            <h3>Noble &amp Barnes &copy 2016</h3>
        </footer>
    </body>
</html>
