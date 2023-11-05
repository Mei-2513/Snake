package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ScoreHistory {
    private JFrame frame;
    private JTable table;

    public ScoreHistory() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        
        Vector<Vector<Object>> data = new Vector<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/resources/History.ser"))) {
            while (true) {
                try {
                    Score score = (Score) ois.readObject();
                    Vector<Object> row = new Vector<>();
                    row.add(score.getDateTime());
                    row.add(score.getPlayerName());
                    row.add(score.getScore());
                    data.add(row);
                } catch (EOFException ex) {
                
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Fecha y hora");
        columnNames.add("Nombre del jugador");
        columnNames.add("Puntuación");
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        
        table = new JTable(model);

        
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ScoreHistory window = new ScoreHistory();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

