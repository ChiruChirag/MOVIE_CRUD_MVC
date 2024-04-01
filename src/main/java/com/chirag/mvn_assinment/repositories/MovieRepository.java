package com.chirag.mvn_assinment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chirag.mvn_assinment.model.Movie;

@Repository

public interface MovieRepository extends JpaRepository<Movie,Long>{
	

}
