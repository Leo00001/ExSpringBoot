package me.spring.boot.data.repo;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.Query;

/**
 * @author baiyu
 * <p>
 * 自定义的Repository
 */
@NoRepositoryBean
public interface ICustomRepository<T, ID> extends PagingAndSortingRepository<T, ID> {


    /**
     * 执行mysql函数
     *
     * @return
     */
    Query executeFunc(String sql);
}
