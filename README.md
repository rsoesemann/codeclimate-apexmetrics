## ApexMetrics - Code Climate engine for Salesforce.com Apex

<img src="https://github.com/Up2Go/codeclimate-apexmetrics/blob/master/resources/ApexMetricsLogo.png" width="150" align="left">

ApexMetrics is a [Code Climate engine](https://codeclimate.com/engines) for the programming language [Apex](https://developer.salesforce.com/docs/atlas.en-us.apexcode.meta/apexcode/) of the [Salesforce.com cloud platform](http://www.salesforce.com/platform/products/force).

If you [connect your Github repositories to Code Climate](https://docs.codeclimate.com/docs/importing-repositories) and enable the Engine [static code analysis](http://stackoverflow.com/questions/49716/what-is-static-code-analysis) is performed on every commit or pull request. The resulting continuous metrics will tell you where code improved or degraded over time and which [hot spots need refactoring](http://blog.xebia.com/static-code-analysis-is-just-tip-of-the-iceberg/).

The engine and [all of](https://github.com/forcedotcom/idecore/tree/b5bf3a1cb6e8d94aaac10f375c771ec8eab821ba/com.salesforce.ide.apex.core/lib) [its "ingredients"](https://github.com/Up2Go/pmd) are open-source so everybody in the community can contribute. The engine was built by a software engineers at [Up2Go](https://github.com/Up2Go) who struggled with enforcing Clean Code into their daily work given [the lack of metrics tools in the Salesforce.com ecosystem](http://salesforce.stackexchange.com/questions/1697/apex-static-code-analysis). Not to forget the [awesome support](https://github.com/adangel) [by](https://github.com/forcedotcom/idecore/issues/167) [many](https://github.com/mrb) [others](https://github.com/sivakumar-kailasam).


**[What we check](#rules) | [How to configure](#configuration) | [How to contribute](#contribute)**


### <a name="rules">What issues are (currently) recognized?</a>


We currently check Apex classes and triggers for the following issues. Those could be easily ported from [existing PMD Java rules](http://pmd.sourceforge.net/snapshot/pmd-java/rules/) and fully applied to Apex. As this engine and its components are open-source everybody can contribute new or improved rules.

- **ExcessiveClassLength:** Long Class files are indications that the class may be trying to do too much. Try to break it down, and reduce the size to something manageable.

- **ExcessiveParameterList:** Methods with numerous parameters are a challenge to maintain, especially if most of them share thesame datatype. These situations usually denote the need for new objects to wrap the numerous parameters.
ExcessiveClassLength: Excessive class file lengths are usually indications that the class may be burdened with excessive responsibilities that could be provided by external classes or functions. In breaking these methodsapart the code becomes more managable and ripe for reuse.

- **ExcessivePublicCount:** Classes with large numbers of public methods and attributes require disproportionate testing effortssince combinational side effects grow rapidly and increase risk. Refactoring these classes intosmaller ones not only increases testability and reliability but also allows new variations to bedeveloped easily.

- **StdCyclomaticComplexity:** Complexity directly affects maintenance costs is determined by the number of decision points in a method plus one for the method entry. The decision points include ‘if’, ‘while’, ‘for’, and ‘case labels’ calls. Generally, numbers ranging from 1-4 denote low complexity, 5-7 denote moderate complexity, 8-10 denotehigh complexity, and 11+ is very high complexity.

- **NcssMethodCount:** This rule uses the NCSS (Non-Commenting Source Statements) algorithm to determine the number of linesof code for a given method. NCSS ignores comments, and counts actual statements. Using this algorithm,lines of code that are split are counted as one.

- **NcssTypeCount:** This rule uses the NCSS (Non-Commenting Source Statements) algorithm to determine the number of linesof code for a given type. NCSS ignores comments, and counts actual statements. Using this algorithm,lines of code that are split are counted as one.

- **NcssConstructorCount:** This rule uses the NCSS (Non-Commenting Source Statements) algorithm to determine the number of linesof code for a given constructor. NCSS ignores comments, and counts actual statements. Using this algorithm,lines of code that are split are counted as one.

- **TooManyFields:** Classes that have too many fields can become unwieldy and could be redesigned to have fewer fields,possibly through grouping related fields in new objects. For example, a class with individual city/state/zip fields could park them within a single Address field.

- **AvoidDeeplyNestedIfStmts:** Avoid creating deeply nested if-then statements since they are harder to read and error-prone to maintain.

- **AvoidSoqlInLoops:** New objects created within loops should be checked to see if they can created outside them and reused.

- **AvoidLogicInTrigger:** As triggers do not allow methods like regular classes they are less flexible and suited to apply good encapsulation style. Therefore delegate the triggers work to a regular class (often called Trigger handler class). See more here: [Apex Trigger Best Practices](https://developer.salesforce.com/page/Trigger_Frameworks_and_Apex_Trigger_Best_Practices)

- **AvoidGlobalModifier:** Global classes should be avoided (especially in managed packages) as they can never be deleted or changed in signature. Always check twice if something needs to be global.Many interfaces (e.g. Batch) required global modifiers in the past but don’t require this anymore. Don’t look yourself in.

- **ClassNamingConventions:** Class names should always begin with an upper case character.

- **MethodNamingConventions:** Method names should always begin with a lower case character, and should not contain underscores.

- **MethodWithSameNameAsEnclosingClass:** Non-constructor methods should not have the same name as the enclosing class.

- **VariableNamingConventions:** A variable naming conventions rule - customize this to your liking. Currently, it checks for final variables that should be fully capitalized and non-final variables that should not include underscores.

See here for a more [detailed description of the Apex rules](http://pmd.sourceforge.net/snapshot/pmd-apex/rules/index.html).

### <a name="configuration">Enable and configure the Engine</a>

Code Climate will not run this engine on your code unless you have this minimal '.codeclimate.yml' (Note the leading dot) in the root directory of your repository. 

```yaml
engines:
  apexmetrics:
    enabled: true
ratings:
  paths:
    - "**.cls"
    - "**.trigger"
```

For more details about Code Climate configuration go to [docs.codeclimate.com](https://docs.codeclimate.com/docs/configuring-your-code-climate-analysis).

##### Exclude files and paths (optional)
To exclude single files or folders from the analysis add a `exclude_paths` section to the file like in this minimal example:

```yaml
engines:
  apexmetrics:
    enabled: true
ratings:
  paths:
    - "**.cls"
    - "**.trigger"
exclude_paths:
  - "/src/triggers"
  - "**.xml"
```

We recommend you to adapt a copy of this [.codeclimate.yml](https://github.com/Up2Go/codeclimate-apex/blob/master/resources/.codeclimate.yml) which already excludes most of the irrelevant Salesforce.com files and folders.

For more details about how to exclude files and folders go to [docs.codeclimate.com](https://docs.codeclimate.com/docs/excluding-files-and-folders).


##### PMD Rule customization (optional)

Not all checks make sense under all circumstances so configurability is crucial when it comes to Code metrix. By adjusting your `.codeclimate.yml` you can eighter disable certain checks [for a single issue](https://docs.codeclimate.com/docs/marking-false-positives) or [for your whole repo](https://docs.codeclimate.com/docs/disabling-individual-checks).

But that's not enough. One key benefit of Code Climate is grading. Grading not only takes the amount of issues into a account but also how severe issues are to fix. To customize if and when code is marked as issue you need to go "one level deeper". The static code analysis performed by this engine uses [an Apex port](https://github.com/pmd/pmd/tree/master/pmd-apex) of the [famous Java tool PMD](https://pmd.github.io/). PMD is very flexible and can be [configured using a so called ruleset xml file](http://pmd.sourceforge.net/pmd-4.3.0/howtomakearuleset.html). 

By default or engine uses this [apex-ruleset.xml](https://github.com/Up2Go/codeclimate-apex/blob/master/apex-ruleset.xml). It enables all rules and runs them with values we came up with as good defaults. Just add a copy of that file to the root directory of your repository and adapt it to your needs.

```xml
<rule ref="rulesets/apex/complexity.xml/ExcessiveClassLength" message="Avoid really long classes (lines of code)">
  <priority>3</priority>
  <properties>
    <!-- Rule specific property defining, when a class is marked as too long -->
    <property name="minimum" value="1000"/>

    <!-- Code Climate specific properties -->
    <property name="cc_remediation_points_multiplier" value="150"/>
    <property name="cc_categories" value="Complexity"/>
    <property name="cc_block_highlighting" value="false"/>
  </properties>	
</rule>
```

Some rules have parameters which define if and when code is marked as issue. To learn how such parameters influence a check go to the readup section of an issue which describs 

<img width="500" src="https://cloud.githubusercontent.com/assets/8180281/15602948/5ed00770-23f8-11e6-9932-97eb44b3f1a6.png">

All properties starting with `cc_` are Code Climate specific and define how the results are displayed or how grades are calculated.

* **cc_remediation_points_multiplier**: This is multiplication factor for the Code Climate Remediation Point default of 50.000. To understand the semantics and importance of Remediation point please check the [docs.codeclimate.com](https://github.com/codeclimate/spec/blob/master/SPEC.md#remediation-points).
* **cc_categories**: The Code Climate specific [categorization](https://github.com/codeclimate/spec/blob/master/SPEC.md#categories). Might differ from PMD.
* **cc_block_highlighting**: Defaults to 'false'. Only the first line of a problematic block is highlighted in the UI. If set to 'true' the whole block is highlighted which currently looks ugly.


### <a name="contribute">How to contribute</a>

There is a lot of room for improvement or extension. Depending on what you want to do you eighter need to fork, extend and pull request this repository or the PMD repository. Please check the [Wiki](https://github.com/Up2Go/codeclimate-apexmetrics/wiki#how-to-contribute) to learn how to contribute. 
