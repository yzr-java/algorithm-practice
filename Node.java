package 练习;

public class Node<T> {
    /**
     * 节点的两个属性：内容、保存下一个节点
     *节点采用泛型设计，便于存储不同类型的数据
     */
    private T content;
    private Node<T> next;

    //默认的构造函数
    public Node() {
    }

    //能传入一个内容参数的构造函数
    public Node(T content) {
        this.content = content;
    }

    //能传入两个参数的构造函数
    public Node(T content, Node<T> next) {
        this.content = content;
        this.next = next;
    }

    //get、set方法
    public void setContent(T content) {
        this.content = content;
    }
    public T getContent() {
        return content;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
    public Node<T> getNext() {
        return next;
    }
    //节点对象设计完毕，下面将用基于节点(链表)设计栈
}
