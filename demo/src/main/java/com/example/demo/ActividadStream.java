package com.example.demo;


import org.springframework.cglib.core.Local;

import java.security.PublicKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
        import java.util.function.Predicate;
import java.util.stream.Collectors;

// clase factura
class Factura{
    int codigo;
    String descripcion;
    int importe;
    int cantidadProductos;
    Date fecha1 ;

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");



    Factura(int codigo,String descripcion,int importe, int cantidadProductos, String fecha){

        this.descripcion=descripcion;
        this.importe=importe;
        this.codigo = codigo;
        this.cantidadProductos = cantidadProductos;
        try {
            this.fecha1 = formato.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImporte() {
        return importe;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }

    public Date getFecha1() {
        return fecha1;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "codigo=" + codigo +
                ", descripcion='" + descripcion + '\'' +
                ", importe=" + importe +
                ", cantidadProductos=" + cantidadProductos +
                ", fecha=" + fecha1 +
                '}';
    }
}


public class ActividadStream {


    public static void main(String[] args) {
        // registros de la data
        Factura f=new Factura(1,"ordenador",1000,3,"11/11/2021");
        Factura f2=new Factura(2,"movil",300,4,"30/01/2022");
        Factura f3=new Factura(3,"imporesora",200,3,"25/10/2022");
        Factura f4=new Factura(4,"imac",1500,5,"15/06/2019");

        // generar una lista
        List<Factura> lista=new ArrayList<Factura>();

        // agregar los productos de la factura
        lista.add(f);
        lista.add(f2);
        lista.add(f3);
        lista.add(f4);


        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        // filtraje funcional con streams

        List<Factura> facturaFechaMenor=lista.stream()
                .filter(elemento-> {
                    try {
                        return elemento.getFecha1().before(formato.parse("30/12/2019"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                        .collect(Collectors.toList());
        System.out.println("las facturas mas antiguas a la fecha dada son: " +facturaFechaMenor);

        List<Factura> facturaFechaMayor=lista.stream()
                .filter(elemento-> {
                    try {
                        return elemento.getFecha1().after(formato.parse("30/12/2019"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .collect(Collectors.toList());
        System.out.println("las facturas mas recientes a la fecha dada son: " + facturaFechaMayor);

        List<Factura> facturaCantidadDeProductoIgual=lista.stream()
                .filter(elemento -> elemento.getCantidadProductos() == 3)
                .collect(Collectors.toList());
        System.out.println("las facturas  con esa cantidad de productos son " + facturaCantidadDeProductoIgual);

        List<Factura> facturaCantidadDeProductoMayor=lista.stream()
                .filter(elemento -> elemento.getCantidadProductos() > 2)
                .collect(Collectors.toList());
        System.out.println("las facturas  con mas de esos productos es:  " + facturaCantidadDeProductoMayor);

        List<Factura> facturaCantidadDeProductoMenor=lista.stream()
                .filter(elemento -> elemento.getCantidadProductos() < 2)
                .collect(Collectors.toList());
        System.out.println("las facturas  con menos de esos productos es:  " + facturaCantidadDeProductoMenor);


        Factura facturaCodigoFactura =lista.stream()
                .filter(elemento -> elemento.getCodigo() == 1)
                        .findFirst().get();
        System.out.println("la factura con ese codigo es: " + facturaCodigoFactura);


    }


}