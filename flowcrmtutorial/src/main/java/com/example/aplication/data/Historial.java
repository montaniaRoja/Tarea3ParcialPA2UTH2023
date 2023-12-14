package com.example.aplication.data;

public class Historial {
	
	private String fecha_cita;
	private String motivo_cita;
	private String diagnostico;
	private String proxima_cita;
	private String paciente_id;
	private String nombre_medico;
	
	public Historial() {
		
	}


	public String getFecha_cita() {
		return fecha_cita;
	}


	public void setFecha_cita(String string) {
		this.fecha_cita = string;
	}


	public String getMotivo_cita() {
		return motivo_cita;
	}


	public void setMotivo_cita(String motivo_cita) {
		this.motivo_cita = motivo_cita;
	}


	public String getDiagnostico() {
		return diagnostico;
	}


	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}


	public String getProxima_cita() {
		return proxima_cita;
	}


	public void setProxima_cita(String proxima_cita) {
		this.proxima_cita = proxima_cita;
	}


	
	public String getPaciente_id() {
		return paciente_id;
	}


	public void setPaciente_id(String paciente_id) {
		this.paciente_id = paciente_id;
	}


	public String getNombre_medico() {
		return nombre_medico;
	}


	public void setNombre_medico(String nombre_medico) {
		this.nombre_medico = nombre_medico;
	}


	

}
