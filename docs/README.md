# User Guide

Duke is a command line chatbot that allows you log, query and track progress of various tasks.

## Features 

Duke supports task types such as `todo`, `deadline` and `event`. It is able to operate on these tasks 
such as `add`, `mark`, and `find`. Duke's intelligent parser allows user to type in command in a natural 
tone, and supports abbreviations to increase your productivity. Duke is also able to save the tasks that 
have been load and auto-load them the next time it starts.

### Natural syntax

Duke allows user to key in semi-structured input. For example, `deadline test by 1900 17/01/2021` and
`add deadline test by 17/1/2021 1900`, or `mark 5` and `done 5` infer the same instructions respectively.

### Abbreviation

Duke supports abbreviations, which allows user to perform operations faster and save time. For example,
`add` and `a`, `delete` and `del` can be used interchangably.

### Auto-loading

Duke is able to save the task list using the `save` command and automatically load them back the next time 
Duke starts.

## Usage

### `add` - Add a new task

Example of usage: 

`(add) todo {task name}` \
`(add) deadline {task name} by {time}` \
`(add) event {task name} on {time}` \

Expected outcome:

```
deadline test by 1900 17/01/2021
Added: test
----------------
```


### `list` - List all available tasks

Example of usage: 

`list` \

Expected outcome:

```
list
1.[D] [_] test | 2021-1-17 19:00
2.[D] [_] test2 | 2019-5-12 19:00
3.[E] [_] exercise | 2020-12-25 12:00
----------------
```

### `delete` - Delete a task

Example of usage: 

`del 1` \

Expected outcome:

```
list
1.[D] [_] test | 2021-1-17 19:00
2.[D] [_] test2 | 2019-5-12 19:00
3.[E] [_] exercise | 2020-12-25 12:00
----------------
del 1
Deleted task 1
1.[D] [_] test | 2021-1-17 19:00
----------------
list
1.[D] [_] test2 | 2019-5-12 19:00
2.[E] [_] exercise | 2020-12-25 12:00
```

### `mark`/`done` - Mark a task as done

Example of usage: 

`mark 1` \

Expected outcome:

```
list
1.[D] [_] test | 2021-1-17 19:00
2.[D] [_] test2 | 2019-5-12 19:00
3.[E] [_] exercise | 2020-12-25 12:00
----------------
mark 1
Marked task 1
1.[D] [X] test | 2021-1-17 19:00
----------------
list
1.[D] [X] test | 2021-1-17 19:00
2.[D] [_] test2 | 2019-5-12 19:00
3.[E] [_] exercise | 2020-12-25 12:00
----------------
```

### `find` - Find all tasks that match the input 

Example of usage: 

`find task` 

Expected outcome:

```
list
1.[D] [X] test | 2021-1-17 19:00
2.[D] [_] test2 | 2019-5-12 19:00
3.[E] [_] exercise | 2020-12-25 12:00
----------------
find test
[D] [X] test | 2021-1-17 19:00
[D] [_] test2 | 2019-5-12 19:00
```



