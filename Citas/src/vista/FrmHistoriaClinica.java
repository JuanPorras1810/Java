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
import modelo.ClsHistoriaClinica;

/**
 *
 * @author Juan Porras
 */
public class FrmHistoriaClinica extends javax.swing.JFrame {

    /**
     * Creates new form FrmProfesional
     */
    public FrmHistoriaClinica() {
        initComponents();
    }
//Declaracion de variables
    ClsHistoriaClinica objClsHistoriaClinica = new ClsHistoriaClinica();

//Contenido tabla
    DefaultTableModel tabladatos;

//Metodos
    public void capturar() {
        objClsHistoriaClinica.setCodHisCli(Integer.parseInt(TxtCodHisCli.getText()));
        objClsHistoriaClinica.setCodAgenHisCli(Integer.parseInt(TxtCodAgenHisCli.getText()));
        objClsHistoriaClinica.setDocApreHisCli(TxtDocApreHisCli.getText());
        objClsHistoriaClinica.setDescHisCli(TxtdescHisCli.getText());
        objClsHistoriaClinica.setObserHisCli(TxtObserHisCli.getText());
        objClsHistoriaClinica.setMedHisCli(TxtMedHisCli.getText());
        objClsHistoriaClinica.setFechHoraHisCli(TxtFechHoraHisCli.getText());
    }

    public void FechaHora() {
        Date FechaHoraActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String CadenaFechaHora = formato.format(FechaHoraActual);
        TxtFechHoraHisCli.setText("" + CadenaFechaHora);
    }

    public void limpiar() {
        TxtCodHisCli.setText(null);
        TxtCodAgenHisCli.setText(null);
        TxtDocApreHisCli.setText(null);
        TxtdescHisCli.setText(null);
        TxtObserHisCli.setText(null);
        TxtMedHisCli.setText(null);
        TxtAprendiz.setText(null);
        TxtFechAgen.setText(null);
        TxtFechHoraHisCli.setText(null);
    }

    public void bloquear() {
        this.limpiar();
        TxtCodHisCli.setEnabled(false);
        TxtCodAgenHisCli.setEnabled(false);
        TxtDocApreHisCli.setEnabled(false);
        TxtdescHisCli.setEnabled(false);
        TxtObserHisCli.setEnabled(false);
        TxtMedHisCli.setEnabled(false);
        TxtFechHoraHisCli.setEnabled(false);
        BtnGua.setEnabled(false);
        BtnAct.setEnabled(false);
        BtnBusEst.setEnabled(false);
    }

    public void desbloquear() {
        TxtDocApreHisCli.setEnabled(true);
        TxtdescHisCli.setEnabled(true);
        TxtObserHisCli.setEnabled(true);
        TxtMedHisCli.setEnabled(true);
        TxtFechHoraHisCli.setEnabled(false);
        BtnGua.setEnabled(true);
        BtnBusEst.setEnabled(true);
    }

    public boolean validar() {
        if (TxtDocApreHisCli.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el documento del aprendiz");
            TxtDocApreHisCli.grabFocus();
        } else if (TxtDocApreHisCli.getText().length() > 11) {
            JOptionPane.showMessageDialog(null, "Error, el documento solo permite un maximo de 11 caracteres");
            TxtDocApreHisCli.grabFocus();
        } else if (TxtAprendiz.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe presionar el botón buscar estudiante");
            BtnBusEst.grabFocus();
        } else if (TxtdescHisCli.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar la descripcion");
            TxtdescHisCli.grabFocus();
        } else if (TxtdescHisCli.getText().length() > 300) {
            JOptionPane.showMessageDialog(null, "Error, la descripcion solo permite un maximo de 300 caracteres");
            TxtdescHisCli.grabFocus();
        } else if (TxtObserHisCli.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar la observacion");
            TxtObserHisCli.grabFocus();
        } else if (TxtObserHisCli.getText().length() > 300) {
            JOptionPane.showMessageDialog(null, "Error, la observacion solo permite un maximo de 300 caracteres");
            TxtObserHisCli.grabFocus();
        } else if (TxtMedHisCli.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el medicamento");
            TxtMedHisCli.grabFocus();
        } else if (TxtMedHisCli.getText().length() > 100) {
            JOptionPane.showMessageDialog(null, "Error, el medicamento solo permite un maximo de 100 caracteres");
            TxtMedHisCli.grabFocus();
        } else {
            return true;
        }
        return false;
    }

