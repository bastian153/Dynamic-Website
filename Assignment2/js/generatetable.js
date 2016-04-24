var http;

function main(cfunc) {
	xhttp = new XMLHttpRequest();
	xhttp.open("GET", "content.php", true);
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState == 4 && xhttp.status == 200){
			cfunc(xhttp.responseText);
		}
	};
	xhttp.send();
}

function displayProducts(response){
	document.getElementById("products").innerHTML = response;
}
