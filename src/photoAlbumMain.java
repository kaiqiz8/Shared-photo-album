import java.io.FileNotFoundException;

import controller.Controller;

public class photoAlbumMain {
  public static void main(String[] args) throws FileNotFoundException {
    Controller controller = new Controller(args);
    controller.go();
  }
}
