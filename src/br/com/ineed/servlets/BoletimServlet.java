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

@WebServlet("/boletim")
public class BoletimServlet extends AbstractServlet {
	
	public BoletimServlet() {
        super();
    }
	
	protected void requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.pag = "/boletim/boletim.jsp";
		
		NotaDAO dao = new NotaDAO();
		ArrayList<Nota> boletim = null;
		
		AvaliacaoDAO avaliacaoDao = new AvaliacaoDAO();
		ArrayList<Avaliacao> avaliacoes = (ArrayList<Avaliacao>) avaliacaoDao.getAll();
		
		MateriaDAO materiaDao = new MateriaDAO();
		ArrayList<Materia> materias = (ArrayList<Materia>) materiaDao.getAllByTurmaId(this.usuario.getTurmaId().getId());
		
		boletim = (ArrayList<Nota>)dao.getBoletim(avaliacoes, materias, this.usuario);
		
		request.setAttribute("pagina", this.pag);
		request.setAttribute("boletim", boletim);
		RequestDispatcher dispatcher = request.getRequestDispatcher("layout/usuario.jsp");
		dispatcher.forward(request, response);
		
	}

}
