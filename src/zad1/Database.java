package zad1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
public class Database extends JFrame {


    String url;
    TravelData travelData;

    public Database(String url, TravelData travelData) {
        this.url = url;
        this.travelData = travelData;
    }


    public void create() {


        try {
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            try {
                statement.executeUpdate("Drop TABLE Oferty");
            }catch (Exception eee){

            }

            statement.executeUpdate("CREATE table Oferty (id INT PRIMARY KEY ,Lokalizacja VARCHAR(10),Name VARCHAR(30),Data_Start DATE,Data_End DATE,Place VARCHAR(30),Prise VARCHAR(20),Value VARCHAR(10))");

            for (int i = 0; i < travelData.list2.size(); i++) {
                int id = i+1;

                statement.executeUpdate("INSERT INTO Oferty VALUES (" + id + ",'"+ travelData.list2.get(i).localization +"', '" + travelData.list2.get(i).country + "', '" +
                        travelData.list2.get(i).data_star + "', '" + travelData.list2.get(i).data_end + "', '" +
                        travelData.list2.get(i).place + "', '" + travelData.list2.get(i).cena + "', '" +
                        travelData.list2.get(i).value + "')");


            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public void showGui() {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Database Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            JPanel panel = new JPanel();
            frame.add(panel);

            JTable table = new JTable();
            JScrollPane scrollPane = new JScrollPane(table);


            frame.getContentPane().add(scrollPane, BorderLayout.CENTER);



            JButton button = new JButton("Polski Oferty");
            panel.add(button);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Connection connection = DriverManager.getConnection(url);
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM Oferty WHERE Lokalizacja='pl'");


                        table.setModel(buildTableModel(resultSet));


                    } catch (SQLException m) {
                        m.printStackTrace();
                    }
                }
            });
            JButton button1 = new JButton("English offers");
            panel.add(button1);
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Connection connection = DriverManager.getConnection(url);
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM Oferty WHERE Lokalizacja='en'");


                        table.setModel(buildTableModel(resultSet));
                        connection.close();

                    } catch (SQLException m) {
                        m.printStackTrace();
                    }
                }
            });

            frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
            frame.getContentPane().add(panel, BorderLayout.SOUTH);
            JButton button2 = new JButton("All offers");
            panel.add(button2);
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Connection connection = DriverManager.getConnection(url);
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM Oferty");


                        table.setModel(buildTableModel(resultSet));

                        connection.close();
                    } catch (SQLException m) {
                        m.printStackTrace();
                    }
                }
            });

            JButton button3 = new JButton("offers");
            panel.add(button3);
            JTextField sentenceTextField = new JTextField(10);

            panel.add(sentenceTextField);
            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String sentence = sentenceTextField.getText();

                    String[] words = sentence.split(" ");
                

                    try {
                        Connection connection = DriverManager.getConnection(url);
                        Statement statement = connection.createStatement();


                     int finalid  =  travelData.list2.size()+1;

                            statement.executeUpdate("INSERT INTO Oferty VALUES (" + finalid + ",'"+ words[0] +"', '" + words[1] + "', '" +
                                    words[2] + "', '" + words[3] + "', '" +
                                    words[4] + "', '" + words[5]+ "', '" +
                                    words[6] + "')");


                        travelData.list2.add(new Wycieczka(words[0],words[1],words[2],words[3],words[4],words[5],words[6]));



                    } catch (SQLException m) {
                        throw new RuntimeException(m);
                    }

                }
            });


            frame.setVisible(true);
        });


    }



    private DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();


        int columnCount = metaData.getColumnCount();


        DefaultTableModel tableModel = new DefaultTableModel();


        for (int column = 1; column <= columnCount; column++) {
            tableModel.addColumn(metaData.getColumnName(column));
        }


        while (resultSet.next()) {
            Object[] row = new Object[columnCount];
            for (int column = 1; column <= columnCount; column++) {
                row[column - 1] = resultSet.getObject(column);
            }
            tableModel.addRow(row);
        }





        return tableModel;
    }
}