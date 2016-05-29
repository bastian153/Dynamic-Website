<%@page import="Web.Cart"%>
<%@page import="Web.DatabaseHelper"%>
<%@page import="java.io.*,java.util.*,java.sql.*" %>
<%@page import="javax.servlet.http.*,javax.servlet.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<% 
    Connection c = DatabaseHelper.loginDatbase(); 
    Cart cart = (Cart)session.getAttribute("cart");
    int size = cart == null ? 0 : cart.size();
%>

<!DOCTYPE html>
<html>
    <head>
      <title>Confirmation Page</title>
      <link type="text/css" rel="stylesheet" href="css/stylesheet.css";
      <meta charset="UTF-8">
    </head>

    <body>
        <header>
            <h1>Noble &amp Barnes</h1>
            <nav>
                <ul>
                    <a href="Products"><li class="col-4">Products</li></a>
                    <a href="about.jsp"><li class="col-4">About</li></a>
                    <a href="Checkout"><li class="col-4">Checkout ( <%=size%> )</li></a>
                </ul>
            </nav>
        </header>

        <%  String oStmt = "SELECT * FROM orders WHERE orders.id = " + request.getParameter("orderId");
            PreparedStatement pOStmt = c.prepareCall(oStmt);
            ResultSet oRS = pOStmt.executeQuery();
            oRS.next();
        %>
        <div class="col-12 details">
            <h2>Confirmation Order ID#: <%=request.getParameter("orderId")%> </h2>
            <h3>Shipping Information</h3>
            <p><%=oRS.getString("first_name") + " " + oRS.getString("last_name")%></p>
            <p><%=oRS.getString("address")%></p>
            <p><%=oRS.getString("city")%>, <%=oRS.getString("state")%> <%=oRS.getString("zp_code")%></p>
            <hr/>
        </div>
            
        <div class="col-12 details">
            <h3>Products Ordered</h3>
            <table>
                <%
                    String tStmt = "SELECT bookName, cover, quantity, price, (quantity * price) AS cost "
                            + "FROM book, cart, orders "
                            + "WHERE orders.id = " + request.getParameter("orderId") 
                            + " AND cart_id = orders.id AND cart.isbn13 = book.isbn13";
                    PreparedStatement pTStmt = c.prepareCall(tStmt);
                    ResultSet tRS = pTStmt.executeQuery();
                    tRS.beforeFirst();
                    
                    double total = 0.0;
                    while(tRS.next()){
                        total = total + tRS.getDouble("cost");
                        out.println("<tr class=\"col-12\">");
                        out.println("<td class=\"col-2\"><img src=\"" + tRS.getString("cover") + "\" "
                                + "alt=\"" + tRS.getString("bookName") + "\"></td>");
                        out.println("<td class=\"col-2\"><p>Name: " + tRS.getString("bookName") +"</p></td>");
                        out.println("<td class=\"col-2\"><p>Price: " + tRS.getDouble("price") + "</p></td>");
                        out.println("<td class=\"col-2\"<p>Quantity: " + tRS.getString("quantity") + "</p></td>");
                        out.println("<td class=\"col-2\"<p>Cost: " + tRS.getDouble("cost") + "</p></td>");
                        out.println("</tr>");
                    }
                %>
            </table>
            <hr/>
        </div>
            
            
        <div class="col-12 details">
            <h3>Total Cost</h3>
            <%
                String taxQuery = "SELECT rate, tax.state FROM tax, orders "
                        + "WHERE tax.state = orders.state AND "
                        + "orders.id = " + request.getParameter("orderId");
                PreparedStatement taxPStmt = c.prepareCall(taxQuery);
                ResultSet taxRS = taxPStmt.executeQuery();
                taxRS.next();
            %>
            <p>Tax(<%=taxRS.getString("state")%>): %<%=taxRS.getDouble("rate")%></p>
            <p>Total(before shipping, after taxes): $<%=Math.round((total + (total * taxRS.getDouble("rate"))/100) * 100.0) / 100.0%></p>
        </div>

        <footer>
            <h3>Support #: 1-800-555-1632</h3>
            <h3>Support Email: <a href="mailto:support@noble.com">
                  support@noble.com</a></h3>
            <h3>Noble &amp Bares &copy 2016</h3>
        </footer>
</body>
</html>

<% DatabaseHelper.logoutDatabase(c);%>