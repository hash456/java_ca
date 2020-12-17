package sg.edu.iss.ca.service;

import java.util.List;

import sg.edu.iss.ca.model.AdminLog;

public interface AdminLogService {
	public void deleteAdminLog(AdminLog adminLog);
	public List<AdminLog> listAllAdminLog();
	public AdminLog createAdminLog(AdminLog adminLog);
	public AdminLog updateAdminLog(AdminLog adminLog);
	public AdminLog findByAdminLogId(Integer id);
	public List<AdminLog> findByInventoryId(Integer id);
}
