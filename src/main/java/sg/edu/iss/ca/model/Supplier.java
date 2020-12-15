package sg.edu.iss.ca.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Supplier {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	private int id;
	private String email;
	private String name;
	
	@OneToMany(mappedBy = "supplier", cascade = {CascadeType.REMOVE})
	private List<Inventory> inventories;
	
	public Supplier() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Supplier(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", email=" + email + ", name=" + name + "]";
	}
	

}
