package com.github.vjuranek.beaker4j.remote_model;

import org.apache.xmlrpc.XmlRpcException;

import com.github.vjuranek.beaker4j.client.BeakerClient;
import com.github.vjuranek.beaker4j.xmlrpc.client.XmlRpcApi;

public class Watchdog extends RemoteBeakerObject {
    
    public Watchdog(BeakerClient beakerClient) {
        this.beakerClient = beakerClient;
    }
    
    public int getRemaingTime(int taskId) throws XmlRpcException {
        //TODO need a check if job is running
        return (Integer)callOnBeaker(XmlRpcApi.RECIPES_TASKS_WATCHDOG, new Object[] {taskId});
    }

}
