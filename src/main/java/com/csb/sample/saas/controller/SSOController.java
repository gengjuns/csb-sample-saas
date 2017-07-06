package com.csb.sample.saas.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.message.AuthRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.csb.sample.model.RegistrationModel;
import com.csb.sample.saas.service.RegistrationService;

/**
 * 
 * 名称： 服务单点登陆<br>
 * 功能： 演示供应商系统如何单点登陆<br>
 * 版本： 1.0<br>
 * 日期： 2017-06<br>
 * 说明： 以下代码只是为了方便供应商集成测试而提供的样例代码，供应商可根据自己需要，按照技术集成文档编写。该代码仅供参考。<br>
 */

@Controller
@RequestMapping(value = "/sso")
public class SSOController {

    private static Logger logger = LoggerFactory.getLogger(SSOController.class);

    // 处理单点登陆时的用来拼装return url
    @Value("${saas.url}")
    private String saasURL;


    /**
     * 在供应商平台的集成信息里面单点登陆URL注册为以下示例url： https://www.your.com/login?openid={openid}&accountid={accountid}
     * 
     * @param openid
     *            用户的openid 如：https://www.yun.com/openid/id/111
     * @param accountId
     *            订购阶段供应商系统返回给大卖场的唯一标示，如：saas-erp-11111111111
     * @return
     */
    @RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
    public String login(@RequestParam("openid") String openid, @RequestParam("accountid") String accountId) {

        try {
            // 开始OpenID Discovery
            String userSuppliedIdentifier = openid;
            DiscoveryInformation discoveryInformation = 
                    RegistrationService.performDiscoveryOnUserSuppliedIdentifier(userSuppliedIdentifier);
            // Store the disovery results in session.
            HttpSession session = getSession();
            HttpServletRequest request = getRequest();
            session.setAttribute("discoveryInformation", discoveryInformation);
            // Create the AuthRequest
            logger.info("saasURL: " + saasURL);
            String returnToUrl = saasURL + request.getContextPath() + "/sso/return";
            AuthRequest authRequest =
                    RegistrationService.createOpenIdAuthRequest(discoveryInformation, returnToUrl);
           
            return "redirect:" + authRequest.getDestinationUrl(true);
        } catch (Exception e) {
            logger.error("common error", e);
        }
        return "";
    }

    // 大卖场鉴定成功后将返回至url
    @RequestMapping(value = "/return", method = { RequestMethod.GET, RequestMethod.POST })
    public String processReturn(Map<String, Object> model) {
        try {
            HttpSession session = getSession();
            HttpServletRequest request = getRequest();
            // extract the receiving URL from the HTTP request
            String returnToUrl = saasURL + request.getContextPath() + "/sso/return";
            RegistrationModel registrationModel = new RegistrationModel();
            DiscoveryInformation discoveryInformation =
                    (DiscoveryInformation) session.getAttribute("discoveryInformation");

            registrationModel =
                    RegistrationService.processReturn
                    (discoveryInformation, request.getParameterMap(), returnToUrl);
            if (registrationModel == null) {
                logger.error("Open ID Confirmation Failed."
                        + " No information was retrieved from the OpenID Provider."
                        + " You will have to enter all information by hand into the text fields provided.");
                return "error";
            }

            // your login logic here
            model.put("name", registrationModel.getFullName());
            model.put("userid", registrationModel.getOpenId()
                    .substring(registrationModel.getOpenId().indexOf("/") + 1));
            model.put("email", registrationModel.getEmailAddress());
            return "home";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("common error", e);
            return "error";
        }
    }

    protected HttpServletRequest getRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
        return request;
    }

    protected HttpServletResponse getResponse() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = ((ServletRequestAttributes) ra).getResponse();
        return response;
    }

    protected HttpSession getSession() {
        return getRequest().getSession();
    }

}
