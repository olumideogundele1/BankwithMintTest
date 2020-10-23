package com.test.mintbank.repositories;

import com.test.mintbank.models.CardCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardCountRepository extends JpaRepository<CardCount,Long> {

    CardCount findByBin(String bin);

    @Query(value = "select count from cardcounts where id = :id", nativeQuery = true)
    int getNoOfCount(@Param("id") Long id);
}
