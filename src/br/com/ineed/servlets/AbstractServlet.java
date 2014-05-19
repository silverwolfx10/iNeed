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

import br.com.ineed.bean.Avaliacao;
import br.com.ineed.bean.Usuario;
import br.com.ineed.dao.AvaliacaoDAO;

abstract class AbstractServlet extends HttpServlet {
	protected static final long serialVersionUID = 1L;
	protected String pag;
	protected Boolean redirect;
	protected Usuario usuario;
	protected Boolean servletAdmin = false;
	
	public AbstractServlet() {
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
		else if(this.servletAdmin && this.usuario.getIsAdmin() == 0)
			response.sendRedirect("login");
		else
			requestHandler(request, response);
	}
	
	abstract void requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
		

}
