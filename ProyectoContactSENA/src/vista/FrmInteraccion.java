/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.ClsCaso;
import modelo.ClsInteraccion;

/**
 *
 * @author Juan Porras
 */
public class FrmInteraccion extends javax.swing.JFrame {

    /**
     * Creates new form FrmUsuario
     */
    ArrayList<Integer> listaIdsTip = new ArrayList<>();
    public static int CodigoInteraccion;
    public static FrmInteraccion instancia;
    private ClsCaso objClsCaso;

    public void setObjClsCaso(ClsCaso caso) {
        this.objClsCaso = caso;
    }

    public FrmInteraccion() {
        initComponents();
        instancia = this;
        objClsInteraccion.setIdUsu(FrmRegistroUsuario.DocumentoUsuario);
        BtnCas.setVisible(false);
        //this.BuscarAsesor();
    }
//Declaracion de variables
    ClsInteraccion objClsInteraccion = new ClsInteraccion();

//Metodos
    public void capturar() {
        objClsInteraccion.setCodInt(Integer.parseInt(TxtCodInt.getText()));
        objClsInteraccion.setConAseInt(Integer.parseInt(TxtConAseInt.getText()));
        objClsInteraccion.setConCliInt(Integer.parseInt(TxtConCliInt.getText()));
        int idSeleccionado = listaIdsTip.get(CboCodTipInt.getSelectedIndex() - 1);
        objClsInteraccion.setCodTipInt(idSeleccionado);
        objClsInteraccion.setTipConInt(CboTipConInt.getSelectedItem().toString());
        objClsInteraccion.setMotInt(TxtMotInt.getText());
        objClsInteraccion.setFecInt(TxtFecInt.getText());
        objClsInteraccion.setHorIniInt(TxtHorIniInt.getText());
        objClsInteraccion.setHorFinInt(TxtHorFinInt.getText());
        objClsInteraccion.setTieProInt(TxtTieProInt.getText());
        objClsInteraccion.setObsInt(TxtObsInt.getText());
        objClsInteraccion.setCieCasInt(CboCieCasInt.getSelectedItem().toString());
    }

    public void limpiar() {
        TxtCodInt.setText(null);
        TxtConCliInt.setText(null);
        CboCodTipInt.setSelectedIndex(0);
        CboTipConInt.setSelectedIndex(0);
        TxtMotInt.setText(null);
        TxtFecInt.setText(null);
        TxtHorIniInt.setText(null);
        TxtHorFinInt.setText(null);
        TxtTieProInt.setText(null);
        TxtObsInt.setText(null);
        CboCieCasInt.setSelectedIndex(0);
        TxtDocCli.setText(null);
        TxtNomCli.setText(null);
        TxtEmaCli.setText(null);
        TxtTelCli.setText(null);
        TxtTelAltCli.setText(null);

    }

    public void bloquear() {
        this.limpiar();
        TxtCodInt.setEnabled(false);
        TxtConCliInt.setEnabled(false);
        CboCodTipInt.setEnabled(false);
        CboTipConInt.setEnabled(false);
        TxtMotInt.setEnabled(false);
        TxtFecInt.setEnabled(false);
        TxtHorIniInt.setEnabled(false);
        TxtHorFinInt.setEnabled(false);
        TxtTieProInt.setEnabled(false);
        TxtObsInt.setEnabled(false);
        CboCieCasInt.setEnabled(false);
        TxtDocCli.setEnabled(false);
        TxtNomCli.setEnabled(false);
        TxtEmaCli.setEnabled(false);
        TxtTelCli.setEnabled(false);
        TxtTelAltCli.setEnabled(false);
        BtnGua.setEnabled(false);
        BtnPdf.setEnabled(false);
    }

    public void desbloquear() {
        TxtCodInt.setEnabled(true);
        TxtConCliInt.setEnabled(true);
        CboCodTipInt.setEnabled(true);
        CboTipConInt.setEnabled(true);
        TxtMotInt.setEnabled(true);
        TxtFecInt.setEnabled(true);
        TxtHorIniInt.setEnabled(true);
        TxtHorFinInt.setEnabled(true);
        TxtTieProInt.setEnabled(true);
        TxtObsInt.setEnabled(true);
        CboCieCasInt.setEnabled(true);
        TxtDocCli.setEnabled(true);
        TxtNomCli.setEnabled(true);
        TxtEmaCli.setEnabled(true);
        TxtTelCli.setEnabled(true);
        TxtTelAltCli.setEnabled(true);
        BtnGua.setEnabled(true);
        BtnPdf.setEnabled(true);
    }

