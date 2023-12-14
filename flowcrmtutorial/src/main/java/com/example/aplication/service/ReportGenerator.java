package com.example.aplication.service;


import java.io.File;
import java.sql.Connection;
import java.util.Map;

import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportGenerator {
	
	private String reportPath;
	
	public boolean generarReportePDF(String nombreReporte, Map<String, Object> parametros, JRDataSource datasource) {
		
		boolean reporteGenerado=false;
		
		try {
			JasperReport reporte=(JasperReport)JRLoader.loadObjectFromFile(obtenerUbicacionReporte(nombreReporte+".jasper"));
			
			JasperPrint impresora=JasperFillManager.fillReport(reporte, parametros, datasource);
			
			String rutaPdf=generarUbicacionReporte();//+nombreReporte+".pdf";
			reportPath=rutaPdf;
			JasperExportManager.exportReportToPdfFile(impresora, rutaPdf);
			reporteGenerado=true;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			reporteGenerado=false;
		}
		
		
		return reporteGenerado;
		
	}
	
	
	private String generarUbicacionReporte() {
		
		String path=null;
		try {
			path=File.createTempFile("temp",".pdf").getAbsolutePath();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(path);
		return path;
		
		
	}
	
	
	
	
	private String obtenerUbicacionReporte(String filename) {
		
		String path=null;
		try {
			path=ResourceUtils.getFile("classpath:"+filename).getAbsolutePath();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return path;
		
	}
	
	
	
	public String getReportPath() {
		
		return reportPath;
		
	}
	
	

}
