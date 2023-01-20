package org.example;

import controlador.ControladorPronostico;
import dao.HistoricoDao;
import dao.PronosticoDao;
import vista.VistaPronostico;

/*
 * @author Nicolas Herrera <herrera.nicolas@correounivalle.edu.co>
 * @author Samuel Galindo Cuevas <samuel.galindo@correounivalle.edu.co>
 */

public class Main
{
    public static void main(String[] args)
    {
        VistaPronostico vistaPronostico = new VistaPronostico();
        HistoricoDao historicoDao = new HistoricoDao();
        PronosticoDao pronosticoDao = new PronosticoDao();
        ControladorPronostico controladorPronostico = new ControladorPronostico(historicoDao, vistaPronostico, pronosticoDao);
    }
}