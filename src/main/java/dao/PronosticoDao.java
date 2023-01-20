package dao;

import modelo.VentaPeriodo;

import java.util.ArrayList;

public class PronosticoDao
{
    private ArrayList<VentaPeriodo> ventasPronostico;

    public PronosticoDao()
    {
        this.ventasPronostico = new ArrayList<VentaPeriodo>();
    }

    public ArrayList<VentaPeriodo> getArrayPronostico()
    {
        return ventasPronostico;
    }

    public boolean anhadirPronostico(VentaPeriodo auxVenta)
    {
        ventasPronostico.add(auxVenta);
        return true;
    }

    public void limpiarPronostico()
    {
        ventasPronostico.clear();
    }
}
