package com.ava.k8s_demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType; 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("k8s")
public class k8sResource {
	private static final Logger LOG = LogManager.getLogger(RestfulClient.class.getName());
	
	@Path("test1")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String testNamespacesList() {
		k8sRestfulClient _rK8sRestfulClient = new k8sRestfulClient("http://192.168.3.222:8001/api/v1");

		Params params = new Params();
		params.setResourceType(ResourceType.NAMESPACES);

		return _rK8sRestfulClient.get(params);
	}
	@Path("test")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String testCreateNamespace() {
		k8sRestfulClient _rK8sRestfulClient = new k8sRestfulClient("http://192.168.3.222:8001/api/v1");
	    
		Params params = new Params();
		params.setResourceType(ResourceType.NAMESPACES);
//		params.setJson();
		return _rK8sRestfulClient.get(params);
	}
}
