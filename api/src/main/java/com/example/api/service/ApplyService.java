package com.example.api.service;

import org.springframework.stereotype.Service;

import com.example.api.producer.CouponCreateProducer;
import com.example.api.repository.CouponCountRepository;

@Service
public class ApplyService {

	private final CouponCountRepository couponCountRepository;

	private final CouponCreateProducer couponCreateProducer;

	public ApplyService(CouponCountRepository couponCountRepository, CouponCreateProducer couponCreateProducer) {
		this.couponCountRepository = couponCountRepository;
		this.couponCreateProducer = couponCreateProducer;
	}

	public void apply(Long userId) {
		long count = couponCountRepository.increment();

		if (count > 100) {
			return;
		}

		couponCreateProducer.create(userId);
	}
}
