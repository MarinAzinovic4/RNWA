function showHint(str) {
    if (str.length == 0) { 
        document.getElementById("txtHint").innerHTML = "";
        return;
    } else {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {			
				let res = xmlhttp.responseText;
				let arrRes = res.split("||");
				//removes the last element, which is "||"
				arrRes.pop();
				
				if(arrRes.length != 0) {
					document.getElementById("emptyRes").innerText = "";
					document.getElementById("txtHint").innerHTML = "<tr><th>RESULT</th><th>DETAILS</th><th>UPDATE</th><th>DELETE</th></tr>";
					arrRes.forEach(item => {
						document.getElementById("txtHint").innerHTML += "<tr><td>" + item + "<td><a href='#'>DETAILS</a></td><td><a href='#'>UPDATE</a></td><td><a href='#'>DELETE</a></td></tr>";
					});
				} else {
					document.getElementById("emptyRes").innerText = "Ne podudara se nista sa tom pretragom.";
					document.getElementById("txtHint").innerText = "";
				}
				

            }
        }
        xmlhttp.open("GET", "php/productGetter.php?searchFor=" + str, true);
        xmlhttp.send();
    }
}
