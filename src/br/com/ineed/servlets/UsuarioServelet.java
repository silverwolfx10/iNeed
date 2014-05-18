package br.com.ineed.servlets;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import br.com.ineed.bean.Usuario;
import br.com.ineed.dao.TurmaDAO;
import br.com.ineed.dao.UsuarioDAO;	
import br.com.ineed.bean.Turma;

@WebServlet("/usuario")
public class UsuarioServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String pag;
	private Boolean redirect;
	
	public  UsuarioServelet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestHandler(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestHandler(request, response);
	}
	
protected void requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String actionParameter = request.getParameter("action");
		this.redirect = false;
		
		if(actionParameter == null){
			this.listar(request);
		}else if(actionParameter.equals("cadastrar")){
			this.cadastrar(request, response);
		}else if(actionParameter.equals("editar")){
			this.cadastrar(request, response);
		}else if(actionParameter.equals("excluir")){
			this.excluir(request, response);
		}else{
			this.listar(request);
		}
		
		if(!this.redirect){
			request.setAttribute("pagina", this.pag);
			RequestDispatcher dispatcher = request.getRequestDispatcher("layout/admin.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void cadastrar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		this.pag = "/usuario/formulario.jsp";
		UsuarioDAO dao = new UsuarioDAO();
		
		String rm = request.getParameter("rm");
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		
		Integer turma_id = null;
		Integer is_admin = null;		
		Integer id = null;
		ArrayList<Turma> turmas = null;
		
		if(request.getParameter("id") != null  && (!request.getParameter("id").equals("")))
			id = Integer.parseInt(request.getParameter("id"));
		
		if(request.getParameter("turma_id") != null  && (!request.getParameter("turma_id").equals("")))
			turma_id = Integer.parseInt(request.getParameter("turma_id"));

		if(request.getParameter("is_admin") != null  && (!request.getParameter("is_admin").equals("")))
			is_admin = Integer.parseInt(request.getParameter("is_admin"));
		
		if(id != null && rm != null){
			//update	
			Usuario usua = new Usuario();
			usua.setId(id);
			usua.setRm(rm);
			usua.setNome(nome);
			usua.setSenha(senha);
			usua.setIsAdmin(is_admin);
			usua.setTurmaId(new Turma(turma_id,""));
			
			dao.update(usua);
			
			this.redirect = true;
			response.sendRedirect("usuario");
			
		}else if(rm != null){
			//cadastrar
			Usuario usua = new Usuario();
			usua.setId(id);
			usua.setRm(rm);
			usua.setNome(nome);
			usua.setSenha(senha);
			usua.setIsAdmin(is_admin);
			usua.setTurmaId(new Turma(turma_id,""));
			
			dao.insert(usua);
			
			this.redirect = true;
			response.sendRedirect("usuario");
			
					
		}else if(id != null){
			//popula o formulario
			Usuario usua = dao.get(id);
			
			//dependencia do select no formulario
			TurmaDAO dao2 = new TurmaDAO();
			turmas 	= (ArrayList<Turma>)dao2.getAll();
			
			this.redirect = false;
			request.setAttribute("usuario",usua);
			
			request.setAttribute("turmas",turmas);
			request.setAttribute("title", "Edicao de Usuario");
		}else{
			
			
			//dependencia do select no formulario
			TurmaDAO dao2 = new TurmaDAO();
			turmas 	= (ArrayList<Turma>)dao2.getAll();
			
			request.setAttribute("turmas",turmas);
			request.setAttribute("title", "Cadastro de Usuario");
		}
		
	}
	
	
	protected void listar(HttpServletRequest request){
		//define a pagina que vai ser carregado o conteudo	
		this.pag = "/usuario/lista.jsp";
		
		UsuarioDAO dao = new UsuarioDAO();
		ArrayList<Usuario> usuarios = null;
		
		//consulta no banco todas as turmas
		usuarios = (ArrayList<Usuario>)dao.getAll();
		
		//seta variavel em escopo de requisicao		
		request.setAttribute("usuarios", usuarios);
		request.setAttribute("title", "Lista de Usuarios");
	}
	
	protected void excluir(HttpServletRequest request, HttpServletResponse response) throws IOException{
		UsuarioDAO dao = new UsuarioDAO();
		//casting
		Integer id = Integer.parseInt(request.getParameter("id"));
		dao.delete(id);
		
		this.redirect = true;
		response.sendRedirect("usuario");
	}

}

