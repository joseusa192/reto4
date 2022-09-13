package sistema_ticket;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
public class JF_Sistema_Ticket extends javax.swing.JFrame {
    DefaultTableModel modelo;
    String [] datos = new String[8];
    
    public JF_Sistema_Ticket() {
        initComponents();
        modelo=new DefaultTableModel();
        modelo.addColumn("id");
        modelo.addColumn("nit");
        modelo.addColumn("nombre_cliente");
        modelo.addColumn("direccion_cliente");
        modelo.addColumn("telefono_cliente");
        modelo.addColumn("fecha_servicio");
        modelo.addColumn("tipo_servicio");
        modelo.addColumn("comentario");
        tlbTicket.setModel(modelo);
        mostrar("");
    }
    
     void CargarTabla (String id){
        String sSQL="";
        Conexion_DB mysql=new Conexion_DB();
        Connection cn=mysql.conectar();
        sSQL="SELECT * FROM Ticket WHERE id LIKE '%"+id+"%'";
        try {
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            while(rs.next()){
                datos[1]=rs.getString("id");
                datos[1]=rs.getString("nit");
                datos[2]=rs.getString("nombre_cliente");
                datos[3]=rs.getString("direccion_cliente");
                datos[4]=rs.getString("telefono_cliente");
                datos[5]=rs.getString("fecha_servicio");
                datos[6]=rs.getString("tipo_servicio");
                datos[7]=rs.getString("comentario");
                modelo.addRow(datos);  
            }
            this.tlbTicket.setModel(modelo);           
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null,"Error "+e,"Error",JOptionPane.ERROR_MESSAGE);            
        }
    }
     
        public void mostrar(String nombre){
        DefaultTableModel modelo =new DefaultTableModel();
        modelo.addColumn("id");
        modelo.addColumn("nit");
        modelo.addColumn("nombre_cliente");
        modelo.addColumn("direccion_cliente");
        modelo.addColumn("telefono_cliente");
        modelo.addColumn("fecha_servicio");
        modelo.addColumn("tipo_servicio");
        modelo.addColumn("comentario");
        tlbTicket.setModel(modelo);
        String sSQL="";
        if (nombre.equals("")){
            sSQL="SELECT * FROM Ticket";
        }else{
           sSQL="SELECT * FROM Ticket WHERE nombre_cliente LIKE '%"+nombre+"%'";
        }
        String [] datos = new String[8];
        Conexion_DB mysql=new Conexion_DB();
        Connection cn=mysql.conectar();
        Statement st;
        try {
            st=cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                datos[6]=rs.getString(7);
                datos[7]=rs.getString(8);               
                modelo.addRow(datos);              
            }
            this.tlbTicket.setModel(modelo);            
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null,"Error "+e,"Error",JOptionPane.ERROR_MESSAGE);            
        }  
    }
        
        public void actualizar(){
        DefaultTableModel modelo =new DefaultTableModel();
        modelo.addColumn("id");
        modelo.addColumn("nit");
        modelo.addColumn("nombre_cliente");
        modelo.addColumn("direccion_cliente");
        modelo.addColumn("telefono_cliente");
        modelo.addColumn("fecha_servicio");
        modelo.addColumn("tipo_servicio");
        modelo.addColumn("comentario");
        tlbTicket.setModel(modelo);
        String sSQL="";
        sSQL="SELECT * FROM Ticket";       
        String [] datos = new String[8];
        Conexion_DB mysql=new Conexion_DB();
        Connection cn=mysql.conectar();
        Statement st;
        try {
            st=cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                datos[6]=rs.getString(7);
                datos[7]=rs.getString(8);
                modelo.addRow(datos);
            }
            this.tlbTicket.setModel(modelo);           
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null,"Error "+e,"Error",JOptionPane.ERROR_MESSAGE);        
        }  
    }
        
    public void modificar(){
        int fila=tlbTicket.getSelectedRow();
        if (fila>=0){   
            lblID.setText(tlbTicket.getValueAt(fila,0).toString());           
            txtNit.setText(tlbTicket.getValueAt(fila,1).toString());
            txtNombre.setText(tlbTicket.getValueAt(fila,2).toString());
            txtDireccion.setText(tlbTicket.getValueAt(fila,3).toString());
            txtTelefono.setText(tlbTicket.getValueAt(fila,4).toString());           
            txtFecha.setText(tlbTicket.getValueAt(fila,5).toString());
            cbxTipo.setSelectedItem(tlbTicket.getValueAt(fila,6).toString());
            txaComentario.setText(tlbTicket.getValueAt(fila,7).toString());
        }else{
            JOptionPane.showMessageDialog(null,"Seleccione Fila");
        }    
    }
    
    public void modificarDatos(){
        try {
            Conexion_DB mysql = new Conexion_DB();
            Connection cn = mysql.conectar();   
            String tipo=cbxTipo.getSelectedItem().toString();
            int fila=tlbTicket.getSelectedRow();
            PreparedStatement pst = cn.prepareStatement("UPDATE Ticket SET nit='"+txtNit.getText()+"',nombre_cliente='"+txtNombre.getText()+"',direccion_cliente='"+txtDireccion.getText()+"',telefono_cliente='"+txtTelefono.getText()+"',fecha_servicio='"+txtFecha.getText()+"',tipo_servicio='"+tipo+"',comentario='"+txaComentario.getText()+"' WHERE id='"+lblID.getText()+"'");
            pst.executeUpdate();
            mostrar("");    
            JOptionPane.showMessageDialog(null,"El registro se ha modificado ","Ingreso",JOptionPane.INFORMATION_MESSAGE);
          }catch (SQLException e) {
             JOptionPane.showMessageDialog(null,"Error no se pudo actualizar registro "+e,"Error",JOptionPane.ERROR_MESSAGE);
        }  
    }
    
    public void Borrar(){
        int fila=tlbTicket.getSelectedRow();
        String Id=tlbTicket.getValueAt(fila,0).toString();
        Conexion_DB mysql=new Conexion_DB();
        Connection cn=mysql.conectar();
        try {
            PreparedStatement pat=cn.prepareStatement("DELETE FROM Ticket WHERE id='"+Id+"'");
            pat.executeUpdate();
            mostrar("");
            JOptionPane.showMessageDialog(null,"El registro se ha eliminado ","Ingreso",JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error no se pudo bprrar regostro "+e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblSistema = new javax.swing.JLabel();
        pnlFormulario = new javax.swing.JPanel();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tlbTicket = new javax.swing.JTable();
        pnlTicket = new javax.swing.JPanel();
        lblNit = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblTipo_Servicio = new javax.swing.JLabel();
        lblComentario = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaComentario = new javax.swing.JTextArea();
        cbxTipo = new javax.swing.JComboBox<>();
        txtNit = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnPrueba = new javax.swing.JButton();
        lblID = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblSistema.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSistema.setText("SISTEMA TICKET");

        pnlFormulario.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        lblBuscar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblBuscar.setText("Buscar Cliente:");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(0, 153, 153));
        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tlbTicket.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        tlbTicket.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nit", "Nombre Cliente", "Direccion", "Telefono", "Fecha Servicio", "Tipo Servicio", "Comentario"
            }
        ));
        tlbTicket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tlbTicketMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tlbTicket);

        pnlTicket.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        lblNit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNit.setText("Nit:");

        lblNombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNombre.setText("Nombre Cliente:");

        lblDireccion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDireccion.setText("Direccion:");

        lblTelefono.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTelefono.setText("Telefono:");

        lblFecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFecha.setText("Fecha Servicio:");

        lblTipo_Servicio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTipo_Servicio.setText("Tipo Servicio");

        lblComentario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblComentario.setText("Comentario:");

        txaComentario.setColumns(20);
        txaComentario.setRows(5);
        jScrollPane2.setViewportView(txaComentario);

        cbxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Instalacion de Camaras", "Mantenimiento de PC", "Mantenimiento de Impresoras", "Limpieza de Virus" }));
        cbxTipo.setToolTipText("Seleccionar");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Modificar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnBorrar.setText("Cancelar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnPrueba.setText("Prueba");
        btnPrueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPruebaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTicketLayout = new javax.swing.GroupLayout(pnlTicket);
        pnlTicket.setLayout(pnlTicketLayout);
        pnlTicketLayout.setHorizontalGroup(
            pnlTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTicketLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblNit)
                        .addComponent(lblNombre)
                        .addGroup(pnlTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDireccion)
                            .addGroup(pnlTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblFecha)
                                .addComponent(lblTelefono))
                            .addGroup(pnlTicketLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(pnlTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblComentario)
                                    .addComponent(lblTipo_Servicio)))))
                    .addGroup(pnlTicketLayout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar)))
                .addGroup(pnlTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTicketLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNit, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxTipo, javax.swing.GroupLayout.Alignment.LEADING, 0, 223, Short.MAX_VALUE))
                        .addContainerGap(133, Short.MAX_VALUE))
                    .addGroup(pnlTicketLayout.createSequentialGroup()
                        .addGroup(pnlTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTicketLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar)
                                .addGap(18, 18, 18)
                                .addComponent(btnPrueba)
                                .addGap(8, 8, 8)
                                .addComponent(btnBorrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCerrar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTicketLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        pnlTicketLayout.setVerticalGroup(
            pnlTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTicketLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNit)
                    .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDireccion)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFecha)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipo_Servicio)
                    .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblComentario)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(pnlTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCerrar)
                    .addGroup(pnlTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregar)
                        .addComponent(btnLimpiar)
                        .addComponent(btnBorrar)
                        .addComponent(btnEliminar)
                        .addComponent(btnPrueba)))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlTicket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFormularioLayout.createSequentialGroup()
                        .addComponent(lblBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuscar)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTicket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSistema))
                    .addComponent(lblID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        Conexion_DB mysql = new Conexion_DB();
        Connection cn = mysql.conectar();
        String id,nit, nom, dir, tel, fec,tipo, comentario ="";        
        String sSQL="";
        id="";
        nit=txtNit.getText();
        nom=txtNombre.getText();
        dir=txtDireccion.getText();
        tel=txtTelefono.getText();
        fec=txtFecha.getText();
        tipo=cbxTipo.getSelectedItem().toString();
        comentario=txaComentario.getText();
        sSQL="INSERT INTO Ticket (nit, nombre_cliente, direccion_cliente, telefono_cliente, fecha_servicio, tipo_servicio, comentario)"+"VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1,nit);
            pst.setString(2,nom);
            pst.setString(3,dir);
            pst.setString(4,tel);
            pst.setString(5,fec);
            pst.setString(6,tipo);
            pst.setString(7,comentario);
            int n=pst.executeUpdate();
            if (n>0){
                JOptionPane.showMessageDialog(null,"El registro se ha ingresado ","Ingreso",JOptionPane.INFORMATION_MESSAGE);
                CargarTabla(id);
                actualizar();
                txtNit.setText("");
                txtNombre.setText("");
                txtDireccion.setText("");
                txtTelefono.setText("");
                txtFecha.setText("");
                cbxTipo.setSelectedIndex(0);  
                txaComentario.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error "+e,"Ingreso",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        mostrar(txtBuscar.getText());
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tlbTicketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tlbTicketMouseClicked
        modificar();
    }//GEN-LAST:event_tlbTicketMouseClicked

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        modificarDatos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        Borrar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        txtNit.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtFecha.setText("");
        cbxTipo.setSelectedIndex(0);  
        txaComentario.setText("");
        actualizar();       
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnPruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPruebaActionPerformed
        prueba_conexion dg = null;
        try {
            dg = new prueba_conexion();
        } catch (Exception ex) {
            Logger.getLogger(JF_Sistema_Ticket.class.getName()).log(Level.SEVERE, null, ex);
        }
        dg.setLocationRelativeTo(null);
        dg.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnPruebaActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        mostrar(txtBuscar.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JF_Sistema_Ticket().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnPrueba;
    private javax.swing.JComboBox<String> cbxTipo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblComentario;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblNit;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSistema;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTipo_Servicio;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlTicket;
    private javax.swing.JTable tlbTicket;
    private javax.swing.JTextArea txaComentario;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
