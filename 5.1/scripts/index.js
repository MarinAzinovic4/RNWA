function showHint(str) {
    if (str.length == 0) { 
        $("#txtHint").html("");
        return;
    } else {
		fetch("http://localhost:8080/djelatnik/get/name/" + str)
			.then(response => response.json())
			.then(data => {
			if(data.djelatnici.length != 0) {
				$("#emptyRes").html("");
				$("#txtHint").html("<tr><th>RESULT</th><th>UPDATE</th><th>DELETE</th></tr>");
				data.djelatnici.forEach(djelatnik => {
					var item = "<tr><td>" + djelatnik.name + `<td><a href='http://localhost/RNWA/views/editDjelatnik.html' onclick=foo(${djelatnik.id})>UPDATE</a></td><td><a href='#' onclick=deleteDjelatnik(${djelatnik.id})>DELETE</a></td></tr>`;
					$("#txtHint").html($("#txtHint").html() + item);
				});
			} else {
				$("#emptyRes").html("Ne podudara se nista sa tom pretragom.");
				$("#txtHint").html("");
			}
		});
    }
}


function addDjelatnik() {
	let djelName = document.getElementById("djelName").value;
	let djelSurname = document.getElementById("djelSurname").value;
	let djelId = document.getElementById("djelId").value;
		
	axios.post("http://localhost:8080/djelatnik/add", {name: djelName, djelSurname, djelId: djelId})
		.then(res => {
			alert("Djelatnik created successfully ;)");
			document.getElementById("addDjelForm").reset();
		})
		.catch(e => {
			console.log(e);
		});
}

function addZupanija() {
	let zupName = document.getElementById("zupName").value;
	let zupId = document.getElementById("zupId").value;
		
	axios.post("http://localhost:8080/djelatnik/add", {name: zupName, zupId: zupId})
		.then(res => {
			alert("Zupanija created successfully ;)");
			document.getElementById("addZupForm").reset();
		})
		.catch(e => {
			console.log(e);
		});
}

function getDjelatnik(id) {
	fetch("http://localhost:8080/djelatnik/get/name/" + id)
		.then(res => res.json())
		.then(data => {
			console.log(data);
		});
}

function getZupanija(id) {
	fetch("http://localhost:8080/zupanija/get/name/" + id)
		.then(res => res.json())
		.then(data => {
			console.log(data);
		});
}

function deleteDjelatnik(id) {
	axios.delete("http://localhost:8080/djelatnik/delete/" + id)
		.then(res => {
			alert("Djelatnik deleted successfully");
			window.location.reload();
		}
	);
}

function deleteZupanija(id) {
	axios.delete("http://localhost:8080/zupanija/delete/" + id)
		.then(res => {
			alert("Zupanija deleted successfully");
			window.location.reload();
		}
	);
}