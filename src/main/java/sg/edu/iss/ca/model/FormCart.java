package sg.edu.iss.ca.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class FormCart {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	private int id;
	private int qty;
	@ManyToOne
	private UsageForm usageForm;
	@ManyToOne
	private Inventory inventory;
	public FormCart() {
		super();
	}
	public FormCart(Inventory inventory, int qty, UsageForm usageForm) {
		super();
		this.inventory = inventory;
		this.qty = qty;
		this.usageForm = usageForm;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UsageForm getUsageForm() {
		return usageForm;
	}
	public void setUsageForm(UsageForm usageForm) {
		this.usageForm = usageForm;
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
	
	
}
