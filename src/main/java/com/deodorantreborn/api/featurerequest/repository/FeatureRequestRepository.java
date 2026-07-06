package com.deodorantreborn.api.featurerequest.repository;

import com.deodorantreborn.api.featurerequest.entity.FeatureRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FeatureRequestRepository extends JpaRepository<FeatureRequest, UUID> {}
