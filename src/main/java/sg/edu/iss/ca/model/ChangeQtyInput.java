package sg.edu.iss.ca.model;


public class ChangeQtyInput {
	private int id;
	private int inventoryId;
	private String action;
	private int changeQty;
	public ChangeQtyInput() {
		super();
	}
	public ChangeQtyInput(int inventoryId, String action) {
		super();
		this.inventoryId = inventoryId;
		this.action = action;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public int getChangeQty() {
		return changeQty;
	}
	public void setChangeQty(int changeQty) {
		this.changeQty = changeQty;
	}
	
	
}
