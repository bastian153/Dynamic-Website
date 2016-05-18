package Web;

import java.io.IOException;
import java.io.PrintWriter;
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
        //TESTING
        if(recentlyViewed == null || recentlyViewed.isEmpty()){
            try(PrintWriter out = response.getWriter()){
                out.println("<h1>" + s.getId() + "</h1>");
                out.println("<h1>" + recentlyViewed + "</h1>");
            } catch(Exception ignore){
            }
        }
        
        if(recentlyViewed != null && !recentlyViewed.isEmpty() && !s.isNew()){
            try (PrintWriter out = response.getWriter()) {
                out.println("<div class=\"recent\">");
                out.println("<h2>Recently Viewed Products</h2>");
                out.println("<p>Your id: " + s.getId() + "</p>");
                out.println("</div>");
            } catch(Exception ignore){}
        }
        RequestDispatcher rd = request.getRequestDispatcher("footer.jsp");
        rd.include(request, response);
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
