package sg.edu.iss.ca.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "native", strategy = "native")
	private int id;
	private int quantity;
	private String shelfLocation;
	private int reorderLvl;
	private int reorderQty;
	private double wholesalePrice;
	private double retailPrice;
	private double partnerPrice;
	
	@ManyToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name="product_id")
	private Product product;
	
	@ManyToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name="supplier_id")
	private Supplier supplier;
	
	@OneToMany(mappedBy = "inventory")
	private List<FormCart> formCartList;

	public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Constructor with no product and supplier; maybe can delete because does not make sense to have no product
	public Inventory(int quantity, String shelfLocation, int reorderLvl, int reorderOty, double wholesalePrice,
			double retailPrice, double partnerPrice) {
		super();
		this.quantity = quantity;
		this.shelfLocation = shelfLocation;
		this.reorderLvl = reorderLvl;
		this.reorderQty = reorderOty;
		this.wholesalePrice = wholesalePrice;
		this.retailPrice = retailPrice;
		this.partnerPrice = partnerPrice;
		
	}
	
	//Constructor with no supplier
	public Inventory(int quantity, String shelfLocation, int reorderLvl, int reorderOty, double wholesalePrice,
			double retailPrice, double partnerPrice, Product product) {
		super();
		this.quantity = quantity;
		this.shelfLocation = shelfLocation;
		this.reorderLvl = reorderLvl;
		this.reorderQty = reorderOty;
		this.wholesalePrice = wholesalePrice;
		this.retailPrice = retailPrice;
		this.partnerPrice = partnerPrice;
		this.product = product;
	}
	
	//Constructor with both product and supplier
	public Inventory(int quantity, String shelfLocation, int reorderLvl, int reorderOty, double wholesalePrice,
			double retailPrice, double partnerPrice, Product product, Supplier supplier) {
		super();
		this.quantity = quantity;
		this.shelfLocation = shelfLocation;
		this.reorderLvl = reorderLvl;
		this.reorderQty = reorderOty;
		this.wholesalePrice = wholesalePrice;
		this.retailPrice = retailPrice;
		this.partnerPrice = partnerPrice;
		this.product = product;
		this.supplier = supplier;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getShelfLocation() {
		return shelfLocation;
	}

	public void setShelfLocation(String shelfLocation) {
		this.shelfLocation = shelfLocation;
	}

	public int getReorderLvl() {
		return reorderLvl;
	}

	public void setReorderLvl(int reorderLvl) {
		this.reorderLvl = reorderLvl;
	}

	public int getReorderQty() {
		return reorderQty;
	}

	public void setReorderQty(int reorderQty) {
		this.reorderQty = reorderQty;
	}

	public double getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(double wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public double getPartnerPrice() {
		return partnerPrice;
	}

	public void setPartnerPrice(double partnerPrice) {
		this.partnerPrice = partnerPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	

	

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", Product=" + product.getName() + ", quantity=" + quantity + ", shelfLocation=" + shelfLocation + ", reorderLvl=" + reorderLvl
				+ ", reorderQty=" + reorderQty + ", wholesalePrice=" + wholesalePrice + ", retailPrice=" + retailPrice + ", partnerPrice="
				+ partnerPrice +  ", supplier=" + supplier.getName() + "]";
	}
	



	// Create a field but don't add it to the table
	@Transient
	private String productName;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	// Create a field but don't add it to the table
	@Transient
	private String supplierName;
	
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

}
