import java.util.*;

public class Main {
    private static ArrayList<Album> albums = new ArrayList<>();
    private static LinkedList<Song> playlist1 = new LinkedList<>();

    public static void main(String[] args) {
        // Add synthetic data
        addAlbums();
        addPlaylist();
        play(playlist1);
    }

    private static void play(LinkedList<Song> playlist){
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;

        ListIterator<Song> listIterator = playlist.listIterator();

        if (playlist.size() == 0)
            System.out.println("Playlist is empty.");
        else {
            System.out.println("Now playing " + listIterator.next().toString());
            printMenu();
        }

        while (!quit){
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 0:
                    System.out.println("Playlist completed.");
                    quit = true;
                    break;

                case 1:
                    if (!forward){
                        if (listIterator.hasNext())
                            listIterator.next();
                        forward = true;
                    }
                    if (listIterator.hasNext()){
                        System.out.println("Playing next song: " + listIterator.next().toString());
                    }
                    else {
                        System.out.println("No song to play. Playlist ended.");
                        forward = false;
                    }
                    break;

                case 2:
                    if (forward){
                        if (listIterator.hasPrevious())
                            listIterator.previous();
                        forward = false;
                    }
                    if (listIterator.hasPrevious()){
                        System.out.println("Playing a previous song: " + listIterator.previous().toString());
                    }
                    else {
                        System.out.println("Can't play a previous song. Reached to the beginning of the playlist.");
                        forward = true;
                    }
                    break;

                //TODO: replay the current song
                case 3:
                    System.out.println("TODO");
                    break;

                case 4:
                    printList(playlist);
                    break;

                case 5:
                    printMenu();
                    break;

                case 6:
                    if (playlist.size() > 0){
                        listIterator.remove();
                        if (listIterator.hasNext()) {
                            System.out.println("Now playing " + listIterator.next().toString());
                        }
                        else {
                            if (listIterator.hasPrevious()) {
                                System.out.println("Now playing " + listIterator.previous().toString()
                                        + "\nDeleted the last song from the list.");
                            }
                        }
                    }
                    break;
            }
        }
    }

    private static void printMenu(){
        System.out.println("Options\n" +
                "0 - quit\n" +
                "1 - play the next song\n" +
                "2 - play the previous song\n" +
                "3 - replay the current song\n" +
                "4 - list of all songs\n" +
                "5 - print available options\n" +
                "6 - delete current song");
    }

    private static void printList(LinkedList<Song> playlist){
        Iterator<Song> iterator = playlist.iterator();
        System.out.println("--------------------------");
        while (iterator.hasNext()){
            Song song = iterator.next();
            System.out.println(song);
        }
        System.out.println("--------------------------");
    }


    private static void addAlbums(){
        Album album = new Album("Album1", "AC/DC");

        album.addSong("TNT", 4.5);
        album.addSong("Highway to Hell", 3.5);
        album.addSong("ThunderStruck", 5.0);

        albums.add(album);

        album = new Album("Album2", "Apocalyptica");

        album.addSong("I Don't Care", 4.0);
        album.addSong("Not Strong Enough", 3.45);
        album.addSong("I'm Not Jesus", 3.5);

        albums.add(album);
    }

    private static void addPlaylist(){
        albums.get(0).addToPlaylist("TNT", playlist1);
        albums.get(0).addToPlaylist("Highway to Hell", playlist1);
        albums.get(1).addToPlaylist("Not Strong Enough", playlist1);
        albums.get(1).addToPlaylist("I'm Not Jesus", playlist1);
        System.out.println();
    }
}