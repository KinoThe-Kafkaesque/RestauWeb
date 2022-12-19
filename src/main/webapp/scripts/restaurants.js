// Get the list of quarters
let quartiers = [];

// Get the dropdown menu element
const $quartierSelect = $('#quartier');
function options(data) {
	quartiers = [...data.quartiers]
$.each(quartiers, function(index, quartier) {
	// Create an option element
	const $option = $('<option>');
	$option.val(quartier.id);
	$option.text(quartier.nom);

	// Add the option to the dropdown menu
	$quarterSelect.append($option);
});
}

function edit(id, nom, x, y, heureOuverture, heureFemeture) {
	$("#id").val(id)
	$("#nom").val(nom)
	$("#x").val(x)
	$("#y").val(y)
	$("#heureOuverture").val(heureOuverture)
	$("#heureFemeture").val(heureFemeture)
}
function del(e) {
	$.ajax({
		url: "RestaurantController",
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
		ligne += "<tr><td>" + e.id + "</td><td>" + e.nom + "</td><td>" + e.x +
			"</td><td>" + e.y + "</td><td>" + e.heureOuverture + "</td><td>" + e.heureFemeture +
			"</td><td><button onClick= \"del(" + e.id +
			")\">Supprimer</button> </td><td><button onClick=\"edit('" +
			e.id + "','" + e.nom + "','" + e.x + "','" + e.y + "','" + e.heureOuverture + "','" + e.heureFemeture +
			"')\">Modifier</button></td></tr>";

		$("#content").html(ligne);
	});
}
$(document).ready(function() {


	$.ajax({
		url: "RestaurantController",
		data: { op: "load" },
		method: "GET",
		success: function(data) {
			remplir(data);
		}
	});

	$("#add").click(function() {
		const nom = $("#nom").val();
		const x = $("#x").val();
		const y = $("#y").val();
		const heureOuverture = $("#heureOuverture").val();
		const heureFemeture = $("#heureFemeture").val();
		const code = $("#id").val();
		if (code) {
			$.ajax({
				url: "RestaurantController",
				data: { op: "ed", code: code, nom: nom, x: x, y: y, heureOuverture: heureOuverture, heureFemeture: heureFemeture },
				method: "GET",
				success: function(data) {
					remplir(data);
				}
			});

		}
		else {
			$.ajax({
				url: "RestaurantController",
				data: { op: "add", nom: nom, x: x, y: y, heureOuverture: heureOuverture, heureFemeture: heureFemeture },
				method: "GET",
				success: function(data) {
					remplir(data);
				}
			});
		}
	});


	//
});