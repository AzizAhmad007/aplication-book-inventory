package com.pos.repository;

import com.pos.model.MstItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<MstItem, Long> {
    @Query(value = "select mb.* from mst_item mb where mb.item_id = :itemId",nativeQuery = true)
    MstItem findByItemId(@Param("itemId") Long itemId);

    @Query(value = "select mb.* from mst_item mb order by mb.item_id asc ",nativeQuery = true)
    List<MstItem> findAll();
}
