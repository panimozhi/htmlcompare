package com.jpc.tools.pgc.schedule;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jpc.tools.pgc.config.SchedulingConfig;
import com.jpc.tools.pgc.util.HCException;
import com.jpc.tools.pgc.vo.HtmlConfig;
import com.jpc.tools.pgc.vo.HtmlParseResult;
import com.jpc.tools.pgc.vo.KeyValue;

@Component("hcScheduler")
public class HCScheduler {

	private final static Logger logger = LoggerFactory.getLogger(HCScheduler.class.getName());

	private Map<String, HtmlParseResult> hparser = new HashMap<>();
	
	@Autowired
	private SchedulingConfig schConfig;

	@Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
	public void scheduleTask() throws HCException {
		try {
			var htmls = schConfig.getHtmlConfigurations();
			hparser.clear();
			for (HtmlConfig h : htmls) {

				HtmlParseResult hrs = new HtmlParseResult();
				hrs.setPageName(h.getPageName());
				hparser.put(h.getKey(), hrs);
				try {
					logger.debug("launch url - " + h.getUrl());
					Connection connect = Jsoup.connect(h.getUrl());
					long time = System.currentTimeMillis();
					connect.get();
					
					Response res = connect.response();
					Document onlineDocument = res.parse();
					
					hrs.setAccessStatus(res.statusCode() + "").
					setResponseTime(System.currentTimeMillis()-time).setOnline(true);
					for (String lookUpWord : h.getLookupWords()) {
						hrs.addPair(new KeyValue(lookUpWord, onlineDocument.html().contains(lookUpWord)+""));
					}

				} catch (HttpStatusException e) {
					hrs.setOnline(false);
				} catch (IOException e) {
					logger.error("Jsoup read failed", e);
				}
			
			}
		} catch (Exception e) {
			throw new HCException("Jsoup read failed", e);
		}
	}

	@Bean
	public Collection<HtmlParseResult> getResult() {
		return hparser.values();
	}

}
