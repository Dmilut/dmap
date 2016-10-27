package ru.dz.vita2d.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.dz.vita2d.model.Text;

@Repository
public interface TextRepository extends JpaRepository<Text, Long> {

	/*
	 * Default CRUD ops
	 */
	void delete(Text text);

	void deleteAll();

	List<Text> findAll();

	Text findOne(Long id);

	Text save(Text text);

	long count();

	boolean exists(Long id);

	/*
	 * Named queries
	 * http://docs.spring.io/spring-data/data-commons/docs/1.12.2.RELEASE/
	 * reference/html/
	 */
	List<Text> findByTitle(String title);
}