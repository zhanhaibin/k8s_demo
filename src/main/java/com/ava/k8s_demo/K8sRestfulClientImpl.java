package com.ava.k8s_demo;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;

public class K8sRestfulClientImpl implements K8sRestfulClient {
	private static final String  METHOD_PATCH = "PATCH";
	private String _baseUrl = null;
    Client _client = null;
    public K8sRestfulClientImpl  (String baseUrl) {
		DefaultClientConfig config = new DefaultClientConfig();
		config.getProperties().put(URLConnectionClientHandler.PROPERTY_HTTP_URL_CONNECTION_SET_METHOD_WORKAROUND, true);
		_client =Client.create(config);
		this._baseUrl = baseUrl;
	}
	@Override
	public String get(K8sParams params) {
		// TODO Auto-generated method stub
		WebResource resource = _client.resource(_baseUrl+params.buildPath());
		String response = resource.accept(MediaType.APPLICATION_JSON_TYPE).get(String.class);
		//System.out.println("Get one resource:\n"+response);
		return response;
	}

	@Override
	public String list(K8sParams params) {
		// TODO Auto-generated method stub
		WebResource resource = _client.resource(_baseUrl+params.buildPath());
		System.out.println("URL:"+_baseUrl+params.buildPath());
		String response = resource.accept(MediaType.APPLICATION_JSON_TYPE).get(String.class);
		return response;
	}

	@Override
	public String create(K8sParams params) {
		// TODO Auto-generated method stub
		WebResource resource = _client.resource(_baseUrl+params.buildPath());
		System.out.println("URL:"+_baseUrl+params.buildPath());
		System.out.println("CREATE resource:" + params.getJson());
		String response = (null==params.getJson())? resource.accept(MediaType.APPLICATION_JSON).post(String.class):resource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(String.class,params.getJson());
		return response; 
	}

	@Override
	public String delete(K8sParams params) {
		// TODO Auto-generated method stub
		WebResource resource = _client.resource(_baseUrl+params.buildPath());
		System.out.println("URL:"+_baseUrl+params.buildPath());
		String response = resource.accept(MediaType.APPLICATION_JSON_TYPE).delete(String .class);
		System.out.println("Delete resource" + params.getResourceType().getType() + "/" +params.getName() + "result:\n"+response);
		return response;
	}

	@Override
	public String update(K8sParams params) {
		// TODO Auto-generated method stub
		return updateWithMediaType(params,MediaType.APPLICATION_JSON);
	}

	@Override
	public String updateWithMediaType(K8sParams params, String mediaType) {
		// TODO Auto-generated method stub
		WebResource resource = _client.resource(_baseUrl+params.buildPath());
		System.out.println("URL:"+_baseUrl+params.buildPath());
		System.out.println("Patch resource:"+ params.getJson());
		String response = resource.type(mediaType).accept(MediaType.APPLICATION_JSON_TYPE).method(METHOD_PATCH, String.class,params.getJson());
		System.out.println("UPdate resource:"+params.buildPath() +"result:\n"+ response);
		return response;
	}

	@Override
	public String replace(K8sParams params) {
		WebResource resource = _client.resource(_baseUrl+params.buildPath());
		System.out.println("URL:"+_baseUrl+params.buildPath());
		System.out.println("Replace resource:" + params.getJson());
		String response = resource.type(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).put(String.class,params.getJson());
		System.out.println("Replace resource"+ params.buildPath()+"result:\n"+response);
		// TODO Auto-generated method stub
		return response;
	}

	@Override
	public String options(K8sParams params) {
		WebResource resource = _client.resource(_baseUrl+params.buildPath()); 
		String response = resource.type(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.TEXT_PLAIN_TYPE).options(String.class);
		System.out.println("Get options for resource " + params.getResourceType().getType()+"/"+params.getName()+"result:\n"+response);
		// TODO Auto-generated method stub
		return response;
	}

	@Override
	public String head(K8sParams params) {
		// TODO Auto-generated method stub
		WebResource resource = _client.resource(_baseUrl+params.buildPath()); 
		String response = resource.accept(MediaType.TEXT_PLAIN_TYPE).head().getResponseStatus().toString();
		System.out.println("Get Head for resource" + params.getResourceType().getType()+"/"+params.getName()+"result:\n"+response);
		return response;
	}
	
	@Override
	public void close() {
		_client.destroy();
	}

}
