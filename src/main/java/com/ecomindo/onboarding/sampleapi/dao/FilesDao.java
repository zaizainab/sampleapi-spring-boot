package com.ecomindo.onboarding.sampleapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecomindo.onboarding.sampleapi.model.FilesModel;

@Repository
public interface FilesDao extends JpaRepository<FilesModel, Long> {

	@Query("SELECT a FROM FilesModel a WHERE a.name = :name")
	FilesModel findByName(String name);
}
