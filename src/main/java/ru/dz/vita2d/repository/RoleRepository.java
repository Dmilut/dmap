/**
 * 
 */
package ru.dz.vita2d.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.dz.vita2d.model.Role;

/**
 * @author dmilut created on Nov 2, 2016
 */

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	//List<Role> finByUser_Id(Long id);

}
