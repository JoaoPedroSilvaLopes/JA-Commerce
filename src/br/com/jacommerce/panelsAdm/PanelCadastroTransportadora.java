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
public class PanelCadastroTransportadora extends javax.swing.JPanel {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public PanelCadastroTransportadora() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    private void adicionar() {
        String sql = "insert into transportadora(id_tranportadora, nome_transportadora, frete, telefone, email) values(?,?,?,?,?)";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1,txtIdTransp.getText());
            pst.setString(2,txtNomeTransp.getText());
            pst.setString(3,txtFreteTransp.getText());
            pst.setString(4,txtFoneTransp.getText());
            pst.setString(5,txtEmailTransp.getText());
            
            int adicionado = pst.executeUpdate();
            
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null,"Transportadora cadastrada com sucesso");
                txtIdTransp.setText(null);
                txtNomeTransp.setText(null);
                txtFreteTransp.setText(null);
                txtFoneTransp.setText(null);
                txtEmailTransp.setText(null);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void pesquisar() {
        String sql = "select * from transportadora where id_tranportadora=?";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdTransp.getText());
            rs = pst.executeQuery();
            
            if (rs.next()) {
                
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                System.out.println(rs.getString(4));
                System.out.println(rs.getString(5));
            
                txtNomeTransp.setText(rs.getString(2));
                txtFreteTransp.setText(rs.getString(3));
                txtEmailTransp.setText(rs.getString(4));
                txtFoneTransp.setText(rs.getString(5));
            }
            else {
                JOptionPane.showMessageDialog(null,"Transportadora não cadastrada");
                txtIdTransp.setText(null);
                txtNomeTransp.setText(null);
                txtFreteTransp.setText(null);
                txtFoneTransp.setText(null);
                txtEmailTransp.setText(null);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void alterar() {
        String sql = "update transportadora set nome_transportadora = ?, frete = ?, telefone = ?, email = ? where id_tranportadora = ?";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1,txtNomeTransp.getText());
            pst.setString(2,txtFreteTransp.getText());
            pst.setString(3,txtFoneTransp.getText());
            pst.setString(4,txtEmailTransp.getText());
            pst.setString(5,txtIdTransp.getText());
            
            int adicionado = pst.executeUpdate();
            
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Dados da transportadora alterados com sucesso");
                txtIdTransp.setText(null);
                txtNomeTransp.setText(null);
                txtFreteTransp.setText(null);
                txtFoneTransp.setText(null);
                txtEmailTransp.setText(null);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void deletar() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esta transportadora?", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from transportadora where id_tranportadora = ?";
            
            try {
                pst = conexao.prepareCall(sql);
                pst.setString(1, txtIdTransp.getText());
                int apagado = pst.executeUpdate();
                
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Transportadora removida com sucesso");
                    txtIdTransp.setText(null);
                    txtNomeTransp.setText(null);
                    txtFreteTransp.setText(null);
                    txtFoneTransp.setText(null);
                    txtEmailTransp.setText(null);
                }
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    private void pesquisar_tabela(){
        String sql= "select * from transportadora where nome_transportadora like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtTabTransp.getText() + "%");
            rs=pst.executeQuery();
            tblTransp.setModel(DbUtils.resultSetToTableModel(rs));

        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCadTransp = new javax.swing.JPanel();
        btnAdicionarTransp = new javax.swing.JButton();
        btnPesquisarTransp = new javax.swing.JButton();
        lblNomeTransp = new javax.swing.JLabel();
        btnAlterarTransp = new javax.swing.JButton();
        txtNomeTransp = new javax.swing.JTextField();
        btnDeletarTransp = new javax.swing.JButton();
        txtEmailTransp = new javax.swing.JTextField();
        lblEmailTransp = new javax.swing.JLabel();
        txtFoneTransp = new javax.swing.JTextField();
        lblFoneTransp = new javax.swing.JLabel();
        txtFreteTransp = new javax.swing.JTextField();
        lblFreteTransp = new javax.swing.JLabel();
        lblIdTransp = new javax.swing.JLabel();
        txtIdTransp = new javax.swing.JTextField();
        panelPesqTransp = new javax.swing.JPanel();
        lblTabTransp = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransp = new javax.swing.JTable();
        txtTabTransp = new javax.swing.JTextField();

        setBackground(new java.awt.Color(245, 245, 245));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelCadTransp.setBackground(new java.awt.Color(255, 255, 255));

        btnAdicionarTransp.setBackground(new java.awt.Color(255, 86, 86));
        btnAdicionarTransp.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionarTransp.setText("ADICIONAR");
        btnAdicionarTransp.setBorder(null);
        btnAdicionarTransp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarTranspActionPerformed(evt);
            }
        });

        btnPesquisarTransp.setBackground(new java.awt.Color(255, 86, 86));
        btnPesquisarTransp.setForeground(new java.awt.Color(255, 255, 255));
        btnPesquisarTransp.setText("PESQUISAR");
        btnPesquisarTransp.setBorder(null);
        btnPesquisarTransp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarTranspActionPerformed(evt);
            }
        });

        lblNomeTransp.setText("NOME TRANSPORTADORA");

        btnAlterarTransp.setBackground(new java.awt.Color(255, 86, 86));
        btnAlterarTransp.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterarTransp.setText("ALTERAR");
        btnAlterarTransp.setBorder(null);
        btnAlterarTransp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarTranspActionPerformed(evt);
            }
        });

        btnDeletarTransp.setBackground(new java.awt.Color(255, 86, 86));
        btnDeletarTransp.setForeground(new java.awt.Color(255, 255, 255));
        btnDeletarTransp.setText("DELETAR");
        btnDeletarTransp.setBorder(null);
        btnDeletarTransp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarTranspActionPerformed(evt);
            }
        });

        lblEmailTransp.setText("EMAIL");

        lblFoneTransp.setText("TELEFONE");

        lblFreteTransp.setText("FRETE");

        lblIdTransp.setText("ID TRANSPORTADORA");

        javax.swing.GroupLayout panelCadTranspLayout = new javax.swing.GroupLayout(panelCadTransp);
        panelCadTransp.setLayout(panelCadTranspLayout);
        panelCadTranspLayout.setHorizontalGroup(
            panelCadTranspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCadTranspLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(btnAdicionarTransp, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPesquisarTransp, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(274, 274, 274))
            .addGroup(panelCadTranspLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(panelCadTranspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelCadTranspLayout.createSequentialGroup()
                        .addGroup(panelCadTranspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFoneTransp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFoneTransp)
                            .addGroup(panelCadTranspLayout.createSequentialGroup()
                                .addGroup(panelCadTranspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtIdTransp, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblIdTransp, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(33, 33, 33)
                                .addComponent(lblNomeTransp)))
                        .addGap(159, 159, 159))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelCadTranspLayout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addGroup(panelCadTranspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panelCadTranspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtEmailTransp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblEmailTransp))
                            .addGroup(panelCadTranspLayout.createSequentialGroup()
                                .addComponent(txtNomeTransp, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panelCadTranspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFreteTransp, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFreteTransp)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelCadTranspLayout.createSequentialGroup()
                                .addGap(101, 101, 101)
                                .addComponent(btnAlterarTransp, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDeletarTransp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        panelCadTranspLayout.setVerticalGroup(
            panelCadTranspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCadTranspLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(panelCadTranspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelCadTranspLayout.createSequentialGroup()
                        .addGroup(panelCadTranspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNomeTransp)
                            .addComponent(lblFreteTransp)
                            .addComponent(lblIdTransp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelCadTranspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNomeTransp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdTransp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtFreteTransp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCadTranspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCadTranspLayout.createSequentialGroup()
                        .addComponent(lblEmailTransp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmailTransp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCadTranspLayout.createSequentialGroup()
                        .addComponent(lblFoneTransp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFoneTransp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelCadTranspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCadTranspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPesquisarTransp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAdicionarTransp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCadTranspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDeletarTransp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAlterarTransp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(panelCadTransp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 530, 180));

        panelPesqTransp.setBackground(new java.awt.Color(255, 255, 255));

        lblTabTransp.setText("Pesquisar");

        tblTransp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "EMAIL", "TELEFONE", "FRETE"
            }
        ));
        jScrollPane1.setViewportView(tblTransp);

        txtTabTransp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTabTranspKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panelPesqTranspLayout = new javax.swing.GroupLayout(panelPesqTransp);
        panelPesqTransp.setLayout(panelPesqTranspLayout);
        panelPesqTranspLayout.setHorizontalGroup(
            panelPesqTranspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPesqTranspLayout.createSequentialGroup()
                .addGroup(panelPesqTranspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPesqTranspLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTabTransp)
                        .addGap(18, 18, 18)
                        .addComponent(txtTabTransp, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPesqTranspLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        panelPesqTranspLayout.setVerticalGroup(
            panelPesqTranspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPesqTranspLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelPesqTranspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTabTransp)
                    .addComponent(txtTabTransp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(panelPesqTransp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 530, 150));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarTranspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarTranspActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_btnAdicionarTranspActionPerformed

    private void btnPesquisarTranspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarTranspActionPerformed
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_btnPesquisarTranspActionPerformed

    private void btnAlterarTranspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarTranspActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_btnAlterarTranspActionPerformed

    private void btnDeletarTranspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarTranspActionPerformed
        // TODO add your handling code here:
        deletar();
    }//GEN-LAST:event_btnDeletarTranspActionPerformed

    private void txtTabTranspKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTabTranspKeyReleased
        // TODO add your handling code here:
        pesquisar_tabela();
    }//GEN-LAST:event_txtTabTranspKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarTransp;
    private javax.swing.JButton btnAlterarTransp;
    private javax.swing.JButton btnDeletarTransp;
    private javax.swing.JButton btnPesquisarTransp;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEmailTransp;
    private javax.swing.JLabel lblFoneTransp;
    private javax.swing.JLabel lblFreteTransp;
    private javax.swing.JLabel lblIdTransp;
    private javax.swing.JLabel lblNomeTransp;
    private javax.swing.JLabel lblTabTransp;
    private javax.swing.JPanel panelCadTransp;
    private javax.swing.JPanel panelPesqTransp;
    private javax.swing.JTable tblTransp;
    private javax.swing.JTextField txtEmailTransp;
    private javax.swing.JTextField txtFoneTransp;
    private javax.swing.JTextField txtFreteTransp;
    private javax.swing.JTextField txtIdTransp;
    private javax.swing.JTextField txtNomeTransp;
    private javax.swing.JTextField txtTabTransp;
    // End of variables declaration//GEN-END:variables
}
