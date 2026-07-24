/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ClsAsignacionLlamada;

/**
 *
 * @author Juan Porras
 */
public class FrmAsignacionLlamada extends javax.swing.JFrame {

    /**
     * Creates new form FrmUsuario
     */
    public FrmAsignacionLlamada() {
        initComponents();
        this.LlenarTabla();
        this.LlenarCampana();
    }
//Declaracion de variables
    ClsAsignacionLlamada objClsAsignacionLlamada = new ClsAsignacionLlamada();

//Contenido tabla
    DefaultTableModel tabladatos;

//Metodos 
    public void capturar() {
        objClsAsignacionLlamada.setCodAsi(Integer.parseInt(TxtCodAsi.getText()));
        objClsAsignacionLlamada.setConAseAsi(Integer.parseInt(CboConAseAsi.getSelectedItem().toString()));
        objClsAsignacionLlamada.setConCliAsi(Integer.parseInt(CboConCliAsi.getSelectedItem().toString()));
        objClsAsignacionLlamada.setFecAsi(TxtFecAsi.getText());
    }

    public void limpiar() {
        TxtCodAsi.setText(null);
        CboCam.setSelectedIndex(0);
        CboConAseAsi.setSelectedIndex(0);
        CboConCliAsi.setSelectedIndex(0);
        TxtNomAse.setText(null);
        TxtNomCli.setText(null);
        TxtFecAsi.setText(null);
    }

    public void bloquear() {
        this.limpiar();
        CboCam.setEnabled(false);
        CboConAseAsi.setEnabled(false);
        CboConCliAsi.setEnabled(false);
        TxtNomAse.setEnabled(false);
        TxtNomCli.setEnabled(false);
        TxtFecAsi.setEnabled(false);
        BtnGua.setEnabled(false);
        BtnAct.setEnabled(false);
    }

    public void desbloquear() {
        CboCam.setEnabled(true);
        CboConAseAsi.setEnabled(true);
        CboConCliAsi.setEnabled(true);
        TxtNomAse.setEnabled(true);
        TxtNomCli.setEnabled(true);
        TxtFecAsi.setEnabled(true);
        BtnGua.setEnabled(true);
    }

    public void fecha() {
        Date FechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String CadenaFecha = formato.format(FechaActual);
        TxtFecAsi.setText("" + CadenaFecha);
    }

