Prompter4J
==========

Prompter4J is a library to get the user raw input in an interactive manner.

### Maven

Add this dependency in your pom.xml and start using.

```xml
<dependency>
    <groupId>com.github.arulrajnet</groupId>
    <artifactId>prompter4j</artifactId>
    <version>1.0</version>
</dependency>
```

### JAR file

http://central.maven.org/maven2/com/github/arulrajnet/prompter4j/1.0/prompter4j-1.0.jar

### Features

* Type conversion to required object
* Support for choices as Collections, Enum, File listing
* Support for required input
* Support for default value if Input not given

### Roadmap

* Yes or No as type
* Range as an Input
* Date as an Input
* Release in Maven repo
 
Contributors are welcome.
 
### Examples

__Get an integer value__

```java
int dd = Prompter.prompt(new PromptOptions("Enter your age :").
        required(Boolean.TRUE).type(Integer.class));
```
*The Output*
```
Enter your age : 
> df
Give input as Integer
Enter your age : 
> 12
```

__Choose a value from an array__

```java
Integer[] levelArray = {3 ,4, 5};
Integer ee = Prompter.prompt(new PromptOptions("Select Any one :").
            choices(levelArray).required(true).type(Integer.class));
```
*The output*
```
Select Any one : 
3
4
5
> 8
Select from choices
Select Any one : 
3
4
5
> 5
```

__Choose a value from Enum__

```
enum DAY {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}
DAY ff = Prompter.prompt(new PromptOptions("Select your day :").
        choices(DAY.class).defaultValue(DAY.SUNDAY.toString()).type(DAY.class));
```

*The Output*

```
Select your day : Default is SUNDAY
SUNDAY
MONDAY
TUESDAY
WEDNESDAY
THURSDAY
FRIDAY
SATURDAY
> 
SUNDAY
```

More Examples From [PrompterTest.java][testclass]

##### Author

| [![follow][avatar]][twitterhandle] |
|---|
| [@arulrajnet][twitterhandle] |

[twitterhandle]: https://twitter.com/arulrajnet "Follow @arulrajnet on Twitter"
[avatar]: https://avatars0.githubusercontent.com/u/834529?s=70
[testclass]: https://github.com/arulrajnet/prompter4j/blob/master/src/test/java/PrompterTest.java "Test class"