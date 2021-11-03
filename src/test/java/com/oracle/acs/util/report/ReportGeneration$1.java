package com.oracle.acs.util.report;

import javax.swing.*;
import java.awt.*;

class ReportGeneration$1 implements Runnable {
    private final ReportGeneration this$0;
    private final JFrame val$counter;
    private final JDialog val$dialog;

    ReportGeneration$1(ReportGeneration var1, JFrame var2, JDialog var3) {
        this.this$0 = var1;
        this.val$counter = var2;
        this.val$dialog = var3;
    }

    public void run() {
        try {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            JLabel lab = new JLabel();
            lab.revalidate();
            this.val$counter.add(lab);
            this.val$counter.getContentPane().setBackground(Color.white);
            this.val$counter.setTitle("Timer");
            this.val$counter.pack();
            this.val$counter.setSize(this.val$counter.getWidth() + 50, this.val$counter.getHeight() + 50);
            this.val$counter.setLocation(screenSize.width - this.val$counter.getWidth() - 200, screenSize.height - this.val$counter.getHeight() - 200);
            this.val$counter.setAlwaysOnTop(true);
            this.val$counter.setVisible(true);

            for(int i = 10; i > 0; --i) {
                Thread.sleep(1000L);
                lab.setText("   " + i + "sec remaining");
            }

            this.val$counter.dispose();
            this.val$dialog.dispose();
        } catch (Throwable var4) {
        }

    }
}
