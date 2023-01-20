package modelo;

public class VentaPeriodo
{
    private int periodo;
    private double cantidadVentas;
    private double variacionVentas;
    private double porcentajeVariacionVenta;

    public VentaPeriodo(double auxCantidadVentas, double auxVariacionVentas, double auxPorcentajeVariacionVenta)
    {
        this.periodo = 0;
        this.cantidadVentas = auxCantidadVentas;
        this.variacionVentas = auxVariacionVentas;
        this.porcentajeVariacionVenta = auxPorcentajeVariacionVenta;
    }

    public VentaPeriodo(double auxCantidadVentas)
    {
        this.cantidadVentas = auxCantidadVentas;
    }

    public int getPeriodo()
    {
        return periodo;
    }

    public void setPeriodo(int auxPeriodo)
    {
        this.periodo = auxPeriodo;
    }

    public double getCantidadVentas()
    {
        return cantidadVentas;
    }

    public void setCantidadVentas(double auxCantidadVentas)
    {
        this.cantidadVentas = auxCantidadVentas;
    }

    public double getVariacionVentas()
    {
        return variacionVentas;
    }

    public void setVariacionVentas(double auxVariacionVentas)
    {
        this.variacionVentas = auxVariacionVentas;
    }

    public double getPorcentajeVariacionVenta()
    {
        return porcentajeVariacionVenta;
    }

    public void setPorcentajeVariacionVenta(double auxPorcentajeVariacionVenta)
    {
        this.porcentajeVariacionVenta = auxPorcentajeVariacionVenta;
    }

}
