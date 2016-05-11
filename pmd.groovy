#!/usr/bin/env groovy
import groovy.io.FileType
import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import groovy.util.FileNameFinder
import groovy.util.XmlParser


def appContext = setupContext(args)
def includePaths = new JsonSlurper().parse(new File(appContext.configFile), "UTF-8").include_paths?.join(" ")
def codeFolder = new File(appContext.codeFolder)

def scriptDir = getClass().protectionDomain.codeSource.location.path.replace("/${this.class.name}.groovy","")
def filesToAnalyse = new FileNameFinder().getFileNames(appContext.codeFolder, includePaths).toString()
filesToAnalyse = filesToAnalyse.substring(1, filesToAnalyse.length()-1).replaceAll("\\s+","")

def ruleset
def defaultRulesetLocation = scriptDir + "/apex-ruleset.xml"
def customRulesetLocation = "/apex-ruleset.xml"
if ( new File(customRulesetLocation).exists() ) {
    ruleset = customRulesetLocation
} 
else {
    ruleset = defaultRulesetLocation
}

def pmdCommand = "${scriptDir}/lib/pmd/bin/run.sh pmd -d ${filesToAnalyse} -f codeclimate -R ${ruleset} -l apex -v 35"

def sout = new StringBuffer()
def serr = new StringBuffer()

def process = pmdCommand.execute()
process.consumeProcessOutput(sout, serr)
process.waitFor()
if (process.exitValue() !=0 ) {
	System.err << serr.toString()
} 
else {
	System.out << sout.toString()
}

def setupContext(cmdArgs) {
	def cli = new CliBuilder(usage:"${this.class.name}")
	cli._(longOpt: "configFile", required: true, args: 1, "Path to config.json file")
	cli._(longOpt: "codeFolder", required: true, args: 1, "Path to code folder")
	cli.parse(cmdArgs)
}
