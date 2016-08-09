package com.github.vjuranek.beaker4j.remote_model;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Status of a beaker task.
 *
 * @see <a href="https://github.com/beaker-project/beaker/blob/master/Server/bkr/server/model/types.py">https://github.com/beaker-project/beaker/blob/master/Server/bkr/server/model/types.py</a>
 */
public enum TaskStatus {

    NEW,
    PROCESSED,
    QUEUED,
    SCHEDULED,
    WAITING,
    INSTALLING,
    RUNNING,
    RESERVED,
    COMPLETED,
    CANCELLED,
    ABORTED,
    UNKNOWN; // Client failed to understand the status

    public static TaskStatus fromString(String s) {
        try {
            return valueOf(s);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TaskStatus.class.getName()).log(Level.SEVERE, "Unable to parse TaskStatus from " + s);
        }
        return UNKNOWN;
    }
}
