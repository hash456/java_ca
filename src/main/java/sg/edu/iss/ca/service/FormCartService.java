package sg.edu.iss.ca.service;

import java.util.List;

import sg.edu.iss.ca.model.FormCart;
import sg.edu.iss.ca.model.Inventory;
import sg.edu.iss.ca.model.Product;

public interface FormCartService {
	public void deleteCart(FormCart formCart);
	public FormCart findFormCartById(int id);
	public void addtoForm(Inventory inventory, int fid);
	public FormCart findFormCartByInventoryIdAndFormId(int iid, int fid);
	public List<FormCart> findFormCartsByInventoryId(int pid);
	//public void updateCart(FormCart formCart);
}
