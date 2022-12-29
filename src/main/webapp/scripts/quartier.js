function edit(id, nom) {

	$("#id").val(id)
	$("#nom").val(nom)

}
function del(e) {
	$.ajax({
		url: "QuartierController",
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
		ligne += "<tr><td>" + e.id + "</td><td>" + e.nom +
			"</td><td><button onClick= \"del(" + e.id +
			")\">Supprimer</button> </td><td><button onClick=\"edit(" +
			e.id + ",'" + e.nom + "'," +
			")\">Modifier</button></td></tr>";
	});
	$("#content").html(ligne);

}
$(document).ready(function() {


	$.ajax({
		url: "QuartierController",
		data: { op: "load" },
		method: "GET",
		success: function(data) {
			$.ajax({
				url: 'http://localhost:8080/restauWebRs/ville',
				success: function(villes) {
					var $villeSelect = $('#ville');
					$villeSelect.empty(); // clear any existing options
					villes.forEach(function(ville) {
						$villeSelect.append('<option value="' + ville.id + '">' + ville.nom + '</option>');
					});
				}
			});
			remplir(data);
		}
	});

	$("#add").click(function() {
		var nom = $("#nom").val();
		const code = $("#id").val();
		const ville = $("#ville").find(":selected").val();

		if (code) {
			$.ajax({
				url: "QuartierController",
				data: { op: "ed", code: code, nom: nom, ville: ville },
				method: "GET",
				success: function(data) {
					remplir(data);
				}
			});

		}
		else {
			$.ajax({
				url: "QuartierController",
				data: { nom: nom , ville : ville },
				method: "GET",
				success: function(data) {
					remplir(data);
				}
			});
		}
	});


	//
});