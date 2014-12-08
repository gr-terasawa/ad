/*-
 * Copyright (c) 2004-2014 GMO-RESEARCH Inc. All rights reserved.
 * Redistribution in source and binary forms, with or without
 * modification, is limited by contract. 
 */
package jp.gmor.research.ad.web;

import is.tagomor.woothee.Classifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jp.gmor.research.ad.service.SaguPanelUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author usr160056
 *
 */
@Controller
public class AdTruthController {

    private static final Logger LOG = LoggerFactory.getLogger(AdTruthController.class);

    /**
     * a
     * @param id a
     * @param panel a
     * @param country a
     * @param deviceinfo a
     * @param request a
     * @param accept a
     * @param acceptLanguage a
     * @param xForwardedFor a
     * @param userAgent a
     * @param model a
     * @return s
     */
    @ResponseBody
    @RequestMapping(value = "/{id}/{panel}/{country}/{deviceinfo}", method = RequestMethod.GET)
    public String home(@PathVariable Long id, @PathVariable String panel, @PathVariable String country, @PathVariable String deviceinfo, HttpServletRequest request, @RequestHeader(value = "Accept") String accept, @RequestHeader(value = "Accept-Language", defaultValue = "") String acceptLanguage, @RequestHeader(value = "X-Forwarded-For", defaultValue = "") String xForwardedFor, @RequestHeader(value = "User-Agent", defaultValue = "") String userAgent, Model model) {
        LOG.debug("id[{}],panel[{}],country[{}],deviceinfo[{}].", id, panel, country, deviceinfo);
        LOG.debug("Accept = {}", accept);
        LOG.debug("Accept-Language = {}", acceptLanguage);
        LOG.debug("X-Forwarded-For = {}", xForwardedFor);
        LOG.debug("User-Agent = {}", userAgent);
        LOG.debug("IP = {}", request.getRemoteAddr());

        List<String> infoList = new ArrayList<>();
        infoList.add(SaguPanelUtils.convertToJnMonitorID(String.valueOf(id), panel));
        infoList.add(String.valueOf(id));
        infoList.add(panel);
        infoList.add(country);
        infoList.add(deviceinfo);
        infoList.add(request.getRemoteAddr());
        Map<String, String> r = Classifier.parse(userAgent);
        infoList.add(r.get("category")); // => "pc", "smartphone", "mobilephone", "appliance", "crawler", "misc", "unknown"
        infoList.add(r.get("os")); // => os from user-agent, or carrier name of mobile phones
        infoList.add(r.get("os_version")); // => version of operating systems (for some typical cases)
        infoList.add(r.get("name")); // => name of browser (or string like name of user-agent)
        infoList.add(r.get("version")); // => version of browser, or terminal type name of mobile phones
        LOG.info(StringUtils.collectionToDelimitedString(infoList, "\t"));

        return "{'status':'success'}";
    }
}
