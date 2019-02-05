package enhanceJgitController;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.eclipse.jgit.api.errors.GitAPIException;

public class EnhanceJgitAdapter {

	static enhanceJgitService.JGitService jGitService;

	// private static final String REMOTE_URL =
	// "https://github.com/eclipse/jgit.git";
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException, IllegalStateException, GitAPIException {

		Scanner sc = new Scanner(System.in);

		jGitService = new enhanceJgitService.JGitServiceImpl();

		System.out.println("-------------- Welcome to Enhance CPS ----------------\n");

		System.out.println("\n\t 1. CLONE REPOSITORY");
		System.out.println("\n\t 2. ADD REPOSITORY");

		System.out.println("\nEnter your choice : ");
		int input = sc.nextInt();

		switch (input) {

		case 1:

			System.out.println("Clone Git");

			System.out.println("Enter path");
			String path = sc.next();
			jGitService.cloneGitRepository(path);
			break;
		case 2:
			System.out.println("Add to Git");
			jGitService.addToGitRepository();
			break;
		default:
			System.out.println("Wrong Input");
			break;
		}

	}

}
