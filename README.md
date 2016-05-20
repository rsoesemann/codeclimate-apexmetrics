# Code Climate Salesforce.com Apex Engine
This is a Code Climate Engine for the static code analysis tool [PMD] (https://pmd.github.io/) to analyse [Salesforce.com Apex] (https://developer.salesforce.com/page/Apex) source code.

You can run it on your command line using the [Code Climate CLI] (https://github.com/codeclimate/codeclimate#code-climate-cli) or on the cloud analysis platform [Code Climate] (https://codeclimate.com/).

### Engine configuration (.codeclimate.yml)
If you want to use this Code Climate Apex engine you have to add this .codeclimate.yml to the root directory of your repository.

```yaml
engines:
  apex:
    enabled: true
```

If you want to exclude some files or directories from the analysis you can define them in your .codeclimate.yml.
You can find more information about that in the official [documentation](https://docs.codeclimate.com/docs/excluding-files-and-folders).

Example:

```yaml
engines:
  apex:
    enabled: true
exclude_paths:
  - ".codeclimate.yml"
```

### Rule configuration (apex-ruleset.xml)
The engine will use the default [apex-ruleset.xml](https://github.com/Up2Go/codeclimate-apex/blob/master/bin/apex-ruleset.xml), if you want to customize it for your own needs you can adept the file and add it to the root directory of your repository. 

