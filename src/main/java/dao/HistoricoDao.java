package dao;
import modelo.VentaPeriodo;

import java.util.ArrayList;

/*
 * @author Nicolas Herrera <herrera.nicolas@correounivalle.edu.co>
 * @author Samuel Galindo Cuevas <samuel.galindo@correounivalle.edu.co>
 */

public class HistoricoDao
{
    private ArrayList<VentaPeriodo> ventasHistorico;

    public HistoricoDao()
    {
        this.ventasHistorico = new ArrayList<VentaPeriodo>();
    }

    public ArrayList<VentaPeriodo> getHistorico()
    {
        return ventasHistorico;
    }

    public boolean agregarHistorico(VentaPeriodo auxVenta)
    {
        ventasHistorico.add(auxVenta);
        auxVenta.setPeriodo(ventasHistorico.indexOf(auxVenta) + 1);
        return true;
    }

    public boolean editarHistorico(VentaPeriodo auxVenta)
    {
        if(ventasHistorico.contains(auxVenta))
        {
            int pos = ventasHistorico.indexOf(auxVenta);
            ventasHistorico.set(pos,auxVenta);
            return true;
        }
        return false;
    }

    public boolean eliminarPronostico(VentaPeriodo auxVenta)
    {
        ventasHistorico.remove(auxVenta);
        return true;
    }

    public VentaPeriodo getVentaHistorica(int auxPeriodo)
    {
        VentaPeriodo auxVenta = null;
        for(VentaPeriodo ventaPeriodo: ventasHistorico)
        {
            if(ventaPeriodo.getPeriodo()== auxPeriodo)
            {
                auxVenta = ventaPeriodo;
                break;
            }
        }
        return auxVenta;
    }
}
