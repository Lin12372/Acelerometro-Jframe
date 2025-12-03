import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AcelerometroGUI extends JFrame {
    private JTextField campoNumeroCalcado;
    private JTextField campoDistancia;
    private JComboBox<String> comboUnidade;
    private JTextArea areaResultado;
    private JButton btnCalcularTempo;
    private JButton btnCalcularPassos;
    private JButton btnLimpar;
    
    public AcelerometroGUI() {
        setTitle("SeaSoft - Acelerômetro para Caminhada/Corrida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Painel de entrada
        JPanel painelEntrada = new JPanel(new GridBagLayout());
        painelEntrada.setBorder(BorderFactory.createTitledBorder("Entrada de Dados"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Campo número do calçado
        gbc.gridx = 0; gbc.gridy = 0;
        painelEntrada.add(new JLabel("Número do calçado:"), gbc);
        
        gbc.gridx = 1;
        campoNumeroCalcado = new JTextField(10);
        painelEntrada.add(campoNumeroCalcado, gbc);
        
        // Campo distância
        gbc.gridx = 0; gbc.gridy = 1;
        painelEntrada.add(new JLabel("Distância:"), gbc);
        
        gbc.gridx = 1;
        JPanel painelDistancia = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        campoDistancia = new JTextField(10);
        painelDistancia.add(campoDistancia);
        
        comboUnidade = new JComboBox<>(new String[]{"metros", "quilômetros"});
        painelDistancia.add(comboUnidade);
        painelEntrada.add(painelDistancia, gbc);
        
        add(painelEntrada, BorderLayout.NORTH);
        
        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        btnCalcularTempo = new JButton("Calcular Tempo Gasto");
        btnCalcularPassos = new JButton("Calcular Quantidade de Passos");
        btnLimpar = new JButton("Limpar");
        
        painelBotoes.add(btnCalcularTempo);
        painelBotoes.add(btnCalcularPassos);
        painelBotoes.add(btnLimpar);
        
        add(painelBotoes, BorderLayout.CENTER);
        
        // Painel de resultado
        JPanel painelResultado = new JPanel(new BorderLayout());
        painelResultado.setBorder(BorderFactory.createTitledBorder("Resultado"));
        
        areaResultado = new JTextArea(8, 40);
        areaResultado.setEditable(false);
        areaResultado.setLineWrap(true);
        areaResultado.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(areaResultado);
        painelResultado.add(scrollPane, BorderLayout.CENTER);
        
        add(painelResultado, BorderLayout.SOUTH);
        
        // Configurar listeners
        configurarListeners();
        
        pack();
        setLocationRelativeTo(null);
        aplicarEstilo();
    }
    
    private void configurarListeners() {
        btnCalcularTempo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularTempo();
            }
        });
        
        btnCalcularPassos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularPassos();
            }
        });
        
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparResultados();
            }
        });
    }
    
    private void calcularTempo() {
        try {
            // Obter e validar entradas
            int numeroCalcado = Integer.parseInt(campoNumeroCalcado.getText());
            double distancia = Double.parseDouble(campoDistancia.getText());
            String unidade = (String) comboUnidade.getSelectedItem();
            
            // Converter distância para metros
            double distanciaMetros = CalculadoraPassos.converterParaMetros(distancia, 
                unidade.equals("quilômetros") ? "km" : "m");
            
            // Calcular tempo estimado
            double tempoMinutos = CalculadoraPassos.calcularTempoEstimado(distanciaMetros);
            String tempoFormatado = CalculadoraPassos.formatarTempo(tempoMinutos);
            double comprimentoPasso = CalculadoraPassos.calcularComprimentoPasso(numeroCalcado);
            
            // Construir resultado apenas do tempo
            StringBuilder resultado = new StringBuilder();
            resultado.append("=== CÁLCULO DE TEMPO GASTO ===\n\n");
            resultado.append(String.format("Número do calçado: %d\n", numeroCalcado));
            resultado.append(String.format("Distância: %.2f %s\n", distancia, unidade));
            resultado.append(String.format("Comprimento médio do passo: %.2f cm\n\n", comprimentoPasso));
            resultado.append("🏃 TEMPO ESTIMADO:\n");
            resultado.append("   " + tempoFormatado);
            
            areaResultado.setText(resultado.toString());
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, insira valores numéricos válidos!", 
                "Erro de Entrada", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Ocorreu um erro: " + ex.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void calcularPassos() {
        try {
            // Obter e validar entradas
            int numeroCalcado = Integer.parseInt(campoNumeroCalcado.getText());
            double distancia = Double.parseDouble(campoDistancia.getText());
            String unidade = (String) comboUnidade.getSelectedItem();
            
            // Converter distância para metros
            double distanciaMetros = CalculadoraPassos.converterParaMetros(distancia, 
                unidade.equals("quilômetros") ? "km" : "m");
            
            // Calcular quantidade de passos
            int quantidadePassos = CalculadoraPassos.calcularQuantidadePassos(distanciaMetros, numeroCalcado);
            double comprimentoPasso = CalculadoraPassos.calcularComprimentoPasso(numeroCalcado);
            
            // Construir resultado apenas dos passos
            StringBuilder resultado = new StringBuilder();
            resultado.append("=== CÁLCULO DE QUANTIDADE DE PASSOS ===\n\n");
            resultado.append(String.format("Número do calçado: %d\n", numeroCalcado));
            resultado.append(String.format("Distância: %.2f %s\n", distancia, unidade));
            resultado.append(String.format("Comprimento médio do passo: %.2f cm\n\n", comprimentoPasso));
            resultado.append("👣 QUANTIDADE DE PASSOS NECESSÁRIOS:\n");
            resultado.append(String.format("   %d passos", quantidadePassos));
            
            areaResultado.setText(resultado.toString());
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, insira valores numéricos válidos!", 
                "Erro de Entrada", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Ocorreu um erro: " + ex.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limparResultados() {
        areaResultado.setText("");
        campoNumeroCalcado.setText("");
        campoDistancia.setText("");
        comboUnidade.setSelectedIndex(0);
    }
    
    private void aplicarEstilo() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Usar look and feel padrão se não conseguir aplicar
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AcelerometroGUI gui = new AcelerometroGUI();
                EstiloGUI.aplicarEstilo(gui);
                gui.setVisible(true);
            }
        });
    }
}