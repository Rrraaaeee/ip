package com.command;

import java.util.HashMap;
import java.util.HashSet;

public enum CommandType {
    LIST,
    ADD,
    DONE,
    DELETE,
    FIND,
    BYE,
    SAVE,
    LOAD,
    INVALID;

    private static final HashMap<String, CommandType> CommandStrToType =
        new HashMap<String, CommandType>() {{
            put("list", CommandType.LIST);
            put("add", CommandType.ADD);
            put("done", CommandType.DONE);
            put("delete", CommandType.DELETE);
            put("find", CommandType.FIND);
            put("bye", CommandType.BYE);
            put("save", CommandType.SAVE);
            put("load", CommandType.LOAD);
            put("invalid", CommandType.INVALID);
        }};
    private static final HashMap<CommandType, String> CommandTypeToStr =
        new HashMap<CommandType, String>() {{
            put(CommandType.LIST, "list");
            put(CommandType.ADD, "add");
            put(CommandType.DONE, "done");
            put(CommandType.DELETE, "delete");
            put(CommandType.FIND, "find");
            put(CommandType.BYE, "bye");
            put(CommandType.SAVE, "save");
            put(CommandType.LOAD, "load");
            put(CommandType.INVALID, "invalid");
        }};

    private static final HashSet<CommandType> SimpleCommand =
        new HashSet<CommandType>() {{
            add(CommandType.LIST);
            add(CommandType.SAVE);
            add(CommandType.BYE);
        }};

    private static CommandType[] list = CommandType.values();

    // https://stackoverflow.com/questions/6692664/how-to-get-enum-value-from-index-in-java
    public static CommandType getCommandTypebyIndex(int i) {
        return list[i];
    }

    public static CommandType getCommandTypeByStr(String commandStr) {
        return CommandStrToType.get(commandStr);
    }

    public static String getCommandStrByType(CommandType commandType) {
        return CommandTypeToStr.get(commandType);
    }

    public static boolean isValidCommandStr(String commandStr) {
        return CommandStrToType.containsKey(commandStr);
    }

    /**
     * Check if a command shoud have trailing arguments
     **/
    public static boolean isSimpleCommand(CommandType commandType) {
        if (SimpleCommand.contains(commandType)) {
            return true;
        } else {
            return false;
        }
    }



}
