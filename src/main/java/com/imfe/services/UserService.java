package com.imfe.services;

import com.imfe.model.User;

public interface UserService {
	
		public boolean serviceValidarUser(User u); //Servicio que devuelve un boolean y le pasamos el objeto user (compuesto de id,username,pass)
		public User crear (String user, String pass); // Servicio que devuelve un objeto pasándole un user y un pass
		public boolean guardar (User usuario); //Servicio que devuelve un boolean y le pasamos el objeto user
		public boolean borrar (Integer id); //Servicio para borrar un usuario de la bd
		public Iterable<User> listar (); //Servicio para listar la tabla usuarios
		

}
