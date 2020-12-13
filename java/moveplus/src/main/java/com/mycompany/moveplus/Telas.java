package com.mycompany.moveplus;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.plaf.basic.BasicScrollBarUI;

import javax.swing.table.DefaultTableModel;
import keeptoo.Drag;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

public class Telas extends javax.swing.JFrame {

    Monitoracao mp = new Monitoracao();
    SystemInfo si = new SystemInfo();    //Criando uma nova classe de infos do Sistema
    OperatingSystem os = si.getOperatingSystem(); //pegando infos do OS do sistema
    HardwareAbstractionLayer hal = si.getHardware(); //Infos de Hardware do sistema    
    CentralProcessor cpu = hal.getProcessor();
    List<HWDiskStore> dadosDisco = hal.getDiskStores(); //Uma lista de dados do disco do meu Hardware
    GlobalMemory memory = si.getHardware().getMemory();

    JInternalFrame grafCpu = new Graficos("CPU");
    JInternalFrame grafRam = new Graficos("RAM");

    Boolean operante = true;

    List<String[]> lista = new ArrayList<>();

    CardLayout cardLayout;

    public Telas() {
        initComponents();
        gerarGrafico(grafCpu, graficoCpu);
        gerarGrafico(grafRam, graficoMemoria);
        cardLayout = (CardLayout) (pnlCards.getLayout());
        txtSenha1.setEchoChar('\u0000');
        pnlBtns.setVisible(false);
        btnCadastrar.setVisible(false);
    }

    void executarProcessos() {
        Timer timer = new Timer(300, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alterarTabela();
            }
        });
        timer.start();
    }

    void alterarTabela() {
        String[] nomesColunas = {"ID", "Nome", "RAM", "CPU"};
        float ramMemory = 0;             //RAM, no Linux RE
        double cpuUsage = 0; //CPU    
        int processID = 0;                           //ID do processo
        String processName = "";                           //Nome do processo     
        DecimalFormat formatador = new DecimalFormat("0.00");

        for (OSProcess process : os.getProcesses(10, OperatingSystem.ProcessSort.CPU)) {
            ramMemory = ((float) process.getResidentSetSize() / 1024) / 1000;             //RAM, no Linux RE
            cpuUsage = process.getProcessCpuLoadBetweenTicks(process) * 100; //CPU    
            processID = process.getProcessID();                           //ID do processo
            processName = process.getName();                           //Nome do processo    
            String ramFormat = formatador.format(ramMemory);

            String ramStr = String.format("%s Mb", ramFormat);
            String idStr = "" + processID + ' ';
            String cpuStr = String.format("%.2f%s", cpuUsage, "%");

            lista.add(new String[]{idStr, processName, ramStr, cpuStr});
        }
        DefaultTableModel model = new DefaultTableModel(
                lista.toArray(new String[lista.size()][]), nomesColunas
        );
        lista.clear();

        jScrollPane1.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(128, 223, 255);
                jScrollPane1.getVerticalScrollBar().setBackground(Color.WHITE);
                jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(240, 240, 240), 1, true));
            }
        });
        jTable1.setModel(model);
        jTable1.getTableHeader().setDefaultRenderer(new PintarCabecalho());
    }
//----

    public void Iniciar() throws Exception {
        String erro = "Erro. Usuário e/ou senha incorretos.";
        String login = txtLogin1.getText();
        String password = txtSenha1.getText();

        pnlBtns.setVisible(true);
        btnCadastrar.setVisible(true);

        if (login.equals("root") && password.equals("root")) {
            pnlBtns.setVisible(true);
            btnCadastrar.setVisible(true);
            switchPanels(jpnlD);
            lblprocessador.setText(mp.catchCpu());
            lblSO.setText(mp.catchSO());
            lblMemoria.setText(mp.catchRam() + "Gb");
            lblDisco.setText(mp.catchDiskSize() + "Gb");
        } else {
            lblResult.setText("Login Inválido!");
        }
    }
