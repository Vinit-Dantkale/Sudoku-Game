package sudoku;
import java.util.Random;
import java.sql.Array;
import java.util.ArrayList;

public class SudokuGenerator{

    private ArrayList<ArrayList<Integer>> Available=new ArrayList<ArrayList<Integer>>();
    private Random rand=new Random();
    public SudokuGenerator(){}


public int[][] generateGrid(){
        int[][] Sudoku=new int[9][9];

        int currentPos=0;

        clearGrid(Sudoku);
        while(currentPos<81){
                 //System.out.print(currentPos+" "+Available.get(currentPos));
                if(Available.get(currentPos).size()!=0){
                int i=rand.nextInt(Available.get(currentPos).size());
                int number=Available.get(currentPos).get(i);
                //System.out.print(" "+number+" \n");
                if(!checkConflict(Sudoku,currentPos,number)){
                    int xPos=currentPos%9;
                    int yPos=currentPos/9;
                    Sudoku[xPos][yPos]=number;
                    Available.get(currentPos).remove(i);

                    currentPos++;
                }else{
                    Available.get(currentPos).remove(i);
                }

            }
            else{
                for(int i=1;i<=9;i++){
                    Available.get(currentPos).add(i);
                }
                currentPos--;
            }
        }
        for(int i=0;i<9;i++){
           for(int j=0;j<9;j++){
               System.out.print(" "+Sudoku[i][j]+" ");
           }
            System.out.println("");
        }
        return Sudoku;

    }
private void clearGrid(int[][] Sudoku){
        Available.clear();
        for(int y=0;y<9;y++){
            for(int x=0;x<9;x++){
                Sudoku[x][y]=-1;
            }
        }

        for(int x=0;x<81;x++){
            Available.add(new ArrayList<Integer>());
            for(int i=1;i<=9;i++){
                Available.get(x).add(i);
            }
        }
    }
public boolean checkConflict(int[][] Sudoku,int currentPos,final int number){
        int xPos=currentPos%9;
        int yPos=currentPos/9;
        if(checkHorizontalConflict(Sudoku,xPos,yPos,number)||checkVerticalConflict(Sudoku,xPos,yPos,number)||checkRegionConflict(Sudoku,xPos,yPos,number)){
            return true;
        }

        return false;
}

private boolean checkHorizontalConflict(final int[][] Sudoku,final int xPos,final int yPos,final int number){
        for(int x=xPos-1;x>=0;x--){
            if(number==Sudoku[x][yPos]){
                return true;
            }
        }

        return false;
}
private boolean checkVerticalConflict(final int[][] Sudoku,final int xPos,final int yPos,final int number){
    for(int y=yPos-1;y>=0;y--){
        if(number==Sudoku[xPos][y]){
            return true;
        }
    }

    return false;
    }
private boolean checkRegionConflict(final int[][] Sudoku,final int xPos,final int yPos,final int number){
        int xRegion=xPos/3;
        int yRegion=yPos/3;

        for(int x=xRegion*3;x<xRegion*3+3;x++){
            for(int y=yRegion*3;y<yRegion*3+3;y++){
                if((x!=xPos||y!=yPos)&&number==Sudoku[x][y]){
                    return true;
                }
            }
        }


        return false;
    }

}
