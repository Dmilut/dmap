/**
 * 
 */
package ru.dz.vita2d.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.dz.vita2d.model.Map;

/**
 * @author dmilut created on Nov 2, 2016
 */

//@Repository
public interface MapRepository extends JpaRepository<Map, Long> {

}
