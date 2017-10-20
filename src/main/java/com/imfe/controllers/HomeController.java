package com.imfe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imfe.model.User;
import com.imfe.model.UserRepo;
import com.imfe.services.UserService;

@Controller
public class HomeController {
		
	@Autowired
	private UserRepo userRepo; // Inyectamos repositorio de la BBDD para poder usarlo aquí
	@Autowired
	private UserService servicio; // Con esto creamos el servicio, inyectamos el servicio
	
	@RequestMapping(value= {"/","/index"})
	public String index () {
		return "index";
	}
	
	@RequestMapping(value="/listado")
	public String listado (Model model) {
		
		/*Metemos en userActual el objeto entero con id 1
		User userActual =userRepo.findOne(1); // me da el elemento que tiene la id 1*/
		
		//Listamos todos los usuarios
		/*Iterable<User> lista=userRepo.findAll();*/
		
		Iterable <User>lista=servicio.listar();
		
			model.addAttribute("resultado", lista);
			
		
		return "listado";
	}
	
	
}
