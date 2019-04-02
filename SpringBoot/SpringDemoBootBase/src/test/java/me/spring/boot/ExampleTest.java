package me.spring.boot;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class ExampleTest {

    @Test
    public void testAutoIncrement() {
        int i = 0;
        i = i++;
        Assert.assertEquals(i, 0);
    }

    @Test
    public void testArraysConvertList() {
        // 基本类型的数组再转换为集合时候会被视为一个对象
        int[] test = new int[]{1, 2, 3, 4};
        List list = Arrays.asList(test);
        System.out.println(list.get(0));
        Assert.assertEquals(list.size(), 1);
    }

    @Test
    public void testEquals() {
        // 相等，因为两个集合都是AbstractList子类，equal方法都是父类方法，相同的比较
        List<String> list = new ArrayList<>();
        list.add("This is Jack");
        Vector<String> vector = new Vector<>();
        vector.add("This is Jack");
        Assert.assertEquals(list, vector);
    }

    @Test
    public void testReflectGeneric() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Integer> list = new ArrayList<>();
        Method method = list.getClass().getMethod("add", Object.class);
        method.invoke(list, "Java反射机制实例。");

        System.out.println(list.get(0));
    }
}
