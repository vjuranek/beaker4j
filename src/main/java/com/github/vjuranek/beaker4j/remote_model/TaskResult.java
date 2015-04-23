package com.github.vjuranek.beaker4j.remote_model;

/**
 * See TaskResult class in <a href="https://github.com/beaker-project/beaker/blob/develop/Server/bkr/server/model.py">model.py</a>
 * @see <a href="https://github.com/beaker-project/beaker/blob/develop/Server/bkr/server/model.py">model.py</a>
 * @author vjuranek
 *
 */
public enum TaskResult {
    
    NEW,
    PASS,
    WARN,
    FAIL,
    PANIC,
    NONE;
    
}
