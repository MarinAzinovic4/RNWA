function showHint(str) {
    if (str.length == 0) { 
        $("#txtHint").html("");
        return;
    } else {
		$.get("php/productGetter.php?searchFor=" + str, function(data) {
			data = data.split("||");
			//removes the last element, which is "||"
			data.pop();
			
			if(data.length != 0) {
				$("#emptyRes").html("");
				$("#txtHint").html("<tr><th>RESULT</th><th>DETAILS</th><th>UPDATE</th><th>DELETE</th></tr>");
				data.forEach(item => {
					$("#txtHint").html( $("#txtHint").html() + "<tr><td>" + item + "<td><a href='#'>DETAILS</a></td><td><a href='#'>UPDATE</a></td><td><a href='#'>DELETE</a></td></tr>");
				});
			} else {
				$("#emptyRes").html("Ne podudara se nista sa tom pretragom.");
				$("#txtHint").html("");
			}
		});
    }
}
