/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playlistgen.View;

import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.table.DefaultTableModel;
import playlistgen.Model.Song;

/**
 *
 * @author nami
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public MainFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGenerate = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPlaylist = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Playlist Generator");

        btnGenerate.setText("Generate");
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });

        tblPlaylist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Title", "Artist"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPlaylist.setName("Playlist"); // NOI18N
        tblPlaylist.setShowGrid(true);
        jScrollPane2.setViewportView(tblPlaylist);
        if (tblPlaylist.getColumnModel().getColumnCount() > 0) {
            tblPlaylist.getColumnModel().getColumn(0).setMinWidth(15);
            tblPlaylist.getColumnModel().getColumn(0).setPreferredWidth(15);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenerate, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGenerate, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed

        String PATH = System.getProperty("user.home") + File.separator + "Music" + File.separator + "iTunes" + File.separator + "iTunes Media" + File.separator + "Music" + File.separator + "Unknown Artist" + File.separator + "Unknown Album" + File.separator;
        List<Song> results = new ArrayList<>();
        File[] files = new File(PATH).listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null. 
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".mp3")) {
                try{
                Song song = new Song(0, file.getName().split("-")[1].replace(".mp3", "").replace("_", "?").trim(), file.getName().split("-")[0].replace("_", "?").trim(), PATH+file.getName());
                results.add(song);
                } catch (Exception ex){
                    //System.out.println(file.getName());
                    //System.out.println(ex);
                }
            }
        }
        Random rand = new Random();
        List<Integer> randomNums = new ArrayList<>();
        for(int i = 0;i<20;i++){
            int randomNum = rand.nextInt((results.size() - 1) + 1) + 1;
            randomNums.add(randomNum);
        }
        DefaultTableModel model = new DefaultTableModel(){
             @Override
             public boolean isCellEditable(int row, int column){  
                return false;  
            }
        };
        model.addColumn("#");
        model.addColumn("Title");
        model.addColumn("Artist");
        List<Song> playlist = new ArrayList<>();
        randomNums.stream().forEach((i) -> {
            Song song = new Song(i, results.get(i).getTitle(), results.get(i).getArtist(), results.get(i).getURL());
            playlist.add(song);
            model.addRow(new Object[]{i, results.get(i).getTitle(), results.get(i).getArtist()});
        });
        tblPlaylist.setModel(model);
        tblPlaylist.getColumn("#").setPreferredWidth(50);
        tblPlaylist.getColumn("#").setMinWidth(50);
        tblPlaylist.getColumn("#").setMaxWidth(50);
        tblPlaylist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    try {
                        Desktop.getDesktop().open(new File(playlist.get(tblPlaylist.getSelectedRow()).getURL()));
                    } catch (IOException ex) {
                        //
                    }
                }
            }
        });

    }//GEN-LAST:event_btnGenerateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerate;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblPlaylist;
    // End of variables declaration//GEN-END:variables
}
