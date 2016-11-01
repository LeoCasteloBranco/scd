package br.cefetrj.scd.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.cefetrj.scd.entity.Usuario;

@Stateless
public class UsuarioBean implements UsuarioBeanRemote {

	@PersistenceContext(unitName="SCDPU")
	private EntityManager manager;

	public boolean registrar(String nome, String senha, String email, boolean isAdmin){
		if(!isEmailExistente(email)) { 
			manager.persist(new Usuario(nome, senha, email, isAdmin));
			return true;
		} else {
			return false;
		}
	}

	public boolean isEmailExistente(String email) {
		try {
			manager.createQuery("SELECT u FROM Usuario u WHERE u.email = :email")
					.setParameter("email", email)
					.getSingleResult();

			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
	
	public Usuario login(String email, String senha) {
		try {
			Usuario usuario = (Usuario) manager.createQuery("SELECT u FROM Usuario u "
					+ "WHERE u.email = :email AND u.senha = :senha")
					.setParameter("email", email)
					.setParameter("senha", senha)
					.getSingleResult();
			
			return usuario;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Usuario getUsuarioId(String id) {
		return manager.find(Usuario.class, Long.parseLong(id));
	}

}
