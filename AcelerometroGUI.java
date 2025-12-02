import javax.swing.*;
import java.awt.*;

public class AcelerometroGUI extends JFrame {

    private JTextField campoCalcado;
    private JTextField campoDistancia;
    private JTextArea areaResultado;

    private JRadioButton opcaoPassosPrimeiro;
    private JRadioButton opcaoTempoPrimeiro;

    // Armazenamento interno
    private String textoPassos = "";
    private String textoTempo = "";
    private boolean calculoValido = false;

    public AcelerometroGUI() {
        super("Simulador de Acelerômetro");

        setLayout(new BorderLayout(10, 10));

        // ===============================
        // Painel principal central
        // ===============================
        JPanel painelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ===============================
        // Linha 1 - Campos de entrada
        // ===============================
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelCentral.add(new JLabel("Número do calçado:"), gbc);

        gbc.gridx = 1;
        campoCalcado = new JTextField(10);
        painelCentral.add(campoCalcado, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        painelCentral.add(new JLabel("Distância (m):"), gbc);

        gbc.gridx = 1;
        campoDistancia = new JTextField(10);
        painelCentral.add(campoDistancia, gbc);

        // ===============================
        // Linha 2 - Botão Calcular
        // ===============================
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JButton btnCalcular = new JButton("Calcular Resultado");
        painelCentral.add(btnCalcular, gbc);

        // ===============================
        // Linha 3 - Opções de ordem
        // ===============================
        JPanel painelOpcoes = new JPanel();
        opcaoPassosPrimeiro = new JRadioButton("Mostrar Passos Primeiro", true);
        opcaoTempoPrimeiro = new JRadioButton("Mostrar Tempo Primeiro");

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(opcaoPassosPrimeiro);
        grupo.add(opcaoTempoPrimeiro);

        painelOpcoes.add(opcaoPassosPrimeiro);
        painelOpcoes.add(opcaoTempoPrimeiro);

        gbc.gridy = 3;
        painelCentral.add(painelOpcoes, gbc);

        add(painelCentral, BorderLayout.CENTER);

        // ===============================
        // Painel de resultados (direita)
        // ===============================
        areaResultado = new JTextArea(14, 28);
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(areaResultado);
        add(scroll, BorderLayout.EAST);

        // ===============================
        // Eventos
        // ===============================
        btnCalcular.addActionListener(e -> calcularTudo());
        opcaoPassosPrimeiro.addActionListener(e -> atualizarOrdem());
        opcaoTempoPrimeiro.addActionListener(e -> atualizarOrdem());

        // ===============================
        // Configuração final da janela
        // ===============================
        setSize(850, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // =======================================================
    // Cálculo completo
    // =======================================================
    private void calcularTudo() {
        try {
            double calcado = Double.parseDouble(campoCalcado.getText());
            double distancia = Double.parseDouble(campoDistancia.getText());

            double comprimentoPasso = (calcado * 0.65) / 100.0;
            double passos = distancia / comprimentoPasso;

            double velocidade = 1.388; // 5 km/h
            double tempoSegundos = distancia / velocidade;

            int minutos = (int) (tempoSegundos / 60);
            int segundos = (int) (tempoSegundos % 60);

            textoPassos = String.format("Passos necessários: %.0f\n", passos);
            textoTempo = String.format("Tempo estimado: %d min %d s\n", minutos, segundos);

            calculoValido = true;

            atualizarOrdem();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Digite valores válidos!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // =======================================================
    // Atualiza o texto conforme a ordem selecionada
    // =======================================================
    private void atualizarOrdem() {
        if (!calculoValido) return;
        areaResultado.setText(formatarResultado());
    }

    // =======================================================
    // Monta o texto final sem duplicação de código
    // =======================================================
    private String formatarResultado() {
        return opcaoPassosPrimeiro.isSelected()
                ? textoPassos + textoTempo
                : textoTempo + textoPassos;
    }

    public static void main(String[] args) {
        new AcelerometroGUI();
    }
}
