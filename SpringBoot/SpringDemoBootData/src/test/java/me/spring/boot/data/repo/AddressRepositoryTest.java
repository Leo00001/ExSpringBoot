package me.spring.boot.data.repo;

import me.spring.boot.data.biz.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 单元测试测试Jpa数据操作
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(AddressRepositoryTest.class.getSimpleName());

    @Resource
    private AddressRepository repository;

    @Test
    public void testQuery() {
        List<Address> list = repository.findByDetailStartingWith("承德二中高三9板");
        print(list.toString());
    }

    @Test
    public void testNameQuery() {
        List<Address> list = repository.findByProvince("河北省2");
        print(list.toString());
    }

    @Test
    public void testQueryByMethodName() {
        List<Address> list = repository.findAddressByNameAndCity("刘大帅哥", "承德市");
        print(list.toString());
    }

    @Test
    public void testQueryLike() {

        List<Address> list = repository.findFirst2ByCityLike("%承德%");
        print(list.toString());
    }


    @Test
    public void testOne() {
        Optional<Address> addressOptional = repository.findById(1L);
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            print(address.toString());
        }
    }

    @Test
    public void testList() {
        List<Address> list = repository.findAll();
        print(list.toString());
    }

    /**
     * 排序查询，支持多字段
     * List<T> findAll(Sort sort);
     * public static Sort by(Sort.Order... orders)
     */
    @Test
    public void testFindOrder() {
        List<Address> list = repository.findAll(Sort.by(Sort.Order.desc("name"), Sort.Order.asc("province")));
        print(list.toString());
    }

    @Test
    public void testSave() {
        Address address = new Address();
        address.setCity("承德市");
        address.setDetail("承德二中高三9板");
        address.setMobile("15101555555");
        address.setPostCode("068150");
        address.setName("刘大帅哥");
        address.setProvince("河北省");
        Address saveRecord = repository.save(address);
        print(saveRecord.toString());
    }

    @Test
    public void testSaveAll() {
        List<Address> list = new ArrayList<>();
        Address address = new Address();
        address.setCity("承德市2");
        address.setDetail("承德二中高三9板2");
        address.setMobile("15101555552");
        address.setPostCode("068152");
        address.setName("刘大帅哥2");
        address.setProvince("河北省2");
        list.add(address);

        Address address2 = new Address();
        address2.setCity("承德市3");
        address2.setDetail("承德二中高三9板3");
        address2.setMobile("15101555553");
        address2.setPostCode("068153");
        address2.setName("刘大帅哥3");
        address2.setProvince("河北省");
        list.add(address2);

        List<Address> saveResultList = repository.saveAll(list);
        print(saveResultList.toString());
    }

    @Test
    public void testCount() {
        long count = repository.count();
        print("==========address table has record : " + count);
    }

    private void print(String msg) {
        logger.info("\n\n" + msg + "\n\n");
    }
}