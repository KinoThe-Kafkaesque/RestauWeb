package ma.test;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ma.entites.Restaurant;
import ma.metier.RestaurantRemote;
@WebServlet("/CompteController")
public class Controller extends HttpServlet {

	public static RestaurantRemote lookUpBanqueRemote() throws NamingException {
		Hashtable<Object, Object> jndiProperties = new Hashtable<Object, Object>();

		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);

		return (RestaurantRemote) context.lookup("ejb:/restaurant/Restaurant!ma.metier.RestaurantRemote");

	}

	RestaurantRemote restaurantEJB;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			restaurantEJB = lookUpBanqueRemote();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (request.getParameter("op") == null) {
			String solde = request.getParameter("solde");
			String dateCreate = request.getParameter("dateCreation").replace("-", "/");
			Restaurant restaurant = new Restaurant();
			restaurantEJB.addRestaurant(restaurant);
		}
//		if (request.getParameter("op").equals("ed")) {
//			String code = request.getParameter("code");
//			String solde = request.getParameter("solde");
//			String dateCreate = request.getParameter("dateCreation").replace("-", "/");
//			Restaurant restaurant = new Restaurant();
//			restaurant.setId(Integer.parseInt(code));
//			restaurantEJB.editRestaurant(restaurant);
//		}
		if (request.getParameter("op").equals("del")) {
			System.out.println("DELETED");
			String code = request.getParameter("code");
			restaurantEJB.deleteRestaurant(Integer.parseInt(code));
		}
		System.out.println(request.getParameter("op"));
		response.setContentType("application/json");
		List<Restaurant> restaurants = restaurantEJB.listRestaurants();
		Gson json = new Gson();
		response.getWriter().write(json.toJson(restaurants));
	}

}
