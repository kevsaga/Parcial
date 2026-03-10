public class Recursividad {
    public static String invertirCadena(String texto) {
		// implementación aquí
         if (texto.length() <= 1) {
            return texto;
        }
        char primero = texto.charAt(0);
        String resto = texto.substring(1);
        return invertirCadena(resto) + primero;
	}

    public static void main(String[] args) {
        String original = "Recursividad";
        String invertida = invertirCadena(original);
        System.out.println("Original: " + original);
        System.out.println("Invertida: " + invertida);
    }

}
