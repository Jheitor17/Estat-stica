public class Estatistica {

    public static double media(double[] valores) {
        double soma = 0;
        for (double v : valores) soma += v;
        return soma / valores.length;
    }

    public static double desvioPadrao(double[] valores) {
        double media = media(valores);
        double somaDiferencas = 0;
        for (double v : valores) {
            double diferenca = v - media;
            somaDiferencas += diferenca * diferenca;
        }
        return Math.sqrt(somaDiferencas / valores.length);
    }
}
