package com.example.aplication.historialview;


import java.time.LocalDate;

import com.example.aplication.data.Historial;
import com.example.aplication.data.Medico;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;

public class HistorialForm extends FormLayout implements HasUrlParameter<String> { 
  private static final long serialVersionUID = 1L;
  TextField dniPaciente;
  DatePicker dateDate = new DatePicker("Fecha Cita");
  TextField motivoDate = new TextField("Motivo Cita"); 
  TextField diagnos = new TextField("Diagnostico");
  DatePicker dateNext = new DatePicker("Proxima Cita");
  
  ComboBox<Medico> nombreMedico=new ComboBox<Medico>("Medico Atiende");
  
  Button save = new Button("Save");
  
  Button close = new Button("Pacientes");
  
  BeanValidationBinder<Historial> binder = new BeanValidationBinder<>(Historial.class);

  public HistorialForm() {
	  close.setId("close");
	  dniPaciente=new TextField("DNI Paciente");
	  addClassName("historial-form"); 
    
	  add(dniPaciente, dateDate,motivoDate,diagnos,dateNext,nombreMedico,createButtonsLayout());
    
    
  }

  private HorizontalLayout createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY); 
    
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
    
    save.addClickListener(e->crearHistorial());
    
    close.addClickListener(e-> UI.getCurrent().navigate("pacientes/"));
    
    save.addClickShortcut(Key.ENTER); 
    close.addClickShortcut(Key.ESCAPE);

    return new HorizontalLayout(save, close); 
  }

private Object crearHistorial() {
	
	Historial historial=new Historial();
	LocalDate fechaCita = dateDate.getValue();
	historial.setFecha_cita(fechaCita.toString());
	historial.setMotivo_cita(motivoDate.getValue());
	historial.setDiagnostico(diagnos.getValue());
	LocalDate fechaSeleccionada = dateNext.getValue();
	historial.setProxima_cita(fechaSeleccionada.toString());
	historial.setPaciente_id(dniPaciente.getValue());
	nombreMedico.setItemLabelGenerator(Medico::getNombre);
	Medico medicoSeleccionado = nombreMedico.getValue();
	String nombreDr = medicoSeleccionado.getNombre();
	System.out.println("el dr se llama  "+nombreDr);
	historial.setNombre_medico(nombreDr);
	
	HistorialView.nuevoHistorial(historial);
	
	this.dniPaciente.clear();
	this.dateDate.clear();
	this.dateNext.clear();
	this.diagnos.clear();
	this.motivoDate.clear();
	this.nombreMedico.clear();
	this.setVisible(false);
	 System.out.println("Historial creado exitosamente.");
	
	return null;
}

public void setHistorial(Object object) {
	// TODO Auto-generated method stub
	
}



@Override
public void setParameter(BeforeEvent event, String parameter) {
	System.out.println(parameter);
	this.dniPaciente.setValue(parameter);
	
}

public void setDniPacienteValue(String parametroRecibido) {
	this.dniPaciente.setValue(parametroRecibido);
	// TODO Auto-generated method stub
	
}

    
  
}