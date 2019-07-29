package io.resk.message.command.repository;

import java.util.UUID;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.resk.message.command.domain.Project;

/**
 * Interface for performing project related Crud Operations
 * 
 * @author Julius Krah
 *
 */
public interface ProjectRepository {
	/**
	 * Delete project by id
	 * 
	 * @param id the id of project
	 */
	void delete(UUID id);

	/**
	 * Find project by name
	 * 
	 * @param id the id of the project
	 * @return the project or empty if not found
	 */
	Maybe<Project> find(UUID id);

	/**
	 * Find project by name for a user
	 * 
	 * @param username  the owner
	 * @param id the id of the project
	 * @return the project or empty
	 */
	Maybe<Project> find(String username, UUID id);

	/**
	 * Find all projects for a user
	 * 
	 * @param username the owner
	 * @return all projects belonging to this user
	 */
	Flowable<Project> findAllByUsername(String username);

	/**
	 * Find all projects
	 * 
	 * @return all projects
	 */
	Flowable<Project> findAll();

	/**
	 * Save a new project
	 * 
	 * @param project the project to save
	 * @return the saved project with its id
	 */
	Single<Project> save(Project project);
}
