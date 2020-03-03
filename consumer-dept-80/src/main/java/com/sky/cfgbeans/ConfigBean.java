package com.sky.cfgbeans;

//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//import com.netflix.loadbalancer.IRule;
//import com.netflix.loadbalancer.RetryRule;

@Configuration
public class ConfigBean //boot -->spring   applicationContext.xml --- @Configuration配置   ConfigBean = applicationContext.xml
{ 
	@Bean
	@LoadBalanced//Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端       负载均衡的工具。
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}

	//Ribbon核心组件IRule，用于定义策略获取规则，若不实现方法，则默认RoundRobinRule()轮询规则
	//也可用自定义规则，自定义规则所在的包不能与注启动类ConsumerDept80Application(@SpringBootApplication)一致，及不能在同一个包下
	//参考包myrule
	@Bean
	public IRule myRule()
	{
		//return new RoundRobinRule(); //轮询规则
		//return new RandomRule();//随机
		return new RetryRule(); //先按照RoundRobinRule()的策略获取服务，如果获取失败，则在指定时间内重试，获取可用策略
	}
}



//@Bean
//public UserServcie getUserServcie()
//{
//	return new UserServcieImpl();
//}
// applicationContext.xml == ConfigBean(@Configuration)
//<bean id="userServcie" class="com.atguigu.tmall.UserServiceImpl">