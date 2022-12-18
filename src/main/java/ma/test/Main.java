//package ma.test;
//
//import java.util.Hashtable;
//import java.util.List;
//import javax.ejb.EJB;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//
//import ma.dao.RestaurantRemote;
//import ma.entites.Restaurant;
//
//public class Main {
//
//	public static RestaurantRemote lookUpBanqueRemote() throws NamingException {
//		Hashtable<Object, Object> jndiProperties = new Hashtable<Object, Object>();
//
//		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
//		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
//		final Context context = new InitialContext(jndiProperties);
//
//		return (RestaurantRemote) context.lookup("ejb:/restaurant/Restaurant!mafile:///home/Nyanpasu/Desktop/code/myspace/RestauCtl/src/main/java/ma/test/Main.java.metier.RestaurantRemote");
//
//	}
//	
//
//    public static void main(String[] args) {
//    	RestaurantRemote restaurantEJB;
//		try {
//			restaurantEJB = lookUpBanqueRemote();
//			   // Create a new restaurant
//	        Restaurant restaurant = new Restaurant();
//	        restaurant.setNom("My Restaurant");
//	        restaurant.setX(123.45);
//	        restaurant.setY(678.90);
//
//	        // Add the restaurant
//	        restaurant = restaurantEJB.addRestaurant(restaurant);
//
//	        // Get the list of restaurants
//	        List<Restaurant> restaurants = restaurantEJB.listRestaurants();
//	        for (Restaurant r : restaurants) {
//	            System.out.println(r.getNom());
//	        }
//
//	        // Get a specific restaurant
//	        int code = 1;
//	        Restaurant r = restaurantEJB.getRestaurant(code);
//	        System.out.println(r.getNom());
//
//	        // Delete a restaurant
//	        int deleted = restaurantEJB.deleteRestaurant(code);
//	        System.out.println(deleted);
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//     
//    }
//}
