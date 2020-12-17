package sg.edu.iss.ca.email;

public class HTMLMail extends AbstractMail {

    public HTMLMail(String to) {
        super(to);
    }

    @Override
    public String getSubject() {
        return "Java CA - Some stocks are running low (HTML)";
    }

    @Override
    public String getContent() {
        return "<html>" +
                    "<body>" +
                        "<p>Dear Admin,</p>" +
                        "<p>One of your products is running low. Please restock soon!</p>" +
                    "</body>" +
                "</html>";
    }

	@Override
	public String getContent(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}
}