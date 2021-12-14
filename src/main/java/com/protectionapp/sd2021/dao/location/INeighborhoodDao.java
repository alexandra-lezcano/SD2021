package com.protectionapp.sd2021.dao.location;

import com.protectionapp.sd2021.domain.location.NeighborhoodDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INeighborhoodDao extends JpaRepository<NeighborhoodDomain, Integer> {
    // Permite retornar results paginados
    public Page<NeighborhoodDomain> findAll(Pageable pageable);

    public List<NeighborhoodDomain> findAllByCity_Id(Integer city_id);

    boolean existsByCity_Neighborhoods_Id(Integer id);

    Page<NeighborhoodDomain> findByCity_IdEquals(Integer id, Pageable pageable);

    Page<NeighborhoodDomain> findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String name, String description, Pageable pageable);

    Page<NeighborhoodDomain> findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCaseOrCity_NameContainsIgnoreCase(String name, String description, String name1, Pageable pageable);



}
