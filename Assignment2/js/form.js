$(document).ready(function () {
	$("#selectStateId").on('focus', function(){
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
		})
	});

	// $("#selectStateId").on('change', function(){
	// 	var stateID = $(this).val();
	// 	if(stateID){
	// 		$.ajax({
	// 			url: "city.php",
	// 			method: "GET",
	// 			data: "state_id="+stateID,
	// 			dataType: 'json',
	// 			success: function(data){
	// 				// empty list of cities when state changes
	// 				$("#selectCityId").empty();
	// 				// empty list of zip codes when state changes
	// 				var zipText = document.createElement("option");
	// 				zipText.innerHTML = "Zip";
	// 				zipText.value = "";
	// 				$("#selectZipId").empty().append(zipText);
	// 				// append new new list of cities with new state
	// 				for(var i = 0; i < data.length; i++){
	// 					var text = document.createElement("option");
	// 					text.innerHTML = data[i]['city'];
	// 					text.value = data[i]['city'];
	// 					$("#selectCityId").append(text);
	// 				}
	// 			}
	// 		})
	// 	}
	// 	else{
	// 		var cityText = document.createElement("option");
	// 		cityText.innerHTML = "City";
	// 		cityText.value = "";
	// 		var zipText = document.createElement("option");
	// 		zipText.innerHTML = "Zip";
	// 		zipText.value = "";
	// 		$("#selectCityId").empty().append(cityText);
	// 		$("#selectZipId").empty().append(zipText);
	// 	}
	// });

	// $("#selectCityId").on('change', function(){
	// 	var cityID = $(this).val();
	// 	//console.log(cityID);
	// 	if(cityID){
	// 		$.ajax({
	// 			url: "zip.php",
	// 			method: "GET",
	// 			data: "city_id="+cityID,
	// 			dataType: 'json',
	// 			success: function(data){
	// 				$("#selectZipId").empty();
	// 				for(var i = 0; i < data.length; i++){
	// 					var text = document.createElement("option");
	// 					text.innerHTML = data[i]['zip'];
	// 					text.value = data[i]['city'];
	// 					$("#selectZipId").append(text);
	// 				}
	// 			}
	// 		})
	// 	}
	// })

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

})
