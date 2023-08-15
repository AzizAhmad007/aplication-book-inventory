package com.pos.repository;

import com.pos.model.MstUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<MstUser, Long> {
    @Query(value = "select mb.* from mst_user mb where mb.user_id = :userId",nativeQuery = true)
    MstUser findByUserId(@Param("userId") Long userId);

    @Query(value = "select mu.* from mst_user mu where lower(mu.user_email)= lower(:userEmail)",nativeQuery = true)
    MstUser findByEmail(@Param("userEmail") String userEmail);

    @Query(value = "select mb.* from mst_user mb order by mb.user_id asc ",nativeQuery = true)
    List<MstUser> findAll();

    public MstUser findByToken(String token);
}