    public void LlenarCampana() {
        try {
            CboCam.removeAllItems();
            CboCam.addItem("Seleccione...");
            objClsAsignacionLlamada.LlenarCampana();
            while (objClsAsignacionLlamada.DatosCampana.next()) {
                CboCam.addItem(objClsAsignacionLlamada.DatosCampana.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al llenar campañas: " + e);
        }

    }

    public void LlenarAsesor() {
        try {
            CboConAseAsi.removeAllItems();
            CboConAseAsi.addItem("Seleccione...");
            objClsAsignacionLlamada.LlenarAsesor();
            while (objClsAsignacionLlamada.DatosAsesor.next() == true) {
                CboConAseAsi.addItem(objClsAsignacionLlamada.DatosAsesor.getString(1));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar los documentos del usuario:  " + e);
        }
    }

    public void BuscarAsesor() {

        try {
            objClsAsignacionLlamada.BuscarAsesor();
            if (objClsAsignacionLlamada.DatosBuscarAsesor.next() == true) {
                TxtNomAse.setText(objClsAsignacionLlamada.DatosBuscarAsesor.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar el nombre del asesor:  " + e);
        }
    }

    public void LlenarCliente() {
        try {
            CboConCliAsi.removeAllItems();
            CboConCliAsi.addItem("Seleccione...");
            objClsAsignacionLlamada.LlenarCliente();
            while (objClsAsignacionLlamada.DatosCliente.next() == true) {
                CboConCliAsi.addItem(objClsAsignacionLlamada.DatosCliente.getString(1));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar los documentos del cliente:  " + e);
        }
    }

    public void BuscarCliente() {
        try {
            objClsAsignacionLlamada.BuscarCliente();
            if (objClsAsignacionLlamada.DatosBuscarCliente.next() == true) {
                TxtNomCli.setText(objClsAsignacionLlamada.DatosBuscarCliente.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar el nombre del asesor:  " + e);
        }
    }

    public boolean validar() {
        if (CboConAseAsi.getSelectedItem() == "Seleccione...") {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el documento del usuario");
            CboConAseAsi.grabFocus();
        } else if (CboConCliAsi.getSelectedItem() == "Seleccione...") {
            JOptionPane.showMessageDialog(null, "Debe seleccionar  el documento del cliente");
            CboConCliAsi.grabFocus();
        } else {
            return true;
        }
        return false;
    }

    public void consecutivo() {
        objClsAsignacionLlamada.consecutivo();
        try {
            if (objClsAsignacionLlamada.DatosConsecutivo.next()) {
                TxtCodAsi.setText(objClsAsignacionLlamada.DatosConsecutivo.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se puede generar el codigo: " + e);
        }
    }

    public void nuevo() {
        this.bloquear();
        this.desbloquear();
        this.consecutivo();
        this.LlenarAsesor();
        this.LlenarCliente();
        this.fecha();
        CboConAseAsi.grabFocus();
    }

    public void CrearTabla() {
        Object modelodata[][] = new Object[0][0];
        Object modelotitulos[] = {"Codigo", "Asesor", "Cliente"};
        tabladatos = new DefaultTableModel();
        tabladatos = new DefaultTableModel(modelodata, modelotitulos);
        this.TblAsiLla.setModel(this.tabladatos);
    }

    public void BorrarTabla() {
        while (0 < this.TblAsiLla.getRowCount()) {
            TblAsiLla.setModel(new DefaultTableModel());
            this.CrearTabla();
        }
    }

    public void LlenarTabla() {
        try {
            this.BorrarTabla();
            objClsAsignacionLlamada.LlenarTabla();
            while (objClsAsignacionLlamada.DatosTabla.next() == true) {
                String codigo = objClsAsignacionLlamada.DatosTabla.getString(1);
                String asesor = objClsAsignacionLlamada.DatosTabla.getString(2);
                String cliente = objClsAsignacionLlamada.DatosTabla.getString(3);
                Object fila[] = {codigo, asesor, cliente};
                tabladatos.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla: " + e);
        }
    }

    public void guardar() {
        if (validar() == true) {
            this.capturar();
            if (objClsAsignacionLlamada.ExisteAsignacionGuardar()) {
                JOptionPane.showMessageDialog(null, "Esta llamada ya existe");
                return;
            }
            objClsAsignacionLlamada.guardar();
            this.LlenarTabla();
            this.bloquear();
        }
    }

public void buscar() {
    try {
        String bus = JOptionPane.showInputDialog("Digite el codigo de la asignacion de llamada que desea buscar");
        objClsAsignacionLlamada.setCodAsi(Integer.parseInt(bus));
        objClsAsignacionLlamada.buscar();
        if (objClsAsignacionLlamada.DatosBuscar.next() == true) {
            this.desbloquear();
            TxtCodAsi.setText(objClsAsignacionLlamada.DatosBuscar.getString(1));
            TxtFecAsi.setText(objClsAsignacionLlamada.DatosBuscar.getString(4));
            int conAse = objClsAsignacionLlamada.DatosBuscar.getInt(2);
            objClsAsignacionLlamada.setConAseAsi(conAse);
            int codCam = objClsAsignacionLlamada.DatosBuscar.getInt(5);
            objClsAsignacionLlamada.setCodCam(codCam);
            CboCam.setSelectedItem(String.valueOf(codCam));
            this.LlenarAsesor();
            this.LlenarCliente();
            CboConAseAsi.setSelectedItem(String.valueOf(conAse));
            int conCli = objClsAsignacionLlamada.DatosBuscar.getInt(3);
            objClsAsignacionLlamada.setConCliAsi(conCli);
            CboConCliAsi.setSelectedItem(String.valueOf(conCli));
            this.BuscarAsesor();
            this.BuscarCliente();
            TxtCodAsi.setEnabled(false);
            BtnAct.setEnabled(true);
            BtnGua.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "La asignacion de llamada no existe");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al buscar: " + e);
    }
}

    public void actualizar() {
        try {
            if (validar() == true) {
                this.capturar();
                if (objClsAsignacionLlamada.existeAsignacionActualizar()) {
                    JOptionPane.showMessageDialog(null, "Esta llamada ya existe");
                    return;
                }
                objClsAsignacionLlamada.actualizar();
                this.LlenarTabla();
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TxtCodAsi = new javax.swing.JTextField();
        BtnGua = new javax.swing.JButton();
        BtnNue = new javax.swing.JButton();
        BtnBus = new javax.swing.JButton();
        BtnAct = new javax.swing.JButton();
        BtnRegr = new javax.swing.JButton();
        CboConCliAsi = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblAsiLla = new javax.swing.JTable();
        CboConAseAsi = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        TxtFecAsi = new javax.swing.JTextField();
        TxtNomAse = new javax.swing.JTextField();
        TxtNomCli = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        CboCam = new javax.swing.JComboBox<>();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Asignacion de llamada");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 51, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Asignacion de llamada");

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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cons.png"))); // NOI18N
        jLabel2.setText("Codigo");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nom.png"))); // NOI18N
        jLabel3.setText("Asesores");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/rol.png"))); // NOI18N
        jLabel4.setText("Clientes");

        TxtCodAsi.setEnabled(false);

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

        CboConCliAsi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..." }));
        CboConCliAsi.setEnabled(false);
        CboConCliAsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboConCliAsiActionPerformed(evt);
            }
        });

        TblAsiLla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Codigo", "Agente", "Cliente"
            }
        ));
        TblAsiLla.setEnabled(false);
        jScrollPane2.setViewportView(TblAsiLla);

        CboConAseAsi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..." }));
        CboConAseAsi.setEnabled(false);
        CboConAseAsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboConAseAsiActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 51, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fec.png"))); // NOI18N
        jLabel5.setText("Fecha");

        TxtFecAsi.setEditable(false);
        TxtFecAsi.setEnabled(false);

        TxtNomAse.setEditable(false);
        TxtNomAse.setEnabled(false);

        TxtNomCli.setEditable(false);
        TxtNomCli.setEnabled(false);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cam.png"))); // NOI18N
        jLabel6.setText("Campaña");

        CboCam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..." }));
        CboCam.setEnabled(false);
        CboCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboCamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtCodAsi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BtnBus))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TxtFecAsi))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CboConAseAsi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CboConCliAsi, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TxtNomAse)
                                    .addComponent(TxtNomCli, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CboCam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 381, Short.MAX_VALUE)
                .addComponent(BtnRegr))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(BtnNue)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnGua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnAct)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(TxtCodAsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(BtnBus, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(CboCam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(CboConAseAsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNomAse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CboConCliAsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNomCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TxtFecAsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(BtnGua, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(BtnNue, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(BtnAct))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnRegr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void CboConAseAsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboConAseAsiActionPerformed
        // TODO add your handling code here:

        if (CboConAseAsi.getSelectedIndex() > 0) {
            objClsAsignacionLlamada.setConAseAsi(Integer.parseInt(CboConAseAsi.getSelectedItem().toString()));
            this.BuscarAsesor();
            this.LlenarCliente();
        } else {
            TxtNomAse.setText(null);
        }

    }//GEN-LAST:event_CboConAseAsiActionPerformed

    private void CboConCliAsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboConCliAsiActionPerformed
        if (CboConCliAsi.getSelectedIndex() > 0) {
            objClsAsignacionLlamada.setConCliAsi(Integer.parseInt(CboConCliAsi.getSelectedItem().toString()));
            this.BuscarCliente();
        } else {
            TxtNomCli.setText(null);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_CboConCliAsiActionPerformed

    private void CboCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboCamActionPerformed
        if (CboCam.getSelectedIndex() > 0) {
            objClsAsignacionLlamada.setCodCam(Integer.parseInt(CboCam.getSelectedItem().toString()));
            this.LlenarAsesor();
            this.LlenarCliente();
        } else {
            CboConAseAsi.removeAllItems();
            CboConAseAsi.addItem("Seleccione...");
            CboConCliAsi.removeAllItems();
            CboConCliAsi.addItem("Seleccione...");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_CboCamActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAsignacionLlamada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAsignacionLlamada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAsignacionLlamada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAsignacionLlamada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAsignacionLlamada().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAct;
    private javax.swing.JButton BtnBus;
    private javax.swing.JButton BtnGua;
    private javax.swing.JButton BtnNue;
    private javax.swing.JButton BtnRegr;
    private javax.swing.JComboBox<String> CboCam;
    private javax.swing.JComboBox<String> CboConAseAsi;
    private javax.swing.JComboBox<String> CboConCliAsi;
    private javax.swing.JTable TblAsiLla;
    private javax.swing.JTextField TxtCodAsi;
    private javax.swing.JTextField TxtFecAsi;
    private javax.swing.JTextField TxtNomAse;
    private javax.swing.JTextField TxtNomCli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
