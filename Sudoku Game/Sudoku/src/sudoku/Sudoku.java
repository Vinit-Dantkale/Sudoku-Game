/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Sudoku {
    
    public static void main(String[] args) {
        
        //Compute Grid
        int[][] sudoku=new int[9][9];
        SudokuGenerator obj=new SudokuGenerator();
        sudoku=obj.generateGrid();
        
        //Add Components to Window
        SetWindow win=new SetWindow(sudoku);
        win.makeGrid();
        win.setGrid();
        win.setButtonPanel();
        win.setWindow();
        
        
    }
    
}