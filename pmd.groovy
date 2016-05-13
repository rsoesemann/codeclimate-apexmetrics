#!/usr/bin/env groovy
import groovy.json.JsonSlurper
import groovy.util.FileNameFinder


def appContext = setupContext(args)
def includePaths = new JsonSlurper().parse(new File(appContext.configFile), "UTF-8").include_paths?.join(" ")
def codeFolder = new File(appContext.codeFolder)

def filesToAnalyse = new FileNameFinder().getFileNames(appContext.codeFolder, includePaths)

def i = filesToAnalyse.iterator()
while(i.hasNext()) {
    string = i.next()
    if( !string.endsWith(".cls") && !string.endsWith(".trigger") ) {
        i.remove()
    }
}

filesToAnalyse = filesToAnalyse.toString()
filesToAnalyse = filesToAnalyse.substring(1, filesToAnalyse.length()-1).replaceAll("\\s+","")
if (filesToAnalyse.isEmpty()) {
    System.exit(0)
}

def ruleset
def defaultRulesetLocation = "/usr/src/app/apex-ruleset.xml"
def customRulesetLocation = "/code/apex-ruleset.xml"
if ( new File(customRulesetLocation).exists() ) {
    ruleset = customRulesetLocation
} 
else {
    ruleset = defaultRulesetLocation
}

def pmdCommand = "/usr/src/app/lib/pmd/bin/run.sh pmd -d ${filesToAnalyse} -f codeclimate -R ${ruleset} -l apex -v 35 -failOnViolation false"

def sout = new StringBuffer()
def serr = new StringBuffer()

def process = pmdCommand.execute()
process.consumeProcessOutput(sout, serr)
process.waitForProcessOutput()
if (analysis.exitValue() !=0 ) {
    System.exit(-1)
}

System.out << sout.toString()
System.exit(0)


def setupContext(cmdArgs) {
	def cli = new CliBuilder(usage:"${this.class.name}")
	cli._(longOpt: "configFile", required: true, args: 1, "Path to config.json file")
	cli._(longOpt: "codeFolder", required: true, args: 1, "Path to code folder")
	cli.parse(cmdArgs)
}