//----

    public void gerarGrafico(JInternalFrame tipoGrafico, JPanel grafico) {
        tipoGrafico.setBorder(null);
        BasicInternalFrameUI bFrame = (BasicInternalFrameUI) tipoGrafico.getUI();
        bFrame.setNorthPane(null);
        tipoGrafico.setSize(100, 100);
        tipoGrafico.setVisible(true);

        grafico.add(tipoGrafico);
        grafico.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        pnlMenu = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        pnlCards = new javax.swing.JPanel();
        hardware = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblMemoria = new javax.swing.JLabel();
        lblprocessador = new javax.swing.JLabel();
        lblDisco = new javax.swing.JLabel();
        lblSO = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        memoria = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        btnClose = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        graficoMemoria = new javax.swing.JPanel();
        processos = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        pncpu = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        graficoCpu = new javax.swing.JPanel();
        manutencao = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        btnMudarStatus = new keeptoo.KButton();
        lblStatus = new javax.swing.JLabel();
        btnClose1 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        pnlBtns = new javax.swing.JPanel();
        btnProcessos = new keeptoo.KButton();
        btnHardware = new keeptoo.KButton();
        btnCPU = new keeptoo.KButton();
        btnMemoria = new keeptoo.KButton();
        jLabel25 = new javax.swing.JLabel();
        btnManutencao = new keeptoo.KButton();
        pnlLogin = new keeptoo.KGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtLogin1 = new javax.swing.JTextField();
        label = new javax.swing.JLabel();
        lblResult = new javax.swing.JLabel();
        btnEntrar = new keeptoo.KButton();
        txtSenha1 = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jpnlD = new com.mycompany.moveplus.PainelImagemFundo();
        jLabel21 = new javax.swing.JLabel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        txtID = new javax.swing.JTextField();
        btnCadastrar = new keeptoo.KButton();
        lblResultID = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(570, 410));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jDesktopPane1.setMaximumSize(new java.awt.Dimension(570, 410));
        jDesktopPane1.setMinimumSize(new java.awt.Dimension(570, 410));
        jDesktopPane1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jDesktopPane1MouseDragged(evt);
            }
        });
        jDesktopPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jDesktopPane1MousePressed(evt);
            }
        });
        jDesktopPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLayeredPane1MouseDragged(evt);
            }
        });
        jLayeredPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLayeredPane1MousePressed(evt);
            }
        });

        jSplitPane1.setDividerLocation(160);
        jSplitPane1.setDividerSize(0);
        jSplitPane1.setMaximumSize(new java.awt.Dimension(570, 410));
        jSplitPane1.setMinimumSize(new java.awt.Dimension(570, 410));
        jSplitPane1.setPreferredSize(new java.awt.Dimension(570, 410));
        jSplitPane1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jSplitPane1MouseDragged(evt);
            }
        });
        jSplitPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jSplitPane1MousePressed(evt);
            }
        });

        pnlCards.setBackground(new java.awt.Color(255, 255, 255));
        pnlCards.setLayout(new java.awt.CardLayout());

        hardware.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Insaniburger", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Informações do sistema");

        jLabel11.setFont(new java.awt.Font("Insaniburger", 0, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 204, 204));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("processador");

        jLabel10.setFont(new java.awt.Font("Insaniburger", 0, 17)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 204, 204));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("disco");

        jLabel12.setFont(new java.awt.Font("Insaniburger", 0, 17)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 204, 204));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Memoria ram");

        jLabel13.setFont(new java.awt.Font("Insaniburger", 0, 17)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 204, 204));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("sistema ");
        jLabel13.setToolTipText("");

        jLabel20.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("X");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        jLabel20.setName("btnClose"); // NOI18N
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                close(evt);
            }
        });

        lblMemoria.setFont(new java.awt.Font("Insaniburger", 0, 17)); // NOI18N
        lblMemoria.setForeground(new java.awt.Color(102, 102, 102));
        lblMemoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMemoria.setText("(erro)");

        lblprocessador.setFont(new java.awt.Font("Insaniburger", 0, 17)); // NOI18N
        lblprocessador.setForeground(new java.awt.Color(102, 102, 102));
        lblprocessador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblprocessador.setText("(erro)");

        lblDisco.setFont(new java.awt.Font("Insaniburger", 0, 17)); // NOI18N
        lblDisco.setForeground(new java.awt.Color(102, 102, 102));
        lblDisco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDisco.setText("(erro)");

        lblSO.setFont(new java.awt.Font("Insaniburger", 0, 17)); // NOI18N
        lblSO.setForeground(new java.awt.Color(102, 102, 102));
        lblSO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSO.setText("(erro)");

        jLabel24.setFont(new java.awt.Font("Insaniburger", 0, 17)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 204, 204));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("operacional");
        jLabel24.setToolTipText("");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hardware.png"))); // NOI18N

        javax.swing.GroupLayout hardwareLayout = new javax.swing.GroupLayout(hardware);
        hardware.setLayout(hardwareLayout);
        hardwareLayout.setHorizontalGroup(
            hardwareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(hardwareLayout.createSequentialGroup()
                .addGroup(hardwareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(hardwareLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(hardwareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(hardwareLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(hardwareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(hardwareLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblprocessador, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12))))
                .addGap(10, 10, 10))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hardwareLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(hardwareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hardwareLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hardwareLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hardwareLayout.createSequentialGroup()
                        .addComponent(lblMemoria, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hardwareLayout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(lblDisco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        hardwareLayout.setVerticalGroup(
            hardwareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hardwareLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel20)
                .addGap(12, 12, 12)
                .addComponent(jLabel9)
                .addGap(64, 64, 64)
                .addGroup(hardwareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblprocessador))
                .addGap(37, 37, 37)
                .addGroup(hardwareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblMemoria))
                .addGap(35, 35, 35)
                .addGroup(hardwareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblDisco))
                .addGap(34, 34, 34)
                .addGroup(hardwareLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(hardwareLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel24))
                    .addComponent(lblSO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        pnlCards.add(hardware, "hardware");

        memoria.setBackground(new java.awt.Color(255, 255, 255));
        memoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                close(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Insaniburger", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("uso de memória");

        btnClose.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        btnClose.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnClose.setText("X");
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        btnClose.setName("btnClose"); // NOI18N
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                close(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hardware.png"))); // NOI18N

        graficoMemoria.setMaximumSize(new java.awt.Dimension(300, 300));
        graficoMemoria.setName("graficoMemoria"); // NOI18N
        graficoMemoria.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout memoriaLayout = new javax.swing.GroupLayout(memoria);
        memoria.setLayout(memoriaLayout);
        memoriaLayout.setHorizontalGroup(
            memoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, memoriaLayout.createSequentialGroup()
                .addGap(0, 358, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, memoriaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(memoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, memoriaLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, memoriaLayout.createSequentialGroup()
                        .addComponent(graficoMemoria, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );
        memoriaLayout.setVerticalGroup(
            memoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, memoriaLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(btnClose)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addGap(27, 27, 27)
                .addComponent(graficoMemoria, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addContainerGap())
        );

        pnlCards.add(memoria, "memoria");

        processos.setBackground(new java.awt.Color(255, 255, 255));
        processos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                close(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Insaniburger", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("processos");

        jLabel18.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("X");
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        jLabel18.setName("btnClose"); // NOI18N
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                close(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hardware.png"))); // NOI18N

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        )
        {public boolean isCellEditable(int rowIndex, int columnIndex) {return false;}}
    );
    jTable1.setName("jTable1"); // NOI18N
    jTable1.setRowHeight(25);
    jTable1.setSelectionBackground(new java.awt.Color(177, 255, 255));
    jTable1.setSelectionForeground(new java.awt.Color(51, 51, 51));
    jTable1.getTableHeader().setResizingAllowed(false);
    jTable1.getTableHeader().setReorderingAllowed(false);
    jScrollPane1.setViewportView(jTable1);

    javax.swing.GroupLayout processosLayout = new javax.swing.GroupLayout(processos);
    processos.setLayout(processosLayout);
    processosLayout.setHorizontalGroup(
        processosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, processosLayout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(processosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, processosLayout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(23, 23, 23))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, processosLayout.createSequentialGroup()
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(14, 14, 14))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, processosLayout.createSequentialGroup()
                    .addComponent(jLabel6)
                    .addGap(10, 10, 10))))
    );
    processosLayout.setVerticalGroup(
        processosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(processosLayout.createSequentialGroup()
            .addGap(8, 8, 8)
            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel15)
            .addGap(31, 31, 31)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel6)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pnlCards.add(processos, "processos");

    pncpu.setBackground(new java.awt.Color(255, 255, 255));
    pncpu.setMaximumSize(new java.awt.Dimension(300, 300));

    jLabel16.setFont(new java.awt.Font("Insaniburger", 0, 24)); // NOI18N
    jLabel16.setForeground(new java.awt.Color(51, 51, 51));
    jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel16.setText("uso de cpu");

    jLabel19.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
    jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel19.setText("X");
    jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
    jLabel19.setName("btnClose"); // NOI18N
    jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            close(evt);
        }
    });

    jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hardware.png"))); // NOI18N

    graficoCpu.setMaximumSize(new java.awt.Dimension(300, 300));
    graficoCpu.setName("graficoArrombado"); // NOI18N
    graficoCpu.setLayout(new java.awt.BorderLayout());

    javax.swing.GroupLayout pncpuLayout = new javax.swing.GroupLayout(pncpu);
    pncpu.setLayout(pncpuLayout);
    pncpuLayout.setHorizontalGroup(
        pncpuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pncpuLayout.createSequentialGroup()
            .addContainerGap(29, Short.MAX_VALUE)
            .addGroup(pncpuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pncpuLayout.createSequentialGroup()
                    .addComponent(jLabel19)
                    .addGap(14, 14, 14))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pncpuLayout.createSequentialGroup()
                    .addComponent(graficoCpu, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(21, 21, 21))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pncpuLayout.createSequentialGroup()
                    .addComponent(jLabel5)
                    .addGap(10, 10, 10))))
    );
    pncpuLayout.setVerticalGroup(
        pncpuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pncpuLayout.createSequentialGroup()
            .addGap(8, 8, 8)
            .addComponent(jLabel19)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel16)
            .addGap(27, 27, 27)
            .addComponent(graficoCpu, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
            .addGap(18, 18, 18)
            .addComponent(jLabel5)
            .addContainerGap())
    );

    pnlCards.add(pncpu, "cpu");

    manutencao.setBackground(new java.awt.Color(255, 255, 255));
    manutencao.setName("manutencao"); // NOI18N

    jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hardware.png"))); // NOI18N

    jLabel23.setFont(new java.awt.Font("Insaniburger", 0, 30)); // NOI18N
    jLabel23.setForeground(new java.awt.Color(51, 51, 51));
    jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel23.setText("manutenção");

    btnMudarStatus.setFont(new java.awt.Font("Waree", 1, 15)); // NOI18N
    btnMudarStatus.setkEndColor(new java.awt.Color(255, 153, 153));
    btnMudarStatus.setkHoverEndColor(new java.awt.Color(153, 255, 255));
    btnMudarStatus.setkHoverForeGround(new java.awt.Color(255, 255, 255));
    btnMudarStatus.setkHoverStartColor(new java.awt.Color(51, 204, 255));
    btnMudarStatus.setkPressedColor(new java.awt.Color(153, 255, 204));
    btnMudarStatus.setkStartColor(new java.awt.Color(255, 153, 153));
    btnMudarStatus.setLabel("SUSPENDER");
    btnMudarStatus.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnMudarStatusActionPerformed(evt);
        }
    });

    lblStatus.setFont(new java.awt.Font("Waree", 0, 16)); // NOI18N
    lblStatus.setForeground(new java.awt.Color(28, 254, 186));
    lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    lblStatus.setText("OPERANTE");

    btnClose1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
    btnClose1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    btnClose1.setText("X");
    btnClose1.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
    btnClose1.setName("btnClose"); // NOI18N
    btnClose1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            btnClose1close(evt);
        }
    });

    jLabel26.setFont(new java.awt.Font("Waree", 0, 16)); // NOI18N
    jLabel26.setText("STATUS DO TERMINAL:");

    javax.swing.GroupLayout manutencaoLayout = new javax.swing.GroupLayout(manutencao);
    manutencao.setLayout(manutencaoLayout);
    manutencaoLayout.setHorizontalGroup(
        manutencaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(manutencaoLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(manutencaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(manutencaoLayout.createSequentialGroup()
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, manutencaoLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(manutencaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, manutencaoLayout.createSequentialGroup()
                            .addComponent(jLabel22)
                            .addGap(10, 10, 10))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, manutencaoLayout.createSequentialGroup()
                            .addComponent(btnClose1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(14, 14, 14))))))
        .addGroup(manutencaoLayout.createSequentialGroup()
            .addGap(50, 50, 50)
            .addComponent(jLabel26)
            .addGap(27, 27, 27)
            .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(30, Short.MAX_VALUE))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, manutencaoLayout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMudarStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(100, 100, 100))
    );
    manutencaoLayout.setVerticalGroup(
        manutencaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, manutencaoLayout.createSequentialGroup()
            .addGap(8, 8, 8)
            .addComponent(btnClose1)
            .addGap(18, 18, 18)
            .addComponent(jLabel23)
            .addGap(72, 72, 72)
            .addGroup(manutencaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel26))
            .addGap(49, 49, 49)
            .addComponent(btnMudarStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
            .addComponent(jLabel22)
            .addContainerGap())
    );

    pnlCards.add(manutencao, "manutencao");

    jSplitPane1.setRightComponent(pnlCards);

    pnlBtns.setBackground(new java.awt.Color(51, 204, 255));
    pnlBtns.setMaximumSize(new java.awt.Dimension(570, 410));
    pnlBtns.setPreferredSize(new java.awt.Dimension(570, 410));

    btnProcessos.setText("Processos");
    btnProcessos.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
    btnProcessos.setkEndColor(new java.awt.Color(102, 255, 255));
    btnProcessos.setkHoverEndColor(new java.awt.Color(255, 255, 255));
    btnProcessos.setkHoverForeGround(new java.awt.Color(51, 51, 51));
    btnProcessos.setkHoverStartColor(new java.awt.Color(255, 255, 255));
    btnProcessos.setkPressedColor(new java.awt.Color(0, 204, 204));
    btnProcessos.setkSelectedColor(new java.awt.Color(255, 255, 255));
    btnProcessos.setkStartColor(new java.awt.Color(51, 204, 255));
    btnProcessos.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnProcessosActionPerformed(evt);
        }
    });

    btnHardware.setText("Hardware");
    btnHardware.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
    btnHardware.setkEndColor(new java.awt.Color(102, 255, 255));
    btnHardware.setkHoverEndColor(new java.awt.Color(255, 255, 255));
    btnHardware.setkHoverForeGround(new java.awt.Color(51, 51, 51));
    btnHardware.setkHoverStartColor(new java.awt.Color(255, 255, 255));
    btnHardware.setkPressedColor(new java.awt.Color(102, 204, 255));
    btnHardware.setkSelectedColor(new java.awt.Color(255, 255, 255));
    btnHardware.setkStartColor(new java.awt.Color(51, 204, 255));
    btnHardware.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnHardwareActionPerformed(evt);
        }
    });

    btnCPU.setText("CPU");
    btnCPU.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
    btnCPU.setkEndColor(new java.awt.Color(102, 255, 255));
    btnCPU.setkHoverEndColor(new java.awt.Color(255, 255, 255));
    btnCPU.setkHoverForeGround(new java.awt.Color(51, 51, 51));
    btnCPU.setkHoverStartColor(new java.awt.Color(255, 255, 255));
    btnCPU.setkPressedColor(new java.awt.Color(0, 204, 204));
    btnCPU.setkSelectedColor(new java.awt.Color(255, 255, 255));
    btnCPU.setkStartColor(new java.awt.Color(51, 204, 255));
    btnCPU.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCPUActionPerformed(evt);
        }
    });

    btnMemoria.setText("Memória");
    btnMemoria.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
    btnMemoria.setkEndColor(new java.awt.Color(102, 255, 255));
    btnMemoria.setkHoverEndColor(new java.awt.Color(255, 255, 255));
    btnMemoria.setkHoverForeGround(new java.awt.Color(51, 51, 51));
    btnMemoria.setkHoverStartColor(new java.awt.Color(255, 255, 255));
    btnMemoria.setkPressedColor(new java.awt.Color(0, 204, 204));
    btnMemoria.setkSelectedColor(new java.awt.Color(255, 255, 255));
    btnMemoria.setkStartColor(new java.awt.Color(51, 204, 255));
    btnMemoria.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnMemoriaActionPerformed(evt);
        }
    });

    jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logopqn.png"))); // NOI18N

    btnManutencao.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
    btnManutencao.setkEndColor(new java.awt.Color(102, 255, 255));
    btnManutencao.setkHoverEndColor(new java.awt.Color(255, 255, 255));
    btnManutencao.setkHoverForeGround(new java.awt.Color(51, 51, 51));
    btnManutencao.setkHoverStartColor(new java.awt.Color(255, 255, 255));
    btnManutencao.setkPressedColor(new java.awt.Color(0, 204, 204));
    btnManutencao.setkSelectedColor(new java.awt.Color(255, 255, 255));
    btnManutencao.setkStartColor(new java.awt.Color(51, 204, 255));
    btnManutencao.setLabel("Manutenção");
    btnManutencao.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnManutencaoActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout pnlBtnsLayout = new javax.swing.GroupLayout(pnlBtns);
    pnlBtns.setLayout(pnlBtnsLayout);
    pnlBtnsLayout.setHorizontalGroup(
        pnlBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlBtnsLayout.createSequentialGroup()
            .addGroup(pnlBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBtnsLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(pnlBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnCPU, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btnProcessos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btnHardware, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btnMemoria, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btnManutencao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnlBtnsLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addContainerGap())
    );
    pnlBtnsLayout.setVerticalGroup(
        pnlBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBtnsLayout.createSequentialGroup()
            .addGap(28, 28, 28)
            .addComponent(jLabel25)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
            .addComponent(btnHardware, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnProcessos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnCPU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnMemoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnManutencao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(27, 27, 27))
    );

    jSplitPane1.setLeftComponent(pnlBtns);

    javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
    pnlMenu.setLayout(pnlMenuLayout);
    pnlMenuLayout.setHorizontalGroup(
        pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    pnlMenuLayout.setVerticalGroup(
        pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pnlLogin.setkBorderRadius(0);
    pnlLogin.setkStartColor(new java.awt.Color(51, 255, 204));
    pnlLogin.setMinimumSize(new java.awt.Dimension(570, 140));
    pnlLogin.setPreferredSize(new java.awt.Dimension(570, 410));
    pnlLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jLabel1.setFont(new java.awt.Font("Waree", 1, 18)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel1.setText("X");
    jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
    jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            close(evt);
        }
    });
    pnlLogin.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(538, 0, 23, -1));

    jPanel2.setBackground(new java.awt.Color(255, 255, 255));
    jPanel2.setName(""); // NOI18N
    jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
        public void mouseDragged(java.awt.event.MouseEvent evt) {
            jPanel1MouseDragged(evt);
        }
    });
    jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mousePressed(java.awt.event.MouseEvent evt) {
            jPanel1MousePressed(evt);
        }
    });

    txtLogin1.setFont(new java.awt.Font("Umpush", 0, 15)); // NOI18N
    txtLogin1.setForeground(new java.awt.Color(204, 204, 204));
    txtLogin1.setText("Login");
    txtLogin1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(153, 153, 153)));
    txtLogin1.setDisabledTextColor(new java.awt.Color(51, 255, 51));
    txtLogin1.setName("a"); // NOI18N
    txtLogin1.setSelectedTextColor(new java.awt.Color(102, 255, 102));
    txtLogin1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            txtLogin1MouseClicked(evt);
        }
    });
    txtLogin1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtLogin1ActionPerformed(evt);
        }
    });

    label.setFont(new java.awt.Font("Umpush", 1, 18)); // NOI18N
    label.setForeground(new java.awt.Color(102, 102, 102));
    label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    label.setText("LOGIN");

    lblResult.setFont(new java.awt.Font("Umpush", 0, 15)); // NOI18N
    lblResult.setForeground(new java.awt.Color(255, 102, 102));
    lblResult.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

    btnEntrar.setText("Entrar");
    btnEntrar.setFont(new java.awt.Font("Umpush", 0, 15)); // NOI18N
    btnEntrar.setkEndColor(new java.awt.Color(99, 49, 227));
    btnEntrar.setkHoverEndColor(new java.awt.Color(0, 255, 255));
    btnEntrar.setkHoverForeGround(new java.awt.Color(255, 255, 255));
    btnEntrar.setkHoverStartColor(new java.awt.Color(0, 153, 204));
    btnEntrar.setkPressedColor(new java.awt.Color(65, 54, 176));
    btnEntrar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnEntrarActionPerformed(evt);
        }
    });

    txtSenha1.setFont(new java.awt.Font("Umpush", 0, 15)); // NOI18N
    txtSenha1.setForeground(new java.awt.Color(204, 204, 204));
    txtSenha1.setText("Senha");
    txtSenha1.setToolTipText("");
    txtSenha1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(153, 153, 153)));
    txtSenha1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            txtSenha1MouseClicked(evt);
        }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(lblResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addContainerGap(212, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(txtLogin1, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addComponent(txtSenha1))
            .addGap(207, 207, 207))
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGap(193, 193, 193)
            .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGap(11, 11, 11)
            .addComponent(label)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(txtLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(32, 32, 32)
            .addComponent(txtSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(36, 36, 36)
            .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
            .addComponent(lblResult, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(103, Short.MAX_VALUE))
    );

    pnlLogin.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 141, -1, -1));

    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logoMp.png"))); // NOI18N
    jLabel3.setText(" ");
    pnlLogin.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

    jpnlD.setBackground(new java.awt.Color(255, 255, 255));
    jpnlD.setImg(new javax.swing.ImageIcon(getClass().getResource("/fundo.jpg")));
    jpnlD.setMaximumSize(new java.awt.Dimension(570, 410));
    jpnlD.setMinimumSize(new java.awt.Dimension(570, 410));
    jpnlD.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
        public void mouseDragged(java.awt.event.MouseEvent evt) {
            jpnlDMouseDragged(evt);
        }
    });
    jpnlD.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mousePressed(java.awt.event.MouseEvent evt) {
            jpnlDMousePressed(evt);
        }
    });

    jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logoMp.png"))); // NOI18N

    kGradientPanel1.setkFillBackground(false);
    kGradientPanel1.setkGradientFocus(1000);
    kGradientPanel1.setkStartColor(new java.awt.Color(0, 255, 255));
    kGradientPanel1.setOpaque(false);

    txtID.setFont(new java.awt.Font("Waree", 0, 15)); // NOI18N
    txtID.setForeground(new java.awt.Color(204, 204, 204));
    txtID.setText("ID do terminal");
    txtID.setBorder(null);
    txtID.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            txtIDMouseClicked(evt);
        }
    });

    javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
    kGradientPanel1.setLayout(kGradientPanel1Layout);
    kGradientPanel1Layout.setHorizontalGroup(
        kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(txtID)
            .addContainerGap())
    );
    kGradientPanel1Layout.setVerticalGroup(
        kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(kGradientPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    btnCadastrar.setText("Cadastrar");
    btnCadastrar.setFont(new java.awt.Font("Waree", 0, 15)); // NOI18N
    btnCadastrar.setkBackGroundColor(new java.awt.Color(153, 255, 255));
    btnCadastrar.setkEndColor(new java.awt.Color(102, 255, 255));
    btnCadastrar.setkHoverEndColor(new java.awt.Color(51, 204, 255));
    btnCadastrar.setkHoverForeGround(new java.awt.Color(255, 255, 255));
    btnCadastrar.setkHoverStartColor(new java.awt.Color(102, 204, 255));
    btnCadastrar.setkPressedColor(new java.awt.Color(204, 204, 255));
    btnCadastrar.setkSelectedColor(new java.awt.Color(102, 153, 255));
    btnCadastrar.setkStartColor(new java.awt.Color(102, 204, 255));
    btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCadastrarActionPerformed(evt);
        }
    });

    lblResultID.setForeground(new java.awt.Color(255, 102, 102));
    lblResultID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

    jLabel4.setFont(new java.awt.Font("Waree", 1, 18)); // NOI18N
    jLabel4.setForeground(new java.awt.Color(255, 255, 255));
    jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel4.setText("X");
    jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
    jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jLabel4close(evt);
        }
        public void mousePressed(java.awt.event.MouseEvent evt) {
            jLabel4MousePressed(evt);
        }
    });

    javax.swing.GroupLayout jpnlDLayout = new javax.swing.GroupLayout(jpnlD);
    jpnlD.setLayout(jpnlDLayout);
    jpnlDLayout.setHorizontalGroup(
        jpnlDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlDLayout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(14, 14, 14))
        .addGroup(jpnlDLayout.createSequentialGroup()
            .addGroup(jpnlDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnlDLayout.createSequentialGroup()
                    .addGap(122, 122, 122)
                    .addGroup(jpnlDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblResultID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jpnlDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(jpnlDLayout.createSequentialGroup()
                    .addGap(201, 201, 201)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(128, Short.MAX_VALUE))
    );
    jpnlDLayout.setVerticalGroup(
        jpnlDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jpnlDLayout.createSequentialGroup()
            .addGap(8, 8, 8)
            .addComponent(jLabel4)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel21)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(lblResultID, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(37, 37, 37)
            .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(89, Short.MAX_VALUE))
    );

    jLayeredPane1.setLayer(pnlMenu, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPane1.setLayer(pnlLogin, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPane1.setLayer(jpnlD, javax.swing.JLayeredPane.DEFAULT_LAYER);

    javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
    jLayeredPane1.setLayout(jLayeredPane1Layout);
    jLayeredPane1Layout.setHorizontalGroup(
        jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jLayeredPane1Layout.createSequentialGroup()
            .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jpnlD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
    );
    jLayeredPane1Layout.setVerticalGroup(
        jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jLayeredPane1Layout.createSequentialGroup()
            .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 8, Short.MAX_VALUE))
        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jpnlD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
    );

    jDesktopPane1.add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 410));

    getContentPane().add(jDesktopPane1);

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        new Drag(pnlLogin).onPress(evt);
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        new Drag(pnlLogin).moveWindow(evt);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void txtLogin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLogin1ActionPerformed

    }//GEN-LAST:event_txtLogin1ActionPerformed

    private void txtLogin1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLogin1MouseClicked
        txtLogin1.setText("");
        txtLogin1.setForeground(Color.decode("#808080"));
    }//GEN-LAST:event_txtLogin1MouseClicked

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        try {
            Iniciar();
        } catch (Exception ex) {
            Logger.getLogger(Telas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void jDesktopPane1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDesktopPane1MousePressed

    }//GEN-LAST:event_jDesktopPane1MousePressed

    private void jLayeredPane1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLayeredPane1MouseDragged
    }//GEN-LAST:event_jLayeredPane1MouseDragged

    private void jLayeredPane1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLayeredPane1MousePressed
    }//GEN-LAST:event_jLayeredPane1MousePressed

    private void jDesktopPane1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDesktopPane1MouseDragged
        // TODO add your handling code here:

    }//GEN-LAST:event_jDesktopPane1MouseDragged

    private void jSplitPane1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSplitPane1MousePressed
        new Drag(jSplitPane1).onPress(evt);
    }//GEN-LAST:event_jSplitPane1MousePressed

    private void jSplitPane1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSplitPane1MouseDragged
        new Drag(jSplitPane1).moveWindow(evt);
    }//GEN-LAST:event_jSplitPane1MouseDragged

    private void txtSenha1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSenha1MouseClicked
        txtSenha1.setEchoChar('*');
        txtSenha1.setText("");
        txtSenha1.setForeground(Color.decode("#808080"));
    }//GEN-LAST:event_txtSenha1MouseClicked

    private void close(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close
        System.exit(0);
    }//GEN-LAST:event_close

    private void jpnlDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnlDMousePressed
        new Drag(jpnlD).onPress(evt);
    }//GEN-LAST:event_jpnlDMousePressed

    private void jpnlDMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnlDMouseDragged
        new Drag(jpnlD).moveWindow(evt);
    }//GEN-LAST:event_jpnlDMouseDragged

    private void txtIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIDMouseClicked
        txtID.setText("");
        txtID.setForeground(Color.decode("#808080"));
    }//GEN-LAST:event_txtIDMouseClicked

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        String idTerminal = txtID.getText();
        if (idTerminal.equals("1")) {
            switchPanels(pnlMenu);
        } else {
            lblResultID.setText("ID inválido! Entre em contato com a central!");
        }
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void jLabel4close(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4close
        System.exit(0);
    }//GEN-LAST:event_jLabel4close

    private void btnManutencaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManutencaoActionPerformed
        cardLayout.show(pnlCards, "manutencao");
    }//GEN-LAST:event_btnManutencaoActionPerformed

    private void btnMemoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMemoriaActionPerformed
        cardLayout.show(pnlCards, "memoria");
    }//GEN-LAST:event_btnMemoriaActionPerformed

    private void btnCPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCPUActionPerformed
        cardLayout.show(pnlCards, "cpu");
    }//GEN-LAST:event_btnCPUActionPerformed

    private void btnHardwareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHardwareActionPerformed
        cardLayout.show(pnlCards, "hardware");
    }//GEN-LAST:event_btnHardwareActionPerformed

    private void btnProcessosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessosActionPerformed
        cardLayout.show(pnlCards, "processos");
        alterarTabela();
        executarProcessos();
    }//GEN-LAST:event_btnProcessosActionPerformed

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed

    }//GEN-LAST:event_jLabel4MousePressed

    private void btnClose1close(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClose1close
        System.exit(0);
    }//GEN-LAST:event_btnClose1close

    private void btnMudarStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMudarStatusActionPerformed
        lblStatus.setText("INOPERANTE");
        if (operante) {
            lblStatus.setText("INOPERANTE");
            lblStatus.setForeground(Color.decode("#FF9999"));
            btnMudarStatus.setkEndColor(Color.decode("#1CFEBA"));
            btnMudarStatus.setkStartColor(Color.decode("#1CFEBA"));
            btnMudarStatus.setText("Ativar");
            operante = false;
        } else {
            lblStatus.setText("OPERANTE");
            lblStatus.setForeground(Color.decode("#1CFEBA"));
            btnMudarStatus.setkEndColor(Color.decode("#FF9999"));
            btnMudarStatus.setkStartColor(Color.decode("#FF9999"));
            btnMudarStatus.setText("Suspender");
            operante = true;
        }
    }//GEN-LAST:event_btnMudarStatusActionPerformed

    public void switchPanels(JPanel panel) {
        jLayeredPane1.removeAll();
        jLayeredPane1.add(panel);
        jLayeredPane1.repaint();
        jLayeredPane1.revalidate();
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new Telas().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private keeptoo.KButton btnCPU;
    private keeptoo.KButton btnCadastrar;
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel btnClose1;
    private keeptoo.KButton btnEntrar;
    private keeptoo.KButton btnHardware;
    private keeptoo.KButton btnManutencao;
    private keeptoo.KButton btnMemoria;
    private keeptoo.KButton btnMudarStatus;
    private keeptoo.KButton btnProcessos;
    private javax.swing.JPanel graficoCpu;
    private javax.swing.JPanel graficoMemoria;
    private javax.swing.JPanel hardware;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable1;
    private com.mycompany.moveplus.PainelImagemFundo jpnlD;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel lblDisco;
    private javax.swing.JLabel lblMemoria;
    private javax.swing.JLabel lblResult;
    private javax.swing.JLabel lblResultID;
    private javax.swing.JLabel lblSO;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblprocessador;
    private javax.swing.JPanel manutencao;
    private javax.swing.JPanel memoria;
    private javax.swing.JPanel pncpu;
    private javax.swing.JPanel pnlBtns;
    private javax.swing.JPanel pnlCards;
    private keeptoo.KGradientPanel pnlLogin;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel processos;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLogin1;
    private javax.swing.JPasswordField txtSenha1;
    // End of variables declaration//GEN-END:variables
}
