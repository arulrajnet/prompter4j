Prompter4J
==========

Prompter4J is a library to get the user raw input in an interactive manner.

### Features

* Type conversion to required object
* Support for choices as Collections and Enum
* Support for required input
* Support for default value if Input not given

### Roadmap

* Collections as an Input
* File as an Input
* Yes or No as type
* Range as an Input
* Date as an Input
* Release in Maven repo
* More detailed error / help messages
 
There is no ETA for the above roadmap. I will work on whenever get time. I will try release before Diwali :) . Contributors welcome.  
 
### Example

__Get an integer value__

```java
int dd = (Integer) Prompter.prompt(new PromptOptions("Enter your age :").
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
Integer ee = (Integer)Prompter.prompt(new PromptOptions("Select Any one :").
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
DAY ff = (DAY) Prompter.prompt(new PromptOptions("Select your day :").
        choices(DAY.values()).defaultValue(DAY.SUNDAY.toString()).type(DAY.class));
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


##### Author

| [![follow][avatar]][twitterhandle] |
|---|
| [@arulrajnet][twitterhandle] |

[twitterhandle]: https://twitter.com/arulrajnet "Follow @arulrajnet on Twitter"
[avatar]: https://avatars0.githubusercontent.com/u/834529?s=70