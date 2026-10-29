package Grafo;

public class Arista {
    private final Vertice vertice1;
    private final Vertice vertice2;


    public Arista(Vertice v1, Vertice v2) {
        this.vertice1 = v1;
        this.vertice2 = v2;

    }

    public Vertice getVertice1()
    { 
    	return vertice1;
    }
    
    public Vertice getVertice2()
    {
    	return vertice2;
    }
    

}