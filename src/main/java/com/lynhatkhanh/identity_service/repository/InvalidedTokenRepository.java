package com.lynhatkhanh.identity_service.repository;

import com.lynhatkhanh.identity_service.entity.InvalidedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidedTokenRepository extends JpaRepository<InvalidedToken, String> {
}
