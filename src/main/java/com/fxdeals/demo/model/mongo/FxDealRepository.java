package com.fxdeals.demo.model.mongo;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fxdeals.demo.entity.FxDeal;

@Repository
public interface FxDealRepository extends MongoRepository<FxDeal, BigDecimal> {

	Optional<FxDeal> findById(BigDecimal id);

}