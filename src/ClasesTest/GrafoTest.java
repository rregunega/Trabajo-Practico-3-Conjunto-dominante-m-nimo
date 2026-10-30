package ClasesTest;

import Grafo.Grafo;
import Grafo.Vertice;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class GrafoTest {

    private Grafo g;
    private Vertice a, b, c ,d,e,f,h,i,j,k;

    public void setUp() {
        g = new Grafo();
        a = new Vertice(200);
        b = new Vertice(0);
        c = new Vertice(20);
        g.agregarVertice(a);
        g.agregarVertice(b);
        g.agregarVertice(c);
    }
    @Before
    public void setUp2() {
        g = new Grafo();
        a = new Vertice(200);
        b = new Vertice(0);
        c = new Vertice(20);
        d = new Vertice(10);
        e = new Vertice(15);
        f = new Vertice(30);
        h = new Vertice(60);
        i = new Vertice(90);
        j = new Vertice(100);
        k = new Vertice(3);
        g.agregarVertice(a);
        g.agregarVertice(b);
        g.agregarVertice(c);
        g.agregarVertice(d);
        g.agregarVertice(e);
        g.agregarVertice(f);
        g.agregarVertice(h);
        g.agregarVertice(i);
        g.agregarVertice(j);
        g.agregarVertice(k);
    }
    public void setUpAristas() {
        g.agregarArista(a, c);
        g.agregarArista(a, k);
        g.agregarArista(b, h);
        g.agregarArista(b, d);
        g.agregarArista(i, e);
        g.agregarArista(j, f);
    }
    @Test
    public void agregarArista() {
        g.agregarArista(a, b);
        assertEquals(1, g.getAristas().size());
        assertTrue(g.getVecinos(a).contains(b));
        assertTrue(g.getVecinos(b).contains(a));
    }

    @Test(expected = IllegalArgumentException.class)
    public void aristaDuplicadaEnCualquierOrden() {
        g.agregarArista(a, b);
        g.agregarArista(b, a);

    }

    @Test(expected = IllegalArgumentException.class)
    public void aristaConVerticeInexistente() {
        Vertice z = new Vertice(10);
        g.agregarArista(a, z);
    }

    @Test(expected = IllegalArgumentException.class)
    public void aristaConVerticeNull() {
        Vertice x = null;
        g.agregarArista(a, x);
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

    @Test()
    public void obtenerConjuntoMinimo(){
        setUpAristas();
        System.out.println(g.ConjuntoDominanteMinimo());
    }
}
