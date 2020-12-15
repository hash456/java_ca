package sg.edu.iss.ca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int staffId;
	private Role role;
	private String staffName;
	private String userName;
	private String password;
	
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Staff(Role role, String staffName, String userName, String password) {
		super();
		this.role = role;
		this.staffName = staffName;
		this.userName = userName;
		this.password = password;
	}
	
	
	
	public Staff(int staffId, String staffName, String userName, String password) {
		super();
		this.staffId=staffId;
		this.staffName = staffName;
		this.userName = userName;
		this.password = password;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}



}
