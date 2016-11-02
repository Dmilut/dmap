package ru.dz.vita2d.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.dz.vita2d.model.User;

/**
 * @author dmilut created on Nov 1, 2016
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByName(String name);

}