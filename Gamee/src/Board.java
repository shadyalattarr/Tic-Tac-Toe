import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends javax.swing.JFrame {
    public Board() {
        initialize();
        checkMoves();
    }

    private JPanel gameBoard;
    private JPanel[][] squares = new JPanel[3][3];
    private JPanel square;
    private Turn turn = Turn.X;
    Game game = new Game();

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
            Turn[][] board;

            @Override
            public void mouseClicked(MouseEvent e) {
                row = e.getY() / 100;
                col = e.getX() / 100;
                board = game.makeMove(row, col, turn);
                if(game.getWinner())
                {
                    JOptionPane.showMessageDialog(null, "We have a winner! ");
                    setVisible(false);
                }
                if (board != null) {
                    turn = game.switchTurn(turn);
                    updateGUI(board);
                }

            }
        });
    }

    private void updateGUI(Turn[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == Turn.X) {
                    squares[row][col].setBackground(Color.RED);
                    continue;
                } else if (board[row][col] == Turn.O) {
                    squares[row][col].setBackground(Color.GREEN);
                    continue;
                }
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
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Board().setVisible(true);
            }
        });
    }
}
