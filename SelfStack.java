package 练习;

public class SelfStack<T> {
    //栈的属性：栈顶，同时也是链表的头部
    private Node<T> header;

    //默认的构造函数，也可不写
    public SelfStack() {
    }

    //下面完成自定义栈的push、pop、peek方法
    public void push(T content) {
        if(this.header == null) {
            this.header = new Node(content);
            this.header.setNext(null);
        } else {
            this.header = new Node(content,this.header);
        }
    }

    public T pop() {
        if(this.header == null) {
            return null;
        } else {
            Node<T> node = this.header;
            this.header = this.header.getNext();
            node.setNext(null);
            return node.getContent();
        }
    }

    public T peek() {
        if(this.header == null) {
            return null;
        } else {
            return this.header.getContent();
        }
    }


}
