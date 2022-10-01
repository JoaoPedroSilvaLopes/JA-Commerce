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
public class PanelCadastroCategoria extends javax.swing.JPanel {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public PanelCadastroCategoria() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    private void adicionar() {
        String sql = "insert into categoria(id_categoria, nome_categoria) values(?,?)";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1,txtIdCategoria.getText());
            pst.setString(2,txtNomeCategoria.getText());
            
            int adicionado = pst.executeUpdate();
            
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null,"Categoria cadastrada com sucesso");
                txtIdCategoria.setText(null);
                txtNomeCategoria.setText(null);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void pesquisar() {
        String sql = "select * from categoria where id_categoria = ?";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdCategoria.getText());
            rs = pst.executeQuery();
            
            if (rs.next()) {
                txtNomeCategoria.setText(rs.getString(2));
            }
            else {
                JOptionPane.showMessageDialog(null,"Categoria não cadastrada");
                txtIdCategoria.setText(null);
                txtNomeCategoria.setText(null);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void alterar() {
        String sql = "update categoria set nome_categoria = ? where id_categoria = ?";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1,txtNomeCategoria.getText());
            pst.setString(2,txtIdCategoria.getText());
            
            int adicionado = pst.executeUpdate();
            
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Dados da categoria alterados com sucesso");
                txtIdCategoria.setText(null);
                txtNomeCategoria.setText(null);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void deletar() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esta categoria?", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from categoria where id_categoria = ?";
            
            try {
                pst = conexao.prepareCall(sql);
                pst.setString(1, txtIdCategoria.getText());
                int apagado = pst.executeUpdate();
                
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Categoria removida com sucesso");
                    txtIdCategoria.setText(null);
                    txtNomeCategoria.setText(null);
                }
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    private void pesquisar_tabela(){
        String sql= "select * from categoria where nome_categoria like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtTabCategoria.getText() + "%");
            rs=pst.executeQuery();
            tblCategoria.setModel(DbUtils.resultSetToTableModel(rs));

        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPesqCategoria = new javax.swing.JPanel();
        lblTabCategoria = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCategoria = new javax.swing.JTable();
        txtTabCategoria = new javax.swing.JTextField();
        lblNomeCategoria = new javax.swing.JLabel();
        txtNomeCategoria = new javax.swing.JTextField();
        lblIdCategoria = new javax.swing.JLabel();
        btnAdicionarCategoria = new javax.swing.JButton();
        btnPesquisarCategoria = new javax.swing.JButton();
        btnAlterarCategoria = new javax.swing.JButton();
        btnDeletarCategoria = new javax.swing.JButton();
        txtIdCategoria = new javax.swing.JTextField();

        setBackground(new java.awt.Color(245, 245, 245));

        panelPesqCategoria.setBackground(new java.awt.Color(255, 255, 255));

        lblTabCategoria.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        lblTabCategoria.setForeground(new java.awt.Color(55, 55, 55));
        lblTabCategoria.setText("Pesquisar");

        tblCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "NOME"
            }
        ));
        jScrollPane1.setViewportView(tblCategoria);

        txtTabCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTabCategoriaKeyReleased(evt);
            }
        });

        lblNomeCategoria.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        lblNomeCategoria.setForeground(new java.awt.Color(55, 55, 55));
        lblNomeCategoria.setText("NOME CATEGORIA");

        lblIdCategoria.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        lblIdCategoria.setForeground(new java.awt.Color(55, 55, 55));
        lblIdCategoria.setText("ID CATEGORIA");

        btnAdicionarCategoria.setBackground(new java.awt.Color(255, 86, 86));
        btnAdicionarCategoria.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnAdicionarCategoria.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionarCategoria.setText("ADICIONAR");
        btnAdicionarCategoria.setBorder(null);
        btnAdicionarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarCategoriaActionPerformed(evt);
            }
        });

        btnPesquisarCategoria.setBackground(new java.awt.Color(255, 86, 86));
        btnPesquisarCategoria.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnPesquisarCategoria.setForeground(new java.awt.Color(255, 255, 255));
        btnPesquisarCategoria.setText("PESQUISAR");
        btnPesquisarCategoria.setBorder(null);
        btnPesquisarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarCategoriaActionPerformed(evt);
            }
        });

        btnAlterarCategoria.setBackground(new java.awt.Color(255, 86, 86));
        btnAlterarCategoria.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnAlterarCategoria.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterarCategoria.setText("ALTERAR");
        btnAlterarCategoria.setBorder(null);
        btnAlterarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarCategoriaActionPerformed(evt);
            }
        });

        btnDeletarCategoria.setBackground(new java.awt.Color(255, 86, 86));
        btnDeletarCategoria.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnDeletarCategoria.setForeground(new java.awt.Color(255, 255, 255));
        btnDeletarCategoria.setText("DELETAR");
        btnDeletarCategoria.setBorder(null);
        btnDeletarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPesqCategoriaLayout = new javax.swing.GroupLayout(panelPesqCategoria);
        panelPesqCategoria.setLayout(panelPesqCategoriaLayout);
        panelPesqCategoriaLayout.setHorizontalGroup(
            panelPesqCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPesqCategoriaLayout.createSequentialGroup()
                .addGroup(panelPesqCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelPesqCategoriaLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(panelPesqCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPesqCategoriaLayout.createSequentialGroup()
                                .addGroup(panelPesqCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblIdCategoria))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelPesqCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNomeCategoria)
                                    .addComponent(txtNomeCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelPesqCategoriaLayout.createSequentialGroup()
                                .addComponent(lblTabCategoria)
                                .addGap(18, 18, 18)
                                .addComponent(txtTabCategoria))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelPesqCategoriaLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdicionarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPesquisarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btnAlterarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeletarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );
        panelPesqCategoriaLayout.setVerticalGroup(
            panelPesqCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPesqCategoriaLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelPesqCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTabCategoria)
                    .addComponent(txtTabCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelPesqCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeCategoria)
                    .addComponent(lblIdCategoria))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPesqCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(panelPesqCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDeletarCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAlterarCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPesqCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPesquisarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAdicionarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPesqCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPesqCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTabCategoriaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTabCategoriaKeyReleased
        // TODO add your handling code here:
        pesquisar_tabela();
    }//GEN-LAST:event_txtTabCategoriaKeyReleased

    private void btnAlterarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarCategoriaActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_btnAlterarCategoriaActionPerformed

    private void btnAdicionarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarCategoriaActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_btnAdicionarCategoriaActionPerformed

    private void btnPesquisarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarCategoriaActionPerformed
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_btnPesquisarCategoriaActionPerformed

    private void btnDeletarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarCategoriaActionPerformed
        // TODO add your handling code here:
        deletar();
    }//GEN-LAST:event_btnDeletarCategoriaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarCategoria;
    private javax.swing.JButton btnAlterarCategoria;
    private javax.swing.JButton btnDeletarCategoria;
    private javax.swing.JButton btnPesquisarCategoria;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIdCategoria;
    private javax.swing.JLabel lblNomeCategoria;
    private javax.swing.JLabel lblTabCategoria;
    private javax.swing.JPanel panelPesqCategoria;
    private javax.swing.JTable tblCategoria;
    private javax.swing.JTextField txtIdCategoria;
    private javax.swing.JTextField txtNomeCategoria;
    private javax.swing.JTextField txtTabCategoria;
    // End of variables declaration//GEN-END:variables
}
