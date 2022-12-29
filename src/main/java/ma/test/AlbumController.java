package ma.test;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.dao.AlbumOnPremise;
import ma.dao.RestaurantOnPremise;
import ma.entites.Album;
import ma.entites.Restaurant;

@WebServlet("/AlbumController")
public class AlbumController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@EJB		
	AlbumOnPremise<Album> albumEJB;
	@EJB		
	RestaurantOnPremise<Restaurant> restaurantEJB;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("op").equals("add")) {
			String photo = request.getParameter("photo");
			Album album = new Album();
			album.setPhoto(photo);
			album.setRestaurant(restaurantEJB.get(Integer.parseInt(request.getParameter("restaurant"))));
			albumEJB.add(album);
		}
		else if (request.getParameter("op").equals("ed")) {
			String code = request.getParameter("code");
			String photo = request.getParameter("photo");
			Album album = new Album();
			album.setPhoto(photo);
			album.setId(Integer.parseInt(code));
			album.setRestaurant(restaurantEJB.get(Integer.parseInt(request.getParameter("restaurant"))));
			albumEJB.edit(album);
		}
		else if (request.getParameter("op").equals("del")) {
			System.out.println("DELETED");
			String code = request.getParameter("code");
			albumEJB.delete(Integer.parseInt(code));
		}
		System.out.println(request.getParameter("op"));
		response.setContentType("application/json");
		List<Album> albums = albumEJB.getAll();
		Jsonb json = JsonbBuilder.create();
		response.getWriter().write(json.toJson(albums));
	}
}
