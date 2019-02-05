package enhanceJgitService;

import java.io.File;

public interface JGitService {
	
	public void createGitRepository(File localPath);
	
	public boolean addToGitRepository(String localPath, String USERNAME, String PASSWORD);
	
	public void cloneGitRepository(String path,  String REMOTE_PATH);
	
	//public String myProperties();

}
