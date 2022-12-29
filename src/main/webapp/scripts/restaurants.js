

function edit(id, nom, x, y, heureOuverture, heureFemeture, cId, cNom, qId, qNom) {
	$("#id").val(id)
	$("#nom").val(nom)
	$("#x").val(x)
	$("#y").val(y)
	$("#heureOuverture").val(heureOuverture)
	$("#heureFemeture").val(heureFemeture)
	$("#category").find(":selected").val(cId);
	$("#quartier").find(":selected").val(qId);
	$("#category").find(":selected").text(cNom);
	$("#quartier").find(":selected").text(qNom);
	console.log($("#quartier").find(":selected").text(), qNom)
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
			"</td><td>" + e.quartier.nom + "</td><td>" + e.category.nom +
			"</td><td><button onClick= \"del(" + e.id +
			")\">Supprimer</button> </td><td><button onClick=\"edit('" +
			e.id + "','" + e.nom + "','" + e.x + "','" + e.y + "','" + e.heureOuverture + "','" + e.heureFemeture + "','" + e.category.id + "','" + e.category.nom +
			"','" + e.quartier.id + "','" + e.quartier.nom + "')\">Modifier</button></td></tr>";


		$("#content").html(ligne);
	});
}
$(document).ready(function() {
	$.ajax({
		url: "RestaurantController",
		data: { op: "load" },
		method: "GET",
		success: function(data) {
			$.ajax({
				url: 'http://localhost:8080/restauWebRs/category',
				success: function(categories) {
					var $categorySelect = $('#category');
					$categorySelect.empty(); // clear any existing options
					categories.forEach(function(category) {
						$categorySelect.append('<option value="' + category.id + '">' + category.nom + '</option>');
					});
				}
			});

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
	$('#ville').change(function() {
		var villeId = $(this).val();
		$.ajax({
			url: 'http://localhost:8080/restauWebRs/ville/' + villeId,
			success: function(ville) {
				var $quartierSelect = $('#quartier');
				$quartierSelect.empty(); // clear any existing options
				ville.quartiers.forEach(function(quartier) {
					$quartierSelect.append('<option value="' + quartier.id + '">' + quartier.nom + '</option>');
				});
			}
		});
	});

	$("#add").click(function() {
		const nom = $("#nom").val();
		const x = $("#x").val();
		const y = $("#y").val();
		const heureOuverture = $("#heureOuverture").val();
		const heureFemeture = $("#heureFemeture").val();
		const code = $("#id").val();
		const category = $("#category").find(":selected").val();
		const quartier = $("#quartier").find(":selected").val();
		if (code) {
			$.ajax({
				url: "RestaurantController",
				data: { op: "ed", code: code, nom: nom, x: x, y: y, heureOuverture: heureOuverture, heureFemeture: heureFemeture, category: category, quartier: quartier },
				method: "GET",
				success: function(data) {
					remplir(data);
				}
			});

		}
		else {
			$.ajax({
				url: "RestaurantController",
				data: { op: "add", nom: nom, x: x, y: y, heureOuverture: heureOuverture, heureFemeture: heureFemeture, category: category, quartier: quartier },
				method: "GET",
				success: function(data) {
					remplir(data);
				}
			});
		}
	});


	//
});