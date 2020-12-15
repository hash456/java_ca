package sg.edu.iss.ca.email;

public class SimpleMail extends AbstractMail {

    public SimpleMail(String to) {
        super(to);
    }

    @Override
    public String getSubject() {
        return "Java CA - Some stocks are running low (Simple)";
    }

    @Override
    public String getContent() {
        return "One of your product is running low on stock, please place an order with the supplier soon!";
    }
}