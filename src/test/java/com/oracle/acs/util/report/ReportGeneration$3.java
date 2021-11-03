package com.oracle.acs.util.report;

import javax.swing.*;

class ReportGeneration$3 implements Runnable {
    private final ReportGeneration this$0;
    private final int val$counter;
    private final JDialog val$jD;

    ReportGeneration$3(ReportGeneration var1, int var2, JDialog var3) {
        this.this$0 = var1;
        this.val$counter = var2;
        this.val$jD = var3;
    }

    public void run() {
        try {
            int i = this.val$counter;

            while(i >= 0) {
                this.val$jD.setTitle("Manual Intervention " + i + "secs left");
                Thread.sleep(1000L);
                --i;
                if (!this.val$jD.isVisible()) {
                    break;
                }
            }

            if (this.val$jD.isVisible()) {
                this.val$jD.dispose();
            }
        } catch (Throwable var2) {
        }

    }
}
