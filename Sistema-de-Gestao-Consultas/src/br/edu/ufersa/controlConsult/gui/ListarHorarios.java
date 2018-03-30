/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufersa.controlConsult.gui;

import br.edu.ufersa.controlConsult.model.HorarioAtendimento;
import br.edu.ufersa.controlConsult.model.Medico;
import br.edu.ufersa.controlConsult.model.Pessoa;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author leone
 */
public class ListarHorarios extends javax.swing.JFrame {

    /**
     * Creates new form ListarHorarios
     */
    public ListarHorarios() {
        initComponents();
    }
    private List<HorarioAtendimento> list;
    private Medico medico;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CPFField = new javax.swing.JFormattedTextField();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listH = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listar Horários de um Médico");

        try {
            CPFField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        CPFField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CPFFieldActionPerformed(evt);
            }
        });

        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("CPF:");

        jButton3.setText("Apagar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        listH.setEnabled(false);
        jScrollPane1.setViewportView(listH);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CPFField, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jButton3)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(CPFField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(38, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(76, 76, 76))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CPFFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CPFFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CPFFieldActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Pessoa pessoa = null;
        try {
            pessoa = Pessoa.findByCPF(CPFField.getText());
        } catch (NoResultException ex) {
            Logger.getLogger(ListarHorarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (pessoa != null) {
            medico = pessoa.getMedico();
        } else {
            medico = null;
        }
        if (medico == null) {
            JOptionPane.showMessageDialog(null, "Médico não cadastrado");
        } else {
            list = HorarioAtendimento.findByMedicoId(medico.getId());
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Médico não possui horários de atendimento cadastrados");
            } else {
                CPFField.setEnabled(false);
                DefaultListModel model = new DefaultListModel();
                int cont = 0;
                for (HorarioAtendimento ha : list) {
                    model.add(cont, String.valueOf(ha.getDiaSemana().getNome() + " " + String.valueOf(ha.getInicio()) + "h até às " + String.valueOf(ha.getFim())) + "h");
                    cont++;
                }
                listH.setModel(model);
                listH.setEnabled(true);
            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (list == null) {
            JOptionPane.showMessageDialog(null, "Informe primeiro o CPF do médico");
        } else {
            list.get(listH.getSelectedIndex()).delete();
            list = HorarioAtendimento.findByMedicoId(medico.getId());

            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Médico não possui horários de atendimento cadastrados");
                DefaultListModel model = new DefaultListModel();
                listH.setModel(model);
            } else {
                DefaultListModel model = new DefaultListModel();
                int cont = 0;
                for (HorarioAtendimento ha : list) {
                    model.add(cont, String.valueOf(ha.getDiaSemana().getNome() + " " + String.valueOf(ha.getInicio()) + "h até às " + String.valueOf(ha.getFim())) + "h");
                    cont++;
                }
                listH.setModel(model);
                listH.setEnabled(true);
            }
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    public String diaSemana(int n) {
        String retorno = null;
        if (n == 0) {
            retorno = "Sábado";
        }
        if (n == 1) {
            retorno = "Segunda-feira";
        }
        if (n == 2) {
            retorno = "Terça-feira";
        }
        if (n == 3) {
            retorno = "Quarta-feira";
        }
        if (n == 4) {
            retorno = "Quinta-feira";
        }
        if (n == 5) {
            retorno = "Sexta-feira";
        }
        if (n == 6) {
            retorno = "Domingo";
        }
        return retorno;
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
            java.util.logging.Logger.getLogger(ListarHorarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListarHorarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListarHorarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListarHorarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListarHorarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField CPFField;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listH;
    // End of variables declaration//GEN-END:variables
}
