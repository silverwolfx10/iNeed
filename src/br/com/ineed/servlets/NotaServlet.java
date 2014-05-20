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
import br.com.ineed.bean.Materia;
import br.com.ineed.bean.Nota;
import br.com.ineed.bean.Turma;
import br.com.ineed.bean.Usuario;
import br.com.ineed.dao.AvaliacaoDAO;
import br.com.ineed.dao.MateriaDAO;
import br.com.ineed.dao.NotaDAO;
import br.com.ineed.dao.TurmaDAO;

@WebServlet("/nota")
public class NotaServlet extends AbstractServlet {
	
	public NotaServlet() {
        super();
    }
	
	protected void requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String actionParameter = request.getParameter("action");
		this.redirect = false;
		
		Integer materia_id = 0;
		if(request.getParameter("materia_id") != null && !request.getParameter("materia_id").equals("")){
			materia_id = Integer.parseInt(request.getParameter("materia_id"));
		}
		
		if(actionParameter == null){
			this.listar(request, materia_id);
		}else if(actionParameter.equals("cadastrar")){
			this.cadastrar(request, response);
		}else if(actionParameter.equals("editar")){
			this.cadastrar(request, response);
		}else if(actionParameter.equals("excluir")){
			this.excluir(request, response, materia_id);
		}else{
			this.listar(request, materia_id);
		}
		
		//somente da um dispatch para o jsp se nao precisar redirecionar		
		if(!this.redirect){
			request.setAttribute("materia_id", materia_id);
			request.setAttribute("pagina", this.pag);
			RequestDispatcher dispatcher = request.getRequestDispatcher("layout/usuario.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void cadastrar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		this.pag = "/nota/formulario.jsp";
		NotaDAO dao = new NotaDAO();
		
		Float nota = null;
		Materia materia = null;
		Avaliacao avaliacao = null;
		Integer semestre = null;
		Integer id = null;
		String mensagem=null;
		
		
		if(request.getParameter("id") != null  && (!request.getParameter("id").equals("")))
			id = Integer.parseInt(request.getParameter("id"));
		
		if(request.getParameter("nota") != null && !request.getParameter("nota").equals("")){
			try {
				nota = Float.parseFloat(request.getParameter("nota"));
			} catch (Exception e) {
				nota = null;
				mensagem="Nota Invalida!!";
			}
		}
		
		if(request.getParameter("materia_id") != null && !request.getParameter("materia_id").equals("")){
			materia = new Materia();
			materia.setId(Integer.parseInt(request.getParameter("materia_id")));
		}
		
		if(request.getParameter("avaliacao_id") != null && !request.getParameter("avaliacao_id").equals("")){
			avaliacao = new Avaliacao();
			avaliacao.setId(Integer.parseInt(request.getParameter("avaliacao_id")));
		}
		
		if(request.getParameter("semestre") != null && !request.getParameter("semestre").equals(""))
			semestre = Integer.parseInt(request.getParameter("semestre"));
		
		
		ArrayList<Materia> materias = null;
		ArrayList<Avaliacao> avaliacoes = null;
		

		
		if(id != null && nota != null && materia != null && avaliacao != null && semestre != null){
			//update	
			Nota nt = new Nota(id, materia, avaliacao, nota, semestre, this.usuario);
			dao.update(nt);
			
			this.redirect = true;
			response.sendRedirect("nota?materia_id="+materia.getId());
			
		}else if(nota != null && materia != null && avaliacao != null && semestre != null){
			//cadastrar
			Nota nt = new Nota(materia, avaliacao, nota, semestre, this.usuario);
			dao.insert(nt);
		
			this.redirect = true;
			response.sendRedirect("nota?materia_id="+materia.getId());
			
		}else if(id != null){
			//popula o formulario
			Nota nt = dao.get(id);
			
			//dependencia do select no formulario
			MateriaDAO materiaDao = new MateriaDAO();
			materias 	= (ArrayList<Materia>)materiaDao.getAllByTurmaId(this.usuario.getTurmaId().getId());
			
			AvaliacaoDAO avaliacaoDao = new AvaliacaoDAO();
			avaliacoes 	= (ArrayList<Avaliacao>)avaliacaoDao.getAll();
			
			this.redirect = false;
			request.setAttribute("mensagem",mensagem);
			request.setAttribute("materias",materias);
			request.setAttribute("avaliacoes",avaliacoes);
			request.setAttribute("nota",nt);
			request.setAttribute("title", "Edicao de Nota");
		}else{
			
			//dependencia do select no formulario
			MateriaDAO materiaDao = new MateriaDAO();
			materias 	= (ArrayList<Materia>)materiaDao.getAllByTurmaId(this.usuario.getTurmaId().getId());
			
			AvaliacaoDAO avaliacaoDao = new AvaliacaoDAO();
			avaliacoes 	= (ArrayList<Avaliacao>)avaliacaoDao.getAll();
			
			request.setAttribute("mensagem",mensagem);
			request.setAttribute("materias",materias);
			request.setAttribute("avaliacoes",avaliacoes);
			request.setAttribute("title", "Cadastro de Nota");
		}
	}

	protected void listar(HttpServletRequest request, Integer materia_id){
		//define a pagina que vai ser carregado o conteudo	
		this.pag = "/nota/lista.jsp";
		
		NotaDAO dao = new NotaDAO();	
		ArrayList<Nota> notas = null;
		
		MateriaDAO materiaDao = new MateriaDAO();	
		ArrayList<Materia> materias = (ArrayList<Materia>) materiaDao.getAllByTurmaId(this.usuario.getTurmaId().getId());
		
		//consulta no banco todas as turmas
		notas = (ArrayList<Nota>)dao.getAllByMateriaIdAndUserId(materia_id, this.usuario.getId());
		
		
		//seta variavel em escopo de requisicao		
		request.setAttribute("notas", notas);
		request.setAttribute("materia_id", materia_id);
		request.setAttribute("materias", materias);
		request.setAttribute("title", "Lista de Notas");
	}
	
	protected void excluir(HttpServletRequest request, HttpServletResponse response, Integer materia_id) throws IOException{
		NotaDAO dao = new NotaDAO();
		//casting
		Integer id = Integer.parseInt(request.getParameter("id"));
		dao.delete(id);
		
		this.redirect = true;
		response.sendRedirect("nota?materia_id="+materia_id);
	}

}
