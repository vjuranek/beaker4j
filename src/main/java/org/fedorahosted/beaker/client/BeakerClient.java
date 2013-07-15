package org.fedorahosted.beaker.client;

import org.apache.xmlrpc.XmlRpcException;
import org.fedorahosted.beaker.xmlrpc.client.XmlRpcApi;

public interface BeakerClient {
    
    public Object execute(XmlRpcApi rpcApi, Object[] params) throws XmlRpcException;
    public Object execute(String cmd, Object[] params) throws XmlRpcException;
    
}
