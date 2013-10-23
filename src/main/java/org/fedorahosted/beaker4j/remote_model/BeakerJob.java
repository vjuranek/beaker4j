package org.fedorahosted.beaker4j.remote_model;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.xmlrpc.XmlRpcException;
import org.fedorahosted.beaker4j.client.BeakerClient;
import org.fedorahosted.beaker4j.model.Job;
import org.fedorahosted.beaker4j.xmlrpc.client.XmlRpcApi;

public class BeakerJob extends RemoteBeakerObject {
    
    private final String jobId;
    
    
    public BeakerJob(String jobId) {
        this.jobId = jobId;
    }
    
    public BeakerJob(String jobId, BeakerClient beakerClient) {
        super(beakerClient);
        this.jobId = jobId;
    }

    public String getJobId() {
        return jobId;
    }
    
    public Job getJob() throws XmlRpcException, JAXBException, UnsupportedEncodingException {
        String jobXml = getJobXml();
        JAXBContext jaxbContext = JAXBContext.newInstance(Job.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Job job = (Job)jaxbUnmarshaller.unmarshal(new ByteArrayInputStream(jobXml.getBytes("UTF-8")));
        return job;
    }
    
    public String getJobXml() throws XmlRpcException {
        @SuppressWarnings("unchecked")
        String resp = (String)callOnBeaker(XmlRpcApi.JOBS_TO_XML, new Object[] {getJobNumber()});
        int beg = resp.indexOf("<job");
        int end = resp.indexOf("</job>") + 6;
        String jobStr = resp.substring(beg, end);
        String xml = jobStr.replace("\\\"", "\"");
        return xml;
    }
    
    public ArrayList<Map<String, String>> getFiles()  throws XmlRpcException {
        Object[] results = (Object[])callOnBeaker(XmlRpcApi.TASKACTIONS_FILES, new Object[] {jobId});
        System.out.println("Beaker4j files: " + results[0].getClass().getCanonicalName());
        ArrayList<Map<String,String>> resp = new ArrayList<Map<String,String>>();
        for(Object o : results)
            if(o instanceof Map) {
                resp.add((Map)o);
            }
        return resp;
    }
    
    public void cancel(String message) throws XmlRpcException {
        System.out.println("jobId je " + jobId);
        callOnBeaker(XmlRpcApi.JOBS_STOP, new Object[] {getJobNumber(), StopType.cancel.toString(), message});
    }
    
    
    private Integer getJobNumber() {
        Integer jobIdNum;
        if(jobId.startsWith("J:")) {
            jobIdNum = new Integer(jobId.substring(2, jobId.length()));
        } else {
            jobIdNum = new Integer(jobId);
        }
        return jobIdNum;
    }
    
    public enum StopType {
        abort,
        cancel;
    }
}
