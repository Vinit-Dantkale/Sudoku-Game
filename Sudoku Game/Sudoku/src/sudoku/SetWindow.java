package sudoku;

import com.sun.glass.ui.Cursor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SetWindow{
   
    private JFrame window=new JFrame();
    private JPanel panel[]=new JPanel[9];
    private JTextField labels[][]=new JTextField[9][9];
    private JPanel mainPanel=new JPanel(new GridLayout(3,3));
    private JPanel buttonPanels=new JPanel();
    private JPanel resultPanel=new JPanel();
    private JLabel result=new JLabel();
    private JButton newgame=new JButton("NEW GAME");
    private JButton check=new JButton("CHECK");
    private JButton solve=new JButton("SOLVE");
    private int[][] sudoku=new int[9][9];
    public int[][] sudogrid=new int[9][9];
    private JTextField last=new JTextField();

    public SetWindow(int[][] sudo){
        window.setSize(500, 500);
        window.setTitle("Sudoku");
        mainPanel.setMaximumSize(new Dimension(450,450));
        buttonPanels.add(newgame);
        buttonPanels.add(check);
        buttonPanels.add(solve);
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                sudoku[i][j]=sudo[i][j];
            }
        }
        result.setVisible(true);
        buttonPanels.add(result,BorderLayout.SOUTH);
    }
    
    public void makeGrid() {
        int x=-3;
        for(int i=0;i<9;i++){
            panel[i]=new JPanel(new GridLayout(3,3));        
        }
        
        for(int i=0;i<9;i++){
            if(i%3==0){x+=3;}    
            for(int j=0;j<9;j++){
                labels[i][j]=new JTextField();
                labels[i][j]=setLabelProperties(labels[i][j],i,j);
                panel[(j/3)+x].add(labels[i][j]);
            }
            panel[i].setBorder(BorderFactory.createLineBorder(Color.black,3));
            mainPanel.add(panel[i]);
        }
    }
    
    public JTextField setLabelProperties(JTextField label,int i,int j){
        label.setText("");
        label.setFont(new Font("Trebuchet MS",Font.BOLD,18));
        label.setBackground(Color.white);
        label.setOpaque(true);            
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        label.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                last.setBorder(BorderFactory.createLineBorder(Color.black,1));
                label.setBorder(BorderFactory.createLineBorder(Color.cyan,2));
                last=label;
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setBackground(Color.lightGray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setBackground(Color.white);
            }

        });
        label.addKeyListener(new KeyListener() {
            int c;
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {    
                c=e.getKeyCode();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                    if(c<58&&c>48){
                    label.setText(""+(c-48));
                    sudogrid[i][j]=c-48;
                    }
                    else{
                        label.setText("");
                    }
            }
        });
           
        return label;
    }
    
    public void setGrid(){
        Random rand=new Random();
        int temp=rand.nextInt(40);
        while(temp<25){
            temp=rand.nextInt(40);
        }
        while(temp--!=0){
            int row=rand.nextInt(81)/9;
            int col=rand.nextInt(81)%9;
            labels[row][col].setText(""+sudoku[row][col]+"");
            sudogrid[row][col]=sudoku[row][col];
            labels[row][col].setEditable(false);
            labels[row][col].addMouseListener(new MouseListener()
            {
                @Override
                public void mouseClicked(MouseEvent e) {
                labels[row][col].setBorder(BorderFactory.createLineBorder(Color.black,1));
                 }
                @Override
                public void mousePressed(MouseEvent e) {
                labels[row][col].setBorder(BorderFactory.createLineBorder(Color.black,1));
                }
                @Override
                public void mouseReleased(MouseEvent e) {}
                @Override
                public void mouseEntered(MouseEvent e) {}
                @Override
                public void mouseExited(MouseEvent e) {}
            });
        }
        
    }
    
    public void setButtonPanel(){
        solve.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    for(int i=0;i<9;i++){
                        for(int j=0;j<9;j++){
                            labels[i][j].setText(""+sudoku[i][j]+"");
                            sudogrid[i][j]=sudoku[i][j];
                        }
                    }
                    result.setText("         Solved:");
            }
            
        });
        
        check.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean res=checksudoku();
                if(res){
                    result.setText("         Solved:");
                }
                else{
                    result.setText("         Check the Puzzle");
                    
                }
            }       
        });
        
        newgame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                String[] arg=new String[1];
                Sudoku.main(arg);
            }
            
        });
        
        
    }
    
    public boolean checksudoku(){
        SudokuGenerator obj=new SudokuGenerator();
        int[] count=new int[10];
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(sudogrid[i][j]==0){
                    return false;
                }
                else{
                    count[sudogrid[i][j]]+=1;
                }
            }
        }
        boolean res=false;
        for(int i=1;i<10;i++){
            if(count[i]==9){
                res=true;
            }
            else{
                return false;
            }
        }
        return res;
    }
    
    public void setWindow(){
        window.add(mainPanel,BorderLayout.CENTER);
        window.add(buttonPanels,BorderLayout.SOUTH);
        window.setVisible(true);
    }
    
    
    
    
}