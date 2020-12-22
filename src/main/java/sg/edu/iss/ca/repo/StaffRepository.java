package sg.edu.iss.ca.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import sg.edu.iss.ca.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer>{
	public Staff findStaffBystaffId(int staffId);
	public Staff findStaffByuserName(String username);
	public Staff findStaffByemail(String email);
}
