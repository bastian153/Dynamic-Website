package Web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;


public class Products extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            displayHeader(request, out);
            displayProducts(out);
            displayRecentlyViewed(request, response);
        }
    }
    
    
    private void displayHeader(HttpServletRequest request, PrintWriter out){
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        int cartSize = cart == null ? 0 : cart.size();
        out.println("<!DOCTYPE html><html><title>Noble &amp Barnes</title>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/stylesheet.css\">");
        out.println("</head><body><header><h1>Noble &amp Barnes</h1><nav><ul>");
        out.println("<a href=\"\"><li class=\"col-4\">Products</li></a>");
        out.println("<a href=\"./about.jsp\"><li class=\"col-4\">About</li></a>");
        out.println("<a href=\"Checkout\"><li class=\"col-4\">Checkout ( <span id=\"itemsInCart\">"
                + cartSize + "</span> )</li></a>");
        out.println("</ul></nav></header>");
    }
    
    
    private void displayProducts(PrintWriter out){
        // TODO: Create connection to MySQL database and display products        
        Connection c = DatabaseHelper.loginDatbase();
        if(c == null){
            out.println("<h1>Connection was not established<h1>");
            return;
        }
        
        // Create the table
        out.println("<div id=\"products\">");
        out.println("<table class=\"col-12\">");
        queryProducts(c, out);
        DatabaseHelper.logoutDatabase(c);
        out.println("</table></div>");
    }

    
    
    private void displayRecentlyViewed(HttpServletRequest request, 
                                    HttpServletResponse response){
        try {
            RequestDispatcher rd = request.getRequestDispatcher("CustomerSeenProducts");
            rd.include(request, response);
        } catch(ServletException | IOException ignore){
        }
    }

    
    
    
    public static void queryProducts(Connection connection, PrintWriter out){
        String stmt = "SELECT bookName, name, isbn13, genre, price, cover "
                + "FROM book, author "
                + "WHERE authorID = id ORDER BY RAND()";
        try {
            PreparedStatement pstmt = connection.prepareStatement(stmt);
            ResultSet result = pstmt.executeQuery();
            int count = 0;
            out.println("<tr>");
            while(result.next()){
                displayTableCell(result, out);
                count = count + 1;
                if(count % 3 == 0 && count < 12){
                    out.println("</tr><tr>");
                }
            }
        } catch(SQLException ignore){}
    }
    
    
    private static void displayTableCell(ResultSet result, PrintWriter out){
        try {
            out.println("<td class=\"col-4\">");
            out.println("<a href=ProductDetails?isbn=" 
                    + result.getString("isbn13") + ">");
            out.println("<img src=\"" + result.getString("cover") + "\" alt=\""
                    + result.getString("bookName") + "\">");
            out.println("<p>" + result.getString("bookName") + "</p>");
            out.println("<p>Author: " + result.getString("name") + "</p>");
            out.println("<p>Genre: " + result.getString("genre") + "</p>");
            out.println("<p>ISBN-13: " 
                    + result.getString("isbn13").substring(0, 3) + "-"
                    + result.getString("isbn13").substring(3) + "</p>");
            out.println("<p>Price: $" + result.getString("price") + "</p>");
            out.println("</a></td>");
            
        } catch (SQLException ignore){}
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