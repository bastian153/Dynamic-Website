package Web;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CompleteCart extends HttpServlet {

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
            
            String isbn = request.getParameter("isbn");
            String quantity = request.getParameter("quantity");
            int orderId = Integer.parseInt(request.getParameter("orderId"));

            try{
                Connection connection = DatabaseHelper.loginDatbase();
                String query = "INSERT INTO cart (cart_id, isbn13, quantity) VALUES (?, ?, ?)";

                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, orderId);
                ps.setString(2, isbn);
                ps.setInt(3, Integer.parseInt(quantity));

                ps.executeUpdate();
                ps.close();
                DatabaseHelper.logoutDatabase(connection);
            }catch (SQLException se){}
            
            emptyCart(request.getSession());
            
            // Redirect to Confirmation JSP
    }
    
    private void emptyCart(HttpSession session){
        Cart cart = (Cart)session.getAttribute("cart");
        cart = null;
        session.setAttribute("cart", cart);
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
