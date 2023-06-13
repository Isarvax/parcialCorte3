import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Functions {
    public static void agregarMascota(HashSet<Pet> mascotas) throws IOException {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la mascota:");
        String especie = JOptionPane.showInputDialog("Ingrese la especie de la mascota:");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad de la mascota:"));

        Pet mascota = new Pet( nombre, especie, edad);
        mascotas.add(mascota);
        savePets(mascotas);
        JOptionPane.showMessageDialog(null, "Mascota agregada exitosamente");
    }

    public static void eliminarMascota(HashSet<Pet> mascotas) throws IOException {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la mascota a eliminar:");
        boolean eliminado = mascotas.removeIf(m -> m.getName().equalsIgnoreCase(nombre));
        savePets(mascotas);
        if (eliminado) {
            JOptionPane.showMessageDialog(null, "Mascota eliminada exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "La mascota no existe");
        }
    }

    public static void actualizarMascota(HashSet<Pet> mascotas) throws IOException {
        String nombreAnterior = JOptionPane.showInputDialog("Ingrese el nombre de la mascota a actualizar:");
        boolean encontrado = false;
        for (Pet mascota : mascotas) {
            if (mascota.getName().equalsIgnoreCase(nombreAnterior)) {
                String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre de la mascota:");
                String nuevaEspecie = JOptionPane.showInputDialog("Ingrese la nueva especie de la mascota:");
                int nuevaEdad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva edad de la mascota:"));

                mascota.setName(nuevoNombre);
                mascota.setSpecie(nuevaEspecie);
                mascota.setAge(nuevaEdad);
                savePets(mascotas);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            JOptionPane.showMessageDialog(null, "Mascota actualizada exitosamente");

        } else {
            JOptionPane.showMessageDialog(null, "La mascota no existe");
        }
    }

    public static void listarMascotas(HashSet<Pet> mascotas) {
        StringBuilder sb = new StringBuilder();
        mascotas.forEach(m -> sb.append(m.toString()).append("\n"));
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public static void buscarMascota(HashSet<Pet> mascotas) {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la mascota a buscar:");
        Optional<Pet> mascota=mascotas.stream().filter(m -> m.getName().equalsIgnoreCase(nombre)).findAny();

        if (mascota.isPresent()) {
            JOptionPane.showMessageDialog(null, mascota);
        } else {
            JOptionPane.showMessageDialog(null, "La mascota no existe");
        }
    }


    public static final String RUTE_ARCHIVE_PET = "src/pets.txt";

    public static void savePets(HashSet<Pet> listClient) throws IOException {
        String contenido = "";
        for(Pet attraction: listClient) {
            contenido+= attraction.getName()+"/"+attraction.getSpecie()+"/"+attraction.getAge()+"\n";
        }
        ArchiveUtil.saveArchive(RUTE_ARCHIVE_PET, contenido, false);
    }

    public static HashSet<Pet> chargePets() throws FileNotFoundException, IOException {
        HashSet<Pet> attractions = new HashSet<Pet>();
        ArrayList<String> content = ArchiveUtil.readArchive(RUTE_ARCHIVE_PET);
        String line = "";
        for (int i = 0;i<content.size(); i++) {
            Pet myAttraction = new Pet();
            line= content.get(i);
            myAttraction.setName(line.split("/")[0]);
            myAttraction.setSpecie(line.split("/")[1]);
            myAttraction.setAge(Integer.parseInt(line.split("/")[2]));
            attractions.add(myAttraction);
        }
        return attractions;
    }
}
