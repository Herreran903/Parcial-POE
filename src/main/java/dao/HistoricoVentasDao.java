package dao;
import modelo.VentaPeriodo;

import java.util.ArrayList;

public class HistoricoVentasDao
{
    private ArrayList<VentaPeriodo> historicoVentas;

    public HistoricoVentasDao()
    {
        this.historicoVentas = new ArrayList<VentaPeriodo>();
    }

    public ArrayList<VentaPeriodo> getHistorico()
    {
        return historicoVentas;
    }

    public boolean anhadirVenta(VentaPeriodo auxVenta)
    {
        historicoVentas.add(auxVenta);
        auxVenta.setPeriodo(historicoVentas.indexOf(auxVenta) + 1);
        return true;
    }

    public boolean editarVenta(VentaPeriodo auxVenta)
    {
        if(historicoVentas.contains(auxVenta))
        {
            int pos = historicoVentas.indexOf(auxVenta);
            historicoVentas.set(pos,auxVenta);
            return true;
        }
        return false;
    }

    public boolean eliminarVenta(VentaPeriodo auxVenta)
    {
        historicoVentas.remove(auxVenta);
        return true;
    }

    public VentaPeriodo getVentaHistorica(int auxPeriodo)
    {
        VentaPeriodo auxVenta = null;
        for(VentaPeriodo ventaPeriodo: historicoVentas)
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
