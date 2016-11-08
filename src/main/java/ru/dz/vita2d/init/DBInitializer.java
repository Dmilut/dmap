/**
 * 
 */
package ru.dz.vita2d.init;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ru.dz.vita2d.model.Map;
import ru.dz.vita2d.model.Role;
import ru.dz.vita2d.model.User;
import ru.dz.vita2d.repository.MapRepository;
import ru.dz.vita2d.repository.UserRepository;

/**
 * @author dmilut created on Nov 2, 2016
 */

@Component
@DependsOn({ "persistenceJPAConfig" })
public class DBInitializer {
	private static final String BACKGROUND_MAP_IMAGE = "/ru/dz/vita2d/img/background.png";

	private static final Logger LOGGER = LoggerFactory.getLogger(DBInitializer.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MapRepository mapRepository;

	@PostConstruct
	public void initDB() {
		initUsersAndRoles();
		initMap();
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
	private void initMap() {
		Map map = new Map();
		map.setName("Пулково");
		map.setBackgroundImage(getByteArray(BACKGROUND_MAP_IMAGE));

		mapRepository.save(map);
	}

	private byte[] getByteArray(String path) {
		byte[] image = null;

		try (InputStream inputStream = DBInitializer.class.getResourceAsStream(path)) {
			// InputStream inputStream =
			// DBInitializer.class.getResourceAsStream(path);
			image = IOUtils.toByteArray(inputStream);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return image;
	}

	private void test() {
		User user = userRepository.findByName("adminName");
		System.out.println("user name= " + user.getName());

		Map map = mapRepository.findByName("Пулково");
		System.out.println("map name= " + map.getName());
		System.out.println("map object= " + map.getBackgroundImage().toString());
	}

}
