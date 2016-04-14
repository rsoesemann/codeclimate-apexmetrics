import java.io.*;
import java.util.*;

public class Apex {
	public static void main(String[] args) {
		String scriptDir = Apex.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String codeDir = "/Users/drenz/git/pmd/pmd-apex/src/test/resources";
		String rules = "apex-ruleset";
		
		executeCommand(scriptDir + "pmd/bin/run.sh pmd -d " + codeDir + " -f codeclimate -R " + rules);

	}
	
	private static void executeCommand(String command) {

		StringBuffer output = new StringBuffer();
		String s = null;
		
		try {
            
			Process p = Runtime.getRuntime().exec(command);
			p.waitFor();
			             
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
 
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}
			 
			while ((s = stdError.readLine()) != null) {
			    System.out.println(s);
			}
			 
			System.exit(0);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
	}
}