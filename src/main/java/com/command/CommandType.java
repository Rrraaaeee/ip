package com.command;

import java.util.HashMap;
// 1. command type
// 1.1 list
// 1.2 add
// 1.3 delete
// 1.3 find
// 2. action
// 3. time
// 3.1 todo
// 3.2 deadline
// 3.3 event
public enum CommandType {
    LIST,
    ADD,
    DONE,
    DELETE,
    FIND,
    BYE,
    INVALID;

    private static final HashMap<String, CommandType> CommandStr2Type =
        new HashMap<String, CommandType>() {{
            put("list", CommandType.LIST);
            put("add", CommandType.ADD);
            put("done", CommandType.DONE);
            put("delete", CommandType.DELETE);
            put("find", CommandType.FIND);
            put("bye", CommandType.BYE);
            put("invalid", CommandType.INVALID);
        }};
    private static final HashMap<CommandType, String> CommandType2Str =
        new HashMap<CommandType, String>() {{
            put(CommandType.LIST, "list");
            put(CommandType.ADD, "add");
            put(CommandType.DONE, "done");
            put(CommandType.DELETE, "delete");
            put(CommandType.FIND, "find");
            put(CommandType.BYE, "bye");
            put(CommandType.INVALID, "invalid");
        }};

    private static CommandType[] list = CommandType.values();
    // https://stackoverflow.com/questions/6692664/how-to-get-enum-value-from-index-in-java
    public static CommandType getCommandTypebyIndex(int i) {
        return list[i];
    }
    public static CommandType getCommandTypebyStr(String cmdStr) {
        return CommandStr2Type.get(cmdStr);
    }
    public static String getCommandStrbyType(CommandType cmdType) {
        return CommandType2Str.get(cmdType);
    }
    public static boolean isValidCommandStr(String cmdStr) {
        return CommandStr2Type.containsKey(cmdStr);
    }
}
