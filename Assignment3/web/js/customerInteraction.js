function addingToCart(isbn, price, src, name){   
    $.ajax({
        type: "POST",
        url: "AddToCart",
        datatype: "text/plain",
        data: {"product" : isbn, "cost" : price, "src": src,
               "name": name},
        
        success: function(size){
            var checkoutHeader = document.getElementById("itemsInCart");
            checkoutHeader.innerHTML = size;
            var addToCartDiv = document.getElementById("divAddToCart");
            addToCartDiv.innerHTML = "<h5>Item added to cart</h5>";
        }
    });
}



function updateQuantity(productPriceId, productQuantityId, productUpdateId, isbn){
    var price = document.getElementById(productPriceId);
    var quantity = document.getElementById(productQuantityId);
    var update = document.getElementById(productUpdateId);
    
    $.ajax({
        type: "GET",
        url: "QuantityUpdater",
        datatype: "text/plain",
        data: {"isbn" : isbn, "quantity" : update.value},
        
        success: function(data){
            var beforePrice = price.innerHTML;
            quantity.innerHTML = update.value;
            price.innerHTML = parseFloat(data).toFixed(2);
            var difference = price.innerHTML - beforePrice;
            var totalPrice = document.getElementById("total_price");
            totalPrice.innerHTML = (parseFloat(totalPrice.innerHTML) + difference).toFixed(2);
        }
    });
}