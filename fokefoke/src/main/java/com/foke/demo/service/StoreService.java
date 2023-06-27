package com.foke.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.foke.demo.dto.StoreDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StoreService {
	@Autowired
	StoreRepository storeRepository;

	public Page<StoreDTO> getList(int page) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("storeId"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return this.storeRepository.findAll(pageable);
	}
	
	public Page<StoreDTO> search(String keyword,int page){
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("storeId"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		Page<StoreDTO> storeList = storeRepository.findByStoreAddressContaining(keyword,pageable);
		return storeList;
	}
	
}
