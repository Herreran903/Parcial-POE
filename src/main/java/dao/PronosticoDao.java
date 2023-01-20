package dao;

import modelo.VentaPeriodo;

import java.util.ArrayList;

/*
 * @author Nicolas Herrera <herrera.nicolas@correounivalle.edu.co>
 * @author Samuel Galindo Cuevas <samuel.galindo@correounivalle.edu.co>
 */

public class PronosticoDao
{
    private final ArrayList<VentaPeriodo> ventasPronostico;

    public PronosticoDao()
    {
        this.ventasPronostico = new ArrayList<VentaPeriodo>();
    }

    public ArrayList<VentaPeriodo> getPronostico()
    {
        return ventasPronostico;
    }

    public boolean agregarPronostico(VentaPeriodo auxVenta)
    {
        ventasPronostico.add(auxVenta);
        return true;
    }

    public void limpiarPronostico()
    {
        ventasPronostico.clear();
    }
}
