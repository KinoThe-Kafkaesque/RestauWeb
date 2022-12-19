function edit(id, restaurant, photo) {
	$("#id").val(id)
	$("#photo").val(photo)
	$("#restaurant").val(restaurant)
}
function del(e) {
	$.ajax({
		url: "AlbumController",
		data: { op: "del", code: e },
		method: "GET",
		success: function(data) {
			remplir(data);
		}
	});

}
function remplir(data) {
	var ligne = "";
	data.forEach(e => {
		ligne += "<tr><td>" + e.id + "</td><td>" +
			"<img src='" + e.photo + "' alt='Photo'>" + "</td><td>" + e.restaurant +
			"</td><td><button onClick= \"del(" + e.id +
			")\">Supprimer</button> </td><td><button onClick=\"edit(" +
			e.id + ",'" + e.restaurant + "'," + + ",'" + e.photo + "'," +
			")\">Modifier</button></td></tr>";
	});
	$("#content").html(ligne);

}
$(document).ready(function() {


	$.ajax({
		url: "AlbumController",
		data: { op: "load" },
		method: "GET",
		success: function(data) {
			remplir(data);
		}
	});

	$("#add").click(function() {
		var nom = $("#nom").val();
		const code = $("#id").val();
		const fileInput = $("#photo")[0];
		const file = fileInput.files[0];
		// Create a FormData object
		const formData = new FormData();

		// Add the file to the form data
		formData.append("photo", file);
		if (code) {
			formData.append("id", code);
			formData.append("op", "ed");
			$.ajax({
				url: "AlbumController",
				data: formData,
				method: "GET",
				contentType: false,
				processData: false,
				success: function(data) {
					remplir(data);
				}
			});

		}
		else {
			$.ajax({
				url: "AlbumController",
				data: formData,
				method: "GET",
				contentType: false,
				processData: false,
				success: function(data) {
					remplir(data);
				}
			});
		}
	});


	//
});