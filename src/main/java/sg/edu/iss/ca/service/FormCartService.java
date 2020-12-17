package sg.edu.iss.ca.service;

import java.util.List;

import sg.edu.iss.ca.model.FormCart;
import sg.edu.iss.ca.model.Product;

public interface FormCartService {
	public void deleteCart(FormCart formCart);
	public FormCart findFormCartById(int id);
	public void addtoForm(Product product, int fid);
	public FormCart findFormCartByProductIdAndFormId(int pid, int fid);
	public List<FormCart> findFormCartsByProductId(int pid);
	//public void updateCart(FormCart formCart);
}
