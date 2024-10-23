package com.example.api.service;

import org.springframework.stereotype.Service;

import com.example.api.producer.CouponCreateProducer;
import com.example.api.repository.AppliedUserRepository;
import com.example.api.repository.CouponCountRepository;

@Service
public class ApplyService {

	private final CouponCountRepository couponCountRepository;

	private final CouponCreateProducer couponCreateProducer;

	private final AppliedUserRepository appliedUserRepository;

	public ApplyService(CouponCountRepository couponCountRepository, CouponCreateProducer couponCreateProducer,
		AppliedUserRepository appliedUserRepository) {
		this.couponCountRepository = couponCountRepository;
		this.couponCreateProducer = couponCreateProducer;
		this.appliedUserRepository = appliedUserRepository;
	}

	public void apply(Long userId) {
		Long apply = appliedUserRepository.add(userId);

		if (apply != 1) {
			return;
		}

		long count = couponCountRepository.increment();

		if (count > 100) {
			return;
		}

		couponCreateProducer.create(userId);
	}
}
