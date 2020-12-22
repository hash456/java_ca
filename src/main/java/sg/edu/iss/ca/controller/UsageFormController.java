package sg.edu.iss.ca.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sg.edu.iss.ca.email.RestockMail;
import sg.edu.iss.ca.model.ChangeQtyInput;
import sg.edu.iss.ca.model.FormCart;
import sg.edu.iss.ca.model.Inventory;
import sg.edu.iss.ca.model.Product;
import sg.edu.iss.ca.model.Staff;
import sg.edu.iss.ca.model.UsageForm;
import sg.edu.iss.ca.repo.FormCartRepository;
import sg.edu.iss.ca.repo.InventoryRepository;
import sg.edu.iss.ca.repo.ProductRepository;
import sg.edu.iss.ca.repo.UsageFormRepository;
import sg.edu.iss.ca.service.FormCartImplement;
import sg.edu.iss.ca.service.FormCartService;
import sg.edu.iss.ca.service.MailSenderService;
import sg.edu.iss.ca.service.UsageFormImplement;
import sg.edu.iss.ca.service.UsageFormService;
import sg.edu.iss.ca.service.UserService;

@Controller
@RequestMapping("/UsageForm")
public class UsageFormController {
	@Autowired
	private InventoryRepository irepo;

	@Autowired
	private UsageFormRepository ufrepo;

	@Autowired
	private FormCartRepository fcrepo;

	@Autowired
	private UsageFormService ufservice;
	
	@Autowired
	private UserService uSvc;
	
	@Autowired
	private MailSenderService senderService;

	@Autowired
	public void setUsageFormService(UsageFormImplement ufimp) {
		this.ufservice = ufimp;
	}

	@Autowired
	private FormCartService fcservice;

	@Autowired
	public void setFormCartService(FormCartImplement fcimp) {
		this.fcservice = fcimp;
	}

//	String sessionId = "123";
//	int userid = 456;

//	@RequestMapping(value = "/details/{id}")
//	public String showDetails(@PathVariable("id") int id, Model model) {
//		model.addAttribute("cartList", ufservice.listAllItems(ufrepo.findById(id).get()));
//		return "UsageForm";
//	}

	@RequestMapping(value = "/addInventory")
	public String addInventory(Model model, HttpSession session) {
		UsageForm uf = (UsageForm) session.getAttribute("UsageForm");
		model.addAttribute("UsageForm", uf);
		return "redirect:/inventory/list";
	}

	@RequestMapping(value = "/add")
	public String createForm(Model model, HttpSession session) {
		UsageForm UF = (UsageForm) session.getAttribute("UsageForm");
		if (UF != null) {
			int id = UF.getId();
			model.addAttribute("UsageForm", UF);
			model.addAttribute("cartList", ufservice.listAllItems(ufrepo.findById(id).get()));
			return "UsageForm";
		}

		else {
			UsageForm usageForm = new UsageForm();
			ufrepo.save(usageForm);
			session.setAttribute("UsageForm", usageForm);
			// model.addAttribute("session", session);
			model.addAttribute("UsageForm", usageForm);
			model.addAttribute("cartList", new ArrayList<FormCart>());
		}
		return "UsageForm";

	}

	@RequestMapping(value = "/details")
	public String viewForm(Model model, HttpSession session) {
		// hard coded formId
		// need some logic here to check if the current user has created a form
		// and get the Usage Form id
//		int id = 1;
		UsageForm uf = (UsageForm) session.getAttribute("UsageForm");
		int id = uf.getId();
		// int id = usageForm.getId();

//		if (ufrepo.existsById(id) == false)
//			ufservice.createForm();
		// model.addAttribute("session", session);
		model.addAttribute("UsageForm", uf);
		model.addAttribute("cartList", ufservice.listAllItems(ufrepo.findById(id).get()));
		return "UsageForm";
	}

	@RequestMapping(value = "/remove/{id}")
	public String removeItem(@PathVariable("id") int id) {
		fcservice.deleteCart(fcservice.findFormCartById(id));
		return "redirect:/UsageForm/details";
	}

	@PostMapping(value = "/ChangeCartQty", produces = "application/json")
	@ResponseBody
	public Map ChangeCartQty(@RequestBody ChangeQtyInput changeQtyInput, HttpSession session) {
		// hard coded formId
		// int id = 1;

		UsageForm uf = (UsageForm) session.getAttribute("UsageForm");
		int id = uf.getId();

		int inventoryIdNum = changeQtyInput.getInventoryId();
		List<FormCart> fcl = ufservice.listAllItems(ufrepo.findById(id).get());
		FormCart fc = fcservice.findFormCartByInventoryIdAndFormId(inventoryIdNum, id);

		if (changeQtyInput.getAction().equals("minus") && fc.getQty() > 1) {
			int qty = fc.getQty();
			fc.setQty(qty - 1);
			fcrepo.save(fc);
		}

		// need to implement validation on stock count
		else if (changeQtyInput.getAction().equals("plus")) {
			int qty = fc.getQty();
			if (qty == fc.getInventory().getQuantity()) {
				return Collections.singletonMap("message", "Reached maximum stock");
//				Map<String, String> json = new HashMap<String, String>();
//				json.put("message", "Reached maximum stock");
//				json.put("status", "success");
//				return json;
			}

			fc.setQty(qty + 1);
			fcrepo.save(fc);
		}
		
		else if (changeQtyInput.getAction().equals("change")) {
			if(fc.getInventory().getQuantity() < changeQtyInput.getChangeQty()) {
				fc.setQty(fc.getInventory().getQuantity());
				fcrepo.save(fc);
				return Collections.singletonMap("message", "Reached maximum stock");
			}
			else {
				fc.setQty(changeQtyInput.getChangeQty());
				fcrepo.save(fc);
			}
				
		}

		return Collections.singletonMap("status", "success");
	}

