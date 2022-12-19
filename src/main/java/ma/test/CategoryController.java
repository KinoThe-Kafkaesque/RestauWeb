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

import ma.dao.CategoryOnPremise;
import ma.entites.Category;

@WebServlet("/CategoryController")
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@EJB		
	CategoryOnPremise<Category> categoryEJB;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("op").equals("add")) {
			String nom = request.getParameter("nom");
			Category category = new Category();
			category.setNom(nom);
			categoryEJB.add(category);
		}
		else if (request.getParameter("op").equals("ed")) {
			String code = request.getParameter("code");
			String nom = request.getParameter("nom");
			Category category = new Category();
			category.setId(Integer.parseInt(code));
			category.setNom(nom);
			categoryEJB.edit(category);
		}
		else if (request.getParameter("op").equals("del")) {
			System.out.println("DELETED");
			String code = request.getParameter("code");
			categoryEJB.delete(Integer.parseInt(code));
		}
		System.out.println(request.getParameter("op"));
		response.setContentType("application/json");
		List<Category> categorys = categoryEJB.getAll();
		Gson json = new Gson();
		response.getWriter().write(json.toJson(categorys));
	}
}
