/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modificarcabeceradocs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

/**
 *
 * @author Alcaide
 */
public class appModificacionDOCs extends javax.swing.JFrame {

    /**
     * Creates new form appModificacionDOCs
     */
    public appModificacionDOCs() {
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

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(231, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(166, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        File raiz = new File(".");
        try {
            recorrerDirectorio(raiz);
        } catch (IOException ex) {
            Logger.getLogger(appModificacionDOCs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(appModificacionDOCs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(appModificacionDOCs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(appModificacionDOCs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(appModificacionDOCs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new appModificacionDOCs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
    
    public int level = 0; // level del directorio a recorrer
    
    // metodo para modificar cabeceras de documentos word
    private int recorrerDirectorio(File dir) throws IOException {
        File[] pathsDocumento = dir.listFiles();
        
        if(pathsDocumento.length==0) // si el directorio esta vacio deshacer recrusion
            return 0;
        
        for(int a=0; a<pathsDocumento.length;a++){
            if(level>0)
                System.out.print("\t");
            
            System.out.println(pathsDocumento[a]);
            if(pathsDocumento[a].isDirectory() && (level+1)<2){
                level++;
                
                recorrerDirectorio(pathsDocumento[a]);                
            }
            else{ // si es un fichero comprobar si es un word para modificarlo
                // añadir patron de busqueda de documentos
                if(pathsDocumento[a].toString().endsWith(".doc") || pathsDocumento[a].toString().endsWith(".docx"))
                    modificarDOCs(pathsDocumento[a]);
            }
        }
        
        level--;
        return 1;
    }

    private void modificarDOCs(File pathsDocumento) throws FileNotFoundException, IOException{
        XWPFDocument doc = new XWPFDocument();
        
        XWPFHeaderFooterPolicy policy = doc.getHeaderFooterPolicy();
        if (policy.getDefaultHeader() == null && policy.getFirstPageHeader() == null
               && policy.getDefaultFooter() == null) {
           // Need to create some new headers
           // The easy way, gives a single empty paragraph
           XWPFHeader headerD = policy.createHeader(policy.DEFAULT);
           //headerD.getParagraphs().createRun().setText("Hello Header World!");
           headerD.getParagraphArray(0).createRun().setText("AAAAAAAAAAAAA");

           // Or the full control way
            CTP ctP1 = CTP.Factory.newInstance();
            CTR ctR1 = ctP1.addNewR();
            CTText t = ctR1.addNewT();
            t.setStringValue("Paragraph in header");

            XWPFParagraph p1 = new XWPFParagraph(ctP1, doc);
            XWPFParagraph[] pars = new XWPFParagraph[1];
            pars[0] = p1;

            policy.createHeader(policy.FIRST, pars);
            
            doc.write(new FileOutputStream(new File("prueba.docx")));
            System.out.println("Cabecera terminada");
        } else {
           // Already has a header, change it
        }
    }
}
