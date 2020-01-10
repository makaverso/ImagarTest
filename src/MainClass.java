import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;
import org.apache.commons.io.FileUtils;

public class MainClass {
    public static void main (String[] Args) throws IOException{

            String ubicacion = JOptionPane.showInputDialog("Introduzca la ruta del archivo");
            contadorPalabras (ubicacion);
    }

    public static void contadorPalabras (String ubicacion) throws IOException{
        try {
            String p1, p2, p3;
            int cont1=0, cont2=0, cont3=0;

            //Pasamos el contenido de nuestro fichero a un String
            File file = new File(ubicacion);
            String contenido = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

            //Introducción de datos por el usuario
            JOptionPane.showMessageDialog(null, "A continuación, introducirá 3 palabras manualmente," +
                            " y este software buscará las veces que aparecen repetidas en el archivo y las ordenará");
            p1 = JOptionPane.showInputDialog("Introduzca la primera palabra").toLowerCase();
            p2 = JOptionPane.showInputDialog("Introduzca la segunda palabra").toLowerCase();
            p3 = JOptionPane.showInputDialog("Introduzca la tercera palabra").toLowerCase();

            //StringTokenizer para la primera palabra
            StringTokenizer st = new StringTokenizer(contenido);
            while (st.hasMoreTokens()){
                if (st.nextToken().equalsIgnoreCase(p1)){
                    cont1++;
                }
            }

            //StringTokenizer para la segunda palabra
            StringTokenizer st2 = new StringTokenizer(contenido);
            while (st2.hasMoreTokens()){
                if (st2.nextToken().equalsIgnoreCase(p2)){
                    cont2++;
                }
            }

            //StringTokenizer para la tercera palabra
            StringTokenizer st3 = new StringTokenizer(contenido);
            while (st3.hasMoreTokens()){
                if (st3.nextToken().equalsIgnoreCase(p3)){
                    cont3++;
                }
            }

            //Llamamos a la función que ordenará las palabras según su repetición en el archivo
            ordenar(p1,p2,p3,cont1,cont2,cont3);
        }
        catch (IOException e){
            JOptionPane.showMessageDialog (null, "No se ha encontrado el archivo" +
                            " en la ruta especificada", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }



    public static void ordenar (String p1, String p2, String p3, int cont1, int cont2, int cont3){
        int rep1=0, rep2=0, rep3=0;
        String primera="", segunda="", tercera="";

        //Primer caso: p1 la que más se repite
        if (cont1>=cont2 && cont1>=cont3) {
            primera = p1;
            rep1 = cont1;
            if (cont2>=cont3){
                segunda = p2;
                rep2 = cont2;
                tercera = p3;
                rep3 = cont3;
            }
            else {
                segunda = p3;
                rep2 = cont3;
                tercera = p2;
                rep3 = cont2;
            }
        }
        //Segundo caso: p2 la que más se repite
        else if (cont2>=cont1 && cont2>=cont3) {
            primera = p2;
            rep1 = cont2;
            if (cont1>=cont3){
                segunda = p1;
                rep2 = cont1;
                tercera = p3;
                rep3 = cont3;
            }
            else {
                segunda = p3;
                rep2 = cont3;
                tercera = p1;
                rep3 = cont1;
            }
        }

        //Tercer caso: p3 la que más se repite
        else if (cont3>=cont2 && cont3>=cont1) {
            primera = p3;
            rep1 = cont3;
            if (cont1>cont2){
                segunda = p1;
                rep2 = cont1;
                tercera = p2;
                rep3 = cont2;
            }
            else {
                segunda = p2;
                rep2 = cont2;
                tercera = p1;
                rep3 = cont1;
            }
        }

        JOptionPane.showMessageDialog(null, "La palabra más repetida es \""
                +primera+"\" y se ha repetido "+rep1+" veces.\n\n"
                +"La segunda palabra más repetida es \""+segunda+"\" y se ha repetido "+rep2+" veces.\n\n"
                +"La tercera palabra más repetida es \""+tercera+"\" y se ha repetido "+rep3+" veces.");
    }
}
