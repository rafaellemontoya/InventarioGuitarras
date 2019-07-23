/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controlador.Inventario;
import Controlador.Menu;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    private JLabel lbFabricanteBus, lbMaderaBus, lbTipoBus, lbModeloBus;
    private JTextField tfFabricante, tfMadera, tfTipo,tfModelo;
    private JButton btnBuscar;
    private FlowLayout lyBuscar;
    private JLabel lbTituloBuscar;
    private JList<String> listResBusqueda;
    
    private ArrayList<Guitarra> alGuitarras;
    private ArrayList<Modelo> alModelos;
    private ArrayList<Fabricante> alFabricantes;
    private ArrayList<Madera> alMaderas;
    private ArrayList<Tipo> alTipos;
    
    private String[]s;
    private Inventario inventario;
    
    
    
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
        
        
        //listGuitarras.add();
        //listGuitarras.setModel(viewModel);
        
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
        
        listResBusqueda = new JList<>();
        
        
        
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
        
        panelInventario.add(labelTitulo);
        panelInventario.add(labelTitulo3);
        panelInventario.add(listGuitarras);
        
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
        
        JLabel lbResultado = new JLabel();
        lbResultado.setText("Resultados de tu Búsqueda:");
        panelTituloRes.add(lbResultado);
        
        panelBuscar.setLayout(new GridLayout(5,2));
        
        panelBuscar.add(panelTituloBuscar);
        panelBuscar.add(panelCtnBuscar);
        panelBuscar.add(panelBtnBuscar);
        panelBuscar.add(panelTituloRes);
        panelBuscar.add(listResBusqueda);

        
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
        DefaultListModel dlm = new DefaultListModel();
    for(Guitarra p : alGuitarras ){
         dlm.addElement(p.getId()+" "+p.getEspecificaciones().getModelo().getNombre()+" "+p.getEspecificaciones().getFabricante().getNombre());
    }    
    if(dlm.isEmpty()){
        labelTitulo3.setText("Tu inventario está vacío");
    }else{
        labelTitulo3.setText("Tienes "+ dlm.getSize() +" elementos en tu inventario");
    }
        listGuitarras.setModel(dlm);
        
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

