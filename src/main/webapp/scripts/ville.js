function edit(id, nom) {

	$("#id").val(id)
	$("#nom").val(nom)

}
function del(e) {
	$.ajax({
		url: "VilleController",
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
		ligne += "<tr><td>" + e.id + "</td><td>" + e.nom  +
			"</td><td><button onClick= \"del(" + e.id +
			")\">Supprimer</button> </td><td><button onClick=\"edit(" +
			e.id + ",'" + e.nom + "'," +
			")\">Modifier</button></td></tr>";
	});
	$("#content").html(ligne);

}
$(document).ready(function() {


	$.ajax({
		url: "VilleController",
		data: { op: "load" },
		method: "GET",
		success: function(data) {
			remplir(data);
		}
	});

	$("#add").click(function() {
		var nom = $("#nom").val();
		const code = $("#id").val();

		if (code) {
			$.ajax({
				url: "VilleController",
				data: { op: "ed", code: code, nom: nom },
				method: "GET",
				success: function(data) {
					remplir(data);
				}
			});

		}
		else {
			$.ajax({
				url: "VilleController",
				data: {nom: nom },
				method: "GET",
				success: function(data) {
					remplir(data);
				}
			});
		}
	});


	//
});