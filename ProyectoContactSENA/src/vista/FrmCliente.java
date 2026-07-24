/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.io.BufferedReader;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;
import modelo.ClsCliente;

/**
 *
 * @author juanp
 */
public class FrmCliente extends javax.swing.JFrame {

    /**
     * Creates new form FrmUsuario
     */
    public FrmCliente() {
        initComponents();
    }
//Declaracion de variables
    ClsCliente objClsCliente = new ClsCliente();

//Metodos
    public void capturar() {
        objClsCliente.setCodCamCli(Integer.parseInt(CboCodCamCli.getSelectedItem().toString()));
        objClsCliente.setIdCli(TxtIdCli.getText());
        objClsCliente.setTipDoCli(CboTipDocCli.getSelectedItem().toString());
        objClsCliente.setNomCli(TxtNomCli.getText());
        objClsCliente.setEmaCli(TxtEmaCli.getText());
        objClsCliente.setDirCli(TxtDirCli.getText());
        objClsCliente.setBarCli(TxtBarCli.getText());
        objClsCliente.setMunCli(TxtMunCli.getText());
        objClsCliente.setTelCli(TxtTelCli.getText());
        objClsCliente.setTelAltCli(TxtTelAltCli.getText());
        objClsCliente.setObsCli(TxtObsCli.getText());
    }

    void limpiar() {
        TxtConCli.setText(null);
        CboCodCamCli.setSelectedIndex(0);
        TxtIdCli.setText(null);
        CboTipDocCli.setSelectedIndex(0);
        TxtNomCli.setText(null);
        TxtEmaCli.setText(null);
        TxtDirCli.setText(null);
        TxtBarCli.setText(null);
        TxtMunCli.setText(null);
        TxtTelCli.setText(null);
        TxtTelAltCli.setText(null);
        TxtObsCli.setText(null);
    }

    public void bloquear() {
        this.limpiar();
        TxtConCli.setEnabled(false);
        CboCodCamCli.setEnabled(false);
        TxtIdCli.setEnabled(false);
        CboTipDocCli.setEnabled(false);
        TxtNomCli.setEnabled(false);
        TxtEmaCli.setEnabled(false);
        TxtDirCli.setEnabled(false);
        TxtBarCli.setEnabled(false);
        TxtMunCli.setEnabled(false);
        TxtTelCli.setEnabled(false);
        TxtTelAltCli.setEnabled(false);
        TxtObsCli.setEnabled(false);
        BtnReg.setEnabled(false);
        BtnAct.setEnabled(false);
        BtnCsv.setEnabled(false);
    }

    public void desbloquear() {
        TxtConCli.setEnabled(true);
        CboCodCamCli.setEnabled(true);
        TxtIdCli.setEnabled(true);
        CboTipDocCli.setEnabled(true);
        TxtNomCli.setEnabled(true);
        TxtEmaCli.setEnabled(true);
        TxtDirCli.setEnabled(true);
        TxtBarCli.setEnabled(true);
        TxtMunCli.setEnabled(true);
        TxtTelCli.setEnabled(true);
        TxtTelAltCli.setEnabled(true);
        TxtObsCli.setEnabled(true);
        BtnReg.setEnabled(true);
        BtnCsv.setEnabled(true);
    }

    public boolean validar() {
        if (CboCodCamCli.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe elegir la campaña");
            CboCodCamCli.grabFocus();
        } else if (TxtTelCli.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el telefono");
            TxtTelCli.grabFocus();
        } else if (TxtTelCli.getText().length() > 10) {
            JOptionPane.showMessageDialog(null, "Error, el telefono solo permite un maximo de 10 caracteres");
            TxtTelCli.grabFocus();
        } else {
            return true;
        }
        return false;
    }

