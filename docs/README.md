# User Guide

Duke is a command line chatbot that helps you log, query and track progress of various tasks.

## Table of Content
1. [Features](#Features)
    1. [Natural syntax](#Natural-syntax)
    2. [Abbreviation](#Abbreviation)
2. [Getting Started](#Getting-Started)
3. [Usage](#Usage)

## Features 

Duke's intelligent parser allows user to type in command in a natural language 
and supports abbreviations to avoid less typing. Duke is also able to save the tasks and
automatically load them back the next time it starts.

### Natural syntax

Duke allows user to key in semi-structured input. For example, `deadline test by 1900 17/01/2021` and
`add deadline test by 17/1/2021 1900`, or `mark 5` and `done 5` infer the same instructions respectively.
Duke also understands various time formats automatically. For eample `1900 17/1/2021`, `17/01/2021 1900` 
and `1900 17/1` all means the same time.

### Abbreviation

Duke supports abbreviations, which allows user to perform operations faster and save time. For example,
`add` and `a`, or `delete` and `del` can be used interchangably.

### Auto-loading

Duke is able to save the task list using the `save` command and automatically load them back the next time 
Duke starts.


## Getting Started

Start Duke by running `java -jar dir/to/duke.jar`. Duke create a `tmp/` folder under your current directory
for its storage. If `tmp/` already exist, it will look for `storage.txt` for saved tasks and automatically 
load them back. \

After Duke is ran successfully, you should see a command prompt where you can key in your commands.
You may add a todo by `add todo hello`. \

Now lets create another task, `deadline`. Run `add deadline exam by 1800 1/10/2021`, this will create 
a deadline with time. Note that the leading `add` can be omitted and the parser will automatically infer it. 

You may run `list` to check if these tasks are created successfully. It should show something like the following,
```
1. [T] [_] hello
2. [D] [_] exam @ 2021-10-01 18:00
```
The first bracket tells the type of the task. The second bracket tells if a task has been marked. \

To mark task 2, run `mark 2`. To delete task 1, run `delete 1`. Run `list` again. You should see task 1 has been
deleted and task 2 has been marked.
```
1. [D] [X] exam @ 2021-10-01 18:00
```
Alternatively, you can also find this task by `find exam`. \

If you are unsure about some commands, type `help` to see available commands. Now you are ready to go!

## Usage

Duke supports a set of operations and task types that fulfil most of your daily task tracking needs. There
are three task types `todo`, `deadline` and `event` where deadline and event types need time to be specified.
Duke supports the following set of operations:
+ add
+ mark
+ delete
+ list
+ find
+ save
+ load
+ help
+ exit
Details of these operations are described below.

### `add` - Add a new task

Example of usage: 

`(add) todo {task name}` 
`(add) deadline {task name} by {time}` 
`(add) event {task name} on {time}` 

Expected outcome:

```
>> deadline test by 1900 17/01/2021
---------------
1900 17/01/2021
I have added task: [D] [_] test @ 2021-01-17 19:00
---------------
```


### `list` - List all available tasks

Example of usage: 

`list` \

Expected outcome:

```
>> list
---------------
Here are your available tasks:
1. [E] [_] party @ 2021-09-30 04:00
2. [T] [X] pay school fee
3. [D] [X] revise math @ 2021-10-01 12:00
----------------
```

### `delete` - Delete a task

Example of usage: 

`del 1` \

Expected outcome:

```
>> list
---------------
Here are your available tasks:
1. [E] [_] party @ 2021-09-30 04:00
2. [T] [X] pay school fee
3. [D] [X] revise math @ 2021-10-01 12:00
---------------
>> del 3
---------------
I have helped you delted task:
[D] [X] revise math @ 2021-10-01 12:00
---------------
>> list
---------------
Here are your available tasks:
1. [E] [_] party @ 2021-09-30 04:00
2. [T] [X] pay school fee
---------------
```

### `mark`/`done` - Mark a task as done

Example of usage: 

`mark 1` \

Expected outcome:

```
>> list
---------------
Here are your available tasks:
1. [E] [_] party @ 2021-09-30 04:00
2. [T] [X] pay school fee
3. [D] [X] revise math @ 2021-10-01 12:00
---------------
>> mark 1
---------------
I have helped you marked your task:
[E] [X] party @ 2021-09-30 04:00
---------------
>> list
---------------
Here are your available tasks:
1. [E] [X] party @ 2021-09-30 04:00
2. [T] [X] pay school fee
3. [D] [X] revise math @ 2021-10-01 12:00
---------------
```

### `find` - Find all tasks that match the input 

Example of usage: 

`find task` 

Expected outcome:

```
>> list
---------------
Here are your available tasks:
1. [E] [X] party @ 2021-09-30 04:00
2. [T] [X] pay school fee
3. [D] [X] revise math @ 2021-10-01 12:00
4. [T] [_] pay Yvonne
---------------
>> find pay
---------------
Found: [T] [X] pay school fee
Found: [T] [_] pay Yvonne
---------------
```

### `save` - Save the current task list

Example of usage: 

`save` \


### `load` - Load available tasks from storage

Example of usage: 

`load` \

### `help` - Get the help manual that shows list of available commands

Example of usage: 

`help` \

Expected outcome:

```
>> help
---------------
========== HELP =========
(add) todo [task name]
(add) deadline [task name] by [HHmm dd/MM/yyyy]
(add) event [task name] on [HHmm dd/MM/yyyy]
find [task name]
mark [task id]
delete [task id]
...
...
```
