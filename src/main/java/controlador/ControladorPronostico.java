package controlador;
import dao.HistoricoVentasDao;
import modelo.VentaPeriodo;

public class ControladorPronostico
{
    private final HistoricoVentasDao historicoVentasDao;
    private final VistaPronostico vistaPronostico;

    private ControladorPronostico(HistoricoVentasDao auxHistoricoVentasDao, VistaPronostico auxVistaPronostico)
    {
        this.vistaPronostico = auxVistaPronostico;
        this.historicoVentasDao = auxHistoricoVentasDao;
    }

    private void agregarVentaHistorica()
    {
        VentaPeriodo auxVentaPeriodo;
        int auxId;
        int auxPeriodo;
        double auxCantidadVentas;

        auxId = Integer.parseInt(vistaPronostico.getId());
        if(auxId == 0)
        {
            if(comprobarCamposCantidadVenta())
            {
                try
                {
                    auxPeriodo = historicoVentasDao.getHistorico().size();
                    auxCantidadVentas = Integer.parseInt(vistaPronostico.getCantidadVentas());
                    auxVentaPeriodo = new VentaPeriodo(auxPeriodo,auxCantidadVentas);

                    if(historicoVentasDao.anhadirVenta(auxVentaPeriodo))
                    {
                        vistaPronostico.setCantidadVentas("");
                        if(auxPeriodo>1)
                        {
                            calcularPorcentajeVariacion();
                        }
                        listarAgregarVentaHistorica();
                    }
                }
                catch (NumberFormatException ex)
                {
                    vistaPronostico.mostrarMensajeError("ingrese la cantidad de ventas correctamente");
                }
            }
        }
        else
        {
            vistaPronostico.mostrarMensajeError("Deseleccione la fila");
        }
    }

    private boolean comprobarCampoCantidadVenta()
    {
        boolean campoValido;
        campoValido = !vistaPronostico.getCantidadVentas().equals("");
        return  campoValido;
    }
}
