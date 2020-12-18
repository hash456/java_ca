package sg.edu.iss.ca.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca.model.FormCart;
import sg.edu.iss.ca.model.UsageForm;
import sg.edu.iss.ca.repo.FormCartRepository;
import sg.edu.iss.ca.repo.UsageFormRepository;

@Service
public class UsageFormImplement implements UsageFormService {
	@Autowired
	private UsageFormRepository ufrepo;
	
	@Autowired
	private FormCartRepository fcrepo;
	
	
	@Override
	@Transactional
	public List<FormCart> listAllItems(UsageForm usageForm) {
		return fcrepo.findAllByFormId(usageForm.getId());
	}

	@Override
	@Transactional
	public void createForm() {
		ufrepo.save(new UsageForm());
	}

	@Override
	@Transactional
	public List<UsageForm> findUsageFormsByInventoryId(int iid) {
		List<FormCart> fcl = fcrepo.findAllByInventoryId(iid);
		List<UsageForm> ufl = ufrepo.findAll();
		List<UsageForm> ufl_found = new ArrayList<UsageForm>();
		for (UsageForm uf : ufl) {
			for (FormCart fc : fcl) {
				if (uf.getId() == fc.getUsageForm().getId())
					ufl_found.add(uf);
			}
		}
		
		return ufl_found;
	}
}
