package sg.edu.iss.ca.email;

public abstract class AbstractMail {
	private final String to;

    public AbstractMail(String to) {
        this.to = to;
    }

    public String getTo() {
        return this.to;
    }

}
