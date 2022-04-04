package ua.org.pma.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ua.org.pma.dto.ChartData;
import ua.org.pma.dto.TimeChartData;
import ua.org.pma.entity.Project;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {

  @Override
  List<Project> findAll();

  @Query(nativeQuery = true, value = "SELECT stage as label, COUNT(*) as value " +
      "FROM project " +
      "GROUP BY stage")
  public List<ChartData> getProjectStatus();

  @Query(nativeQuery = true, value =
      "SELECT name as projectName, start_date as startDate, end_date as endDate"
          + " FROM project WHERE start_date is not null")
  public List<TimeChartData> getTimeData();
}
