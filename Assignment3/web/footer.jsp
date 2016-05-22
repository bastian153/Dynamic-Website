<%-- 
    Document   : footer
    Created on : May 21, 2016, 6:46:04 PM
    Author     : Adrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <footer>
        <h3>Support #: 1-800-555-1632</h3>
        <h3>Support Email: <a href="mailto:support@noble.com">support@noble.com</a></h3>
        <h3>Noble &amp Barnes &copy 2016</h3>
    </footer>
    
    <script src="js/jquery-1.12.4.min.js" type="text/javascript"></script>
    <script>
        $(document).ready(function(){            
            $(window).bind("beforeunload", function(){
                $.ajax({
                    type: "GET",
                    url: "LeavingCustomer",
                    async: false,
                    
                    success: function(data){
                        console.log(data);
                    }
                });
            });
        });
    </script>
    </body>
</html>
