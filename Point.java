package 练习;

public class Point {
    //位置的两个属性：行、列
    private int row;
    private int column;

    public Point() {
    }
    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }
    public int getRow() {
        return row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    public int getColumn() {
        return column;
    }

}
