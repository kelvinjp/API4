/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/*
 * Fichero: JavaMail.java
 * Autor: Jeison Nisperuza
 * Fecha: 24/12/2010 18:14
 */
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class JavaMail
{
public MimeMultipart multiParte;
    private String clave; 
    String usuario;
    String strAdjunto;
    String strNombreadd;
public void email(String usuario, String clave, String Adjunto, String nombreadd, String para, String asunto,String mensaje) {
    this.usuario = usuario;
    this.clave = clave;
    this.strAdjunto = Adjunto;
    this.strNombreadd = nombreadd;
    
   
    
       try
        {
        // JMailxView.barra_progreso.setVisible(true);
        //Configuracion del correo de salida 
         Properties props = new Properties();
         props.setProperty("mail.smtp.host", "smtp.gmail.com");
         props.setProperty("mail.smtp.starttls.enable", "true");
         props.setProperty("mail.smtp.port", "587");
         props.setProperty("mail.smtp.user", usuario);
         props.setProperty("mail.smtp.auth", "true");
         //correos a los que se le enviara el correo
         String [] correos;
         //los correos estaran en un string y se separaran por coma
         correos = para.split("\n");
         
         for(int i=0;i<correos.length;i++){
            Session session = Session.getDefaultInstance(props, null);
            //se verifica si hay archivos adjuntos
  /*stradjunto*/          if (!strAdjunto.equals("")){
                //cuerpo del mensaje 
                BodyPart texto = new MimeBodyPart();
                texto.setText(mensaje);
                //archivo adjunto
                BodyPart adjunto = new MimeBodyPart();
                
                adjunto.setDataHandler(
       /*stradjunto*/          new DataHandler(new FileDataSource(strAdjunto)));
  /*strNombreadd*/               adjunto.setFileName(strNombreadd);                
                /*MimeMultipart*/multiParte = new MimeMultipart();
               multiParte.addBodyPart(texto);
             multiParte.addBodyPart(adjunto);
            }
            MimeMessage message = new MimeMessage(session);
     /*usuario*/        message.setFrom(new InternetAddress(usuario));            
                    //emails
                    message.addRecipient(
                    Message.RecipientType.TO,
                      new InternetAddress(correos[i]));
                        message.setSubject(asunto);
                       //si hay adjunto hay multipoarte si no solo el cuerpo del mensaje
                        if (!strAdjunto.equals("")){
         /*strAdjunto*/                    message.setContent(multiParte);                                                        
                       }
            /*stradjunto*/             else if (strAdjunto.equals("")){
                            message.setText(mensaje);
                       }

                    Transport t = session.getTransport("smtp");
                 /*usuario clave*/    t.connect(usuario, clave);
                    t.sendMessage(message, message.getAllRecipients());
                    //barra progresiva que aumenta segun 
//                    JMailxView.barra_progreso.setMaximum(correos.length);
//                    JMailxView.barra_progreso.setValue(i);
                t.close();
//                JMailxView.mainPanel.update(JMailxView.mainPanel.getGraphics());
//                JMailxView.statusPanel.update(JMailxView.statusPanel.getGraphics());
              }
          if (correos.length==1){
               System.out.println(" El email ha sido enviado ");
            }
          else if (correos.length>1){
               System.out.println( " Los emails han sido enviados ");
          }
//            JMailxView.barra_progreso.setVisible(false);
//            JMailxView.statusAnimationLabel.setVisible(false);
         }
        catch (Exception enc)
        {

           System.out.println( " ERROR: No se pudo enviar el email ");
        }
    }
}
