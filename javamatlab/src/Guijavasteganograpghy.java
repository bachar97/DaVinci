/* @authors
 * Bachar Sabra
 * Kifah Naim
 */

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;
import matlabcontrol.MatlabProxy;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JSeparator;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Toolkit;

public class Guijavasteganograpghy extends JFrame  implements ActionListener{
	public JLabel filetext;
	public static MatlabEngine eng ;
	public String pathcover= "",pathpasscover ="", pathpasscoverdecode="";
	public String pathencrypted= "";
	public String imagepathencrypted= "";
	public String imagepathdecrypted= "", embedimagepathencrypted ="" ;
	public JButton coverfile ;
	public JPanel contentPane;
	public JButton decode, encode;
	public JPanel textdecode, textencode;
	public String texthide;
	public MatlabProxy proxy;
	public JTextField text;
	public Future<String> secreturl;
	public ImageIcon imageIcon;
	public JLabel imagebefore;
	public JLabel imageencrypted;
	public JLabel filetext_1 ;
	public String textoutput;
	public JLabel Textdecrypted;
	public JButton encryptImageInImage, decryptImageInImage;
	public JLabel embedimagefiletext, imagefiletext,fileencryptedimage;
	public String encryptedimagex,encryptedimagepass,decryptedimagepass;
	private JTextField textpass;
	private JTextField passwordencrypttext;
	private JTextField passwordtextdecode;

	public Guijavasteganograpghy(java.util.ArrayList<java.lang.Integer> x, java.util.ArrayList<java.lang.Integer> y) {
		System.out.println("called from matlab");
		System.out.println(x);
		System.out.println(y);
	}
	
