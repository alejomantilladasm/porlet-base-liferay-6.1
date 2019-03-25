package com.test;

import com.liferay.faces.util.portal.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;

/**
 *
 * @author David
 */
@ViewScoped
@ManagedBean
public class Usuario implements Serializable {

    private String localidad;
    private static Map<String, Object> lstPaises;

    @PostConstruct
    public void init() {
        System.out.println("Init Usuario ...!");

        Locale espaniol = new Locale("es", "ES");
        Locale english = new Locale("en", "US");

        localidad = espaniol.toString();

        lstPaises = new LinkedHashMap<String, Object>();
        lstPaises.put("Spanish", espaniol);
        lstPaises.put("English", english);

        System.out.println("Locale Size : " + lstPaises.size());
        System.out.println("Curent Locale : " + getLocale());

    }

    public Map<String, Object> getLstPaises() {
        return lstPaises;
    }

    public void setLstPaises(Map<String, Object> lstPaises) {
        Usuario.lstPaises = lstPaises;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void cambioLocalidad() {
        for (Map.Entry<String, Object> entry : lstPaises.entrySet()) {
            if (entry.getValue().toString().equals(localidad)) {
                updateLocale((Locale) entry.getValue());
                System.out.println("== >" + entry.getValue());
                System.out.println("Curent Locale : " + getLocale());
            }
        }

    }

    public void setHola() {
        System.out.println("Hola prueba ...!");
    }

    public void setEspaniol() {
        System.out.println("setEspaniol ...!");

        Locale espaniol = new Locale("es", "ES");

        PortletRequest portletRequest = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
        themeDisplay.setLocale(espaniol);
        this.localidad = espaniol.toString();
        System.out.println("setEspaniol end ...!");

        System.out.println("Curent Locale : " + getLocale());

    }

    public void setIngles() {
        System.out.println("setIngles ...!");

        Locale english = new Locale("en", "US");
        PortletRequest portletRequest = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
        themeDisplay.setLocale(english);
        this.localidad = english.toString();

        System.out.println("setIngles end ...!");

        System.out.println("Curent Locale : " + getLocale());
    }

    public void updateLocale(Locale locale) {
        PortletRequest portletRequest = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
        themeDisplay.setLocale(locale);
    }

    public String getLocale() {
        PortletRequest portletRequest = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
        return themeDisplay.getLocale().toString();
    }

}
