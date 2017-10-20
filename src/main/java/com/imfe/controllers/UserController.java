package com.imfe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.imfe.model.User;
import com.imfe.model.UserRepo;
import com.imfe.services.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
		
		@Autowired
		private UserService servicio; // Con esto creamos el servicio, inyectamos el servicio
		
		@Autowired
		private UserRepo userRepo; // Inyectamos repositorio de la BBDD para poder usarlo aquí
		
		
		//Obligamos a que se use el método POST en el formulario
		@RequestMapping(value="/validar", method=RequestMethod.POST) 
		public String validarp(Model model, @RequestParam(name="usuario") String usuario, @RequestParam(name="pass") String pass) {
			
			//Ejecutamos el servicio 'crear' para crear el objeto usuario pasandole los parametros del formulario
			User userTemporal=servicio.crear(usuario, pass);
			
			//Ejecutamos el servicio 'serviceValidarUser' para comprobar la sesión
			boolean validacion=servicio.serviceValidarUser(userTemporal);
			
			String destino="index";
			//Si el usuario y la contraseña del 'userTemporal' son válidos 
			if(validacion) { 
				
				model.addAttribute("mensaje", "Login correcto");
				model.addAttribute("user",userTemporal); //Enviamos el objeto a home
				destino="home";
			}
			else {
				
				model.addAttribute("mensaje", "Login incorrecto");
				destino="index";
			}
			
			return destino;
		}
		
		
		//Evitamos que el usuario pueda logearse por el método GET
		@RequestMapping(value="/validar", method=RequestMethod.GET)
		public String validarg(Model model) {
			
			model.addAttribute("mensaje","No puedes logearte así");
			return "index";
		}
		
		//Mapeo para el registro de usuarios en la BBDD
		@RequestMapping(value="/alta", method=RequestMethod.POST)
		public String altausuario(Model model, @RequestParam(name="usuario")String usuario, @RequestParam(name="pass")String pass) {
			
			//Llamamos al servicio 'servicio.crear' para crear un objeto con los parámetros k le pasamos por el formulario
			User altaUser=servicio.crear(usuario, pass);
			
			//Llamamos al servicio 'service.guardar' para guardar un objeto en la base de datos pasándole el objeto creado
			servicio.guardar(altaUser);
			
			model.addAttribute("mensaje", "Usuario guardado correctamente");
			
			return "alta";
		}
		
		//Mapeo para ir al formulario 'alta.html'
		@RequestMapping(value= {"/nuevo", "/alta"}, method=RequestMethod.GET)
		public String nuevo() {
			return "alta";
		}
		
		/**
		 * 
		 * 
		 * @param n - parametro pasado por el formulario que indica el id el usuario a borrar
		 * @return - devuelve el listado restante de usuario despues de haber borrado al usuario
		 */
		//Mapeo para borrar un usuario de la bd y luego mostrar listado de usuarios restante
		@RequestMapping(value="/delete/{n}")
		public String delete (Model model, @PathVariable(name="n")Integer n) {
			
			servicio.borrar(n);
			
			/*Iterable<User> rdo=servicio.listar(); Usando el servicio listar*/
			
			/*model.addAttribute("resultado", rdo); Usando el servicio listar*/
			model.addAttribute("mensaje", "Usuario borrado correctamente");
			return "redirect:/listado"; //Redirigimos así listado que está en HomeController
		}
		
		

}
