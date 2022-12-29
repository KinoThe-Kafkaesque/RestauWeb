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

import ma.dao.CategoryOnPremise;
import ma.dao.QuartierOnPremise;
import ma.dao.RestaurantOnPremise;
import ma.entites.Category;
import ma.entites.Quartier;
import ma.entites.Restaurant;

@WebServlet("/RestaurantController")
public class RestaurantController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@EJB		
	RestaurantOnPremise<Restaurant> restaurantEJB;
	@EJB		
	CategoryOnPremise<Category> categoryEJB;
	@EJB		
	QuartierOnPremise<Quartier> quartierEJB;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("op").equals("add")) {
			String nom = request.getParameter("nom");
			double x = Double.parseDouble(request.getParameter("x"));
			double y = Double.parseDouble(request.getParameter("y"));
			String dateOuverture = request.getParameter("heureOuverture");
			String dateFermeture = request.getParameter("heureOuverture");
			int categoryId = Integer.parseInt(request.getParameter("category"));
			int quarterId = Integer.parseInt(request.getParameter("quartier"));

			Category category = categoryEJB.get(categoryId);
			Quartier quartier = quartierEJB.get(quarterId);
			Restaurant restaurant = new Restaurant();

			restaurant.setCategory(category);
			restaurant.setQuartier(quartier);
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
			int categoryId = Integer.parseInt(request.getParameter("category"));
			int quarterId = Integer.parseInt(request.getParameter("quartier"));
			Category category = categoryEJB.get(categoryId);
			Quartier quartier = quartierEJB.get(quarterId);
			Restaurant restaurant = new Restaurant();
			restaurant.setCategory(category);
			restaurant.setQuartier(quartier);
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
		Jsonb json = JsonbBuilder.create();
		response.getWriter().write(json.toJson(restaurants));
				}
}
