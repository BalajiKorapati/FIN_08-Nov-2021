package com.oracle.acs.util.report;

import javax.swing.*;
import java.awt.*;

class ReportGeneration$2 implements Runnable {
    private final ReportGeneration this$0;
    private final JFrame val$counter;
    private final JFrame val$jfra;

    ReportGeneration$2(ReportGeneration var1, JFrame var2, JFrame var3) {
        this.this$0 = var1;
        this.val$counter = var2;
        this.val$jfra = var3;
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
            this.val$counter.setVisible(true);
            this.val$counter.setAlwaysOnTop(true);

            for(int i = 20; i > 0; --i) {
                Thread.sleep(1000L);
                lab.setText("   " + i + "sec remaining");
                lab.revalidate();
            }

            this.val$counter.dispose();
            this.val$jfra.dispose();
        } catch (Throwable var4) {
        }

    }
}

