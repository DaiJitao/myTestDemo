package person.test.tree;

/**
 * Created by daijitao on 2017/7/1.
 * 二叉搜索树的API
 */
public class Tree {
    private Node root;

    public Node find(int key){
        Node current = root;
        while (current.idata != key) {
            if (key < current.idata) {
                current = current.leftChild;
            } else
                current = current.rightChild;
            if (current == null)
                return current;
        }
        return current;
    }
    public void insert(int id, double dd){
        Node newNode = new Node(id, dd);
        Node current = root;
        if (root == null){
            root = newNode;
        }
        else
        {
            Node parent;
            //while循环一定要写上终止条件
            while (true){
                if (id < current.idata){
                    parent = current;
                    current = current.leftChild;
                    //终止条件在这地方加入
                    if (current == null){
                        parent.leftChild = newNode;
                        return;
                    }
                } else if (id >= current.idata){
                    parent = current;
                    current = current.rightChild;
                    //终止条件在这地方加入
                    if (current == null){
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    public void insert(int key){
        Node newNode = new Node(key,0.0);
        Node parent = null;
        Node current = root;
        if (current == null){
            root = newNode;
        }
        else
        {
            while (true){ //循环一定要写上终止条件
                parent = current;
                if (key < current.idata){
                    current = current.leftChild;
                    if (current == null){
                        parent = newNode;
                        return;
                    }
                }
                else
                {
                    current = current.rightChild;
                    if (current == null){
                        current = newNode;
                        return;
                    }
                }
            }
        }
    }

    public boolean delete(int key){
        //删除有一个子节点的 节点
        //删除怒有两个子节点的 节点
        //删除没有子节点的 节点
        Node parent = root,current = root;
        boolean isLeftChild = true;
        if (current == null)
            return false;
        while (current.idata != key){
            if (key < current.idata)
            {
                isLeftChild = true;
                parent = current;
                current = current.leftChild;
            }
            else
            {
                isLeftChild = false;
                parent = current;
                current = current.rightChild;
            }
        }
        //用于判断没有子节点的情况
        if (current.leftChild == null && current.rightChild == null){
            if (current == root){
                root = null;
                return true;
            } else if (isLeftChild)
            {
                parent.leftChild = null;
                return true;
            } else
            {
                return true;
            }
        } else {
            return false;
        }

    }

    public Node minimum(){
        Node current = root;
        while (current != null){
            if (current.leftChild == null)
                return current;
            current = current.leftChild;
        }
        return current;
    }
    public Node minimum1(){
        Node last = null;
        Node current = root;
        while (current != null){
            last = current;
            current = current.leftChild;
        }
        return last;
    }
}
