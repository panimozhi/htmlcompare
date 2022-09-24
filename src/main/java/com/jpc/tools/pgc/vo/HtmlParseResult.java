package com.jpc.tools.pgc.vo;

import java.util.ArrayList;
import java.util.List;

public class HtmlParseResult {

	private String pageName;
	private String accessStatus;
	private Boolean online;
	private Boolean found;
	private Long responseTime;
	private List<KeyValue> listPair;
	public String getPageName() {
		return pageName;
	}
	public HtmlParseResult setPageName(String pageName) {
		this.pageName = pageName;
		return this;
	}
	public String getAccessStatus() {
		return accessStatus;
	}
	public HtmlParseResult setAccessStatus(String accessStatus) {
		this.accessStatus = accessStatus;
		return this;
	}
	public Boolean getOnline() {
		return online;
	}
	public HtmlParseResult setOnline(Boolean online) {
		this.online = online;
		return this;
	}
	public Boolean getFound() {
		return found;
	}
	public void setFound(Boolean found) {
		this.found = found;
	}
	public Long getResponseTime() {
		return responseTime;
	}
	public HtmlParseResult setResponseTime(Long responseTime) {
		this.responseTime = responseTime;
		return this;
	}
	public void addPair(KeyValue v) {
		if(getListPair() == null)
			listPair = new ArrayList<>();
		getListPair().add(v);
	}
	public HtmlParseResult setListPair(List<KeyValue> listPair) {
		this.listPair = listPair;
		return this;
	}
	public List<KeyValue> getListPair() {
		return listPair;
	}
	
	
}
