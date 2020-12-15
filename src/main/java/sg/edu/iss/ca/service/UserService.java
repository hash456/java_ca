package sg.edu.iss.ca.service;

import java.util.List;


import sg.edu.iss.ca.model.Staff;

public interface UserService {
public void addStaff(Staff staff);
	
	public void deleteStaff(Staff staff);
	public List<Staff> liststaff();
	public Staff findStaffById(Integer id);
	public void changeRole(Staff staff);
}
