package com.github.vjuranek.beaker4j.remote_model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Result of a Beaker task.
 *
 * @see <a href="https://github.com/beaker-project/beaker/blob/master/Server/bkr/server/model/types.py">https://github.com/beaker-project/beaker/blob/master/Server/bkr/server/model/types.py</a>
 */
public enum TaskResult {
    
    NEW,
    PASS,
    WARN,
    FAIL,
    PANIC,
    NONE,
    UNKNOWN; // Client failed to understand the status

    public static TaskResult fromString(String s) {
        try {
            return valueOf(s);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TaskStatus.class.getName()).log(Level.SEVERE, "Unable to parse TaskResult from " + s);
        }
        return UNKNOWN;
    }
}
