/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package br.com.jacommerce.panelsAdm;

import br.com.jacommerce.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author finkl
 */
public class PanelCadastroMarca extends javax.swing.JPanel {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public PanelCadastroMarca() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    private void adicionar() {
        String sql = "insert into marca(id_marca, nome_marca, telefone, email) values(?,?,?,?)";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1,txtIdMarca.getText());
            pst.setString(2,txtNomeMarca.getText());
            pst.setString(3,txtFoneMarca.getText());
            pst.setString(4,txtEmailMarca.getText());
            
            int adicionado = pst.executeUpdate();
            
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null,"Marca cadastrada com sucesso");
                txtIdMarca.setText(null);
                txtNomeMarca.setText(null);
                txtFoneMarca.setText(null);
                txtEmailMarca.setText(null);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void pesquisar() {
        String sql = "select * from marca where id_marca = ?";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdMarca.getText());
            rs = pst.executeQuery();
            
            if (rs.next()) {
                txtNomeMarca.setText(rs.getString(2));
                txtFoneMarca.setText(rs.getString(3));
                txtEmailMarca.setText(rs.getString(4));
            }
            else {
                JOptionPane.showMessageDialog(null,"Marca não cadastrada");
                txtIdMarca.setText(null);
                txtNomeMarca.setText(null);
                txtEmailMarca.setText(null);
                txtFoneMarca.setText(null);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void alterar() {
        String sql = "update marca set nome_marca = ?, telefone = ?, email = ? where id_marca = ?";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1,txtNomeMarca.getText());
            pst.setString(2,txtFoneMarca.getText());
            pst.setString(3,txtEmailMarca.getText());
            pst.setString(4,txtIdMarca.getText());
            
            int adicionado = pst.executeUpdate();
            
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Dados da marca alterados com sucesso");
                txtIdMarca.setText(null);
                txtNomeMarca.setText(null);
                txtFoneMarca.setText(null);
                txtEmailMarca.setText(null);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void deletar() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esta marca?", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from marca where id_marca = ?";
            
            try {
                pst = conexao.prepareCall(sql);
                pst.setString(1, txtIdMarca.getText());
                int apagado = pst.executeUpdate();
                
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Marca removida com sucesso");
                    txtIdMarca.setText(null);
                    txtNomeMarca.setText(null);
                    txtFoneMarca.setText(null);
                    txtEmailMarca.setText(null);
                }
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    private void pesquisar_tabela(){
        String sql= "select * from marca where nome_marca like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtTabMarca.getText() + "%");
            rs=pst.executeQuery();
            tblMarca.setModel(DbUtils.resultSetToTableModel(rs));

        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCadMarca = new javax.swing.JPanel();
        btnAdicionarMarca = new javax.swing.JButton();
        btnPesquisarMarca = new javax.swing.JButton();
        lblNomeMarca = new javax.swing.JLabel();
        btnAlterarMarca = new javax.swing.JButton();
        txtNomeMarca = new javax.swing.JTextField();
        btnDeletarMarca = new javax.swing.JButton();
        txtEmailMarca = new javax.swing.JTextField();
        lblEmailMarca = new javax.swing.JLabel();
        txtFoneMarca = new javax.swing.JTextField();
        lblFoneMarca = new javax.swing.JLabel();
        lblIdMarca = new javax.swing.JLabel();
        txtIdMarca = new javax.swing.JTextField();
        panelPesqMarca = new javax.swing.JPanel();
        lblTabMarca = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMarca = new javax.swing.JTable();
        txtTabMarca = new javax.swing.JTextField();

        setBackground(new java.awt.Color(245, 245, 245));

        panelCadMarca.setBackground(new java.awt.Color(255, 255, 255));

        btnAdicionarMarca.setBackground(new java.awt.Color(255, 86, 86));
        btnAdicionarMarca.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnAdicionarMarca.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionarMarca.setText("ADICIONAR");
        btnAdicionarMarca.setBorder(null);
        btnAdicionarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarMarcaActionPerformed(evt);
            }
        });

        btnPesquisarMarca.setBackground(new java.awt.Color(255, 86, 86));
        btnPesquisarMarca.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnPesquisarMarca.setForeground(new java.awt.Color(255, 255, 255));
        btnPesquisarMarca.setText("PESQUISAR");
        btnPesquisarMarca.setBorder(null);
        btnPesquisarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarMarcaActionPerformed(evt);
            }
        });

        lblNomeMarca.setText("NOME MARCA");

        btnAlterarMarca.setBackground(new java.awt.Color(255, 86, 86));
        btnAlterarMarca.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnAlterarMarca.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterarMarca.setText("ALTERAR");
        btnAlterarMarca.setBorder(null);
        btnAlterarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarMarcaActionPerformed(evt);
            }
        });

        btnDeletarMarca.setBackground(new java.awt.Color(255, 86, 86));
        btnDeletarMarca.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnDeletarMarca.setForeground(new java.awt.Color(255, 255, 255));
        btnDeletarMarca.setText("DELETAR");
        btnDeletarMarca.setBorder(null);
        btnDeletarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarMarcaActionPerformed(evt);
            }
        });

        lblEmailMarca.setText("EMAIL");

        lblFoneMarca.setText("TELEFONE");

        lblIdMarca.setText("ID MARCA");

        javax.swing.GroupLayout panelCadMarcaLayout = new javax.swing.GroupLayout(panelCadMarca);
        panelCadMarca.setLayout(panelCadMarcaLayout);
        panelCadMarcaLayout.setHorizontalGroup(
            panelCadMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCadMarcaLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(panelCadMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCadMarcaLayout.createSequentialGroup()
                        .addGroup(panelCadMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFoneMarca)
                            .addComponent(lblIdMarca)
                            .addGroup(panelCadMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtIdMarca)
                                .addComponent(txtFoneMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addGroup(panelCadMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNomeMarca)
                            .addGroup(panelCadMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelCadMarcaLayout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addComponent(btnAlterarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnDeletarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtEmailMarca, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblEmailMarca, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNomeMarca, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelCadMarcaLayout.createSequentialGroup()
                        .addComponent(btnAdicionarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPesquisarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(274, 274, 274))))
        );
        panelCadMarcaLayout.setVerticalGroup(
            panelCadMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCadMarcaLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(panelCadMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeMarca)
                    .addComponent(lblIdMarca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCadMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCadMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCadMarcaLayout.createSequentialGroup()
                        .addComponent(lblEmailMarca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmailMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCadMarcaLayout.createSequentialGroup()
                        .addComponent(lblFoneMarca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFoneMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelCadMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCadMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPesquisarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAdicionarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCadMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDeletarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAlterarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelPesqMarca.setBackground(new java.awt.Color(255, 255, 255));

        lblTabMarca.setText("Pesquisar");

        tblMarca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "EMAIL", "TELEFONE"
            }
        ));
        jScrollPane1.setViewportView(tblMarca);

        txtTabMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTabMarcaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panelPesqMarcaLayout = new javax.swing.GroupLayout(panelPesqMarca);
        panelPesqMarca.setLayout(panelPesqMarcaLayout);
        panelPesqMarcaLayout.setHorizontalGroup(
            panelPesqMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPesqMarcaLayout.createSequentialGroup()
                .addGroup(panelPesqMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPesqMarcaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTabMarca)
                        .addGap(18, 18, 18)
                        .addComponent(txtTabMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPesqMarcaLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        panelPesqMarcaLayout.setVerticalGroup(
            panelPesqMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPesqMarcaLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(panelPesqMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTabMarca)
                    .addComponent(txtTabMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 10, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(panelPesqMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelCadMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 10, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelPesqMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(panelCadMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarMarcaActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_btnAdicionarMarcaActionPerformed

    private void btnPesquisarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarMarcaActionPerformed
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_btnPesquisarMarcaActionPerformed

    private void btnAlterarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarMarcaActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_btnAlterarMarcaActionPerformed

    private void btnDeletarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarMarcaActionPerformed
        // TODO add your handling code here:
        deletar();
    }//GEN-LAST:event_btnDeletarMarcaActionPerformed

    private void txtTabMarcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTabMarcaKeyReleased
        // TODO add your handling code here:
        pesquisar_tabela();
    }//GEN-LAST:event_txtTabMarcaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarMarca;
    private javax.swing.JButton btnAlterarMarca;
    private javax.swing.JButton btnDeletarMarca;
    private javax.swing.JButton btnPesquisarMarca;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEmailMarca;
    private javax.swing.JLabel lblFoneMarca;
    private javax.swing.JLabel lblIdMarca;
    private javax.swing.JLabel lblNomeMarca;
    private javax.swing.JLabel lblTabMarca;
    private javax.swing.JPanel panelCadMarca;
    private javax.swing.JPanel panelPesqMarca;
    private javax.swing.JTable tblMarca;
    private javax.swing.JTextField txtEmailMarca;
    private javax.swing.JTextField txtFoneMarca;
    private javax.swing.JTextField txtIdMarca;
    private javax.swing.JTextField txtNomeMarca;
    private javax.swing.JTextField txtTabMarca;
    // End of variables declaration//GEN-END:variables
}
