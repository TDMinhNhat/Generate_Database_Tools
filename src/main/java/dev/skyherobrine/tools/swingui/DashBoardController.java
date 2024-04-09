package dev.skyherobrine.tools.swingui;

import dev.skyherobrine.tools.database.InteractDatabase;
import dev.skyherobrine.tools.database.TypeDatabase;
import dev.skyherobrine.tools.database.thread.ProcessGenerate;
import org.hibernate.Session;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.FutureTask;

class DashBoardController implements ActionListener {

    private DashBoardUI dashBoardUI;

    DashBoardController(DashBoardUI dashBoardUI) {
        this.dashBoardUI = dashBoardUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JButton btnSource = (JButton) e.getSource();

            if(btnSource.equals(dashBoardUI.getBtnClose())) {
                System.exit(0);
            }

            if(dashBoardUI.getTxtDatabaseName().getText() == null) {
                JOptionPane.showMessageDialog(null, "Please input the database name", "Error", JOptionPane.ERROR_MESSAGE);
                dashBoardUI.getTxtDatabaseName().requestFocus();
                return;
            } else if(dashBoardUI.getTxtUsername().getText() == null) {
                JOptionPane.showMessageDialog(null, "The username can't be empty or null", "Error", JOptionPane.ERROR_MESSAGE);
                dashBoardUI.getTxtUsername().requestFocus();
                return;
            } else if(dashBoardUI.getPwPassword().getText() == null) {
                JOptionPane.showMessageDialog(null, "The password can't be empty or null", "Error", JOptionPane.ERROR_MESSAGE);
                dashBoardUI.getPwPassword().requestFocus();
                return;
            } else if(dashBoardUI.getTxtHostIP().getText() == null) {
                JOptionPane.showMessageDialog(null, "The host ip can't be empty or null", "Error", JOptionPane.ERROR_MESSAGE);
                dashBoardUI.getTxtHostIP().requestFocus();
                return;
            }

            String getIpHost = dashBoardUI.getTxtHostIP().getText();
            String getDatabaseName = dashBoardUI.getTxtDatabaseName().getText();
            String getTypeDatabase = dashBoardUI.getCbDatabaseType().getSelectedItem().toString();
            String getTopic = dashBoardUI.getCbTopic().getSelectedItem().toString();
            String getUsername = dashBoardUI.getTxtUsername().getText();
            String getPassword = dashBoardUI.getPwPassword().getText();
            int getPortIp = Integer.parseInt(dashBoardUI.getSpPortNumber().getValue().toString());

            dashBoardUI.getBtnGenerate().setText("Generating...!");
            dashBoardUI.getBtnGenerate().setEnabled(false);
            dashBoardUI.getBtnClose().setEnabled(false);

            FutureTask<Boolean> task = new FutureTask<>(new ProcessGenerate(getTypeDatabase, getDatabaseName, getIpHost, getUsername, getPassword, getTopic, getPortIp));
            task.run();
            boolean result = task.get();
            if(result) {
                JOptionPane.showMessageDialog(null, "Database generated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Database generated failed", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            dashBoardUI.getBtnGenerate().setEnabled(true);
            dashBoardUI.getBtnGenerate().setText("Generate");
            dashBoardUI.getBtnClose().setEnabled(true);
        }
    }
}
