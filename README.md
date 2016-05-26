## ApexMetrics - Code Climate engine for Apex

ApexMetrics is a [Code Climate engine](https://codeclimate.com/engines) for the programming language [Apex](https://developer.salesforce.com/docs/atlas.en-us.apexcode.meta/apexcode/) of the [Salesforce.com cloud platform](http://www.salesforce.com/platform/products/force).

If you [connect your Github repositories to Code Climate](https://docs.codeclimate.com/docs/importing-repositories) and enable the Engine [static code analysis](http://stackoverflow.com/questions/49716/what-is-static-code-analysis) is performed on every commit or pull request. The resulting continuous metrics will tell you where code improved or degraded over time and which [hot spots need refactoring](http://blog.xebia.com/static-code-analysis-is-just-tip-of-the-iceberg/).

The engine and [all of](https://github.com/forcedotcom/idecore/tree/b5bf3a1cb6e8d94aaac10f375c771ec8eab821ba/com.salesforce.ide.apex.core/lib) [its "ingredients"](https://github.com/Up2Go/pmd) are open-source so everybody in the community can contribute improvements. The engine was built by a few engineers at [Up2Go](https://github.com/Up2Go) (including [great](https://github.com/Up2Go/pmd/pull/7) [support](https://github.com/forcedotcom/idecore/issues/167) [by others](https://github.com/sivakumar-kailasam/codeclimate-pmd)) who struggled with enforcing Clean Code into their daily work given [the lack of metrics tools in the Salesforce.com ecosystem](http://salesforce.stackexchange.com/questions/1697/apex-static-code-analysis). 

### Enable and configure the Engine

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
  - "/triggers"
  - "**.xml"
```

We recommend you to adapt a copy of this [.codeclimate.yml](https://github.com/Up2Go/codeclimate-apex/blob/master/resources/.codeclimate.yml) which already excludes most of the irrelevant Salesforce.com files and folders.

For more details about how to exclude files and folders go to [docs.codeclimate.com](https://docs.codeclimate.com/docs/excluding-files-and-folders).


##### PMD Rule customization (optional)

The static code analysis performed by this engine uses [an Apex port](https://github.com/pmd/pmd/tree/master/pmd-apex) of the [famous Java tool PMD](https://pmd.github.io/). PMD is very flexible and can be [configured using a so called ruleset xml file](http://pmd.sourceforge.net/pmd-4.3.0/howtomakearuleset.html). You can include or exclude certain rules and customize rule-specific parameters to influence if or when violations are detected.

By default or engine uses this [apex-ruleset.xml](https://github.com/Up2Go/codeclimate-apex/blob/master/apex-ruleset.xml). It enables all rules and runs them with values we came up with as good defaults. Just add a copy of that file to the root directory of your repository and adapt it to your needs.

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

All properties starting with `cc_` are Code Climate specific and define how the results are displayed and how grade and ratings are calculated.

* **cc_categories**: The Code Climate specific [categorization](https://github.com/codeclimate/spec/blob/master/SPEC.md#categories). Might differ from PMD.
* **cc_remediation_points_multiplier**: Multiplication factor for the Code Climate [Remediation Point](https://github.com/codeclimate/spec/blob/master/SPEC.md#remediation-points) (default value is 50.000)
* **cc_block_highlighting**: Defaults to 'false'. Only the first line of a problematic block is highlighted in the UI. If set to 'true' the whole block is highlighted which currently looks ugly.


### How to contribute

There is a lot of room for improvement or extension. Depending on what you want to do you eighter need to fork, extend and pull request this repository or the PMD repository.

##### Add or improve existing PMD rules

If you want to add rules that work similarly to the ones we already have you will have to extend PMD itsself. Check the PMD README.md to learn how to contribute. 

To get a new version of PMD into this engine this is what you need to do.

TODO

##### Upgrade PMD version used by the engine 

Contributions made to PMD will not be automatically used by the Code Climate engine. You have to replace the current build of PMD in the engine with new one. Those are the steps you need to do:

TODO

##### Add new engine component

Beside extending the underlying PMD framework people could also add totally new mechanisms to evaluate code. Code would then not only run through PMD but also through other internal components. Just imagine using the Tooling Api and then reporting back on usused code across class boundaries as [implemented by Salesforce.com MVP Andrew Fawcett](https://andyinthecloud.com/2013/02/02/spring-cleaning-apex-code-with-the-tooling-api/).
