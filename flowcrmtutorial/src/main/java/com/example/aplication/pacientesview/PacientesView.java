package com.example.aplication.pacientesview;

import com.vaadin.flow.component.Component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

//import org.hibernate.mapping.Map;

import java.util.Map;
import java.time.LocalDate;
import java.util.ArrayList;

import com.example.aplication.controller.PacientesInteractor;
import com.example.aplication.controller.PacientesInteractorImpl;
import com.example.aplication.data.*;
import com.example.aplication.inicio.MainLayout;
import com.example.aplication.service.ReportGenerator;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
//import com.vaadin.flow.component.map.Map;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;


@Route(value = "pacientes", layout = MainLayout.class)
@PageTitle("Pacientes | Hospital CRM")
public class PacientesView  extends Div implements PacientesViewModel, BeforeEnterObserver {
	
	private static final long serialVersionUID = 1L;
	Grid<Paciente> grid = new Grid<>(Paciente.class);
	TextField filterText = new TextField();
    PacientForm form;
    private static PacientesInteractor controladorPac;
    private List<Paciente> elementos;
    private Span status;
    
    public PacientesView() {
        addClassName("list-view"); 
        setSizeFull();
        configureGrid(); 
        configureForm();
        controladorPac=new PacientesInteractorImpl(this);
        this.setElementos(new ArrayList<>());
        add(getToolbar(), getContent()); 
            
    
        controladorPac.consultarPacientes(); //ESTE ES PARA traer los pacientes desde oracle
        
        closeEditor();
    }

    private void closeEditor() {
    	form.setPaciente(null);
        form.setVisible(false);
        removeClassName("editing");
		
	}
    
    private void addPaciente() { 
        //grid.asSingleSelect().clear();
        form.firstName.clear();
        form.lastName.clear();
        form.datePicker.clear();
        form.setVisible(true);
        editPaciente(new Paciente());
        
       
        
    }	
    
   
	private void configureForm() {
    	form = new PacientForm(); 
        form.setWidth("35em");
        
		// TODO Auto-generated method stub
		
	}
	
	
	private void configureGrid() {
        grid.addClassNames("paciente-grid"); 
        grid.setSizeFull();
        grid.setColumns("dni","nombre", "apellido", "fecha","genero","direccion","telefono","responsable"); 
        
        grid.getColumns().forEach(col -> col.setAutoWidth(true)); 
        
        grid.asSingleSelect().addValueChangeListener(event ->
        editPaciente(event.getValue()));
        
        
    }
	
	

	public void editPaciente(Paciente paciente) { 
        if (paciente == null) {
            closeEditor();
        } else {
           //form.setPaciente(paciente);//aqui llenamos el formulario con los datos del paciente pero como no me funciono lo hice con todos los campos
           form.dNi.setValue(paciente.getDni());
           form.firstName.setValue(paciente.getNombre());
           form.lastName.setValue(paciente.getApellido());
           form.datePicker.setValue(LocalDate.parse(paciente.getFecha()));// estos campos se agregaron manualmente
           form.genero.setValue(paciente.getGenero());
           form.direccion.setValue(paciente.getDireccion());
           form.telefono.setValue(paciente.getTelefono());
           form.responsable.setValue(paciente.getResponsable());
           
           String dni=form.dNi.getValue();
           
           form.setVisible(true);
          
          form.update.addClickListener(e->{
        	Paciente existente=new Paciente();
        	existente.setDni(form.dNi.getValue());
  	   		existente.setNombre(form.firstName.getValue());
  	   		existente.setApellido(form.lastName.getValue());
  	   		existente.setFechaNac(form.datePicker.getValue().toString());
  	   		existente.setGenero(form.genero.getValue());
  	   		existente.setDireccion(form.direccion.getValue());
  	   		existente.setTelefono(form.telefono.getValue());
  	   		existente.setResponsable(form.responsable.getValue());
        	       	  
        	controladorPac.actualizarPacientes(existente);
        	controladorPac.consultarPacientes();
        	  
          });
          
          form.borrar.addClickListener(e->{
        	  
        	  
        	  controladorPac.eliminarPacientes(dni);
        	  controladorPac.consultarPacientes();
          });
          
          
          addClassName("editing");
         
        }
    }
	
	

	private HorizontalLayout getToolbar() {
       // filterText.setPlaceholder("Filtrar por nombre...");
       // filterText.setClearButtonVisible(true);
       // filterText.setValueChangeMode(ValueChangeMode.LAZY); 

        Button addPacienteButton = new Button("Agregar Paciente");
        addPacienteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addPacienteButton.addClickListener(click -> addPaciente());
        addPacienteButton.setId("addP");
        
        Button reporteButton=new Button("Imprimir Lista");
        reporteButton.addClickListener(e->generarReporte());
        reporteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        
        
        
        var toolbar = new HorizontalLayout(addPacienteButton, reporteButton); 
        toolbar.addClassName("toolbar"); 
        return toolbar;
    }
    
	
    
    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid,form );
         
        content.setFlexGrow(1, form);
        content.setFlexGrow(2, grid);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

	@Override
	public void mostrarPacientesEnGrid(List<Paciente> items) {
		Collection<Paciente> itemsCollections=items;
		grid.setItems(itemsCollections);
		setElementos(items);
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		// TODO Auto-generated method stub
		
	}

	public List<Paciente> getElementos() {
		return elementos;
	}

	public void setElementos(List<Paciente> elementos) {
		this.elementos = elementos;
	}

	@Override
	public void mostrarMensaje(String string) {
		Notification.show(string);
		// TODO Auto-generated method stub
		
	}

	public static void nuevoPaciente(Paciente paciente) {
		try {
    			 		
	    	controladorPac.crearPacientes(paciente);
	    	controladorPac.consultarPacientes();
	    		
	    	} catch (Exception ex) {
	            
	            ex.printStackTrace(); 
	            
	            Notification.show("Error al guardar el paciente: " + ex.getMessage(), 3000, Notification.Position.TOP_CENTER);
	        }
		
		
		
	}
	

    private void generarReporte() {
    	
    	ReportGenerator generator=new ReportGenerator();
    	PacientesReport datasource=new PacientesReport();
    	datasource.setPacientes(elementos);
    	
    	Map<String, Object> parametros=new HashMap<>();
		boolean generado=generator.generarReportePDF("reportepacientes", parametros, datasource);
		// TODO Auto-generated method stub
		
		if(generado) {
			String ubicacion=generator.getReportPath();
			Anchor url=new Anchor (ubicacion,"Abrir Reporte");
			url.setTarget("_blank");
			
			Notification notificacion=new Notification(url);
			notificacion.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
			notificacion.setDuration(10000);
			notificacion.open();
			
		}else {
			Notification.show("No se genero el reporte");
			
		}
		
		//
	}
	

	
}