package org.fedorahosted.beaker.remote_model;


/**
 * See TaskStatus class in <a href="https://github.com/beaker-project/beaker/blob/develop/Server/bkr/server/model.py">model.py</a>
 * @see <a href="https://github.com/beaker-project/beaker/blob/develop/Server/bkr/server/model.py">model.py</a>
 * @author vjuranek
 *
 */
public enum TaskStatus {

    NEW,
    PROCESSED,
    QUEUED,
    SCHEDULED,
    WAITING,
    RUNNING,
    COMPLETED,
    CANCELLED,
    ABORTED;
    
}
