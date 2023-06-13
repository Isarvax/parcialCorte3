import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class PersistencePet {
    public static final String RUTE_ARCHIVE_PET = "src/Data/pets.txt";

    public void savePets(HashSet<Pet> listClient) throws IOException {
        String contenido = "";
        for(Pet attraction: listClient) {
            contenido+= attraction.getName()+"/"+attraction.getSpecie()+"/"+attraction.getAge()+"\n";
        }
        ArchiveUtil.saveArchive(RUTE_ARCHIVE_PET, contenido, false);
    }

    public HashSet<Pet> chargePets() throws FileNotFoundException, IOException {
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
