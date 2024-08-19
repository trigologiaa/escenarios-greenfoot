import greenfoot.*;

public class Mundo extends World {
    private static int width = 600;
    private static int height = 400;
    private static int cell = 50;
    private Color FONDO = new Color(33, 34, 44);
    
    private Cartel cartel;
    
    private Tortuga tortuga;
    
    public Mundo() {
        super(width, height, 1);
        this.cartel = new Cartel();
        addObject(cartel, 535, 370);
        usarTortugaVerde();
    }
    
    public void limpiar() {
        Color colorAnterior = getBackground().getColor();
        
        if (this.tortuga != null) {
            this.tortuga.setLocation(width/2, height/2);
            this.tortuga.setRotation(0);
        }
        this.cartel.reset();

        pintarFondo();
        dibujarGrilla();
        
        getBackground().setColor(colorAnterior);
    }
    
    private void pintarFondo() {
        getBackground().setColor(FONDO);
        getBackground().fillRect(0, 0, width, height);
    }
    
    private void dibujarGrilla() {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                getBackground().setColor(Color.DARK_GRAY);
                getBackground().drawRect(x * cell, y * cell, cell, cell);
            }
        }
    }
    
    public void usarTortugaVerde() {
        reiniciar(new TortugaVerde());
    }
    
    public void usarTortugaRoja() {
        reiniciar(new TortugaRoja());
    }

    public void usarTortugaAmarilla() {
        reiniciar(new TortugaAmarilla());
    }
    
    public void usarTortugaAzul() {
        reiniciar(new TortugaAzul());
    }
    
    private void reiniciar(Tortuga tortuga) {
        limpiar();
        cambiarTortugaAnteriorPor(tortuga);
    }
    
    private void cambiarTortugaAnteriorPor(Tortuga tortuga) {
        if (this.tortuga != null) {
            this.tortuga.morir();
        }
        this.tortuga = tortuga;
        addObject(tortuga, width/2, height/2);
    }
}
