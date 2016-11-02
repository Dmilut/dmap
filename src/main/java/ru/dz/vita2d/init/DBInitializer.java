/**
 * 
 */
package ru.dz.vita2d.init;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ru.dz.vita2d.model.Role;
import ru.dz.vita2d.model.User;
import ru.dz.vita2d.repository.RoleRepository;
import ru.dz.vita2d.repository.UserRepository;

/**
 * @author dmilut created on Nov 2, 2016
 */

public class DBInitializer {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public void initDB() {
		initUsersAndRoles();
		test();

	}

	@Transactional
	private void initUsersAndRoles() {
		User userAdmin = new User();
		userAdmin.setName("adminName");
		userAdmin.setPassword("adminPassword");
		User userUser = new User();
		userUser.setName("userName");
		userUser.setPassword("userPassword");

		Role roleAdmin = new Role();
		roleAdmin.setName("adminRole");
		Role roleUser = new Role();
		roleUser.setName("userRole");

		Set<Role> roles1 = new HashSet<>();
		roles1.add(roleAdmin);
		Set<Role> roles2 = new HashSet<>();
		roles1.add(roleUser);

		userAdmin.setRoles(roles1);
		userUser.setRoles(roles2);

		userRepository.save(userAdmin);
		userRepository.save(userUser);

	}
	
	@Transactional
	private void test(){
		User user = userRepository.findByName("adminName");
		System.out.println("user name= " + user.getName());
	}

}
