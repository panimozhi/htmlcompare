package com.jpc.tools.pgc.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jpc.tools.pgc.util.HCException;
import com.jpc.tools.pgc.util.JsonUtils;
import com.jpc.tools.pgc.vo.HtmlConfig;

@Component
public class SchedulingConfig {
	
	private final static Logger logger = LoggerFactory.getLogger(SchedulingConfig.class.getName());
	
	private static Map<String, Document> localHTMLFiles = new HashMap<>();
	
	@Value("classpath:htmls")
	private Resource htmlFolder;
	
	@Value("classpath:htmlconfig.json")
	private Resource resourceFile;
	
	@Bean
    public TaskScheduler taskScheduler() {
		logger.debug("taskscheduler set thread name");
        ThreadPoolTaskScheduler tsk = new ThreadPoolTaskScheduler();
        tsk.setPoolSize(1);
        tsk.setThreadNamePrefix("HCScheduler");
        return tsk;
    }
    
    @Bean
    public List<HtmlConfig> getHtmlConfigurations() throws HCException {
		List<HtmlConfig> properties;
		try {
			properties = JsonUtils.fromJSON(new TypeReference<List<HtmlConfig>>() {
			}, resourceFile.getInputStream());
		} catch (IOException e) {
			throw new HCException();
		}
		return properties;
	}
    
    @Bean
    public void loadLocalHTMLCache() throws HCException {
    	try {
			Arrays.stream(htmlFolder.getFile().listFiles()).forEach(f-> {
				try {
					if(f.getName().toLowerCase().endsWith(".html")) {
						Document doc = Jsoup.parse(f, "UTF-8", "");
						localHTMLFiles.put(f.getName(), doc);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			throw new HCException("stream error in reading local html file", e);
		}
    	
    	
    }
    
    public Document getHTMLDocument(String fileName) {
    	return localHTMLFiles.get(fileName);
    }
}
