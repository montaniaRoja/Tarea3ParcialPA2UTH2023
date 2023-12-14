package com.example.aplication.medicosview;

import com.example.aplication.data.Medico;
import com.example.aplication.data.Paciente;
import com.example.aplication.pacientesview.PacientesView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

public class MedicoForm extends FormLayout{

	private static final long serialVersionUID = 1L;
	
	
	 TextField carnet = new TextField("Numero Carnet");
	  
	  TextField nombre = new TextField("Nombre"); 
	  TextField telefono = new TextField("Telefono");
	  TextField correo = new TextField("Correo");
	  TextField especialidad = new TextField("Especialidad");
	  	  
	  Button save = new Button("Save");
	  Button update = new Button("Update");
	  Button close = new Button("Cancel");
	  Button borrar=new Button("Delete");
	  
	  BeanValidationBinder<Medico> binder = new BeanValidationBinder<>(Medico.class);
	
	  public MedicoForm() {
		  
			carnet.setId("carnet");
			nombre.setId("nombre");
		    addClassName("medico-form");
		    binder.bindInstanceFields(this); 
		    
		    add(carnet,nombre,telefono,correo,especialidad,createButtonsLayout());
		    
		    
		  }
	  
	  private HorizontalLayout createButtonsLayout() {
		    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY); 
		    update.addThemeVariants(ButtonVariant.LUMO_ERROR);
		    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		    borrar.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
		    
		    save.addClickListener(e -> medicoCrear());
		       
		        
		    close.addClickListener(e->limpiarFormulario());
		    borrar.addClickListener(e -> { 
		    	
		    	agregarHistorial();
		    	
		    
		    });
		         
		    
		    
		    save.addClickShortcut(Key.ENTER); 
		    close.addClickShortcut(Key.ESCAPE);

		    return new HorizontalLayout(save, update, close, borrar); 
		  }
	  
	  private Object limpiarFormulario() {
		  
			
			 
			 this.carnet.clear();
			 this.nombre.clear();
			 this.telefono.clear();
			 this.correo.clear();
			 this.especialidad.clear();
			 this.setVisible(false);
			
			return null;
		}
	  
	  private void agregarHistorial() {
		  //String dni=dNi.getValue();
		 // UI.getCurrent().navigate("historial/"+dni);
		}


	  public void setMedico(Medico medico) {
		binder.setBean(medico);
		// TODO Auto-generated method stub
		
	}
	
	  public void medicoCrear() {
		  try {
	          
	          Medico medico=new Medico();
	   		
	   		medico.setCarnet(this.carnet.getValue());
	   		medico.setNombre(this.nombre.getValue());
	   		medico.setTelefono(this.telefono.getValue());
	   		medico.setCorreo(this.correo.getValue());
	   		medico.setEspecialidad(this.especialidad.getValue());
	   		
	        MedicosView.nuevoMedico(medico);
	        
	           
	       } catch (Exception ex) {
	           ex.printStackTrace();
	           Notification.show("Error al guardar el medico: " + ex.getMessage(), 3000, Notification.Position.TOP_CENTER);
	       }
	  }
	

}
