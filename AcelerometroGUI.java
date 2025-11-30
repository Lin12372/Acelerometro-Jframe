import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AcelerometroGUI extends JFrame {

    private JTextField campoCalcado;
    private JTextField campoDistancia;
    private JTextArea areaResultado;
    private JRadioButton opcaoPassosPrimeiro;
    private JRadioButton opcaoTempoPrimeiro;

    public AcelerometroGUI() {
        super("Simulador de Acelerômetro");

        setLayout(new BorderLayout());

        // Painel de entrada
        JPanel painelEntrada = new JPanel(new GridLayout(3, 2, 8, 8));

        painelEntrada.add(new JLabel("Número do calçado:"));
        campoCalcado = new JTextField();
        painelEntrada.add(campoCalcado);

        painelEntrada.add(new JLabel("Distância (em metros):"));
        campoDistancia = new JTextField();
        painelEntrada.add(campoDistancia);

        add(painelEntrada, BorderLayout.NORTH);


        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout());

        JButton btnCalcularTempo = new JButton("Calcular Tempo");
        JButton btnCalcularPassos = new JButton("Calcular Passos");

        painelBotoes.add(btnCalcularTempo);
        painelBotoes.add(btnCalcularPassos);

        add(painelBotoes, BorderLayout.CENTER);


        // Opções de exibição
        JPanel painelOpcoes = new JPanel(new FlowLayout());
        opcaoPassosPrimeiro = new JRadioButton("Mostrar Passos Primeiro", true);
        opcaoTempoPrimeiro = new JRadioButton("Mostrar Tempo Primeiro");

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(opcaoPassosPrimeiro);
        grupo.add(opcaoTempoPrimeiro);

        painelOpcoes.add(opcaoPassosPrimeiro);
        painelOpcoes.add(opcaoTempoPrimeiro);

        add(painelOpcoes, BorderLayout.SOUTH);


        // Área de resultado
        areaResultado = new JTextArea(7, 30);
        areaResultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaResultado);
        add(scroll, BorderLayout.EAST);


        // Evento: calcular passos
        btnCalcularPassos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcular(true);
            }
        });

        // Evento: calcular tempo
        btnCalcularTempo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcular(false);
            }
        });


        // Configuração final da janela
        setSize(650, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    // Função central de cálculo
    private void calcular(boolean calcularPassos) {
        try {
            double calcado = Double.parseDouble(campoCalcado.getText());
            double distancia = Double.parseDouble(campoDistancia.getText());

            // Comprimento do passo
            double comprimentoPasso = calcado * 0.65; // em cm

            // Passos necessários
            double distanciaCm = distancia * 100;
            double passos = distanciaCm / comprimentoPasso;

            // Tempo necessário (5 km/h = 1.388 m/s)
            double velocidade = 1.388;
            double tempoSegundos = distancia / velocidade;

            int minutos = (int) (tempoSegundos / 60);
            int segundos = (int) (tempoSegundos % 60);

            String textoPassos = String.format("Passos necessários: %.0f\n", passos);
            String textoTempo = String.format("Tempo estimado: %d min e %d s\n", minutos, segundos);

            // Escolha da ordem de exibição
            if (opcaoPassosPrimeiro.isSelected()) {
                areaResultado.setText(textoPassos + textoTempo);
            } else {
                areaResultado.setText(textoTempo + textoPassos);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite valores válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        new AcelerometroGUI();
    }
}
