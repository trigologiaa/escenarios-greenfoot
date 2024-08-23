import greenfoot.*;

/**
 * Define el comportamiento base para todos los Pilotos de la Batalla Espacial
 */
public abstract class PilotoBase extends ActorBase {
    protected double ESCALA_X = 0.8;
    protected double ESCALA_Y = 0.8;

    /**
     * La nave que pilotará
     */
    protected NaveDeAtaque navePilotada;
    GreenfootImage picture = new GreenfootImage(getImage());

    /**
     * post: El Piloto se sube a la Nave
     * 
     * @param nave es la Nave a la que se subirá el piloto
     */
    public void subirse(NaveDeAtaque nave) {
        navePilotada = nave;
        actualizarImagen();
    }

    /**
     * post: El Piloto deja la Nave
     */
    public void bajarse() {
        navePilotada = null;
        actualizarImagen();
    }

    /**
     * Actualiza la imagen del Piloto, de acuerdo a su estado (con nave o sin nave)
     */
    @Override
    protected void actualizarImagen() {
        int tamCelda = getWorld().getCellSize();
        GreenfootImage image;
        if (navePilotada != null) {
            image = getImage();
            MyGreenfootImage nuevaImagen = new MyGreenfootImage(image) {
                public void configurar() {
                    highlight();
                    setTransparency(150);
                }
            };
            nuevaImagen.scale((int) (tamCelda * ESCALA_X), (int) (tamCelda * ESCALA_Y));
            setImage(nuevaImagen);
        } else {
            image = picture;
            image.scale((int) (tamCelda * ESCALA_X), (int) (tamCelda * ESCALA_Y));
            setImage(image);
        }
    }
}