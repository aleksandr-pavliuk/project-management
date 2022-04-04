package ua.org.pma.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import ua.org.pma.entity.Project;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@SqlGroup({
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql",
        "classpath:demo.sql"}),
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:drop.sql")})
public class ProjectRepositoryIntegrationTest {

  @Autowired
  ProjectRepository projectRepository;

  @Test
  public void ifNewProjectSaved_thenSuccess() {
    Project newProject = new Project("New Test Project", "COMPLETED", "Test Description");
    projectRepository.save(newProject);
    assertEquals(5, projectRepository.findAll().size());
  }
}