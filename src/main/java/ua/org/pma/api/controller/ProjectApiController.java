package ua.org.pma.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.org.pma.dao.ProjectRepository;
import ua.org.pma.entity.Project;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {

  @Autowired
  ProjectRepository projectRepository;

  @GetMapping
  public Iterable<Project> getProjects() {
    return projectRepository.findAll();
  }

  @GetMapping("/{id}")
  public Project getProjectById(@PathVariable("id") Long id) {
    return projectRepository.findById(id).get();
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Project create(@RequestBody Project project) {
    return projectRepository.save(project);
  }

  @PutMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.OK)
  public Project update(@RequestBody Project project) {
    return projectRepository.save(project);
  }

  @PatchMapping(path = "/{id}", consumes = "application/json")
  public Project partialUpdate(@PathVariable("id") long id, @RequestBody Project patchProject) {
    Project project = projectRepository.findById(id).get();

    if (patchProject.getName() != null) {
      project.setName(patchProject.getName());
    }
    if (patchProject.getStage() != null) {
      project.setStage(patchProject.getStage());
    }
    if (patchProject.getDescription() != null) {
      project.setDescription(patchProject.getDescription());
    }

    return projectRepository.save(project);
  }


  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") Long id) {
    try {
      projectRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {

    }
  }
}
