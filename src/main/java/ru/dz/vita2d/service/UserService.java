/**
 * 
 */
package ru.dz.vita2d.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.dz.vita2d.model.User;
import ru.dz.vita2d.repository.UserRepository;

/**
 * @author dmilut created on Nov 2, 2016
 */

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User getUserByName(String name) {

		return userRepository.findByName(name);
	}
	
	
}
