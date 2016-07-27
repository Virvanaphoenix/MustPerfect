package com.feng.mustwin.inter;


/** 
 * @author wuwf
 * @version 创建时间：2014-12-1 下午4:56:45 
 * 类说明 
 */
public abstract class GetDataCallBack {

	public void start() {
		
	}
	public void finish() {
		
	}
	/**
	 * 成功返回并且返回字符串
	 * @param content
	 * 返回的字符串
	 */
	public void success(String content) {
		
	}
	/**
	 * 成功返回并且返回对象
	 * 返回的对象
	 */
	public <T> void successBean(T bean) {
		
	}
	/**
	 * 失败调用
	 */
	public void failure() {
		
	}
}
