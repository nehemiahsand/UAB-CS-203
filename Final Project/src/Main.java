import javax.swing.JFrame;

class Main {
    public static void main(String[] args) {
        WordleGUI frame = new WordleGUI();
        frame.setTitle("Wordle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}