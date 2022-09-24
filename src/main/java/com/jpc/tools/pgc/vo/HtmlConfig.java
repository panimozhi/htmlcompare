package com.jpc.tools.pgc.vo;

import java.util.List;

import lombok.Data;

@Data
public class HtmlConfig {

	private String key;
	private String pageName;
	private String url;
	private Boolean download;
	private List<String> lookupWords;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Boolean getDownload() {
		return download;
	}
	public void setDownload(Boolean download) {
		this.download = download;
	}
	public List<String> getLookupWords() {
		return lookupWords;
	}
	public void setLookupWords(List<String> lookupWords) {
		this.lookupWords = lookupWords;
	}
	
}
