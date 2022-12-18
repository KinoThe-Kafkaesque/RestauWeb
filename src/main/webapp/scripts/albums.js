function edit(id, nom, x, y, heureOuverture, heureFemeture) {

	code.value = id
	nom.value = nom
	x.value = x
	y.value = y
	heureOuverture.value = heureOuverture
	heureFemeture.value = heureFemeture

}
function del(e) {
	$.ajax({
		url: "CompteController",
		data: { op: "del", code: code.value },
		method: "POST",
		success: function(data) {
			remplir(data);
		}
	});

}
function remplir(data) {
	var ligne = "";
	data.forEach(e => {
		ligne += "<tr><td>" + e.id + "</td><td>" + e.nom + "</td><td>" + e.x +
			"</td><td>" + e.y + "</td><td>" + e.heureOuverture + "</td><td>" + e.heureFemeture +
			"</td><td><button onClick= \"del(" + e.id +
			")\">Supprimer</button> </td><td><button onClick=\"edit(" +
			e.id + "," + e.nom + "," + e.x + "," + e.y + "," + e.heureOuverture + "," + e.heureFemeture +
			")\">Modifier</button></td></tr>";
	});
	$("#content").html(ligne);

}
$(document).ready(function() {


	$.ajax({
		url: "AlbumController",
		data: { op: "load" },
		method: "POST",
		success: function(data) {
			remplir(data);
		}
	});

	$("#add").click(function() {
		var nom = $("#nom").val();
		var x = $("#x").val();
		var y = $("#y").val();
		var heureOuverture = $("#heureOuverture").val();
		var heureFemeture = $("#heureFemeture").val();
		if (code.value) {
			$.ajax({
				url: "CompteController",
				data: { op: "ed", code: code.value, nom: nom, x: x, y: y, heureOuverture: heureOuverture, heureFemeture: heureFemeture },
				method: "POST",
				success: function(data) {
					remplir(data);
				}
			});

		}
		else {
			$.ajax({
				url: "CompteController",
				data: { nom: nom, x: x, y: y, heureOuverture: heureOuverture, heureFemeture: heureFemeture },
				method: "POST",
				success: function(data) {
					remplir(data);
				}
			});
		}
	});


	//
});