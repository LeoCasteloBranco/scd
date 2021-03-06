package br.cefetrj.scd.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cefetrj.scd.bean.UsuarioBeanRemote;

@WebServlet("/registrar")
public class RegistrarUsuarioServlet extends HttpServlet {
	
	@EJB
	private UsuarioBeanRemote usuarioBeanRemote;
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(usuarioBeanRemote.registrar("Luana", "teste", "l@h.com", true)) {
			response.getWriter().append(String.valueOf("Cadastro realizado com sucesso"));
		} else {
			response.getWriter().append(String.valueOf("Usuario já existe"));
		}
		
		if(usuarioBeanRemote.registrar("Renato", "teste2", "r@h.com", true)) {
			response.getWriter().append(String.valueOf("Cadastro realizado com sucesso"));
		} else {
			response.getWriter().append(String.valueOf("Usuario já existe"));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
