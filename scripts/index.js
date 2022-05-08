function showHint(str) {
    if (str.length == 0) { 
        $("#txtHint").html("");
        return;
    } else {
		fetch("http://localhost:8080/department/get/name/" + str)
			.then(response => response.json())
			.then(data => {
			if(data.departments.length != 0) {
				$("#emptyRes").html("");
				$("#txtHint").html("<tr><th>RESULT</th><th>UPDATE</th><th>DELETE</th></tr>");
				data.departments.forEach(department => {
					var item = "<tr><td>" + department.name + `<td><a href='http://localhost/RNWA/views/editDepartment.html' onclick=foo(${department.id})>UPDATE</a></td><td><a href='#' onclick=deleteDepartment(${department.id})>DELETE</a></td></tr>`;
					$("#txtHint").html($("#txtHint").html() + item);
				});
			} else {
				$("#emptyRes").html("Ne podudara se nista sa tom pretragom.");
				$("#txtHint").html("");
			}
		});
    }
}


function addDepartment() {
	let deptName = document.getElementById("deptName").value;
	let groupName = document.getElementById("groupName").value;
		
	axios.post("http://localhost:8080/department/add", {name: deptName, groupName: groupName})
		.then(res => {
			alert("Department created successfully");
			document.getElementById("addDeptForm").reset();
		})
		.catch(e => {
			console.log(e);
		});
}


function getDepartment(id) {
	fetch("http://localhost:8080/department/get/name/" + id)
		.then(res => res.json())
		.then(data => {
			console.log(data);
		});
}


function deleteDepartment(id) {
	axios.delete("http://localhost:8080/department/delete/" + id)
		.then(res => {
			alert("Department deleted successfully");
			window.location.reload();
		}
	);
}
