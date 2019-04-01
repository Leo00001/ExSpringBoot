package me.spring.boot.data.jpa;

import me.spring.boot.data.biz.Address;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author baiyu
 * <p>
 * Jpa测试用例
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JpaTest {

    private Logger logger
            = LoggerFactory.getLogger(JpaTest.class.getSimpleName());

    @Resource
    private JpaRepository<Address, Long> repository;

    @Before
    public void before() {
        logger.info("==========================test before \n");
    }

    @After
    public void after() {
        logger.info("==========================test after \n");
    }

    @Test
    public void testQueryAll() {
        List<Address> list = repository.findAll();
        logger.info(list.toString());
    }
}
