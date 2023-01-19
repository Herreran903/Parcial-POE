package modelo;

public class VentaPeriodo
{
    private int numero;
    private int id;
    private int periodo;
    private double cantidadVentas;

    public VentaPeriodo(int auxPeriodo, double auxCantidadVentas)
    {
        numero++;
        this.id = numero;
        this.periodo = auxPeriodo;
        this.cantidadVentas = auxCantidadVentas;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int auxId)
    {
        this.id = auxId;
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
}
