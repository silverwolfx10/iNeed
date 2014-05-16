package br.com.ineed.servlets;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ineed.bean.Avaliacao;
import br.com.ineed.bean.Materia;
import br.com.ineed.bean.Turma;
import br.com.ineed.dao.AvaliacaoDAO;
import br.com.ineed.dao.MateriaDAO;
import br.com.ineed.dao.TurmaDAO;



@WebServlet("/materia")
public class MateriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String pag;
	private Boolean redirect;
	
	public MateriaServlet() {
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
		
		//somente da um dispatch para o jsp se nao precisar redirecionar		
		if(!this.redirect){
			request.setAttribute("pagina", this.pag);
			RequestDispatcher dispatcher = request.getRequestDispatcher("layout/admin.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void cadastrar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		this.pag = "/materia/formulario.jsp";
		MateriaDAO dao = new MateriaDAO();
		
		String descricao = request.getParameter("descricao");
		Integer id = null;
		Integer turma_id = null;
		ArrayList<Turma> turmas = null;
		
		
		if(request.getParameter("id") != null  && (!request.getParameter("id").equals("")))
			id = Integer.parseInt(request.getParameter("id"));
		
		if(request.getParameter("turma_id") != null  && (!request.getParameter("turma_id").equals("")))
			turma_id = Integer.parseInt(request.getParameter("turma_id"));
		
		if(id != null && descricao != null && turma_id != null){
			//update	
			Materia mt = new Materia(id, descricao, turma_id);
			dao.update(mt);
			
			this.redirect = true;
			response.sendRedirect("materia");
			
		}else if(descricao != null && turma_id != null){
			//cadastrar
			Materia mt = new Materia();
			Turma turma = new Turma(turma_id, "");
			mt.setDescricao(descricao);
			mt.setTurmaId(turma);
			
			dao.insert(mt);
			
			this.redirect = true;
			response.sendRedirect("materia");
			
		}else if(id != null){
			//popula o formulario
			Materia mt = dao.get(id);
			
			//dependencia do select no formulario
			TurmaDAO dao2 = new TurmaDAO();
			turmas 	= (ArrayList<Turma>)dao2.getAll();
			
			this.redirect = false;
			request.setAttribute("turmas",turmas);
			request.setAttribute("materia",mt);
			request.setAttribute("title", "Edicao de Materia");
		}else{
			
			//dependencia do select no formulario
			TurmaDAO dao2 = new TurmaDAO();
			turmas 	= (ArrayList<Turma>)dao2.getAll();
			
			request.setAttribute("turmas",turmas);
			request.setAttribute("title", "Cadastro de Materia");
		}
	}

	protected void listar(HttpServletRequest request){
		//define a pagina que vai ser carregado o conteudo	
		this.pag = "/materia/lista.jsp";
		
		MateriaDAO dao = new MateriaDAO();
		ArrayList<Materia> materias = null;
		
		//consulta no banco todas as turmas
		materias = (ArrayList<Materia>)dao.getAll();
		
		//seta variavel em escopo de requisicao		
		request.setAttribute("materias", materias);
		request.setAttribute("title", "Lista de Materias");
	}
	
	protected void excluir(HttpServletRequest request, HttpServletResponse response) throws IOException{
		MateriaDAO dao = new MateriaDAO();
		//casting
		Integer id = Integer.parseInt(request.getParameter("id"));
		dao.delete(id);
		
		this.redirect = true;
		response.sendRedirect("materia");
	}

}
