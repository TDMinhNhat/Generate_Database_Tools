package dev.skyherobrine.tools.swingui;

import com.google.common.reflect.ClassPath;
import dev.skyherobrine.tools.database.TypeDatabase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class DashBoardUI extends JFrame {

    private JTextField txtDatabaseName, txtHostIP, txtUsername;
    private JPasswordField pwPassword;
    private JSpinner spPortNumber;
    private JComboBox<String> cbTopic;
    private JComboBox<String> cbDatabaseType;
    private DefaultTableModel tmGenerate = new DefaultTableModel(new String[]{"ID", "Table Name", "Number of Properties"}, 0);
    private JTable tbGenerate;
    private JButton btnGenerate, btnClose;

    DashBoardUI() throws Exception {
        setLayout(new BorderLayout());
        setTitle("DashBoard's Generate Database UI");
        setSize(650, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponent();
    }

    private void initComponent() throws Exception {
        //North
        JPanel pnNorth = new JPanel();
        Box vertical = new Box(BoxLayout.Y_AXIS);
        Box horizon_1 = new Box(BoxLayout.X_AXIS);
        horizon_1.add(new JLabel("Database Name: "));
        horizon_1.add(txtDatabaseName = new JTextField());
        horizon_1.add(new JLabel("Choose database type: "));
        horizon_1.add(cbDatabaseType = new JComboBox<>(TypeDatabase.getListDatabaseType()));
        horizon_1.add(new JLabel("Choose topic: "));
        horizon_1.add(cbTopic = new JComboBox<>(getListTopics()));
        cbTopic.addActionListener((event) -> {
            String getTopic = cbTopic.getSelectedItem().toString();
            addDataIntoTable(getTopic);
        });
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
        spGenerate.setPreferredSize(new Dimension(620, 340));
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

    private String[] getListTopics() throws Exception {
        Object[] objects = ClassPath.from(ClassLoader.getSystemClassLoader())
                .getTopLevelClassesRecursive("dev.skyherobrine.tools.entities")
                .stream()
                .map(p -> {
                    List<String> splits = Arrays.stream(p.getName().split("\\.")).toList();
                    return splits.get(splits.indexOf("entities") + 1);
                })
                .collect(Collectors.toSet())
                .toArray();

        String[] results = new String[objects.length];
        for (int i = 0; i < objects.length; i++) {
            results[i] = objects[i].toString();
        }
        return results;
    }

    private void addDataIntoTable(String getTopic) {
        try {
            ClassPath.from(ClassLoader.getSystemClassLoader()).getTopLevelClasses(
                    "dev.skyherobrine.tools.entities." + getTopic
            ).forEach(target -> {
                try {
                    tmGenerate.addRow(new Object[]{
                            tmGenerate.getRowCount() + 1,
                            target.getSimpleName(),
                            Class.forName(target.getName()).getDeclaredFields().length
                    });
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
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

    public JTextField getTxtHostIP() {
        return txtHostIP;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JPasswordField getPwPassword() {
        return pwPassword;
    }

    public JSpinner getSpPortNumber() {
        return spPortNumber;
    }

    public JComboBox<String> getCbDatabaseType() {
        return cbDatabaseType;
    }
}
