package com.bird.stream;

import com.bird.stream.material.Student;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author lipu
 * @Date 2021/1/23 13:46
 * @Description Stream流
 */
public class Client {
    private static List<Student> studentList = new ArrayList<>();

    //集合初始化
    static {
        studentList.add(new Student(1, "张三", 11, "1班"));
        studentList.add(new Student(1, "张三", 11, "1班"));
        studentList.add(new Student(2, "李四", 13, "1班"));
        studentList.add(new Student(3, "王五", 15, "1班"));
        studentList.add(new Student(4, "赵六", 9, "1班"));
        studentList.add(new Student(5, "小红", 8, "2班"));
        studentList.add(new Student(6, "小丽", 12, "2班"));
        studentList.add(new Student(7, "小明", 10, "2班"));
    }

    /**
     * @Author lipu
     * @Date 2021/1/23 14:11
     * @Description forEach 遍历
     */
    public static void forEach() {
        studentList.forEach((System.out::println));
    }

    /**
     * @Author lipu
     * @Date 2021/1/23 14:23
     * @Description count 计算数量
     */
    public static void count() {
        long count = studentList.stream().count();
        System.out.println(count);
    }

    /**
     * @Author lipu
     * @Date 2021/1/23 14:23
     * @Description filter 过滤
     */
    public static void filter() {
        studentList.stream().filter((item) -> {
            return item.getAge() > 11;
        }).forEach(item -> {
            System.out.println(item);
        });
    }

    /**
     * @Author lipu
     * @Date 2021/1/23 14:29
     * @Description limit 截取前n
     */
    public static void limit() {
        long count = studentList.stream().limit(3).count();
        System.out.println(count);
    }

    /**
     * @Author lipu
     * @Date 2021/1/23 14:31
     * @Description skip 跳过前n
     */
    public static void skip() {
        long count = studentList.stream().skip(3).count();
        System.out.println(count);
    }

    /**
     * @Author lipu
     * @Date 2021/1/23 14:32
     * @Description map 流转换
     */
    public static void map() {
        studentList.stream().map(Student::getName)
                .forEach(System.out::println);
    }

    /**
     * @Author lipu
     * @Date 2021/1/23 14:43
     * @Description sorted 排序
     */
    public static void sorted() {
        studentList.stream().sorted((o1, o2) -> o1.getAge() - o2.getAge())
                .forEach(System.out::println);
    }

    /**
     * @Author lipu
     * @Date 2021/1/23 14:53
     * @Description distinct 去重
     */
    public static void distinct() {
        studentList.stream().distinct().forEach(item -> {
            System.out.println(item);
        });
    }

    /**
     * @Author lipu
     * @Date 2021/1/23 15:10
     * @Description max 最大值
     */
    public static void max() {
        Optional<Student> optional = studentList.stream().max((o1, o2) -> o1.getAge() - o2.getAge());
        Student student = optional.get();
        System.out.println(student);
    }

    /**
     * @Author lipu
     * @Date 2021/1/23 15:11
     * @Description min 最小值
     */
    public static void min() {
        Optional<Student> optional = studentList.stream().min((o1, o2) -> o1.getAge() - o2.getAge());
        Student student = optional.get();
        System.out.println(student);
    }

    /**
     * @Author lipu
     * @Date 2021/1/23 15:58
     * @Description 匹配
     */
    public static void match() {
        //任意满足
        boolean AnyResult = studentList.stream().anyMatch((student -> {
            return student.getAge() > 11;
        }));
        System.out.println(AnyResult);
        //没有满足
        boolean noneMatch = studentList.stream().noneMatch((student -> {
            return student.getAge() > 11;
        }));
        System.out.println(noneMatch);
        //全部满足
        boolean allMatch = studentList.stream().allMatch((student -> {
            return student.getAge() > 11;
        }));
        System.out.println(allMatch);
    }

    /**
     * @Author lipu
     * @Date 2021/1/23 16:29
     * @Description reduce 迭代
     */
    public static void reduce() {
        //计算年龄和
        Integer reduce = studentList.stream()
                .map((Student::getAge))
                .reduce(0, (x, y) -> {
                    return x + y;
                });
        System.out.println(reduce);
    }

    /**
     * @Author lipu
     * @Date 2021/1/23 16:43
     * @Description concat 流合并
     */
    public static void concat(){
        Stream<Integer> streamA = studentList.stream().map((Student::getAge));
        Stream<String> streamB = studentList.stream().map((Student::getName));
        Stream<? extends Serializable> stream = Stream.concat(streamA, streamB);
        stream.forEach(System.out::println);
    }

    /**
     * @Author lipu
     * @Date 2021/1/23 16:49
     * @Description collect 结果收集
     */
    public static void collect(){
        //收集转换为集合
        List<String> collect = studentList.stream()
                .map((Student::getName))
                .collect(Collectors.toList());
        //收集获取最大值
        Optional<Student> maxStudent = studentList.stream()
                .collect(Collectors.maxBy(((o1, o2) -> o1.getAge() - o2.getAge())));
        System.out.println(maxStudent.get());

        //分组
        Map<String, List<Student>> groupMap = studentList.stream().collect(Collectors.groupingBy((student -> {
            return student.getClassName();
        })));
        groupMap.forEach((key,value)->{
            System.out.println(key+":"+value);
        });

        //分区 符合条件的一个区 不符合条件的一个区
        Map<Boolean, List<Student>> partMap = studentList.stream().collect(Collectors.partitioningBy(student -> {
            return student.getAge() > 10;
        }));
        System.out.println(partMap);

        //拼接
        String join = studentList.stream().map((student -> {
            return student.getName();
        })).collect(Collectors.joining("_"));
        System.out.println(join);
    }

    /**
     * @Author lipu
     * @Date 2021/1/23 17:20
     * @Description 并行流
     */
    public static void parallelStream(){
        //直接获取并行流
        studentList.parallelStream().forEach((item)->{
            System.out.println(Thread.currentThread().getName()+":"+item);
        });
        //将串行流转换为并行流 或者将并行流转换为串行流
        studentList.stream().parallel();
    }



    public static void main(String[] args) {
        parallelStream();
    }
}
