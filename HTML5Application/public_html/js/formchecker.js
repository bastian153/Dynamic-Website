/*
 * 
 * To check if regular expression matches
 *      variable.test(stringToMatch);
 */


// Section 1
function checkBookInformation(){
    var isbn = new RegExp("^(([0-9]{3}-?)?[0-9]{10})$");
    isbn.test();
    
    //TODO: Check to see if a quantity is entered
}


// Section 2
function checkBillingInformation(){    
    //TODO: Get the first and last name, check to see if length > 0
    //TODO: Get address and city, check to see if length > 0
    // Might need regular expression for address?
    // Check to see if state selected is not the default value
    var zip = new RegExp("^([0-9]{5}(-[0-9]{4})?)$");
    zip.test();
}


// Section 3
// MIGHT NOT BE NEEDED?
function checkShippingInformation(){
    // Get the value of radio selection
}


// Section 4
function checkCreditCard(){
    var cardNumber = new RegExp("^([0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4})$");
    var csv = new RegExp("^([0-9]{3})$");
    
    /*
     * TODO: Get element ID for the number and csv and check to
     * see if correct. If not correct, change elements to indicate
     * wrong input
     */ 
    cardNumber.test();
    csv.test;
    
    // Check to see if month selected is not the default value
    // Check to see if year selected is not the default value
    
}