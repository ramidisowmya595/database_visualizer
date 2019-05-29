package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "insertdb", path = "insertdb")
public interface InsertdbRepository extends PagingAndSortingRepository<Insertdb, Long> {

	@Query(value = "SELECT * FROM resource_table WHERE File_id=?1", nativeQuery = true)
	Insertdb findResource(Integer i);
	

}
