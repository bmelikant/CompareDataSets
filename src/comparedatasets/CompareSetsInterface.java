/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparedatasets;
import java.util.ArrayList;

/**
 *
 * @author bmelikant
 */
public class CompareSetsInterface extends javax.swing.JFrame {

    /**
     * Creates new form CompareSetsInterface
     */
    public CompareSetsInterface() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jCheckBox1.setText("Ignore Duplicate Rows");
        jCheckBox1.setName("cb_ignoreDuplicates"); // NOI18N

        jCheckBox2.setText("Remove Whitespace");
        jCheckBox2.setName("cb_removeWhitespace"); // NOI18N

        jButton1.setText("Compare");
        jButton1.setName("btn_compareSets"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_compareSetsActionPerformed(evt);
            }
        });

        jLabel1.setText("Source Set");

        jLabel2.setText("Comparison Set");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox1)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox2))
                    .addComponent(jButton1))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jCheckBox1.getAccessibleContext().setAccessibleName("Ignore Duplicate Rows");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_compareSetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_compareSetsActionPerformed

        boolean unique = false;
        
        // there must be text in both boxes
        if (this.jTextArea1.getText().length() > 0 && jTextArea2.getText().length() > 0) {
            String [] srcContent = jTextArea1.getText().split("\n");
            String [] cmpContent = jTextArea2.getText().split("\n");
            
            if (this.jCheckBox1.getSelectedObjects() != null) {
                srcContent = filterDuplicates(srcContent);
                cmpContent = filterDuplicates(cmpContent);
                unique = true;
            } 
            
            if (this.jCheckBox2.getSelectedObjects() != null) {
                srcContent = filterOutWhitespace(srcContent);
                cmpContent = filterOutWhitespace(cmpContent);
            }
            
            int commonItems = 0, disparateItems = 0, srcItemCount = srcContent.length, cmpItemCount = cmpContent.length;
            
            for (String src : srcContent) {
                boolean found = false;
                for (String cmp : cmpContent) {
                    if (src.equals(cmp)) {
                        commonItems++;
                        found = true;
                    }
                }
                if (!found) {
                    disparateItems++;
                }
            }
            
            String message = "There are " + srcItemCount + ((unique) ? " unique" : "") + " items in the source box.\n" +
                             "There are " + cmpItemCount + ((unique) ? " unique" : "") + " items in the comparison box.\n" +
                             "There are " + commonItems + " common items between the two data sets\n" +
                             "There are " + disparateItems + " items in the source set that are not in the destination set.";
            
            javax.swing.JOptionPane.showMessageDialog(null, message);
            
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "You must provide both source and comparison sets!");
        }
    }//GEN-LAST:event_btn_compareSetsActionPerformed

    private String [] filterDuplicates(String [] items) {
        ArrayList<String> returnArray = new ArrayList<>();
        
        for (int i = 0; i < items.length; i++) {
            if (!returnArray.contains(items[i])) {
                returnArray.add(items[i]);
            }
        }
        
        dumpStringArrayList("filter duplicates", returnArray);
        return (String []) returnArray.toArray(new String[0]);
    }
    private String [] filterOutWhitespace(String [] items) {
        ArrayList<String> returnArray = new ArrayList<>();
        
        for (int i = 0; i < items.length; i++) {
            // filter out blank lines
            if (!items[i].trim().isEmpty()) {
                returnArray.add(items[i].replaceAll("\\s+", ""));
            }
        }
        
        dumpStringArrayList("filter whitespace", returnArray);
        return (String []) returnArray.toArray(new String[0]);
    }
    
    private void dumpStringArrayList(String name, ArrayList<String> list) {
        System.out.println("Array " + name + " contains: ");
        for (String s : list) {
            System.out.println(s);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CompareSetsInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CompareSetsInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CompareSetsInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CompareSetsInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CompareSetsInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
