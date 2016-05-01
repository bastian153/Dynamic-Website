$(document).ready(function () {
	getQueryStringParam();

	$.ajax({
		url: "states.php",
		method: "GET",
		dataType: 'json',
		success: function(data){
			for(var i = 0; i < data.length; i++){
				var text = document.createElement("option");
				text.innerHTML = data[i]['state'];
				text.value = data[i]['state'];
				$("#selectStateId").append(text);
			}
		}
	});

	$(function(){
		$("#city").autocomplete({
			source: function(request, response){
				$.ajax({
					url: "city.php",
					dataType: "json",
					data: {
						q: request.term
					},
					success: function(data){
						response(data);
					}
				});
			},
			minLength: 3
		})
	});

	$(function(){
		$("#zip").autocomplete({
			source: function(request, response){
				$.ajax({
					url: "zip.php",
					dataType: 'json',
					data: {
						q: request.term
					},
					success: function(data){
						response(data);
					}
				})
			}
		})
	})

	function getQueryStringParam(){
    var query = window.location['search'];
    var isbn = query.split("=")[1];
    document.forms["Book-Info"]["ISBN"].value = isbn;
  }

})
