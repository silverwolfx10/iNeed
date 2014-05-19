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

@WebServlet("/avaliacao")
public class AvaliacaoServlet extends AbstractServlet {
	
	public AvaliacaoServlet() {
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
		this.pag = "/avaliacao/formulario.jsp";
		AvaliacaoDAO dao = new AvaliacaoDAO();
		
		String descricao = request.getParameter("descricao");
		Integer id = null;
		Float peso = null;
		
		if(request.getParameter("id") != null  && (!request.getParameter("id").equals("")))
			id = Integer.parseInt(request.getParameter("id"));
		
		if(request.getParameter("peso") != null  && (!request.getParameter("peso").equals("")))
			peso = Float.parseFloat(request.getParameter("peso"));
		
		if(id != null && descricao != null && peso != null){
			//update	
			Avaliacao av = new Avaliacao(id, descricao, peso);
			dao.update(av);
			
			this.redirect = true;
			response.sendRedirect("avaliacao");
			
		}else if(descricao != null && peso != null){
			//cadastrar
			Avaliacao av = new Avaliacao();
			av.setDescricao(descricao);
			av.setPeso(peso);
			
			dao.insert(av);
			
			this.redirect = true;
			response.sendRedirect("avaliacao");
			
		}else if(id != null){
			//popula o formulario
			Avaliacao av = dao.get(id);
			
			this.redirect = false;
			request.setAttribute("avaliacao",av);
			request.setAttribute("title", "Edicao do Tipo de Avaliacao");
		}else{
			request.setAttribute("title", "Cadastro do Tipo de Avaliacao");
		}
		
	}

	protected void listar(HttpServletRequest request){
		//define a pagina que vai ser carregado o conteudo	
		this.pag = "/avaliacao/lista.jsp";
		
		AvaliacaoDAO dao = new AvaliacaoDAO();
		ArrayList<Avaliacao> avaliacoes = null;
		
		//consulta no banco todas as turmas
		avaliacoes = (ArrayList<Avaliacao>)dao.getAll();
		
		//seta variavel em escopo de requisicao		
		request.setAttribute("avaliacoes", avaliacoes);
		request.setAttribute("title", "Tipos de Avaliacao");
	}
	
	protected void excluir(HttpServletRequest request, HttpServletResponse response) throws IOException{
		AvaliacaoDAO dao = new AvaliacaoDAO();
		//casting
		Integer id = Integer.parseInt(request.getParameter("id"));
		dao.delete(id);
		
		this.redirect = true;
		response.sendRedirect("avaliacao");
	}

}
