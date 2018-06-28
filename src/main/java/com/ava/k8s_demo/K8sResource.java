 package com.ava.k8s_demo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.internal.guava.ExecutionError;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("k8s")
public class K8sResource {
	Utils Utils = new Utils();

	@Path("GetNamespacesList")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String GetNamespacesList() {
		K8sRestfulClientImpl2 _rK8sRestfulClient = new K8sRestfulClientImpl2("http://192.168.3.222:8001");

		K8sParams params = new K8sParams();
		params.setResourceType(K8sResourceType.NAMESPACES);

		return _rK8sRestfulClient.get(params);
	}

	@Path("CreateNamespace")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String CreateNamespace() {
		K8sRestfulClientImpl2 _rK8sRestfulClient = new K8sRestfulClientImpl2("http://192.168.3.222:8001");
		K8sParams params = new K8sParams();
		params.setResourceType(K8sResourceType.NAMESPACES);
		params.setJson(Utils.getJson("D:\\namespaces.json"));
		return _rK8sRestfulClient.create(params);
	}

	@Path("GetPodList")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String GetPodList() {
		K8sRestfulClientImpl2 _rK8sRestfulClient = new K8sRestfulClientImpl2("http://192.168.3.222:8001");
		K8sParams params = new K8sParams();
		params.setResourceType(K8sResourceType.PODS);
		return _rK8sRestfulClient.get(params);
	}

	@Path("GetDeployList")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String GetDeployList() {
		// "http://192.168.3.222:8001/apis/apps/v1beta1"
		K8sRestfulClientImpl2 _rK8sRestfulClient = new K8sRestfulClientImpl2("http://192.168.3.222:8001");
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

		K8sRestfulClientImpl2 _rK8sRestfulClient = new K8sRestfulClientImpl2("http://192.168.3.222:8001");
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
		// http://192.168.3.222:8001/api/v1
		K8sRestfulClientImpl2 _rK8sRestfulClient = new K8sRestfulClientImpl2("http://192.168.3.222:8001");

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
		// http://192.168.3.222:8001/apis/extensions/v1beta1/ingresses
		K8sRestfulClientImpl2 _rK8sRestfulClient = new K8sRestfulClientImpl2("http://192.168.3.222:8001");
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
		JsonParser parser = new JsonParser();
		// http://192.168.3.222:8001/apis/extensions/v1beta1/ingresses
		K8sRestfulClientImpl2 _rK8sRestfulClient = new K8sRestfulClientImpl2("http://192.168.3.222:8001");
		String res = Utils.getJson("D:\\c01-01.json");
		JsonObject jsonObject = (JsonObject) parser.parse(res);
		JsonArray array = jsonObject.get("items").getAsJsonArray();
		 
		ResponseBuilder rBuilder = null;
		for (int i = 0; i < array.size(); i++) {
			JsonObject jo = array.get(i).getAsJsonObject();
			if (jo.get("kind").getAsString().endsWith("Deployment")) {
				K8sParams params = new K8sParams();
				params.setJson(jo.toString());
				params.setApps(true);
				params.setResourceType(K8sResourceType.DEPLOYMENTS);
				params.setNamespace("deploy");
				try {
					rBuilder =_rK8sRestfulClient.create2(params).status(Response.Status.OK);
				} catch (Exception e) {
					// TODO: handle exception
					return e.getMessage();
				}
			}
			if (jo.get("kind").getAsString().endsWith("Service")) {
				K8sParams params = new K8sParams();
				params.setJson(jo.toString());
				params.setResourceType(K8sResourceType.SERVICES);
				params.setNamespace("deploy");
				try {
					rBuilder =_rK8sRestfulClient.create2(params).status(Response.Status.OK);
				} catch (Exception e) {
					// TODO: handle exception
					return e.getMessage();
				}
			}
			if (jo.get("kind").getAsString().endsWith("Ingress")) {
				K8sParams params = new K8sParams();
				params.setJson(jo.toString());
				params.setExtensions(true);
				params.setResourceType(K8sResourceType.INGRESSES);
				params.setNamespace("deploy");
				try {
					rBuilder =_rK8sRestfulClient.create2(params).status(Response.Status.OK);
				} catch (Exception e) {
					// TODO: handle exception
					return e.getMessage();
				}
			}
		}
		return rBuilder.build().getStatusInfo().toString();
	}
	
	@Path("GetPod")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String GetPod() {
		K8sRestfulClientImpl2 _rK8sRestfulClient = new K8sRestfulClientImpl2("http://192.168.3.222:8001");
		K8sParams params = new K8sParams();
		params.setResourceType(K8sResourceType.PODS);
//		Map<String,String> labels = new HashMap<String,String>();
//		labels.put("app", "nginx");
//		params.setLabels(labels);
//		return _rK8sRestfulClient.list(params);
//		params.setLabels(null);
//		
		Map<String, List<String>> inLabels = new HashMap<String,List<String>>();
		List list = new ArrayList<String>();
		list.add("nginx");
		inLabels.put("deploy", list);
		params.setInLabels(inLabels);
		return _rK8sRestfulClient.list(params);
//		params.setInLabels(null);
//		
//		Map<String,String> fields = new HashMap<String,String>();
//		fields.put("metadata.name", "nginx");
//		params.setLabels(fields);
//		params.setNamespace("deploy");
//		return _rK8sRestfulClient.list(params);
	}

}
