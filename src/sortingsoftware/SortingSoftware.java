package sortingsoftware;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SortingSoftware {
    public static void main(String[] args) 
    {
        Frame f = new Frame();
        f.homepage();
    }
    
}

class Frame 
{
    public void homepage()
    {
        JFrame hframe =  new JFrame("Home Page");
        hframe.setSize(300, 150);
        hframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hframe.setLayout(null);
        hframe.setResizable(false);
        
        JLabel path = new JLabel("Path :");
        path.setSize(60, 30);
        path.setForeground(Color.BLACK);
        Font f = new Font("Ubuntu 13", Font.PLAIN, 20);
        path.setFont(f);
        path.setLocation(10, 20);
        hframe.add(path);
                
        JTextField address = new JTextField();
        address.setSize(200, 30);
        address.setLocation(80, 20);
        hframe.add(address);
        
        JButton sort = new JButton("SORT");
        sort.setSize(80, 30);
        sort.setLocation(110, 60);
        hframe.add(sort);
        sort.addActionListener((ActionEvent e) -> {
            String fpath = address.getText();
            JOptionPane.showMessageDialog(hframe, "Sorted");
            Sort s = new Sort();
            try {
                s.sortFiles(fpath);
            } catch (IOException ex) {
                Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        hframe.setVisible(true);
    }
}

class Sort 
{
    public void sortFiles(String s) throws FileNotFoundException, IOException
    {
        File file = new File(s);
        String files[] = file.list();
        for(String name : files)
        {
            File newf = new File(s + "/" + name);
            if(newf.isFile())
            {
                FileOutputStream fo = null;
                long l = newf.lastModified();
                Date d = new Date(l);
                String folderName = d.getDate() + "-" + (d.getMonth() + 1) + "-" + (d.getYear() +1900);
                File folder = new File(s + "/" + folderName);
                if(!folder.exists())
                {
                    folder.mkdir();
                }
                fo = new FileOutputStream(folder + "/" + name);
                FileInputStream fi = new FileInputStream(newf);
                int i = 0;
                while((i = fi.read()) != -1)
                {
                    fo.write(i);
                    newf.delete();
                }
                fi.close();
                fo.close();
            }
            
        }
    }
}