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

import ma.dao.QuartierOnPremise;
import ma.entites.Quartier;

@WebServlet("/QuartierController")
public class QuartierController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@EJB		
	QuartierOnPremise<Quartier> quartierEJB;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("op").equals("add")) {
			String nom = request.getParameter("nom");
			Quartier quartier = new Quartier();
			quartier.setNom(nom);
			quartierEJB.add(quartier);
		}
		else if (request.getParameter("op").equals("ed")) {
			String code = request.getParameter("code");
			String nom = request.getParameter("nom");
			Quartier quartier = new Quartier();
			quartier.setId(Integer.parseInt(code));
			quartier.setNom(nom);
			quartierEJB.edit(quartier);
		}
		else if (request.getParameter("op").equals("del")) {
			System.out.println("DELETED");
			String code = request.getParameter("code");
			quartierEJB.delete(Integer.parseInt(code));
		}
		System.out.println(request.getParameter("op"));
		response.setContentType("application/json");
		List<Quartier> quartiers = quartierEJB.getAll();
		Gson json = new Gson();
		response.getWriter().write(json.toJson(quartiers));
	}
}
