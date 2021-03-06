package 练习;

public class Tile {
    //方块属性：位置、需要探索的方向(默认初始方块的值为-1即未探索)
    private Point point;
    private int direction = -1;

    //默认构造函数
    public Tile() {
    }

    //初始化方块时可传入两个参数，确定方块的位置和探索方向
    public Tile(Point point,int direction) {
        this.point = point;
        this.direction = direction;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
    public Point getPoint() {
        return point;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    public int getDirection() {
        return direction;
    }

}
