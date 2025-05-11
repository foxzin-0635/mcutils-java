package net.mcutils.nbtedit_sim;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class MainTest {
	public void Window() {
		
		// Icon Randomizer
		String[] iconsArray = {
				".///res/tag_list.png",
				".///res/tag_base64_array.png", 
				".///res/tag_byte.png"
		};
		Random random = new Random();
		int randomIcon = random.nextInt(iconsArray.length);
		String selRandomIcon = iconsArray[randomIcon];
		
		//Window
		JFrame frame = new JFrame("NBT Editor (Simulation)");	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(new ImageIcon(selRandomIcon).getImage());
		frame.setSize(600, 400);
		
		//Window - Contents - TextBox
		JTextPane tPane = new JTextPane();
		tPane.setBackground(Color.DARK_GRAY);
		tPane.setCaretColor(Color.WHITE);
		tPane.setForeground(Color.WHITE);
		
		StyledDocument doc = tPane.getStyledDocument();
		Style style_list = tPane.addStyle("TAG_List_Style", null);
		StyleConstants.setFontSize(style_list, 16);
		StyleConstants.setForeground(style_list, Color.WHITE);
		
		Style style_base64_array = tPane.addStyle("TAG_Base64_Array_Style", null);
		StyleConstants.setFontSize(style_base64_array, 16);
		StyleConstants.setForeground(style_base64_array, Color.WHITE);
		
		Style style_byte = tPane.addStyle("TAG_Byte_Style", null);
		StyleConstants.setFontSize(style_byte, 16);
		StyleConstants.setForeground(style_byte, Color.WHITE);
		
		/*JTextField textField = new JTextField(20);
		textField.setBackground(Color.DARK_GRAY);
		textField.setCaretColor(Color.WHITE);
		textField.setForeground(Color.WHITE);
		textField.setBorder(new EmptyBorder(-300, 10, 30, 10));*/
		// Contents
		Container contentPane = frame.getContentPane();
		contentPane.add(tPane);
		
		frame.setVisible(true);
		
		String icon_list = "TAG_List";
		String icon_base64_array = "TAG_Base64_Array";
		String icon_byte = "TAG_Byte";
		
		Document doc2 = tPane.getDocument();
		doc2.addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				test();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			private void test() {
				Runnable doTest = new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						ImageIcon tag_list_icon = new ImageIcon(".///res/tag_list.png");
						Image un = tag_list_icon.getImage();
						Image tw = un.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
						tag_list_icon = new ImageIcon(tw);
						StyleConstants.setIcon(style_list, tag_list_icon);
						
						ImageIcon tag_base64_array_icon = new ImageIcon(".///res/tag_base64_array.png");
						Image un2 = tag_base64_array_icon.getImage();
						Image tw2 = un2.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
						tag_base64_array_icon = new ImageIcon(tw2);
						StyleConstants.setIcon(style_base64_array, tag_base64_array_icon);
						
						ImageIcon tag_byte_icon = new ImageIcon(".///res/tag_byte.png");
						Image un3 = tag_byte_icon.getImage();
						Image tw3 = un3.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
						tag_byte_icon = new ImageIcon(tw3);
						StyleConstants.setIcon(style_byte, tag_byte_icon);
						
						String text = tPane.getText();
			            String[] lines = text.split("\\n");
			            int currentIndex = 0;

			            for (String line : lines) {
			                if (line.contains(icon_list)) {
			                    int indexInLine = line.indexOf(icon_list);
			                    int actualIndex = currentIndex + indexInLine;
			                    try {
			                        doc.remove(actualIndex, icon_list.length());
			                        doc.insertString(actualIndex, " ", style_list);
			                    } catch (BadLocationException e2) {
			                        e2.printStackTrace();
			                    }
			                }
			                if (line.contains(icon_base64_array)) {
			                    int indexInLine = line.indexOf(icon_base64_array);
			                    int actualIndex = currentIndex + indexInLine;
			                    try {
			                        doc.remove(actualIndex, icon_base64_array.length());
			                        doc.insertString(actualIndex, " ", style_base64_array);
			                    } catch (BadLocationException e2) {
			                        e2.printStackTrace();
			                    }
			                }
			                if (line.contains(icon_byte)) {
			                    int indexInLine = line.indexOf(icon_byte);
			                    int actualIndex = currentIndex + indexInLine;
			                    try {
			                        doc.remove(actualIndex, icon_byte.length());
			                        doc.insertString(actualIndex, " ", style_byte);
			                    } catch (BadLocationException e2) {
			                        e2.printStackTrace();
			                    }
			                }
			                currentIndex += line.length() + 1; // +1 para o caractere de nova linha
			            }
						
						
						/*
						Pattern pt = Pattern.compile(icon_list);
						Matcher mt = pt.matcher(tPane.getText());
						
						Pattern pt2 = Pattern.compile(icon_base64_array);
						Matcher mt2 = pt2.matcher(tPane.getText());
						
						Pattern pt3 = Pattern.compile(icon_byte);
						Matcher mt3 = pt3.matcher(tPane.getText());
						if (mt.find()) {
							try {
								int idx = tPane.getText().indexOf(icon_list);
								if (idx != -1) {
									doc.remove(idx, icon_list.length());
									doc.insertString(idx, " ", style_list);
								}
							} catch (BadLocationException e2) {
								e2.printStackTrace();
							}
						}
						if (mt2.find()) {
							try {
								int idx = tPane.getText().indexOf(icon_base64_array);
								if (idx != -1) {
									doc.remove(idx, icon_base64_array.length());
									doc.insertString(idx, " ", style_base64_array);
								}
							} catch (BadLocationException e2) {
								e2.printStackTrace();
							}
						}
						if (mt3.find()) {
							try {
								int idx = tPane.getText().indexOf(icon_byte);
								if (idx != -1) {
									doc.remove(idx, icon_byte.length());
									doc.insertString(idx, " ", style_byte);
								}
							} catch (BadLocationException e2) {
								e2.printStackTrace();
							}
						}*/
					}
				};
				SwingUtilities.invokeLater(doTest);
			}
			
		});
		
	}
	private void WindowDebug() {

		// Icon Randomizer
		String[] iconsArray = {
				".///res/tag_list.png",
				".///res/tag_base64_array.png", 
				".///res/tag_byte.png"
		};
		Random random = new Random();
		int randomIcon = random.nextInt(iconsArray.length);
		String selRandomIcon = iconsArray[randomIcon];
		
		//Window
		JFrame frame = new JFrame("NBT Editor (Simulation)");	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(new ImageIcon(selRandomIcon).getImage());
		frame.setSize(600, 400);
		
		//Window - Contents - TextBox
		JTextPane tPane = new JTextPane();
		tPane.setBackground(Color.DARK_GRAY);
		tPane.setCaretColor(Color.WHITE);
		tPane.setForeground(Color.WHITE);
		
		StyledDocument doc = tPane.getStyledDocument();
		Style style_list = tPane.addStyle("TAG_List_Style", null);
		StyleConstants.setFontSize(style_list, 16);
		StyleConstants.setForeground(style_list, Color.WHITE);
		
		Style style_base64_array = tPane.addStyle("TAG_Base64_Array_Style", null);
		StyleConstants.setFontSize(style_base64_array, 16);
		StyleConstants.setForeground(style_base64_array, Color.WHITE);
		
		Style style_byte = tPane.addStyle("TAG_Byte_Style", null);
		StyleConstants.setFontSize(style_byte, 16);
		StyleConstants.setForeground(style_byte, Color.WHITE);
		
		/*JTextField textField = new JTextField(20);
		textField.setBackground(Color.DARK_GRAY);
		textField.setCaretColor(Color.WHITE);
		textField.setForeground(Color.WHITE);
		textField.setBorder(new EmptyBorder(-300, 10, 30, 10));*/
		// Contents
		Container contentPane = frame.getContentPane();
		contentPane.add(tPane);
		
		frame.setVisible(true);
		
		String icon_list = "TAG_List";
		String icon_base64_array = "TAG_Base64_Array";
		String icon_byte = "TAG_Byte";
		Pattern pt = Pattern.compile(icon_list);
		Pattern pt2 = Pattern.compile(icon_base64_array);
		Pattern pt3 = Pattern.compile(icon_byte);
		
		int curIdx = 0;
		
		Document doc2 = tPane.getDocument();
		doc2.addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				test();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			private void test() {
				Runnable doTest = new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						ImageIcon tag_list_icon = new ImageIcon(".///res/tag_list.png");
						Image un = tag_list_icon.getImage();
						Image tw = un.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
						tag_list_icon = new ImageIcon(tw);
						StyleConstants.setIcon(style_list, tag_list_icon);
						
						ImageIcon tag_base64_array_icon = new ImageIcon(".///res/tag_base64_array.png");
						Image un2 = tag_base64_array_icon.getImage();
						Image tw2 = un2.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
						tag_base64_array_icon = new ImageIcon(tw2);
						StyleConstants.setIcon(style_base64_array, tag_base64_array_icon);
						
						ImageIcon tag_byte_icon = new ImageIcon(".///res/tag_byte.png");
						Image un3 = tag_byte_icon.getImage();
						Image tw3 = un3.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
						tag_byte_icon = new ImageIcon(tw3);
						StyleConstants.setIcon(style_byte, tag_byte_icon);
						
						/*String text = tPane.getText();
			            String[] lines = text.split("\\n");
			            int currentIndex = 0;

			            for (String line : lines) {
			                if (line.contains(icon_list)) {
			                    int indexInLine = line.indexOf(icon_list);
			                    int actualIndex = currentIndex + indexInLine;
			                    try {
			                        doc.remove(actualIndex, icon_list.length());
			                        doc.insertString(actualIndex, " ", style_list);
			                    } catch (BadLocationException e2) {
			                        e2.printStackTrace();
			                    }
			                }
			                if (line.contains(icon_base64_array)) {
			                    int indexInLine = line.indexOf(icon_base64_array);
			                    int actualIndex = currentIndex + indexInLine;
			                    try {
			                        doc.remove(actualIndex, icon_base64_array.length());
			                        doc.insertString(actualIndex, " ", style_base64_array);
			                    } catch (BadLocationException e2) {
			                        e2.printStackTrace();
			                    }
			                }
			                if (line.contains(icon_byte)) {
			                    int indexInLine = line.indexOf(icon_byte);
			                    int actualIndex = currentIndex + indexInLine;
			                    try {
			                        doc.remove(actualIndex, icon_byte.length());
			                        doc.insertString(actualIndex, " ", style_byte);
			                    } catch (BadLocationException e2) {
			                        e2.printStackTrace();
			                    }
			                }
			                currentIndex += line.length() + 1; // +1 para o caractere de nova linha
			            }*/
						
						
						
						Matcher mt = pt.matcher(tPane.getText());
						Matcher mt2 = pt2.matcher(tPane.getText());
						Matcher mt3 = pt3.matcher(tPane.getText());
						if (mt.find()) {
							try {
								int idx = tPane.getText().indexOf(icon_list);
								if (idx != -1) {
									doc.remove(idx, icon_list.length());
									doc.insertString(idx, " ", style_list);
								}
							} catch (BadLocationException e2) {
								e2.printStackTrace();
							}
						}
						if (mt2.find()) {
							try {
								int idx = tPane.getText().indexOf(icon_base64_array);
								if (idx != -1) {
									doc.remove(idx, icon_base64_array.length());
									doc.insertString(idx, " ", style_base64_array);
								}
							} catch (BadLocationException e2) {
								e2.printStackTrace();
							}
						}
						if (mt3.find()) {
							String[] lines = tPane.getText().split("\n");
							
							for (String line : lines) {
								int idx = mt3.start();
								int idxline = curIdx + idx - lines.length;
								
								try {
									doc.remove(idxline, icon_byte.length());
									doc.insertString(curIdx, " ", style_byte);
									System.out.println(style_byte.toString());
									System.out.println(doc.toString());
								} catch (BadLocationException e) {
									e.printStackTrace();
								}
								
							}
						}
						/*if (mt3.find()) {
							try {
								//int idx = tPane.getText().indexOf(icon_byte);
								if (idx != -1) {
									//doc.remove(idx, icon_byte.length());
									//doc.insertString(idx, " ", style_byte);
								}
								String[] lines = tPane.getText().split("\n");
								int curIdx = 0;
								
								for (String line : lines) {
									int idx = mt3.start();
									if (idx != -1) {
										int idxline = curIdx + idx;
										System.out.println(line + " " + idxline);
										doc.remove(idxline, icon_byte.length());
										doc.insertString(idxline, " ", style_byte);
									}
									curIdx += line.length() + 1;
								}
							} catch (BadLocationException e2) {
								e2.printStackTrace();
							}
						}*/
					}
				};
				SwingUtilities.invokeLater(doTest);
			}
			
		});
		
	}
	public static void main(String[] args) {
		new MainTest().WindowDebug();
	}
}
