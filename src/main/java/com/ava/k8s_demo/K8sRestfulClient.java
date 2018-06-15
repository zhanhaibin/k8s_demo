package com.ava.k8s_demo;

public interface K8sRestfulClient {
	public String get(K8sParams params); 	//获取单个资源对象
	public String list(K8sParams params); 	//获取资源对象列表
	public String create(K8sParams params); //创建资源对象
	public String delete(K8sParams params); //删除某个资源对象
	public String update(K8sParams params); //部分更新某个资源对象
	public String updateWithMediaType(K8sParams params,String mediaType);	//通过mediaType，实现Merge
	public String replace(K8sParams params);	//替换某个资源对象
	public String options(K8sParams params);
	public String head(K8sParams params); 
	public void close() ;
}
