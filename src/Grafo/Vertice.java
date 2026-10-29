package Grafo;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Vertice implements Comparable<Vertice> {

    private final Integer valor;
    private final Set<Vertice> vecinos = new LinkedHashSet<>();

    public Vertice(int valor) {
        this.valor= valor;
    }

    public int getValor() { return valor; }

    public Set<Vertice> getVecinos() {
        return Collections.unmodifiableSet(vecinos);
    }

    // visibilidad de paquete: solo Grafo puede llamarlo
    void agregarVecino(Vertice otro) { vecinos.add(otro); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertice)) return false;
        return  valor.equals(((Vertice) o).getValor()) ;
    }

    public int numeroVecinos(){
        return vecinos.size();
    }
    @Override
    public int hashCode() { return Objects.hash(valor); }

    @Override
    public String toString() { return String.valueOf(valor); }

    @Override
    public int compareTo(Vertice otro) {
        return Integer.compare(otro.numeroVecinos(), this.numeroVecinos());
    }
}



