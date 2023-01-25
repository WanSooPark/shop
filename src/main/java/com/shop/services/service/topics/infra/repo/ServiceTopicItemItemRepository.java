package com.shop.services.service.topics.infra.repo;

import com.shop.models.items.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceTopicItemItemRepository extends JpaRepository<Item, Long>, QDSLServiceTopicItemItemRepository {
    @Query(nativeQuery = true, value = ""
            + " SELECT *"
            + " FROM item i"
            + " LEFT JOIN topic_item_x_item tixi on i.id = tixi.item_id"
            + " LEFT JOIN topic_item ti on tixi.topic_item_id = ti.id"
            + " WHERE ti.topic_code = :topicCode"
            + " and ti.id = :topicItemId"
            + " ORDER BY ti.ord"
    )
    Page<Item> searchItem(@Param("topicCode") String topicCode, @Param("topicItemId") Long topicItemId, Pageable pageable);

    @Query(nativeQuery = true, value = ""
            + " SELECT *"
            + " FROM item i"
            + " LEFT JOIN topic_item_x_item tixi on i.id = tixi.item_id"
            + " LEFT JOIN topic_item ti on tixi.topic_item_id = ti.id"
            + " WHERE ti.topic_code = :topicCode"
            + " ORDER BY ti.ord"
    )
    Page<Item> searchItem(@Param("topicCode") String topicCode, Pageable pageable);
}
