package com.cn.common.util.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortTest {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "1", 1));
        list.add(new User(1, "1", 2));
        list.add(new User(2, "2", 2));
        list.add(new User(3, "3", 3));
        list.add(new User(1, "3", 1));

        SetMapSort<User> userSetMapSort = new SetMapSort<>(list, Comparator.comparingInt(o1 ->
                o1.getAge()));

        userSetMapSort.consumer(o -> {
            System.out.println(o);
        });
        List<User> to = new ArrayList<>();
        userSetMapSort.transfer(to);
        System.out.println(to);

    }
}
