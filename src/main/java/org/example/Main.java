package org.example;

import controlador.ControladorPronostico;
import dao.HistoricoVentasDao;
import vista.VistaPronostico;

public class Main
{
    public static void main(String[] args)
    {
        VistaPronostico vistaPronostico = new VistaPronostico();
        HistoricoVentasDao historicoVentasDao = new HistoricoVentasDao();
        ControladorPronostico controladorPronostico = new ControladorPronostico(historicoVentasDao, vistaPronostico);
    }
}