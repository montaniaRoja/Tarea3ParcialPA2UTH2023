package com.example.aplication.data;


import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class PacientesReport implements JRDataSource {
	
	private List<Paciente> pacientes;
	private int contador=-1;
	private int maxContador=0;

	@Override
	public boolean next() throws JRException {
		if(contador<maxContador) {
			contador++;
			return true;
		}
		
		return false;
		
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		if("dni".equals(jrField.getName())) {
			return pacientes.get(contador).getDni();
		}else if("nombre".equals(jrField.getName())) {
			return pacientes.get(contador).getNombre();
		}else if("apellido".equals(jrField.getName())) {
			return pacientes.get(contador).getApellido();
		}else if("direccion".equals(jrField.getName())) {
			return pacientes.get(contador).getDireccion();
		}else if("telefono".equals(jrField.getName())) {
			return pacientes.get(contador).getTelefono();
		}
		
		
		// TODO Auto-generated method stub
		return "";
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
		this.maxContador=this.pacientes.size()-1;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public int getMaxContador() {
		return maxContador;
	}

	public void setMaxContador(int maxContador) {
		this.maxContador = maxContador;
	}
	
	
	

}

