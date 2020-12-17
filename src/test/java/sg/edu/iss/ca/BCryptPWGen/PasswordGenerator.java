package sg.edu.iss.ca.BCryptPWGen;

import java.util.Scanner;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
		Scanner Obj = new Scanner(System.in);
		System.out.println("Enter a password to be encrypted.");
		String input = Obj.nextLine();
		String rawPassword = input.toString();
		
		String encodedPassword = encoder.encode(rawPassword);
		
		System.out.println("Your password has been encrypted by BCrypt:");
		System.out.println(encodedPassword);
		
		
		
		//This is a test case to turn a password into an encrypted format by using BCrypt. 
		//This is mostly used for MYSQL user creation purposes. Always run as a java program.
		//Right Click > Run As > Java Application.
	}

}
