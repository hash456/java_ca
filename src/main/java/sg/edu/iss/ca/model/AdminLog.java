package sg.edu.iss.ca.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class AdminLog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "native", strategy = "native")
	private int id;
	@ManyToOne
	@JoinColumn(name="staff_id")
	private Staff staff;
	@ManyToOne
	@JoinColumn(name="inventory_id")
	private Inventory inventory;
	private int qty;
	private String action;
	private LocalDate date;
	public AdminLog() {
		super();
	}
	public AdminLog(Staff staff, Inventory inventory, int qty, String action, LocalDate date) {
		super();
		this.staff = staff;
		this.inventory = inventory;
		this.qty = qty;
		this.action = action;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}
