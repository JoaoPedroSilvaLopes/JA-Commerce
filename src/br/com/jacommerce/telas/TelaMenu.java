/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.jacommerce.telas;

import br.com.jacommerce.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author finkl
 */
public class TelaMenu extends javax.swing.JFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    boolean token = false;
    boolean adm = false;
    float preçoTotal = 0;
    
    String id = "";
    String email = "";
    String nome_usuario = "";
    String data_nascimento = "";
    String sexo = "";
    String cpf = "";

    public TelaMenu(int id, String nome, String cpf, String sexo, String data_nascimento) {
        initComponents();
        conexao = ModuloConexao.conector();
        isLogin(id);
        
        add_table("select * from produto");
    }
    
    private void cadastrar_usuario() {
        String sql = "insert into usuario(cpf, nome_usuario, email, senha, data_nascimento, sexo, id_cargo) values(?,?,?,?,?,?,?)";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1,txtCpfCad.getText());
            pst.setString(2,txtUsuarioCad.getText());
            pst.setString(3,txtEmailCad.getText());
            pst.setString(4,txtSenhaCad.getText());
            pst.setString(5,txtDateCad.getText());
            
            if (cbSexCad.getToolTipText() == "Masculino") {
                pst.setString(6, "M");
            } 
            else if (cbSexCad.getToolTipText() == "Feminino") {
                pst.setString(6, "F");
            }
            else {
                pst.setString(6, "X");
            }
            
            pst.setString(7, "2");
            
            int adicionado = pst.executeUpdate();
            
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null,"Cadastrado realizado com sucesso");
                txtCpfCad.setText(null);
                txtUsuarioCad.setText(null);
                txtEmailCad.setText(null);
                txtSenhaCad.setText(null);
                txtDateCad.setText(null);
                cbSexCad.setSelectedIndex(0);
                multTelas.setSelectedIndex(0);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void logar() {
        String sql = "select * from usuario where email = ? and senha = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuario.getText());
            pst.setString(2, txtSenha.getText());
            
            rs = pst.executeQuery();
            
            if (rs.next()) {  
                id = rs.getString(1);
                cpf = rs.getString(2);
                nome_usuario = rs.getString(3);
                email = rs.getString(4);
                data_nascimento = rs.getString(6);
                
                switch (rs.getString(7)) {
                    case "M":
                        sexo = "Masculino";
                        break;
                    case "F":
                        sexo = "Feminino";
                        break;
                    default:
                        sexo = "Outro";
                        break;
                }
                        
                int index = Integer.parseInt(rs.getString(8));
                
                adm = index == 1 ? true : false;
                
                if (adm == true) {
                    TelaCadastro telaAdm = new TelaCadastro();
                    telaAdm.setVisible(true);
                    this.dispose();
                }
                else {
                    multTelas.setSelectedIndex(0);
                    lblLogin.setText("Minha conta");
                    email = txtUsuario.getText();
                    
                    lblNomeUsuario.setText(nome_usuario);
                    lblEmailUsuario.setText("Email: " + email);
                    lblDataUsuario.setText("Data de nascimento: " + data_nascimento);
                    lblSexoUsuario.setText("Sexo: " + sexo);
                    lblCpfUsuario.setText("CPF: " + cpf);
                    
                    isLogin(index);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha invalido");
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public boolean isLogin(int id) {
        return (id > 0) ? (token = true) : (token = false);
    }
    
    public void close() {
        this.dispose();
    }
    
    public void cadastrar_endereço() {
        String sql = "insert into endereco(endereco, cidade, estado, id_usuario) values(?,?,?,?)";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1,txtEndereçoCad.getText());
            pst.setString(2,txtCidadeCad.getText());
            pst.setString(3,cbEstado.getSelectedItem().toString());
            pst.setString(4,id);
            
            int adicionado = pst.executeUpdate();
            
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null,"Endereço cadastrado com sucesso");
                txtEndereçoCad.setText(null);
                txtCidadeCad.setText(null);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void cadastrar_telefone() {
        String sql = "insert into telefone(id_usuario, telefone) values(?,?)";
        
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1,id);
            pst.setString(2,txtTelefoneCad.getText());
            
            int adicionado = pst.executeUpdate();
            
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null,"Telefone cadastrado com sucesso");
                txtTelefoneCad.setText(null);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void is_disable_pedido() {
        if (lblValorPedido.getText() == "R$ 00,00") {
            btnFazerPedido.setEnabled(false);
        }
        else {
            btnFazerPedido.setEnabled(true);
        }
    }
    
    public void pegar_transportadora() {
        cbTransportadora.removeAllItems();
        String sql= "select * from transportadora";
        
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()) {
                cbTransportadora.addItem(rs.getString(2) + " Frete R$ " + rs.getString(3));
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void pegar_endereço() {
        cbEndereco.removeAllItems();
        String sql= "select endereco, cidade, estado from endereco where id_usuario = " + id + ";";
        
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()) {
                cbEndereco.addItem(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void add_table(String dados) {
        String sql = dados;       
        DefaultTableModel model = template_table();
        
        try {
            pst=conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            int contador = 0;
            
            while(rs.next()) {
                contador++;
                if (contador <= 1) {
                    model.addRow(
                        new Object[] { rs.getString(2), "R$ " + rs.getString(4), "COMPRAR" }
                    );
                }
                else {
                    contador = 0;
                }
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public DefaultTableModel template_table() {
        DefaultTableModel model = (DefaultTableModel)tblProd.getModel();
        model.setRowCount(0);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        
        TableColumn c1 = tblProd.getColumnModel().getColumn(0);
        TableColumn c2 = tblProd.getColumnModel().getColumn(1);
        TableColumn c3 = tblProd.getColumnModel().getColumn(2);
        
        c1.setPreferredWidth(150);
        c2.setPreferredWidth(75);
        c3.setPreferredWidth(75);
        
        tblProd.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        tblProd.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        tblProd.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        
        tblProd.getTableHeader().setUI(null);
        
        tblProd.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tblProd.rowAtPoint(evt.getPoint());
                int col = tblProd.columnAtPoint(evt.getPoint());
                if (row >= 0 && col == 2) {
                    multTelas.setSelectedIndex(5);
                    String data = (String) tblProd.getValueAt(row, 0);
                    pagina_produto(data);
                }
            }
        });
        
        return model;
    }
    
    public void pagina_produto(String data) {
        String sql = "select p.descricao, p.preço, c.nome_marca, p.garantia, p.estoque "
        + " from produto as p"
        + " join marca as c on p.id_marca = c.id_marca"
        + " where p.nome like ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, data);
            rs = pst.executeQuery();

            if (rs.next()) {
                lblDescricao.setText("Descrição do produto: " + rs.getString(1));
                lblPreço.setText("R$ " + rs.getString(2));
                lblNomeMarca.setText(rs.getString(3));
                lblGarantia.setText("Garantia de " + rs.getString(4) + " meses");
                lblEstoque.setText("Unidades disponíveis " + rs.getString(5));
                lblNomeProduto.setText(data);
                
                if (token == true) {
                    settar_botao(true, false, Integer.parseInt(rs.getString(5)));
                }
                else {
                    settar_botao(false, false, Integer.parseInt(rs.getString(5)));
                }
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void settar_botao(Boolean comEstoque, Boolean semEstoque, int estoque) {
        if (estoque > 0) {
            btnAdicionarCarrinho.setText("ADICIONAR AO CARRINHO");
            btnAdicionarCarrinho.setEnabled(comEstoque);
        }
        else {
            btnAdicionarCarrinho.setText("FORA DE ESTOQUE");
            btnAdicionarCarrinho.setEnabled(semEstoque);
        }
    }
    
    public int pegar_valor(String nome) {
        String sql = "select estoque from produto where nome = ?";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return 0;
    }
    
    public void gerenciar_estoque() {
        int contador = tblProdutosCarrinho.getRowCount();
        
        for (int i = 0; i < contador; i++) {
            String nome = (String) tblProdutosCarrinho.getValueAt(i, 0);
            String sql = "update produto set estoque = ? where nome = ?";
            
            try {
                int estoqueNovo = pegar_valor(nome) - 1;
                
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, estoqueNovo);
                pst.setString(2, nome);
                int concluido = pst.executeUpdate();
                
                if (concluido > 0) {
                    JOptionPane.showMessageDialog(null,"Pedido feito com sucesso");
                }
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    public void limpar_carrinho(String texto) {
        String sql = "delete from produto_carrinho where id_usuario = " + id + ";";
        
        try {
            pst = conexao.prepareStatement(sql);
            int apagado = pst.executeUpdate();
            
            if (apagado > 0) {
                JOptionPane.showMessageDialog(null, texto);
                multTelas.setSelectedIndex(0);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupMarca = new javax.swing.ButtonGroup();
        btnGroupCategoria = new javax.swing.ButtonGroup();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        telaMenu = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        lblTituloApp = new javax.swing.JLabel();
        btnPesquisa = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        headerOptions = new javax.swing.JPanel();
        lblLogin = new javax.swing.JLabel();
        btnCarrinhho = new javax.swing.JButton();
        sidebarCategorias = new javax.swing.JPanel();
        panelFiltros = new javax.swing.JPanel();
        lblCategoria = new javax.swing.JLabel();
        lblMarca = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jRadioButton10 = new javax.swing.JRadioButton();
        jRadioButton11 = new javax.swing.JRadioButton();
        jRadioButton12 = new javax.swing.JRadioButton();
        jRadioButton13 = new javax.swing.JRadioButton();
        jRadioButton14 = new javax.swing.JRadioButton();
        jButton3 = new javax.swing.JButton();
        lblFiltro = new javax.swing.JLabel();
        multTelas = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProd = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        panelCadastro = new javax.swing.JPanel();
        txtEmailCad = new javax.swing.JTextField();
        txtUsuarioCad = new javax.swing.JTextField();
        txtCpfCad = new javax.swing.JTextField();
        txtDateCad = new javax.swing.JTextField();
        cbSexCad = new javax.swing.JComboBox<>();
        lblUsuarioCad = new javax.swing.JLabel();
        lblEmailCad = new javax.swing.JLabel();
        lblSenhaCad = new javax.swing.JLabel();
        lblCpfCad = new javax.swing.JLabel();
        lblDateCad = new javax.swing.JLabel();
        btnCadUsuario = new javax.swing.JButton();
        txtSenhaCad = new javax.swing.JPasswordField();
        titleLogin2 = new javax.swing.JLabel();
        panelLogin = new javax.swing.JPanel();
        btnLogin = new javax.swing.JButton();
        txtUsuario = new javax.swing.JTextField();
        lblSenha = new javax.swing.JLabel();
        lblLogin1 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        titleLogin1 = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 2), new java.awt.Dimension(0, 2), new java.awt.Dimension(32767, 2));
        jPanel16 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnCadastrarTelefone = new javax.swing.JButton();
        txtTelefoneCad = new javax.swing.JTextField();
        lblTelefoneCad = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnCadastrarEndereço = new javax.swing.JButton();
        txtEndereçoCad = new javax.swing.JTextField();
        lblEndereçoCad = new javax.swing.JLabel();
        txtCidadeCad = new javax.swing.JTextField();
        lblEstadoCad = new javax.swing.JLabel();
        lblCidadeCad = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        lblFiltro4 = new javax.swing.JLabel();
        lblEmailUsuario = new javax.swing.JLabel();
        lblSexoUsuario = new javax.swing.JLabel();
        lblDataUsuario = new javax.swing.JLabel();
        lblCpfUsuario = new javax.swing.JLabel();
        lblNomeUsuario = new javax.swing.JLabel();
        btnVerPedidos = new javax.swing.JButton();
        lblFiltro1 = new javax.swing.JLabel();
        lblFiltro2 = new javax.swing.JLabel();
        lblFiltro3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lblFiltro5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidos = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProdutosCarrinho = new javax.swing.JTable();
        titleLogin4 = new javax.swing.JLabel();
        lblEstadoCad1 = new javax.swing.JLabel();
        cbMetodoPagamento = new javax.swing.JComboBox<>();
        cbEndereco = new javax.swing.JComboBox<>();
        lblEstadoCad2 = new javax.swing.JLabel();
        lblEstadoCad3 = new javax.swing.JLabel();
        cbTransportadora = new javax.swing.JComboBox<>();
        btnLimparCarrinho = new javax.swing.JButton();
        lblFiltro7 = new javax.swing.JLabel();
        btnFazerPedido = new javax.swing.JButton();
        lblFiltro8 = new javax.swing.JLabel();
        lblValorPedido = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        lblNomeProduto = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        lblGarantia = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        lblEstoque = new javax.swing.JLabel();
        lblNomeMarca = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        btnAdicionarCarrinho = new javax.swing.JButton();
        lblPreço = new javax.swing.JLabel();

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jPanel12.setBackground(new java.awt.Color(255, 153, 0));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("COMPRAR");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel3)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 219, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        telaMenu.setBackground(new java.awt.Color(245, 245, 245));
        telaMenu.setMaximumSize(new java.awt.Dimension(1000, 700));
        telaMenu.setPreferredSize(new java.awt.Dimension(1000, 700));
        telaMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setBackground(new java.awt.Color(255, 86, 86));

        lblTituloApp.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTituloApp.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloApp.setText("JA-COMMERCE");
        lblTituloApp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTituloApp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTituloAppMouseClicked(evt);
            }
        });

        btnPesquisa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPesquisa.setForeground(new java.awt.Color(153, 153, 153));
        btnPesquisa.setToolTipText("");
        btnPesquisa.setBorder(null);
        btnPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnPesquisaKeyReleased(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("X");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        headerOptions.setBackground(new java.awt.Color(255, 86, 86));

        lblLogin.setBackground(new java.awt.Color(255, 153, 0));
        lblLogin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblLogin.setForeground(new java.awt.Color(255, 255, 255));
        lblLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogin.setText("Entrar ou cadastrar");
        lblLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLoginMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout headerOptionsLayout = new javax.swing.GroupLayout(headerOptions);
        headerOptions.setLayout(headerOptionsLayout);
        headerOptionsLayout.setHorizontalGroup(
            headerOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerOptionsLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(lblLogin)
                .addGap(18, 18, 18))
        );
        headerOptionsLayout.setVerticalGroup(
            headerOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogin)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        btnCarrinhho.setBackground(new java.awt.Color(255, 86, 86));
        btnCarrinhho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/jacommerce/icons/carrinho_icone.png"))); // NOI18N
        btnCarrinhho.setBorder(null);
        btnCarrinhho.setBorderPainted(false);
        btnCarrinhho.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCarrinhho.setDisabledIcon(null);
        btnCarrinhho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarrinhhoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lblTituloApp)
                .addGap(42, 42, 42)
                .addComponent(btnPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(headerOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCarrinhho, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lblTituloApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCarrinhho, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(headerOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 3, Short.MAX_VALUE)))
                .addContainerGap())
        );

        telaMenu.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 70));

        sidebarCategorias.setBackground(new java.awt.Color(245, 245, 245));
        sidebarCategorias.setMaximumSize(new java.awt.Dimension(200, 580));

        panelFiltros.setBackground(new java.awt.Color(255, 255, 255));

        lblCategoria.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCategoria.setForeground(new java.awt.Color(51, 51, 51));
        lblCategoria.setText("CATEGORIA");

        lblMarca.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblMarca.setForeground(new java.awt.Color(51, 51, 51));
        lblMarca.setText("MARCA");

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        btnGroupMarca.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton1.setText("Ngreenzinha");
        jRadioButton1.setBorder(null);
        jRadioButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        btnGroupMarca.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton2.setText("MAD");
        jRadioButton2.setBorder(null);
        jRadioButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jRadioButton3.setBackground(new java.awt.Color(255, 255, 255));
        btnGroupMarca.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton3.setText("CorS");
        jRadioButton3.setBorder(null);
        jRadioButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jRadioButton4.setBackground(new java.awt.Color(255, 255, 255));
        btnGroupMarca.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jRadioButton4.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton4.setText("King");
        jRadioButton4.setBorder(null);
        jRadioButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jRadioButton5.setBackground(new java.awt.Color(255, 255, 255));
        btnGroupMarca.add(jRadioButton5);
        jRadioButton5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jRadioButton5.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton5.setText("Int");
        jRadioButton5.setBorder(null);
        jRadioButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jRadioButton6.setBackground(new java.awt.Color(255, 255, 255));
        btnGroupMarca.add(jRadioButton6);
        jRadioButton6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jRadioButton6.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton6.setText("Sus");
        jRadioButton6.setBorder(null);
        jRadioButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jRadioButton7.setBackground(new java.awt.Color(255, 255, 255));
        btnGroupMarca.add(jRadioButton7);
        jRadioButton7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jRadioButton7.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton7.setText("Bytebyte");
        jRadioButton7.setBorder(null);
        jRadioButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jRadioButton8.setBackground(new java.awt.Color(255, 255, 255));
        btnGroupCategoria.add(jRadioButton8);
        jRadioButton8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jRadioButton8.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton8.setText("Memória");
        jRadioButton8.setBorder(null);
        jRadioButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jRadioButton9.setBackground(new java.awt.Color(255, 255, 255));
        btnGroupCategoria.add(jRadioButton9);
        jRadioButton9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jRadioButton9.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton9.setText("Gabinete");
        jRadioButton9.setToolTipText("");
        jRadioButton9.setBorder(null);
        jRadioButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jRadioButton10.setBackground(new java.awt.Color(255, 255, 255));
        btnGroupCategoria.add(jRadioButton10);
        jRadioButton10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jRadioButton10.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton10.setText("Placa mãe");
        jRadioButton10.setBorder(null);
        jRadioButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jRadioButton11.setBackground(new java.awt.Color(255, 255, 255));
        btnGroupCategoria.add(jRadioButton11);
        jRadioButton11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jRadioButton11.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton11.setText("Processador");
        jRadioButton11.setBorder(null);
        jRadioButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jRadioButton12.setBackground(new java.awt.Color(255, 255, 255));
        btnGroupCategoria.add(jRadioButton12);
        jRadioButton12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jRadioButton12.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton12.setText("Placa de video");
        jRadioButton12.setBorder(null);
        jRadioButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jRadioButton13.setBackground(new java.awt.Color(255, 255, 255));
        btnGroupCategoria.add(jRadioButton13);
        jRadioButton13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jRadioButton13.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton13.setText("Armazenamento");
        jRadioButton13.setBorder(null);
        jRadioButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jRadioButton14.setBackground(new java.awt.Color(255, 255, 255));
        btnGroupCategoria.add(jRadioButton14);
        jRadioButton14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jRadioButton14.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton14.setText("Fonte");
        jRadioButton14.setBorder(null);
        jRadioButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton3.setBackground(new java.awt.Color(255, 86, 86));
        jButton3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("PESQUISAR");
        jButton3.setBorder(null);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFiltrosLayout = new javax.swing.GroupLayout(panelFiltros);
        panelFiltros.setLayout(panelFiltrosLayout);
        panelFiltrosLayout.setHorizontalGroup(
            panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelFiltrosLayout.createSequentialGroup()
                        .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton14)
                            .addComponent(jRadioButton12)
                            .addComponent(jRadioButton11)
                            .addComponent(jRadioButton10)
                            .addComponent(jRadioButton9)
                            .addComponent(lblMarca)
                            .addComponent(lblCategoria)
                            .addComponent(jRadioButton8)
                            .addComponent(jRadioButton13)
                            .addComponent(jRadioButton7)
                            .addComponent(jRadioButton6)
                            .addComponent(jRadioButton5)
                            .addComponent(jRadioButton4)
                            .addComponent(jRadioButton3)
                            .addComponent(jRadioButton2)
                            .addComponent(jRadioButton1))
                        .addGap(0, 49, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelFiltrosLayout.setVerticalGroup(
            panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFiltrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMarca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(lblCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton14)
                .addGap(59, 59, 59)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblFiltro.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblFiltro.setForeground(new java.awt.Color(51, 51, 51));
        lblFiltro.setText("Filtrar por:");

        javax.swing.GroupLayout sidebarCategoriasLayout = new javax.swing.GroupLayout(sidebarCategorias);
        sidebarCategorias.setLayout(sidebarCategoriasLayout);
        sidebarCategoriasLayout.setHorizontalGroup(
            sidebarCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarCategoriasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidebarCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelFiltros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(sidebarCategoriasLayout.createSequentialGroup()
                        .addComponent(lblFiltro)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        sidebarCategoriasLayout.setVerticalGroup(
            sidebarCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidebarCategoriasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFiltro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        telaMenu.add(sidebarCategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 200, 610));

        multTelas.setFocusable(false);

        jPanel2.setBackground(new java.awt.Color(245, 245, 245));

        tblProd.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        tblProd.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tblProd.setForeground(new java.awt.Color(51, 51, 51));
        tblProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOME", "PREÇO", "COMPRAR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProd.setRowHeight(75);
        tblProd.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tblProd.setShowHorizontalLines(false);
        tblProd.setShowVerticalLines(false);
        tblProd.getTableHeader().setResizingAllowed(false);
        tblProd.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblProd);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        multTelas.addTab("tab3", jPanel2);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        panelCadastro.setBackground(new java.awt.Color(255, 255, 255));

        txtEmailCad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtEmailCad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 86, 86), 2));

        txtUsuarioCad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtUsuarioCad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 86, 86), 2));

        txtCpfCad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCpfCad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 86, 86), 2));

        txtDateCad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDateCad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 86, 86), 2));

        cbSexCad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino", "Outro", " " }));
        cbSexCad.setBorder(null);

        lblUsuarioCad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblUsuarioCad.setForeground(new java.awt.Color(102, 102, 102));
        lblUsuarioCad.setText("Usuario");

        lblEmailCad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblEmailCad.setForeground(new java.awt.Color(102, 102, 102));
        lblEmailCad.setText("Email");

        lblSenhaCad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSenhaCad.setForeground(new java.awt.Color(102, 102, 102));
        lblSenhaCad.setText("Senha");

        lblCpfCad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCpfCad.setForeground(new java.awt.Color(102, 102, 102));
        lblCpfCad.setText("CPF");

        lblDateCad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDateCad.setForeground(new java.awt.Color(102, 102, 102));
        lblDateCad.setText("Data de nascimento");

        btnCadUsuario.setBackground(new java.awt.Color(255, 86, 86));
        btnCadUsuario.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnCadUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnCadUsuario.setText("CADASTRAR");
        btnCadUsuario.setBorder(null);
        btnCadUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadUsuarioActionPerformed(evt);
            }
        });

        txtSenhaCad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSenhaCad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 86, 86), 2));

        javax.swing.GroupLayout panelCadastroLayout = new javax.swing.GroupLayout(panelCadastro);
        panelCadastro.setLayout(panelCadastroLayout);
        panelCadastroLayout.setHorizontalGroup(
            panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCadastroLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCadastroLayout.createSequentialGroup()
                        .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSenhaCad, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCpfCad)
                            .addComponent(lblEmailCad)
                            .addComponent(cbSexCad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDateCad, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCpfCad, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsuarioCad, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmailCad, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUsuarioCad)
                            .addComponent(lblSenhaCad)
                            .addComponent(lblDateCad))
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCadastroLayout.createSequentialGroup()
                        .addComponent(btnCadUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82))))
        );
        panelCadastroLayout.setVerticalGroup(
            panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCadastroLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblUsuarioCad, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuarioCad, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEmailCad, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmailCad, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSenhaCad, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenhaCad, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCpfCad, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCpfCad, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDateCad, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDateCad, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbSexCad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(btnCadUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        titleLogin2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        titleLogin2.setForeground(new java.awt.Color(51, 51, 51));
        titleLogin2.setText("Não possuo cadastro");

        panelLogin.setBackground(new java.awt.Color(255, 255, 255));

        btnLogin.setBackground(new java.awt.Color(255, 86, 86));
        btnLogin.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("ENTRAR");
        btnLogin.setBorder(null);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        txtUsuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 86, 86), 2));

        lblSenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSenha.setForeground(new java.awt.Color(102, 102, 102));
        lblSenha.setText("Senha");

        lblLogin1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblLogin1.setForeground(new java.awt.Color(102, 102, 102));
        lblLogin1.setText("Email");

        txtSenha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSenha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 86, 86), 2));

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSenha)
                            .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(lblSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        titleLogin1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        titleLogin1.setForeground(new java.awt.Color(51, 51, 51));
        titleLogin1.setText("Ja possuo cadastro");

        jPanel16.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 474, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLogin1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titleLogin2))
                .addGap(46, 46, 46))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLogin1)
                    .addComponent(titleLogin2))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );

        multTelas.addTab("tab2", jPanel1);

        jPanel3.setBackground(new java.awt.Color(245, 245, 245));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnCadastrarTelefone.setBackground(new java.awt.Color(255, 86, 86));
        btnCadastrarTelefone.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCadastrarTelefone.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastrarTelefone.setText("CADASTRAR TELEFONE");
        btnCadastrarTelefone.setBorder(null);
        btnCadastrarTelefone.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCadastrarTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarTelefoneActionPerformed(evt);
            }
        });

        txtTelefoneCad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTelefoneCad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 86, 86), 2));

        lblTelefoneCad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTelefoneCad.setForeground(new java.awt.Color(102, 102, 102));
        lblTelefoneCad.setText("Telefone");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTelefoneCad, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblTelefoneCad)
                                .addGap(241, 241, 241))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(btnCadastrarTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTelefoneCad, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefoneCad, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCadastrarTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btnCadastrarEndereço.setBackground(new java.awt.Color(255, 86, 86));
        btnCadastrarEndereço.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCadastrarEndereço.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastrarEndereço.setText("CADASTRAR ENDEREÇO");
        btnCadastrarEndereço.setBorder(null);
        btnCadastrarEndereço.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCadastrarEndereço.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEndereçoActionPerformed(evt);
            }
        });

        txtEndereçoCad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtEndereçoCad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 86, 86), 2));

        lblEndereçoCad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblEndereçoCad.setForeground(new java.awt.Color(102, 102, 102));
        lblEndereçoCad.setText("Endereço");

        txtCidadeCad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCidadeCad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 86, 86), 2));

        lblEstadoCad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblEstadoCad.setForeground(new java.awt.Color(102, 102, 102));
        lblEstadoCad.setText("Estado");

        lblCidadeCad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCidadeCad.setForeground(new java.awt.Color(102, 102, 102));
        lblCidadeCad.setText("Cidade");

        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RO", "RR", "SC", "SP", "SE", "TO", "DF" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(91, Short.MAX_VALUE)
                .addComponent(btnCadastrarEndereço, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCidadeCad)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblEndereçoCad)
                        .addComponent(lblEstadoCad)
                        .addComponent(txtEndereçoCad, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                        .addComponent(txtCidadeCad)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEndereçoCad, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEndereçoCad, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCidadeCad, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(txtCidadeCad, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEstadoCad, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCadastrarEndereço, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        lblFiltro4.setFont(new java.awt.Font("Arial", 1, 32)); // NOI18N
        lblFiltro4.setForeground(new java.awt.Color(51, 51, 51));
        lblFiltro4.setText("Olá, seja bem vindo");

        lblEmailUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblEmailUsuario.setForeground(new java.awt.Color(102, 102, 102));
        lblEmailUsuario.setText("email");

        lblSexoUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSexoUsuario.setForeground(new java.awt.Color(102, 102, 102));
        lblSexoUsuario.setText("sexo");

        lblDataUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDataUsuario.setForeground(new java.awt.Color(102, 102, 102));
        lblDataUsuario.setText("data de nascimento");

        lblCpfUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCpfUsuario.setForeground(new java.awt.Color(102, 102, 102));
        lblCpfUsuario.setText("CPF");

        lblNomeUsuario.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblNomeUsuario.setForeground(new java.awt.Color(255, 86, 86));
        lblNomeUsuario.setText("nomeUsuario");

        btnVerPedidos.setBackground(new java.awt.Color(255, 86, 86));
        btnVerPedidos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnVerPedidos.setForeground(new java.awt.Color(255, 255, 255));
        btnVerPedidos.setText("VER MEUS PEDIDOS");
        btnVerPedidos.setBorder(null);
        btnVerPedidos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerPedidosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCpfUsuario)
                            .addComponent(lblDataUsuario)
                            .addComponent(lblSexoUsuario)
                            .addComponent(lblEmailUsuario)
                            .addComponent(lblFiltro4, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(btnVerPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblFiltro4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNomeUsuario)
                .addGap(18, 18, 18)
                .addComponent(lblEmailUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDataUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblSexoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCpfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                .addComponent(btnVerPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        lblFiltro1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblFiltro1.setForeground(new java.awt.Color(51, 51, 51));
        lblFiltro1.setText("Dados pessoais");

        lblFiltro2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblFiltro2.setForeground(new java.awt.Color(51, 51, 51));
        lblFiltro2.setText("Cadastrar Endereço");

        lblFiltro3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblFiltro3.setForeground(new java.awt.Color(51, 51, 51));
        lblFiltro3.setText("Cadastrar Telefone");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFiltro1)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFiltro3)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFiltro2))))
                .addGap(29, 29, 29))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFiltro1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFiltro3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblFiltro2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        multTelas.addTab("tab3", jPanel3);

        jPanel7.setBackground(new java.awt.Color(245, 245, 245));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        lblFiltro5.setFont(new java.awt.Font("Arial", 1, 32)); // NOI18N
        lblFiltro5.setForeground(new java.awt.Color(51, 51, 51));
        lblFiltro5.setText("Seus pedidos");

        tblPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Número pedido", "Data do pedido", "Preço", "Transportadora", "Método de pagamento", "Endereço"
            }
        ));
        jScrollPane1.setViewportView(tblPedidos);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFiltro5, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblFiltro5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        multTelas.addTab("tab4", jPanel7);

        jPanel9.setBackground(new java.awt.Color(245, 245, 245));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        tblProdutosCarrinho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome produto", "Preço", "Quantidade"
            }
        ));
        jScrollPane3.setViewportView(tblProdutosCarrinho);

        titleLogin4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        titleLogin4.setForeground(new java.awt.Color(51, 51, 51));
        titleLogin4.setText("PRODUTOS");

        lblEstadoCad1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblEstadoCad1.setForeground(new java.awt.Color(102, 102, 102));
        lblEstadoCad1.setText("Endereço");

        cbMetodoPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Crédito", "Boleto", "PIX" }));

        lblEstadoCad2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblEstadoCad2.setForeground(new java.awt.Color(102, 102, 102));
        lblEstadoCad2.setText("Método de pagamento");

        lblEstadoCad3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblEstadoCad3.setForeground(new java.awt.Color(102, 102, 102));
        lblEstadoCad3.setText("Transportadora");

        btnLimparCarrinho.setBackground(new java.awt.Color(255, 86, 86));
        btnLimparCarrinho.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnLimparCarrinho.setForeground(new java.awt.Color(255, 255, 255));
        btnLimparCarrinho.setText("LIMPAR CARRINHO");
        btnLimparCarrinho.setBorder(null);
        btnLimparCarrinho.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimparCarrinho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparCarrinhoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnLimparCarrinho, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEstadoCad2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(titleLogin4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbMetodoPagamento, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEstadoCad1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbEndereco, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEstadoCad3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbTransportadora, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(408, 408, 408))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLogin4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblEstadoCad2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbMetodoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEstadoCad1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEstadoCad3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTransportadora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLimparCarrinho, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblFiltro7.setFont(new java.awt.Font("Arial", 1, 32)); // NOI18N
        lblFiltro7.setForeground(new java.awt.Color(51, 51, 51));
        lblFiltro7.setText("Preço total");

        btnFazerPedido.setBackground(new java.awt.Color(255, 86, 86));
        btnFazerPedido.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnFazerPedido.setForeground(new java.awt.Color(255, 255, 255));
        btnFazerPedido.setText("FAZER PEDIDO");
        btnFazerPedido.setBorder(null);
        btnFazerPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFazerPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFazerPedidoActionPerformed(evt);
            }
        });

        lblFiltro8.setFont(new java.awt.Font("Arial", 1, 32)); // NOI18N
        lblFiltro8.setForeground(new java.awt.Color(51, 51, 51));
        lblFiltro8.setText("Carrinho");

        lblValorPedido.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblValorPedido.setForeground(new java.awt.Color(51, 51, 51));
        lblValorPedido.setText("R$ 00,00");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFiltro8)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFiltro7)
                                    .addComponent(lblValorPedido)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnFazerPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFiltro8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lblFiltro7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblValorPedido)
                        .addGap(406, 406, 406)
                        .addComponent(btnFazerPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        multTelas.addTab("tab5", jPanel9);

        jPanel13.setBackground(new java.awt.Color(245, 245, 245));

        lblNomeProduto.setFont(new java.awt.Font("Arial", 1, 32)); // NOI18N
        lblNomeProduto.setForeground(new java.awt.Color(51, 51, 51));
        lblNomeProduto.setText("nomeProduto");

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        lblGarantia.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblGarantia.setForeground(new java.awt.Color(51, 51, 51));
        lblGarantia.setText("GARANTIA");

        lblDescricao.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblDescricao.setForeground(new java.awt.Color(51, 51, 51));
        lblDescricao.setText("DESCRIÇÃO");

        lblEstoque.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblEstoque.setForeground(new java.awt.Color(51, 51, 51));
        lblEstoque.setText("UNIDADES DISPONÍVEIS");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEstoque)
                    .addComponent(lblDescricao)
                    .addComponent(lblGarantia))
                .addContainerGap(159, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblGarantia)
                .addGap(34, 34, 34)
                .addComponent(lblDescricao)
                .addGap(27, 27, 27)
                .addComponent(lblEstoque)
                .addContainerGap(235, Short.MAX_VALUE))
        );

        lblNomeMarca.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        lblNomeMarca.setForeground(new java.awt.Color(51, 51, 51));
        lblNomeMarca.setText("nomeMarca");

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        btnAdicionarCarrinho.setBackground(new java.awt.Color(255, 86, 86));
        btnAdicionarCarrinho.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnAdicionarCarrinho.setForeground(new java.awt.Color(255, 255, 255));
        btnAdicionarCarrinho.setText("ADICIONAR AO CARRINHO");
        btnAdicionarCarrinho.setBorder(null);
        btnAdicionarCarrinho.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionarCarrinho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarCarrinhoActionPerformed(evt);
            }
        });

        lblPreço.setFont(new java.awt.Font("Arial", 1, 32)); // NOI18N
        lblPreço.setForeground(new java.awt.Color(51, 51, 51));
        lblPreço.setText("R$ 00,00");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(lblPreço)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnAdicionarCarrinho, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPreço)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdicionarCarrinho, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNomeMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomeProduto)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(lblNomeMarca)
                .addGap(42, 42, 42)
                .addComponent(lblNomeProduto)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        multTelas.addTab("tab6", jPanel13);

        telaMenu.add(multTelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 820, 650));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(telaMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(telaMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        close();
    }//GEN-LAST:event_jButton1MouseClicked

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        logar();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void lblLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLoginMouseClicked
        // TODO add your handling code here:
        if (token == false) {
            multTelas.setSelectedIndex(1);
        }
        else {
            multTelas.setSelectedIndex(2);
        }
    }//GEN-LAST:event_lblLoginMouseClicked

    private void btnCadUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadUsuarioActionPerformed
        // TODO add your handling code here:
        cadastrar_usuario();
    }//GEN-LAST:event_btnCadUsuarioActionPerformed

    private void btnCadastrarEndereçoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEndereçoActionPerformed
        // TODO add your handling code here:
        cadastrar_endereço();
    }//GEN-LAST:event_btnCadastrarEndereçoActionPerformed

    private void btnCadastrarTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarTelefoneActionPerformed
        // TODO add your handling code here:
        cadastrar_telefone();
    }//GEN-LAST:event_btnCadastrarTelefoneActionPerformed

    private void btnCarrinhhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarrinhhoActionPerformed
        // TODO add your handling code here:
        if (token == true) {
            multTelas.setSelectedIndex(4);
            
            DefaultTableModel model = (DefaultTableModel)tblProdutosCarrinho.getModel();
            model.setRowCount(0);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

            centerRenderer.setHorizontalAlignment( JLabel.CENTER );

            TableColumn c1 = tblProdutosCarrinho.getColumnModel().getColumn(0);
            TableColumn c2 = tblProdutosCarrinho.getColumnModel().getColumn(1);
            TableColumn c3 = tblProdutosCarrinho.getColumnModel().getColumn(2);

            c1.setPreferredWidth(150);
            c2.setPreferredWidth(75);
            c3.setPreferredWidth(75);

            tblProdutosCarrinho.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
            tblProdutosCarrinho.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
            tblProdutosCarrinho.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );

            tblProdutosCarrinho.getTableHeader().setUI(null);
            
            String sql= "select id_produto, quantidade, preço from produto_carrinho where id_usuario = " + id + ";";
            
            try {
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();

                while(rs.next()) {
                    model.addRow(  
                        new Object[] { rs.getString(1), "Quantidade: " + rs.getString(2), "R$ " + rs.getString(3)}
                    );
                    preçoTotal = Float.parseFloat(rs.getString(3)) + preçoTotal;
                };
                lblValorPedido.setText("R$ " + preçoTotal);
                
                pegar_endereço();
                pegar_transportadora();
                is_disable_pedido();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btnCarrinhhoActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String marca = "";
        String categoria = "";
        
        int contador_marca = 0;
        int contador_marca2 = 0;
        int contador_categoria = 0;
        int contador_categoria2 = 0;
        
        for (Enumeration<AbstractButton> buttons = btnGroupMarca.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                marca = button.getText();
                contador_marca = contador_marca2;
            }
            contador_marca2++;
        }
        
        for (Enumeration<AbstractButton> buttons = btnGroupCategoria.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                categoria = button.getText();
                contador_categoria = contador_categoria2;
            }
            contador_categoria2++;
        }
        
        btnGroupMarca.clearSelection();
        btnGroupCategoria.clearSelection();
        
        System.out.println(contador_marca + " " + contador_categoria);
        
        contador_marca++;
        contador_categoria++;
        
        if (marca == "" && categoria == "") {
            add_table("select * from produto");
        }
        else if (marca == "" && categoria != "") {            
            add_table("select * from produto inner join categoria on produto.id_categoria = " + contador_categoria);
        }
        else if (marca != "" && categoria == "") {
            add_table("select * from produto inner join marca on produto.id_marca = " + contador_marca);
        }
        else {
            add_table(
                "select * from produto"
                + " inner join marca on produto.id_marca = " + contador_marca
                + " inner join categoria on produto.id_categoria = " + contador_categoria
            );
        }
        System.out.println(marca + " " + categoria);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPesquisaKeyReleased
        // TODO add your handling code here:
        String sql= "select nome, preço from produto where nome like ?";
        try {
            DefaultTableModel model = template_table();
            
            pst = conexao.prepareStatement(sql);
            pst.setString(1, btnPesquisa.getText() + "%");
            rs=pst.executeQuery();
            
            while(rs.next()) {model.addRow(new Object[] { rs.getString(1), "R$ " + rs.getString(2), "COMPRAR" });}
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnPesquisaKeyReleased

    private void lblTituloAppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTituloAppMouseClicked
        // TODO add your handling code here:
        multTelas.setSelectedIndex(0);
    }//GEN-LAST:event_lblTituloAppMouseClicked

    private void btnVerPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerPedidosActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)tblPedidos.getModel();
        model.setRowCount(0);

        String sql= "select numero_pedido, data_pedido, preço, id_transportadora, metodo_pagamento, id_endereço from pedido where id_usuario = " + id + ";";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while(rs.next()) {model.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});};
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        multTelas.setSelectedIndex(3);
    }//GEN-LAST:event_btnVerPedidosActionPerformed

    private void btnAdicionarCarrinhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarCarrinhoActionPerformed
        // TODO add your handling code here:
        String sql = "insert into produto_carrinho(id_usuario, id_produto, quantidade, preço) values(?,?,?,?)";
        
        String preço = lblPreço.getText().substring(3);

        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1,id);
            pst.setString(2,lblNomeProduto.getText());
            pst.setString(3,"1");
            pst.setString(4,preço);

            int adicionado = pst.executeUpdate();

            if(adicionado > 0){
                JOptionPane.showMessageDialog(null,"Produto adicionado ao carrinho com sucesso");
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        
        multTelas.setSelectedIndex(0);
    }//GEN-LAST:event_btnAdicionarCarrinhoActionPerformed

    private void btnFazerPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFazerPedidoActionPerformed
        // TODO add your handling code here:
        gerenciar_estoque();
        
        String sql = "insert into pedido(data_pedido, preço, id_transportadora, metodo_pagamento, id_endereço, id_usuario) values(?,?,?,?,?,?)";
        
        try {
            Calendar cal = Calendar.getInstance(); 
            
            java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
            
            pst = conexao.prepareStatement(sql);
            pst.setTimestamp(1, timestamp);
            pst.setFloat(2, preçoTotal);
            pst.setString(3, (String) cbTransportadora.getSelectedItem());
            pst.setString(4, (String) cbMetodoPagamento.getSelectedItem());
            pst.setString(5, (String) cbEndereco.getSelectedItem());
            pst.setString(6, id);
            
            int adicionado = pst.executeUpdate();
            
            if(adicionado > 0){
                preçoTotal = 0;
                limpar_carrinho("Pedido feito com sucesso");
                multTelas.setSelectedIndex(0);
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_btnFazerPedidoActionPerformed

    private void btnLimparCarrinhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparCarrinhoActionPerformed
        // TODO add your handling code here:
        limpar_carrinho("Carrinho limpo");
    }//GEN-LAST:event_btnLimparCarrinhoActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaMenu(0, "", "", "", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarCarrinho;
    private javax.swing.JButton btnCadUsuario;
    private javax.swing.JButton btnCadastrarEndereço;
    private javax.swing.JButton btnCadastrarTelefone;
    private javax.swing.JButton btnCarrinhho;
    private javax.swing.JButton btnFazerPedido;
    private javax.swing.ButtonGroup btnGroupCategoria;
    private javax.swing.ButtonGroup btnGroupMarca;
    private javax.swing.JButton btnLimparCarrinho;
    private javax.swing.JButton btnLogin;
    private javax.swing.JTextField btnPesquisa;
    private javax.swing.JButton btnVerPedidos;
    private javax.swing.JComboBox<String> cbEndereco;
    private javax.swing.JComboBox<String> cbEstado;
    private javax.swing.JComboBox<String> cbMetodoPagamento;
    private javax.swing.JComboBox<String> cbSexCad;
    private javax.swing.JComboBox<String> cbTransportadora;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JPanel header;
    private javax.swing.JPanel headerOptions;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JRadioButton jRadioButton13;
    private javax.swing.JRadioButton jRadioButton14;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblCidadeCad;
    private javax.swing.JLabel lblCpfCad;
    private javax.swing.JLabel lblCpfUsuario;
    private javax.swing.JLabel lblDataUsuario;
    private javax.swing.JLabel lblDateCad;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblEmailCad;
    private javax.swing.JLabel lblEmailUsuario;
    private javax.swing.JLabel lblEndereçoCad;
    private javax.swing.JLabel lblEstadoCad;
    private javax.swing.JLabel lblEstadoCad1;
    private javax.swing.JLabel lblEstadoCad2;
    private javax.swing.JLabel lblEstadoCad3;
    private javax.swing.JLabel lblEstoque;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblFiltro1;
    private javax.swing.JLabel lblFiltro2;
    private javax.swing.JLabel lblFiltro3;
    private javax.swing.JLabel lblFiltro4;
    private javax.swing.JLabel lblFiltro5;
    private javax.swing.JLabel lblFiltro7;
    private javax.swing.JLabel lblFiltro8;
    private javax.swing.JLabel lblGarantia;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblLogin1;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblNomeMarca;
    private javax.swing.JLabel lblNomeProduto;
    private javax.swing.JLabel lblNomeUsuario;
    private javax.swing.JLabel lblPreço;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblSenhaCad;
    private javax.swing.JLabel lblSexoUsuario;
    private javax.swing.JLabel lblTelefoneCad;
    private javax.swing.JLabel lblTituloApp;
    private javax.swing.JLabel lblUsuarioCad;
    private javax.swing.JLabel lblValorPedido;
    private javax.swing.JTabbedPane multTelas;
    private javax.swing.JPanel panelCadastro;
    private javax.swing.JPanel panelFiltros;
    private javax.swing.JPanel panelLogin;
    private javax.swing.JPanel sidebarCategorias;
    private javax.swing.JTable tblPedidos;
    private javax.swing.JTable tblProd;
    private javax.swing.JTable tblProdutosCarrinho;
    private javax.swing.JPanel telaMenu;
    private javax.swing.JLabel titleLogin1;
    private javax.swing.JLabel titleLogin2;
    private javax.swing.JLabel titleLogin4;
    private javax.swing.JTextField txtCidadeCad;
    private javax.swing.JTextField txtCpfCad;
    private javax.swing.JTextField txtDateCad;
    private javax.swing.JTextField txtEmailCad;
    private javax.swing.JTextField txtEndereçoCad;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JPasswordField txtSenhaCad;
    private javax.swing.JTextField txtTelefoneCad;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txtUsuarioCad;
    // End of variables declaration//GEN-END:variables
}
