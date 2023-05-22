package model;

/**
 * This class represents positions on the checkerboard, such as (0, 0), (0, 7), and so on
 * Where, the upper left corner is (0, 0), the lower left corner is (7, 0), the upper right corner is (0, 7), and the lower right corner is (7, 7).
 */
public class ChessboardPoint {
    private final int row;
    private final int col;

    public ChessboardPoint(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }


    @Override
    public int hashCode() {
        return row + col;
    }

    @Override
    @SuppressWarnings("ALL")
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        ChessboardPoint temp = (ChessboardPoint) obj;
        return (temp.getRow() == this.row) && (temp.getCol() == this.col);
    }

    @Override
    public String toString() {
        return "("+row + ","+col+") " + "on the chessboard is clicked!";
    }
    public boolean isRiver(){//判断是否是河流
        boolean b= false;
        if(this.row==3||this.row==4||this.row==5){
            if(this.col==1||this.col==2||this.col==4||this.col==5)
                b= true;
        }
        return b;
    }
    public boolean isBesideRiver(){
        boolean b = false;
        if(this.row==2||this.row==6){
            if(this.col==1||this.col==2||this.col==4||this.col==5)
                b=true;
        }
        else if(this.row==3||this.row==4||this.row==5){
            if(this.col==0||this.col==3||this.col==6)
                b = true;
        }
        return b;
    }
    public boolean isRedTrap(){
        boolean b = false;
        if((this.row==0&&this.col==2)||(this.row==0&&this.col==4)||(this.row==1&&this.col==3))
            b = true;
        return b;
    }
    public boolean isBlueTrap(){
        boolean b= false;
        if((this.row==8&&this.col==2)||(this.row==8&&this.col==4)||(this.row==7&&this.col==3))
            b = true;
        return b;
    }
    public boolean isRedDen(){
        boolean b = false;
        if(this.getRow()==0&&this.getCol()==3)
            b=true;
        return b;
    }
    public boolean isBlueDen(){
        boolean b = false;
        if(this.getRow()==8&&this.getCol()==3)
            b = true;
        return b;
    }

    public boolean isOnChessboard(){
        boolean b = false;
        if(this.row>=0&&this.row<=8&&this.col>=0&&this.col<=6)
            b = true;
        return b;
    }


}
