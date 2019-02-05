package enhanceJgitController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import org.eclipse.jgit.api.errors.GitAPIException;

import enhanceJgitService.JGitServiceImpl;

public class EnhanceJgitAdapter {

	static enhanceJgitService.JGitService jGitService;
	private static String REMOTE_URL = null;
	private static String USERNAME = null;
	private static String PASSWORD = null;

	// private static final String REMOTE_URL =
	// "https://github.com/eclipse/jgit.git";
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException, IllegalStateException, GitAPIException {

		Scanner sc = new Scanner(System.in);

		jGitService = new enhanceJgitService.JGitServiceImpl();

		/**
		 * Getting Property file data
		 */
		Properties prop = new Properties();
		InputStream input = null;

		try {
			String filePath = "config.properties";
			// String filename = "config.properties";
			input = JGitServiceImpl.class.getClassLoader().getResourceAsStream(filePath);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filePath);
			}

			// load a properties file from class path, inside static method
			prop.load(input);
			// get the property value and print it out
			REMOTE_URL = prop.getProperty("REMOTE_URL");
			System.out.println("REMOTE_URL : " + REMOTE_URL);
			USERNAME = prop.getProperty("USERNAME");
			System.out.println("USERNAME : " + USERNAME);
			PASSWORD = prop.getProperty("PASSWORD");
			System.out.println("PASSWORD : " + PASSWORD);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
		System.out.println("\n-------------- Welcome to Enhance CPS ----------------\n");

		System.out.println("\n\t 1. CREATE REPOSITORY");
		System.out.println("\n\t 2. CLONE REPOSITORY");
		System.out.println("\n\t 3. ADD REPOSITORY");

		System.out.println("\nEnter your choice : ");
		int choice = sc.nextInt();

		switch (choice) {

		case 1:

			System.out.println("-----------Create Git----------\n");

			//System.out.println("REMOTE_URL :"+REMOTE_URL);
			System.out.println("Enter path (empty directory with '//) :");
			String folderPath = sc.next();
			File directoryPath = new File(folderPath);
			
			jGitService.createGitRepository(directoryPath);
			break;
		case 2:

			System.out.println("-----------Clone Git----------\n");

			//System.out.println("REMOTE_URL :"+REMOTE_URL);
			System.out.println("Enter path (empty directory) :");
			String path = sc.next();
			jGitService.cloneGitRepository(path, REMOTE_URL);
			break;
		case 3:
			System.out.println("----------Add to Git------------\n");
			System.out.println("Enter local path (git repository)");
			String localPath = sc.next();
			
			jGitService.addToGitRepository(localPath,USERNAME,PASSWORD);
			break;
		default:
			System.out.println("Wrong Input");
			break;
		}

	}

}
}
