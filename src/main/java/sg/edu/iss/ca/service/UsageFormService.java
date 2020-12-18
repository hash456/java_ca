package sg.edu.iss.ca.service;

import java.util.List;

import sg.edu.iss.ca.model.FormCart;
import sg.edu.iss.ca.model.UsageForm;

public interface UsageFormService {
	public List<FormCart> listAllItems(UsageForm usageForm);
	public void createForm();
	public List<UsageForm> findUsageFormsByInventoryId(int iid);
}
