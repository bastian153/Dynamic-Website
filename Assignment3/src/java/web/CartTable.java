package Web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartTable extends HttpServlet {

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
        HttpSession session = request.getSession();
        Map<String, Integer> cart = (Map<String, Integer>)session.getAttribute("cart");
        Connection connection = DatabaseHelper.loginDatbase();
        
        try (PrintWriter out = response.getWriter()) {
            for(Map.Entry<String, Integer> pair: cart.entrySet()){
                writeTableRow(connection, out, pair);
            }
        }
        
        DatabaseHelper.logoutDatabase(connection);
    }
    
    
    private void writeTableRow(Connection c, PrintWriter out, 
                                   Map.Entry<String, Integer> pair){
        String stmt = "SELECT *"
                + "FROM book "
                + "WHERE isbn13 = " + pair.getKey();
        try {
            PreparedStatement pstmt = c.prepareCall(stmt);
            ResultSet result = pstmt.executeQuery();
            if(result.next()){
                out.println("<tr class=\"col-12\">");
                displayCover(out, result);
                displayName(out, result);
                displayPrice(out, result);
                out.println("</tr>");
            }
        } catch(SQLException ignore){
            out.println("<h1>Error</h1>");
        }
    }
    
    
    private void displayCover(PrintWriter out, ResultSet rs) throws SQLException{
        out.println("<td class=\"col-2\"><img src=\""
                + rs.getString("cover") + "\" alt=\"" + rs.getString("bookName")
                + "\">");
    }
    
    
    private void displayName(PrintWriter out, ResultSet rs) throws SQLException{
        out.println("<td class=\"col-2>\"><p>" + rs.getString("bookName")
                    + "</p></td>");
    }
    
    
    private void displayPrice(PrintWriter out, ResultSet rs) throws SQLException{
        out.println("<td class=\"col-2\"><p>" + rs.getString("price")
                    + "</p></td>");
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
