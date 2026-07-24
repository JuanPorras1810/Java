/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ClsCampaña;

/**
 *
 * @author Juan Porras
 */
public class FrmCampaña extends javax.swing.JFrame {

    /**
     * Creates new form FrmUsuario
     */
    public FrmCampaña() {
        initComponents();
        this.BloquearFechasPasadas();
        this.LlenarTabla();
    }
//Declaracion de variables
    String RutaPDF;
    ClsCampaña objClsCampaña = new ClsCampaña();

//Contenido tabla
    DefaultTableModel tabladatos;

//Metodos 
    public void capturar() {
        objClsCampaña.setCodCam(Integer.parseInt(TxtCodCam.getText()));
        objClsCampaña.setNomCam(TxtNomCam.getText());
        String DatoFecIni = new SimpleDateFormat("yyyy-MM-dd").format(TxtFecIniCam.getDate());
        objClsCampaña.setFecIniCam(DatoFecIni);
        String DatoFecFin = new SimpleDateFormat("yyyy-MM-dd").format(TxtFecFinCam.getDate());
        objClsCampaña.setFecFinCam(DatoFecFin);
        objClsCampaña.setProCam(TxtUrlPro.getText());

    }

    public void CapturarTipificaciones() {
        objClsCampaña.setCodTip(Integer.parseInt(TxtCodTipif.getText()));
        objClsCampaña.setCodCamTip(Integer.parseInt(CboCodCamTip.getSelectedItem().toString()));
        objClsCampaña.setNomTip(TxtNomTip.getText());

    }

    public void limpiar() {
        TxtCodCam.setText(null);
        TxtFecIniCam.setDate(null);
        TxtFecFinCam.setDate(null);
        TxtNomCam.setText(null);
        TxtUrlPro.setText(null);
    }

    public void bloquear() {
        this.limpiar();
        TxtFecIniCam.setEnabled(false);
        TxtFecFinCam.setEnabled(false);
        TxtNomCam.setEnabled(false);
        TxtUrlPro.setEnabled(false);
        BtnGua.setEnabled(false);
        BtnAct.setEnabled(false);
    }

    public void desbloquear() {
        TxtFecIniCam.setEnabled(true);
        TxtFecFinCam.setEnabled(true);
        TxtNomCam.setEnabled(true);
        TxtUrlPro.setEnabled(true);
        BtnGua.setEnabled(true);
    }

    public void LimpiarTipificacion() {
        TxtCodTipif.setText(null);
        CboCodCamTip.setSelectedIndex(0);
        TxtNomTip.setText(null);
    }

    public void BloquearTipificacion() {
        this.LimpiarTipificacion();
        TxtCodTipif.setEnabled(false);
        CboCodCamTip.setEnabled(false);
        TxtNomTip.setEnabled(false);
        BtnGua1.setEnabled(false);
        BtnAct1.setEnabled(false);
    }

    public void DesbloquearTipificacion() {
        TxtCodTipif.setEnabled(true);
        CboCodCamTip.setEnabled(true);
        TxtNomTip.setEnabled(true);
        BtnGua1.setEnabled(true);
    }

    public boolean validar() {
        if (TxtFecIniCam.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar o digitar la fecha inicio");
            TxtFecIniCam.grabFocus();
        } else if (TxtFecFinCam.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar o digitar la fecha fin");
            TxtFecFinCam.grabFocus();
        } else if (TxtNomCam.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el objetivo de la campaña");
            TxtNomCam.grabFocus();
        } else if (TxtNomCam.getText().length() > 1000) {
            JOptionPane.showMessageDialog(null, "Error, el objetivo solo permite un maximo de 1000 caracteres");
            TxtNomCam.grabFocus();
        } else {
            return true;
        }
        return false;
    }

    public boolean ValidarTipificacion() {
        if (CboCodCamTip.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una campaña");
            CboCodCamTip.grabFocus();
        } else if (TxtNomTip.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el nombre de la tipificacion");
            TxtNomTip.grabFocus();
        } else if (TxtNomTip.getText().length() > 100) {
            JOptionPane.showMessageDialog(null, "Error, la tipificacion solo permite un maximo de 100 caracteres");
            TxtNomTip.grabFocus();
        } else {
            return true;
        }
        return false;
    }

