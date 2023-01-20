package controlador;
import dao.HistoricoVentasDao;
import modelo.VentaPeriodo;
import vista.VistaPronostico;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorPronostico
{
    private final HistoricoVentasDao historicoVentasDao;
    private final VistaPronostico vistaPronostico;

    public ControladorPronostico(HistoricoVentasDao auxHistoricoVentasDao, VistaPronostico auxVistaPronostico)
    {
        this.vistaPronostico = auxVistaPronostico;
        this.historicoVentasDao = auxHistoricoVentasDao;

        vistaPronostico.pantallaCompleta();

        PronosticoListener pronosticoListener = new PronosticoListener();
        vistaPronostico.addBtnAgregarAñoListener(pronosticoListener);
        vistaPronostico.addBtnModificarAñoListener(pronosticoListener);
        vistaPronostico.addBtnBorrarAñoListener(pronosticoListener);
        vistaPronostico.addBtnNuevoPronosticoListener(pronosticoListener);
    }

    private void agregarVentaHistorica()
    {
        VentaPeriodo auxVentaPeriodo;
        int auxId;
        int auxPeriodo;
        double auxCantidadVentas;

        auxId = Integer.parseInt(vistaPronostico.getAño());
        if(auxId == 0)
        {
            if(comprobarCampoCantidadVenta())
            {
                try
                {
                    auxCantidadVentas = Double.parseDouble(vistaPronostico.getCantidadVentas());


                    if(auxCantidadVentas >= 0)
                    {
                        if(historicoVentasDao.getHistorico().isEmpty())
                        {
                            auxVentaPeriodo = new VentaPeriodo(auxCantidadVentas, 0 ,0);
                            if(historicoVentasDao.anhadirVenta(auxVentaPeriodo))
                            {
                                vistaPronostico.setCantidadVentas("");
                                listarAgregarVenta(auxVentaPeriodo);
                            }
                        }
                        else
                        {
                            auxVentaPeriodo = new VentaPeriodo(auxCantidadVentas, 0 ,0);
                            if(historicoVentasDao.anhadirVenta(auxVentaPeriodo))
                            {
                                calcularVariacionAgregar(auxVentaPeriodo);
                                vistaPronostico.setCantidadVentas("");
                                vistaPronostico.setTotalVariacion("" + calcularTotalPorcentajeVariacion());
                                listarAgregarVenta(auxVentaPeriodo);
                            }
                        }
                    }
                    else
                    {
                        vistaPronostico.mostrarMensajeError("No se puede agregar ventas negativas");
                        vistaPronostico.setCantidadVentas("");
                    }

                }
                catch (NumberFormatException ex)
                {
                    vistaPronostico.mostrarMensajeError("ingrese la cantidad de ventas correctamente");
                    vistaPronostico.setCantidadVentas("");
                }
            }
        }
        else
        {
            vistaPronostico.mostrarMensajeError("Deseleccione la fila");
        }
    }

    private void modificarVentaHistorica()
    {
        VentaPeriodo auxVentaPeriodo;
        int auxAño;
        int auxPeriodo;
        double auxCantidadVentas;

        auxAño = Integer.parseInt(vistaPronostico.getAño());
        auxVentaPeriodo = historicoVentasDao.getVentaHistorica(auxAño);

        if(auxVentaPeriodo != null)
        {
            if(comprobarCampoCantidadVenta())
            {
                try
                {
                    auxCantidadVentas = Double.parseDouble(vistaPronostico.getCantidadVentas());

                    if(auxCantidadVentas >= 0)
                    {
                        if(historicoVentasDao.getHistorico().size() == 1)
                        {
                            auxVentaPeriodo.setCantidadVentas(auxCantidadVentas);
                            if(historicoVentasDao.editarVenta(auxVentaPeriodo))
                            {
                                listarModificarVenta(auxVentaPeriodo, vistaPronostico.getFilaSeleccionadaVentas());
                                vistaPronostico.setCantidadVentas("");
                                vistaPronostico.setTotalVariacion("" + calcularTotalPorcentajeVariacion());
                                vistaPronostico.deseleccionarFilaTablaVentas();
                            }
                        }
                        else
                        {
                            auxVentaPeriodo.setCantidadVentas(auxCantidadVentas);
                            if(historicoVentasDao.editarVenta(auxVentaPeriodo))
                            {
                                calcularVariacionModificar(auxVentaPeriodo);
                                vistaPronostico.setCantidadVentas("");
                                vistaPronostico.setTotalVariacion("" + calcularTotalPorcentajeVariacion());
                                vistaPronostico.deseleccionarFilaTablaVentas();
                            }
                        }
                    }
                    else
                    {
                        vistaPronostico.mostrarMensajeError("No se puede agregar ventas negativas");
                        vistaPronostico.setCantidadVentas("");
                    }
                }
                catch (NumberFormatException ex)
                {
                    vistaPronostico.mostrarMensajeError("ingrese la cantidad de ventas correctamente");
                    vistaPronostico.setCantidadVentas("");
                }
            }
        }
        else
        {
            vistaPronostico.mostrarMensajeError("Seleccione una fila");
        }
    }

    private boolean comprobarCampoCantidadVenta()
    {
        boolean campoValido;
        campoValido = !vistaPronostico.getCantidadVentas().equals("");
        return  campoValido;
    }

    private void calcularVariacionAgregar(VentaPeriodo auxVenta)
    {
        double variacionPorcentajeVenta;
        double auxVariacion;

        VentaPeriodo auxVentaAnterior = historicoVentasDao.getVentaHistorica(auxVenta.getPeriodo()-1);
        auxVariacion = auxVenta.getCantidadVentas() - auxVentaAnterior.getCantidadVentas();
        if(auxVentaAnterior.getCantidadVentas()!=0)
        {
            variacionPorcentajeVenta = auxVariacion / auxVentaAnterior.getCantidadVentas();
        }
        else
        {
            variacionPorcentajeVenta = auxVariacion;
        }

        auxVenta.setVariacionVentas(auxVariacion);
        auxVenta.setPorcentajeVariacionVenta(variacionPorcentajeVenta);
    }

    private void calcularVariacionModificar(VentaPeriodo auxVenta)
    {
        double auxVariacion = 0;
        double auxVariacionPorcentaje = 0;

        if(historicoVentasDao.getHistorico().size() != auxVenta.getPeriodo() && auxVenta.getPeriodo() != 1)
        {
            VentaPeriodo auxVentaAnterior = historicoVentasDao.getVentaHistorica(auxVenta.getPeriodo() - 1);
            VentaPeriodo auxVentaPosterior = historicoVentasDao.getVentaHistorica(auxVenta.getPeriodo() + 1);

            auxVariacion = auxVenta.getCantidadVentas() - auxVentaAnterior.getCantidadVentas();
            auxVenta.setVariacionVentas(auxVariacion);

            if(auxVentaAnterior.getCantidadVentas() != 0)
            {
                auxVariacionPorcentaje = auxVariacion/auxVentaAnterior.getCantidadVentas();
                auxVenta.setPorcentajeVariacionVenta(auxVariacionPorcentaje);
            }
            else
            {
                auxVariacionPorcentaje = auxVariacion;
                auxVenta.setPorcentajeVariacionVenta(auxVariacionPorcentaje);
            }

            listarModificarVenta(auxVenta, vistaPronostico.getFilaSeleccionadaVentas());

            auxVariacion = auxVentaPosterior.getCantidadVentas() - auxVenta.getCantidadVentas();
            auxVentaPosterior.setVariacionVentas(auxVariacion);

            if(auxVenta.getCantidadVentas() != 0)
            {
                auxVariacionPorcentaje = auxVariacion/auxVenta.getCantidadVentas();
                auxVentaPosterior.setPorcentajeVariacionVenta(auxVariacionPorcentaje);
            }
            else
            {
                auxVariacionPorcentaje = auxVariacion;
                auxVentaPosterior.setPorcentajeVariacionVenta(auxVariacionPorcentaje);
            }

            listarModificarVenta(auxVentaPosterior, vistaPronostico.getFilaSeleccionadaVentas() + 1);
        }
        else if(historicoVentasDao.getHistorico().size() == auxVenta.getPeriodo())
        {
            VentaPeriodo auxVentaAnterior = historicoVentasDao.getVentaHistorica(auxVenta.getPeriodo() - 1);
            auxVariacion = auxVenta.getCantidadVentas() - auxVentaAnterior.getCantidadVentas();
            auxVenta.setVariacionVentas(auxVariacion);

            if(auxVentaAnterior.getCantidadVentas() != 0)
            {
                auxVariacionPorcentaje = auxVariacion/auxVentaAnterior.getCantidadVentas();
                auxVenta.setPorcentajeVariacionVenta(auxVariacionPorcentaje);
            }
            else
            {
                auxVariacionPorcentaje = auxVariacion;
                auxVenta.setPorcentajeVariacionVenta(auxVariacionPorcentaje);
            }

            listarModificarVenta(auxVenta, vistaPronostico.getFilaSeleccionadaVentas());
        }
        else if(auxVenta.getPeriodo() == 1)
        {
            VentaPeriodo auxVentaPosterior = historicoVentasDao.getVentaHistorica(auxVenta.getPeriodo() + 1);

            auxVariacion = auxVentaPosterior.getCantidadVentas() - auxVenta.getCantidadVentas();
            auxVentaPosterior.setVariacionVentas(auxVariacion);

            if(auxVenta.getCantidadVentas() != 0)
            {
                auxVariacionPorcentaje = auxVariacion/auxVenta.getCantidadVentas();
                auxVentaPosterior.setPorcentajeVariacionVenta(auxVariacionPorcentaje);
            }
            else
            {
                auxVariacionPorcentaje = auxVariacion;
                auxVentaPosterior.setPorcentajeVariacionVenta(auxVariacionPorcentaje);
            }

            listarModificarVenta(auxVenta, vistaPronostico.getFilaSeleccionadaVentas());
            listarModificarVenta(auxVentaPosterior, vistaPronostico.getFilaSeleccionadaVentas() + 1);
        }
    }

    private double calcularPorcentajeVariacion(VentaPeriodo auxVenta)
    {
        VentaPeriodo auxVentaAnterior = historicoVentasDao.getVentaHistorica(auxVenta.getPeriodo()-1);
        double variacionPorcentajeVenta;

        if (auxVentaAnterior.getCantidadVentas()!=0)
        {
             variacionPorcentajeVenta = (auxVenta.getCantidadVentas() - auxVentaAnterior.getCantidadVentas())/auxVentaAnterior.getCantidadVentas();
        }
        else
        {
            variacionPorcentajeVenta = 0;
        }

        return variacionPorcentajeVenta;
    }

    private double calcularTotalPorcentajeVariacion()
    {
        ArrayList<VentaPeriodo> ventasHistoricas = historicoVentasDao.getHistorico();
        double totalPorcentajeVariacion = 0;
        for(VentaPeriodo ventaPeriodo : ventasHistoricas)
        {
            totalPorcentajeVariacion = totalPorcentajeVariacion + ventaPeriodo.getPorcentajeVariacionVenta();
        }

        return totalPorcentajeVariacion;
    }

    private void listarAgregarVenta(VentaPeriodo auxVenta)
    {
        DefaultTableModel modelo = (DefaultTableModel) vistaPronostico.getTableModelHistoricoVentas();
        modelo.addRow(new Object[]{auxVenta.getPeriodo(),auxVenta.getCantidadVentas(), auxVenta.getVariacionVentas(), auxVenta.getPorcentajeVariacionVenta()});
    }

    private void listarModificarVenta(VentaPeriodo auxVenta, int auxFila)
    {
        double auxCantidadVentas = auxVenta.getCantidadVentas();
        double variacionVenta = auxVenta.getVariacionVentas();
        double porcentajeVariacion = auxVenta.getPorcentajeVariacionVenta();

        DefaultTableModel modelo = (DefaultTableModel) vistaPronostico.getTableModelHistoricoVentas();
        modelo.setValueAt(auxCantidadVentas, auxFila, 1);
        modelo.setValueAt(variacionVenta, auxFila, 2);
        modelo.setValueAt(porcentajeVariacion, auxFila, 3);
    }

    class PronosticoListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equalsIgnoreCase("AGREGAR AÑO"))
            {
                agregarVentaHistorica();
            }
            if (e.getActionCommand().equalsIgnoreCase("MODIFICAR AÑO"))
            {
                modificarVentaHistorica();
            }
            if (e.getActionCommand().equalsIgnoreCase("BORRAR AÑO"))
            {
               // eliminarCliente();
            }
            if(e.getActionCommand().equalsIgnoreCase("NUEVO PRONOSTICO"))
            {

            }
        }
    }
}
