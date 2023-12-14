package com.example.aplication.inicio;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

@PageTitle("Hospital || CRM")
@Route(value="", layout = MainLayout.class)
public class ListViewModel extends VerticalLayout {
	
	
	
    public ListViewModel() {
        setSpacing(false);

        Image img = new Image("images/hospital.png", "logo hospital");
        img.setWidth("200px");
        add(img);

        H2 header = new H2("C R M");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new Paragraph("Sistema de Gestion de Hospital"));
        add(new Paragraph(" UTH Programacion Avanzada II"));
        add(new Paragraph(" Grupo 3"));
        add(new Paragraph(" Adolfo Carranza 201020043069"));
        add(new Paragraph(" Marta Anabel Perez 202010010082 "));
        
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
        
        
    }

	

	

}
