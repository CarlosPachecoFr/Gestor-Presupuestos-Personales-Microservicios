package com.microservice.metas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.metas.entity.MetaEntity;

@Repository
public interface MetaRepository extends JpaRepository<MetaEntity, Long>{

}
