package sg.edu.iss.ca.email;

public class AccountMail extends AbstractMail {
	
    public AccountMail(String to) {
        super(to);
    }
    
    public String getSubject() {
        return "Your new Account";
    }

    public String getContent(String username, String password) {
        return "Username: " + username + "\n" + "Password: " + password;
    }

	public String getContent() {
		// TODO Auto-generated method stub
		return null;
	}
}
