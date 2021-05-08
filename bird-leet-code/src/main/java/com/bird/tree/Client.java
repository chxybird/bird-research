package com.bird.tree;

/**
 * @Author lipu
 * @Date 2021/5/7 17:11
 * @Description
 */
public class Client {
    public static void main(String[] args) {
        BirdBinaryTree tree=new BirdBinaryTree();
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.add(1);
        tree.preOrder();
    }

}
