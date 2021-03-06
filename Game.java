package 练习;
import java.util.ArrayList;

public class Game {
    /**
     * 整个自动寻路游戏的属性：
     * 地图信息(采用二维字符数组存储)、
     * 自动寻路路径信息(采用栈存储所走的方格信息，间接存储方格的位置信息)、
     * 探索的最佳方向顺序(采用整型二维数组存储、对应着方块的探索属性)
     *
     * 核心方法：
     * 自动寻路方法(findPaths)
     *
     * '*'代表已访问的方块
     * '@'代表死路方块
     * ' '代表可移动到的方块
     * '#'代表障碍物
     */
    private char[][] map;
    private SelfStack<Tile> stack = new SelfStack<>();
    private int[][] directions = {{0, 1},{1, 0},{0, -1},{-1, 0}};

    //根据地图建立实例游戏
    public Game(char[][] map) {
        this.map = map;
    }

    public ArrayList<Point> findPaths(Point first, Point end) {
        ArrayList<Point> paths = new ArrayList<>();

        //每次寻路之前先把栈清空
        this.stack = new SelfStack<>();

        //把起点方块添加到栈中，并将起点方块在地图上的标记变为已访问
        Tile tile = new Tile(first,-1);
        this.stack.push(tile);
        this.map[tile.getPoint().getRow()][tile.getPoint().getColumn()] = '*';

        //自动探路核心，目的是为了得到从起点到终点所走方块信息的栈（终点到起点）
        while(this.stack.peek() != null) {

            //获取栈顶的方格，为探索做准备
            Tile tileHeader = this.stack.peek();
            Point point = tileHeader.getPoint();

            //退出循环条件：栈顶方块的位置和结束方块的位置相同
            if(this.pointSame(point,end)) {
                break;
            }

            //准备探索，把栈顶方块探索位置+1
            tileHeader.setDirection(tileHeader.getDirection() + 1);

            /**
             * 基于已知的右下左上方向向量、结合栈顶方块的位置信息进行四个方向的探索
             * 当探索位置为4时，说明此方格为死路方格，需要从栈顶弹出此方格,并将其地图上标记为死路
             * ！！！一定要注意标记，很重要，因为会影响nullJudge方法的判断
             * 当小于4时，基于方块的探索位置和方向向量进行探索
             */
            if(tileHeader.getDirection() == 4) {
                this.map[tileHeader.getPoint().getRow()][tileHeader.getPoint().getColumn()] = '@';
                this.stack.pop();
            } else {
                Point newPoint = new Point(
                        tileHeader.getPoint().getRow() + directions[tileHeader.getDirection()][0],
                        tileHeader.getPoint().getColumn() + directions[tileHeader.getDirection()][1]
                );
                Tile newTile = new Tile(newPoint,-1);
                if(this.nullJudge(newTile)) {
                    this.map[newTile.getPoint().getRow()][newTile.getPoint().getColumn()] = '*';
                    this.stack.push(newTile);
                }
            }
        }

        while(this.stack.peek() != null) {
            paths.add(0,this.stack.pop().getPoint());
        }

        return paths;
    }

    //根据两个方块的位置判断是不是同一方块
    public boolean pointSame(Point searchPoint, Point end){
        return (searchPoint.getRow() == end.getRow()) && (searchPoint.getColumn() == end.getColumn());
    }

    //判断方块在地图上是否为空
    public boolean nullJudge(Tile tile) {
        return this.map[tile.getPoint().getRow()][tile.getPoint().getColumn()] == ' ';
    }

    public static void main(String[] args) {
        char[][] map = {
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#','#','#'},
                {'#', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ',' ','#'},
                {'#', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ',' ','#'},
                {'#', ' ', ' ', ' ', ' ', '#', '#', ' ', ' ', ' ',' ','#'},
                {'#', ' ', '#', '#', '#', ' ', ' ', ' ', ' ', ' ',' ','#'},
                {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ',' ','#'},
                {'#', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ',' ','#'},
                {'#', ' ', '#', '#', '#', ' ', '#', '#', ' ', ' ',' ','#'},
                {'#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ',' ','#'},
                {'#', ' ', '#', ' ', ' ', ' ', ' ', '#', '#', ' ',' ','#'},
                {'#', ' ', '#', ' ', ' ', ' ', ' ', '#', '#', ' ',' ','#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#','#','#'}
        };
        Game game = new Game(map);
        Point start = new Point(1, 1);
        Point end = new Point(9, 9);
        ArrayList<Point> path = game.findPaths(start, end);
        for (Point point : path) {
            System.out.println(point.getRow() + " - " + point.getColumn());
        }
    }
}

