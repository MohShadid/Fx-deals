package com.progressSoft.demo.model.mongo;

import com.progressSoft.demo.entity.FxDeal;


import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FxDealRepository extends MongoRepository<FxDeal, BigDecimal> {

	Optional<FxDeal> findById(BigDecimal id);

}