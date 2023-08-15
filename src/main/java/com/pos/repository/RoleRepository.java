package com.pos.repository;

import com.pos.model.MstRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<MstRole, Long> {
    @Query(value = "select mb.* from mst_role mb where mb.role_id = :roleId",nativeQuery = true)
    MstRole findByRoleId(@Param("roleId") Long roleId);

    @Query(value = "select mb.* from mst_role mb order by mb.role_id asc ",nativeQuery = true)
    List<MstRole> findAll();
}
