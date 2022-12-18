package ma.test;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ma.dao.ResaturantOnPremise;
import ma.entites.Restaurant;

@WebServlet("/RestaurantController")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@EJB		
	ResaturantOnPremise<Restaurant> restaurantEJB;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("op").equals("add")) {
			String nom = request.getParameter("nom");
			double x = Double.parseDouble(request.getParameter("x"));
			double y = Double.parseDouble(request.getParameter("y"));
			String dateOuverture = request.getParameter("heureOuverture");
			String dateFermeture = request.getParameter("heureOuverture");

			Restaurant restaurant = new Restaurant();
			restaurant.setNom(nom);
			restaurant.setX(x);
			restaurant.setY(y);
			restaurant.setHeureOuverture(dateOuverture);
			restaurant.setHeureFemeture(dateFermeture);
			restaurantEJB.add(restaurant);
		}
		else if (request.getParameter("op").equals("ed")) {
			String code = request.getParameter("code");
			String nom = request.getParameter("nom");
			double x = Double.parseDouble(request.getParameter("x"));
			double y = Double.parseDouble(request.getParameter("y"));
			String dateOuverture = request.getParameter("heureOuverture");
			String dateFermeture = request.getParameter("heureOuverture");
			Restaurant restaurant = new Restaurant();
			restaurant.setId(Integer.parseInt(code));
			restaurant.setNom(nom);
			restaurant.setX(x);
			restaurant.setY(y);
			restaurant.setHeureOuverture(dateOuverture);
			restaurant.setHeureFemeture(dateFermeture);
			restaurantEJB.edit(restaurant);
		}
		else if (request.getParameter("op").equals("del")) {
			System.out.println("DELETED");
			String code = request.getParameter("code");
			restaurantEJB.delete(Integer.parseInt(code));
		}
		System.out.println(request.getParameter("op"));
		response.setContentType("application/json");
		List<Restaurant> restaurants = restaurantEJB.getAll();
		Gson json = new Gson();
		response.getWriter().write(json.toJson(restaurants));
	}
}
