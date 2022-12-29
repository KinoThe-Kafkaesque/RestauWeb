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
			e.id + ",'" + e.restaurant + "','" + e.photo +
			"')\">Modifier</button></td></tr>";
	});
	$("#content").html(ligne);
}
$(document).ready(function() {


	$.ajax({
		url: "AlbumController",
		data: { op: "load" },
		method: "GET",
		success: function(data) {
			$.ajax({
				url: 'http://localhost:8080/restauWebRs/restaurant',
				success: function(restaurants) {
					var $restaurantSelect = $('#restaurant');
					$restaurantSelect.empty(); // clear any existing options
					restaurants.forEach(function(restaurant) {
						$restaurantSelect.append('<option value="' + restaurant.id + '">' + restaurant.nom + '</option>');
					});
				}
			});
			remplir(data);
		}
	});

	$("#add").click(function() {
		const code = $("#id").val();
		const restaurant = $("#restaurant").val();
		const photoUrl = $("#photo").val();
		if (code) {
			$.ajax({
				url: "AlbumController",
				data: { op: "ed", id: code, restaurant: restaurant, photo: photoUrl },
				method: "GET",
				success: function(data) {
					remplir(data);
				}
			});
		} else {
			$.ajax({
				url: "AlbumController",
				data: { op: "add", restaurant: restaurant, photo: photoUrl },
				method: "GET",
				success: function(data) {
					remplir(data);
				}
			});
		}
	});


	//
});