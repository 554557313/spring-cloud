package com.woasis.consulconsumer.remote;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.woasis.consulconsumer.service.ConsumerFallBackService;

/**
 * 调用生产者服务
 */
@FeignClient(name="ghw",fallback=ConsumerFallBackService.class)
public interface ProducerRemote {

	@RequestMapping(value="/producer/get",method=RequestMethod.GET)
    String producer();
	
}
