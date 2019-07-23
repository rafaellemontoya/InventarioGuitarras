/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controlador.Inventario;
import DBManager.SqlFabricante;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelo.Especificacion;
import modelo.Fabricante;
import modelo.Guitarra;
import modelo.Madera;
import modelo.Modelo;
import modelo.Tipo;

/**
 *
 * @author Rafa
 */
public class UIPrincipal implements Runnable{
    JFrame frame ;
    private JTabbedPane pane;
    
    private JPanel mainPanel;
    
    private JPanel panelInventario;
    private JPanel panelAgregar;
    private JPanel panelBuscar;
    
    private JList<String> listGuitarras;
    private JTable tableGuitarras;
    private DefaultListModel viewModel;
    
    private JLabel labelTitulo;
    
    private JLabel labelTitulo3;
    //Etiquetas comunes
    private JLabel lbFabricante, lbMadera, lbTipo, lbModelo;
    //****tab agregar*****///
    private JComboBox fabricante, madera, tipo,modelo ;
    private JTextField tfPrecio;
    private JButton btnGuardar;
    private FlowLayout layout;
    private JLabel lbTituloAgregar;
    private JTextField costoNuevo;
    
        //****TAB BUSCAR*****///
    private JLabel lbFabricanteBus, lbMaderaBus, lbTipoBus, lbModeloBus,lbResultado;
    private JTextField tfFabricante, tfMadera, tfTipo,tfModelo;
    private JButton btnBuscar;
    private FlowLayout lyBuscar;
    private JLabel lbTituloBuscar;
    private JTable listResBusqueda;
    
    private ArrayList<Guitarra> alGuitarras;
    private ArrayList<Modelo> alModelos;
    private ArrayList<Fabricante> alFabricantes;
    private ArrayList<Madera> alMaderas;
    private ArrayList<Tipo> alTipos;
    
   
    private Inventario inventario;
    String col[] = {"Id","Fabricante","Modelo", "Madera", "Tipo", "Precio"};
    
    
    public UIPrincipal(Inventario inventario){
        this.inventario = inventario;
        
        
        this.alGuitarras = inventario.getAlGuitarras();
        this.alModelos = inventario.getAlModelos();
        this.alMaderas = inventario.getAlMadera();
        this.alTipos = inventario.getAlTipos();
        this.alFabricantes = inventario.getAlFabricantes();
        
        
        viewModel = new DefaultListModel<>();
        layout = new FlowLayout();
        labelTitulo = new JLabel();
        
        labelTitulo3 = new JLabel();
        
        costoNuevo = new JTextField();
        
        
  
        listGuitarras = new JList();
        
        tableGuitarras = new JTable();
        
        mainPanel = new JPanel();
        frame = new JFrame();
        pane = new JTabbedPane();
        
        //TAB AGREGAR
      
        fabricante = new JComboBox();
        madera = new JComboBox();
        tipo = new JComboBox();
        modelo  = new JComboBox();
        tfPrecio = new JTextField();
        
        lbTituloAgregar = new JLabel();
        lbFabricante = new JLabel();
        lbMadera = new JLabel();
        lbTipo = new JLabel();
        lbModelo = new JLabel();
        
        btnGuardar = new JButton();
        
        
        //TAB BUSCAR
        lbTituloBuscar = new JLabel();
        lbFabricanteBus = new JLabel();
        lbMaderaBus = new JLabel();
        lbTipoBus = new JLabel();
        lbModeloBus = new JLabel();
        
        tfFabricante = new JTextField();
        tfMadera = new JTextField();
        tfTipo = new JTextField();
        tfModelo = new JTextField();
        
        btnBuscar = new JButton();
        
        listResBusqueda = new JTable();
        lbResultado = new JLabel();
        
        
        mainPanel = new JPanel();
        panelInventario = new JPanel();
        panelAgregar = new JPanel();
        panelBuscar = new JPanel();
        loadInfo();
        refreshList();
    }


