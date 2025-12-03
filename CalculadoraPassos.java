public class CalculadoraPassos {
    
    /**
     * Calcula o comprimento médio do passo baseado no número do calçado
     * @param numeroCalcado Número do calçado (ex: 38, 40, 42)
     * @return Comprimento do passo em centímetros
     */
    public static double calcularComprimentoPasso(int numeroCalcado) {
        // Estima o comprimento do passo como 45% do número do calçado
        return numeroCalcado * 0.45; // em cm
    }
    
    /**
     * Calcula a quantidade de passos necessários para percorrer uma distância
     * @param distancia Distância total em metros
     * @param numeroCalcado Número do calçado do usuário
     * @return Quantidade de passos necessários
     */
    public static int calcularQuantidadePassos(double distancia, int numeroCalcado) {
        double comprimentoPasso = calcularComprimentoPasso(numeroCalcado);
        double comprimentoPassoMetros = comprimentoPasso / 100.0; // Convertendo cm para m
        return (int) Math.ceil(distancia / comprimentoPassoMetros);
    }
    
    /**
     * Calcula o tempo estimado para percorrer uma distância
     * @param distancia Distância total em metros
     * @return Tempo estimado em minutos
     */
    public static double calcularTempoEstimado(double distancia) {
        // Velocidade média de 5 km/h = 5000 m/60 min = 83.333 m/min
        double velocidadeMedia = 5000.0 / 60.0; // metros por minuto
        return distancia / velocidadeMedia; // tempo em minutos
    }
    
    /**
     * Formata o tempo em horas e minutos
     * @param tempoMinutos Tempo em minutos
     * @return String formatada com horas e minutos
     */
    public static String formatarTempo(double tempoMinutos) {
        int horas = (int) (tempoMinutos / 60);
        int minutos = (int) (tempoMinutos % 60);
        int segundos = (int) ((tempoMinutos * 60) % 60);
        
        if (horas > 0) {
            return String.format("%d hora(s), %d minuto(s) e %d segundo(s)", horas, minutos, segundos);
        } else {
            return String.format("%d minuto(s) e %d segundo(s)", minutos, segundos);
        }
    }
    
    /**
     * Converte distância para metros
     * @param distancia Valor da distância
     * @param unidade "m" para metros ou "km" para quilômetros
     * @return Distância em metros
     */
    public static double converterParaMetros(double distancia, String unidade) {
        if (unidade.equals("km")) {
            return distancia * 1000.0;
        }
        return distancia;
    }
}