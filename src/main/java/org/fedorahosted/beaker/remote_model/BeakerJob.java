package org.fedorahosted.beaker.remote_model;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.xmlrpc.XmlRpcException;
import org.fedorahosted.beaker.client.BeakerClient;
import org.fedorahosted.beaker.model.Job;
import org.fedorahosted.beaker.xmlrpc.client.XmlRpcApi;

public class BeakerJob extends RemoteBeakerObject {
    
    private final String jobId;
    
    
    public BeakerJob(String jobId) {
        this.jobId = jobId;
    }
    
    public BeakerJob(String jobId, BeakerClient beakerClient) {
        super(beakerClient);
        this.jobId = jobId;
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
        String resp = (String)callOnBeaker(XmlRpcApi.JOBS_TO_XML, new Object[] {getId()});
        int beg = resp.indexOf("<job");
        int end = resp.indexOf("</job>") + 6;
        String jobStr = resp.substring(beg, end);
        String xml = jobStr.replace("\\\"", "\"");
        return xml;
    }
    
    public void cancel(String message) throws XmlRpcException {
        System.out.println("jobId je " + jobId);
        callOnBeaker(XmlRpcApi.JOBS_STOP, new Object[] {getId(), StopType.cancel.toString(), message});
    }
    
    
    private Integer getId() {
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
