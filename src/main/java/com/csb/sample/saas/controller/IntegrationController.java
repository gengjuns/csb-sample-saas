package com.csb.sample.saas.controller;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csb.sample.SampleConstant;
import com.csb.sample.model.SaasRequest;
import com.csb.sample.model.SaasResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 名称： 服务集成<br>
 * 功能： 演示供应商系统如何将系统集成到信息消费大卖场<br>
 * 版本： 1.0<br>
 * 日期： 2015-11<br>
 * 说明： 以下代码只是为了方便供应商集成测试而提供的样例代码，供应商可根据自己需要，按照技术集成文档编写。该代码仅供参考。<br>
 */
@Controller
@RequestMapping(value = "/saas")
public class IntegrationController {

    private static Logger logger = LoggerFactory.getLogger(IntegrationController.class);

    /**
     * 该方法处理从创客超市平台传递过来的请求，包括：订购，更新，取消，用户分配，取消用户分配
     * 
     * @param url
     *            创客超市平台数据接口地址
     * @return 处理结果信息
     */
    @RequestMapping(value = "/integration", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<SaasResponse> provision(@RequestParam("url") String url) {

        ObjectMapper mapper = new ObjectMapper();
        SaasResponse saasResponse = new SaasResponse();
        saasResponse.setReturnCode(SampleConstant.FAILED);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            CloseableHttpResponse response = null;
            String responseEvent = "";
            try {
                // 从供应商平台的集成信息里面取得consumerKey 和consumerSecret
                String consumerKey = "df82d80c-5406-4b";
                String consumerSecret = "c070bb73-d3f4-4737-ad79-576165fa7c18";
                logger.debug(">>>>>> consumerKey " + consumerKey + "; consumerSecret: " + consumerSecret);
                OAuthConsumer consumer = new DefaultOAuthConsumer(consumerKey, consumerSecret);
                // 给url签名
                logger.debug("url: " + url);
                String signUrl = consumer.sign(url);
                logger.debug("signUrl: " + signUrl);

                // 调用数据接口得到数据
                HttpGet request = new HttpGet(signUrl);
                response = httpclient.execute(request);
                HttpEntity entity = response.getEntity();
                responseEvent = EntityUtils.toString(entity);
                logger.info("Event Details: \n" + responseEvent);
            } finally {
                if (response != null) {
                    response.close();
                }

            }
            // 解析返回来的json数据
            logger.info("start to handle provision logic..........");
            SaasRequest saas = mapper.readValue(responseEvent, SaasRequest.class);
            
            Thread.sleep(10*1000);
            if ("SUBSCRIPTION".equals(saas.getType())) {
                if ("CREATE".equals(saas.getAction())) {
                    logger.info("start to hanle create subscription logic");
                    // your create logic here
                    saasResponse.setReturnCode(SampleConstant.SUCCESS);
                    saasResponse.setMessage("SaaS ERP 订购创建成功");
                    saasResponse.setAccountId("saas-erp-11111111111");
                    saasResponse.setUserAccountId("useradmin-erp-111111111");
                    logger.info("end to hanle create subscription logic");

                } else if ("UPDATE".equals(saas.getAction())) {
                    logger.info("start to hanle update subscription logic");
                    // your update logic here
                    saasResponse.setReturnCode(SampleConstant.SUCCESS);
                    saasResponse.setMessage("SaaS ERP 订购更新成功");
                    logger.info("end to hanle update subscription logic");
                } else if ("CANCEL".equals(saas.getAction())) {
                    logger.info("start to hanle cancel subscription logic");
                    // your cancel logic here
                    saasResponse.setReturnCode(SampleConstant.SUCCESS);
                    saasResponse.setMessage("SaaS ERP 订购取消成功");
                    logger.info("end to hanle cancel subscription logic");
                } 

            } else if ("ASSIGNMENT".equals(saas.getType())) {
                if ("ASSIGN".equals(saas.getAction())) {
                    logger.info("start to hanle assign logic");
                    // your assign logic here
                    saasResponse.setReturnCode(SampleConstant.SUCCESS);
                    saasResponse.setMessage("SaaS ERP 分配成功");
                    saasResponse.setUserAccountId("user-o87cvcvc88kkvckvcvcv");
                    logger.info("end to hanle assign logic");
                } else if ("UNASSIGN".equals(saas.getAction())) {
                    logger.info("start to hanle unassign logic");
                    // your unassign logic here
                    saasResponse.setReturnCode(SampleConstant.SUCCESS);
                    saasResponse.setMessage("SaaS ERP 取消分配成功");
                    logger.info("end to hanle unassign logic");
                }

            }

        } catch (Exception e) {
            logger.error("privision failed", e);
        } finally {
            try {
                httpclient.close();
            } catch (Exception e) {
                logger.error("network problem", e);
            }
            logger.info("end to handle provision logic..........");
        }
        return new ResponseEntity<SaasResponse>(saasResponse, HttpStatus.OK);
    }

}
