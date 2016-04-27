$(document).ready(function () {
	$("#selectStateId").focus(function(){
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

	$("#selectStateId").on('change', function(){
		var stateID = $(this).val();
		if(stateID){
			$.ajax({
				url: "city.php",
				method: "GET",
				data: "state_id="+stateID,
				dataType: 'json',
				success: function(data){
					$("#selectCityId").empty();
					for(var i = 0; i < data.length; i++){
						var text = document.createElement("option");
						text.innerHTML = data[i]['city'];
						text.value = data[i]['city'];
						$("#selectCityId").append(text);
					}
				}
			})
		}
	});

	$("#selectCityId").on('change', function(){
		var cityID = $(this).val();
		if(cityID){
			$.ajax({
				url: "zip.php",
				method: "GET",
				data: "city_id="+cityID,
				dataType: 'json',
				success: function(data){
					$("#selectZipId").empty();
					for(var i = 0; i < data.length; i++){
						var text = document.createElement("option");
						text.innerHTML = data[i]['zip'];
						text.value = data[i]['city'];
						$("#selectZipId").append(text);
					}
				}
			})
		}
	})

})