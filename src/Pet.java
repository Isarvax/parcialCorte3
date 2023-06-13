public class Pet {
    private String name;
    private String specie;
    private int age;

    public Pet() {

    }

    public Pet(String name, String specie, int age) {
        this.name = name;
        this.specie = specie;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "name='" + name + '\'' +
                ", specie='" + specie + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
