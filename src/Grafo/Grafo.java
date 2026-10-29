package Grafo;

import java.util.*;
import java.util.PriorityQueue;
import java.util.Queue;

public class Grafo {
    private final HashMap<Integer, Vertice> vertices=new HashMap<>();
    private final List<Arista> aristas=new ArrayList<>();

    public void agregarVertice(Vertice v) {
        if (v == null) throw new IllegalArgumentException("el vertice no puede ser null");
        if(vertices.containsKey(v.getValor())) throw new IllegalArgumentException("el valor ya existe en el conjunto");
        vertices.put(v.getValor(),v);
    }

    public void agregarArista(Vertice a, Vertice b) {
        verificarExiste(a);
        verificarExiste(b);
        if (a.getVecinos().contains(b))
            throw new IllegalArgumentException("La arista " + a + "-" + b + " ya existe");

        a.agregarVecino(b);
        b.agregarVecino(a);
        aristas.add(new Arista(a, b));
    }

    public Map<Integer, Vertice> getVertices() { return Collections.unmodifiableMap(vertices); }
    public List<Arista> getAristas() { return Collections.unmodifiableList(aristas); }

    public Set<Vertice> getVecinos(Vertice v) {
        verificarExiste(v);
        return v.getVecinos();
    }

    public Set<Vertice> recorrer(Vertice inicio) {
        verificarExiste(inicio);
        Set<Vertice> visitados = new LinkedHashSet<>();
        Queue<Vertice> cola = new LinkedList<>();
        visitados.add(inicio);
        cola.add(inicio);
        while (!cola.isEmpty()) {
            for (Vertice v : cola.remove().getVecinos()) {
                if (visitados.add(v)) cola.add(v);
            }
        }
        return visitados;
    }

    public boolean esConexo() {
        if (vertices.isEmpty()) return true;
        return recorrer(vertices.values().iterator().next()).size() == vertices.size();
    }

    private void verificarExiste(Vertice v) {
        if (v == null || !vertices.containsKey(v.getValor()))
            throw new IllegalArgumentException("el vertice no existe en el grafo");
    }

    public Grafo ConjuntoDominanteMinimo(){
        Set<Vertice> visitados = new HashSet<>();
        Grafo conjuntoD =new Grafo();
        Queue<Vertice> pila = new PriorityQueue<>((v1, v2) -> {
            int ganancia1 = calcularGanancia(v1, visitados);
            int ganancia2 = calcularGanancia(v2, visitados);
            return Integer.compare(ganancia1, ganancia2);});

        pila.addAll(vertices.values());
        while(visitados.size()!=vertices.size()){
            Vertice verticePila=pila.poll(); //reordeno
            pila.add(verticePila);
            verticePila=pila.poll();
            conjuntoD.agregarVertice(verticePila);
            visitados.addAll(verticePila.getVecinos());
        }

return conjuntoD;
    }

    private int calcularGanancia(Vertice v1, Set<Vertice> visitados) {
        int ganancia = 0;
        for (Vertice vecino : v1.getVecinos()) {
            if (!visitados.contains(vecino)) {
                ganancia++;  //contar solo vecinos NO visitados
            }
    }
    return ganancia;}
}