    public void LlenarCampana() {
        try {
            CboCodCamCli.removeAllItems();
            CboCodCamCli.addItem("Seleccione...");
            objClsCliente.LlenarCampana();
            while (objClsCliente.DatosCampana.next() == true) {
                CboCodCamCli.addItem(objClsCliente.DatosCampana.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar las campaña:  " + e);
        }
    }

    public void nuevo() {
        this.bloquear();
        this.desbloquear();
        this.LlenarCampana();
        TxtIdCli.grabFocus();
    }

    public void registrar() {
        if (validar() == true) {
            this.capturar();
            objClsCliente.registrar();
            this.bloquear();
        }
    }

    public void buscar() {
        try {
            String bus = JOptionPane.showInputDialog("Digite el consecutivo del cliente que desea buscar");
            objClsCliente.setConCli(Integer.parseInt(bus));
            objClsCliente.buscar();
            if (objClsCliente.DatosBuscar.next() == true) {
                this.LlenarCampana();
                TxtConCli.setText(objClsCliente.DatosBuscar.getString(1));
                CboCodCamCli.setSelectedItem(objClsCliente.DatosBuscar.getString(2));
                TxtIdCli.setText(objClsCliente.DatosBuscar.getString(3));
                CboTipDocCli.setSelectedItem(objClsCliente.DatosBuscar.getString(4));
                TxtNomCli.setText(objClsCliente.DatosBuscar.getString(5));
                TxtEmaCli.setText(objClsCliente.DatosBuscar.getString(6));
                TxtDirCli.setText(objClsCliente.DatosBuscar.getString(7));
                TxtBarCli.setText(objClsCliente.DatosBuscar.getString(8));
                TxtMunCli.setText(objClsCliente.DatosBuscar.getString(9));
                TxtTelCli.setText(objClsCliente.DatosBuscar.getString(10));
                TxtTelAltCli.setText(objClsCliente.DatosBuscar.getString(11));
                TxtObsCli.setText(objClsCliente.DatosBuscar.getString(12));
                this.desbloquear();
                BtnAct.setEnabled(true);
                BtnReg.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "El cliente no existe");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al visualizar los datos del cliente: " + e);
        }
    }

    public void actualizar() {
        try {
            if (validar() == true) {
                this.capturar();
                objClsCliente.actualizar();
                this.bloquear();
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

    public void leerCSV(String ruta) {//Ruta donde esta el archivo
        String linea;//Guardar cada linea del archivo
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) { //Abre el archivo 
            br.readLine(); //salta la primera fila documento

            while ((linea = br.readLine()) != null) {//Bucle que lee el archivo hasta que llegue al final
                String[] datos = linea.split(";", -1);//Separa los datos
                objClsCliente.setCodCamCli(Integer.parseInt(datos[0]));
                objClsCliente.setIdCli(datos[1]);
                objClsCliente.setTipDoCli(datos[2]);
                objClsCliente.setNomCli(datos[3]);
                objClsCliente.setEmaCli(datos[4]);
                objClsCliente.setDirCli(datos[5]);
                objClsCliente.setBarCli(datos[6]);
                objClsCliente.setMunCli(datos[7]);
                objClsCliente.setTelCli(datos[8]);
                objClsCliente.setTelAltCli(datos[9]);
                objClsCliente.registrar();
            }
            JOptionPane.showMessageDialog(null, "Carga de cliente exitosa!!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer CSV: " + e);
        }
    }

    public void cargarCSV() {
        JFileChooser fileChooser = new JFileChooser();//Crea una ventana para seleccionar archivos
        fileChooser.setDialogTitle("Seleccionar archivo CSV");//Titulo
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("CSV", "csv"));//Solo permite ver archivos con extensión .csv
        int seleccion = fileChooser.showOpenDialog(this);//Muestra el cuadro de dialogo
        if (seleccion == JFileChooser.APPROVE_OPTION) {  //Verifica si selecciono un archivo
            File archivo = fileChooser.getSelectedFile(); //Obtiene el archivo seleccionado
            leerCSV(archivo.getAbsolutePath()); //Lee el archivo
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
        TxtConCli = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TxtIdCli = new javax.swing.JTextField();
        CboTipDocCli = new javax.swing.JComboBox<>();
        TxtNomCli = new javax.swing.JTextField();
        TxtEmaCli = new javax.swing.JTextField();
        TxtDirCli = new javax.swing.JTextField();
        TxtTelCli = new javax.swing.JTextField();
        TxtTelAltCli = new javax.swing.JTextField();
        BtnReg = new javax.swing.JButton();
        BtnNue = new javax.swing.JButton();
        BtnBus = new javax.swing.JButton();
        BtnAct = new javax.swing.JButton();
        BtnRegr = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtObsCli = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        TxtBarCli = new javax.swing.JTextField();
        TxtMunCli = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        CboCodCamCli = new javax.swing.JComboBox<>();
        BtnCsv = new javax.swing.JButton();

        TxtConCli.setEditable(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cliente");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 51, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cliente");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/doc.png"))); // NOI18N
        jLabel2.setText("Documento");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/TipDoc.png"))); // NOI18N
        jLabel3.setText("Tipo de documento");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nom.png"))); // NOI18N
        jLabel4.setText("Nombre");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ema.png"))); // NOI18N
        jLabel5.setText("Email");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dir.png"))); // NOI18N
        jLabel6.setText("Direccion");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tel.png"))); // NOI18N
        jLabel7.setText("Telefono");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tel.png"))); // NOI18N
        jLabel8.setText("Telefono alternativo");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/obs.png"))); // NOI18N
        jLabel9.setText("Observaciones");

        TxtIdCli.setEnabled(false);

        CboTipDocCli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Cedula de ciudadania", "Tarjeta de identidad", "Cedula de extranjeria", "PEP", "Permiso por proteccion temporal" }));
        CboTipDocCli.setEnabled(false);

        TxtNomCli.setEnabled(false);

        TxtEmaCli.setEnabled(false);

        TxtDirCli.setEnabled(false);

        TxtTelCli.setEnabled(false);

        TxtTelAltCli.setEnabled(false);

        BtnReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usu.png"))); // NOI18N
        BtnReg.setToolTipText("Registrar");
        BtnReg.setContentAreaFilled(false);
        BtnReg.setDefaultCapable(false);
        BtnReg.setEnabled(false);
        BtnReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegActionPerformed(evt);
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

        TxtObsCli.setColumns(20);
        TxtObsCli.setRows(5);
        TxtObsCli.setEnabled(false);
        jScrollPane1.setViewportView(TxtObsCli);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dir.png"))); // NOI18N
        jLabel10.setText("Barrio");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dir.png"))); // NOI18N
        jLabel11.setText("Municipio");

        TxtBarCli.setEnabled(false);

        TxtMunCli.setEnabled(false);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cam.png"))); // NOI18N
        jLabel12.setText("Campaña");

        CboCodCamCli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..." }));
        CboCodCamCli.setEnabled(false);

        BtnCsv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/csv.png"))); // NOI18N
        BtnCsv.setToolTipText("Guarda datos desde excel");
        BtnCsv.setBorderPainted(false);
        BtnCsv.setContentAreaFilled(false);
        BtnCsv.setEnabled(false);
        BtnCsv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCsvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 384, Short.MAX_VALUE)
                .addComponent(BtnRegr))
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CboCodCamCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnBus)
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TxtTelCli, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TxtTelAltCli, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TxtMunCli))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TxtBarCli))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TxtNomCli, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TxtEmaCli))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TxtDirCli, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CboTipDocCli, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(BtnNue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnReg, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnAct)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnCsv, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(CboCodCamCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BtnBus, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxtIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(CboTipDocCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TxtNomCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TxtEmaCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TxtDirCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(TxtBarCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(TxtMunCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TxtTelCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(TxtTelAltCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnReg, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BtnNue, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addComponent(BtnAct))
                    .addComponent(BtnCsv))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnRegr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnNueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNueActionPerformed
        this.nuevo();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnNueActionPerformed

    private void BtnRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegActionPerformed
        this.registrar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRegActionPerformed

    private void BtnActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActActionPerformed
        this.actualizar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnActActionPerformed

    private void BtnBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBusActionPerformed
        this.buscar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBusActionPerformed

    private void BtnRegrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegrActionPerformed
        this.regresar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRegrActionPerformed

    private void BtnCsvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCsvActionPerformed
        this.cargarCSV();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCsvActionPerformed

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
            java.util.logging.Logger.getLogger(FrmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAct;
    private javax.swing.JButton BtnBus;
    private javax.swing.JButton BtnCsv;
    private javax.swing.JButton BtnNue;
    private javax.swing.JButton BtnReg;
    private javax.swing.JButton BtnRegr;
    private javax.swing.JComboBox<String> CboCodCamCli;
    private javax.swing.JComboBox<String> CboTipDocCli;
    private javax.swing.JTextField TxtBarCli;
    private javax.swing.JTextField TxtConCli;
    private javax.swing.JTextField TxtDirCli;
    private javax.swing.JTextField TxtEmaCli;
    private javax.swing.JTextField TxtIdCli;
    private javax.swing.JTextField TxtMunCli;
    private javax.swing.JTextField TxtNomCli;
    private javax.swing.JTextArea TxtObsCli;
    private javax.swing.JTextField TxtTelAltCli;
    private javax.swing.JTextField TxtTelCli;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
