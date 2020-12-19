package sg.edu.iss.ca.javapath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PathTest {
	@Test
	void getProjectDirectory() {
		System.out.println(System.getProperty("user.dir"));
	}
	
	@Test
	void createNewDirectory() throws IOException {
		Path currentPath = Paths.get(System.getProperty("user.dir"));
		Path filePath = Paths.get(currentPath.toString(), "report");
        if (!Files.exists(filePath)) { 
            Files.createDirectory(filePath);
            System.out.println("Directory created");
        } else {
            
            System.out.println("Directory already exists");
        }
	}
}