    public void consecutivo() {
        objClsCampaña.consecutivo();
        try {
            if (objClsCampaña.DatosConsecutivo.next()) {
                TxtCodCam.setText(objClsCampaña.DatosConsecutivo.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se puede generar el codigo: " + e);
        }
    }

    public void LlenarCampana() {
        try {
            CboCodCamTip.removeAllItems();
            CboCodCamTip.addItem("Seleccione...");
            objClsCampaña.LlenarCampana();
            while (objClsCampaña.DatosCampana.next() == true) {
                CboCodCamTip.addItem(objClsCampaña.DatosCampana.getString(1));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar las campañas:  " + e);
        }

    }

    public void ConsecutivoTipificaciones() {
        objClsCampaña.ConsecutivoTipificaciones();
        try {
            if (objClsCampaña.DatosConsecutivoTipificaciones.next()) {
                TxtCodTipif.setText(objClsCampaña.DatosConsecutivoTipificaciones.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se puede generar el codigo de tipificaciones: " + e);
        }
    }

    public void BloquearFechasPasadas() {
        java.util.Date hoy = new java.util.Date();
        TxtFecIniCam.setMinSelectableDate(hoy);
        TxtFecFinCam.setMinSelectableDate(hoy);
    }

    public void CrearTabla() {
        Object modelodata[][] = new Object[0][0];
        Object modelotitulos[] = {"Codigo", "Campaña", "Nombre"};
        tabladatos = new DefaultTableModel();
        tabladatos = new DefaultTableModel(modelodata, modelotitulos);
        this.TblTip.setModel(this.tabladatos);
    }

    public void BorrarTabla() {
        while (0 < this.TblTip.getRowCount()) {
            TblTip.setModel(new DefaultTableModel());
            this.CrearTabla();
        }
    }

    public void LlenarTabla() {
        try {
            this.BorrarTabla();
            objClsCampaña.LlenarTabla();
            while (objClsCampaña.DatosTabla.next() == true) {
                String codigo = objClsCampaña.DatosTabla.getString(1);
                String campaña = objClsCampaña.DatosTabla.getString(2);
                String nombre = objClsCampaña.DatosTabla.getString(3);
                Object fila[] = {codigo, campaña, nombre};
                tabladatos.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla: " + e);
        }
    }

    public void nuevo() {
        this.bloquear();
        this.desbloquear();
        this.consecutivo();
        TxtFecIniCam.grabFocus();
    }

    public void NuevoTipificaciones() {
        this.BloquearTipificacion();
        this.DesbloquearTipificacion();
        this.ConsecutivoTipificaciones();
        this.LlenarCampana();
        CboCodCamTip.grabFocus();
    }

    public void guardar() {
        if (validar() == true) {
            this.capturar();
            objClsCampaña.guardar();
            this.bloquear();
        }
    }

    public void GuardarTipificaciones() {
        if (ValidarTipificacion() == true) {
            this.CapturarTipificaciones();
            objClsCampaña.GuardarTipificacion();
            this.LlenarTabla();
            this.BloquearTipificacion();
        }
    }

    public void buscar() {
        try {
            String bus = JOptionPane.showInputDialog("Digite el codigo de la campaña que desea buscar");
            objClsCampaña.setCodCam(Integer.parseInt(bus));
            objClsCampaña.buscar();
            if (objClsCampaña.DatosBuscar.next() == true) {
                TxtCodCam.setText(objClsCampaña.DatosBuscar.getString(1));
                TxtNomCam.setText(objClsCampaña.DatosBuscar.getString(2));
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    TxtFecIniCam.setDate(formato.parse(objClsCampaña.DatosBuscar.getString(3)));
                    TxtFecFinCam.setDate(formato.parse(objClsCampaña.DatosBuscar.getString(4)));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al traer fechas: " + e);
                }
                TxtUrlPro.setText(objClsCampaña.DatosBuscar.getString(5));
                this.desbloquear();
                TxtCodCam.setEnabled(false);
                BtnAct.setEnabled(true);
                BtnGua.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "La campaña no existe");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al visualizar los datos de la campaña: " + e);
        }
    }

    public void actualizar() {
        try {
            if (validar() == true) {
                this.capturar();
                objClsCampaña.actualizar();
                this.bloquear();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fallas Tecnicas al actualizar: " + e);
        }
    }

    public void ActualizarTipificaciones() {
        try {
            if (ValidarTipificacion() == true) {
                this.CapturarTipificaciones();
                objClsCampaña.ActualizarTipificacion();
                this.LlenarTabla();
                this.BloquearTipificacion();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fallas Tecnicas al actualizar: " + e);
        }
    }

    public void regresar() {
        FrmIndex objFrmIdex = new FrmIndex();
        objFrmIdex.setVisible(true);
        this.dispose();
    }

    public void cargarPDF() {
        JFileChooser fileChooser = new JFileChooser();//Crea una ventana para seleccionar archivos
        fileChooser.setDialogTitle("Seleccionar PDF"); //Titulo que aparece en la ventana
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PDF", "pdf"));//Solo permite ver archivos con extensión .pdf
        int seleccion = fileChooser.showOpenDialog(this); //Guarda la opción que el usuario selecciona
        if (seleccion == JFileChooser.APPROVE_OPTION) { //Verifica si seleccionoun archivo
            File archivo = fileChooser.getSelectedFile();//Obtiene el archivo 
            RutaPDF = archivo.getAbsolutePath(); //Guarda la ruta
            TxtUrlPro.setText(RutaPDF);
            JOptionPane.showMessageDialog(null, "PDF seleccionado:" + RutaPDF);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TxtCodCam = new javax.swing.JTextField();
        BtnGua = new javax.swing.JButton();
        BtnNue = new javax.swing.JButton();
        BtnBus = new javax.swing.JButton();
        BtnAct = new javax.swing.JButton();
        BtnRegr = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtNomCam = new javax.swing.JTextArea();
        TxtFecIniCam = new com.toedter.calendar.JDateChooser();
        TxtFecFinCam = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        TxtUrlPro = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblTip = new javax.swing.JTable();
        BtnNue1 = new javax.swing.JButton();
        BtnGua1 = new javax.swing.JButton();
        BtnAct1 = new javax.swing.JButton();
        TxtCodTipif = new javax.swing.JTextField();
        TxtNomTip = new javax.swing.JTextField();
        CboCodCamTip = new javax.swing.JComboBox<>();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Campaña");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 51, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Campaña");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 51, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cons.png"))); // NOI18N
        jLabel2.setText("Codigo");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fec.png"))); // NOI18N
        jLabel3.setText("Fecha inicio");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fec.png"))); // NOI18N
        jLabel4.setText("Fecha fin");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/obs.png"))); // NOI18N
        jLabel9.setText("Nombre");

        TxtCodCam.setEnabled(false);

        BtnGua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sav.png"))); // NOI18N
        BtnGua.setToolTipText("Guardar");
        BtnGua.setContentAreaFilled(false);
        BtnGua.setDefaultCapable(false);
        BtnGua.setEnabled(false);
        BtnGua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuaActionPerformed(evt);
            }
        });

        BtnNue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nue.png"))); // NOI18N
        BtnNue.setToolTipText("Nuevo");
        BtnNue.setBorderPainted(false);
        BtnNue.setContentAreaFilled(false);
        BtnNue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNueActionPerformed(evt);
            }
        });

        BtnBus.setBackground(new java.awt.Color(0, 51, 255));
        BtnBus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BtnBus.setForeground(new java.awt.Color(255, 255, 255));
        BtnBus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bus.png"))); // NOI18N
        BtnBus.setToolTipText("Buscar");
        BtnBus.setBorderPainted(false);
        BtnBus.setDefaultCapable(false);
        BtnBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBusActionPerformed(evt);
            }
        });

        BtnAct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/act.png"))); // NOI18N
        BtnAct.setToolTipText("Actualizar");
        BtnAct.setBorderPainted(false);
        BtnAct.setContentAreaFilled(false);
        BtnAct.setEnabled(false);
        BtnAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActActionPerformed(evt);
            }
        });

        BtnRegr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reg.png"))); // NOI18N
        BtnRegr.setText("Regresar");
        BtnRegr.setToolTipText("Regresa al menu principal");
        BtnRegr.setContentAreaFilled(false);
        BtnRegr.setDefaultCapable(false);
        BtnRegr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegrActionPerformed(evt);
            }
        });

        jScrollPane1.setEnabled(false);

        TxtNomCam.setColumns(20);
        TxtNomCam.setRows(5);
        TxtNomCam.setEnabled(false);
        jScrollPane1.setViewportView(TxtNomCam);

        TxtFecIniCam.setEnabled(false);

        TxtFecFinCam.setEnabled(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pdf.png"))); // NOI18N
        jButton1.setText("Cargar protocolo");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        TxtUrlPro.setEditable(false);
        TxtUrlPro.setEnabled(false);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanel3.setBackground(new java.awt.Color(0, 51, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Tipificaciones");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/obs.png"))); // NOI18N
        jLabel6.setText("Nombre");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cam.png"))); // NOI18N
        jLabel7.setText("Campaña");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cons.png"))); // NOI18N
        jLabel8.setText("Codigo");

        TblTip.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblTip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblTipMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TblTip);

        BtnNue1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nue.png"))); // NOI18N
        BtnNue1.setToolTipText("Nuevo");
        BtnNue1.setBorderPainted(false);
        BtnNue1.setContentAreaFilled(false);
        BtnNue1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNue1ActionPerformed(evt);
            }
        });

        BtnGua1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sav.png"))); // NOI18N
        BtnGua1.setToolTipText("Guardar");
        BtnGua1.setContentAreaFilled(false);
        BtnGua1.setDefaultCapable(false);
        BtnGua1.setEnabled(false);
        BtnGua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGua1ActionPerformed(evt);
            }
        });

        BtnAct1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/act.png"))); // NOI18N
        BtnAct1.setToolTipText("Actualizar");
        BtnAct1.setBorderPainted(false);
        BtnAct1.setContentAreaFilled(false);
        BtnAct1.setEnabled(false);
        BtnAct1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAct1ActionPerformed(evt);
            }
        });

        TxtCodTipif.setEditable(false);
        TxtCodTipif.setEnabled(false);

        TxtNomTip.setEnabled(false);

        CboCodCamTip.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..." }));
        CboCodCamTip.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 738, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtCodCam, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BtnBus))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(TxtUrlPro)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TxtFecIniCam, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TxtFecFinCam, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(BtnNue)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnGua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnAct))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(CboCodCamTip, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(TxtCodTipif, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TxtNomTip, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(BtnNue1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnGua1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnAct1)))
                        .addContainerGap(29, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnRegr))))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(TxtCodTipif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(CboCodCamTip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(TxtNomTip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnNue1)
                            .addComponent(BtnAct1)
                            .addComponent(BtnGua1))
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(BtnRegr))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(TxtCodCam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(BtnBus, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(TxtFecIniCam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(TxtFecFinCam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtUrlPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(BtnGua, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(BtnNue, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(BtnAct)))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnNueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNueActionPerformed
        this.nuevo();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnNueActionPerformed

    private void BtnGuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuaActionPerformed
        this.guardar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGuaActionPerformed

    private void BtnActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActActionPerformed
        this.actualizar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnActActionPerformed

    private void BtnBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBusActionPerformed
        this.buscar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBusActionPerformed

    private void BtnRegrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegrActionPerformed
        this.regresar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRegrActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.cargarPDF();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BtnNue1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNue1ActionPerformed
        this.NuevoTipificaciones();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnNue1ActionPerformed

    private void BtnGua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGua1ActionPerformed
        this.GuardarTipificaciones();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGua1ActionPerformed

    private void BtnAct1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAct1ActionPerformed
        this.ActualizarTipificaciones();// TODO add your handling code here:
    }//GEN-LAST:event_BtnAct1ActionPerformed

    private void TblTipMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblTipMouseClicked
        int linea = TblTip.getSelectedRow();
        this.DesbloquearTipificacion();
        BtnAct1.setEnabled(true);
        BtnGua1.setEnabled(false);
        this.LlenarCampana();
        TxtCodTipif.setText(TblTip.getValueAt(linea, 0).toString());
        CboCodCamTip.setSelectedItem(TblTip.getValueAt(linea, 1));
        TxtNomTip.setText(TblTip.getValueAt(linea, 2).toString());
        // TODO add your handling code here:
    }//GEN-LAST:event_TblTipMouseClicked

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
            java.util.logging.Logger.getLogger(FrmCampaña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCampaña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCampaña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCampaña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCampaña().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAct;
    private javax.swing.JButton BtnAct1;
    private javax.swing.JButton BtnBus;
    private javax.swing.JButton BtnGua;
    private javax.swing.JButton BtnGua1;
    private javax.swing.JButton BtnNue;
    private javax.swing.JButton BtnNue1;
    private javax.swing.JButton BtnRegr;
    private javax.swing.JComboBox<String> CboCodCamTip;
    private javax.swing.JTable TblTip;
    private javax.swing.JTextField TxtCodCam;
    private javax.swing.JTextField TxtCodTipif;
    private com.toedter.calendar.JDateChooser TxtFecFinCam;
    private com.toedter.calendar.JDateChooser TxtFecIniCam;
    private javax.swing.JTextArea TxtNomCam;
    private javax.swing.JTextField TxtNomTip;
    private javax.swing.JTextField TxtUrlPro;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
