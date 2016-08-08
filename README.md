# Beaker4j

Beakerj4 is a Java library for XML-RPC communication with [Beaker](https://beaker-project.org/).

To include Beaker4j into you project, add following dependency to your `pom.xml`:
```xml
<dependency>
    <groupId>com.github.vjuranek.beaker4j</groupId>
    <artifactId>beaker4j</artifactId>
    <version>1.0.1</version>
</dependency>
```

Simple example how to use the Beaker client in your app could be as follows:
```java
BeakerClient beakerClient = BeakerServer.getXmlRpcClient(beakerURL);
Identity identity = beakerClient.authenticate(new Identity(login, password));
BeakerJob job = beakerClient.scheduleJob(jobXml);
String jobId = job.getJobId();
```
