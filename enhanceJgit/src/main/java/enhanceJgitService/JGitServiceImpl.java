package enhanceJgitService;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.StatusCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class JGitServiceImpl implements JGitService {

	public JGitServiceImpl() {

	}

	public boolean addToGitRepository() {
		try {

			Git git = Git.open(new File("D://GitData//ECps"));
			// git.init().setDirectory("D://SWASTIK_BIN//TEMP").call();
			AddCommand add = git.add();
			try {
				add.addFilepattern(".").call();
				CommitCommand commit = git.commit();
				commit.setMessage("enhance cps code").call();
				try {
					PushCommand pushCommand = git.push();
					pushCommand
							.setCredentialsProvider(new UsernamePasswordCredentialsProvider("swastik93", "tilak@93"));
					pushCommand.call();

					Iterable<RevCommit> log = git.log().call();
					for (RevCommit revCommit : log) {
						System.out.println(revCommit.getCommitTime() + " " + revCommit.getFullMessage() + "");
					}
				} catch (GitAPIException e) {
					throw new RuntimeException(e);
				}
			} catch (NoFilepatternException e) {
				e.printStackTrace();
			} catch (GitAPIException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void cloneGitRepository(String path) {

		try {
			// status = git.status().call();
			StatusCommand status = Git.cloneRepository().setURI("https://github.com/swastik93/EnhanceCPSWorkSpace")
					.setDirectory(new File(path)).call().status();

			// Status status = git.status().call();
			System.out.println("Current status :" + status.toString());

		} catch (Exception ee) {
			System.out.println(ee.getMessage());
		}

	}

}