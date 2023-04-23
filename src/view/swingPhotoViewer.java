package view;

import java.awt.*;       // Using AWT's Graphics and Color
import java.awt.event.*;  // Using AWT's event classes and listener interface
import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;    // Using Swing's components and containers

import model.ShapePosition;
import model.SnapShots;

/**
 * Custom Graphics Example: Using key/button to move a line left or right.
 */

@SuppressWarnings("serial")
public class swingPhotoViewer extends JFrame {
  private model.Canvas canvas1;
  private DrawCanvas drawCanvas;
  private int index;

  // Constructor to set up the GUI components and event handlers
  public swingPhotoViewer(model.Canvas canvas1) {
    this.canvas1 = canvas1;
    int size = canvas1.getSnapshotsCollection().size();
    this.index = 0;
    setSize((int)this.canvas1.getCanvasX(), (int)this.canvas1.getCanvasY());
    //todo: hash map that matches index with snapshot ID

    //set up framework:
    JPanel btnPanel = new JPanel(new FlowLayout());
    JPanel textPanel = new JPanel(new GridLayout(2,1));
//    textPanel.setBackground(Color.PINK);
////    JLabel label = new JLabel("Snapshot description: ");  //todo: add description and snapshotID;
//    JLabel label1 = new JLabel(canvas1.getSnapshotsCollection().get(index).getID());
////    JPanel textPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
////    textPanel2.setBackground(Color.PINK);
//    JLabel label2 = new JLabel("Snapshot description: " + canvas1.getSnapshotsCollection().get(index).getDescription());
//    textPanel.add(label1);
//    textPanel.add(label2);
    Container cp = getContentPane();

    drawCanvas = new DrawCanvas(canvas1,index);
    System.out.printf("First " + index  + " " + drawCanvas.toString()+"\n");
    drawCanvas.setPreferredSize(new Dimension((int) canvas1.getCanvasX(), (int) canvas1.getCanvasY()));



    //button
    JButton btnLeft = new JButton("<<prev<< ");
    btnPanel.add(btnLeft);

    JButton btnmid1 = new JButton("^^ Select ^^");
    btnPanel.add(btnmid1);

    String[] snapshotsDropDown = new String[size];
    for (int ind = 0; ind < size; ind++){
      snapshotsDropDown[ind] = canvas1.getSnapshotsCollection().get(ind).getID() + "-No-" + ind;
    }

    String[] snapshotsDescription = new String[size];
    for (int ind = 0; ind < size; ind++){
      snapshotsDescription[ind] = canvas1.getSnapshotsCollection().get(ind).getDescription();
    }

    paintCanvas paintCanvas = new paintCanvas(drawCanvas, textPanel, btnPanel,
            cp, canvas1, snapshotsDropDown, snapshotsDescription);

    System.out.println(Arrays.toString(snapshotsDropDown));
    System.out.println(Arrays.toString(snapshotsDescription));

//    String input = (String) JOptionPane.showInputDialog(null, "Choose Now",
//            "Select a snapshot to view", JOptionPane.QUESTION_MESSAGE, null,snapshotsDropDown, snapshotsDropDown[0]);
//    JComboBox jComboBox = new JComboBox<>(snapshotsDropDown);
//    jComboBox.setBounds(80, 50, 130, 20);
//    JButton done = new JButton("Done");
//    done.setBounds(100, 100, 90, 20);
//    textPanel.setBackground(Color.PINK);
//    JLabel label1 = new JLabel(this.canvas1.getSnapshotsCollection().get(0).getID());
//    JLabel label2 = new JLabel("Snapshot description: " + this.canvas1.getSnapshotsCollection().get(0).getDescription());

    JButton btnmid2 = new JButton(">> Next >>");
    btnPanel.add(btnmid2);

    JButton btnRight = new JButton("xx Quit xx");
    btnPanel.add(btnRight);

    // button functionality:

    btnLeft.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
//        x1 -= 10;
//        x2 -= 10;
        if (index == 0){
          //todo: pop up window
          JFrame popup = new JFrame();
          JOptionPane.showMessageDialog(popup, "End of album, no snapshot to show beyond this point");
        } else {
          index -= 1;
//          textPanel.setBackground(Color.PINK);
//          JLabel label1 = new JLabel(canvas1.getSnapshotsCollection().get(index).getID());
//          JLabel label2 = new JLabel("Snapshot description: " + canvas1.getSnapshotsCollection().get(index).getDescription());
//          textPanel.add(label1);
//          textPanel.add(label2);

//          drawCanvas.removeAll();
//          drawCanvas.revalidate();
//
//          drawCanvas = new DrawCanvas(canvas1,index);
//
////          System.out.printf("Left " + canvas.toString() +"\n");
//          System.out.printf("Left " + index  +" "+ canvas1.getSnapshotsCollection().get(index).getShapePositions() +"\n");
//          drawCanvas.repaint();
//          Container cp = getContentPane();
//          cp.setLayout(new BorderLayout());
//          cp.add(textPanel, BorderLayout.NORTH);
//          cp.add(drawCanvas, BorderLayout.CENTER);
//          cp.add(btnPanel, BorderLayout.SOUTH);
//          requestFocus(); // change the focus to JFrame to receive KeyEvent
          paintCanvas.rePainting(index);
        }
      }
    });

    btnmid1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        String input = (String) JOptionPane.showInputDialog(null,
                "Choose Now",
                "Select a snapshot to view", JOptionPane.QUESTION_MESSAGE,
                null,snapshotsDropDown, snapshotsDropDown[0]);
