package dev.skyherobrine.tools.swingui;

import dev.skyherobrine.tools.database.TypeDatabase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

class DashBoardUI extends JFrame {

    private JTextField txtDatabaseName, txtHostIP, txtUsername;
    private JPasswordField pwPassword;
    private JSpinner spPortNumber;
    private JComboBox<String> cbTopic;
    private JComboBox<String> cbDatabaseType;
    private DefaultTableModel tmGenerate = new DefaultTableModel(new String[]{"ID", "Table Name", "Number of Properties", "View Details"}, 0);
    private JTable tbGenerate;
    private JButton btnGenerate, btnClose;

    DashBoardUI() {
        setLayout(new BorderLayout());
        setTitle("DashBoard's Generate Database UI");
        setSize(650, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponent();
    }

    private void initComponent() {
        //North
        JPanel pnNorth = new JPanel();
        Box vertical = new Box(BoxLayout.Y_AXIS);
        Box horizon_1 = new Box(BoxLayout.X_AXIS);
        horizon_1.add(new JLabel("Database Name: "));
        horizon_1.add(txtDatabaseName = new JTextField());
        horizon_1.add(new JLabel("Choose database type: "));
        horizon_1.add(cbDatabaseType = new JComboBox<>(TypeDatabase.getListDatabaseType()));
        horizon_1.add(new JLabel("Choose topic: "));
        horizon_1.add(cbTopic = new JComboBox<>());
        vertical.add(horizon_1);

        Box horizon_2 = new Box(BoxLayout.X_AXIS);
        horizon_2.add(new JLabel("IP Host Server: "));
        horizon_2.add(txtHostIP = new JTextField());
        horizon_2.add(new JLabel("Port Number: "));
        horizon_2.add(spPortNumber = new JSpinner(new SpinnerNumberModel(0, 0, 65535, 1)));
        vertical.add(horizon_2);

        Box horizon_3 = new Box(BoxLayout.X_AXIS);
        horizon_3.add(new JLabel("Username: "));
        horizon_3.add(txtUsername = new JTextField());
        horizon_3.add(new JLabel("Password: "));
        horizon_3.add(pwPassword = new JPasswordField());
        vertical.add(horizon_3);

        vertical.setBorder(BorderFactory.createTitledBorder("Input Information"));
        vertical.setPreferredSize(new Dimension(620, 120));
        pnNorth.add(vertical);

        //Center
        JPanel pnlCenter = new JPanel();
        JScrollPane spGenerate = new JScrollPane(tbGenerate = new JTable(tmGenerate));
        spGenerate.setPreferredSize(new Dimension(620, 380));
        pnlCenter.add(spGenerate);

        //South
        JPanel pnlSouth = new JPanel();
        pnlSouth.setBorder(BorderFactory.createTitledBorder("Action"));
        pnlSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        pnlSouth.add(btnGenerate = new JButton("Generate"));
        pnlSouth.add(btnClose = new JButton("Close"));

        add(pnNorth, BorderLayout.NORTH);
        add(pnlCenter, BorderLayout.CENTER);
        add(pnlSouth, BorderLayout.SOUTH);
        addController();
    }

    private void addController() {
        btnGenerate.addActionListener(new DashBoardController(this));
        btnClose.addActionListener(new DashBoardController(this));
    }

    JButton getBtnGenerate() {
        return btnGenerate;
    }

    JButton getBtnClose() {
        return btnClose;
    }

    JTextField getTxtDatabaseName() {
        return txtDatabaseName;
    }

    JComboBox<String> getCbTopic() {
        return cbTopic;
    }

    DefaultTableModel getTmGenerate() {
        return tmGenerate;
    }

    JTable getTbGenerate() {
        return tbGenerate;
    }
}
