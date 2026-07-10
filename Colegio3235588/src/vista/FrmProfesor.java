/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ClsProfesor;

/**
 *
 * @author Juan Porras
 */
public class FrmProfesor extends javax.swing.JFrame {

    /**
     * Creates new form FrmEstudiante
     */
    public FrmProfesor() {
        initComponents();
    }
//Declaracion de variables
    ClsProfesor objClsProfesor = new ClsProfesor();

//Contenido tabla
    DefaultTableModel tabladatos;

//Metodos
    public void capturar() {
        objClsProfesor.setDocPro(TxtDocPro.getText());
        objClsProfesor.setNomPro(TxtNomPro.getText());
        objClsProfesor.setApePro(TxtApePro.getText());
        objClsProfesor.setDirPro(TxtDirPro.getText());
        objClsProfesor.setTelPro(TxtTelPro.getText());
        objClsProfesor.setEmaPro(TxtEmaPro.getText());
        objClsProfesor.setTitPro(CboTitPro.getSelectedItem().toString());
    }

    public void CapturarMateria() {
        objClsProfesor.setDocPro(TxtDocPro.getText());
        objClsProfesor.setCodMat(Integer.parseInt(TxtCodMatMatxPro.getText()));
        objClsProfesor.setGraMat(TxtGraMatxPro.getText());
        objClsProfesor.setNomMat(CboNomMatMatxPro.getSelectedItem().toString());
    }

    public void limpiar() {
        TxtDocPro.setText(null);
        TxtNomPro.setText(null);
        TxtApePro.setText(null);
        TxtDirPro.setText(null);
        TxtTelPro.setText(null);
        TxtEmaPro.setText(null);
        CboTitPro.setSelectedIndex(0);
        CboNomMatMatxPro.setSelectedIndex(0);

    }

    public void bloquear() {
        this.limpiar();
        TxtDocPro.setEnabled(false);
        TxtNomPro.setEnabled(false);
        TxtApePro.setEnabled(false);
        TxtDirPro.setEnabled(false);
        TxtTelPro.setEnabled(false);
        TxtEmaPro.setEnabled(false);
        CboTitPro.setEnabled(false);
        BtnAct.setEnabled(false);
        BtnAct2.setEnabled(false);
        BtnGua.setEnabled(false);
        BtnGua2.setEnabled(false);
        CboNomMatMatxPro.setEnabled(false);
    }

    public void desbloquear() {
        TxtDocPro.setEnabled(true);
        TxtNomPro.setEnabled(true);
        TxtApePro.setEnabled(true);
        TxtDirPro.setEnabled(true);
        TxtTelPro.setEnabled(true);
        TxtEmaPro.setEnabled(true);
        CboTitPro.setEnabled(true);
        BtnGua.setEnabled(true);

    }

    public boolean validar() {
        if (TxtDocPro.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el documento");
            TxtDocPro.grabFocus();
        } else if (TxtDocPro.getText().length() > 11) {
            JOptionPane.showMessageDialog(null, "Error, el documento solo permite un maximo de 11 caracteres");
            TxtDocPro.grabFocus();
        } else if (TxtNomPro.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el nombre");
            TxtNomPro.grabFocus();
        } else if (TxtNomPro.getText().length() > 30) {
            JOptionPane.showMessageDialog(null, "Error, el nombre solo permite un maximo de 30 caracteres");
            TxtNomPro.grabFocus();
        } else if (TxtApePro.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el apellido");
            TxtApePro.grabFocus();
        } else if (TxtApePro.getText().length() > 30) {
            JOptionPane.showMessageDialog(null, "Error, el apellido solo permite un maximo de 30 caracteres");
            TxtApePro.grabFocus();
        } else if (TxtDirPro.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar la direccion");
            TxtDirPro.grabFocus();
        } else if (TxtDirPro.getText().length() > 60) {
            JOptionPane.showMessageDialog(null, "Error, la direccion solo permite un maximo de 60 caracteres");
            TxtDirPro.grabFocus();
        } else if (TxtTelPro.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el numero de telefono");
            TxtTelPro.grabFocus();
        } else if (TxtTelPro.getText().length() > 11) {
            JOptionPane.showMessageDialog(null, "Error, el telefono solo permite un maximo de 11 caracteres");
            TxtTelPro.grabFocus();
        } else if (TxtEmaPro.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe digitar el email");
            TxtEmaPro.grabFocus();
        } else if (TxtEmaPro.getText().length() > 30) {
            JOptionPane.showMessageDialog(null, "Error, el email solo permite un maximo de 30 caracteres");
            TxtEmaPro.grabFocus();
        } else if (CboTitPro.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe elegir el titulo");
            CboTitPro.grabFocus();
        } else {
            return true;
        }
        return false;
    }

