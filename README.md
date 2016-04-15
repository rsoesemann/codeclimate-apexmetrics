# codeclimate-apex
Code Climate Engine for Salesforce.com Apex (based on PMD)

###.codeclimate.yml (engine configuration)
```yaml
engines:
  apex:
    enabled: true
    config: 'codeclimate-apex.xml'
```

###codeclimate-apex.xml (rule configuration)
```xml
<ruleset name="Customizable ruleset used by the CodeClimate Engine for Salesforce.com Apex"
	     xmlns="http://pmd.sourceforge.net/ruleset/2.0.0"
	     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	     xsi:schemaLocation="http://pmd.sourceforge.net/ruleset/2.0.0 http://pmd.sourceforge.net/ruleset_2_0_0.xsd">
	<description>Customizable ruleset used by the Code Climate Engine for Salesforce.com Apex</description>

	<rule ref="rulesets/apex/basic.xml/AvoidSoqlInLoops"/>
	<rule ref="rulesets/apex/basic.xml/AvoidLogicInTrigger"/>
	<rule ref="rulesets/apex/basic.xml/AvoidGlobalModifier"/>
	
	<rule ref="rulesets/apex/codesize.xml/StdCyclomaticComplexity"/>
	<rule ref="rulesets/apex/codesize.xml/ExcessiveClassLength"/>
	<rule ref="rulesets/apex/codesize.xml/ExcessiveParameterList"/>
	<rule ref="rulesets/apex/codesize.xml/NcssConstructorCount"/>
	<rule ref="rulesets/apex/codesize.xml/NcssMethodCount"/>
	<rule ref="rulesets/apex/codesize.xml/NcssTypeCount"/>
	<rule ref="rulesets/apex/codesize.xml/TooManyFields"/>
	<rule ref="rulesets/apex/codesize.xml/ExcessivePublicCount"/>

	<rule ref="rulesets/apex/design.xml/AvoidDeeplyNestedIfStmts"/>
	
	<rule ref="rulesets/apex/naming.xml/VariableNamingConventions"/>
	<rule ref="rulesets/apex/naming.xml/MethodNamingConventions"/>
	<rule ref="rulesets/apex/naming.xml/ClassNamingConventions"/>
	<rule ref="rulesets/apex/naming.xml/MethodWithSameNameAsEnclosingClass"/>

</ruleset>
```
