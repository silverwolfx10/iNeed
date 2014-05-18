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

public class RankingServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private String pag;
	private Boolean redirect;
	private Usuario usuario;


public  RankingServlet() {
    super();
}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	verifyLogin(request, response);
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	verifyLogin(request, response);
}

protected void verifyLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// salva na sessao
	HttpSession session = request.getSession(true);
	this.usuario = (Usuario) session.getAttribute("usuarioLogado");
	if(this.usuario == null)
		response.sendRedirect("login");
	else
		requestHandler(request, response);
}

	
	protected void requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.redirect = false;
		
		Integer usuario_id = 0;
		if(request.getParameter("usuario_id") != null && !request.getParameter("usuario_id").equals("")){
			usuario_id = Integer.parseInt(request.getParameter("usuario_id"));
		}
		
			this.listar(request, usuario_id);

		
		//somente da um dispatch para o jsp se nao precisar redirecionar		
		if(!this.redirect){
			request.setAttribute("pagina", this.pag);
			RequestDispatcher dispatcher = request.getRequestDispatcher("layout/usuario.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void listar(HttpServletRequest request, Integer materia_id){
		//define a pagina que vai ser carregado o conteudo	
		this.pag = "/ranking/ranking.jsp";
		
		NotaDAO dao = new NotaDAO();	
		ArrayList<Nota> notas = null;
		
				
			notas = (ArrayList<Nota>)dao.getAllByMateriaIdAndUserId(materia_id, this.usuario.getId());
				
		//seta variavel em escopo de requisicao		
		request.setAttribute("notas", notas);
		request.setAttribute("title", "Ranking");
	}


}
