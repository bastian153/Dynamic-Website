/*
 * 
 * To check if regular expression matches
 *      variable.test(stringToMatch);
 */


// Section 1
function checkBookInformation(){
    var isbn_input = document.forms["Book-Info"]["ISBN"].value;
    var quan_input = document.forms["Book-Info"]["Quantity"].value;

    // Check Regex
    var isbn = new RegExp("^(([0-9]{3}-?)?[0-9]{10})$");
    var reg = isbn.test(isbn_input);
    
    // Check empty input field
    var check = 0;
    if(isbn_input == null || isbn_input == ""){
        document.forms["Book-Info"]["ISBN"].style.borderColor="#FD8182";
        check = 1;
    }
    if(quan_input == null || quan_input == ""){
        document.forms["Book-Info"]["Quantity"].style.borderColor="#FD8182";
        check = 1;
    }
    return check;
}


// Section 2
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
        check = 1;
    }
    if(!lastReg){
        document.forms["Billing-Info"]["lastName"].style.borderColor="#FD8182";
        check = 1;
    }
    if(!addrReg){
        document.forms["Billing-Info"]["address"].style.borderColor="#FD8182";
        check = 1;
    }
    if(!cityReg){
        document.forms["Billing-Info"]["city"].style.borderColor="#FD8182";
        check = 1;
    }
    if(state[state.selectedIndex].value === ""){
        document.forms["Billing-Info"]["state"].style.borderColor="#FD8182";
        check = 1;
    }
    if(!zipReg){
        document.forms["Billing-Info"]["areaCode"].style.borderColor="#FD8182";
        check = 1;
    }
    return check;
}


// Section 3
function checkShippingInformation(){
    // Get the value of radio selection
    var check = 0;
    var radioList = document.forms["Shipping-Info"].getElementsByTagName("input");
    for(var i = 0; i < radioList.length; i++){
        if(radioList[i].checked){
            return check;
        }
    }
    document.forms["Shipping-Info"].style.backgroundColor="#FD8182";
    return check+1;
}


// Section 4
function checkCreditCard(){
    var creditcard = document.forms["Creditcard-Info"]["card"].value;
    var csv_num = document.forms["Creditcard-Info"]["csv"].value;

    // Check regex
    var cardNumber = new RegExp("^([0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4})$");
    var csv = new RegExp("^([0-9]{3})$");
    cardNumber.test(creditcard);
    csv.test(csv_num);
    
    // Check Month and year
    var month = document.getElementById("month").options;
    var year = document.getElementById("year").options;
    
    var check = 0;
    if(creditcard == null || creditcard == ""){
        document.forms["Creditcard-Info"]["card"].style.borderColor="#FD8182";
        check = 1;
    }
    if(csv_num == null || csv_num == ""){
        document.forms["Creditcard-Info"]["csv"].style.borderColor="#FD8182";
        check = 1;
    }
    if(month[month.selectedIndex].value === ''){
        document.forms["Creditcard-Info"]["month"].style.borderColor="#FD8182";
        check = 1;
    }
    if(year[year.selectedIndex].value === ''){
        document.forms["Creditcard-Info"]["year"].style.borderColor="#FD8182";
        check = 1;
    }
    return check;
}

function checkValidation(){
    var check1 = checkBookInformation();
    var check2 = checkBillingInformation();
    var check3 = checkShippingInformation();
    var check4 = checkCreditCard();

    var isbn_input = document.forms["Book-Info"]["ISBN"].value;
    var quan_input = document.forms["Book-Info"]["Quantity"].value;

    var firstN = document.forms["Billing-Info"]["firstName"].value;
    var lastN = document.forms["Billing-Info"]["lastName"].value;
    var addr = document.forms["Billing-Info"]["address"].value;
    var city = document.forms["Billing-Info"]["city"].value;
    var state = document.getElementById("selectElementId").options;
    var postalCode = document.forms["Billing-Info"]["areaCode"].value;



    if((check1+check2+check3+check4) !== 0){
        console.log("Error");
    }
    else{
        var email = 'sample@gmail.com';
        var subject = 'Test';
        var emailBody = 'ISBN: ' + isbn_input + "%0A"
                       +'Quantity: ' + quan_input + "%0A%0A"
                       +'Shipping Information:' + "%0A" 
                       +'First Name: ' + firstN + "%0A"
                       +'Last Name: ' + lastN + "%0A"
                       +'Address: ' + addr + "%0A"
                       +'City: ' + city + "%0A"
                       +'State: ' + state + "%0A"
                       +'Zip Code: ' + postalCode + "%0A%0A";
        window.location = "mailto:" + email + "?subject=" + subject + "&body=" + emailBody;
    }
}