package com.mdp.repository;

import com.mdp.entity.customerAddress.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByCustomerId(Long customerId);

    @Modifying
    @Transactional
    @Query("UPDATE ADDRESS a SET a.currentAddress = false WHERE a.customer.id = :customerId")
    void deactivateCustomerAddresses(@Param("customerId") Long customerId);
}

