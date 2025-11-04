
import javax.swing.SwingUtilities;
import pl.GUI;



public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI().createAndShowGui());
    }
}
