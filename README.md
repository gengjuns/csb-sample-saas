# 服务集成示例代码
该示例是服务商的服务产品集成至创客超市的Java示例, 示例代码只是为了方便服务商集成测试而提供的样例代码，供应商可根据自己需要，按照技术集成文档编写。该代码仅供参考, 
示例代码主要包括
* 处理从创客超市传递过来的请求，包括：订购，更新，取消，用户分配，取消用户分配, 代码位于com.csb.sample.saas.controller.IntegrationController
* 单点登录, com.csb.sample.saas.controller.SSOController

##启动  
mvn clean install tomcat7:run  -Dmaven.tomcat.uriEncoding=UTF-8