    public void ConsecutivoHistoriaClinica() {
        objClsHistoriaClinica.ConsecutivoHistoriaClinica();
        try {
            if (objClsHistoriaClinica.datosConsecutivo.next()) {
                TxtCodHisCli.setText(objClsHistoriaClinica.datosConsecutivo.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se puede generar el codigo: " + e);
        }

    }

    public void BuscarAprendiz() {
        try {
            String busApre = TxtDocApreHisCli.getText();
            objClsHistoriaClinica.setDocApreHisCli(busApre);
            objClsHistoriaClinica.BuscarAprendiz();
            if (objClsHistoriaClinica.datosAprendiz.next() == true) {
                String nombre = objClsHistoriaClinica.datosAprendiz.getString(3);
                String apellido = objClsHistoriaClinica.datosAprendiz.getString(4);
                TxtAprendiz.setText(nombre + " " + apellido);

            } else {
                TxtAprendiz.setText(null);
                TxtFechAgen.setText(null);
                TxtCodAgenHisCli.setText(null);
                JOptionPane.showMessageDialog(null, "No se encontro el nombre y apellido del aprendiz");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al visualizar el nombre y apellido del aprendiz" + e);
        }
    }

    public void BuscarAgendamiento() {
        try {
            String busAge = TxtDocApreHisCli.getText();
            objClsHistoriaClinica.setDocApreHisCli(busAge);
            objClsHistoriaClinica.buscarAgendamiento();
            while (objClsHistoriaClinica.datosAgendamiento.next() == true) {
                String fechAge = objClsHistoriaClinica.datosAgendamiento.getString(4);
                String codAge = objClsHistoriaClinica.datosAgendamiento.getString(1);
                String motAge = objClsHistoriaClinica.datosAgendamiento.getString(6);
                TxtFechAgen.setText(fechAge);
                TxtCodAgenHisCli.setText(codAge);
                TxtdescHisCli.setText(motAge);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al visualizar datos de agendamiento: " + e);
        }

    }

    public void nuevo() {
        this.bloquear();
        this.desbloquear();
        this.ConsecutivoHistoriaClinica();
        this.FechaHora();
        this.BorrarTabla();
        TxtDocApreHisCli.grabFocus();
    }

    public void guardar() {
        if (validar() == true) {
            this.capturar();
            objClsHistoriaClinica.guardar();
            this.bloquear();
        }
    }

    public void CrearTabla() {
        Object modelodata[][] = new Object[0][0];
        Object modelotitulos[] = {"Nombre", "Apellido", "Fecha", "Hora", "Descripcion", "Observacion", "Medicamentos"};
        tabladatos = new DefaultTableModel();
        tabladatos = new DefaultTableModel(modelodata, modelotitulos);
        this.TblRepCit.setModel(this.tabladatos);
    }

    public void BorrarTabla() {
        while (0 < this.TblRepCit.getRowCount()) {
            TblRepCit.setModel(new DefaultTableModel());
            this.CrearTabla();
        }
    }

    public void LlenarTabla() {
        try {
            this.BorrarTabla();
            String LlenTabl = JOptionPane.showInputDialog("Digite el documento del estudiante que desea consultar");
            objClsHistoriaClinica.setDocApreHisCli(LlenTabl);
            objClsHistoriaClinica.LlenarTabla();
            while (objClsHistoriaClinica.datosTabla.next() == true) {
                String Nombre = objClsHistoriaClinica.datosTabla.getString(1);
                String Apellido = objClsHistoriaClinica.datosTabla.getString(2);
                String Fecha = objClsHistoriaClinica.datosTabla.getString(3);
                String Hora = objClsHistoriaClinica.datosTabla.getString(4);
                String Descripsion = objClsHistoriaClinica.datosTabla.getString(5);
                String Observacion = objClsHistoriaClinica.datosTabla.getString(6);
                String Medicamentos = objClsHistoriaClinica.datosTabla.getString(7);
                Object fila[] = {Nombre, Apellido, Fecha, Hora, Descripsion, Observacion, Medicamentos};
                tabladatos.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla: " + e);
        }

    }

    public void buscar() {
        try {
            String bus = JOptionPane.showInputDialog("Digite el codigo de la historia clinica que desea buscar");
            int codigo = Integer.parseInt(bus);
            objClsHistoriaClinica.setCodHisCli(codigo);
            objClsHistoriaClinica.buscar();
            if (objClsHistoriaClinica.datosHisCli.next() == true) {
                TxtCodHisCli.setText(objClsHistoriaClinica.datosHisCli.getString(1));
                TxtCodAgenHisCli.setText(objClsHistoriaClinica.datosHisCli.getString(2));
                TxtDocApreHisCli.setText(objClsHistoriaClinica.datosHisCli.getString(3));
                TxtdescHisCli.setText(objClsHistoriaClinica.datosHisCli.getString(4));
                TxtObserHisCli.setText(objClsHistoriaClinica.datosHisCli.getString(5));
                TxtMedHisCli.setText(objClsHistoriaClinica.datosHisCli.getString(6));
                TxtFechHoraHisCli.setText(objClsHistoriaClinica.datosHisCli.getString(7));
                this.BuscarAprendiz();
                this.BuscarAgendamiento();
                this.desbloquear();
                BtnAct.setEnabled(true);
                BtnGua.setEnabled(false);
                BtnCon.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "La historia clinica no existe");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al visualizar los datos de la historia clinica: " + e);
        }
    }

    public void actualizar() {
        try {
            if (validar() == true) {
                this.capturar();
                objClsHistoriaClinica.actualizar();
                this.bloquear();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fallas Tecnicas al actualizar: " + e);
        }

    }

    public void regresar() {
        FrmIdex objFrmIdex = new FrmIdex();
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

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        TxtCodHisCli = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TxtAprendiz = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        BtnNue = new javax.swing.JButton();
        BtnGua = new javax.swing.JButton();
        BtnBus = new javax.swing.JButton();
        BtnAct = new javax.swing.JButton();
        BtnReg = new javax.swing.JButton();
        TxtDocApreHisCli = new javax.swing.JTextField();
        BtnBusEst = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TxtdescHisCli = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        TxtObserHisCli = new javax.swing.JTextArea();
        TxtMedHisCli = new javax.swing.JTextField();
        TxtCodAgenHisCli = new javax.swing.JTextField();
        jScrollPaneTabla = new javax.swing.JScrollPane();
        TblRepCit = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TxtFechAgen = new javax.swing.JTextField();
        BtnCon = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        TxtFechHoraHisCli = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Historia clinica");
        setResizable(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Codigo");

        TxtCodHisCli.setEnabled(false);

        jLabel2.setText("Documento aprendiz");

        jLabel3.setText("Descripcion");

        TxtAprendiz.setEnabled(false);

        jLabel4.setText("Observaciones");

        jLabel5.setText("Medicamentos");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Historia clinica");

        BtnNue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar-archivo.png"))); // NOI18N
        BtnNue.setToolTipText("Crea un nuevo formulario editable");
        BtnNue.setBorderPainted(false);
        BtnNue.setContentAreaFilled(false);
        BtnNue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNueActionPerformed(evt);
            }
        });

        BtnGua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sav.png"))); // NOI18N
        BtnGua.setToolTipText("Guarda la informacion");
        BtnGua.setBorderPainted(false);
        BtnGua.setContentAreaFilled(false);
        BtnGua.setEnabled(false);
        BtnGua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuaActionPerformed(evt);
            }
        });

        BtnBus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        BtnBus.setToolTipText("Busca la informacion guardada");
        BtnBus.setBorderPainted(false);
        BtnBus.setContentAreaFilled(false);
        BtnBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBusActionPerformed(evt);
            }
        });

        BtnAct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/archivo.png"))); // NOI18N
        BtnAct.setToolTipText("Actualiza datos guardados");
        BtnAct.setBorderPainted(false);
        BtnAct.setContentAreaFilled(false);
        BtnAct.setEnabled(false);
        BtnAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActActionPerformed(evt);
            }
        });

        BtnReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/deshacer.png"))); // NOI18N
        BtnReg.setToolTipText("Regresa al menu principal ");
        BtnReg.setBorderPainted(false);
        BtnReg.setContentAreaFilled(false);
        BtnReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegActionPerformed(evt);
            }
        });

        TxtDocApreHisCli.setEnabled(false);

        BtnBusEst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        BtnBusEst.setToolTipText("Busca los datos del aprendiz");
        BtnBusEst.setBorderPainted(false);
        BtnBusEst.setContentAreaFilled(false);
        BtnBusEst.setEnabled(false);
        BtnBusEst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBusEstActionPerformed(evt);
            }
        });

        TxtdescHisCli.setColumns(20);
        TxtdescHisCli.setRows(5);
        TxtdescHisCli.setEnabled(false);
        jScrollPane1.setViewportView(TxtdescHisCli);

        TxtObserHisCli.setColumns(20);
        TxtObserHisCli.setRows(5);
        TxtObserHisCli.setEnabled(false);
        jScrollPane2.setViewportView(TxtObserHisCli);

        TxtMedHisCli.setEnabled(false);

        TxtCodAgenHisCli.setEnabled(false);
        TxtCodAgenHisCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtCodAgenHisCliActionPerformed(evt);
            }
        });

        TblRepCit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido", "Fecha", "Hora", "Descripcion", "Observacion", "Medicamentos"
            }
        ));
        TblRepCit.setEnabled(false);
        jScrollPaneTabla.setViewportView(TblRepCit);

        jLabel6.setText("Nombre y apellido");

        jLabel7.setText("Fecha agendamiento");

        jLabel9.setText("Codigo agendamiento");

        TxtFechAgen.setEnabled(false);

        BtnCon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/entrevista.png"))); // NOI18N
        BtnCon.setToolTipText("Consulta las historias clinicas");
        BtnCon.setBorderPainted(false);
        BtnCon.setContentAreaFilled(false);
        BtnCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnConActionPerformed(evt);
            }
        });

        jLabel10.setText("Fecha y hora");

        TxtFechHoraHisCli.setEditable(false);
        TxtFechHoraHisCli.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneTabla)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtCodHisCli, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtDocApreHisCli, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addComponent(BtnBusEst, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtMedHisCli, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtFechHoraHisCli, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9)
                    .addComponent(TxtAprendiz)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(TxtFechAgen)
                    .addComponent(TxtCodAgenHisCli, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(97, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnNue, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnGua, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnBus, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnAct, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnCon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnReg, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(169, 169, 169))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtAprendiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtFechAgen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(BtnBusEst)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(TxtCodHisCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(TxtDocApreHisCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtCodAgenHisCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(TxtMedHisCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(TxtFechHoraHisCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnNue)
                    .addComponent(BtnGua)
                    .addComponent(BtnBus)
                    .addComponent(BtnAct)
                    .addComponent(BtnCon)
                    .addComponent(BtnReg))
                .addGap(32, 32, 32)
                .addComponent(jScrollPaneTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBusEstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBusEstActionPerformed
        this.BuscarAprendiz();
        this.BuscarAgendamiento();    // TODO add your handling code here:
    }//GEN-LAST:event_BtnBusEstActionPerformed

    private void BtnBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBusActionPerformed
        this.buscar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBusActionPerformed

    private void BtnActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActActionPerformed
        this.actualizar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnActActionPerformed

    private void BtnGuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuaActionPerformed
        this.guardar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGuaActionPerformed

    private void BtnNueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNueActionPerformed
        this.nuevo();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnNueActionPerformed

    private void BtnConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnConActionPerformed
        this.LlenarTabla();// TODO add your handling code here:
    }//GEN-LAST:event_BtnConActionPerformed

    private void TxtCodAgenHisCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtCodAgenHisCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtCodAgenHisCliActionPerformed

    private void BtnRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegActionPerformed
        this.regresar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRegActionPerformed

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
            java.util.logging.Logger.getLogger(FrmHistoriaClinica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmHistoriaClinica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmHistoriaClinica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmHistoriaClinica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmHistoriaClinica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAct;
    private javax.swing.JButton BtnBus;
    private javax.swing.JButton BtnBusEst;
    private javax.swing.JButton BtnCon;
    private javax.swing.JButton BtnGua;
    private javax.swing.JButton BtnNue;
    private javax.swing.JButton BtnReg;
    private javax.swing.JTable TblRepCit;
    private javax.swing.JTextField TxtAprendiz;
    private javax.swing.JTextField TxtCodAgenHisCli;
    private javax.swing.JTextField TxtCodHisCli;
    private javax.swing.JTextField TxtDocApreHisCli;
    private javax.swing.JTextField TxtFechAgen;
    private javax.swing.JTextField TxtFechHoraHisCli;
    private javax.swing.JTextField TxtMedHisCli;
    private javax.swing.JTextArea TxtObserHisCli;
    private javax.swing.JTextArea TxtdescHisCli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPaneTabla;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
