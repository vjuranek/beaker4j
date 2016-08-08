package com.github.vjuranek.beaker4j.remote_model;

import java.util.HashMap;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;

import com.github.vjuranek.beaker4j.client.BeakerClient;
import com.github.vjuranek.beaker4j.xmlrpc.client.XmlRpcApi;

/**
 * User identity.
 * 
 * @author vjuranek
 */
public class Identity extends RemoteBeakerObject {

    public static final String SYS_PROP_LOGIN_KEY = "beaker.client.login";
    public static final String SYS_PROP_PASSWD_KEY = "beaker.client.passwd";
    
    private final String login;
    private final String passwd;
    
    public Identity() {
        this.login = System.getProperty(SYS_PROP_LOGIN_KEY);
        this.passwd = System.getProperty(SYS_PROP_PASSWD_KEY);
    }
    
    public Identity(String login, String passwd) {
        this.login = login;
        this.passwd = passwd;
    }

    public Identity(String login, String passwd, BeakerClient beakerClient) {
        super(beakerClient);
        this.login = login;
        this.passwd = passwd;
    }
    
    public Identity(BeakerClient beakerClient) {
        super(beakerClient);
        this.login = System.getProperty(SYS_PROP_LOGIN_KEY);
        this.passwd = System.getProperty(SYS_PROP_PASSWD_KEY);
    }
    
    public String getLogin() {
        return login;
    }

    public String getPasswd() {
        return passwd;
    }

    @Deprecated /** Use {@link BeakerClient#authenticate(Identity identity)} instead. */
    public boolean authenticate() {
        try {
            beakerClient.authenticate(this);
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Deprecated /** Use {@link BeakerClient#authenticate(Identity identity)} instead. */
    public boolean authenticate(String login, String passwd) {
        try {
            beakerClient.authenticate(new Identity(login, passwd));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public String whoAmI() {
        Map<String,String> me = new HashMap<String,String>(); 
        try{
            //@SuppressWarnings("unchecked")
            me = (Map<String,String>)callOnBeaker(XmlRpcApi.AUTH_WHO_AM_I, new Object[] {});
        } catch(XmlRpcException e) {
            if(e.getMessage().contains("Please log in first")) {
                me.put("username", "unauthenticated");   
            } else {
                me.put("username", "unknown");
                System.err.println("ERROR: " + e.getMessage());
            }
        }
        return me.get("username");
    }
}
