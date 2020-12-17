package sg.edu.iss.ca.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca.model.AdminLog;
import sg.edu.iss.ca.repo.AdminLogRepository;

@Service
public class AdminLogImplement implements AdminLogService {
	@Autowired
	private AdminLogRepository adminRepo;
	
	@Transactional
	public void deleteAdminLog(AdminLog adminLog) {
		adminRepo.delete(adminLog);
	}
	
	@Transactional
	public List<AdminLog> listAllAdminLog(){
		return adminRepo.findAll();
	}

	@Override
	@Transactional
	public AdminLog createAdminLog(AdminLog adminLog) {
		return adminRepo.save(adminLog);
	}

	@Override
	@Transactional
	public AdminLog updateAdminLog(AdminLog adminLog) {
		AdminLog a = this.findByAdminLogId(adminLog.getId());
		if(a != null)
			return adminRepo.save(a);
		return null;
	}


	@Override
	@Transactional
	public AdminLog findByAdminLogId(Integer id) {
		Optional<AdminLog> adminResponse = adminRepo.findById(id);
		if (adminResponse.isPresent()) {
			AdminLog admin = adminResponse.get();			
			return admin;
		}
		return null;
	}
	
	@Override
	@Transactional
	public List<AdminLog> findByInventoryId(Integer id) {
		return adminRepo.findAdminLogByInventoryId(id);
	}
}
