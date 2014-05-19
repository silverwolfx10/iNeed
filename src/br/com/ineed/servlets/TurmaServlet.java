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

import br.com.ineed.bean.Turma;
import br.com.ineed.bean.Usuario;
import br.com.ineed.dao.TurmaDAO;

@WebServlet("/turma")
public class TurmaServlet extends AbstractServlet{

	
	public TurmaServlet() {
        super();
        //requer privilegio de admin     
        this.servletAdmin = true;
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
		
		//somente da um dispatch para o jsp se nao precisar redirecionar		
		if(!this.redirect){
			request.setAttribute("pagina", this.pag);
			RequestDispatcher dispatcher = request.getRequestDispatcher("layout/admin.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void cadastrar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		this.pag = "/turma/formulario.jsp";
		TurmaDAO dao = new TurmaDAO();
		
		String descricao = request.getParameter("descricao");
		Integer id = null;
		
		if(request.getParameter("id") != null  && (!request.getParameter("id").equals("")))
			id = Integer.parseInt(request.getParameter("id"));
		
		if(id != null && descricao != null){
			//update	
			Turma t = new Turma(id, descricao);
			dao.update(t);
			
			this.redirect = true;
			response.sendRedirect("turma");
			
		}else if(descricao != null){
			//cadastrar
			Turma t = new Turma();
			t.setDescricao(descricao);
			
			dao.insert(t);
			
			this.redirect = true;
			response.sendRedirect("turma");
			
		}else if(id != null){
			//popula o formulario
			Turma turma = dao.get(id);
			
			this.redirect = false;
			request.setAttribute("turma",turma);
			request.setAttribute("title", "Edicao de Turma");
		}else{
			request.setAttribute("title", "Cadastro de Turma");
		}
		
	}

	protected void listar(HttpServletRequest request){
		//define a pagina que vai ser carregado o conteudo	
		this.pag = "/turma/lista.jsp";
		
		TurmaDAO dao = new TurmaDAO();
		ArrayList<Turma> turmas = null;
		
		//consulta no banco todas as turmas
		turmas = (ArrayList<Turma>)dao.getAll();
		
		//seta variavel em escopo de requisicao		
		request.setAttribute("turmas", turmas);
		request.setAttribute("title", "Listagem des Turmas");
	}
	
	protected void excluir(HttpServletRequest request, HttpServletResponse response) throws IOException{
		TurmaDAO dao = new TurmaDAO();
		//casting
		Integer id = Integer.parseInt(request.getParameter("id"));
		dao.delete(id);
		
		this.redirect = true;
		response.sendRedirect("turma");
	}

}
