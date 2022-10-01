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
public class PanelCadastroProduto extends javax.swing.JPanel {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public PanelCadastroProduto() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    private void adicionar() {
        String sql = "insert into produto(id_produto, nome, descricao, preço, id_marca, id_categoria, garantia, estoque) values(?,?,?,?,?,?,?,?)";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1,txtIdProd.getText());
            pst.setString(2,txtNomeProd.getText());
            pst.setString(3,txtDescricaoProd.getText());
            pst.setString(4,txtPrecoProd.getText());
            pst.setString(5,txtMarcaProd.getText());
            pst.setString(6,txtCategoriaProd.getText());
            pst.setString(7,txtGarantiaProd.getText());
            pst.setString(8, "0");
            
            int adicionado = pst.executeUpdate();
            
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso");
                txtIdProd.setText(null);
                txtNomeProd.setText(null);
                txtDescricaoProd.setText(null);
                txtPrecoProd.setText(null);
                txtMarcaProd.setText(null);
                txtCategoriaProd.setText(null);
                txtGarantiaProd.setText(null);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void pesquisar() {
        String sql = "select * from produto where id_produto = ?";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdProd.getText());
            rs = pst.executeQuery();
            
            if (rs.next()) {
                txtNomeProd.setText(rs.getString(2));
                txtDescricaoProd.setText(rs.getString(3));
                txtPrecoProd.setText(rs.getString(4));
                txtMarcaProd.setText(rs.getString(5));
                txtCategoriaProd.setText(rs.getString(6));
                txtGarantiaProd.setText(rs.getString(7));
            }
            else {
                JOptionPane.showMessageDialog(null,"Produto não cadastrado");
                txtIdProd.setText(null);
                txtNomeProd.setText(null);
                txtDescricaoProd.setText(null);
                txtPrecoProd.setText(null);
                txtMarcaProd.setText(null);
                txtCategoriaProd.setText(null);
                txtGarantiaProd.setText(null);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void alterar() {
        String sql = "update produto set nome = ?, descricao = ?, preço = ?, id_marca = ?, id_categoria = ?, garantia = ? where id_produto = ?";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1,txtNomeProd.getText());
            pst.setString(2,txtDescricaoProd.getText());
            pst.setString(3,txtPrecoProd.getText());
            pst.setString(4,txtMarcaProd.getText());
            pst.setString(5,txtCategoriaProd.getText());
            pst.setString(6,txtGarantiaProd.getText());
            pst.setString(7,txtIdProd.getText());
            
            int adicionado = pst.executeUpdate();
            
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Dados do produto alterados com sucesso");
                txtIdProd.setText(null);
                txtNomeProd.setText(null);
                txtDescricaoProd.setText(null);
                txtPrecoProd.setText(null);
                txtMarcaProd.setText(null);
                txtCategoriaProd.setText(null);
                txtGarantiaProd.setText(null);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void deletar() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este produto?", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from produto where id_produto = ?";
            
            try {
                pst = conexao.prepareCall(sql);
                pst.setString(1, txtIdProd.getText());
                int apagado = pst.executeUpdate();
                
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Produto removido com sucesso");
                    txtIdProd.setText(null);
                    txtNomeProd.setText(null);
                    txtDescricaoProd.setText(null);
                    txtPrecoProd.setText(null);
                    txtMarcaProd.setText(null);
                    txtCategoriaProd.setText(null);
                    txtGarantiaProd.setText(null);
                    
                }
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    private void pesquisar_tabela(){
        String sql= "select * from produto where nome like ?";
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
        btnAdicionarProd = new javax.swing.JButton();
        btnPesquisarProd = new javax.swing.JButton();
        lblIdCategoriaProd = new javax.swing.JLabel();
        btnAlterarProd = new javax.swing.JButton();
        txtCategoriaProd = new javax.swing.JTextField();
        btnDeletarProd = new javax.swing.JButton();
        lblIdProd = new javax.swing.JLabel();
        txtIdProd = new javax.swing.JTextField();
        txtNomeProd = new javax.swing.JTextField();
        txtMarcaProd = new javax.swing.JTextField();
        txtDescricaoProd = new javax.swing.JTextField();
        txtPrecoProd = new javax.swing.JTextField();
        lblIdMarcaProd = new javax.swing.JLabel();
        lblPrecoProd = new javax.swing.JLabel();
        lblDescricaoProd = new javax.swing.JLabel();
        lblNomeProd = new javax.swing.JLabel();
        txtGarantiaProd = new javax.swing.JTextField();
        lblGarantiaProd = new javax.swing.JLabel();
        panelPesqProd = new javax.swing.JPanel();
        lblTabProd = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProd = new javax.swing.JTable();
        txtTabProd = new javax.swing.JTextField();

        setBackground(new java.awt.Color(245, 245, 245));

        panelCadProd.setBackground(new java.awt.Color(255, 255, 255));

        btnAdicionarProd.setBackground(new java.awt.Color(255, 86, 86));
        btnAdicionarProd.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnAdicionarProd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionarProd.setText("ADICIONAR");
        btnAdicionarProd.setBorder(null);
        btnAdicionarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarProdActionPerformed(evt);
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

        lblIdCategoriaProd.setText("CATEGORIA");

        btnAlterarProd.setBackground(new java.awt.Color(255, 86, 86));
        btnAlterarProd.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnAlterarProd.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterarProd.setText("ALTERAR");
        btnAlterarProd.setBorder(null);
        btnAlterarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarProdActionPerformed(evt);
            }
        });

        btnDeletarProd.setBackground(new java.awt.Color(255, 86, 86));
        btnDeletarProd.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnDeletarProd.setForeground(new java.awt.Color(255, 255, 255));
        btnDeletarProd.setText("DELETAR");
        btnDeletarProd.setBorder(null);
        btnDeletarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarProdActionPerformed(evt);
            }
        });

        lblIdProd.setText("ID PRODUTO");

        lblIdMarcaProd.setText("MARCA");

        lblPrecoProd.setText("PREÇO");

        lblDescricaoProd.setText("DESCRIÇÃO PRODUTO");

        lblNomeProd.setText("NOME PRODUTO");

        lblGarantiaProd.setText("GARANTIA");

        javax.swing.GroupLayout panelCadProdLayout = new javax.swing.GroupLayout(panelCadProd);
        panelCadProd.setLayout(panelCadProdLayout);
        panelCadProdLayout.setHorizontalGroup(
            panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCadProdLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCadProdLayout.createSequentialGroup()
                        .addGroup(panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCadProdLayout.createSequentialGroup()
                                .addGroup(panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtIdProd, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblIdProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(37, 37, 37)
                                .addGroup(panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtCategoriaProd, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblIdCategoriaProd, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addComponent(txtNomeProd, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNomeProd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCadProdLayout.createSequentialGroup()
                                .addGroup(panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblIdMarcaProd)
                                    .addComponent(txtMarcaProd, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPrecoProd)
                                    .addComponent(txtPrecoProd, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblGarantiaProd)
                                    .addComponent(txtGarantiaProd, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblDescricaoProd)
                                .addComponent(txtDescricaoProd, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelCadProdLayout.createSequentialGroup()
                        .addComponent(btnAdicionarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPesquisarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnAlterarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnDeletarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );
        panelCadProdLayout.setVerticalGroup(
            panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCadProdLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCadProdLayout.createSequentialGroup()
                                .addComponent(lblIdProd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelCadProdLayout.createSequentialGroup()
                                .addComponent(lblPrecoProd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPrecoProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCadProdLayout.createSequentialGroup()
                                .addComponent(lblGarantiaProd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGarantiaProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelCadProdLayout.createSequentialGroup()
                            .addComponent(lblIdMarcaProd)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtMarcaProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCadProdLayout.createSequentialGroup()
                        .addComponent(lblIdCategoriaProd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCategoriaProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNomeProd)
                    .addComponent(lblDescricaoProd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescricaoProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdicionarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelCadProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDeletarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAlterarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPesquisarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelPesqProd.setBackground(new java.awt.Color(255, 255, 255));

        lblTabProd.setText("Pesquisar");

        tblProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "DESCRIÇÃO", "PREÇO", "MARCA", "CATEGORIA", "GARANTIA"
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPesqProdLayout.setVerticalGroup(
            panelPesqProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPesqProdLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(panelPesqProdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTabProd)
                    .addComponent(txtTabProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 558, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 21, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(panelPesqProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelCadProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 20, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelPesqProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(panelCadProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarProdActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_btnAdicionarProdActionPerformed

    private void btnPesquisarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarProdActionPerformed
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_btnPesquisarProdActionPerformed

    private void btnAlterarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarProdActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_btnAlterarProdActionPerformed

    private void btnDeletarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarProdActionPerformed
        // TODO add your handling code here:
        deletar();
    }//GEN-LAST:event_btnDeletarProdActionPerformed

    private void txtTabProdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTabProdKeyReleased
        // TODO add your handling code here:
        pesquisar_tabela();
    }//GEN-LAST:event_txtTabProdKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarProd;
    private javax.swing.JButton btnAlterarProd;
    private javax.swing.JButton btnDeletarProd;
    private javax.swing.JButton btnPesquisarProd;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescricaoProd;
    private javax.swing.JLabel lblGarantiaProd;
    private javax.swing.JLabel lblIdCategoriaProd;
    private javax.swing.JLabel lblIdMarcaProd;
    private javax.swing.JLabel lblIdProd;
    private javax.swing.JLabel lblNomeProd;
    private javax.swing.JLabel lblPrecoProd;
    private javax.swing.JLabel lblTabProd;
    private javax.swing.JPanel panelCadProd;
    private javax.swing.JPanel panelPesqProd;
    private javax.swing.JTable tblProd;
    private javax.swing.JTextField txtCategoriaProd;
    private javax.swing.JTextField txtDescricaoProd;
    private javax.swing.JTextField txtGarantiaProd;
    private javax.swing.JTextField txtIdProd;
    private javax.swing.JTextField txtMarcaProd;
    private javax.swing.JTextField txtNomeProd;
    private javax.swing.JTextField txtPrecoProd;
    private javax.swing.JTextField txtTabProd;
    // End of variables declaration//GEN-END:variables
}
