package sg.edu.iss.ca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "native", strategy = "native")
	private int staffId;
	private String role;
	private String staffName;
	@Column(name="username", unique=true)
	private String userName;
	private String password;
	@Column(unique=true)
	private String email;
	private Boolean enabled;
	
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Staff(String role, String email, String staffName, String userName, String password, Boolean enabled) {
		super();
		this.role = role;
		this.staffName = staffName;
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
		this.email = email;
	}
	public Staff(String role, String email, String staffName, String userName, String password) {
		super();
		this.role = role;
		this.staffName = staffName;
		this.userName = userName;
		this.password = password;
		this.enabled = true;
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
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
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}



}
