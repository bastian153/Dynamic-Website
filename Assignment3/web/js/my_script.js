function createState() {
	var stateList = ["AK","AL","AR","AZ","CA","CO","CT","DC","DE","FL","GA","GU","HI","IA","ID", "IL","IN","KS","KY","LA","MA","MD","ME","MH","MI","MN","MO","MS","MT","NC","ND","NE","NH","NJ","NM","NV","NY", "OH","OK","OR","PA","PR","PW","RI","SC","SD","TN","TX","UT","VA","VI","VT","WA","WI","WV","WY"];
	select = document.getElementById('selectElementId');

	for(var i = 0; i < stateList.length; i++){
		var opt = document.createElement('option');
		opt.value = stateList[i];
		opt.innerHTML = stateList[i];
		select.appendChild(opt);
	}
}

function createMonth() {
    var month = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'];
    var select = document.getElementById("month");

    for(var i = 0; i < month.length; i++){
        var opt = document.createElement('option');
        opt.value = i+1;
        opt.innerHTML = month[i];
        select.appendChild(opt);
    }
}

function createCartTable(){
    $.ajax({
        type: "GET",
        url: "CartTable",
        datatype: "text/plain",
        
        success: function(data){
            var e = document.getElementById("checkoutCart");
            e.innerHTML = data;
            console.log(data);
        },
        error: function(){
            console.log("Servlet Error");
        }
    });
}


function main(){
    createState();
    createMonth();
    createCartTable();
}