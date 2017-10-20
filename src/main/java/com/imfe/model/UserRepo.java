package com.imfe.model;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User,Integer>{ // <clase del objeto,tipo de la clave primaria>

}
