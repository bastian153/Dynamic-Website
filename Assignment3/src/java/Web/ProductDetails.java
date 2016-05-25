package Web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProductDetails extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String isbn = request.getParameter("isbn");
            addProductViewed(request, isbn);  
            displayHeader(request, out);
            Connection c = DatabaseHelper.loginDatbase();
            queryProduct(request, c, out, isbn);
            DatabaseHelper.logoutDatabase(c);
            getProductsViewed(request, isbn, out);
            displayFooter(request, response);
        } 
    }
    
    
    private void displayHeader(HttpServletRequest request, PrintWriter out){
        HttpSession session = request.getSession();
        Map<String, Integer> cart = (Map<String, Integer>)session.getAttribute("cart");
        int cartSize = cart == null ? 0 : cart.size();
        out.println("<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<title>Noble &amp Barnes:Product Details</title>"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/stylesheet.css\">"
                + "<script src=\"js/jquery-1.12.4.min.js\" type=\"text/javascript\"></script>"
                + "<script src=\"js/customerInteraction.js\" type=\"text/javascript\"></script>"
                + "</head>"
                + "<body>"
                + "<header><h1>Noble &amp Barnes</h1>"
                + "<nav><ul>"
                + "<a href=\"Products\"><li class=\"col-4\">Products</li></a>"
                + "<a href=\"./about.jsp\"><li class=\"col-4\">About</li></a>"
                + "<a href=\"Checkout\"><li class=\"col-4\">Checkout ( <span id=\"itemsInCart\">"
                + cartSize + "</span> )</li></a>"
                + "</ul></nav></header>");
    }
    
    
    private void addProductViewed(HttpServletRequest request, String isbn){
        HttpSession s = request.getSession();
        LinkedList<String> recent = (LinkedList<String>)s.getAttribute("view");
        if(recent == null){
            recent = new LinkedList<>();
            recent.add(isbn);
            s.setAttribute("view", recent);
            return;
        }
        
        if(recent.contains(isbn))
            recent.remove(isbn);
        recent.addFirst(isbn);
        s.setAttribute("view", recent);
    }                    
                         
    
    private void queryProduct(HttpServletRequest request, Connection c, 
                                PrintWriter out, String isbn){
        String stmt = "SELECT * FROM book, author WHERE "
                + isbn + " = isbn13 AND authorID = id";
        try {
            PreparedStatement pstmt = c.prepareStatement(stmt);
            ResultSet r = pstmt.executeQuery();
            r.next();
            out.println("<div class=\"col-12 description\">"
                    + "<div class=\"col-5\">");
            
            // Product Information
            out.println("<img src=\"" + r.getString("cover")
                        + "\" alt=\"" + r.getString("bookName") + "\">");
            out.println("<h2>" + r.getString("bookName") + "</h2>");
            out.println("<p>Author: " + r.getString("name") + "</p>");
            out.println("<p>ISBN-10: " + r.getString("isbn10") + "</p>");
            out.println("<p>ISBN-13: " + r.getString("isbn13").substring(0, 3)
                + "-" + r.getString("isbn13") + "</p>");
            out.println("<p>Genre: " + r.getString("genre") + "</p>");
            out.println("<p>Price: $" + r.getString("price") + "</p></div>");
            
            
            // Product Description
            out.println("<div class=\"col-6 summary\"><h1>Description</h1>");
            out.println("<p>" + r.getString("summary") + "</p>");
            
            
            // Check to see if item is already in cart
            HttpSession session = request.getSession();
            Map<String, Integer> cart = (Map<String, Integer>)session.getAttribute("cart");
            out.println("<div id=\"divAddToCart\">");
            if(cart != null && cart.containsKey(r.getString("isbn13"))){
                out.println("<h5>Item already in cart</h5>");
            } else {
                out.println("<input type=\"submit\" onclick=\"addingToCart(" + 
                        r.getString("isbn13") + ")\" value=\"Add To Cart\" id=\"buttonAddToCart\">");
            }
            out.println("</div></div></div>");
            
            
            // Author Description
            out.println("<div id=\"author\" class\"col-12\">");
            out.println("<h1>About the Author</h1>" + r.getString("description"));
            out.println("</div>");
            
        } catch(SQLException ignore){
            out.println("<h1>Product details could not be found</h1>");
        }
    }
    
    private void getProductsViewed(HttpServletRequest request, String isbn, PrintWriter out){
        ServletContext context = request.getServletContext();
        String userId = request.getSession().getId();
        Map<String, String> userToProduct = (Map<String, String>)context.getAttribute("mostViewed");
        if(userToProduct == null){
            userToProduct = new LinkedHashMap<String, String>();
        }
        
        userToProduct.put(userId, isbn);
        context.setAttribute("mostViewed", userToProduct);
        
        out.println("<div class=\"recent\">"
                + "<h2>Most Currently Viewed Products</h2>"
                + "<table class=\"col-12\">"
                + "<tr>");
        
        Map<String, Integer> mostViewed = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : userToProduct.entrySet()){
            Integer visits = mostViewed.get(entry.getValue());
            if(visits == null){
                mostViewed.put(entry.getValue(), 1);
            } else {
                mostViewed.put(entry.getValue(), visits + 1);
            }
        }        
        
        Connection c = DatabaseHelper.loginDatbase();
        
        int count = 0;
        int size = Math.min(5, mostViewed.size());
        Iterator it = mostViewed.entrySet().iterator();
        while(it.hasNext() && count < size){
            Map.Entry pair = (Map.Entry)it.next();
            String stmt = "SELECT * FROM book, author WHERE "
                + pair.getKey() + " = isbn13 AND authorID = id";
            try {
                PreparedStatement pstmt = c.prepareStatement(stmt);
                ResultSet result = pstmt.executeQuery();
                result.next();
                out.println("<td class=\"col-2\">");
                out.println("<a href=ProductDetails?isbn=" 
                    + result.getString("isbn13") + ">");
                out.println("<img src="+ result.getString("cover") + " alt=\""
                        + result.getString("bookName") + "\">");
                out.println("<p>" + result.getString("bookName") + "</p>");
                out.println("<p>Author: " + result.getString("name") + "</p>");
                out.println("<p>Genre: " + result.getString("genre") + "</p>");
                out.println("<p>ISBN-13: " 
                        + result.getString("isbn13").substring(0, 3) + "-"
                        + result.getString("isbn13").substring(3) + "</p>");
                out.println("<p>Price: $" + result.getString("price") + "</p>");
                out.println("Currently Viewing: " + mostViewed.get(result.getString("isbn13")));
                out.println("</a></td>");
            } catch(SQLException ignore){}
            count += 1;
        }
        
        DatabaseHelper.logoutDatabase(c);
        out.println("</tr></table></div>");
    }
    
    
    private <K, V extends Comparable<? super V>> Map<K, V> 
        sortByValue( Map<K, V> map )
    {
        List<Map.Entry<K, V>> list =
            new LinkedList<>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
        {
            @Override
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }

    
    private void displayFooter(HttpServletRequest request, 
            HttpServletResponse response){
        try {
            RequestDispatcher rd = request.getRequestDispatcher("/footer.jsp");
            rd.include(request, response);
        } catch(ServletException | IOException ignore){}
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}