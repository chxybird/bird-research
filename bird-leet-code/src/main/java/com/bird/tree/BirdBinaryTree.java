package com.bird.tree;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author lipu
 * @Date 2021/5/7 15:44
 * @Description 二叉树 二叉排序树
 */
public class BirdBinaryTree {
    /**
     * 树根节点
     */
    private Node ROOT = new Node();

    static class Node {
        //元数据
        Integer data;
        //左节点
        Node left;
        //右节点
        Node right;
        //父节点
        Node father;
    }

    /**
     * @Author lipu
     * @Date 2021/5/7 15:57
     * @Description 二叉排序树添加
     */
    public void add(Integer data) {
        //如果根节点为空直接插入
        if (ROOT.data == null) {
            ROOT.data = data;
            return;
        }
        //寻找插入的位置 从根节点遍历搜寻位置
        searchAndInsert(ROOT, data);
    }

    private void searchAndInsert(Node node, Integer data) {
        //元素已经存在 不插入直接返回
        if (node.data.equals(data)) {
            return;
        }
        //左子树 递归
        if (data < node.data) {
            //没有子树可递归 即为插入位置
            if (node.left == null) {
                Node e = new Node();
                e.data = data;
                e.father = node;
                node.left = e;
            } else {
                searchAndInsert(node.left, data);
            }
        }
        //右子树 递归
        if (data > node.data) {
            if (node.right == null) {
                Node e = new Node();
                e.data = data;
                e.father = node;
                node.right = e;
            } else {
                searchAndInsert(node.right, data);
            }
        }
    }

    private Node search(Node node, Integer data) {
        //节点为空 不存在节点
        if (node == null) {
            return null;
        }
        //元素已经存在 不插入直接返回
        if (node.data.equals(data)) {
            return node;
        }
        //左子树 递归
        if (data < node.data) {
            return search(node.left, data);
        } else {
            //右子树 递归
            return search(node.right, data);
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/7 17:18
     * @Description 前序遍历 根 左 右
     */
    public void pre() {
        if (ROOT.data == null) {
            System.out.println("[]");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        //根节点开始递归遍历
        preOrder(ROOT, stringBuilder);
        stringBuilder.append("]");
        stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        System.out.println(stringBuilder.toString());
    }

    /**
     * @Author lipu
     * @Date 2021/5/28 16:32
     * @Description 中序遍历 左 根 右
     */
    public void in() {
        List<Node> nodeList=new ArrayList<>();
        if (ROOT.data == null) {
            System.out.println("[]");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        //根节点开始递归遍历
        inOrder(ROOT, stringBuilder,nodeList);
        stringBuilder.append("]");
        stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        System.out.println(stringBuilder.toString());
    }

    /**
     * @Author lipu
     * @Date 2021/5/28 16:55
     * @Description 后续遍历 左 右 根
     */
    public void post() {
        if (ROOT.data == null) {
            System.out.println("[]");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        //根节点开始递归遍历
        postOrder(ROOT, stringBuilder);
        stringBuilder.append("]");
        stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        System.out.println(stringBuilder.toString());
    }


    /**
     * @Author lipu
     * @Date 2021/6/11 17:02
     * @Description 用于删除节点第三种情况获取右子树中序遍历的第一个节点
     */
    private Node inFirst(Node node){
        List<Node> nodeList=new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        inOrder(node, stringBuilder,nodeList);
        stringBuilder.append("]");
        stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        return nodeList.get(0);
    }


    /**
     * @Author lipu
     * @Date 2021/5/28 15:56
     * @Description
     */
    private void preOrder(Node node, StringBuilder stringBuilder) {
        //从根节点开始遍历
        if (node != null) {
            //访问根节点
            stringBuilder.append(node.data).append(",");
            //递归遍历左子树
            preOrder(node.left, stringBuilder);
            //递归遍历右子树
            preOrder(node.right, stringBuilder);
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/28 16:25
     * @Description
     */
    private void inOrder(Node node, StringBuilder stringBuilder,List<Node> nodeList) {
        //从根节点开始遍历
        if (node != null) {
            //递归遍历左子树
            inOrder(node.left, stringBuilder,nodeList);
            //访问节点
            stringBuilder.append(node.data).append(",");
            //加入集合
            nodeList.add(node);
            //递归遍历右子树
            inOrder(node.right, stringBuilder,nodeList);
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/28 16:36
     * @Description 后序遍历
     */
    private void postOrder(Node node, StringBuilder stringBuilder) {
        //从根节点开始遍历
        if (node != null) {
            //递归遍历左子树
            postOrder(node.left, stringBuilder);
            //递归遍历右子树
            postOrder(node.right, stringBuilder);
            //访问节点
            stringBuilder.append(node.data).append(",");
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/28 17:00
     * @Description 删除元素
     * 二叉排序树的删除有三种情况
     * 第一种 叶子结点直接删除
     * 第二种 仅有一个孩子节点 上移子树
     * 第三种 两个孩子节点都在 选择删除节点的右子树中序遍历的第一个元素进行替换
     */
    public void delete(Integer data) {
        Node node = search(ROOT, data);
        //查找二叉树节点存不存在 存在删除 不存在直接返回
        if (node == null) {
            return;
        }
        //第一种情况 叶子结点 直接删除
        if (node.left == null && node.right == null) {
            //当前节点为根节点 不要置null 修改其data即可
            if (node.data.equals(ROOT.data)) {
                node.data = null;
                return;
            } else {
                Node father = node.father;
                if (father.left==node){
                    father.left=null;
                }else {
                    father.right=null;
                }
            }
        }
        //第二种情况 仅有一个孩子节点 上移子树
        if (node.left == null || node.right == null) {
            if (node.left != null) {
                node = node.left;
            } else {
                node = node.right;
            }
            return;
        }

        //第三种情况 两个孩子节点都在(操作三步走)
        //寻找当前删除节点右子树中序遍历的第一个节点N
        Node search= inFirst(node.right);
        //删除该节点N
        delete(search.data);
        //该N节点替换被删除的节点
        search=node;
    }

}
