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
}
