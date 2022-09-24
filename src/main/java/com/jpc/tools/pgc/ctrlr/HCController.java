package com.jpc.tools.pgc.ctrlr;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpc.tools.pgc.config.SchedulingConfig;
import com.jpc.tools.pgc.schedule.HCScheduler;
import com.jpc.tools.pgc.util.HCException;
import com.jpc.tools.pgc.vo.HtmlConfig;
import com.jpc.tools.pgc.vo.HtmlParseResult;

@RestController
public class HCController {

	@Autowired
	private SchedulingConfig schConfig;

	@Autowired
	private HCScheduler hcs;
	
	
	@GetMapping(value = "/htmlconfiglist")
	public List<HtmlConfig> getHtmlConfigurations() throws HCException {
		return schConfig.getHtmlConfigurations();
	}
	
	
	@GetMapping(value = "/hcreport")
	public List<HtmlParseResult> getHtmlReport() throws HCException {
		return new ArrayList<>(hcs.getResult());
	}
}
