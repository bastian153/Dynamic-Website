function addingToCart(isbn, price, src, name){   
    $.ajax({
        type: "POST",
        url: "AddToCart",
        datatype: "text/plain",
        data: {"product": isbn, "cost" : price, "src": src,
               "name": name},
        
        success: function(size){
            var checkoutHeader = document.getElementById("itemsInCart");
            checkoutHeader.innerHTML = size;
            var addToCartDiv = document.getElementById("divAddToCart");
            addToCartDiv.innerHTML = "<h5>Item added to cart</h5>";
        }
    });
}