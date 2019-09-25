package com.jijo.module.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jijo.lawrence
 *
 */
@Controller
public class ErrorController {
    @RequestMapping(value = "/error", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String, Object> handle(HttpServletRequest request) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", request.getAttribute("javax.servlet.error.status_code"));
        map.put("reason", request.getAttribute("javax.servlet.error.message"));
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        map.put("exception", (exception == null ? null : exception.toString()));

        return map;
    }

    // TODO redirect all exceptions to a common handler
}
