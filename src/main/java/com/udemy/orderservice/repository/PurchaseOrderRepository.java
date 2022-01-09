package com.udemy.orderservice.repository;

import com.udemy.orderservice.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> { }
