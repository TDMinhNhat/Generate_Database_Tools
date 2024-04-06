package dev.skyherobrine.tools.swingui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

class DashBoard extends JFrame {

    private JTextField txtDatabaseName;
    private JComboBox<String> cbTopic;
    private DefaultTableModel tmGenerate = new DefaultTableModel(new String[]{"ID", "Table Name", "Number of Properties", "View Details"}, 0);
    private JTable tbGenerate;
    private JButton btnGenerate, btnClose;

    DashBoard() {
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
        horizon_1.add(new JLabel("Choose topic: "));
        horizon_1.add(cbTopic = new JComboBox<>());
        vertical.setBorder(BorderFactory.createTitledBorder("Input Information"));
        vertical.add(horizon_1);
        vertical.setPreferredSize(new Dimension(620, 50));
        pnNorth.add(vertical);

        //Center
        JPanel pnlCenter = new JPanel();
        JScrollPane spGenerate = new JScrollPane(tbGenerate = new JTable(tmGenerate));
        spGenerate.setPreferredSize(new Dimension(620, 400));
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

}
