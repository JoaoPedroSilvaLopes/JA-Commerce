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
public class PanelCadastroProdutoEstoque extends javax.swing.JPanel {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public PanelCadastroProdutoEstoque() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    private void pesquisar() {
        String sql = "select * from produto where id_produto = ?";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdProd.getText());
            rs = pst.executeQuery();
            
            if (rs.next()) {
                txtNomeProd.setText(rs.getString(2));
                txtEstoqueProd.setText(rs.getString(8));
            }
            else {
                JOptionPane.showMessageDialog(null,"Produto não cadastrado");
                txtIdProd.setText(null);
                txtNomeProd.setText(null);
                txtEstoqueProd.setText(null);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void adicionar_estoque() {
        String sql = "update produto set estoque = ? where id_produto = ?";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1,txtEstoqueProd.getText());
            pst.setString(2,txtIdProd.getText());
            
            int adicionado = pst.executeUpdate();
            
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null,"Estoque adicionado com sucesso");
                txtIdProd.setText(null);
                txtNomeProd.setText(null);
                txtEstoqueProd.setText(null);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void remover_estoque() {
        String sql = "update produto set estoque = ? where id_produto = ?";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1,txtEstoqueProd.getText());
            pst.setString(2,txtIdProd.getText());
            
            int adicionado = pst.executeUpdate();
            
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Estoque removido com sucesso");
                txtIdProd.setText(null);
                txtNomeProd.setText(null);
                txtEstoqueProd.setText(null);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void pesquisar_tabela(){
        String sql= "select id_produto, nome, estoque from produto where nome like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtTabProd.getText() + "%");
            rs=pst.executeQuery();
            tblProd.setModel(DbUtils.resultSetToTableModel(rs));

        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCadProd = new javax.swing.JPanel();
        btnAdicionarEstoque = new javax.swing.JButton();
        btnPesquisarProd = new javax.swing.JButton();
        lblIdCategoriaProd = new javax.swing.JLabel();
        btnRemoverEstoque = new javax.swing.JButton();
        txtEstoqueProd = new javax.swing.JTextField();
        lblIdProd = new javax.swing.JLabel();
        txtIdProd = new javax.swing.JTextField();
        txtNomeProd = new javax.swing.JTextField();
        lblNomeProd = new javax.swing.JLabel();
        panelPesqProd = new javax.swing.JPanel();
        lblTabProd = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProd = new javax.swing.JTable();
        txtTabProd = new javax.swing.JTextField();

        setBackground(new java.awt.Color(245, 245, 245));

        panelCadProd.setBackground(new java.awt.Color(255, 255, 255));

        btnAdicionarEstoque.setBackground(new java.awt.Color(255, 86, 86));
        btnAdicionarEstoque.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnAdicionarEstoque.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionarEstoque.setText("ADICIONAR ESTOQUE");
        btnAdicionarEstoque.setBorder(null);
        btnAdicionarEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarEstoqueActionPerformed(evt);
            }
        });

        btnPesquisarProd.setBackground(new java.awt.Color(255, 86, 86));
        btnPesquisarProd.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnPesquisarProd.setForeground(new java.awt.Color(255, 255, 255));
        btnPesquisarProd.setText("PESQUISAR");
        btnPesquisarProd.setBorder(null);
        btnPesquisarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarProdActionPerformed(evt);
            }
        });

        lblIdCategoriaProd.setText("ESTOQUE");

        btnRemoverEstoque.setBackground(new java.awt.Color(255, 86, 86));
        btnRemoverEstoque.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnRemoverEstoque.setForeground(new java.awt.Color(255, 255, 255));
        btnRemoverEstoque.setText("REMOVER ESTOQUE");
        btnRemoverEstoque.setBorder(null);
        btnRemoverEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverEstoqueActionPerformed(evt);
            }
        });

        lblIdProd.setText("ID PRODUTO");

        lblNomeProd.setText("NOME PRODUTO");

        javax.swing.GroupLayout panelCadProdLayout = new javax.swing.GroupLayout(panelCadProd);
        panelCadProd.setLayout(panelCadProdLayout);
        panelCadProdLayout.setHorizontalGroup(
            panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCadProdLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPesquisarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdProd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNomeProd, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionarEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomeProd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEstoqueProd)
                    .addComponent(lblIdCategoriaProd)
                    .addComponent(btnRemoverEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );
        panelCadProdLayout.setVerticalGroup(
            panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCadProdLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCadProdLayout.createSequentialGroup()
                        .addComponent(lblIdProd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCadProdLayout.createSequentialGroup()
                        .addComponent(lblIdCategoriaProd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEstoqueProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCadProdLayout.createSequentialGroup()
                        .addComponent(lblNomeProd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addGroup(panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPesquisarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionarEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemoverEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        panelPesqProd.setBackground(new java.awt.Color(255, 255, 255));

        lblTabProd.setText("Pesquisar");

        tblProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "NOME", "ESTOQUE"
            }
        ));
        jScrollPane1.setViewportView(tblProd);

        txtTabProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTabProdKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panelPesqProdLayout = new javax.swing.GroupLayout(panelPesqProd);
        panelPesqProd.setLayout(panelPesqProdLayout);
        panelPesqProdLayout.setHorizontalGroup(
            panelPesqProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPesqProdLayout.createSequentialGroup()
                .addGroup(panelPesqProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPesqProdLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTabProd)
                        .addGap(18, 18, 18)
                        .addComponent(txtTabProd, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPesqProdLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        panelPesqProdLayout.setVerticalGroup(
            panelPesqProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPesqProdLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelPesqProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTabProd)
                    .addComponent(txtTabProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
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
                        .addComponent(panelPesqProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelCadProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 10, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 414, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 12, Short.MAX_VALUE)
                    .addComponent(panelPesqProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(panelCadProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 11, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarEstoqueActionPerformed
        // TODO add your handling code here:
        adicionar_estoque();
    }//GEN-LAST:event_btnAdicionarEstoqueActionPerformed

    private void btnPesquisarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarProdActionPerformed
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_btnPesquisarProdActionPerformed

    private void btnRemoverEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverEstoqueActionPerformed
        // TODO add your handling code here:
        remover_estoque();
    }//GEN-LAST:event_btnRemoverEstoqueActionPerformed

    private void txtTabProdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTabProdKeyReleased
        // TODO add your handling code here:
        pesquisar_tabela();
    }//GEN-LAST:event_txtTabProdKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarEstoque;
    private javax.swing.JButton btnPesquisarProd;
    private javax.swing.JButton btnRemoverEstoque;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIdCategoriaProd;
    private javax.swing.JLabel lblIdProd;
    private javax.swing.JLabel lblNomeProd;
    private javax.swing.JLabel lblTabProd;
    private javax.swing.JPanel panelCadProd;
    private javax.swing.JPanel panelPesqProd;
    private javax.swing.JTable tblProd;
    private javax.swing.JTextField txtEstoqueProd;
    private javax.swing.JTextField txtIdProd;
    private javax.swing.JTextField txtNomeProd;
    private javax.swing.JTextField txtTabProd;
    // End of variables declaration//GEN-END:variables
}
