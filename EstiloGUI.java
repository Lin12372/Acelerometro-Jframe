import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class EstiloGUI {
    
    public static void aplicarEstilo(JFrame frame) {
        // Definir cores principais (Tema moderno escuro)
        Color corFundo = new Color(18, 18, 18); // Preto suave
        Color corDestaque = new Color(0, 184, 148); // Turquesa vibrante
        Color corTexto = new Color(230, 230, 230); // Branco suave
        Color corBotao1 = new Color(0, 184, 148); // Turquesa (tempo)
        Color corBotao2 = new Color(108, 92, 231); // Roxo azulado (passos)
        Color corBotao3 = new Color(45, 45, 45); // Cinza escuro (limpar)
        Color corBotaoHover = new Color(24, 220, 180); // Turquesa claro
        
        // Aplicar cor de fundo
        frame.getContentPane().setBackground(corFundo);
        
        // Coletar todos os componentes
        aplicarEstiloRecursivo(frame.getContentPane(), corFundo, corDestaque, 
                             corTexto, corBotao1, corBotao2, corBotao3, corBotaoHover);
    }
    
    private static void aplicarEstiloRecursivo(Container container, 
                                              Color corFundo, Color corDestaque, 
                                              Color corTexto, Color corBotao1,
                                              Color corBotao2, Color corBotao3,
                                              Color corBotaoHover) {
        
        for (Component comp : container.getComponents()) {
            // Aplicar estilo a JLabels
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                label.setForeground(corTexto);
                label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
            }
            
            // Aplicar estilo a JTextFields
            else if (comp instanceof JTextField) {
                JTextField textField = (JTextField) comp;
                textField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                textField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(corDestaque, 2),
                    BorderFactory.createEmptyBorder(8, 10, 8, 10)
                ));
                textField.setBackground(new Color(30, 30, 30));
                textField.setForeground(corTexto);
                textField.setCaretColor(corDestaque);
            }
            
            // Aplicar estilo a JComboBox
            else if (comp instanceof JComboBox) {
                JComboBox<?> comboBox = (JComboBox<?>) comp;
                comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                comboBox.setBackground(new Color(30, 30, 30));
                comboBox.setForeground(corTexto);
                comboBox.setBorder(BorderFactory.createLineBorder(corDestaque, 2));
            }
            
            // Aplicar estilo a JButtons
            else if (comp instanceof JButton) {
                JButton botao = (JButton) comp;
                botao.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
                botao.setForeground(Color.WHITE);
                botao.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(corDestaque, 2),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
                ));
                botao.setFocusPainted(false);
                botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
                
                // Definir cores específicas para cada botão
                if (botao.getText().contains("Tempo")) {
                    botao.setBackground(corBotao1);
                } else if (botao.getText().contains("Passos")) {
                    botao.setBackground(corBotao2);
                } else if (botao.getText().contains("Limpar")) {
                    botao.setBackground(corBotao3);
                    botao.setForeground(new Color(200, 200, 200));
                }
                
                // Adicionar efeito hover
                botao.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        botao.setBackground(corBotaoHover);
                        botao.setForeground(Color.WHITE);
                    }
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        if (botao.getText().contains("Tempo")) {
                            botao.setBackground(corBotao1);
                        } else if (botao.getText().contains("Passos")) {
                            botao.setBackground(corBotao2);
                        } else if (botao.getText().contains("Limpar")) {
                            botao.setBackground(corBotao3);
                            botao.setForeground(new Color(200, 200, 200));
                        }
                    }
                });
            }
            
            // Aplicar estilo a JTextArea
            else if (comp instanceof JTextArea) {
                JTextArea textArea = (JTextArea) comp;
                textArea.setFont(new Font("Consolas", Font.PLAIN, 13));
                textArea.setBackground(new Color(25, 25, 25));
                textArea.setForeground(corTexto);
                textArea.setCaretColor(corDestaque);
                textArea.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(corDestaque, 2),
                    BorderFactory.createEmptyBorder(12, 12, 12, 12)
                ));
            }
            
            // Aplicar estilo a TitledBorders
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                Border border = panel.getBorder();
                if (border instanceof TitledBorder) {
                    TitledBorder titledBorder = (TitledBorder) border;
                    titledBorder.setTitleFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
                    titledBorder.setTitleColor(corDestaque);
                }
                panel.setBackground(corFundo);
            }
            
            // Aplicar recursivamente a containers filhos
            if (comp instanceof Container) {
                aplicarEstiloRecursivo((Container) comp, corFundo, corDestaque, 
                                     corTexto, corBotao1, corBotao2, corBotao3, corBotaoHover);
            }
        }
    }
}