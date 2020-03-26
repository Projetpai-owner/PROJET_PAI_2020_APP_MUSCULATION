function alimenterSelect(){
	$.ajax({
		url:'http://localhost:8080/getAllTypeSeance',
		type:'GET',
		dataType:'json',
		success : function(result) {
			console.log(result);
			$.each(result, function(key, value) {
				console.log(value);
				$("#typeSeance").append('<option value='+value.idSeance+'>'+value.libelle+'</option>');
			});
		}
	});
}

alimenterSelect();