//        int selectedInd = -1;
//        System.out.printf(selectedInd +"\n");
        for (int k = 0; k < size; k++){
          if (input == snapshotsDropDown[k]){
            index = k;
          }
        }
        paintCanvas.rePainting(index);
        System.out.printf("index selected: " + index +"\n");

//        JFrame jFrame = new JFrame();
//        jFrame.add(done);
//        jFrame.add(jComboBox);
//        jFrame.setLayout(null);
//        jFrame.setSize(350, 350);
//        jFrame.setVisible(true);
//
//
//        done.addActionListener(new ActionListener() {
//          @Override
//          public void actionPerformed(ActionEvent e) {
//            index = jComboBox.getSelectedIndex();
//            System.out.println(index);
//            paintCanvas.rePainting(index);
//            jFrame.setVisible(false);
//          }
//        });
//        drawCanvas.repaint();
//        requestFocus(); // change the focus to JFrame to receive KeyEvent
      }
    });


    btnmid2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
//        x1 += 10;
//        x2 += 10;
        if (index == size - 1){
          JFrame popup = new JFrame();
          JOptionPane.showMessageDialog(popup, "End of album, no snapshot to show beyond this point");
        } else {
          index += 1;
//          drawCanvas.removeAll();
//          drawCanvas.revalidate();
//
//          drawCanvas = new DrawCanvas(canvas1,index);
//          drawCanvas.repaint();
//
//          Container cp = getContentPane();
//          cp.setLayout(new BorderLayout());
//          cp.add(textPanel, BorderLayout.NORTH);
//          cp.add(drawCanvas, BorderLayout.CENTER);
//          cp.add(btnPanel, BorderLayout.SOUTH);

//          textPanel.setBackground(Color.PINK);
//          JLabel label1 = new JLabel(canvas1.getSnapshotsCollection().get(index).getID());
//          JLabel label2 = new JLabel("Snapshot description: " + canvas1.getSnapshotsCollection().get(index).getDescription());
//          textPanel.add(label1);
//          textPanel.add(label2);
          paintCanvas.rePainting(index);
        }
      }
    });

    btnRight.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        System.exit(0);
      }
    });
    paintCanvas.startPainting();

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handle the CLOSE button
    setTitle("Snapshots");
    pack();           // pack all the components in the JFrame
    setVisible(true);
  }


}
class paintCanvas extends JPanel{
  private DrawCanvas drawCanvas;
  private JPanel textPanel;
  private JPanel btnPanel;
  private Container cp;
  private model.Canvas canvas;
  private String[] snapshots;
  private String[] descriptions;
  private JLabel label1;
  private JLabel label2;

  public paintCanvas(DrawCanvas drawCanvas, JPanel textPanel, JPanel btnPanel, Container cp, model.Canvas canvas,
                     String[] snapshots, String[] descriptions){
    this.drawCanvas = drawCanvas;
    this.textPanel = textPanel;
    this.btnPanel = btnPanel;
    this.cp = cp;
    this.canvas = canvas;
    this.snapshots = snapshots;
    this.descriptions = descriptions;
    label1 = new JLabel();
    label2 = new JLabel();
  }

