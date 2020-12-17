package sg.edu.iss.ca.service;

import java.util.List;

import sg.edu.iss.ca.model.FormCart;
import sg.edu.iss.ca.model.UsageForm;
import sg.edu.iss.ca.model.UsageRecord;

public interface UsageFormService {
	public List<FormCart> listAllItems(UsageForm usageForm);
	public void createForm();
	public List<UsageForm> findUsageFormsByProductId(int pid);
}
