package me.spring.boot.data.biz;

import org.springframework.data.jpa.domain.Specification;

/**
 * @author baiyu
 * 查询准则
 */
public class ObjSpecs {

    /**
     * 查询石家庄的地址
     *
     * @return
     */
    public static Specification<Address> findAddressFromSjz() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("city"), "石家庄");
    }

}

