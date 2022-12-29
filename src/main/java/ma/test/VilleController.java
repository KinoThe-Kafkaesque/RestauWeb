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


import ma.dao.VilleOnPremise;
import ma.entites.Ville;

@WebServlet("/VilleController")
public class VilleController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@EJB		
	VilleOnPremise<Ville> villeEJB;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("op").equals("add")) {
			String nom = request.getParameter("nom");
			Ville ville = new Ville();
			ville.setNom(nom);
			villeEJB.add(ville);
		}
		else if (request.getParameter("op").equals("ed")) {
			String code = request.getParameter("code");
			String nom = request.getParameter("nom");
			Ville ville = new Ville();
			ville.setId(Integer.parseInt(code));
			ville.setNom(nom);
			villeEJB.edit(ville);
		}
		else if (request.getParameter("op").equals("del")) {
			System.out.println("DELETED");
			String code = request.getParameter("code");
			villeEJB.delete(Integer.parseInt(code));
		}
		System.out.println(request.getParameter("op"));
		response.setContentType("application/json");
		List<Ville> villes = villeEJB.getAll();
		Jsonb json = JsonbBuilder.create();
			response.getWriter().write(json.toJson(villes));
	}
}
