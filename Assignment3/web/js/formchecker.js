// Section 1
function checkBillingInformation(){
    var firstN = document.forms["Billing-Info"]["firstName"].value;
    var lastN = document.forms["Billing-Info"]["lastName"].value;
    var addr = document.forms["Billing-Info"]["address"].value;
    var city = document.forms["Billing-Info"]["city"].value;
    var state = document.getElementById("selectElementId").options;
    var postalCode = document.forms["Billing-Info"]["areaCode"].value;

    // Check Regex
    var first = new RegExp("([a-zA-Z]+)");
    var last = new RegExp("([a-zA-Z]+)");
    var address = new RegExp("^([0-9]+ )[a-zA-Z. ]+$");
    var r_city = new RegExp("([a-zA-Z]+)");
    var zip = new RegExp("^([0-9]{5}(-[0-9]{4})?)$");

    var firstReg = first.test(firstN);
    var lastReg = last.test(lastN);
    var addrReg = address.test(addr);
    var cityReg = r_city.test(city);
    var zipReg = zip.test(postalCode);

    // Check empty input field
    var check = 0;
    if(!firstReg){
        document.forms["Billing-Info"]["firstName"].style.borderColor="#FD8182";
        document.getElementById("first-name-error").style.visibility = "visible";
        check = 1;
    } else {
        document.forms["Billing-Info"]["firstName"].style.border = "1px solid #ccc";
        document.getElementById("first-name-error").style.visibility = "hidden";
    }


    if(!lastReg){
        document.forms["Billing-Info"]["lastName"].style.borderColor="#FD8182";
        document.getElementById("last-name-error").style.visibility = "visible";
        check = 1;
    } else {
        document.forms["Billing-Info"]["lastName"].style.border = "1px solid #ccc";
        document.getElementById("last-name-error").style.visibility = "hidden";
    }


    if(!addrReg){
        document.forms["Billing-Info"]["address"].style.borderColor="#FD8182";
        document.getElementById("address-error").style.visibility = "visible";
        check = 1;
    } else {
        document.forms["Billing-Info"]["address"].style.border = "1px solid #ccc";
        document.getElementById("address-error").style.visibility = "hidden";
    }

    if(!cityReg){
        document.forms["Billing-Info"]["city"].style.borderColor="#FD8182";
        document.getElementById("city-error").style.visibility = "visible";
        check = 1;
    } else {
        document.forms["Billing-Info"]["city"].style.border = "1px solid #ccc";
        document.getElementById("city-error").style.visibility = "hidden";
    }

    if(state[state.selectedIndex].value === ""){
        document.forms["Billing-Info"]["state"].style.borderColor="#FD8182";
        document.getElementById("state-error").style.visibility = "visible";
        check = 1;
    } else {
        document.forms["Billing-Info"]["state"].style.border = "1px solid #ccc";
        document.getElementById("state-error").style.visibility = "hidden";
    }


    if(!zipReg){
        document.forms["Billing-Info"]["areaCode"].style.borderColor="#FD8182";
        document.getElementById("zip-code-error").style.visibility = "visible";
        check = 1;
    } else {
        document.forms["Billing-Info"]["areaCode"].style.border = "1px solid #ccc";
        document.getElementById("zip-code-error").style.visibility = "hidden";
    }
    return check;
}



// Section 2
function checkCreditCard(){
    var creditcard = document.forms["Creditcard-Info"]["card"].value;
    var csv_num = document.forms["Creditcard-Info"]["csv"].value;

    // Check regex
    var cardNumber = new RegExp("^([0-9]{4}-?[0-9]{4}-?[0-9]{4}-?[0-9]{4})$");
    var csv = new RegExp("^([0-9]{3})$");
    cardNumber.test(creditcard);
    csv.test(csv_num);

    // Check Month and year
    var month = document.getElementById("month").options;
    var year = document.getElementById("year").options;

    var check = 0;
    if(creditcard === null || creditcard === ""){
        document.forms["Creditcard-Info"]["card"].style.borderColor="#FD8182";
        document.getElementById("credit-card-error").style.visibility = "visible";
        check = 1;
    } else {
        document.forms["Creditcard-Info"]["card"].style.border = "1px solid #ccc";
        document.getElementById("credit-card-error").style.visibility = "hidden";
    }


    if(csv_num === null || csv_num === ""){
        document.forms["Creditcard-Info"]["csv"].style.borderColor="#FD8182";
        document.getElementById("csv-error").style.visibility = "visible";
        check = 1;
    } else {
        document.forms["Creditcard-Info"]["csv"].style.border = "1px solid #ccc";
        document.getElementById("csv-error").style.visibility = "hidden";
    }

    if(month[month.selectedIndex].value === ''){
        document.forms["Creditcard-Info"]["month"].style.borderColor="#FD8182";
        check = 1;
    } else {
        document.forms["Creditcard-Info"]["month"].style.border = "1px solid #ccc";
    }

    if(year[year.selectedIndex].value === ''){
        document.forms["Creditcard-Info"]["year"].style.borderColor="#FD8182";
        check = 1;
    } else {
        document.forms["Creditcard-Info"]["year"].style.border = "1px solid #ccc";
    }
    return check;
}



function checkValidation(){
    var check1 = checkBillingInformation();
    var check2 = checkCreditCard();

    var firstN = document.forms["Billing-Info"]["firstName"].value;
    var lastN = document.forms["Billing-Info"]["lastName"].value;
    var addr = document.forms["Billing-Info"]["address"].value;
    var city = document.forms["Billing-Info"]["city"].value;
    var state = document.getElementById("selectElementId").value;
    var postalCode = document.forms["Billing-Info"]["areaCode"].value;


    if((check1 + check2) !== 0){
        document.getElementById("submit-error").style.visibility = "visible";
    }

    else{
        document.getElementById("submit-error").style.visibility = "hidden";
        var orderId;
        $.ajax({
            type: "POST",
            url: "AddOrderToDB",
            datatype: "text/plain",
            async: false,
            data: {"firstN" : firstN, "lastN" : lastN, "addr" : addr, "city" : city, "state" : state,
                   "postalCode" : postalCode},

            success: function(data){
                orderId = parseInt(data);
            }
        });


        //var imagePath = "";
        var isbn = "";
        var quantity = "";

        var table = document.getElementById('checkoutCart');

        var rowLength = table.rows.length;

        for(var i=0; i<rowLength; i+=1){
            var row = table.rows[i];
            var cellLength = row.cells.length;

            for(var y=0; y<cellLength; y+=1){
                var cell = row.cells[y];
                var child = cell.childNodes[0];
                if (child.tagName === "P" && y === 3){
                    quantity = child.innerHTML;
                } else if (y === 4){
                    var onClickText = cell.childNodes[2].getAttribute('onclick').split("\'");
                    isbn = onClickText[7];
                }
            }

            $.ajax({
                type: "POST",
                url: "CompleteCart",
                datatype: "text/plain",
                data: {"isbn" : isbn, "quantity" : quantity, "orderId" : orderId},
                success: function(){
                }
            });
        }
    }
}

