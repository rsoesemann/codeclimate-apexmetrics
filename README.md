## ApexMetrics - Code Climate Engine for Salesforce.com Apex
A Code Climate Engine for the static code analysis tool [PMD] (https://pmd.github.io/) to analyse [Salesforce.com Apex] (https://developer.salesforce.com/page/Apex) source code.

You can run it on your command line using the [Code Climate CLI] (https://github.com/codeclimate/codeclimate#code-climate-cli) or on the cloud analysis platform [Code Climate] (https://codeclimate.com/).



### Enable the Engine
To use this Code Climate Apex engine you have to add this .codeclimate.yml to the root directory of your repository.

```yaml
engines:
  apex:
    enabled: true
ratings:
  paths:
  - "**.cls"
  - "**.trigger"
```



### Exclude files and paths
If you want to exclude some files or directories from the analysis you can define them in your .codeclimate.yml.
You can find more information about that in the official [documentation](https://docs.codeclimate.com/docs/excluding-files-and-folders).

We recommend you to adapt our [.codeclimate.yml](https://github.com/Up2Go/codeclimate-apex/blob/master/.codeclimate.yml), which is already exluding most of the Salesforce.com irrelevant files.

Example:

```yaml
engines:
  apex:
    enabled: true
exclude_paths:
  - ".codeclimate.yml"
```



### Rule configuration
The engine will use the default [apex-ruleset.xml](https://github.com/Up2Go/codeclimate-apex/blob/master/apex-ruleset.xml), if you want to customize it for your own needs you can adept the file and add it to the root directory of your repository.

Example:

```xml
<rule ref="rulesets/apex/complexity.xml/ExcessiveClassLength" message="Avoid really long classes (lines of code)">
  <priority>3</priority>
  <properties>
    <property name="minimum" value="1000"/>
    <property name="cc_categories" value="Complexity"/>
    <property name="cc_remediation_points_multiplier" value="150"/>
    <property name="cc_block_highlighting" value="false"/>
  </properties>	
</rule>
```

You can configurate the properties of the rules according to your needs.
* **minimum**: The threshold value of the rule
* **cc_categories**: The Code Climate specific [categorization](https://github.com/codeclimate/spec/blob/master/SPEC.md#categories)
* **cc_remediation_points_multiplier**: The multiplier of the Code Climate specific [Remediation Points](https://github.com/codeclimate/spec/blob/master/SPEC.md#remediation-points) (default multiplicand is 50.000)
* **cc_block_highlighting**: Defines if the hole related block or just the first line of the issue should be highlighted at the Code Climate Platform. By default the block highlighting is disabled for reasons of clarity.



### How to contribute
If you want to improve or adapt the engine just fork it. Pull requests are welcome.
[Here] (http://blog.codeclimate.com/blog/2015/07/07/build-your-own-codeclimate-engine/) you can find some information about building you own Code Climate Engine.

The engine is just a wrapper for Apex module of the static code analysis tool PMD. You can find more information about it on our [PMD GitHub repository] (https://github.com/Up2Go/pmd/blob/master/README.md#pmd---salesforcecom-apex).
