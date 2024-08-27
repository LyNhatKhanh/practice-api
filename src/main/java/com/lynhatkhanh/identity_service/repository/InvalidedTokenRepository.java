package com.lynhatkhanh.identity_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lynhatkhanh.identity_service.entity.InvalidedToken;

@Repository
public interface InvalidedTokenRepository extends JpaRepository<InvalidedToken, String> {}
