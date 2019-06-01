package com.example.demo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RepositoryRestResource(collectionResourceRel = "insertdb", path = "insertdb")
public interface InsertdbRepository extends PagingAndSortingRepository<Insertdb, Long>  {

	/*
	@Query(value = "SELECT * FROM insertdb WHERE File_id=?1", nativeQuery = true)
	Insertdb findResource(Integer i);*/
	

}
