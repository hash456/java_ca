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
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
	private int id;
	private String partNumber;
	private String name;
	private String description;
	private String color;
	private String dimension;
	private String category;
	private String subCategory;
	private String type;
	
	@ManyToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	@OneToMany(mappedBy = "product", cascade = {CascadeType.REMOVE})
	private List<Inventory> inventories;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String partNumber, String name, String description, String color,
			String dimension, String category, String subCategory, String type) {
		super();
		this.partNumber = partNumber;
		this.name = name;
		this.description = description;
		this.color = color;
		this.dimension = dimension;
		this.category = category;
		this.subCategory = subCategory;
		this.type = type;
	}
	public Product(String partNumber, String name, String description, String color, 
			String dimension, String category,String subCategory, String type, Brand brand) {
		super();
		this.partNumber = partNumber;
		this.name = name;
		this.description = description;
		this.color = color;
		this.dimension = dimension;
		this.category = category;
		this.subCategory = subCategory;
		this.type = type;
		this.brand = brand;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", partNumber=" + partNumber + ", name=" + name + ", description=" + description
				+ ", color=" + color + ", dimension=" + dimension + ", category=" + category + ", subCategory="
				+ subCategory + ", type=" + type + ", brand=" + brand + "]";
	}
	
	// Create a field but don't add it to the table
	@Transient
	private String brandName;
	
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
}
