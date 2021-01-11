/**
 * 
 */
package com.rest.app.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.app.table.Transactions;

/**
 * @author danielf
 *
 */
public interface TransactionsRepository extends JpaRepository<Transactions, String> {

	List<Transactions> findAllByTransactiontype(String transactiontype, Pageable pageable);

	List<Transactions> findAllByTransactiontype(String transactiontype);

}
