package com.example.aplication.historialview;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.internal.ComponentTracker.Location;
import com.vaadin.flow.component.map.Map;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.aplication.controller.HistorialInteractor;
import com.example.aplication.controller.HistorialInteractorImpl;
import com.example.aplication.data.*;
import com.example.aplication.inicio.MainLayout;


@Route(value = "historial", layout = MainLayout.class)
@PageTitle("Historial | Hospital CRM")
public class HistorialView  extends VerticalLayout implements HasUrlParameter<String>, HistorialViewModel, BeforeEnterObserver {
	
	private static final long serialVersionUID = 1L;
	
	Grid<Historial> grid = new Grid<>(Historial.class);
		
    TextField filterText = new TextField();
    HistorialForm form;
    String parametroRecibido;
    
    private static HistorialInteractor controlador;
    private List<Historial> elementos;
    private List<Medico> medicosRegistrados;
    
    
    public HistorialView() {
    	    	
        addClassName("list-view"); 
        setSizeFull();
        configureGrid(); 
        configureForm();
        this.medicosRegistrados=new ArrayList<>();
        HistorialView.controlador=new HistorialInteractorImpl(this);
        
        this.setElementos(new ArrayList<>());
        
       add(getContent()); 
       	
       controlador.consultarMedicos();
       closeEditor();
    }

    private void closeEditor() {
    	form.setHistorial(null);
        form.setVisible(true);
        removeClassName("editing");
		
	}
    
	
	
	private void configureForm() {
		
		
		System.out.println("configguracion formualrio "+parametroRecibido);
    	form = new HistorialForm(); 
    	form.setWidth("25em");
    	
	}
   
	private void configureGrid() {
        grid.addClassNames("historial-grid"); 
        grid.setSizeFull();
        grid.setColumns("paciente_id","fecha_cita", "motivo_cita", "diagnostico","proxima_cita","nombre_medico"); 
        grid.getColumns().forEach(col -> col.setAutoWidth(true)); 
    }
	
	  
    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form );
         
        content.setFlexGrow(1, form);
        content.setFlexGrow(2, grid);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }
    
    
    
   
    
    @Override
	public void mostrarHistorialEnGrid(List<Historial> items) {
		Collection<Historial> itemsCollections=items;
		grid.setItems(itemsCollections);
		setElementos(items);
	}


	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	public List<Historial> getElementos() {
		return elementos;
	}
    
	
	public void setElementos(List<Historial> elementos) {
		this.elementos = elementos;
	}
	
	
	@Override
	public void mostrarMensaje(String string) {
		Notification.show(string);
		
		
	}
	
	
	public static void nuevoHistorial(Historial historial) {
		try {
    			 		
	    	controlador.crearHistorial(historial);
	    	
	    	String dni=historial.getPaciente_id();
	    		    	
	    	controlador.consultarHistorial(dni);
	    	
	    		
	    	} catch (Exception ex) {
	            
	            ex.printStackTrace(); 
	            
	            Notification.show("Error al guardar el historial: " + ex.getMessage(), 3000, Notification.Position.TOP_CENTER);
	        }
		
		
		
	}

	@Override
	public void setParameter(BeforeEvent event, String parameter) {
		 parametroRecibido=(String.format("%s", parameter));
		 System.out.println("la identidad recibida es "+parametroRecibido);
		 form.setDniPacienteValue(parametroRecibido);
		 controlador.consultarHistorial(parametroRecibido);
		 
	}

	@Override
	public void llenarComboBoxMedicos(List<Medico> items) {
		form.nombreMedico.setItems(items);
        form.nombreMedico.setItemLabelGenerator(Medico::getNombre); 

		medicosRegistrados=items;
		
	}
	
	
}
	


