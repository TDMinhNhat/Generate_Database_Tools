package dev.skyherobrine.tools.swingui;

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


    }
}
