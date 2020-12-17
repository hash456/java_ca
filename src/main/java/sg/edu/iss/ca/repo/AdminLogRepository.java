package sg.edu.iss.ca.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ca.model.AdminLog;

public interface AdminLogRepository extends JpaRepository<AdminLog, Integer> {
	@Query("select a from AdminLog a join a.inventory i where i.id = :id")
	public List<AdminLog> findAdminLogByInventoryId(@Param("id") Integer id);
}
