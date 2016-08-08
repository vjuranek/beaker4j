package com.github.vjuranek.beaker4j.client;

import com.github.vjuranek.beaker4j.remote_model.Identity;
import org.apache.xmlrpc.XmlRpcException;

import com.github.vjuranek.beaker4j.remote_model.BeakerJob;
import com.github.vjuranek.beaker4j.xmlrpc.client.XmlRpcApi;
import org.apache.xmlrpc.client.XmlRpcClient;

public interface BeakerClient {

    Identity authenticate(Identity identity) throws XmlRpcException;
    BeakerJob scheduleJob(String jobXml) throws XmlRpcException;
    
    Object execute(XmlRpcApi rpcApi, Object[] params) throws XmlRpcException;
    Object execute(String cmd, Object[] params) throws XmlRpcException;
}
