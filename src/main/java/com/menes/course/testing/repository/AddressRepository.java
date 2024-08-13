package com.menes.course.testing.repository;

import com.menes.course.testing.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a from Address  a WHERE a.user.id = :userId")
    List<Address> findAllByUserId(Long userId);
}
