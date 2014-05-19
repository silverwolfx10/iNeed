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
import br.com.ineed.dao.MateriaDAO;
import br.com.ineed.dao.UsuarioDAO;



@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public LoginServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestHandler(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestHandler(request, response);
	}
	
protected void requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String mensagem = null;
		Usuario usuario = null;
		String action = request.getParameter("action");
		HttpSession session = request.getSession(true);
		
		if(action != null && action.equals("logout")){
			session.removeAttribute("usuarioLogado");
			response.sendRedirect("login");
		}else{
			if(login != null && !login.equals("") && password != null && !password.equals("")){
				//faz login	
				UsuarioDAO dao = new UsuarioDAO();
				usuario = dao.makeLogin(login, password);
				if(usuario != null){
					session.setAttribute("usuarioLogado", usuario);
					if(usuario.getIsAdmin() == 1){ 
						response.sendRedirect("turmas");
					}else{
						response.sendRedirect("nota");
					}
				}else{
					mensagem = "Login ou senha invalidos";
				}
			}
			
			if(usuario == null){
				request.setAttribute("mensagem", mensagem);
				RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
				dispatcher.forward(request, response);
			}
		}
		
	}

}
