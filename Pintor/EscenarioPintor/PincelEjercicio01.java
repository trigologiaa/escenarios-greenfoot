public class PincelEjercicio01 extends PincelBase {
    public void pintar() {
        int tamaño = obtenerTamañoDelLienzo();
        // mi código aquí
        for(int i = 0; i < tamaño; i++)
            pintar(i,i);
    }
}
