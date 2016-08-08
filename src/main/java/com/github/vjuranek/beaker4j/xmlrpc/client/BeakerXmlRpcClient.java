package com.github.vjuranek.beaker4j.xmlrpc.client;

import com.github.vjuranek.beaker4j.remote_model.Identity;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

import com.github.vjuranek.beaker4j.client.BeakerClient;
import com.github.vjuranek.beaker4j.remote_model.BeakerJob;


public class BeakerXmlRpcClient implements BeakerClient {
    
    private final XmlRpcClient client;

    public BeakerXmlRpcClient(XmlRpcClient client) {
        this.client = client;
    }

    public Identity authenticate(Identity identity) throws XmlRpcException {
        execute(XmlRpcApi.AUTH_LOGIN_PASSWORD, new Object[] {
            identity.getLogin(),
            identity.getPasswd()
        });
        return new Identity(identity.getLogin(), identity.getPasswd(), this);
    }

    @Deprecated /** Use {@link #authenticate(Identity identity)} instead. */
    public boolean authenticate(String login, String password) {
        try {
            execute(XmlRpcApi.AUTH_LOGIN_PASSWORD, new Object[] {login, password});
        } catch(XmlRpcException e){
            return false;
        }
        return true;
    }
    
    @Override
    public BeakerJob scheduleJob(String jobXml) throws XmlRpcException {
        Object[] params = new Object[] { jobXml };
        String jobId = (String)execute(XmlRpcApi.JOBS_UPLOAD, params);
        return new BeakerJob(jobId, this);
    }
    
    @Override
    public Object execute(String cmd, Object[] params) throws XmlRpcException {
        return doRPCall(cmd, params);
    }
    
    @Override
    public Object execute(XmlRpcApi rpcApi, Object[] params) throws XmlRpcException {
        return doRPCall(rpcApi, params);
    }

    private Object doRPCall(XmlRpcApi rpcApi, Object[] params) throws XmlRpcException {
        return doRPCall(rpcApi.getRpc(), params);
    }
    
    private Object doRPCall(String rpc, Object[] params) throws XmlRpcException {
        Object result = null;
        //try {
            synchronized (client) { // only one thread and/or method is using this connection manager at a time
                result = client.execute(rpc, params);
            }
        /*} catch (XmlRpcException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }*/
        return result;
    }
}
