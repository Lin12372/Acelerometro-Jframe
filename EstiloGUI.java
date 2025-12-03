import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class EstiloGUI {
    
    public static void aplicarEstilo(JFrame frame) {
        // Definir cores principais
        Color corFundo = new Color(240, 248, 255); // Azul claro
        Color corDestaque = new Color(70, 130, 180); // Azul aço
        Color corTexto = new Color(25, 25, 112); // Azul marinho
        Color corBotao1 = new Color(100, 149, 237); // Azul claro (tempo)
        Color corBotao2 = new Color(32, 178, 170); // Verde água (passos)
        Color corBotao3 = new Color(211, 211, 211); // Cinza claro (limpar)
        Color corBotaoHover = new Color(65, 105, 225); // Azul royal
        
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
                label.setFont(new Font("Segoe UI", Font.BOLD, 12));
            }
            
            // Aplicar estilo a JTextFields
            else if (comp instanceof JTextField) {
                JTextField textField = (JTextField) comp;
                textField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                textField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(corDestaque, 1),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)
                ));
                textField.setBackground(Color.WHITE);
            }
            
            // Aplicar estilo a JComboBox
            else if (comp instanceof JComboBox) {
                JComboBox<?> comboBox = (JComboBox<?>) comp;
                comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                comboBox.setBackground(Color.WHITE);
                comboBox.setBorder(BorderFactory.createLineBorder(corDestaque, 1));
            }
            
            // Aplicar estilo a JButtons
            else if (comp instanceof JButton) {
                JButton botao = (JButton) comp;
                botao.setFont(new Font("Segoe UI", Font.BOLD, 12));
                botao.setForeground(Color.WHITE);
                botao.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(corDestaque, 1),
                    BorderFactory.createEmptyBorder(8, 15, 8, 15)
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
                    botao.setForeground(Color.BLACK);
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
                            botao.setForeground(Color.BLACK);
                        }
                    }
                });
            }
            
            // Aplicar estilo a JTextArea
            else if (comp instanceof JTextArea) {
                JTextArea textArea = (JTextArea) comp;
                textArea.setFont(new Font("Consolas", Font.PLAIN, 12));
                textArea.setBackground(new Color(253, 253, 253));
                textArea.setForeground(corTexto);
                textArea.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(corDestaque, 1),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
            }
            
            // Aplicar estilo a TitledBorders
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                Border border = panel.getBorder();
                if (border instanceof TitledBorder) {
                    TitledBorder titledBorder = (TitledBorder) border;
                    titledBorder.setTitleFont(new Font("Segoe UI", Font.BOLD, 13));
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