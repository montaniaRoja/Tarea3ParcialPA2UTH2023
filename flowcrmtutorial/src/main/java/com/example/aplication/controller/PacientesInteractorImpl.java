package com.example.aplication.controller;

import java.io.IOException;

import com.example.aplication.data.Paciente;
import com.example.aplication.data.PacientesResponse;
import com.example.aplication.model.DatabaseRepositoryImpl;
import com.example.aplication.pacientesview.PacientesViewModel;

public class PacientesInteractorImpl implements PacientesInteractor{
	
	
	private DatabaseRepositoryImpl modelo;
	private PacientesViewModel vista;
	
	
	public PacientesInteractorImpl(PacientesViewModel view) {
		
		super();
		this.vista=view;
		this.modelo= DatabaseRepositoryImpl.getInstance("https://apex.oracle.com",30000L);
		
		
		
	}
	
	@Override
	public void consultarPacientes() {
		try {
			this.modelo.consultarPacientes();
			PacientesResponse respuesta=this.modelo.consultarPacientes();
			this.vista.mostrarPacientesEnGrid(respuesta.getItems());
		}catch(IOException e) {
			
		}
		
		
	}

	@Override
	public void crearPacientes(Paciente nuevo) {
		try {
			this.modelo.crearPacientes(nuevo);
			boolean respuesta=this.modelo.crearPacientes(nuevo);
			if(respuesta) {
				this.vista.mostrarMensaje("Exito");
			}
			else {
				this.vista.mostrarMensaje("Fallo");
			}
			
		}catch(IOException e) {
			
		}
		
	}

	@Override
	public void actualizarPacientes(Paciente existente) {
		try {
			this.modelo.actualizarPacientes(existente);
			boolean respuesta=this.modelo.actualizarPacientes(existente);
			if(respuesta) {
				this.vista.mostrarMensaje("Paciente ha sido actualizado");
			}
			else {
				this.vista.mostrarMensaje("Fallo al actualizar el paciente");
			}
			
		}catch(IOException e) {
			
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarPacientes(String dni) {
		try {
			this.modelo.eliminarPacientes(dni);
			boolean respuesta=this.modelo.eliminarPacientes(dni);
			if(respuesta) {
				this.vista.mostrarMensaje("Datos han sido eliminados");
			}
			else {
				this.vista.mostrarMensaje("Fallo al eliminar datos del paciente");
			}
			
		}catch(IOException e) {
			
		}
		// TODO Auto-generated method stub
		
	}
	
	

}
