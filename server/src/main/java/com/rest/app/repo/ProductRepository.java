/**
 * 
 */
package com.rest.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.app.table.Product;

/**
 * @author danielf
 *
 */
public interface ProductRepository extends JpaRepository<Product, String> {

}
