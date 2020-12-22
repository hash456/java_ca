package sg.edu.iss.ca.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca.model.Product;
import sg.edu.iss.ca.model.Staff;

import sg.edu.iss.ca.repo.StaffRepository;

@Service
public class UserImplement implements UserService{
	@Autowired
	public StaffRepository staffrepo;
	
	@Transactional
	public Staff addStaff(Staff staff) {
		 return staffrepo.save(staff);
	}
	@Transactional
	public List<Staff> liststaff() {
		return staffrepo.findAll();
	}	
	@Transactional
	public void deleteStaff(Staff staff){
		staffrepo.delete(staff);
	}
	@Transactional
	public Staff findStaffById(Integer staffId)
	{
		return staffrepo.findStaffBystaffId(staffId);
	}
	@Transactional
	public Staff changeRole(Staff staff) {
		if(staff.getRole().equals("ROLE_ADMIN"))
			staff.setRole("ROLE_MECHANIC");
		else
			staff.setRole("ROLE_ADMIN");
				
		return staffrepo.save(staff);
	}
	@Transactional
	public Staff findStaffByUsername(String username) {
		return staffrepo.findStaffByuserName(username);
	}
	@Override
	public Page<Staff> findPaginated(int pageNo, int pageSize) {
		Pageable pageable= PageRequest.of(pageNo-1, pageSize);
		return staffrepo.findAll(pageable);
	}
}
