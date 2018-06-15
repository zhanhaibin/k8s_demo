package com.ava.k8s_demo;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("k8s")
public class K8sResource {
	Utils Utils =new Utils();
	@Path("GetNamespacesList")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String GetNamespacesList() {
		K8sRestfulClientImpl _rK8sRestfulClient = new K8sRestfulClientImpl("http://101.200.42.71:8001");

		K8sParams params = new K8sParams();
		params.setResourceType(K8sResourceType.NAMESPACES);

		return _rK8sRestfulClient.get(params);
	}
	@Path("CreateNamespace")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String CreateNamespace() {
		K8sRestfulClientImpl _rK8sRestfulClient = new K8sRestfulClientImpl("http://101.200.42.71:8001");
		K8sParams params = new K8sParams();
		params.setResourceType(K8sResourceType.NAMESPACES);
		params.setJson(Utils.getJson("D:\\namespaces.json"));
		return _rK8sRestfulClient.create(params);
	}
	
	@Path("GetPodList")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String GetPodList() {
		K8sRestfulClientImpl _rK8sRestfulClient = new K8sRestfulClientImpl("http://101.200.42.71:8001");
		K8sParams params = new K8sParams();
		params.setResourceType(K8sResourceType.PODS);
		return _rK8sRestfulClient.get(params);
	}
	
	@Path("GetDeployList")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String GetDeployList() {
		//"http://192.168.3.222:8001/apis/apps/v1beta1"
		K8sRestfulClientImpl _rK8sRestfulClient = new K8sRestfulClientImpl("http://101.200.42.71:8001");
		K8sParams params = new K8sParams();
		params.setApps(true);
		params.setResourceType(K8sResourceType.DEPLOYMENTS);
		params.setNamespace("kube-system");
		return _rK8sRestfulClient.get(params);
	}
	@Path("CreateDeploy")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String CreateDeploy() {
		
		K8sRestfulClientImpl _rK8sRestfulClient = new K8sRestfulClientImpl("http://101.200.42.71:8001");
		K8sParams params = new K8sParams();
		params.setApps(true);
		params.setResourceType(K8sResourceType.DEPLOYMENTS);
		params.setJson(Utils.getJson("D:\\c01-01-deploy.json"));
		params.setNamespace("deploy");
		return _rK8sRestfulClient.create(params); 

	}
	
	@Path("CreateService")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String CreateService() {
		//http://192.168.3.222:8001/api/v1
		K8sRestfulClientImpl _rK8sRestfulClient = new K8sRestfulClientImpl("http://101.200.42.71:8001");
		
		K8sParams params = new K8sParams();
		params.setResourceType(K8sResourceType.SERVICES);
		params.setJson(Utils.getJson("D:\\c01-01-service.json"));
		params.setNamespace("deploy");
		return _rK8sRestfulClient.create(params);
	}
	
	@Path("CreateIngress")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String CreateIngress() {
		//http://101.200.42.71:8001/apis/extensions/v1beta1/ingresses
		K8sRestfulClientImpl _rK8sRestfulClient = new K8sRestfulClientImpl("http://101.200.42.71:8001");
		K8sParams params = new K8sParams();
		params.setExtensions(true);
		params.setResourceType(K8sResourceType.INGRESSES);
		params.setJson(Utils.getJson("D:\\c01-01-ing.json"));
		params.setNamespace("deploy");
		return _rK8sRestfulClient.create(params);
	}
	
	@Path("CreateAll")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String CreateAll() {
		//http://101.200.42.71:8001/apis/extensions/v1beta1/ingresses
		K8sRestfulClientImpl _rK8sRestfulClient = new K8sRestfulClientImpl("http://101.200.42.71:8001");
		Utils.getJson("D:\\c01-01-ing.json");
		K8sParams params = new K8sParams();
		params.setExtensions(true);
		params.setResourceType(K8sResourceType.INGRESSES);
		params.setJson(Utils.getJson("D:\\c01-01-ing.json"));
		params.setNamespace("deploy");
		return null;// _rK8sRestfulClient.create(params);
	}
	 
}
