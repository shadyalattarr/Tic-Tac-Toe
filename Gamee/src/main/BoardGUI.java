package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import StatePattern.OState;
import StatePattern.XState;
import java.util.Stack;


public class BoardGUI extends javax.swing.JFrame {
    //frontend of board

    private JPanel gameBoard;
    private JPanel[][] squares = new JPanel[3][3];
    private JPanel square;
    Game game = new Game();

    private Stack<Memento> mementoStack = new Stack<>();


    public BoardGUI() {
        initialize();
        checkMoves();
    }
    
    private void initialize() {
        JPanel buttonPanel = new JPanel();
        JButton undo = new JButton("Undo");
        undo.setPreferredSize(new Dimension(100, 90));
        buttonPanel.add(undo);

        int row, col;
        gameBoard = new JPanel(new GridLayout(3, 3));
        for (row = 0; row < 3; row++) {
            for (col = 0; col < 3; col++) {
                square = new JPanel();
                square.setPreferredSize(new Dimension(100, 100));
                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                square.setBackground(Color.CYAN);
                squares[row][col] = square;
                gameBoard.add(square);
            }
        }

        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undoMove();
                
                try {
                    updateGUI(game.getBoard());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                System.out.println("undo and notify");
                // memento here + observer( observer is board and subject is validatemoves)
            }
        });

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(gameBoard, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.EAST);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    // instead of getbackground, when we have an image or drawing, we'll add it to
    // frame
    // and use getcomponentcount
    private void checkMoves() {
        gameBoard.addMouseListener(new MouseAdapter() {
            int row, col;           

            @Override
            public void mouseClicked(MouseEvent e) {
                row = e.getY() / 100;
                col = e.getX() / 100;

                Memento currentMemento = game.createMemento();//can make this a separate method
                mementoStack.push(currentMemento);


                game.makemove(row, col);


                try {
                    updateGUI(game.getBoard());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                if(game.getWinner())
                {
                    JOptionPane.showMessageDialog(null, "We have a winner! ");
                    setVisible(false);
                }

            }
        });
    }

    public void undoMove() {
        
        if (!mementoStack.isEmpty()) {           
            Memento previousMemento = mementoStack.pop();
            game.restore(previousMemento);           
        }else{
            JOptionPane.showMessageDialog(null, "No more Undos");
        }
    }

      

    private void updateGUI(Square[][] board) throws IOException {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
               
                squares[row][col].removeAll();
                
                if (board[row][col].getSquareState() instanceof XState) {
                    squares[row][col].setBackground(Color.RED);
                    Shape y = FlyWeightFactory.getShape("X");
                    y.draw(squares[row][col]);
                } else if (board[row][col].getSquareState() instanceof OState) {
                    squares[row][col].setBackground(Color.GREEN);
                    Shape y = FlyWeightFactory.getShape("O");
                    y.draw(squares[row][col]);
                } else {
                    squares[row][col].setBackground(Color.CYAN);
                }
                
                squares[row][col].revalidate();
                squares[row][col].repaint();
            }
        }
    }
    

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BoardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BoardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BoardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BoardGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BoardGUI().setVisible(true);
            }
        });
    }
}
