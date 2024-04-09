package dev.skyherobrine.tools.swingui;

import dev.skyherobrine.tools.database.TypeDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DashBoardController implements ActionListener {

    private DashBoardUI dashBoardUI;

    DashBoardController(DashBoardUI dashBoardUI) {
        this.dashBoardUI = dashBoardUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton btnSource = (JButton) e.getSource();

        if(btnSource.equals(dashBoardUI.getBtnClose())) {
            System.exit(0);
        }

        String getDatabaseName = dashBoardUI.getTxtDatabaseName().getText();
        String getTypeDatabase = dashBoardUI.getCbDatabaseType().getSelectedItem().toString();
        String getTopic = dashBoardUI.getCbTopic().getSelectedItem().toString();
        String getUsername = dashBoardUI.getTxtUsername().getText();
        String getPassword = dashBoardUI.getPwPassword().getPassword().toString();
        int getSpinner = Integer.parseInt(dashBoardUI.getSpPortNumber().getValue().toString());
    }
}
