package br.com.ineed.servlets;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ineed.bean.Materia;
import br.com.ineed.dao.MateriaDAO;

@WebServlet("/listarMaterias")
public class ListaMateriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListaMateriaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestHandler(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestHandler(request, response);
	}

	protected void requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MateriaDAO dao = new MateriaDAO();
		
		ArrayList<Materia> materias = null;
		
		materias = (ArrayList<Materia>)dao.getAll();
		
		request.setAttribute("listaMaterias", materias);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/materias/listaMaterias.jsp");
		dispatcher.forward(request, response);
		
	}

}

