/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author jerry
 */
public class Venta {
    
    private int ventaId;
    private int empleadoId;
    private int mesaId;
    private String comensales;
    private String fecha;
    private String nota1;
    private String nota2;
    private String preventa;
    private int cajaId;
    private int facturado;
    private int clienteId;
    private float cargo;
    private float descuento;
    private float propina;
    private int estadoVentaId;
    private ArrayList<DetalleVenta> detalle;

    public ArrayList<DetalleVenta> getDetalle() {
        return detalle;
    }

    public void setDetalle(ArrayList<DetalleVenta> detalle) {
        this.detalle = detalle;
    }
    
    

    public int getVentaId() {
        return ventaId;
    }

    public void setVentaId(int ventaId) {
        this.ventaId = ventaId;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public int getMesaId() {
        return mesaId;
    }

    public void setMesaId(int mesaId) {
        this.mesaId = mesaId;
    }

    public String getComensales() {
        return comensales;
    }

    public void setComensales(String comensales) {
        this.comensales = comensales;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public String getPreventa() {
        return preventa;
    }

    public void setPreventa(String preventa) {
        this.preventa = preventa;
    }

    public int getCajaId() {
        return cajaId;
    }

    public void setCajaId(int cajaId) {
        this.cajaId = cajaId;
    }

    public int getFacturado() {
        return facturado;
    }

    public void setFacturado(int facturado) {
        this.facturado = facturado;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public float getCargo() {
        return cargo;
    }

    public void setCargo(float cargo) {
        this.cargo = cargo;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public float getPropina() {
        return propina;
    }

    public void setPropina(float propina) {
        this.propina = propina;
    }

    public int getEstadoVentaId() {
        return estadoVentaId;
    }

    public void setEstadoVentaId(int estadoVentaId) {
        this.estadoVentaId = estadoVentaId;
    }
    
    
    
}
