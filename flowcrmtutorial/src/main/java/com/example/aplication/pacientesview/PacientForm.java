package com.example.aplication.pacientesview;


import com.example.aplication.data.Paciente;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

public class PacientForm extends FormLayout { 
  private static final long serialVersionUID = 1L;
  
  //componentes del formulario
  
  TextField dNi = new TextField("Numero de Identidad");
  
  TextField firstName = new TextField("Nombre"); 
  TextField lastName = new TextField("Apellidos");
  DatePicker datePicker = new DatePicker("Fecha de Nacimiento");
  
  
  
  String generos[]= {"Masculino","Femenino"};
  ComboBox<String> genero = new ComboBox<>("Género");
  
  TextField direccion = new TextField("Direccion");
  TextField telefono = new TextField("Telefono"); 
  TextField responsable = new TextField("Responsable");
  
  Button save = new Button("Save");
  Button update = new Button("Update");
  Button close = new Button("Cancel");
  Button borrar = new Button("Delete");
  Button appmnt=new Button("Citas");
  
  
  BeanValidationBinder<Paciente> binder = new BeanValidationBinder<>(Paciente.class);

   
  public PacientForm() {
	  
	dNi.setId("dNi");
	appmnt.setId("citas");
	close.setId("cancelar");
	firstName.setId("nombre");
	lastName.setId("apellido");
	direccion.setId("direccion");
	
	
	
    addClassName("pacient-form");
    binder.bindInstanceFields(this); 
    genero.setItems(generos);
    add(dNi,firstName,lastName,datePicker,genero,direccion,telefono,responsable,createButtonsLayout());
    appmnt.setEnabled(false);
    dNi.addValueChangeListener(event -> {
       
        appmnt.setEnabled(!event.getValue().isBlank());
    });
    
    
  }

  private HorizontalLayout createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY); 
    update.addThemeVariants(ButtonVariant.LUMO_ERROR);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
    borrar.addThemeVariants(ButtonVariant.LUMO_ERROR);
    appmnt.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
    
    save.addClickListener(e -> pacienteCrear());
       
        
    close.addClickListener(e->limpiarFormulario());
    appmnt.addClickListener(e -> { 
    	
    	agregarHistorial();
    	
    
    });
         
    
    
    save.addClickShortcut(Key.ENTER); 
    close.addClickShortcut(Key.ESCAPE);

    return new HorizontalLayout(save, update, close, appmnt,borrar); 
  }
  
  
  private Object limpiarFormulario() {
		 
	  	this.dNi.clear();
	  	this.dNi.clear();
		this.firstName.clear();
		this.lastName.clear();
		this.direccion.clear();
		this.telefono.clear();
		this.responsable.clear();
		this.setVisible(false);
	
	return null;
}

  private void agregarHistorial() {
	  String dni=dNi.getValue();
	  UI.getCurrent().navigate("historial/"+dni);
	}




  public void setPaciente(Paciente paciente) {
	binder.setBean(paciente);
	// TODO Auto-generated method stub
	
}

  public void pacienteCrear() {
	  try {
		  
          	
			Paciente paciente=new Paciente();
	   		paciente.setDni(this.dNi.getValue());
	   		paciente.setNombre(this.firstName.getValue());
	   		paciente.setApellido(this.lastName.getValue());
	   		paciente.setFechaNac(this.datePicker.getValue().toString());
	   		paciente.setGenero(this.genero.getValue());
	   		paciente.setDireccion(this.direccion.getValue());
	   		paciente.setTelefono(this.telefono.getValue());
	   		paciente.setResponsable(this.responsable.getValue());
	   		
	   		PacientesView.nuevoPaciente(paciente);
	   		limpiarFormulario();
          	
	       } catch (Exception ex) {
	           ex.printStackTrace();
	           Notification.show("Error al guardar el paciente: " + ex.getMessage(), 3000, Notification.Position.TOP_CENTER);
	       }
	  }
  
}