package sg.edu.iss.ca.service;

import java.util.List;

import org.springframework.data.domain.Page;

import sg.edu.iss.ca.model.Product;
import sg.edu.iss.ca.model.Staff;

public interface UserService {
	public Staff addStaff(Staff staff);	
	public void deleteStaff(Staff staff);
	public List<Staff> liststaff();
	public Staff findStaffById(Integer id);
	public Staff changeRole(Staff staff);
	public Staff findStaffByUsername(String username);
	public Page<Staff> findPaginated(int pageNo,int pageSize);
	
	public Staff findStaffByEmail(String email);
}
