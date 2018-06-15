package com.ava.k8s_demo;

import org.omg.CORBA.PRIVATE_MEMBER;

public enum K8sResourceType {
	NODES("nodes"),
	NAMESPACES("namespaces"),
	SERVICES("services"),
	REPLICATIONCONTROLLERS("replicationcontrollers"),
	PODS("pods"),
	BINDINGS("bindings"),
	ENDPOINTS("endpoints"),
	SERVICEACCOUNTS("serviceaccounts"),
	SECRETS("secrets"),
	EVENTS("events"),
	COMPOMENTSTATUSES("compomentstatuses"),
	LIMITRANGES("limitranges"),
	RESOURCEQUOTAS("resourcequotas"),
	PODTEMPLATES("podtemplates"),
	PERSISTENTVOLUMECLAIMS("persistentvolumeclaims"),
	PERSISTEMVOLUMES("persistentvolumes"),
	DEPLOYMENTS("deployments"),
	INGRESSES("ingresses");
	
	
	private String type;
	private K8sResourceType(String type) {
		// TODO Auto-generated constructor stub
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
