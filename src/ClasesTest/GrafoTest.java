package ClasesTest;

import Grafo.Grafo;
import Grafo.Vertice;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class GrafoTest {

    private Grafo g;
    private Vertice a, b, c;

    @Before
    public void setUp() {
        g = new Grafo();
        a = new Vertice(200);
        b = new Vertice(0);
        c = new Vertice(20);
        g.agregarVertice(a);
        g.agregarVertice(b);
        g.agregarVertice(c);
    }

    @Test
    public void agregarArista() {
        g.agregarArista(a, b);
        assertEquals(1, g.getAristas().size());
        assertTrue(g.getVecinos(a).contains(b));
        assertTrue(g.getVecinos(b).contains(a));
    }

    @Test(expected = IllegalArgumentException.class)
    public void aristaConsigoMisma() {
        g.agregarArista(a, a);
    }

    @Test(expected = IllegalArgumentException.class)
    public void aristaDuplicadaEnCualquierOrden() {
        g.agregarArista(a, b);
        g.agregarArista(b, a);
    }

    @Test(expected = IllegalArgumentException.class)
    public void aristaConVerticeInexistente() {
        Vertice z = new Vertice(0);
        g.agregarArista(a, b);
    }

    @Test(expected = IllegalArgumentException.class)
    public void aristaConVerticeNull() {
        g.agregarArista(a, b);
    }

    @Test(expected = IllegalArgumentException.class)
    public void agregarVerticeNull() {
        g.agregarVertice(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verticeRepetidoNoSeDuplica() {
        g.agregarVertice(new Vertice(200));
    }

    @Test
    public void recorrerAlcanzaSoloLosVerticesDeLaComponente() {
        g.agregarArista(a, b);
        Set<Vertice> alcanzados = g.recorrer(a);
        assertEquals(2, alcanzados.size());
        assertTrue(alcanzados.contains(b));
        assertFalse(alcanzados.contains(c));
    }

    @Test
    public void conexo() {
        g.agregarArista(a, b);
        g.agregarArista(b, c);
        assertTrue(g.esConexo());
    }

    @Test
    public void noConexo() {
        g.agregarArista(a, b);
        assertFalse(g.esConexo());
    }

    @Test
    public void grafoVacioEsConexo() {
        assertTrue(new Grafo().esConexo());
    }

    @Test
    public void grafoDeUnSoloVerticeEsConexo() {
        Grafo solo = new Grafo();
        solo.agregarVertice(new Vertice(0));
        assertTrue(solo.esConexo());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getAristasNoPermiteModificarDesdeAfuera() {
        g.getAristas().add(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getVecinosNoPermiteModificarDesdeAfuera() {
        g.getVecinos(a).add(b);
    }
}