  public void startPainting(){
//    this.textPanel.removeAll();
//    this.textPanel.revalidate();
//    this.textPanel.repaint();

    this.textPanel.setBackground(Color.PINK);
//    label1 = new JLabel(this.snapshots[0]);

    label1.setText(this.snapshots[0]);
//    label2 = new JLabel("Snapshot description: " + this.descriptions[0]);

    label2.setText("Snapshot description: " + this.descriptions[0]);
//    System.out.printf(this.canvas.getSnapshotsCollection().getID() +"\n");
//    for (int ind = 0; ind < canvas.getSnapshotsCollection().size(); ind++){
//      System.out.println(canvas.getSnapshotsCollection().get(ind).getID() + "-No-" + ind +"\n");
//      System.out.println(canvas.getSnapshotsCollection().get(ind).getDescription() + "-No-" + ind +"\n");
//    }
//
    this.textPanel.add(label1);
    this.textPanel.add(label2);

    cp.setLayout(new BorderLayout());
    cp.add(this.textPanel, BorderLayout.NORTH);
    cp.add(this.drawCanvas, BorderLayout.CENTER);
    cp.add(this.btnPanel, BorderLayout.SOUTH);
  }

  //    public void rePainting(model.Canvas canvas, int index){
  public void rePainting(int index){
//    this.textPanel.removeAll();
//    this.textPanel.revalidate();
//    this.textPanel.repaint();

    this.textPanel.setBackground(Color.PINK);
//    label1 = new JLabel(this.snapshots[index]);
    label1.setText(this.snapshots[index]);
//    System.out.printf(this.canvas.getSnapshotsCollection().get(index).getID() +"\n");
//    label2 = new JLabel("Snapshot description: " + this.descriptions[index]);
    label2.setText("Snapshot description: " + this.descriptions[index]);
//    this.textPanel.add(label1);
//    this.textPanel.add(label2);
    System.out.printf("current snapshot: " +index +"\n");

    drawCanvas.removeAll();
    drawCanvas.revalidate();
    drawCanvas = new DrawCanvas(canvas,index);
    cp.setLayout(new BorderLayout());
    cp.add(this.textPanel, BorderLayout.NORTH);
    cp.add(this.drawCanvas, BorderLayout.CENTER);
    cp.add(this.btnPanel, BorderLayout.SOUTH);
//    startPainting();
  }
}


/**
 * Define inner class DrawCanvas, which is a JPanel used for custom drawing.
 */
class DrawCanvas extends JPanel {
  private int i;
  private List<ShapePosition> shapePositionList;
  private model.Canvas canvas1;

  public DrawCanvas(model.Canvas canvas1, int i){
    this.i = i;
    this.canvas1 = canvas1;
    this.shapePositionList = canvas1.getSnapshotsCollection().get(i).getShapePositions();
    System.out.printf(shapePositionList.toString() +"\n");
    setSize((int)this.canvas1.getCanvasX(), (int)this.canvas1.getCanvasY());
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.ORANGE);
    for (ShapePosition sp: this.shapePositionList){
      Color newColor = new Color((int) sp.getShape().getColor().getR(),
              (int) sp.getShape().getColor().getG(), (int) sp.getShape().getColor().getB());
      g.setColor(newColor);
      if (sp.getShape().getType().equalsIgnoreCase("rectangle")) {
        g.drawRect((int) sp.getXPos(), (int) sp.getYPos(),
                (int) sp.getShape().getXParam(), (int) sp.getShape().getYParam());
        g.fillRect((int) sp.getXPos(), (int) sp.getYPos(),
                (int) sp.getShape().getXParam(), (int) sp.getShape().getYParam());
      }
      if (sp.getShape().getType().equalsIgnoreCase("oval")) {
        g.drawOval((int) sp.getXPos(), (int) sp.getYPos(),
                (int) sp.getShape().getXParam(), (int) sp.getShape().getYParam());
        g.fillOval((int) sp.getXPos(), (int) sp.getYPos(),
                (int) sp.getShape().getXParam(), (int) sp.getShape().getYParam());
        System.out.printf("\n");
      }
    }
  }
}

