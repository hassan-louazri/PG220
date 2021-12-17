package Logic;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import java.io.File;
import java.lang.SuppressWarnings;

class GUI {
  JFrame frame;
  @SuppressWarnings("unchecked")
  GUI(List<Client> clients, List<Supplier> suppliers){
    frame=new JFrame("PG220: SVG GENERATOR");
    final JLabel label = new JLabel();
    final JTextArea textArea = new JTextArea();
    textArea.setBounds(50, 250, 180, 20);
    label.setHorizontalAlignment(JLabel.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    label.setSize(350,100);
    JButton button = new JButton("RUN");
    button.setBounds(200, 100, 75, 25);
    String algorithms[]={"First Algorithm", "Second Algorithm"};
    final JComboBox box = new JComboBox(algorithms);
    box.setBounds(50, 100, 150, 25);
    frame.add(box); frame.add(label); frame.add(button); frame.add(textArea);
    frame.setLayout(null);
    frame.setSize(350,350);
    frame.setVisible(true);
    button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
          String selectedAlgo = (String)box.getItemAt(box.getSelectedIndex());
          String selected = "Selected Algorithm: " + selectedAlgo;
          label.setText(selected);
          if(selectedAlgo == "First Algorithm"){
            Main.SetCutsList(new CutFactory().generate(clients, suppliers));
          }
          else if(selectedAlgo == "Second Algorithm"){
            Main.SetCutsList(new CutFactory2().generate(clients, suppliers));
          }
          String directory = System.getProperty("user.dir");
          deleteSVGFiles(directory);
          new Solution(Main.getClientsList(), Main.getSuppliersList(), Main.getCutsList());
          textArea.setText("Done, please close Window.");
        }
      });
    }
    void deleteSVGFiles(String directory){
      File folder = new File(directory);
      for (File file : folder.listFiles()) {
          if (file.getName().endsWith(".svg")) {
              file.delete();
          }
      }
    }
}
