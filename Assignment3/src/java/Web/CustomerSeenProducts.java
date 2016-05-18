package Web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CustomerSeenProducts extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession s = request.getSession();
        LinkedList<String> recentlyViewed = (LinkedList<String>)s.getAttribute("view");
        
        if(recentlyViewed != null && !recentlyViewed.isEmpty() && !s.isNew()){
            try (PrintWriter out = response.getWriter()) {
                out.println("<div class=\"recent\">");
                out.println("<h2>Your ID: " + s.getId() + "</h2>");
                out.println("<h2>Recently Viewed Products</h2>");
                displayRecentProducts(recentlyViewed, out);
                out.println("</div>");
            } catch(Exception ignore){}
        }
        RequestDispatcher rd = request.getRequestDispatcher("footer.jsp");
        rd.include(request, response);
    }
    
    
    private void displayRecentProducts(LinkedList<String> recent, PrintWriter out){
        Connection c = DatabaseHelper.loginDatbase();
        out.println("<table class=\"col-12\"><tr>");
        try {
            int size = Math.min(5, recent.size());
            for(int i = 0; i < size; i++){
                String stmt = "SELECT bookName, name, isbn13, genre, price, cover "
                + "FROM book, author "
                + "WHERE authorID = id AND isbn13 = " + recent.get(i);
                PreparedStatement pstmt = c.prepareStatement(stmt);
                ResultSet result = pstmt.executeQuery();
                result.next();
                
                out.println("<td class=\"col-3\">");
                out.println("<a href=product.jsp?isbn=" 
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
                out.println("</a></td>");
            }
        } catch(SQLException e){}
                
        out.println("</tr></table>");
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
