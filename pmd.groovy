#!/usr/bin/env groovy
import groovy.json.JsonSlurper
import groovy.util.FileNameFinder


def appContext = setupContext(args)
def includePaths = new JsonSlurper().parse(new File(appContext.configFile), "UTF-8").include_paths?.join(" ").replace("./", "")
def codeFolder = new File(appContext.codeFolder)

def filesToAnalyse = new FileNameFinder().getFileNames(appContext.codeFolder, includePaths)

def i = filesToAnalyse.iterator()
while(i.hasNext()) {
    string = i.next()
    if( !string.endsWith(".cls") && !string.endsWith(".trigger") ) {
        i.remove()
    }
}

if (filesToAnalyse.isEmpty()) {
    System.exit(0)
}


def fileList = File.createTempFile("apexmetrics-filelist-", null, null)
fileList.deleteOnExit()
fileList.write filesToAnalyse.join(",\n")
fileList << "\n"

def ruleset
def defaultRulesetLocation = "/usr/src/app/apex-ruleset.xml"
def customRulesetLocation = "/code/apex-ruleset.xml"
if ( new File(customRulesetLocation).exists() ) {
    ruleset = customRulesetLocation
}
else {
    ruleset = defaultRulesetLocation
}

def pmdCommand = "/usr/src/app/lib/pmd/bin/run.sh pmd -filelist ${fileList} -f codeclimate -R ${ruleset} -l apex -v 35 -failOnViolation false"

ProcessBuilder builder = new ProcessBuilder( pmdCommand.split(' ') )

Process process = builder.start()

InputStream stdout = process.getInputStream ()
BufferedReader reader = new BufferedReader (new InputStreamReader(stdout))

while ((line = reader.readLine ()) != null) {
   System.out.println ( line )
}

process.waitForProcessOutput()

if ( process.exitValue() != 0 ) {
    System.exit(-1)
}

System.exit(0)


def setupContext(cmdArgs) {
    def cli = new CliBuilder(usage:"${this.class.name}")
    cli._(longOpt: "configFile", required: true, args: 1, "Path to config.json file")
    cli._(longOpt: "codeFolder", required: true, args: 1, "Path to code folder")
    cli.parse(cmdArgs)
}
