import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.*;

public class Simpletexteditor implements ActionListener {
    JFrame frame;
    JTextArea jTextArea;
    JMenuBar jMenuBar;
    JMenu file;
    JMenu edit;
    JMenu close;

    JMenuItem newfile;
    JMenuItem openfile;
    JMenuItem savefile;
    JMenuItem printfile;

    JMenuItem cutfile;
    JMenuItem copyfile;
    JMenuItem pastefile;

    JMenuItem closefile;


    Simpletexteditor(){
        frame = new JFrame("simple text Editor");
        frame.setBounds( 20, 20, 1000, 800);
        jTextArea= new JTextArea("Welcome to the Editor");

        frame.add(jTextArea);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jMenuBar = new JMenuBar();
        file = new JMenu("file");
        edit = new JMenu("edit");
        close = new JMenu("close");
        jMenuBar.add(file);
        jMenuBar.add(edit);
        jMenuBar.add(close);


        newfile = new JMenuItem("new");
        newfile.addActionListener(this);
        openfile= new JMenuItem("open");
        openfile.addActionListener(this);
        savefile = new JMenuItem("save");
        savefile.addActionListener(this);
        printfile= new JMenuItem("print");
        printfile.addActionListener(this);

        cutfile = new JMenuItem("cut");
        cutfile.addActionListener(this);
        copyfile= new JMenuItem("copy");
        copyfile.addActionListener(this);
        pastefile= new JMenuItem("paste");
        pastefile.addActionListener(this);

        closefile = new JMenuItem("close");
        closefile.addActionListener(this);
        file.add(newfile);
        file.add(openfile);
        file.add(savefile);
        file.add(printfile);

        edit.add(cutfile);
        edit.add(copyfile);
        edit.add(pastefile);

        close.add(closefile);


        frame.setJMenuBar(jMenuBar);
        frame.add(jTextArea);
        frame.setVisible(true);


    }


    public static void main(String[] args) {
        Simpletexteditor simple = new Simpletexteditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("copy")){
            jTextArea.copy();
        }
        else if(s.equals("cut")){
            jTextArea.paste();
        }
        else if(s.equals("paste")){
            jTextArea.paste();
        }
        else if(s.equals("print")){
            try {
                jTextArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(s.equals("new")){
            jTextArea.setText("");
        }
        else if(s.equals("close")){
            frame.setVisible(false);
            System.exit(1);
        }
        else if(s.equals("open")){
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans = jFileChooser.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION){
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                String s1="",s2="";
                try{
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2 = bufferedReader.readLine();
                    while((s1=bufferedReader.readLine())!=null){
                        s2+=s1+"\n";
                    }
                    jTextArea.setText(s2);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(s.equals("save")){
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans = jFileChooser.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION){
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(file, false));
                    writer.write(jTextArea.getText());
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }


    }
}
