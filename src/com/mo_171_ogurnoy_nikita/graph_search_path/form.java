package com.mo_171_ogurnoy_nikita.graph_search_path;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class form extends JFrame {
    private JPanel panel1;
    private JTextField vnum;
    private JTextArea weightEdge;
    private JButton ok;
    private JList pathList;
    private JTextField pathNum;
    private JTextField vstart;
    private JTextField vtarget;
    private JLabel notification;
    private JLabel errorLabel;

    private UIManager.LookAndFeelInfo looks[];


    void init(){
        setTitle("Поиск кратчайших путей в графе");
        add(panel1);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ok.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    int vertexNumber = 0;
                    try {
                        vertexNumber = Integer.parseInt(vnum.getText());
                    } catch (Exception exc) {
                        notification.setText("Число вершин введено неверно!");
                        throw exc;
                    }

                    Double[][] weightMatrix = new Double[vertexNumber][vertexNumber];

                    String weightText = weightEdge.getText();
                    Scanner reader = null;
                    reader = new Scanner(weightText);

                    try {
                        for (int i = 0; i < vertexNumber; i++)
                            for (int j = 0; j < vertexNumber; j++) {
                                double next = reader.nextDouble();
                                if (next > 0)
                                    weightMatrix[i][j] = next;
                                else weightMatrix[i][j] = Double.MAX_VALUE;
                            }
                    } catch (InputMismatchException exc) {
                        errorLabel.setText("Матрица весов введена неверно!");
                        throw exc;
                    } catch (NoSuchElementException exc) {
                        errorLabel.setText("Матрица весов не соответствует заданному числу вершин!");
                        throw exc;
                    } catch (Exception exc) {
                        errorLabel.setText("Ошибка ввода матрица весов!");
                        throw exc;
                    }
                    Integer start, target, pnum;
                    Graph graph = new Graph(weightMatrix);
                    try {
                        start = Integer.parseInt(vstart.getText());
                    } catch (Exception exc) {
                        errorLabel.setText("Стартовая вершина введена неверно!");
                        throw exc;
                    }
                    try {
                        target = Integer.parseInt(vtarget.getText());
                    } catch (Exception exc) {
                        errorLabel.setText("Целевая вершина введена неверно!");
                        throw exc;
                    }
                    try {
                        pnum = Integer.parseInt(pathNum.getText());
                    } catch (Exception exc) {
                        errorLabel.setText("Требуемое число путей введено неверно!");
                        throw exc;
                    }


                    ArrayList<Path> paths;
                    try {
                        paths = graph.getShortestPath(start, target, pnum);
                    } catch (Exception exc) {
                        errorLabel.setText("Ошибка! Проверьте корректность введённых данных.");
                        throw exc;
                    }

                    int resPnum = paths.size();

                    if (resPnum < pnum) {
                        notification.setText("Найдены все пути (" + resPnum + " шт.)");
                    } else
                        notification.setText("");

                    errorLabel.setText("");

                    pathList.setListData(paths.toArray());
                } catch (Exception exc){
                    pathList.setModel(new DefaultListModel());
                    notification.setText("");
                }
            }
        });
    }


}
