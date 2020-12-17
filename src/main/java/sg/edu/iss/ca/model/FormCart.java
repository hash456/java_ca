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
	private Product product;
	public FormCart() {
		super();
	}
	public FormCart(Product product, int qty, UsageForm usageForm) {
		super();
		this.product = product;
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
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
}
