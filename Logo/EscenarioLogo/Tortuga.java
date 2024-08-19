import greenfoot.*;
import java.util.function.Consumer;

public abstract class Tortuga extends Actor {
    protected boolean dibujar = true;
    protected int grosor = 5;
    
    private static Color[] colores = {
        new Color(189, 147, 249), // Dracula Purple
        new Color(241, 250, 140), // Dracula Yellow
        new Color(255, 184, 108), // Dracula Orange
        new Color(255, 85, 85),   // Dracula Red
        new Color(255, 121, 198), // Dracula Pink
        new Color(80, 250, 123),  // Dracula Green
        new Color(139, 233, 253)  // Dracula Cyan
    };
    private int colorActual = 0;
    
    protected void addedToWorld(World world) {
        cambiarColor();
    }
    
    private Cartel getCartel() {
        return ((Mundo)getWorld()).getObjects(Cartel.class).get(0);
    }
      
    public void adelante(int pasos) {
        int x0 = this.getX();
        int y0 = this.getY();
        this.move(pasos);
        int x1 = this.getX();
        int y1 = this.getY();
        linea(x0, y0, x1, y1);
        
        Cartel cartel = getCartel();
        cartel.contarDistancia(Math.abs(pasos));
        cartel.contarPaso();
        
        Greenfoot.delay(1);
    }
    
    private void linea(int x0, int y0, int x1, int y1) {
        int medio = grosor/2;
        if (dibujar) {
            for (int i = -medio; i <= medio; i++) {
                for (int j = -medio; j <= medio; j++) {
                    if (Math.abs(i) + Math.abs(j) <= medio) {
                        getWorld().getBackground().drawLine(x0 + i, y0 + j, x1 + i, y1 + j);
                    }
                }
            }
        }
    }
    
    public void atras(int pasos) {
        this.adelante(-pasos);
    }
    
    public void derecha(int grados) {
        this.turn(grados);
        Cartel cartel = getCartel();
        cartel.contarPaso();
    }
    
    public void izquierda(int grados) {
        this.turn(-grados);
        Cartel cartel = getCartel();
        cartel.contarPaso();
    }
    
    public void morir() {
        if (this.getWorld() != null) {
            getWorld().removeObject(this);
        }
    }
    
    // Métodos del trazo
    public void subirLapiz() {
        this.dibujar = false;
        Cartel cartel = getCartel();
        cartel.contarPaso();
    }
    
    public void bajarLapiz() {
        this.dibujar = true;
        Cartel cartel = getCartel();
        cartel.contarPaso();
    }
    
    public void cambiarColor() {
        cambiarColor(colorActual);
        colorActual++;
    }
    
    public void cambiarColor(int numero) {
        this.colorActual = Math.abs(numero)%colores.length;
        getWorld().getBackground().setColor(colores[colorActual]);
    }
    
    // Utilidades
    protected void repetir(int veces, Consumer<Integer> accion) {
        for (int i = 1; i <= veces; i++) {
            accion.accept(i);
        }
    }
}