    public void guardar() {
        if (validar() == true) {
            this.capturar();
            objClsProfesor.guardar();
            this.bloquear();
        }

    }

    public void LlenarComboNomMat() {
        CboNomMatMatxPro.removeAllItems();
        CboNomMatMatxPro.addItem("Seleccione...");
        objClsProfesor.LlenarComboNomMat();
        try {
            while (objClsProfesor.datosMateria.next() == true) {
                CboNomMatMatxPro.addItem(objClsProfesor.datosMateria.getString(2));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo generar el nombre de las materias" + e);
        }

    }

    public void LlenarGrado() {
        if (CboNomMatMatxPro.getSelectedItem() == null) {
            return;
        }

        if (CboNomMatMatxPro.getSelectedItem() == ("Seleccione...")) {
            TxtGraMatxPro.setText(null);
        } else {
            try {
                String bus = CboNomMatMatxPro.getSelectedItem().toString();
                objClsProfesor.setNomMat(bus);
                objClsProfesor.LlenarGrado();
                if (objClsProfesor.datosGrado.next() == true) {
                    String codigo = objClsProfesor.datosGrado.getString(1);
                    String grado = objClsProfesor.datosGrado.getString(3);
                    TxtCodMatMatxPro.setText(codigo);
                    TxtGraMatxPro.setText(grado);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro el grado");
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al visualizar los datos del grado: " + e);
            }
        }
    }

    public boolean ValidarMateria() {
        if (CboNomMatMatxPro.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe elegir un nombre de materia");
            TxtDocPro.grabFocus();
        } else {
            return true;
        }
        return false;
    }

    public void GuardarMateria() {
        if (ValidarMateria() == true) {
            this.CapturarMateria();
            objClsProfesor.GuardarMateria();
            this.LlenarTabla();
            TxtGraMatxPro.setText(null);
            CboNomMatMatxPro.setSelectedIndex(0);

        }
    }

    public void CrearTabla() {
        Object modelodata[][] = new Object[0][0];
        Object modelotitulos[] = {"Nombre", "Grado",};
        tabladatos = new DefaultTableModel();
        tabladatos = new DefaultTableModel(modelodata, modelotitulos);
        this.TblMat.setModel(this.tabladatos);
    }

    public void BorrarTabla() {
        while (0 < this.TblMat.getRowCount()) {
            TblMat.setModel(new DefaultTableModel());
            this.CrearTabla();
        }
    }

    public void LlenarTabla() {
        try {
            this.BorrarTabla();
            objClsProfesor.setDocPro(TxtDocPro.getText());
            objClsProfesor.LlenarTabla();
            while (objClsProfesor.datosTabla.next() == true) {
                String Nombre = objClsProfesor.datosTabla.getString(1);
                String Grado = objClsProfesor.datosTabla.getString(2);

                Object fila[] = {Nombre, Grado};
                tabladatos.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al llenar la tabla: " + e);
        }

    }

    public void buscar() {
        try {
            String bus = JOptionPane.showInputDialog("Digite el documento que desea buscar");
            objClsProfesor.setDocPro(bus);
            objClsProfesor.buscar();
            if (objClsProfesor.datosProfesor.next() == true) {
                TxtDocPro.setText(objClsProfesor.datosProfesor.getString(1));
                TxtNomPro.setText(objClsProfesor.datosProfesor.getString(2));
                TxtApePro.setText(objClsProfesor.datosProfesor.getString(3));
                TxtDirPro.setText(objClsProfesor.datosProfesor.getString(4));
                TxtTelPro.setText(objClsProfesor.datosProfesor.getString(5));
                TxtEmaPro.setText(objClsProfesor.datosProfesor.getString(6));
                CboTitPro.setSelectedItem(objClsProfesor.datosProfesor.getString(7));
                this.LlenarComboNomMat();
                this.desbloquear();
                this.LlenarTabla();
                TxtDocPro.setEnabled(false);
                BtnGua.setEnabled(false);
                BtnAct.setEnabled(true);
                BtnGua2.setEnabled(true);
                CboNomMatMatxPro.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "El profesor no existe");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al visualizar los datos del profesor: " + e);
        }

    }

    public void BuscarMateria() {
        try {
            String BusMat = JOptionPane.showInputDialog("Digite el codigo de la materia");
            objClsProfesor.setConMatxPro(BusMat);
            objClsProfesor.BuscarMateria();
            if (objClsProfesor.datosMateriaXprofesor.next() == true) {
                this.LlenarComboNomMat();
                TxtCodMatMatxPro.setText(objClsProfesor.datosMateriaXprofesor.getString(2));
                TxtDocPro.setText(objClsProfesor.datosMateriaXprofesor.getString(3));
                TxtGraMatxPro.setText(objClsProfesor.datosMateriaXprofesor.getString(4));
                CboNomMatMatxPro.setSelectedItem(objClsProfesor.datosMateriaXprofesor.getString(5));
                this.desbloquear();
                TxtDocPro.setEnabled(false);
                BtnGua.setEnabled(false);
                BtnAct.setEnabled(true);
                BtnAct2.setEnabled(true);
                BtnGua2.setEnabled(true);
                CboNomMatMatxPro.setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(null, "La materia no existe");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al visualizar los datos de la materia: " + e);
        }

    }

    public void actualizar() {
        try {
            if (validar() == true) {
                this.capturar();
                objClsProfesor.actualizar();
                this.bloquear();

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fallas Tecnicas: " + e);
        }

    }

    public void ActualizarMateria() {
        try {
            if (ValidarMateria() == true) {
                this.CapturarMateria();
                objClsProfesor.ActualizarMateria();
                this.bloquear();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fallas Tecnicas: " + e);
        }

    }

    public void nuevo() {
        this.bloquear();
        this.desbloquear();
        this.limpiar();
        jTabbedPane1.setSelectedIndex(0);
        TxtDocPro.grabFocus();
    }

    public void regresar() {
        FrmIndex objFrmIndex = new FrmIndex();
        objFrmIndex.setVisible(true);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        TxtCodMatMatxPro = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        PanelProfesor = new javax.swing.JPanel();
        TxtApePro = new javax.swing.JTextField();
        BtnReg = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TxtDirPro = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TxtEmaPro = new javax.swing.JTextField();
        TxtTelPro = new javax.swing.JTextField();
        BtnNue = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        BtnGua = new javax.swing.JButton();
        TxtDocPro = new javax.swing.JTextField();
        BtnBus = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        BtnMod = new javax.swing.JButton();
        TxtNomPro = new javax.swing.JTextField();
        BtnAct = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        BtnImp = new javax.swing.JButton();
        CboTitPro = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        PanelMateriaProfesor = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        TxtGraMatxPro = new javax.swing.JTextField();
        BtnNue2 = new javax.swing.JButton();
        BtnGua2 = new javax.swing.JButton();
        BtnBus2 = new javax.swing.JButton();
        BtnImp2 = new javax.swing.JButton();
        BtnReg2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TblMat = new javax.swing.JTable();
        CboNomMatMatxPro = new javax.swing.JComboBox<>();
        BtnAct2 = new javax.swing.JButton();

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
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Profesor");
        setResizable(false);

        PanelProfesor.setPreferredSize(new java.awt.Dimension(558, 600));

        TxtApePro.setEnabled(false);

        BtnReg.setText("Regresar");
        BtnReg.setToolTipText("Regresa al menu principal ");
        BtnReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Direccion");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Profesor");

        TxtDirPro.setEnabled(false);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Telefono");

        TxtEmaPro.setEnabled(false);

        TxtTelPro.setEnabled(false);

        BtnNue.setText("Nuevo");
        BtnNue.setToolTipText("Limpia las cajas de texto del formulario");
        BtnNue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNueActionPerformed(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Titulo");

        jLabel1.setText("Documento");

        BtnGua.setText("Guardar");
        BtnGua.setToolTipText("Guarda la informacion en la base de datos");
        BtnGua.setEnabled(false);
        BtnGua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuaActionPerformed(evt);
            }
        });

        TxtDocPro.setEnabled(false);
        TxtDocPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtDocProActionPerformed(evt);
            }
        });

        BtnBus.setText("Buscar");
        BtnBus.setToolTipText("Busca la informacion en la base de datos");
        BtnBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBusActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nombre");

        BtnMod.setText("Modificar");
        BtnMod.setToolTipText("Modifica datos ya guardados");
        BtnMod.setEnabled(false);

        TxtNomPro.setEnabled(false);

        BtnAct.setText("Actualizar");
        BtnAct.setToolTipText("Actualiza los datos que ya estan guardados");
        BtnAct.setEnabled(false);
        BtnAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Apellido");

        BtnImp.setText("Imprimir");
        BtnImp.setEnabled(false);

        CboTitPro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Tecnico", "Tecnologo", "Profesional", "Especialista", "Magister", "Doctor" }));
        CboTitPro.setEnabled(false);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Email");

        javax.swing.GroupLayout PanelProfesorLayout = new javax.swing.GroupLayout(PanelProfesor);
        PanelProfesor.setLayout(PanelProfesorLayout);
        PanelProfesorLayout.setHorizontalGroup(
            PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelProfesorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnNue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnGua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnBus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnMod)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnAct)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnImp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnReg)
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(PanelProfesorLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelProfesorLayout.createSequentialGroup()
                        .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelProfesorLayout.createSequentialGroup()
                                .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TxtApePro)
                                    .addComponent(TxtDirPro)
                                    .addComponent(CboTitPro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TxtDocPro)
                                    .addComponent(TxtNomPro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6))
                            .addGroup(PanelProfesorLayout.createSequentialGroup()
                                .addComponent(TxtTelPro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(116, 116, 116))
                    .addGroup(PanelProfesorLayout.createSequentialGroup()
                        .addComponent(TxtEmaPro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        PanelProfesorLayout.setVerticalGroup(
            PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelProfesorLayout.createSequentialGroup()
                .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelProfesorLayout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabel6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelProfesorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(TxtDocPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(TxtNomPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(TxtApePro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtDirPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(15, 15, 15)
                        .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtTelPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtEmaPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(15, 15, 15)
                        .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(CboTitPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnNue)
                    .addComponent(BtnGua)
                    .addComponent(BtnBus)
                    .addComponent(BtnMod)
                    .addComponent(BtnAct)
                    .addComponent(BtnImp)
                    .addComponent(BtnReg))
                .addGap(39, 39, 39))
        );

        jTabbedPane1.addTab("Profesor", PanelProfesor);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Materia");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Grado");

        TxtGraMatxPro.setEnabled(false);

        BtnNue2.setText("Nuevo");
        BtnNue2.setToolTipText("Limpia las cajas de texto del formulario");
        BtnNue2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNue2ActionPerformed(evt);
            }
        });

        BtnGua2.setText("Guardar");
        BtnGua2.setEnabled(false);
        BtnGua2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGua2ActionPerformed(evt);
            }
        });

        BtnBus2.setText("Buscar");
        BtnBus2.setToolTipText("Busca la informacion en la base de datos");
        BtnBus2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBus2ActionPerformed(evt);
            }
        });

        BtnImp2.setText("Imprimir");
        BtnImp2.setEnabled(false);

        BtnReg2.setText("Regresar");
        BtnReg2.setToolTipText("Regresa al menu principal ");
        BtnReg2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnReg2ActionPerformed(evt);
            }
        });

        TblMat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nombre", "Grado"
            }
        ));
        jScrollPane3.setViewportView(TblMat);

        CboNomMatMatxPro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..." }));
        CboNomMatMatxPro.setEnabled(false);
        CboNomMatMatxPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboNomMatMatxProActionPerformed(evt);
            }
        });

        BtnAct2.setText("Actualizar");
        BtnAct2.setToolTipText("Actualiza los datos que ya estan guardados");
        BtnAct2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAct2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelMateriaProfesorLayout = new javax.swing.GroupLayout(PanelMateriaProfesor);
        PanelMateriaProfesor.setLayout(PanelMateriaProfesorLayout);
        PanelMateriaProfesorLayout.setHorizontalGroup(
            PanelMateriaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMateriaProfesorLayout.createSequentialGroup()
                .addGroup(PanelMateriaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelMateriaProfesorLayout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addGroup(PanelMateriaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PanelMateriaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TxtGraMatxPro, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(CboNomMatMatxPro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(PanelMateriaProfesorLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(PanelMateriaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelMateriaProfesorLayout.createSequentialGroup()
                                .addComponent(BtnNue2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnGua2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnBus2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnImp2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BtnAct2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnReg2))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        PanelMateriaProfesorLayout.setVerticalGroup(
            PanelMateriaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMateriaProfesorLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(PanelMateriaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(CboNomMatMatxPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(PanelMateriaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(TxtGraMatxPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(PanelMateriaProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnBus2)
                    .addComponent(BtnGua2)
                    .addComponent(BtnNue2)
                    .addComponent(BtnImp2)
                    .addComponent(BtnReg2)
                    .addComponent(BtnAct2))
                .addGap(63, 63, 63)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Materia profesor", PanelMateriaProfesor);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtDocProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtDocProActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtDocProActionPerformed

    private void BtnRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegActionPerformed
        this.regresar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRegActionPerformed

    private void BtnNueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNueActionPerformed
        this.nuevo();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnNueActionPerformed

    private void BtnNue2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNue2ActionPerformed
        this.nuevo();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnNue2ActionPerformed

    private void BtnReg2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReg2ActionPerformed
        this.regresar();      // TODO add your handling code here:
    }//GEN-LAST:event_BtnReg2ActionPerformed

    private void BtnGuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuaActionPerformed
        this.guardar();// TODO add your handling code here:
    }//GEN-LAST:event_BtnGuaActionPerformed

    private void BtnBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBusActionPerformed
        this.buscar();
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBusActionPerformed

    private void BtnActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActActionPerformed
        this.actualizar();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnActActionPerformed

    private void CboNomMatMatxProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboNomMatMatxProActionPerformed
        this.LlenarGrado();     // TODO add your handling code here:
    }//GEN-LAST:event_CboNomMatMatxProActionPerformed

    private void BtnGua2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGua2ActionPerformed
        this.GuardarMateria();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGua2ActionPerformed

    private void BtnBus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBus2ActionPerformed
        this.BuscarMateria();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBus2ActionPerformed

    private void BtnAct2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAct2ActionPerformed
        this.ActualizarMateria();        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAct2ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmProfesor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAct;
    private javax.swing.JButton BtnAct2;
    private javax.swing.JButton BtnBus;
    private javax.swing.JButton BtnBus2;
    private javax.swing.JButton BtnGua;
    private javax.swing.JButton BtnGua2;
    private javax.swing.JButton BtnImp;
    private javax.swing.JButton BtnImp2;
    private javax.swing.JButton BtnMod;
    private javax.swing.JButton BtnNue;
    private javax.swing.JButton BtnNue2;
    private javax.swing.JButton BtnReg;
    private javax.swing.JButton BtnReg2;
    private javax.swing.JComboBox<String> CboNomMatMatxPro;
    private javax.swing.JComboBox<String> CboTitPro;
    private javax.swing.JPanel PanelMateriaProfesor;
    private javax.swing.JPanel PanelProfesor;
    private javax.swing.JTable TblMat;
    private javax.swing.JTextField TxtApePro;
    private javax.swing.JTextField TxtCodMatMatxPro;
    private javax.swing.JTextField TxtDirPro;
    private javax.swing.JTextField TxtDocPro;
    private javax.swing.JTextField TxtEmaPro;
    private javax.swing.JTextField TxtGraMatxPro;
    private javax.swing.JTextField TxtNomPro;
    private javax.swing.JTextField TxtTelPro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