    public boolean validar() {

        if (CboTipConInt.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el tipo de contacto");
            CboTipConInt.grabFocus();
        } else if (CboCodTipInt.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar la tipificacion");
            CboCodTipInt.grabFocus();
        } else if (TxtMotInt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el motivo");
            TxtMotInt.grabFocus();
        } else if (TxtObsInt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar la observación");
            TxtObsInt.grabFocus();
        } else if (CboCieCasInt.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el cierre del caso");
            CboCieCasInt.grabFocus();
        } else {
            return true;
        }
        return false;
    }

    public void BuscarAsesor() {
        try {
            objClsInteraccion.setConCliInt(Integer.parseInt(TxtConCliInt.getText()));
            objClsInteraccion.BuscarAsesor();
            if (objClsInteraccion.DatosAsesor.next()) {
                TxtConAseInt.setText(objClsInteraccion.DatosAsesor.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la interfaz al buscar asesor: " + e);
        }
    }

    public void consecutivo() {
        objClsInteraccion.consecutivo();
        try {
            if (objClsInteraccion.DatosConsecutivo.next()) {
                TxtCodInt.setText(objClsInteraccion.DatosConsecutivo.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se puede generar el codigo: " + e);
        }
    }

    public void LlenarInformacionCliente() {
        try {
            objClsInteraccion.setIdUsu(FrmRegistroUsuario.DocumentoUsuario);
            objClsInteraccion.LlenarCliente();
            if (objClsInteraccion.DatosCliente.next() == true) {
                TxtConCliInt.setText(objClsInteraccion.DatosCliente.getString(1));
                TxtDocCli.setText(objClsInteraccion.DatosCliente.getString(2));
                TxtNomCli.setText(objClsInteraccion.DatosCliente.getString(3));
                TxtEmaCli.setText(objClsInteraccion.DatosCliente.getString(4));
                TxtTelCli.setText(objClsInteraccion.DatosCliente.getString(5));
                TxtTelAltCli.setText(objClsInteraccion.DatosCliente.getString(6));
            } else {
                JOptionPane.showMessageDialog(this, "No tienes clientes asignados");
                this.bloquear();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar los datos del cliente:  " + e);
        }
    }

    public void LlenarTipificaciones() {
        try {
            CboCodTipInt.removeAllItems();
            CboCodTipInt.addItem("Seleccione...");
            listaIdsTip.clear();
            objClsInteraccion.setConAseInt(Integer.parseInt(TxtConAseInt.getText()));
            objClsInteraccion.LlenarTipificaciones();
            while (objClsInteraccion.DatosTipificaciones.next() == true) {
                int id = objClsInteraccion.DatosTipificaciones.getInt(1);
                String nombre = objClsInteraccion.DatosTipificaciones.getString(2);
                CboCodTipInt.addItem(nombre);
                listaIdsTip.add(id);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar las tipificaciones:  " + e);
        }
    }

    public void LlenarMotivo() {
        try {
            objClsInteraccion.setConCliInt(Integer.parseInt(TxtConCliInt.getText()));
            objClsInteraccion.LlenarMotivo();
            if (objClsInteraccion.DatosMotivo.next() == true) {
                TxtMotInt.setText(objClsInteraccion.DatosMotivo.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar el motivo:  " + e);
        }
    }

    public void fecha() {
        Date FechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String CadenaFecha = formato.format(FechaActual);
        TxtFecInt.setText("" + CadenaFecha);
    }

    public void HoraInicio() {
        Date HoraInicioActual = new Date();
        SimpleDateFormat FormatoHoraInicio = new SimpleDateFormat("HH:mm:ss");
        String CadenaHoraInicio = FormatoHoraInicio.format(HoraInicioActual);
        TxtHorIniInt.setText("" + CadenaHoraInicio);
    }

    public void HoraFin() {
        Date HoraFinActual = new Date();
        SimpleDateFormat formatoHoraFin = new SimpleDateFormat("HH:mm:ss");
        String CadenaHoraFin = formatoHoraFin.format(HoraFinActual);
        TxtHorFinInt.setText("" + CadenaHoraFin);
    }

    public void nuevo() {
        this.bloquear();
        this.desbloquear();
        this.consecutivo();
        this.fecha();
        this.HoraInicio();
        this.LlenarInformacionCliente();
        this.BuscarAsesor();

        this.LlenarMotivo();
        this.LlenarTipificaciones();
        CboTipConInt.grabFocus();
    }

    public void guardar() {
        if (validar() == true) {
            if (CboCieCasInt.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar el cierre del caso");
                return;
            }
            String cierre = CboCieCasInt.getSelectedItem().toString();
            if (!cierre.equals("Cerrado") && objClsCaso == null) {
                JOptionPane.showMessageDialog(this, "Debe crear el caso antes de guardar la interacción");
                return;
            }
            this.HoraFin();
            this.capturar();
            objClsInteraccion.guardar();

            if (!cierre.equals("Cerrado") && objClsCaso != null) {
                objClsCaso.guardar();
                this.setObjClsCaso(null);
            }

            objClsInteraccion.ActualizarAsignacionLlamada();
            this.bloquear();
        }
    }

    public void regresar() {
        FrmIndex objFrmIdex = new FrmIndex();
        objFrmIdex.setVisible(true);
        this.dispose();
    }

    public void actualizar() {
        try {
            this.capturar();
            objClsInteraccion.ActualizarAsignacionLlamada();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fallas Tecnicas al actualizar: " + e);
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

        BtgRol = new javax.swing.ButtonGroup();
        BtnBus = new javax.swing.JButton();
        TxtConAseInt = new javax.swing.JTextField();
        TxtIdUsu = new javax.swing.JTextField();
        TxtConCliInt = new javax.swing.JTextField();
        TxtTipCod = new javax.swing.JTextField();
        TxtFecInt = new javax.swing.JTextField();
        TxtHorFinInt = new javax.swing.JTextField();
        TxtHorIniInt = new javax.swing.JTextField();
        TxtTieProInt = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        TxtCodInt = new javax.swing.JTextField();
        BtnGua = new javax.swing.JButton();
        BtnNue = new javax.swing.JButton();
        BtnRegr = new javax.swing.JButton();
        CboTipConInt = new javax.swing.JComboBox<>();
        CboCieCasInt = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtMotInt = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        TxtNomCli = new javax.swing.JTextField();
        TxtEmaCli = new javax.swing.JTextField();
        TxtTelCli = new javax.swing.JTextField();
        TxtTelAltCli = new javax.swing.JTextField();
        TxtDocCli = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TxtObsInt = new javax.swing.JTextArea();
        BtnPdf = new javax.swing.JButton();
        BtnCas = new javax.swing.JButton();
        CboCodTipInt = new javax.swing.JComboBox<>();

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

        TxtFecInt.setEditable(false);

        TxtHorFinInt.setEditable(false);

        TxtHorIniInt.setEditable(false);

        TxtTieProInt.setEditable(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Interaccion");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 51, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Interaccion");

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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cons.png"))); // NOI18N
        jLabel2.setText("Codigo");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/TipDoc.png"))); // NOI18N
        jLabel5.setText("Tipificaciones");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nom.png"))); // NOI18N
        jLabel6.setText("Tipo contacto");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/obs.png"))); // NOI18N
        jLabel7.setText("Motivo");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/obse.png"))); // NOI18N
        jLabel9.setText("Observacion");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cerr.png"))); // NOI18N
        jLabel10.setText("Cierre caso ");

        TxtCodInt.setEditable(false);
        TxtCodInt.setEnabled(false);

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

        CboTipConInt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Llamada", "Chat", "Correo" }));
        CboTipConInt.setEnabled(false);

        CboCieCasInt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Cerrado", "Abierto", "Escalado" }));
        CboCieCasInt.setEnabled(false);
        CboCieCasInt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboCieCasIntActionPerformed(evt);
            }
        });

        TxtMotInt.setColumns(20);
        TxtMotInt.setRows(5);
        TxtMotInt.setEnabled(false);
        jScrollPane1.setViewportView(TxtMotInt);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255), 10));

        jLabel11.setText("Nombre");

        jLabel12.setText("Email");

        jLabel13.setText("Telefono");

        jPanel4.setBackground(new java.awt.Color(0, 51, 255));
        jPanel4.setToolTipText("");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Datos cliente");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel14.setText("Telefono alternativo");

        TxtNomCli.setEditable(false);
        TxtNomCli.setEnabled(false);

        TxtEmaCli.setEditable(false);
        TxtEmaCli.setEnabled(false);

        TxtTelCli.setEditable(false);
        TxtTelCli.setEnabled(false);

        TxtTelAltCli.setEditable(false);
        TxtTelAltCli.setEnabled(false);

        TxtDocCli.setEditable(false);
        TxtDocCli.setEnabled(false);

        jLabel4.setText("Documento");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtTelAltCli, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(TxtDocCli, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(TxtNomCli, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(TxtEmaCli))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(TxtTelCli, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TxtDocCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(TxtNomCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(TxtEmaCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(TxtTelCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(TxtTelAltCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        TxtObsInt.setColumns(20);
        TxtObsInt.setRows(5);
        TxtObsInt.setEnabled(false);
        jScrollPane3.setViewportView(TxtObsInt);

        BtnPdf.setBackground(new java.awt.Color(255, 255, 255));
        BtnPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pdf.png"))); // NOI18N
        BtnPdf.setText("Ver protocolo");
        BtnPdf.setEnabled(false);
        BtnPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPdfActionPerformed(evt);
            }
        });

        BtnCas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/carp.png"))); // NOI18N
        BtnCas.setText("Abrir caso");
        BtnCas.setBorderPainted(false);
        BtnCas.setContentAreaFilled(false);
        BtnCas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCasActionPerformed(evt);
            }
        });

        CboCodTipInt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..." }));
        CboCodTipInt.setEnabled(false);
        CboCodTipInt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboCodTipIntActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(BtnNue)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnGua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 288, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtCodInt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CboTipConInt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CboCodTipInt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(50, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BtnPdf)
                                .addGap(98, 98, 98))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CboCieCasInt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnCas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnRegr)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(BtnPdf))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(TxtCodInt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(CboTipConInt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(CboCodTipInt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(CboCieCasInt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCas))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnGua, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BtnNue, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(17, 17, 17)
                .addComponent(BtnRegr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBusActionPerformed
        //this.buscar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBusActionPerformed

    private void BtnGuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuaActionPerformed
        this.guardar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGuaActionPerformed

    private void BtnNueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNueActionPerformed
        this.nuevo();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnNueActionPerformed

    private void BtnRegrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegrActionPerformed
        this.regresar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRegrActionPerformed

    private void BtnPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPdfActionPerformed
        try {
            objClsInteraccion.setConAseInt(Integer.parseInt(TxtConAseInt.getText()));
            objClsInteraccion.ObtenerRutaPdf();
            if (objClsInteraccion.DatosPdf.next()) {
                String ruta = objClsInteraccion.DatosPdf.getString(1);
                if (ruta.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Esta campaña no tiene protocolo asignado");
                    return;
                }
                File archivo = new File(ruta);
                if (!archivo.exists()) {
                    JOptionPane.showMessageDialog(null, "El archivo del protocolo no se encuentra");
                    return;
                }
                java.awt.Desktop.getDesktop().open(archivo);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró protocolo para esta campaña");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al abrir PDF: " + e);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPdfActionPerformed

    private void CboCieCasIntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboCieCasIntActionPerformed
        if (CboCieCasInt.getSelectedIndex() > 1) {
            CodigoInteraccion = Integer.parseInt(TxtCodInt.getText());
            BtnCas.setVisible(true);
        } else {
            BtnCas.setVisible(false);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_CboCieCasIntActionPerformed

    private void BtnCasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCasActionPerformed
        FrmCaso objFrmCaso = new FrmCaso();
        objFrmCaso.setLocationRelativeTo(this);
        objFrmCaso.setVisible(true);       // TODO add your handling code here:
    }//GEN-LAST:event_BtnCasActionPerformed

    private void CboCodTipIntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboCodTipIntActionPerformed
        if (CboCodTipInt.getSelectedIndex() > 0) {
            int idSeleccionado = listaIdsTip.get(CboCodTipInt.getSelectedIndex() - 1);
            TxtTipCod.setText(String.valueOf(idSeleccionado));
        } else {
            TxtTipCod.setText(null);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_CboCodTipIntActionPerformed

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
            java.util.logging.Logger.getLogger(FrmInteraccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmInteraccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmInteraccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmInteraccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmInteraccion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BtgRol;
    private javax.swing.JButton BtnBus;
    private javax.swing.JButton BtnCas;
    private javax.swing.JButton BtnGua;
    private javax.swing.JButton BtnNue;
    private javax.swing.JButton BtnPdf;
    private javax.swing.JButton BtnRegr;
    private javax.swing.JComboBox<String> CboCieCasInt;
    private javax.swing.JComboBox<String> CboCodTipInt;
    private javax.swing.JComboBox<String> CboTipConInt;
    private javax.swing.JTextField TxtCodInt;
    private javax.swing.JTextField TxtConAseInt;
    private javax.swing.JTextField TxtConCliInt;
    private javax.swing.JTextField TxtDocCli;
    private javax.swing.JTextField TxtEmaCli;
    private javax.swing.JTextField TxtFecInt;
    private javax.swing.JTextField TxtHorFinInt;
    private javax.swing.JTextField TxtHorIniInt;
    private javax.swing.JTextField TxtIdUsu;
    private javax.swing.JTextArea TxtMotInt;
    private javax.swing.JTextField TxtNomCli;
    private javax.swing.JTextArea TxtObsInt;
    private javax.swing.JTextField TxtTelAltCli;
    private javax.swing.JTextField TxtTelCli;
    private javax.swing.JTextField TxtTieProInt;
    private javax.swing.JTextField TxtTipCod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
