function addingToCart(isbn){    
    $.ajax({
        type: "POST",
        url: "AddToCart",
        datatype: "text/plain",
        data: {"product": isbn},
        
        success: function(data){
            var checkoutHeader = document.getElementById("itemsInCart");
            checkoutHeader.innerHTML = data;
            var addToCartDiv = document.getElementById("divAddToCart");
            addToCartDiv.innerHTML = "<h5>Item added to cart</h5>";
        }
    });
}