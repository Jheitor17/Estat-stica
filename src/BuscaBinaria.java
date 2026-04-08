public class BuscaBinaria {
    public static int buscar(int[] vetor, int alvo) {
        int esquerda = 0;
        int direita = vetor.length - 1;

        while(esquerda <= direita) {
            int meio = esquerda + (esquerda - direita) / 2;

            if(vetor[meio] == alvo) {
                return meio;
            }

            if(vetor[meio] < alvo) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }

        return -1;
    }
}
