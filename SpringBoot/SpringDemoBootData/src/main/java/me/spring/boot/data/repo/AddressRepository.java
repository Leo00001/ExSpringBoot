package me.spring.boot.data.repo;

import me.spring.boot.data.biz.Address;
import me.spring.boot.data.biz.Product;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author baiyu
 * <p>
 * Address Dao
 */
public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {

    /**
     * Jpa支持通过方法名称来查询，使用Intellij会自动提示，昊强大
     *
     * @param name 名称
     * @param city 城市
     * @return 集合
     */
    List<Address> findAddressByNameAndCity(String name, String city);

    /**
     * like 和limit使用
     *
     * @param city 城市
     * @return 集合
     */
    List<Address> findFirst2ByCityLike(String city);

    /**
     * 通过@NamedQuery指定的JPQL查询
     * {@link Address}
     *
     * @param province 省份
     * @return 集合
     */
    List<Address> findByProvince(String province);


    /**
     * 注解@Query查询
     *
     * @param detail 模糊地址
     * @return 集合
     */
    @Query(value = "select o from Address o where o.detail like ?1%")
    List<Address> findByDetailStartingWith(String detail);

    /**
     * 更新 @Modifying
     *
     * @param key
     * @param city
     * @return
     */
    @Modifying
    @Transactional
    @Query("update Address o set o.city = :city where o.id = :id")
    int setCity(@Param("id") long key, @Param("city") String city);

    /**
     * 分页查询
     * @param city
     * @return
     */
    Page<Address> findByCity(String city, Pageable pageable);
}
