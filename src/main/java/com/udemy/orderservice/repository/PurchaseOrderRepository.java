package com.udemy.orderservice.repository;

import com.udemy.orderservice.entity.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {

  // query the database table to return purchase orders
  List<PurchaseOrder> findByUserId(int userId);

}
