package com.kodilla.hibernate.manytomany.dao;

import com.kodilla.hibernate.manytomany.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface CompanyDao extends CrudRepository<Company,Integer>{

    @Query
    List<Company> searchByFirstThreeLetters(@Param("NAME") String name,@Param("LENGHT") int lenght);

    @Query
    List<Company> searchCompanyLike(@Param("NAME") String name);
}