	@PostMapping(value = "/save")
	public String transactionSave(@ModelAttribute("UsageForm") @Valid UsageForm usageForm, BindingResult bindingResult,
			Model model, HttpSession session, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors())
			return "redirect:/UsageForm/details";

		// hard coded formId
		// int id = 1;
		UsageForm uf = (UsageForm) session.getAttribute("UsageForm");
		int id = uf.getId();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		uf.setCustomer(usageForm.getCustomer());
		uf.setCar(usageForm.getCar());
		uf.setDescription(usageForm.getDescription());
		uf.setCreationDate(date);
		uf.setSubmitted(true);
		ufrepo.save(uf);
		List<FormCart> fcl = ufservice.listAllItems(ufrepo.findById(id).get());
		for (FormCart fc : fcl) {
			int qty = fc.getQty();
			Inventory inv = fc.getInventory();
			inv.setQuantity(inv.getQuantity() - qty);
			if(inv.getQuantity() <= inv.getReorderLvl()) {
				Staff s = uSvc.findStaffByUsername(httpServletRequest.getRemoteUser());
				senderService.sendRestockMail(
						new RestockMail(s.getEmail()),
						inv.getProduct().getName(),
						inv.getSupplier().getName()
						);	
			}
			irepo.save(inv);
		}

//		model.addAttribute("date", date);
//		model.addAttribute("description", uf.getDescription());
//		model.addAttribute("customer", uf.getCustomer());
		model.addAttribute("UsageForm", uf);
		model.addAttribute("cartList", fcl);

		session.removeAttribute("UsageForm");

		return "TransactionSummary";
	}

	@PostMapping(value = "cancel")
	public String cancelForm(Model model, HttpSession session) {
		UsageForm uf = (UsageForm) session.getAttribute("UsageForm");
		int id = uf.getId();
		List<FormCart> fcl = ufservice.listAllItems(ufrepo.findById(id).get());
		for (FormCart fc : fcl) {
			fcrepo.delete(fc);
		}
		ufrepo.delete(uf);
		session.removeAttribute("UsageForm");
		return "redirect:/inventory/list";
	}

	@RequestMapping(value = "/checkHistory/{id}")
	public String checkHistory(@PathVariable("id") int iid, Model model) {
		Inventory inventory = irepo.findInventoryById(iid);
		List<FormCart> fcl_draft = fcservice.findFormCartsByInventoryId(iid);
		List<UsageForm> ufl_draft = ufservice.findUsageFormsByInventoryId(iid);

		if (ufl_draft == null) {
			return "NoTransHistory";
		}

		List<UsageForm> ufl = new ArrayList<>();

		for (UsageForm uf : ufl_draft) {
			if (uf.isSubmitted() == true) {
				ufl.add(uf);
			}
		}

		List<FormCart> fcl = new ArrayList<>();

		for (FormCart fc : fcl_draft) {
			if (fc.getUsageForm().isSubmitted() == true) {
				fcl.add(fc);
			}
		}

		model.addAttribute("Inventory", inventory);
		model.addAttribute("UsageForm", ufl);
		model.addAttribute("cartList", fcl);
		return "PartTransHistory";
	}

//	@RequestMapping(value = "/ChangeCartQty")
//	public String ChangeCartQty(@RequestBody ChangeQtyInput changeQtyInput) {
//		int id = 1;
//		
//		int productIdNum = changeQtyInput.getProductId();
//		List<FormCart> fcl = ufservice.listAllItems(ufrepo.findById(id).get());
//		FormCart fc = fcservice.findFormCartByProductIdAndFormId(productIdNum, id);
//		
//		if (changeQtyInput.getAction() == "minus" && fc.getQty() > 1) {
//			int qty = fc.getQty();
//			fc.setQty(qty+1);
//			fcrepo.save(fc);
//		}
//		// need to implement validation on stock count
//		else if (changeQtyInput.getAction() == "plus") {
//			int qty = fc.getQty();
//			fc.setQty(qty-1);
//			fcrepo.save(fc);
//		}
//		
//		return "UsageForm";
//	}

}
