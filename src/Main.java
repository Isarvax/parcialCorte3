import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    private Functions functions;


    public static void main(String[] args) {
        HashSet<Pet> mascotas=new HashSet<>();
        try {
            mascotas= chargePets();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        boolean continuar = true;
        while (continuar) {
            String opcion = JOptionPane.showInputDialog(
                    "Seleccione una opción:\n" +
                            "1. Agregar una mascota\n" +
                            "2. Eliminar una mascota\n" +
                            "3. Actualizar una mascota\n" +
                            "4. Listar todas las mascotas\n" +
                            "5. Buscar una mascota\n" +
                            "6. Salir"
            );

            if (opcion == null || opcion.equals("6")) {
                continuar = false;
            } else {
                switch (opcion) {
                    case "1":
                        try {
                            Functions.agregarMascota(mascotas);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "2":
                        try {
                            Functions.eliminarMascota(mascotas);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "3":
                        try {
                            Functions.actualizarMascota(mascotas);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "4":
                        Functions.listarMascotas(mascotas);
                        break;
                    case "5":
                        Functions.buscarMascota(mascotas);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida");
                        break;
                }
            }
        }
    }
    public static final String RUTE_ARCHIVE_PET = "src/pets.txt";

    public void savePets(HashSet<Pet> listClient) throws IOException {
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
