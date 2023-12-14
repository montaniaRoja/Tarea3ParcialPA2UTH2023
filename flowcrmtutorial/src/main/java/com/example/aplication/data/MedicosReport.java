package com.example.aplication.data;


import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class MedicosReport implements JRDataSource {
	
	private List<Medico> medicos;
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
		if("carnet".equals(jrField.getName())) {
			return medicos.get(contador).getCarnet();
		}else if("telefono".equals(jrField.getName())) {
			return medicos.get(contador).getTelefono();
		}else if("correo".equals(jrField.getName())) {
			return medicos.get(contador).getCorreo();
		}else if("especialidad".equals(jrField.getName())) {
			return medicos.get(contador).getEspecialidad();
		}else if("nombre".equals(jrField.getName())) {
			return medicos.get(contador).getNombre();
		}
		
		
		// TODO Auto-generated method stub
		return "";
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
	    this.medicos = medicos;
	    if (this.medicos != null) {
	        this.maxContador = this.medicos.size() - 1;
	    } else {
	        // Manejo adecuado cuando la lista de médicos es null
	        System.out.println("La lista de médicos es nula.");
	    }
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
