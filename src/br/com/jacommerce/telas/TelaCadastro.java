/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.jacommerce.telas;

/**
 *
 * @author finkl
 */
public class TelaCadastro extends javax.swing.JFrame {

    public TelaCadastro() {
        initComponents();
    }
    
    public void close() {
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        lblTituloAdm = new javax.swing.JLabel();
        sidebarCadastro = new javax.swing.JPanel();
        cadOptions = new javax.swing.JPanel();
        btnCadTransp = new javax.swing.JButton();
        btnCadMarca = new javax.swing.JButton();
        btnCadProd = new javax.swing.JButton();
        btnCadCategoria = new javax.swing.JButton();
        btnCadProd1 = new javax.swing.JButton();
        lblTitleCad = new javax.swing.JLabel();
        btnSair = new javax.swing.JButton();
        multTelas = new javax.swing.JTabbedPane();
        panelCadastroTransportadora = new br.com.jacommerce.panelsAdm.PanelCadastroTransportadora();
        panelCadastroMarca = new br.com.jacommerce.panelsAdm.PanelCadastroMarca();
        panelCadastroCategoria = new br.com.jacommerce.panelsAdm.PanelCadastroCategoria();
        panelCadastroProduto = new br.com.jacommerce.panelsAdm.PanelCadastroProduto();
        panelCadastroProdutoEstoque = new br.com.jacommerce.panelsAdm.PanelCadastroProdutoEstoque();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setBackground(new java.awt.Color(255, 86, 86));

        lblTituloAdm.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTituloAdm.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloAdm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloAdm.setText("CADASTRAR TRANSPORTADORA");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(205, Short.MAX_VALUE)
                .addComponent(lblTituloAdm)
                .addGap(164, 164, 164))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblTituloAdm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 70));

        sidebarCadastro.setBackground(new java.awt.Color(245, 245, 245));

        cadOptions.setBackground(new java.awt.Color(255, 255, 255));

        btnCadTransp.setBackground(new java.awt.Color(255, 86, 86));
        btnCadTransp.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCadTransp.setForeground(new java.awt.Color(255, 255, 255));
        btnCadTransp.setText("TRANSPORTADORA");
        btnCadTransp.setBorder(null);
        btnCadTransp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadTranspActionPerformed(evt);
            }
        });

        btnCadMarca.setBackground(new java.awt.Color(255, 86, 86));
        btnCadMarca.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCadMarca.setForeground(new java.awt.Color(255, 255, 255));
        btnCadMarca.setText("MARCA");
        btnCadMarca.setBorder(null);
        btnCadMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadMarcaActionPerformed(evt);
            }
        });

        btnCadProd.setBackground(new java.awt.Color(255, 86, 86));
        btnCadProd.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCadProd.setForeground(new java.awt.Color(255, 255, 255));
        btnCadProd.setText("PRODUTO");
        btnCadProd.setBorder(null);
        btnCadProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadProdActionPerformed(evt);
            }
        });

        btnCadCategoria.setBackground(new java.awt.Color(255, 86, 86));
        btnCadCategoria.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCadCategoria.setForeground(new java.awt.Color(255, 255, 255));
        btnCadCategoria.setText("CATEGORIA");
        btnCadCategoria.setBorder(null);
        btnCadCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadCategoriaActionPerformed(evt);
            }
        });

        btnCadProd1.setBackground(new java.awt.Color(255, 86, 86));
        btnCadProd1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCadProd1.setForeground(new java.awt.Color(255, 255, 255));
        btnCadProd1.setText("ESTOQUE");
        btnCadProd1.setBorder(null);
        btnCadProd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadProd1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cadOptionsLayout = new javax.swing.GroupLayout(cadOptions);
        cadOptions.setLayout(cadOptionsLayout);
        cadOptionsLayout.setHorizontalGroup(
            cadOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cadOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cadOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCadTransp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCadMarca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCadProd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCadCategoria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCadProd1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        cadOptionsLayout.setVerticalGroup(
            cadOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cadOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCadTransp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCadMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCadCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCadProd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCadProd1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTitleCad.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblTitleCad.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleCad.setText("Cadastrar:");

        btnSair.setBackground(new java.awt.Color(102, 102, 102));
        btnSair.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSair.setForeground(new java.awt.Color(255, 255, 255));
        btnSair.setText("SAIR");
        btnSair.setBorder(null);
        btnSair.setBorderPainted(false);
        btnSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSairMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout sidebarCadastroLayout = new javax.swing.GroupLayout(sidebarCadastro);
        sidebarCadastro.setLayout(sidebarCadastroLayout);
        sidebarCadastroLayout.setHorizontalGroup(
            sidebarCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidebarCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cadOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(sidebarCadastroLayout.createSequentialGroup()
                        .addGroup(sidebarCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitleCad)
                            .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        sidebarCadastroLayout.setVerticalGroup(
            sidebarCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidebarCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitleCad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cadOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel1.add(sidebarCadastro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 200, 410));

        multTelas.addTab("tab1", panelCadastroTransportadora);
        multTelas.addTab("tab2", panelCadastroMarca);
        multTelas.addTab("tab5", panelCadastroCategoria);
        multTelas.addTab("tab4", panelCadastroProduto);
        multTelas.addTab("tab5", panelCadastroProdutoEstoque);

        jPanel1.add(multTelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 570, 450));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseClicked
        // TODO add your handling code here:
        close();
    }//GEN-LAST:event_btnSairMouseClicked

    private void btnCadTranspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadTranspActionPerformed
        // TODO add your handling code here:
        lblTituloAdm.setText("CADASTRAR TRANSPORTADORA");
        multTelas.setSelectedIndex(0);
    }//GEN-LAST:event_btnCadTranspActionPerformed

    private void btnCadMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadMarcaActionPerformed
        // TODO add your handling code here:
        lblTituloAdm.setText("CADASTRAR MARCA");
        multTelas.setSelectedIndex(1);
    }//GEN-LAST:event_btnCadMarcaActionPerformed

    private void btnCadCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadCategoriaActionPerformed
        // TODO add your handling code here:
        lblTituloAdm.setText("CADASTRAR CATEGORIA");
        multTelas.setSelectedIndex(2);
    }//GEN-LAST:event_btnCadCategoriaActionPerformed

    private void btnCadProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadProdActionPerformed
        // TODO add your handling code here:
        lblTituloAdm.setText("CADASTRAR PRODUTO");
        multTelas.setSelectedIndex(3);
    }//GEN-LAST:event_btnCadProdActionPerformed

    private void btnCadProd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadProd1ActionPerformed
        // TODO add your handling code here:
        lblTituloAdm.setText("CONTROLE DE ESTOQUE");
        multTelas.setSelectedIndex(4);
    }//GEN-LAST:event_btnCadProd1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadCategoria;
    private javax.swing.JButton btnCadMarca;
    private javax.swing.JButton btnCadProd;
    private javax.swing.JButton btnCadProd1;
    private javax.swing.JButton btnCadTransp;
    private javax.swing.JButton btnSair;
    private javax.swing.JPanel cadOptions;
    private javax.swing.JPanel header;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblTitleCad;
    private javax.swing.JLabel lblTituloAdm;
    private javax.swing.JTabbedPane multTelas;
    private br.com.jacommerce.panelsAdm.PanelCadastroCategoria panelCadastroCategoria;
    private br.com.jacommerce.panelsAdm.PanelCadastroMarca panelCadastroMarca;
    private br.com.jacommerce.panelsAdm.PanelCadastroProduto panelCadastroProduto;
    private br.com.jacommerce.panelsAdm.PanelCadastroProdutoEstoque panelCadastroProdutoEstoque;
    private br.com.jacommerce.panelsAdm.PanelCadastroTransportadora panelCadastroTransportadora;
    private javax.swing.JPanel sidebarCadastro;
    // End of variables declaration//GEN-END:variables
}
