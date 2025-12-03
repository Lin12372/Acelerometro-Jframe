public class CalculadoraPassos {
    
    /**
     * Calcula o comprimento médio do passo baseado no número do calçado
     * @param numeroCalcado Número do calçado (ex: 38, 40, 42)
     * @return Comprimento do passo em centímetros
     * @throws IllegalArgumentException se número do calçado for inválido
     */
    public static double calcularComprimentoPasso(int numeroCalcado) {
        // Validação básica - pequena adição
        if (numeroCalcado < 20) {
            throw new IllegalArgumentException("Número do calçado muito pequeno! Mínimo: 20");
        }
        if (numeroCalcado > 50) {
            throw new IllegalArgumentException("Número do calçado muito grande! Máximo: 50");
        }
        
        // Estima o comprimento do passo como 45% do número do calçado
        return numeroCalcado * 0.45; // em cm
    }
    
    /**
     * Calcula a quantidade de passos necessários para percorrer uma distância
     * @param distancia Distância total em metros
     * @param numeroCalcado Número do calçado do usuário
     * @return Quantidade de passos necessários
     * @throws IllegalArgumentException se distância for inválida
     */
    public static int calcularQuantidadePassos(double distancia, int numeroCalcado) {
        // Validações adicionadas
        if (distancia <= 0) {
            throw new IllegalArgumentException("Distância deve ser maior que zero!");
        }
        if (distancia > 1000000) { // 1000 km
            throw new IllegalArgumentException("Distância muito grande! Máximo: 1.000.000 metros");
        }
        
        double comprimentoPasso = calcularComprimentoPasso(numeroCalcado);
        double comprimentoPassoMetros = comprimentoPasso / 100.0; // Convertendo cm para m
        return (int) Math.ceil(distancia / comprimentoPassoMetros);
    }
    
    /**
     * Calcula o tempo estimado para percorrer uma distância
     * @param distancia Distância total em metros
     * @return Tempo estimado em minutos
     * @throws IllegalArgumentException se distância for inválida
     */
    public static double calcularTempoEstimado(double distancia) {
        // Validação adicionada
        if (distancia <= 0) {
            throw new IllegalArgumentException("Distância deve ser maior que zero!");
        }
        
        // Velocidade média de 5 km/h = 5000 m/60 min = 83.333 m/min
        double velocidadeMedia = 5000.0 / 60.0; // metros por minuto
        return distancia / velocidadeMedia; // tempo em minutos
    }
    
    /**
     * Formata o tempo em horas e minutos (melhorado)
     * @param tempoMinutos Tempo em minutos
     * @return String formatada com horas e minutos
     */
    public static String formatarTempo(double tempoMinutos) {
        // Arredonda para baixo para evitar "60 minutos"
        int totalSegundos = (int) Math.round(tempoMinutos * 60);
        int horas = totalSegundos / 3600;
        int minutos = (totalSegundos % 3600) / 60;
        int segundos = totalSegundos % 60;
        
        StringBuilder resultado = new StringBuilder();
        
        // Formata horas se houver
        if (horas > 0) {
            resultado.append(horas).append(" hora");
            if (horas > 1) resultado.append("s");
        }
        
        // Formata minutos
        if (minutos > 0) {
            if (horas > 0) resultado.append(", ");
            resultado.append(minutos).append(" minuto");
            if (minutos > 1) resultado.append("s");
        }
        
        // Mostra segundos apenas se for menos de 10 minutos
        if (segundos > 0 && tempoMinutos < 10) {
            if (horas > 0 || minutos > 0) resultado.append(" e ");
            resultado.append(segundos).append(" segundo");
            if (segundos > 1) resultado.append("s");
        }
        
        // Caso tempo seja zero
        if (resultado.length() == 0) {
            resultado.append("0 segundos");
        }
        
        return resultado.toString();
    }
    
    /**
     * Converte distância para metros
     * @param distancia Valor da distância
     * @param unidade "m" para metros ou "km" para quilômetros
     * @return Distância em metros
     * @throws IllegalArgumentException se unidade for inválida
     */
    public static double converterParaMetros(double distancia, String unidade) {
        if (unidade.equalsIgnoreCase("km") || unidade.equals("quilômetros")) {
            return distancia * 1000.0;
        } else if (unidade.equalsIgnoreCase("m") || unidade.equals("metros")) {
            return distancia;
        } else {
            throw new IllegalArgumentException("Unidade inválida! Use 'metros' ou 'quilômetros'");
        }
    }
    
    /**
     * NOVO MÉTODO: Calcula velocidade média em km/h
     * @param distanciaMetros Distância em metros
     * @param tempoMinutos Tempo em minutos
     * @return Velocidade média em km/h
     */
    public static double calcularVelocidadeMedia(double distanciaMetros, double tempoMinutos) {
        if (tempoMinutos <= 0) return 0;
        double distanciaKm = distanciaMetros / 1000.0;
        double tempoHoras = tempoMinutos / 60.0;
        return distanciaKm / tempoHoras;
    }
    
    /**
     * NOVO MÉTODO: Calcula passos por minuto
     * @param distanciaMetros Distância em metros
     * @param tempoMinutos Tempo em minutos
     * @param numeroCalcado Número do calçado
     * @return Passos por minuto
     */
    public static double calcularPassosPorMinuto(double distanciaMetros, double tempoMinutos, int numeroCalcado) {
        if (tempoMinutos <= 0) return 0;
        int passos = calcularQuantidadePassos(distanciaMetros, numeroCalcado);
        return passos / tempoMinutos;
    }
}