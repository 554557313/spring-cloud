package com.woasis.consulconsumer.service;

import org.springframework.stereotype.Component;

import com.woasis.consulconsumer.remote.ProducerRemote;

@Component
//@RequestMapping("consumer/fallback")
public class ConsumerFallBackService implements ProducerRemote{
	
	public String fallback() {
		return "fallback";
	}

	@Override
	public String producer() {
		return "fallback";
	}

}
