package tech.weiyi.dynamic.bytecode.compiler;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Hello {
    public void test(Object test) throws Exception {
        int[] ints = new int[100];
        ints[1] = 2;
        ints[3] = ints[1];

        float[][][] floats = new float[3][][];
        floats.toString();

        Date[] dates = new Date[10];
    }

    public void test2() {
        int[] ints = {1, 2, 4};
    }

    public void testList() {
        List<String> list = Arrays.asList("1", "2", "3");
        List<Integer> intList = list.stream().map(Integer::valueOf).collect(Collectors.toList());
    }
}