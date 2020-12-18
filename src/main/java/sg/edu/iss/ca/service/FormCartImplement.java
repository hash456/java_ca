package sg.edu.iss.ca.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ca.model.FormCart;
import sg.edu.iss.ca.model.Inventory;
import sg.edu.iss.ca.model.UsageForm;
import sg.edu.iss.ca.repo.FormCartRepository;
import sg.edu.iss.ca.repo.UsageFormRepository;

@Service
public class FormCartImplement implements FormCartService {
	@Autowired
	private FormCartRepository fcrepo;

	@Autowired
	private UsageFormRepository ufrepo;

	@Transactional
	public void deleteCart(FormCart formCart) {
		fcrepo.delete(formCart);
	}

	@Transactional
	public FormCart findFormCartById(int id) {
		return fcrepo.findFormCartById(id);
	}
	
	@Transactional
	public FormCart findFormCartByInventoryIdAndFormId(int iid, int fid) {
		List<FormCart> fcl = fcrepo.findAllByFormId(fid);
		FormCart fc = null;
		if (fcl.size() > 0) {
			for (int i = 0; i < fcl.size(); i++) {
				if (fcl.get(i).getInventory().getId() == iid)
					fc = fcl.get(i);
			}
		}
		return fc;
	}

	@Transactional
	public void addtoForm(Inventory inventory, int fid) {
		// need to dynamically get the UsageForm id, through userid/sessionid?
		// int id = 1;
		
		UsageForm uf = ufrepo.findById(fid).get();
		FormCart fc = this.findFormCartByInventoryIdAndFormId(inventory.getId(), fid);
		if (fc != null) {
			int qty = fc.getQty();
			fc.setQty(qty+1);
			fcrepo.save(fc);
		}
		else {
			FormCart formCart = new FormCart(inventory, 1, uf);
			fcrepo.save(formCart);
		}		
	}

	@Transactional
	public List<FormCart> findFormCartsByInventoryId(int iid) {
		return fcrepo.findAllByInventoryId(iid);
	}
}
