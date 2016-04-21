# codeclimate-apex
Code Climate Engine for Salesforce.com Apex (based on PMD)

###engine configuration (.codeclimate.yml)
If you want to use this Code Climate Apex engine you have to add this .codeclimate.yml to the root directory of your repository.

```yaml
engines:
  apex:
    enabled: true
```

###rule configuration (apex-ruleset.xml)
The engine will use the default [apex-ruleset.xml](https://github.com/Up2Go/codeclimate-apex/blob/master/bin/apex-ruleset.xml), if you want to customize it for your own needs you can adept the file and add it to the root directory of your repository. 