	public static void main(String[] args) throws Exception {
		String[] engines = MatlabEngine.findMatlab();
		eng = MatlabEngine.connectMatlab(engines[0]);
		// Change directory according to project location
		eng.eval("cd 'C:\\Users\\Administrator\\My Files\\Year 3\\SEM.2\\Software Development Technics under MATLAB\\DaVinci' ");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Guijavasteganograpghy frame = new Guijavasteganograpghy();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	public void setDefaultCloseOperation(int operation) {
		this.dispose();
	}
	
	public Guijavasteganograpghy() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Guijavasteganograpghy.class.getResource("/Images/icon.jpg")));
		setResizable(false);
		setTitle("DaVinci");
		setBounds(100, 100, 1196, 782);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 52, 76));
		panel.setBounds(0, 0, 296, 752);
		contentPane.add(panel);
		
		JLabel Steganographylabel = new JLabel("DaVinci", SwingConstants.LEFT);
		Steganographylabel.setForeground(Color.WHITE);
		Steganographylabel.setFont(new Font("Georgia", Font.BOLD, 15));
		Steganographylabel.setBounds(85, 0, 131, 100);
		panel.add(Steganographylabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(32, 98, 198, 2);
		panel.add(separator);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(38, 69, 122));
		panel_1_1.setBounds(0, 160, 296, 44);
		panel.add(panel_1_1);
		panel_1_1.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel picLabel = new JLabel((Icon) null);
		panel_1_1.add(picLabel);
		JPanel panel_1_1_1_1_1 = new JPanel();
		panel_1_1_1_1_1.setBackground(new Color(38, 69, 122));
		panel_1_1_1_1_1.setBounds(0, 365, 296, 44);
		panel.add(panel_1_1_1_1_1);
		panel_1_1_1_1_1.setLayout(new FlowLayout(FlowLayout.LEFT));		
		
		JPanel panel_1_1_1_1 = new JPanel();
		panel_1_1_1_1.setBackground(new Color(38, 69, 122));
		panel_1_1_1_1.setBounds(0, 298, 296, 44);
		panel.add(panel_1_1_1_1);
		panel_1_1_1_1.setLayout(new FlowLayout(FlowLayout.LEFT));
		encryptImageInImage = new JButton("Image in image encode");

		encryptImageInImage.setForeground(Color.BLACK);
		encryptImageInImage.setFocusPainted(false);
		encryptImageInImage.setContentAreaFilled(false);
		encryptImageInImage.setBorderPainted(false);
		encryptImageInImage.setBackground(new Color(38, 69, 122));
		panel_1_1_1_1.add(encryptImageInImage);
		
		decryptImageInImage = new JButton("Image in image decode");
		decryptImageInImage.setForeground(Color.BLACK);
		decryptImageInImage.setFocusPainted(false);
		decryptImageInImage.setContentAreaFilled(false);
		decryptImageInImage.setBorderPainted(false);
		decryptImageInImage.setBackground(new Color(38, 69, 122));
		panel_1_1_1_1_1.add(decryptImageInImage);
		
		encode = new JButton("Text in image encode");

		encode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				encode.setCursor(new Cursor(Cursor.HAND_CURSOR));
				encode.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent me) {
				encode.setForeground(Color.BLACK);
			}
		});
		
		encode.setForeground(Color.BLACK);
		encode.setFocusPainted(false);
		encode.setContentAreaFilled(false);
		encode.setBorderPainted(false);
		encode.setBackground(new Color(38, 69, 122));
		panel_1_1.add(encode);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBackground(new Color(38, 69, 122));
		panel_1_1_1.setBounds(0, 228, 296, 44);
		panel.add(panel_1_1_1);
		panel_1_1_1.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel picLabel_1 = new JLabel((Icon) null);
		panel_1_1_1.add(picLabel_1);
		
		decode = new JButton("Text in image decode");

		decode.addMouseListener(new MouseAdapter(){
			 @Override
		        public void mouseEntered(MouseEvent e) {
				 decode.setCursor(new Cursor(Cursor.HAND_CURSOR));
				 decode.setForeground(Color.WHITE);
		        }
			   public void mouseExited(MouseEvent me){
				   decode.setForeground(Color.BLACK);
			      }
		});
		
		encryptImageInImage.addMouseListener(new MouseAdapter(){
			 @Override
		        public void mouseEntered(MouseEvent e) {
				 encryptImageInImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
				 encryptImageInImage.setForeground(Color.WHITE);
		        }
			   public void mouseExited(MouseEvent me){
				   encryptImageInImage.setForeground(Color.BLACK);
			      }
		});
		
		decryptImageInImage.addMouseListener(new MouseAdapter(){
			 @Override
		        public void mouseEntered(MouseEvent e) {
				 decryptImageInImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
				 decryptImageInImage.setForeground(Color.WHITE);
		        }
			   public void mouseExited(MouseEvent me){
				   decryptImageInImage.setForeground(Color.BLACK);
			      }
		});
		
		decode.setForeground(Color.BLACK);
		decode.setFocusPainted(false);
		decode.setContentAreaFilled(false);
		decode.setBorderPainted(false);
		decode.setBackground(new Color(38, 69, 122));
		panel_1_1_1.add(decode);
	
		JLabel picLabel_1_1 = new JLabel((Icon) null);
		panel_1_1_1_1.add(picLabel_1_1);

		JLabel picLabel_1_1_1 = new JLabel((Icon) null);
		panel_1_1_1_1_1.add(picLabel_1_1_1);
		
		JPanel panel_1_1_2 = new JPanel();
		panel_1_1_2.setBackground(new Color(38, 69, 122));
		panel_1_1_2.setBounds(0, 430, 296, 44);
		panel.add(panel_1_1_2);
		panel_1_1_2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel picLabel_2 = new JLabel((Icon) null);
		panel_1_1_2.add(picLabel_2);
		
		final JButton btnTextInImage = new JButton("Text in image encode using password");

		btnTextInImage.setForeground(Color.BLACK);
		btnTextInImage.setFocusPainted(false);
		btnTextInImage.setContentAreaFilled(false);
		btnTextInImage.setBorderPainted(false);
		btnTextInImage.setBackground(new Color(38, 69, 122));
		panel_1_1_2.add(btnTextInImage);
		
		JPanel panel_1_1_2_1 = new JPanel();
		panel_1_1_2_1.setBackground(new Color(38, 69, 122));
		panel_1_1_2_1.setBounds(0, 496, 296, 44);
		panel.add(panel_1_1_2_1);
		panel_1_1_2_1.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel picLabel_2_1 = new JLabel((Icon) null);
		panel_1_1_2_1.add(picLabel_2_1);
		
		final JButton btnTextInImage_2 = new JButton("Text in image decode using password");

		btnTextInImage_2.setForeground(Color.BLACK);
		btnTextInImage_2.setFocusPainted(false);
		btnTextInImage_2.setContentAreaFilled(false);
		btnTextInImage_2.setBorderPainted(false);
		btnTextInImage_2.setBackground(new Color(38, 69, 122));
		panel_1_1_2_1.add(btnTextInImage_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(295, 0, 885, 743);
		contentPane.add(panel_2);
		panel_2.setLayout(new CardLayout(0, 0));
		
		textencode = new JPanel();
		textencode.setLayout(null);
		textencode.setBackground(Color.WHITE);
		panel_2.add(textencode, "name_2694738452300");
		
		JLabel lblNewLabel = new JLabel("Choose a cover photo");
		lblNewLabel.setFont(new Font("Elephant", Font.PLAIN, 14));
		lblNewLabel.setBounds(73, 75, 147, 26);
		textencode.add(lblNewLabel);
		
		JButton coverfile = new JButton("Open");
		coverfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		coverfile = new JButton("Open");
		
		coverfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fc = new JFileChooser();
				int i = fc.showOpenDialog(textdecode);
				if (i == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					String filepath = f.getPath();
					try {
						BufferedReader br = new BufferedReader(new FileReader(filepath));
						String s1 = "", s2 = "";
						while ((s1 = br.readLine()) != null) {
							s2 += s1 + "\n";
						}
						pathcover = fc.getSelectedFile().getAbsolutePath();
						filetext.setText(fc.getSelectedFile().getAbsolutePath());
						eng.putVariableAsync("pathcover", pathcover);
						ImageIcon image = new ImageIcon(
								new ImageIcon(pathcover).getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
						imagebefore.setIcon(image);
						System.out.print(coverimage());
						br.close();

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

			}
		});
			
		coverfile.setBounds(104, 117, 89, 23);		
		textencode.add(coverfile);
		
		filetext = new JLabel("");
		filetext.setBounds(64, 151, 129, 14);
		textencode.add(filetext);
		
		JLabel lblTypeTheText = new JLabel("Type the text you need to hide");
		lblTypeTheText.setFont(new Font("Elephant", Font.PLAIN, 14));
		lblTypeTheText.setBounds(73, 177, 234, 26);
		textencode.add(lblTypeTheText);
		
		JButton Submit = new JButton("Submit");
		Submit.setBounds(104, 332, 89, 23);
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text.getText().length() > 0 && filetext.getText().length() > 0) {
					texthide = text.getText();
					new Thread(new Runnable() {
						public void run() {
							try {
								eng.putVariable("texthide", texthide);
							} catch (CancellationException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (EngineException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (IllegalStateException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (InterruptedException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (ExecutionException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							try {
								System.out.print("before function");
								encryptedimagex = eng.feval("functry");
								System.out.print("after function");
								System.out.println(encryptedimagex);
								imageIcon = new ImageIcon(new ImageIcon(encryptedimagex).getImage()
										.getScaledInstance(200, 200, Image.SCALE_DEFAULT));
								JOptionPane.showMessageDialog(null, "Secret image", "Secret Image",
										JOptionPane.PLAIN_MESSAGE, imageIcon);
							} catch (RejectedExecutionException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (EngineException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ExecutionException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}).start();

				} else {
					JOptionPane.showMessageDialog(null, "Please choose an image and a text to hide", "Error",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		});

		textencode.add(Submit);	
		text = new JTextField();
		text.setColumns(10);
		text.setBounds(73, 214, 204, 87);
		textencode.add(text);
		
		imagebefore = new JLabel((Icon) null);
		imagebefore.setBounds(468, 39, 360, 209);
		textencode.add(imagebefore);
		
		textdecode = new JPanel();
		textdecode.setBackground(SystemColor.text);
		panel_2.add(textdecode, "name_2795629397000");
		textdecode.setLayout(null);
		
		JButton btnChoose = new JButton("Open");
		
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fc = new JFileChooser();
				int i = fc.showOpenDialog(textdecode);
				if (i == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					String filepath = f.getPath();
					try {
						BufferedReader br = new BufferedReader(new FileReader(filepath));
						String s1 = "", s2 = "";
						while ((s1 = br.readLine()) != null) {
							s2 += s1 + "\n";
						}
						pathencrypted = fc.getSelectedFile().getAbsolutePath();
						System.out.println(pathencrypted);
						filetext_1.setText(pathencrypted);
						eng.putVariableAsync("pathencrypted", pathencrypted);
						ImageIcon image = new ImageIcon(new ImageIcon(pathencrypted).getImage().getScaledInstance(250,
								250, Image.SCALE_SMOOTH));
						imageencrypted.setIcon(image);

						br.close();

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

			}
		});
		
		btnChoose.setBounds(104, 117, 89, 23);
		textdecode.add(btnChoose);
		
		JLabel lblChooseSecretImage = new JLabel("Choose secret photo");
		lblChooseSecretImage.setFont(new Font("Elephant", Font.PLAIN, 14));
		lblChooseSecretImage.setBounds(73, 75, 147, 26);
		textdecode.add(lblChooseSecretImage);
		
		imageencrypted = new JLabel((Icon) null);
		imageencrypted.setBounds(479, 22, 360, 209);
		textdecode.add(imageencrypted);
		
		Textdecrypted = new JLabel("");
		Textdecrypted.setBounds(56, 268, 205, 40);
		textdecode.add(Textdecrypted);
		
		filetext_1 = new JLabel("");
		filetext_1.setBounds(56, 140, 129, 14);
		textdecode.add(filetext_1);
		
		JButton Extract = new JButton("Extract text");
		
		Extract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (filetext_1.getText().length() > 0) {

					new Thread(new Runnable() {

						public void run() {

							try {
								String x = eng.feval("decryptfunc");
								Textdecrypted.setText(x.replaceFirst(".$", ""));

							} catch (RejectedExecutionException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (EngineException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ExecutionException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}).start();

				} else {
					JOptionPane.showMessageDialog(null, "Please choose an image to encrypt", "Error",
							JOptionPane.PLAIN_MESSAGE);
				}

			}
		});
		
		Extract.setBounds(73, 196, 129, 35);
		textdecode.add(Extract);
		
		JLabel lblNewLabel_1 = new JLabel("Please choose a previously encrypted image.");
		lblNewLabel_1.setBounds(223, 105, 289, 53);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_1.setForeground(Color.RED);
		textdecode.add(lblNewLabel_1);
		
		final JPanel imageencode = new JPanel();
		imageencode.setBackground(new Color(255, 255, 255));
		panel_2.add(imageencode, "name_72238389642800");
		imageencode.setLayout(null);
		
		JButton coverchooseimage = new JButton("Open");
		coverchooseimage.setBounds(104, 117, 89, 23);
		imageencode.add(coverchooseimage);
		
		JLabel lblChooseCoverPhoto = new JLabel("Choose cover photo");
		lblChooseCoverPhoto.setFont(new Font("Elephant", Font.PLAIN, 14));
		lblChooseCoverPhoto.setBounds(73, 75, 147, 26);
		imageencode.add(lblChooseCoverPhoto);
		
		JLabel lblChooseEmbeddedPhoto = new JLabel("Choose embedded photo");
		lblChooseEmbeddedPhoto.setFont(new Font("Elephant", Font.PLAIN, 14));
		lblChooseEmbeddedPhoto.setBounds(73, 181, 181, 26);
		imageencode.add(lblChooseEmbeddedPhoto);
		
		JButton embedchooseimage = new JButton("Open");
		embedchooseimage.setBounds(104, 231, 89, 23);
		imageencode.add(embedchooseimage);
		
		final JLabel imageinimageencrypted = new JLabel((Icon) null);
		imageinimageencrypted.setBounds(432, 11, 360, 171);
		imageencode.add(imageinimageencrypted);
		
		JButton submitimageinimage = new JButton("Submit");
		
		submitimageinimage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (imagefiletext.getText().length() > 0 && embedimagefiletext.getText().length() > 0) {
					new Thread(new Runnable() {
						public void run() {
							try {
								System.out.print("before function");
								eng.feval("embed");
								String imageencryptedhide = eng.getVariable("imageencrypted");
								imageIcon = new ImageIcon(new ImageIcon(imageencryptedhide).getImage()
										.getScaledInstance(200, 200, Image.SCALE_DEFAULT));
								JOptionPane.showMessageDialog(null, "Encrypted image", "Encrypted image",
										JOptionPane.PLAIN_MESSAGE, imageIcon);

							} catch (RejectedExecutionException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (EngineException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ExecutionException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}).start();
				} else {
					JOptionPane.showMessageDialog(null, "Please choose an image and an image to hide", "Error",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		submitimageinimage.setBounds(253, 379, 147, 42);
		imageencode.add(submitimageinimage);
		
		imagefiletext = new JLabel("");
		imagefiletext.setBounds(151, 151, 116, 19);
		imageencode.add(imagefiletext);
		
		final JLabel embedimageinimageencrypted = new JLabel((Icon) null);
		embedimageinimageencrypted.setBounds(432, 193, 360, 162);
		imageencode.add(embedimageinimageencrypted);
		
		embedimagefiletext = new JLabel("");
		embedimagefiletext.setBounds(151, 283, 116, 19);
		imageencode.add(embedimagefiletext);
		
		final JPanel imagedecode = new JPanel();
		imagedecode.setBackground(Color.WHITE);
		panel_2.add(imagedecode, "name_81188067218200");
		imagedecode.setLayout(null);
		
		JLabel lblChooseEmbeddedPhoto_1 = new JLabel("Choose embedded photo");
		lblChooseEmbeddedPhoto_1.setBounds(73, 75, 181, 26);
		lblChooseEmbeddedPhoto_1.setFont(new Font("Elephant", Font.PLAIN, 14));
		imagedecode.add(lblChooseEmbeddedPhoto_1);
		
		JButton decryptimageinimage = new JButton("Open");

		decryptimageinimage.setBounds(104, 117, 89, 23);
		imagedecode.add(decryptimageinimage);
		
		final JLabel imageinimagedecrypt = new JLabel((Icon) null);
		imageinimagedecrypt.setBounds(317, 24, 360, 171);
		imagedecode.add(imageinimagedecrypt);
		
		JButton extractimage = new JButton("Extract Image");
		
		extractimage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileencryptedimage.getText().length() > 0) {
					new Thread(new Runnable() {
						public void run() {
							try {
								System.out.print("before");
								String recovered = eng.feval("recover");

								imageIcon = new ImageIcon(new ImageIcon(recovered).getImage().getScaledInstance(200,
										200, Image.SCALE_DEFAULT));
								System.out.print("after");
								JOptionPane.showMessageDialog(null, "Recovered image", "Recovered Image",
										JOptionPane.PLAIN_MESSAGE, imageIcon);
							} catch (RejectedExecutionException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (EngineException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ExecutionException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}).start();
				} else {
					JOptionPane.showMessageDialog(null, "Please choose an image to decrypt", "Error",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		extractimage.setBounds(98, 205, 121, 39);
		imagedecode.add(extractimage);
		
		fileencryptedimage = new JLabel("");
		fileencryptedimage.setBounds(163, 80, 144, 18);
		imagedecode.add(fileencryptedimage);
		
		final JPanel textencodepassword = new JPanel();
		textencodepassword.setLayout(null);
		textencodepassword.setBackground(Color.WHITE);
		panel_2.add(textencodepassword, "name_160323865222700");
		
		JLabel lblNewLabel_2 = new JLabel("Choose a cover photo");
		lblNewLabel_2.setFont(new Font("Elephant", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(73, 75, 147, 26);
		textencodepassword.add(lblNewLabel_2);
		
		JButton choosecoverpass = new JButton("Open");

		choosecoverpass.setBounds(104, 117, 89, 23);
		textencodepassword.add(choosecoverpass);
		
		final JLabel filetext_2 = new JLabel("");
		filetext_2.setBounds(64, 151, 129, 14);
		textencodepassword.add(filetext_2);
		
		JLabel lblTypeTheText_1 = new JLabel("Type the text you need to hide");
		lblTypeTheText_1.setFont(new Font("Elephant", Font.PLAIN, 14));
		lblTypeTheText_1.setBounds(73, 177, 234, 26);
		textencodepassword.add(lblTypeTheText_1);
		
		JButton Submittextpass = new JButton("Submit");

		Submittextpass.setBounds(104, 432, 89, 23);
		textencodepassword.add(Submittextpass);
		
		textpass = new JTextField();
		textpass.setColumns(10);
		textpass.setBounds(73, 214, 204, 87);
		textencodepassword.add(textpass);
		
		final JLabel imagebeforepass = new JLabel((Icon) null);
		imagebeforepass.setBounds(468, 39, 360, 209);
		textencodepassword.add(imagebeforepass);
		
		passwordencrypttext = new JTextField();
		passwordencrypttext.setBounds(73, 369, 204, 42);
		textencodepassword.add(passwordencrypttext);
		passwordencrypttext.setColumns(10);
		
		JLabel lblTypeTheText_1_1 = new JLabel("Enter the password ");
		lblTypeTheText_1_1.setFont(new Font("Elephant", Font.PLAIN, 14));
		lblTypeTheText_1_1.setBounds(73, 332, 234, 26);
		textencodepassword.add(lblTypeTheText_1_1);
		
		final JPanel textdecodepassword = new JPanel();
		textdecodepassword.setLayout(null);
		textdecodepassword.setBackground(Color.WHITE);
		panel_2.add(textdecodepassword, "name_163800017326100");
		
		JLabel lblNewLabel_2_1 = new JLabel("Choose encrypted image with password");
		lblNewLabel_2_1.setFont(new Font("Elephant", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(73, 75, 272, 31);
		textdecodepassword.add(lblNewLabel_2_1);
		
		JButton choosecoverpassdecode = new JButton("Open");
		choosecoverpassdecode.setBounds(104, 117, 89, 23);
		textdecodepassword.add(choosecoverpassdecode);
		
		final JLabel filetext_2_1 = new JLabel("");
		filetext_2_1.setBounds(64, 151, 129, 14);
		textdecodepassword.add(filetext_2_1);
		
		JButton Submittextpass_1 = new JButton("Submit");
		Submittextpass_1.setBounds(112, 280, 89, 23);
		textdecodepassword.add(Submittextpass_1);
		
		final JLabel imagebeforepassdecode = new JLabel((Icon) null);
		imagebeforepassdecode.setBounds(468, 39, 360, 209);
		textdecodepassword.add(imagebeforepassdecode);
		
		choosecoverpassdecode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int i = fc.showOpenDialog(textdecode);
				if (i == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					String filepath = f.getPath();
					try {
						BufferedReader br = new BufferedReader(new FileReader(filepath));
						String s1 = "", s2 = "";
						while ((s1 = br.readLine()) != null) {
							s2 += s1 + "\n";
						}
						pathpasscoverdecode = fc.getSelectedFile().getAbsolutePath();
						filetext_2_1.setText(fc.getSelectedFile().getAbsolutePath());

						eng.putVariableAsync("pathpasscoverdecode", pathpasscoverdecode);
						ImageIcon image = new ImageIcon(new ImageIcon(pathpasscoverdecode).getImage()
								.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
						imagebeforepassdecode.setIcon(image);
						System.out.print(coverimage());
						br.close();

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		
		passwordtextdecode = new JTextField();
		passwordtextdecode.setColumns(10);
		passwordtextdecode.setBounds(73, 214, 204, 42);
		textdecodepassword.add(passwordtextdecode);
		
		JLabel lblTypeTheText_1_1_1 = new JLabel("Enter the password ");
		lblTypeTheText_1_1_1.setFont(new Font("Elephant", Font.PLAIN, 14));
		lblTypeTheText_1_1_1.setBounds(73, 177, 234, 26);
		textdecodepassword.add(lblTypeTheText_1_1_1);
		
		final JLabel textdecodedpass = new JLabel("");
		textdecodedpass.setBounds(124, 381, 225, 42);
		textdecodepassword.add(textdecodedpass);
		
		choosecoverpass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int i = fc.showOpenDialog(textdecode);
				if (i == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					String filepath = f.getPath();
					try {
						BufferedReader br = new BufferedReader(new FileReader(filepath));
						String s1 = "", s2 = "";
						while ((s1 = br.readLine()) != null) {
							s2 += s1 + "\n";
						}
						pathpasscover = fc.getSelectedFile().getAbsolutePath();
						filetext_2.setText(fc.getSelectedFile().getAbsolutePath());

						eng.putVariableAsync("pathpasscover", pathpasscover);
						ImageIcon image = new ImageIcon(new ImageIcon(pathpasscover).getImage().getScaledInstance(250,
								250, Image.SCALE_SMOOTH));
						imagebeforepass.setIcon(image);
						System.out.print(coverimage());
						br.close();

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		
		coverchooseimage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int i = fc.showOpenDialog(textdecode);
				if (i == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					String filepath = f.getPath();
					try {
						BufferedReader br = new BufferedReader(new FileReader(filepath));
						String s1 = "", s2 = "";
						while ((s1 = br.readLine()) != null) {
							s2 += s1 + "\n";
						}
						imagepathencrypted = fc.getSelectedFile().getAbsolutePath();
						System.out.println(imagepathencrypted);
						imagefiletext.setText(imagepathencrypted);
						eng.putVariableAsync("imagepathencrypted", imagepathencrypted);
						ImageIcon image = new ImageIcon(new ImageIcon(imagepathencrypted).getImage()
								.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
						imageinimageencrypted.setIcon(image);

						br.close();

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		embedchooseimage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int i = fc.showOpenDialog(textdecode);
				if (i == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					String filepath = f.getPath();
					try {
						BufferedReader br = new BufferedReader(new FileReader(filepath));
						String s1 = "", s2 = "";
						while ((s1 = br.readLine()) != null) {
							s2 += s1 + "\n";
						}
						embedimagepathencrypted = fc.getSelectedFile().getAbsolutePath();
						System.out.println(embedimagepathencrypted);
						embedimagefiletext.setText(embedimagepathencrypted);
						eng.putVariableAsync("embedimagepathencrypted", embedimagepathencrypted);
						ImageIcon image = new ImageIcon(new ImageIcon(embedimagepathencrypted).getImage()
								.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
						embedimageinimageencrypted.setIcon(image);

						br.close();

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		
		decryptimageinimage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fc = new JFileChooser();
				int i = fc.showOpenDialog(textdecode);
				if (i == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					String filepath = f.getPath();
					try {
						BufferedReader br = new BufferedReader(new FileReader(filepath));
						String s1 = "", s2 = "";
						while ((s1 = br.readLine()) != null) {
							s2 += s1 + "\n";
						}
						String pathimageinimageencrypted = fc.getSelectedFile().getAbsolutePath();
						System.out.println(pathimageinimageencrypted);
						fileencryptedimage.setText(pathimageinimageencrypted);
						eng.putVariableAsync("patheimagefordecryption", pathimageinimageencrypted);
						ImageIcon image = new ImageIcon(new ImageIcon(pathimageinimageencrypted).getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
						imageinimagedecrypt.setIcon(image);
						br.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		
		decryptImageInImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textencode.setVisible(false);
				textdecode.setVisible(false);
				imageencode.setVisible(false);
				imagedecode.setVisible(true);
				textdecodepassword.setVisible(false);
				textencodepassword.setVisible(false);
			}
		});
		encryptImageInImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textencode.setVisible(false);
				textdecode.setVisible(false);
				imageencode.setVisible(true);
				imagedecode.setVisible(false);
				textdecodepassword.setVisible(false);
				textencodepassword.setVisible(false);
			}
		});
		encode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textencode.setVisible(true);
				textdecode.setVisible(false);
				imageencode.setVisible(false);
				imagedecode.setVisible(false);
				textdecodepassword.setVisible(false);
				textencodepassword.setVisible(false);
			}
		});
	
		decode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		 		textencode.setVisible(false);
		 		textdecode.setVisible(true);
				imageencode.setVisible(false);
				imagedecode.setVisible(false);
				textdecodepassword.setVisible(false);
				textencodepassword.setVisible(false);
		 	}
		 });
	 		 
		btnTextInImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textencodepassword.setVisible(true);
				textdecodepassword.setVisible(false);
				textencode.setVisible(false);
				textdecode.setVisible(false);
				imageencode.setVisible(false);
				imagedecode.setVisible(false);
			}
		});
							
		btnTextInImage_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textdecodepassword.setVisible(true);
				textencode.setVisible(false);
				textdecode.setVisible(false);
				imageencode.setVisible(false);
				imagedecode.setVisible(false);
				textencodepassword.setVisible(false);
			}
		});
		
		btnTextInImage.addMouseListener(new MouseAdapter(){
			 @Override
		        public void mouseEntered(MouseEvent e) {
				 btnTextInImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
				 btnTextInImage.setForeground(Color.WHITE);
		        }
			   public void mouseExited(MouseEvent me){
				   btnTextInImage.setForeground(Color.BLACK);
			      }			
		});
		
		btnTextInImage_2.addMouseListener(new MouseAdapter(){
			 @Override
		        public void mouseEntered(MouseEvent e) {
				 btnTextInImage_2.setCursor(new Cursor(Cursor.HAND_CURSOR));
				 btnTextInImage_2.setForeground(Color.WHITE);
		        }
			   public void mouseExited(MouseEvent me){
				   btnTextInImage_2.setForeground(Color.BLACK);
			      }			
		});
								
		Submittextpass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (filetext_2.getText().length() > 0 && textpass.getText().length() > 0
						&& passwordencrypttext.getText().length() > 0) {
					if (passwordencrypttext.getText().length() == 6) {
						new Thread(new Runnable() {
							String texthidepass = textpass.getText();
							String passwordencrypt = passwordencrypttext.getText();

							public void run() {
								try {
									eng.putVariable("texthidepass", texthidepass);
									eng.putVariable("passwordencrypt", passwordencrypt);
								} catch (CancellationException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								} catch (EngineException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								} catch (IllegalStateException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								} catch (InterruptedException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								} catch (ExecutionException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								try {
									System.out.print("before function");
									encryptedimagepass = eng.feval("encode2");
									System.out.print("after function");
									System.out.println(encryptedimagepass);
									imageIcon = new ImageIcon(new ImageIcon(encryptedimagepass).getImage()
											.getScaledInstance(200, 200, Image.SCALE_DEFAULT));
									JOptionPane.showMessageDialog(null, "Secret image", "Secret Image",
											JOptionPane.PLAIN_MESSAGE, imageIcon);
								} catch (RejectedExecutionException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (EngineException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (ExecutionException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}).start();
					} else {
						JOptionPane.showMessageDialog(null, "Your password length should approximately be 6 characters",
								"Error", JOptionPane.PLAIN_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please choose an image and a text with a password to hide",
							"Error", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});

		Submittextpass_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (filetext_2_1.getText().length() > 0 && passwordtextdecode.getText().length() > 0) {
					new Thread(new Runnable() {
						String passwordecode = passwordtextdecode.getText();

						public void run() {
							try {
								eng.putVariable("passwordecode", passwordecode);
							} catch (CancellationException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (EngineException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (IllegalStateException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (InterruptedException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (ExecutionException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							try {
								System.out.print("before function");
								decryptedimagepass = eng.feval("decode2");
								textdecodedpass.setText(decryptedimagepass.replaceFirst(".$", ""));
								final String x = decryptedimagepass.replaceFirst(".$", "");
								System.out.println(x);
								if (x.matches(".[a-zA-Z]+.")) {
									textdecodedpass.setText(decryptedimagepass.replaceFirst(".$", ""));
								} else {
									JOptionPane.showMessageDialog(null,
											"Error occured while decrypting, Please try again with a different password!", "Error",
											JOptionPane.PLAIN_MESSAGE);
								}
							} catch (RejectedExecutionException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (EngineException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ExecutionException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}).start();

				} else {
					JOptionPane.showMessageDialog(null, "Please choose an image and type the password to decrypt","Error",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
	}
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public String coverimage() {		
		return pathcover;
	}
}
