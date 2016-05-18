package Web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
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
            Connection c = DatabaseHelper.loginDatbase();
            queryProduct(c, out, isbn);
            DatabaseHelper.logoutDatabase(c);
            /* TODO: Finish this method, add servlet context 
               on Most Viewed Products and include footer.jsp*/ 
            getProductsViewed(request, response);
        } 
    }
    
    
    private void addProductViewed(HttpServletRequest request, String isbn){
        HttpSession s = request.getSession();
        LinkedList<String> recent = (LinkedList<String>)s.getAttribute("view");
        if(recent == null){
            recent = new LinkedList<String>();
            recent.add(isbn);
            s.setAttribute("view", recent);
            return;
        }
        
        if(recent.contains(isbn))
            recent.remove(isbn);
        recent.addFirst(isbn);
        s.setAttribute("view", recent);
    }
    
    
    private void queryProduct(Connection c, PrintWriter out, String isbn){
        String stmt = "SELECT * FROM book, author WHERE "
                + isbn + " = isbn13 AND authorID = id";
        try {
            PreparedStatement pstmt = c.prepareStatement(stmt);
            ResultSet r = pstmt.executeQuery();
            r.next();
            
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
            out.println(r.getString("summary"));
            // TODO: Add a FORM button to update THE CART
            out.println("</div></div>");
            
            
            // Author Description
            out.println("<div id=\"author\" class\"col-12\">");
            out.println("<h1>About the Author</h1>" + r.getString("description"));
            out.println("</div>");
            
        } catch(SQLException ignore){
            out.println("<h1>Product details could not be found</h1>");
        }
    }
    
    private void getProductsViewed(HttpServletRequest request, 
                                        HttpServletResponse response){
        // TODO: Create Servlet Context Object for most Viewed Products
        // TODO: Include footer.jsp for the rest of the webpage
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
