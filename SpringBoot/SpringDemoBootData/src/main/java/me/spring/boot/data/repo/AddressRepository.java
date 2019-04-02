package me.spring.boot.data.repo;

import me.spring.boot.data.biz.Address;
import me.spring.boot.data.biz.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author baiyu
 * <p>
 * Address Dao
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
}
