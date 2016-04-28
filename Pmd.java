import java.io.*;
import java.util.*;
import java.text.MessageFormat;

public class Pmd {
	
	private static final String SCRIPT_DIRECTORY = Pmd.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	private static final String DEFAULT_RULESET_LOCATION = SCRIPT_DIRECTORY + "apex-ruleset.xml";
	private static final String CUSTOM_RULESET_LOCATION = "/apex-ruleset.xml";
	private static final String CODE_DIRECTORY = "/code";
	private static final MessageFormat COMMAND = new MessageFormat("{0}lib/pmd/bin/run.sh pmd -d {1} -f codeclimate -R {2}");
	
	
	public static void main(String[] args) {
		String ruleset;
		
		if(new File(CUSTOM_RULESET_LOCATION).exists()) {
			ruleset = CUSTOM_RULESET_LOCATION;
		} else {
			ruleset = DEFAULT_RULESET_LOCATION;
		}
		
		executeCommand(COMMAND.format(new Object[] { SCRIPT_DIRECTORY, CODE_DIRECTORY, "apex-ruleset" } ));
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
			    System.err.println(s);
			}
			 
			System.exit(0);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
	}
}
