package com.ava.k8s_demo;

public interface RestfulClient {
	public String get(Params params); 	//获取单个资源对象
	public String list(Params params); 	//获取资源对象列表
	public String create(Params params); //创建资源对象
	public String delete(Params params); //删除某个资源对象
	public String update(Params params); //部分更新某个资源对象
	public String updateWithMediaType(Params params,String mediaType);	//通过mediaType，实现Merge
	public String replace(Params params);	//替换某个资源对象
	public String options(Params params);
	public String head(Params params); 
	public void close() ;
}
