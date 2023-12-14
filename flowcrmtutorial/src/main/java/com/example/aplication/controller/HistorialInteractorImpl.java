package com.example.aplication.controller;

import java.io.IOException;

import com.example.aplication.data.Historial;
import com.example.aplication.data.HistorialResponse;
import com.example.aplication.data.MedicoResponse;
import com.example.aplication.historialview.HistorialView;
import com.example.aplication.historialview.HistorialViewModel;
import com.example.aplication.model.DatabaseRepositoryImpl;

public class HistorialInteractorImpl implements HistorialInteractor {
	
	private DatabaseRepositoryImpl modelo;
	private HistorialViewModel vista;

	public HistorialInteractorImpl(HistorialView historialView) {
		super();
		this.vista=historialView;
		this.modelo= DatabaseRepositoryImpl.getInstance("https://apex.oracle.com",30000L);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void consultarHistorial(String dni) {
		try {
			this.modelo.consultarHistorial(dni);
			HistorialResponse respuesta=this.modelo.consultarHistorial(dni);
			this.vista.mostrarHistorialEnGrid(respuesta.getItems());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void crearHistorial(Historial historial) {
		try {
			this.modelo.salvarHistorial(historial);
			System.out.println("historial salvado linea 40");
			boolean respuesta=this.modelo.salvarHistorial(historial);
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
	public void consultarMedicos() {
		try {
			this.modelo.consultarMedico();
			MedicoResponse respuesta=this.modelo.consultarMedico();
			if(respuesta!=null && respuesta.getItems()!=null) {
				this.vista.llenarComboBoxMedicos(respuesta.getItems());
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	

	
}
