package sg.edu.iss.ca.email;

public class RestockMail extends AbstractMail {

    public RestockMail(String to) {
        super(to);
    }

    public String getSubject() {
        return "Your inventory are running low";
    }

    public String getContent(String productName, String supplierName) {
        return "The following product is running low, please reorder soon." + "\n" +
        		"Product: " + productName + "\n" + "Supplier: " + supplierName;
    }
}
