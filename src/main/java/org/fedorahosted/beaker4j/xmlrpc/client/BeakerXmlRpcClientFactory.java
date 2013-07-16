package org.fedorahosted.beaker4j.xmlrpc.client;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;
import org.fedorahosted.beaker4j.client.BeakerClient;
import org.fedorahosted.beaker4j.client.BeakerClientFactory;


public class BeakerXmlRpcClientFactory implements BeakerClientFactory {
    
	private static final String RPC_PATH = "/RPC2";
	
	private final String server;
	private BeakerClient client;

	public BeakerXmlRpcClientFactory(String server) {
		this.server = server;
	}

	public synchronized BeakerClient getClient() {
		if (client == null) {
			client = createClient(server);
		}
		return client;
	}

	public static BeakerClient getClient(String server) {
	    return createClient(server);
	}
	
	private static BeakerClient createClient(String server) {
		XmlRpcClient client = new XmlRpcClient(); 
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		client.setTypeFactory(new XmlRpcTypeNil(client)); // allow nil return by python
		try {
		    if(server.endsWith("/"))
		        server = server.substring(0, server.length()-1);
			config.setServerURL(new URL(server + RPC_PATH));
			config.setEnabledForExtensions(true); // we need this because of nil return by python (allowed in beaker) ?? not really, this is ext:nil, not nil
		} catch (MalformedURLException e) {
			//LOGGER.severe(e.getMessage());
		}
		client.setConfig(config);
		
		// setup http transport
		XmlRpcCommonsTransportFactory factory = new XmlRpcCommonsTransportFactory(client);
        HttpClient httpClient = new HttpClient();
        factory.setHttpClient(httpClient);
        client.setTransportFactory(factory);
		
		return new BeakerXmlRpcClient(client);
	}

}
