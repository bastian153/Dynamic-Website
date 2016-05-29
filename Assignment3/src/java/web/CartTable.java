package Web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
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
        Cart cart = (Cart) session.getAttribute("cart");
        Connection connection = DatabaseHelper.loginDatbase();
        List<Product> products = cart.getProducts();
        double sum = 0;
        
        try (PrintWriter out = response.getWriter()) {
            for(Product product: products){
                sum = sum + product.getPrice();
                writeTableRow(connection, out, product, products.indexOf(product));
            }
            out.println(";" + sum);
        }
        
        DatabaseHelper.logoutDatabase(connection);
    }
    
    
    private void writeTableRow(Connection c, PrintWriter out, 
                               Product product, int index){
        out.println("<tr class=\"col-12\" id=\"Row-" + index + "\">");
        displayCover(out, product);
        displayName(out, product);
        displayPrice(out, product, index);
        displayQuantity(out, product, index);
        displayUpdate(out, product, index);
        //displayRemove(out, product, index);
        out.println("</tr>");
    }
    
    
    private void displayCover(PrintWriter out, Product p){
        out.println("<td class=\"col-2\"><img src=\""
                + p.getImgSrc() + "\" alt=\"" + p.getName()
                + "\">");
    }
    
    
    private void displayName(PrintWriter out, Product p){
        out.println("<td class=\"col-2>\"><p>" + p.getName()
                    + "</p></td>");
    }
    
    
    private void displayPrice(PrintWriter out, Product p, int index){
        out.println("<td class=\"col-2\"><p id=\"Price-" + index
                    + "\">" + p.getPrice()
                    + "</p></td>");
    }
    
    
    private void displayQuantity(PrintWriter out, Product p, int index) {
        out.println("<td class=\"col-2\"><p id=\"Quantity-" + index
                    + "\">1</p></td>");
    }
    
    private void displayUpdate(PrintWriter out, Product p, int index){
        out.println("<td class=\"col-2\">"
                + "<p>Quantity</p>"
                + "<input type=\"number\" min=\"1\" id=\"Update-" + index
                + "\" value=\"1\"/>"
                + "<input type=\"submit\" value=\"Update\" onclick=\"updateQuantity("
                + "'Price-" + index + "', 'Quantity-" + index + "', 'Update-"
                + index + "', '" + p.getIsbn() + "')\">");
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
