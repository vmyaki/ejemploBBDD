package com.imfe.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imfe.model.User;
import com.imfe.model.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo; // Inyectamos repositorio de la BBDD para poder usarlo aquí
	@Autowired
	private UserService servicio; // Con esto creamos el servicio, inyectamos el servicio
	
	
	//Servicio para validar el inicio de sesión
	@Override
	public boolean serviceValidarUser(User u) {
			
		boolean validador=false;
		
		//Llamamos al servicio listar para obtener el listado completo de la tabla usuarios
		Iterable<User>lista=servicio.listar();
		
		//Recorremos con un for each toda la lista y metemos los objetos en p 
		for (User p : lista) { 
			//Luego comparamos el userName y el pass introducidos en el formulario con los de la bd
		
			if(u.getUserName().equals(p.getUserName()) && u.getPass().equals(p.getPass()))
			{
				validador = true;
			}
		}
		
			
		return validador;
	}
	
	
	//Servicio para crear un usuario
	@Override
	public User crear(String user, String pass) {
		User u =new User();
		u.setUserName(user);
		u.setPass(pass);
		return u;
	}
	
	//Servicio para guardar un usuario en la base de datos recibiendo un objeto usuario
	@Override
	public boolean guardar(User usuario) {
		
		userRepo.save(usuario);
		
		return true; //TODO comprobar realmente que la inserción fue bien
	}
	
	//Servicio para borrar un usuario recibiendo el parametro id
	@Override
	public boolean borrar(Integer id) {
		userRepo.delete(id);
		return false;
	}

	//Servicio para listar la tabla usuarios
	@Override
	public Iterable<User> listar() {
		Iterable<User>listado=userRepo.findAll();
		return listado;
	}

}
