package br.com.ineed.servlets;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;

import br.com.ineed.bean.Materia;
import br.com.ineed.bean.Nota;
import br.com.ineed.bean.Usuario;
import br.com.ineed.dao.MateriaDAO;
import br.com.ineed.dao.NotaDAO;
@WebServlet("/ranking")

public class RankingServlet extends AbstractServlet {
	
	public  RankingServlet() {
	    super();
	}
	protected void requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.pag = "/ranking/ranking.jsp";
		
		NotaDAO dao = new NotaDAO();	
		ArrayList<Nota> notas = (ArrayList<Nota>) dao.getRanking();
		
				
		//seta variavel em escopo de requisicao		
		request.setAttribute("notas", notas);
		request.setAttribute("title", "Ranking");
		
		request.setAttribute("pagina", this.pag);
		RequestDispatcher dispatcher = request.getRequestDispatcher("layout/usuario.jsp");
		dispatcher.forward(request, response);
		
	}
}