    @Override
    public void run() {
        
        /**TAB INVENTARIO**/
        
        panelInventario.setLayout(new GridLayout(3,1));
        JPanel panelTituloInv = new JPanel();
        
        labelTitulo.setText("Inventario");
        
        
        listGuitarras.add(labelTitulo);
        listGuitarras.add(labelTitulo);
        listGuitarras.add(labelTitulo);
        JScrollPane listScroller = new JScrollPane(tableGuitarras);
        
        listScroller.setPreferredSize(new Dimension(250, 80));
        
        panelInventario.add(labelTitulo);
        panelInventario.add(labelTitulo3);
        panelInventario.add(listScroller);
        
        //******TAB AGREGAR****/
        lbFabricante.setText("Fabricante");
        lbMadera.setText("Madera");
        lbTipo.setText("Tipo");
        lbModelo.setText("Modelo");
        lbTituloAgregar.setText("Agregar nueva guitarra");
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new AddButtonListener());
        
        JPanel panelTitulo = new JPanel();
        panelTitulo.setLayout(new FlowLayout());
        panelTitulo.add(lbTituloAgregar);
        
        JPanel panelPrecio = new JPanel();
        panelPrecio.setLayout(new GridLayout(1,2));
        
        
        panelPrecio.add(new Label("Precio: "));
        panelPrecio.add(costoNuevo);
        
        
        //GridLayout layout = new GridLayout(3,4);
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new GridLayout(2,4));
        
        panelContenido.add(lbFabricante);
        panelContenido.add(lbMadera);
        panelContenido.add(lbTipo);
        panelContenido.add(lbModelo);
        
        panelContenido.add(fabricante);
        panelContenido.add(madera);
        panelContenido.add(tipo);
        panelContenido.add(modelo);
        
        JPanel panelGuardar = new JPanel();
        panelGuardar.setLayout(new FlowLayout());
        panelGuardar.add(btnGuardar);
        
        panelAgregar.setLayout(new GridLayout(4,2));
        panelAgregar.add(panelTitulo);
        panelAgregar.add(panelPrecio);
        panelAgregar.add(panelContenido);
        panelAgregar.add(panelGuardar);
        //****** FIN TAB AGREGAR****/
        
        
        //******TAB BUSCAR****/
        lbFabricanteBus.setText("Fabricante");
        lbMaderaBus.setText("Madera");
        lbTipoBus.setText("Tipo");
        lbModeloBus.setText("Modelo");
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new BuscarButtonListener() );
        lbTituloBuscar.setText("Buscar guitarra");
        
        JPanel panelTituloBuscar = new JPanel();
        panelTituloBuscar.setLayout(new FlowLayout());
        panelTituloBuscar.add(lbTituloBuscar);
        
        JPanel panelCtnBuscar = new JPanel();
        panelCtnBuscar.setLayout(new GridLayout(2,4));
        
        panelCtnBuscar.add(lbFabricanteBus);
        panelCtnBuscar.add(lbMaderaBus);
        panelCtnBuscar.add(lbTipoBus);
        panelCtnBuscar.add(lbModeloBus);
        
        panelCtnBuscar.add(tfFabricante);
        panelCtnBuscar.add(tfMadera);
        panelCtnBuscar.add(tfTipo);
        panelCtnBuscar.add(tfModelo);
        
        JPanel panelBtnBuscar = new JPanel();
        panelBtnBuscar.setLayout(new FlowLayout());
        panelBtnBuscar.add(btnBuscar);
        
        JPanel panelTituloRes = new JPanel();
        panelTituloRes.setLayout(new FlowLayout());
        
        
        lbResultado.setText("Resultados de tu Búsqueda:");
        panelTituloRes.add(lbResultado);
        
        panelBuscar.setLayout(new GridLayout(5,2));
        
        listResBusqueda.getSelectionModel().addListSelectionListener(new ListListener());
        JScrollPane listScrollerBusqueda = new JScrollPane(listResBusqueda);
        
        listScrollerBusqueda.setPreferredSize(new Dimension(250, 80));
        
        panelBuscar.add(panelTituloBuscar);
        panelBuscar.add(panelCtnBuscar);
        panelBuscar.add(panelBtnBuscar);
        panelBuscar.add(panelTituloRes);
        panelBuscar.add(listScrollerBusqueda);

        
        /**TABS**/
        pane.setSize(800,300);
        pane.addTab("Inventario", panelInventario);
        pane.addTab("Agregar", panelAgregar);
        pane.addTab("Buscar", panelBuscar);

        
        //pane2.setTitleAt(0, "Titulo2");
        frame.getContentPane().add(pane);
        
        //frame.getContentPane().add(pane2);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,300);
        //frame.pack();
        frame.setVisible(true);
        
    }
    public void refreshList(){
        alGuitarras= inventario.getAlGuitarras();
        

        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
                                            // The 0 argument is number rows.

        
    for(Guitarra p : alGuitarras ){
        Object [] info = {p.getId(),p.getEspecificaciones().getFabricante().getNombre(),p.getEspecificaciones().getModelo().getNombre(),
            p.getEspecificaciones().getMadera().getNombre(),p.getEspecificaciones().getTipo().getNombre(),"$"+p.getPrecio()};
        tableModel.addRow(info);
                  
    }    
    if(alGuitarras.isEmpty()){
        labelTitulo3.setText("Tu inventario está vacío");
    }else{
        labelTitulo3.setText("Tienes "+ alGuitarras.size()+" elementos en tu inventario");
    }
        
        tableGuitarras.setModel(tableModel);
        
    }
    public void loadInfo(){
        
        for(Fabricante f : alFabricantes ){
         
                 
        fabricante.addItem(f.getNombre());
        }
        for(Madera m : alMaderas ){
         
                 
        madera.addItem(m.getNombre());
        }
         for(Modelo mod : alModelos ){
         
                 
        modelo.addItem(mod.getNombre());
        }  
        for(Tipo tip : alTipos ){
         
                 
        tipo.addItem(tip.getNombre());
        }  
                        
    }
    
    class BuscarButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Guitarra> alBusqueda = new ArrayList<>();
            DefaultTableModel tableModel = new DefaultTableModel(col, 0);
            if (tfFabricante.getText().length()!= 0){
                
                alBusqueda = inventario.buscarFabricante(new Fabricante(tfFabricante.getText(),""));
                
                
            }else if(tfMadera.getText().length()!=0){
                 alBusqueda = inventario.buscarMadera(new Madera(tfMadera.getText(),""));
                
            }else if(tfModelo.getText().length()!=0){
                 alBusqueda = inventario.buscarModelo(new Modelo(tfModelo.getText(),"",""));
               
                
            }else if(tfTipo.getText().length()!=0){
               alBusqueda = inventario.buscarTipo(new Tipo(tfTipo.getText(),"",""));
                
                
            }
            if (alBusqueda.isEmpty()){
                    lbResultado.setText("Resultados de tu Búsqueda: 0 coincidencias");
                }
            else{
                for(Guitarra p : alBusqueda ){
                     Object [] info = {p.getId(),p.getEspecificaciones().getFabricante().getNombre(),p.getEspecificaciones().getModelo().getNombre(),
                        p.getEspecificaciones().getMadera().getNombre(),p.getEspecificaciones().getTipo().getNombre(),"$"+p.getPrecio()};
                    tableModel.addRow(info);
                }
                lbResultado.setText("Resultados de tu Búsqueda: "+alBusqueda.size()+" coincidencias");
                listResBusqueda.setModel(tableModel);
            }
        }
        
    }
    
    class AddButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Fabricante f = alFabricantes.get(fabricante.getSelectedIndex());
            Madera m = alMaderas.get(madera.getSelectedIndex());
            Modelo mo = alModelos.get(modelo.getSelectedIndex());
            Tipo ti = alTipos.get(tipo.getSelectedIndex());
            
            Especificacion especificacion = new Especificacion(f,m,mo,ti);
            
            inventario.crearGuitarra(Float.valueOf( costoNuevo.getText()), especificacion);
            
            System.out.println(f.getNombre());
            refreshList();
            
        }
        
    }
    
    class ListListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            
        }
        
    }
    
    
}

