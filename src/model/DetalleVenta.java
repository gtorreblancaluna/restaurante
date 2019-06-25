/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jerry
 */
public class DetalleVenta {
    
    private int detalleVentaId;
    private int ventaId;
    private float cantidad;
    private int detalleCategoriaId;
    private String nota1;
    private String nota2;

    public int getDetalleVentaId() {
        return detalleVentaId;
    }

    public void setDetalleVentaId(int detalleVentaId) {
        this.detalleVentaId = detalleVentaId;
    }

    public int getVentaId() {
        return ventaId;
    }

    public void setVentaId(int ventaId) {
        this.ventaId = ventaId;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public int getDetalleCategoriaId() {
        return detalleCategoriaId;
    }

    public void setDetalleCategoriaId(int detalleCategoriaId) {
        this.detalleCategoriaId = detalleCategoriaId;
    }

    public String getNota1() {
        return nota1;
    }

    public void setNota1(String nota1) {
        this.nota1 = nota1;
    }

    public String getNota2() {
        return nota2;
    }

    public void setNota2(String nota2) {
        this.nota2 = nota2;
    }
    
    
}
