package ar.edu.davinci.dvds20231cg4.domain;

import java.util.LinkedList;
import java.util.List;

public enum EstadoPrenda {
    NUEVA("PrendaNueva"),
    PROMOCION("PrendaPromocion"),
    LIQUIDACION("PrendaLiquidacion");

    private String descripcion;

    private EstadoPrenda(String descripcion){this.descripcion = descripcion;};

    public String getDescripcion(){return descripcion;}

    public static List<EstadoPrenda> getEstadoPrendas(){
        List<EstadoPrenda> estadoPrendas = new LinkedList<EstadoPrenda>();
        estadoPrendas.add(EstadoPrenda.NUEVA);
        estadoPrendas.add(EstadoPrenda.PROMOCION);
        estadoPrendas.add(EstadoPrenda.LIQUIDACION);
        return  estadoPrendas;
    }


}
