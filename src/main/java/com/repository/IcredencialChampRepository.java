package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.credencialChamp;



@Repository
public interface IcredencialChampRepository extends JpaRepository<credencialChamp, Integer> {

	@Query("select count(c.namecredencialChamp) from credencialChamp c where upper(c.namecredencialChamp)=upper(:namecredencialChamp)")
	public int searchcredencialChamp(@Param("namecredencialChamp") String namecredencialChamp);
	
	@Query("select idcredencialChamp from credencialChamp c where upper(c.namecredencialChamp)=upper(:parametro)")
	public int getIDCredencialChamp(@Param("parametro")String namecredencialChamp);
	
	@Query("from credencialChamp c where lower(c.namecredencialChamp) like lower(concat('%', :parametro,'%'))")
	List<credencialChamp> findBynamecredencialChamp(@Param("parametro")String namecredencialChamp);
	
}
