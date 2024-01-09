class Menu {

    private static int idSeq = 1;

    private int id;
    private String name;        
    private String description;

    public Menu(String name, String description) {
        this.id = idSeq++;
        this.name = name;
        this.description = description;
    }
}
